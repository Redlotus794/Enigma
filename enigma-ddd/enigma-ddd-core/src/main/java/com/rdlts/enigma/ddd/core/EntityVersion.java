package com.rdlts.enigma.ddd.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * <a href="https://wcnn2j4xsnan.feishu.cn/wiki/DEBPw3oHoiU0W3kzrmBcFiJ6nnR?fromScene=spaceOverview">
 *     EntityVersion - 实体版本
 *     </a>
 * <p>
 * 值对象, 用以记录实体的版本，实现乐观锁的功能
 * </p>
 * <p>
 * <h3>最佳实践</h3>
 * <ul>
 *  <li>每次需要新增/更新的时候，总是使用next()方法获取下一个版本号</li>
 *  <li>在资源库的保存方法中，根据版本号来判断是否需要新增还是更新（减少一下select库的查询）</li>
 * </p>
 * </ul>
 * @author wangjialong
 * @since 2025/11/27 14:39
 */
@Data
@EqualsAndHashCode
@ToString
public class EntityVersion implements Serializable {

    /**
     * 初始版本，可用于资源库判断实体是否是新增或更新（0代表新增实体，其他代表更新）
     */
    public static final EntityVersion ZERO_VERSION = new EntityVersion(0L);

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 版本号
     */
    private long version;

    /**
     * 构造器
     */
    public EntityVersion() {
        this(0L);
    }

    /**
     * 构造函数
     * @param version long
     */
    public EntityVersion(long version) {
        this.version = version;
    }

    /**
     * 初始版本
     *
     * @see EntityVersion
     * @return EntityVersion
     */
    public static EntityVersion zeroVersion() {
        return ZERO_VERSION;
    }

    /**
     * 下一个版本号
     * @return new EntityVersion
     */
    public EntityVersion next() {
        return new EntityVersion(this.version + 1);
    }
}
