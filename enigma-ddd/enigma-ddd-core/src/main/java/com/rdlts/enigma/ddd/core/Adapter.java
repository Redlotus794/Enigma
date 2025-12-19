package com.rdlts.enigma.ddd.core;

/**
 * Adapter - 适配器
 * copy from Arcus Project
 * <p>
 *  关于适配器模式 Adapter Pattern: <br>
 *  在DDD（领域驱动设计）中，适配器模式（Adapter Pattern）主要用于隔离和解耦不同层之间的实现细节。<br>
 *  它的核心作用是将领域层与基础设施层（如数据库、消息中间件、外部服务等）进行解耦，使领域模型不依赖于具体的技术实现。
 *
 *  <h3>命名规则：</h3>
 *  <p>
 *  标准格式 [Adaptee]To[Target]Adapter: 既能明确表达适配方向，又能避免命名冲突。<br>
 *  简化格式 [Target]Adapter：使用者明确知道需要适配的目标类型。
 *  </p>
 *
 *
 *  <h3>应用：</h3>
 *  <p>
 *  此类仅作为建议类。实际业务应用中，以Target命名的Adapter，可以从多个Adaptee中进行适配，定义在一个类中。
 *  </p>
 * </p>
 *
 * @param <Adaptee> Adaptee - 源类型，通常是需要被适配的类型, 实体或者聚合对象等
 * @param <Target> Target - 目标类型，通常是适配后的类型
 * @author wangjialong
 * @since 2025-06-27
 */
public interface Adapter<Adaptee, Target> {

    /**
     * 适配方法，将 Adaptee 转换为 Target
     * @param adaptee Adaptee
     * @return Target
     */
    Target adapt(Adaptee adaptee);
}
