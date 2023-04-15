package dmikhaylov.qa.base;

import com.codeborne.selenide.logevents.SelenideLogger;
import dmikhaylov.qa.config.WebDriverProvider;
import dmikhaylov.qa.helpers.Attach;
import dmikhaylov.qa.pages.MailRuPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    protected MailRuPage mailRuPage = new MailRuPage();

    @BeforeAll
    static void setUp() {
        WebDriverProvider.setUp();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
