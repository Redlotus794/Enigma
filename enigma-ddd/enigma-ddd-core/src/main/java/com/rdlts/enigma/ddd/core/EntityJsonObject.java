package com.rdlts.enigma.ddd.core;

import com.rdlts.enigma.ddd.core.exception.EnigmaDDDRuntimeException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * EntityJsonObject
 * copy from Arcus Project
 * <p>
 * 表示领域实体的 JSON 可序列化格式。
 * 提供方法用于在 JSON 对象与领域实体之间进行转换。通常用于在系统或层之间以序列化形式传递实体数据。
 * 尽可能的使用基本类型，避免在转换的时候出现异常情况。
 *
 * <h2>使用场景</h2>
 * <ul>
 *   <li>序列化：将领域实体转换为 JSON 对象以便传输或存储。</li>
 *   <li>反序列化：从 JSON 表示重建领域实体。</li>
 * </ul>
 *
 * @param <DE> 领域实体类型
 * @author wangjialong
 * @since 2025/7/21 16:26
 */
public abstract class EntityJsonObject<DE extends DomainEntity<?>> {

    public EntityJsonObject() {
        // 默认构造函数
    }

    /**
     * 创建一个新的 EntityJsonObject 实例，并使用给定的实体对象进行初始化。
     * @param entity 领域实体对象
     */
    public EntityJsonObject(@Nullable DE entity) {
        // 带实体参数的构造函数
        this.asJson(entity);
    }

    /**
     * 转换为实体的具体实现
     *
     * @return T 新的实体对象
     */
    public abstract DE toEntity();
    
    /**
     * 实体转为当前Json对象并赋值
     *
     * @param entity T
     */
    public abstract void asJson(@Nullable DE entity);

    /**
     * 提供一个静态方法，创建一个新的 EntityJsonObject 实例，并从给定的领域实体转换。
     * 建议在具体的应用代码中使用RuntimeException封装异常.
     *
     * @see EntityJsonObject#EntityJsonObject(DomainEntity)
     * @param ejoClass EJO类型
     * @param entity 实体对象
     * @return EntityJsonObject<T> 新的 JSON 对象实例
     * @param <DE> 领域实体类型
     * @param <EJO> EntityJsonObject 类型
     * @throws EnigmaDDDRuntimeException 如果在创建实例或转换过程中发生错误
     */
    public static <DE extends DomainEntity<?>, EJO extends EntityJsonObject<DE>> EJO toEntityJson(
        @Nonnull Class<EJO> ejoClass,
        @Nonnull DE entity
    ) throws EnigmaDDDRuntimeException {
        try {
            // 创建 EntityJsonObject 的实例并从实体转换
            final Constructor<EJO> constructor = ejoClass.getDeclaredConstructor(entity.getClass());
            return constructor.newInstance(entity);
        } catch (NoSuchMethodException noSuchMethodException) {
            // 未找到构造方法，请查看是否有覆盖构造方法
            throw new EnigmaDDDRuntimeException("No such constructor found in " + ejoClass.getName(), noSuchMethodException);
        } catch (InvocationTargetException invocationTargetException) {
            // 可能是由于参数不匹配或其他反射问题
            throw new EnigmaDDDRuntimeException("Parameters not match for constructor in " + ejoClass.getName(), invocationTargetException);
        } catch (InstantiationException instantiationException) {
            // 可能是由于抽象类或接口, 不可实例化抽象类和接口
            throw new EnigmaDDDRuntimeException("Cannot instantiate " + ejoClass.getName() + ", it may be an abstract class or interface", instantiationException);
        } catch (IllegalAccessException illegalAccessException) {
            // 无法访问构造方法，可能是由于访问修饰符限制
            throw new EnigmaDDDRuntimeException("Cannot access constructor in " + ejoClass.getName(), illegalAccessException);
        }
    }

    /**
     * 将 EntityJsonObject 转换为领域实体。
     *
     * @param entityJsonObject EntityJsonObject 实例
     * @return DE 领域实体对象
     * @param <DE> 领域实体类型
     */
    @Nonnull
    public static <DE extends DomainEntity<?>> DE toEntity(@Nonnull EntityJsonObject<DE> entityJsonObject) {
        return entityJsonObject.toEntity();
    }
}
