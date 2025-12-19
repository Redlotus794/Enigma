package com.rdlts.enigma.ddd.spring.utils;

import com.alibaba.fastjson2.JSON;
import com.rdlts.enigma.ddd.spring.test.domain.Shield;
import com.rdlts.enigma.ddd.spring.test.domain.ShieldId;
import com.rdlts.enigma.ddd.spring.test.domain.ShieldSavedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FastJsonUtilsTest {

    @Test
    void testPrettyJson() {
        Shield shield = new Shield(ShieldId.of("test"));
        ShieldSavedEvent shieldSavedEvent = new ShieldSavedEvent(shield);
        System.out.println(FastJsonUtils.prettyJson(shieldSavedEvent));
    }

    @Test
    void testValueObject() {
        ShieldId shieldID = new ShieldId("test");
        final ShieldId shieldId = FastJsonUtils.parseObject(JSON.toJSONString(shieldID), ShieldId.class);
        Assertions.assertEquals(shieldId, shieldID);
    }
}