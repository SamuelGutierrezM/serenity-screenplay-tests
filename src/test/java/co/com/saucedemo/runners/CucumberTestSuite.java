package co.com.saucedemo.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.com.saucedemo.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty")
// Solo se ejecutan los escenarios con este tag. Admite expresiones, p. ej. "@Regresion and not @wip"
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@Regresion")
public class CucumberTestSuite {
}
