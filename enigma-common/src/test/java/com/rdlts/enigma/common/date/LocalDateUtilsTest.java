package com.rdlts.enigma.common.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LocalDateUtilsTest {

    @Test
    @SuppressWarnings("all")
    public void init() {
        // for coverage
        LocalDateUtils localDateUtils = new LocalDateUtils();
        Assertions.assertNotNull(localDateUtils);
    }

    /**
     * 格式化测试
     * @see LocalDateUtils#format(LocalDate, DateTimeFormatter)
     */
    @Test
    public void format() {
        LocalDate localDate = LocalDate.of(2025, 11, 18);
        Assertions.assertEquals("2025-11-18", LocalDateUtils.format(localDate, DateTimeFormatter.ISO_LOCAL_DATE).orElse(null));
        Assertions.assertEquals("2025-11-18", LocalDateUtils.format(localDate,  DateTimeFormatter.ofPattern("yyyy-MM-dd")).orElse(null));
        Assertions.assertEquals("2025年11月18日", LocalDateUtils.format(localDate,  DateTimeFormatter.ofPattern("yyyy年MM月dd日")).orElse(null));
        Assertions.assertNull(LocalDateUtils.format(null, DateTimeFormatter.ISO_LOCAL_DATE).orElse(null));
        Assertions.assertEquals("", LocalDateUtils.format(null, DateTimeFormatter.ISO_LOCAL_DATE).orElse(""));
    }
}