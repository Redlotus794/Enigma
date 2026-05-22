package io.github.redlotus794.enigma.ddd.spring.event;

import io.github.redlotus794.enigma.ddd.spring.test.domain.Shield;
import io.github.redlotus794.enigma.ddd.spring.test.domain.ShieldSavedEvent;
import io.github.redlotus794.enigma.ddd.spring.utils.FastJsonUtils;
import org.junit.jupiter.api.Test;

class DomainEventStoreTest {

    @Test
    void testBuildEvent() {
        final ShieldSavedEvent next = ShieldSavedEvent.next();
        DomainEventStore<Shield> domainEventStore = DomainEventStore.of(next);
        System.out.println(FastJsonUtils.prettyJson(domainEventStore));
    }
}