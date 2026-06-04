package io.github.redlotus794.enigma.ddd.core;

/**
 * <a href="file:///Users/wangjialong/github/Enigma/docs/domain-driven-design/html/strategy-design/集成方式.html">
 *     Customer / Supplier 中 Customer 侧定义的 Provider 抽象
 * </a>
 * <p>
 * Provider 用于表达 Customer 限界上下文对 Supplier 限界上下文的能力诉求。
 * 该抽象应由 Customer 在领域层或明确的领域契约层定义，
 * 再由基础设施层、集成层或防腐层提供具体实现，
 * 以隔离 Supplier 的 API、SDK、DTO 和技术实现细节。
 * </p>
 * <p>
 * Provider 本身只作为概念标记接口，不约束具体方法签名。
 * 业务上下文应按统一语言定义专用 Provider，例如 {@code VehicleSeriesProvider}、{@code CharacterSnapshotProvider}。
 * </p>
 *
 * @author wangjialong
 * @since 2026/06/04
 */
public interface Provider {
}
