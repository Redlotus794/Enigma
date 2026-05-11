package com.rdlts.enigma.test.application.service;

import com.rdlts.enigma.test.domain.valueobject.BracerQuestRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BracerQuestEvaluationServiceTest {

    private final BracerQuestEvaluationService service = new BracerQuestEvaluationService();

    @Test
    void shouldReturnBRankWhenHighCompletionButWithCivilianCasualties() {
        Assertions.assertEquals(BracerQuestRank.B, service.evaluateRank(90, true));
        Assertions.assertEquals(BracerQuestRank.B, service.evaluateRank(100, true));
    }

    @Test
    void shouldReturnCRankWhenCompletionIsLowAndWithCivilianCasualties() {
        Assertions.assertEquals(BracerQuestRank.C, service.evaluateRank(89, true));
    }

    @Test
    void shouldReturnSRankWhenCompletionIsExceptionalAndWithoutCivilianCasualties() {
        Assertions.assertEquals(BracerQuestRank.S, service.evaluateRank(95, false));
        Assertions.assertEquals(BracerQuestRank.S, service.evaluateRank(100, false));
    }

    @Test
    void shouldReturnARankWhenCompletionIsHighAndWithoutCivilianCasualties() {
        Assertions.assertEquals(BracerQuestRank.A, service.evaluateRank(80, false));
        Assertions.assertEquals(BracerQuestRank.A, service.evaluateRank(94, false));
    }

    @Test
    void shouldReturnBRankWhenCompletionIsQualifiedAndWithoutCivilianCasualties() {
        Assertions.assertEquals(BracerQuestRank.B, service.evaluateRank(60, false));
        Assertions.assertEquals(BracerQuestRank.B, service.evaluateRank(79, false));
    }

    @Test
    void shouldReturnCRankWhenCompletionIsLowAndWithoutCivilianCasualties() {
        Assertions.assertEquals(BracerQuestRank.C, service.evaluateRank(59, false));
    }
}

