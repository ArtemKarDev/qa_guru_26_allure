package qa.guru.allure;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    @Step("Открыть страницу в разрешении 1920x1080")
    static void beforeAll() {
        //Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        //Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    @Step("Закрыть браузер")
    void afterEach() {
        closeWebDriver();
    }
}