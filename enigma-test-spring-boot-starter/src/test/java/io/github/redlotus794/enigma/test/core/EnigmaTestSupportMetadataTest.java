package io.github.redlotus794.enigma.test.core;

import io.github.redlotus794.enigma.EnigmaSpringBootBasedTest;
import io.github.redlotus794.enigma.EnigmaTestSpringBootApplication;
import io.github.redlotus794.enigma.common.constant.ProfileConstant;
import io.github.redlotus794.enigma.test.annotation.EnigmaCucumberSpringBootTest;
import io.github.redlotus794.enigma.test.annotation.EnigmaWebSpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.ActiveProfiles;

class EnigmaTestSupportMetadataTest {

    @EnigmaWebSpringBootTest(classes = EnigmaTestSpringBootApplication.class, activeProfiles = ProfileConstant.DEV)
    private static class CustomizedWebSpringBootTestClass {
    }

    @EnigmaCucumberSpringBootTest(classes = EnigmaTestSpringBootApplication.class, activeProfiles = ProfileConstant.SIT)
    private static class CustomizedCucumberSpringBootTestClass {
    }

    private static class LocalCucumberSpringBootBasedTest extends EnigmaCucumberSpringBootBasedTest {
    }

    @Test
    void shouldExposeMergedMetadataForCustomizedWebTestAnnotation() {
        final SpringBootTest springBootTest = AnnotatedElementUtils.findMergedAnnotation(
                CustomizedWebSpringBootTestClass.class, SpringBootTest.class);
        final ActiveProfiles activeProfiles = AnnotatedElementUtils.findMergedAnnotation(
                CustomizedWebSpringBootTestClass.class, ActiveProfiles.class);

        Assertions.assertNotNull(springBootTest);
        Assertions.assertArrayEquals(new Class<?>[]{EnigmaTestSpringBootApplication.class}, springBootTest.classes());
        Assertions.assertEquals(SpringBootTest.WebEnvironment.RANDOM_PORT, springBootTest.webEnvironment());
        Assertions.assertNotNull(activeProfiles);
        Assertions.assertArrayEquals(new String[]{ProfileConstant.DEV}, activeProfiles.profiles());
    }

    @Test
    void shouldExposeMergedMetadataForCustomizedCucumberTestAnnotation() {
        final SpringBootTest springBootTest = AnnotatedElementUtils.findMergedAnnotation(
                CustomizedCucumberSpringBootTestClass.class, SpringBootTest.class);
        final ActiveProfiles activeProfiles = AnnotatedElementUtils.findMergedAnnotation(
                CustomizedCucumberSpringBootTestClass.class, ActiveProfiles.class);

        Assertions.assertNotNull(springBootTest);
        Assertions.assertArrayEquals(new Class<?>[]{EnigmaTestSpringBootApplication.class}, springBootTest.classes());
        Assertions.assertEquals(SpringBootTest.WebEnvironment.RANDOM_PORT, springBootTest.webEnvironment());
        Assertions.assertNotNull(activeProfiles);
        Assertions.assertArrayEquals(new String[]{ProfileConstant.SIT}, activeProfiles.profiles());
        Assertions.assertTrue(AnnotatedElementUtils.hasAnnotation(
                CustomizedCucumberSpringBootTestClass.class, CucumberContextConfiguration.class));
    }

    @Test
    void shouldExposeDefaultMetadataForBaseSpringBootTests() {
        final SpringBootTest webSpringBootTest = AnnotatedElementUtils.findMergedAnnotation(
                EnigmaSpringBootBasedTest.class, SpringBootTest.class);
        final ActiveProfiles webActiveProfiles = AnnotatedElementUtils.findMergedAnnotation(
                EnigmaSpringBootBasedTest.class, ActiveProfiles.class);
        final SpringBootTest cucumberSpringBootTest = AnnotatedElementUtils.findMergedAnnotation(
                EnigmaCucumberSpringBootBasedTest.class, SpringBootTest.class);
        final ActiveProfiles cucumberActiveProfiles = AnnotatedElementUtils.findMergedAnnotation(
                EnigmaCucumberSpringBootBasedTest.class, ActiveProfiles.class);

        Assertions.assertNotNull(webSpringBootTest);
        Assertions.assertArrayEquals(new Class<?>[]{EnigmaTestSpringBootApplication.class}, webSpringBootTest.classes());
        Assertions.assertNotNull(webActiveProfiles);
        Assertions.assertArrayEquals(new String[]{ProfileConstant.TEST}, webActiveProfiles.profiles());
        Assertions.assertNotNull(cucumberSpringBootTest);
        Assertions.assertArrayEquals(new Class<?>[]{EnigmaTestSpringBootApplication.class}, cucumberSpringBootTest.classes());
        Assertions.assertNotNull(cucumberActiveProfiles);
        Assertions.assertArrayEquals(new String[]{ProfileConstant.TEST}, cucumberActiveProfiles.profiles());
        Assertions.assertTrue(AnnotatedElementUtils.hasAnnotation(
                EnigmaCucumberSpringBootBasedTest.class, CucumberContextConfiguration.class));
    }

    @Test
    void shouldAllowInstantiationOfBaseTestTypes() {
        final Object webBasedTest = new EnigmaSpringBootBasedTest();
        final Object cucumberBasedTest = new LocalCucumberSpringBootBasedTest();

        Assertions.assertTrue(webBasedTest instanceof EnigmaSpringBootBasedTest);
        Assertions.assertTrue(cucumberBasedTest instanceof EnigmaCucumberSpringBootBasedTest);
    }

    @Test
    void shouldBootstrapApplicationMainMethodWithNonWebMode() {
        EnigmaTestSpringBootApplication.main(new String[]{
                "--spring.main.web-application-type=none",
                "--spring.main.banner-mode=off",
                "--logging.level.root=OFF"
        });
    }
}

