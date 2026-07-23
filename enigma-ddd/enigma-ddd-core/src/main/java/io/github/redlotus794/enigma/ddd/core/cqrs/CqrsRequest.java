package io.github.redlotus794.enigma.ddd.core.cqrs;

/**
 * CqrsRequest - CQRS 请求模型
 * <p>
 * 用于表达 CQRS 体系中的请求概念，是 {@link Command} 和 {@link Query} 的共同父类型。
 * 应用层可基于该抽象统一表达“进入系统的一次请求”，再按写操作或读操作细分为 Command 和 Query。
 * </p>
 *
 * @see CQRS
 * @see Command
 * @see Query
 * @author wangjialong
 * @since 2026/06/04
 */
public interface CqrsRequest extends CQRS {
}
