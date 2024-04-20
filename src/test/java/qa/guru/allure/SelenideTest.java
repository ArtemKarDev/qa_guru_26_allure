package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideTest extends TestBase {


    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".search-input").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").pressEnter();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#80")).should(Condition.exist);

    }

    @Test
    public void testIssueSearchLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Кликаем на поиск", () -> {
            $(".search-input").click();
        });
        step("Вводим запрос", () -> {
            $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        });
        step("Жмем Enter", () -> {
            $("#query-builder-test").pressEnter();
        });
        step("Кликаем на результат поиска", () -> {
            $(linkText("eroshenkoam/allure-example")).click();
        });
        step("Кликаем на кнопку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Выполняется переход на страницу Issues", () -> {
            $(withText("#80")).should(Condition.exist);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }


    @Test
    public void testGithubIssueSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchInputClick();
        steps.searchInputSendKeys();
        steps.searchInputPressEnter();
        steps.searchOutputClick();
        steps.issuesTabClick();
        steps.issuesExist();

    }


}