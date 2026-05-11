package com.rdlts.enigma.test.bdd.demo;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * EnigmaBracerQuestBddTest
 * 游击士支援任务评级 BDD 示例入口。
 *
 * @author wangjialong
 * @since 2026/05/11 16:05
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/bdd")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.rdlts.enigma.test.bdd.demo")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
public class EnigmaBracerQuestBddTest {
}


