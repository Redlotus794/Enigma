package io.github.redlotus794.enigma.ddd.spring.event.autolog;

import io.github.redlotus794.enigma.EnigmaSpringBootBasedTest;
import io.github.redlotus794.enigma.ddd.spring.test.infrastructure.AutoLogRepository;
import io.github.redlotus794.enigma.ddd.spring.test.infrastructure.NonRepositoryAutoLog;
import io.github.redlotus794.enigma.ddd.spring.test.infrastructure.ShieldRepository;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

class RepositoryEventAutoLogAspectTest extends EnigmaSpringBootBasedTest {

    @Autowired
    AutoLogRepository autoLogRepository;
    @Autowired
    NonRepositoryAutoLog nonRepositoryAutoLog;

    @Test
    void testSaveNullEntity() {
        autoLogRepository.save(null);
    }

    @Test
    void testRemoveNullEntity() {
        autoLogRepository.remove(null);
    }

    @Test
    void testNonRepositoryAutoLog() {
        nonRepositoryAutoLog.doSomething();
    }

    @Test
    @SneakyThrows
    void testException() {
        RepositoryEventAutoLogAspect repositoryEventAutoLogAspect = new RepositoryEventAutoLogAspect(null);
        ProceedingJoinPoint mockJoinPoint = mock(ProceedingJoinPoint.class);
        doThrow(new RuntimeException("mock exception")).when(mockJoinPoint).getSignature();
        // 测试exception
        repositoryEventAutoLogAspect.around(mockJoinPoint);

        final Signature mockSignature = mock(Signature.class);
        doReturn(mockSignature).when(mockJoinPoint).getSignature();
        doReturn(new ShieldRepository()).when(mockJoinPoint).getTarget();
        // 测试signature.getName的额外情况
        repositoryEventAutoLogAspect.around(mockJoinPoint);
    }
}