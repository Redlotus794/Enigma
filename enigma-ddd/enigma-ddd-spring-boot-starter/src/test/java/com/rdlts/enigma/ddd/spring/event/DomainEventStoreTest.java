package com.rdlts.enigma.ddd.spring.event;

import com.rdlts.enigma.ddd.spring.test.domain.Shield;
import com.rdlts.enigma.ddd.spring.test.domain.ShieldSavedEvent;
import com.rdlts.enigma.ddd.spring.utils.FastJsonUtils;
import org.junit.jupiter.api.Test;

class DomainEventStoreTest {

    @Test
    void testBuildEvent() {
        final ShieldSavedEvent next = ShieldSavedEvent.next();
        DomainEventStore<Shield> domainEventStore = DomainEventStore.of(next);
        System.out.println(FastJsonUtils.prettyJson(domainEventStore));
    }
}