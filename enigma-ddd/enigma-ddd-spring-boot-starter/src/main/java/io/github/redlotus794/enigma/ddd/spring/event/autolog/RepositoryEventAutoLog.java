package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import java.lang.annotation.*;

/**
 * RepositoryEventAutoLog
 * 资源库事件自动记录
 *
 * @see io.github.redlotus794.enigma.ddd.core.DomainRepository
 * @author wangjialong
 * @since 2025/12/17 10:32
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RepositoryEventAutoLog {

}
