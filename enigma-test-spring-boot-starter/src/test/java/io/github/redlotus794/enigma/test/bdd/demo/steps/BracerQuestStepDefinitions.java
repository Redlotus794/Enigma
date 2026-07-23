package io.github.redlotus794.enigma.test.bdd.demo.steps;

import io.github.redlotus794.enigma.test.application.service.BracerQuestEvaluationService;
import io.github.redlotus794.enigma.test.domain.valueobject.BracerQuestRank;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import org.jspecify.annotations.NonNull;

/**
 * BracerQuestStepDefinitions
 * 游击士支援任务评级示例步骤定义。
 *
 * @author wangjialong
 * @since 2026/05/11 16:05
 */
public class BracerQuestStepDefinitions {

    @Autowired
    private BracerQuestEvaluationService bracerQuestEvaluationService;

    private int completionScore;

    private boolean civilianCasualties;

    private BracerQuestRank actualRank;

    @Given("支援任务完成度为 {int}")
    public void givenQuestCompletionScore(final int completionScore) {
        this.completionScore = completionScore;
    }

    @Given("该任务未造成平民伤亡")
    public void givenQuestWithoutCivilianCasualties() {
        this.civilianCasualties = false;
    }

    @Given("该任务造成了平民伤亡")
    public void givenQuestWithCivilianCasualties() {
        this.civilianCasualties = true;
    }

    @When("执行任务评级结算")
    public void whenEvaluateQuestRank() {
        this.actualRank = bracerQuestEvaluationService.evaluateRank(completionScore, civilianCasualties);
    }

    @Then("任务评级应为 {word}")
    public void thenQuestRankShouldBe(@NonNull final String expectedRank) {
        Assertions.assertEquals(BracerQuestRank.valueOf(expectedRank), actualRank);
    }
}


