package io.github.redlotus794.enigma.test.application.service;

import io.github.redlotus794.enigma.test.domain.valueobject.BracerQuestRank;
import org.springframework.stereotype.Service;

import org.jspecify.annotations.NonNull;

/**
 * BracerQuestEvaluationService
 * 游击士支援任务评级示例服务。
 *
 * @author wangjialong
 * @since 2026/05/11 16:05
 */
@Service
public class BracerQuestEvaluationService {

    /**
     * 按任务完成度与平民伤亡情况结算支援任务评级。
     *
     * @param completionScore 任务完成度，满分 100
     * @param civilianCasualties 是否造成平民伤亡
     * @return 游击士支援任务评级
     */
    @NonNull
    public BracerQuestRank evaluateRank(final int completionScore, final boolean civilianCasualties) {
        if (civilianCasualties) {
            if (completionScore >= 90) {
                return BracerQuestRank.B;
            }
            return BracerQuestRank.C;
        }

        if (completionScore >= 95) {
            return BracerQuestRank.S;
        }
        if (completionScore >= 80) {
            return BracerQuestRank.A;
        }
        if (completionScore >= 60) {
            return BracerQuestRank.B;
        }
        return BracerQuestRank.C;
    }
}


