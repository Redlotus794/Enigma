package com.rdlts.enigma.ddd.spring.event;

import com.rdlts.enigma.ddd.core.DomainEventReproducer;
import com.rdlts.enigma.ddd.spring.EnigmaSpringBootBasedTest;
import com.rdlts.enigma.ddd.spring.exception.EnigmaDDDSpringRuntimeException;
import com.rdlts.enigma.ddd.spring.test.domain.ShieldSavedEvent;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;

class EnigmaDomainEventReproducerTest extends EnigmaSpringBootBasedTest {

    @Autowired
    DomainEventReproducer domainEventReproducer;

    @Test
    void testInstance() {
        Assertions.assertTrue(domainEventReproducer instanceof EnigmaDomainEventReproducer);
        Assertions.assertEquals(Charset.defaultCharset(), Charset.forName("UTF-8"));
    }

    @Test
    @SneakyThrows
    void testRebuildSuccess() {
        final Enumeration<URL> systemResources = ClassLoader.getSystemResources("data/shieldSavedEvent_test_1.json");
        while (systemResources.hasMoreElements()) {
            final URL url = systemResources.nextElement();
            final ShieldSavedEvent shieldSavedEvent = domainEventReproducer.rebuild(new File(url.toURI()), Charset.defaultCharset());
            Assertions.assertEquals("test_1", shieldSavedEvent.getEventContent().getShieldId().getVal());
            Assertions.assertEquals(1, shieldSavedEvent.getEventContent().getEntityVersion().getVersion());
        }
    }

    @Test
    @SneakyThrows
    void test_array_rebuild_shouldThrowIllegalArgumentException() {
        final Enumeration<URL> systemResources = ClassLoader.getSystemResources("data/shieldSavedEvent_list_test1_test2.json");
        while (systemResources.hasMoreElements()) {
            final URL url = systemResources.nextElement();
            Assertions.assertThrows(EnigmaDDDSpringRuntimeException.class,
                    () -> domainEventReproducer.rebuild(new File(url.toURI()), Charset.defaultCharset()));
        }
    }

    @Test
    void test_noFile_rebuild_shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(EnigmaDDDSpringRuntimeException.class,
                () -> domainEventReproducer.rebuild(new File(""), Charset.defaultCharset()));

        Assertions.assertThrows(EnigmaDDDSpringRuntimeException.class,
                () -> domainEventReproducer.rebuild(null, Charset.defaultCharset()));
    }
}