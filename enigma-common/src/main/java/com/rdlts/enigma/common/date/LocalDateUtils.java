package com.rdlts.enigma.common.date;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * LocalDateUtils
 * 本地日期工具类
 *
 * @see LocalDate Java8中提供的本地日期类型
 * @see DateTimeFormatter 日期格式化对象，包含了常用的日期格式化
 * @see DateTimeFormatter#ISO_LOCAL_DATE => yyyy-MM-dd
 *
 * @author wangjialong
 * @since 2025/11/18 09:49
 */
public final class LocalDateUtils {

    /**
     * 本地日期格式化 —> DateTimeFormatter
     * <pre>
     *     {@code
     *     LocalDate localDate = LocalDate.now();
     *     LocalDateUtils.format(localDate, DateTimeFormatter.ISO_LOCAL_DATE); => "2025-11-18"
     *     LocalDateUtils.format(localDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")); => "2025-11-18"
     *     LocalDateUtils.format(localDate, DateTimeFormatter.ofPattern("yyyy年MM月dd日")); => "2025年11月18日"
     *     LocalDateUtils.format(null, DateTimeFormatter.ofPattern("yyyy/MM/dd")); => Optional empty
     *     }
     * </pre>
     * @param localDate LocalDate 可为空
     * @param dateTimeFormatter DateTimeFormatter 必填
     * @return Optional String
     */
    public static Optional<String> format(@Nullable LocalDate localDate, @Nonnull DateTimeFormatter dateTimeFormatter) {
        if (localDate == null) {
            return Optional.empty();
        }
        return Optional.of(localDate.format(dateTimeFormatter));
    }
}
