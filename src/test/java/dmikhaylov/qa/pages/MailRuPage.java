package dmikhaylov.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MailRuPage {

    final private ElementsCollection headMenu = $$("div.headline div.ph-projects a");
    final private ElementsCollection tabsMenu = $$("div.tabs a");
    final private ElementsCollection tabsContent = $$("ul.tabs-content li div.news-item_regular");
    final private SelenideElement buttonEnter = $x("//button[text()='Войти']");
    final private SelenideElement buttonCreate = $x("//button[text()='Создать почту']");

    public MailRuPage openPage() {
        open("/");
        return this;
    }

    public MailRuPage verifyHeaderMenuItems(List<String> items) {
        headMenu.filter(visible).shouldHave(texts(items));
        return this;
    }

    public MailRuPage verifyTabsMenuItems(List<String> items) {
        tabsMenu.filter(visible).shouldHave(texts(items));
        return this;
    }

    public MailRuPage verifySizeOfContent(int size) {
        tabsContent.filter(visible).shouldHave(sizeLessThanOrEqual(size));
        return this;
    }

    public MailRuPage clickOnTabMenu(String tabMenu) {
        $x("//div[@data-testid='news-tabs']/a[text()='" + tabMenu + "']").click();
        return this;
    }

    public MailRuPage clickOnEnterButton() {
        buttonEnter.click();
        return this;
    }

    public MailRuPage checkModalAppears() {
        switchTo().frame($x("//div[contains(@class, 'ag-popup__frame_show')]//iframe"));
        $x("//h3[text()='Войти в аккаунт']").shouldBe(visible);
        return this;
    }

    public MailRuPage selectProviderInModal(String provider) {
        $("div[data-provider='" + provider + "']").click();
        return this;
    }

    public MailRuPage checkDomainInModal(String domain) {
        $x("//div[@class='domain-select']//span[text()='@" + domain + "']").shouldBe(visible);
        return this;
    }
}
