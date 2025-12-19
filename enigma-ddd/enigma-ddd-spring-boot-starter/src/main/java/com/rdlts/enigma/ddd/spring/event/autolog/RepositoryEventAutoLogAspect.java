package com.rdlts.enigma.ddd.spring.event.autolog;

import com.rdlts.enigma.ddd.core.DomainEntity;
import com.rdlts.enigma.ddd.core.DomainRepository;
import com.rdlts.enigma.ddd.core.event.DomainEventRepository;
import com.rdlts.enigma.ddd.spring.constant.DomainRepositoryConstant;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * <h1>RepositoryEventLogAspect</h1>
 * 自动记录基于领域资源库的保存和删除事件。
 *
 * @author wangjialong
 * @since 2025/12/17 10:33
 */
@Aspect
@Component
@Log4j2
public class RepositoryEventAutoLogAspect {

    public static final String CREATOR = "AutoLog";

    private final DomainEventRepository domainEventRepository;

    @Autowired
    public RepositoryEventAutoLogAspect(DomainEventRepository domainEventRepository) {
        this.domainEventRepository = domainEventRepository;
    }

    /**
     * 资源库完成SAVE/SAVE_ALL/REMOVE/REMOVE_ALL等操作后，自动记录对应的领域事件。
     *
     * @param joinPoint ProceedingJoinPoint
     * @return Object joinPoint的运行结果
     * @throws Throwable e
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Around(value = "@within(com.rdlts.enigma.ddd.spring.event.autolog.RepositoryEventAutoLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceedResult = joinPoint.proceed();
        try {
            final Signature signature = joinPoint.getSignature();
            final Class<?> targetClass = joinPoint.getTarget().getClass();
            if (!DomainRepository.class.isAssignableFrom(targetClass)) {
                log.warn("Skipping non-DomainRepository class: {}", targetClass.getName());
                return proceedResult;
            }

            final Class<? extends DomainRepository> repositoryClass = (Class<? extends DomainRepository>) targetClass;
            final DomainEntity<?> entity;

            switch (signature.getName()) {
                case DomainRepositoryConstant.METHOD_SAVE:
                    entity = getEntity(joinPoint);
                    if (entity == null) {
                        log.warn("Skip save null object event!");
                        break;
                    }
                    DomainSavedEvent domainSavedEvent = new DomainSavedEvent(
                            new DomainSavedEventParam(entity, repositoryClass));
                    domainSavedEvent.setCreatedBy(CREATOR);
                    domainEventRepository.save(domainSavedEvent);
                    break;
                case DomainRepositoryConstant.METHOD_SAVE_ALL:
                    DomainSavedAllEvent domainSavedAllEvent = new DomainSavedAllEvent(
                            new DomainSavedAllEventParam(getEntityList(joinPoint), repositoryClass));
                    domainSavedAllEvent.setCreatedBy(CREATOR);
                    domainEventRepository.save(domainSavedAllEvent);
                    break;
                case DomainRepositoryConstant.METHOD_REMOVE:
                    entity = getEntity(joinPoint);
                    if (entity == null) {
                        log.warn("Skip remove null object event!");
                        break;
                    }
                    DomainRemovedEvent domainRemovedEvent = new DomainRemovedEvent(
                            new DomainRemovedEventParam(entity, repositoryClass));
                    domainRemovedEvent.setCreatedBy(CREATOR);
                    domainEventRepository.save(domainRemovedEvent);
                    break;
                case DomainRepositoryConstant.METHOD_REMOVE_ALL:
                    DomainRemovedAllEvent domainRemovedAllEvent = new DomainRemovedAllEvent(
                            new DomainRemovedAllEventParam(getEntityList(joinPoint), repositoryClass));
                    domainRemovedAllEvent.setCreatedBy(CREATOR);
                    domainEventRepository.save(domainRemovedAllEvent);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            // 防御性编程，事件记录异常不影响业务正常进行
            log.error("Repository Event Auto Log Around Error! {}", e.getMessage());
        }
        return proceedResult;
    }

    public static Collection<DomainEntity> getEntityList(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        return (Collection<DomainEntity>) Array.get(args, 0);
    }

    public static DomainEntity<?> getEntity(JoinPoint joinPoint) {
        return (DomainEntity<?>) Array.get(joinPoint.getArgs(),0);
    }

}
