package dmikhaylov.qa.tests;

import dmikhaylov.qa.base.BaseTest;
import static io.qameta.allure.Allure.step;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static io.qameta.allure.SeverityLevel.*;
import java.util.List;
import java.util.stream.Stream;

public class MainPageTest extends BaseTest {

    static Stream<List<String>> headMenuProvider() {
        return Stream.of(List.of("Mail.ru", "Почта", "Облако", "Одноклассники",
                "Вконтакте", "Новости", "Знакомства"));
    }

    static Stream<List<String>> tabsMenuProvider() {
        return Stream.of(List.of("Новости", "Спецоперация", "Спорт", "Леди", "Авто", "Кино",
                "Hi-Tech", "Игры", "Дети", "Здоровье", "Дом", "Питомцы"));
    }

    @Owner("dmikhaylov")
    @Severity(BLOCKER)
    @Tags({@Tag("ui")})
    @MethodSource("headMenuProvider")
    @ParameterizedTest(name="Наличие пунктов меню в заголовке главной страницы")
    public void verifyElementsOfHeadMenuTest(List<String> items) {
        step("Открываем главную страницу сайта", () -> {
            mailRuPage.openPage();
        });
        step("Проверяем наличие пунктов меню в заголовке главной страницы", () -> {
            mailRuPage.verifyHeaderMenuItems(items);
        });
    }

    @Owner("dmikhaylov")
    @Severity(BLOCKER)
    @Tags({@Tag("ui")})
    @MethodSource("tabsMenuProvider")
    @ParameterizedTest(name="Наличие пунктов таб-меню в основной части страницы")
    public void verifyElementsOfTabsMenuTest(List<String> items) {
        step("Открываем главную страницу сайта", () -> {
            mailRuPage.openPage();
        });
        step("Проверяем наличие пунктов таб-меню в основной части страницы", () -> {
            mailRuPage.verifyTabsMenuItems(items);
        });
    }

    @ValueSource(strings = {"Новости", "Спецоперация", "Спорт", "Леди", "Авто", "Кино",
            "Hi-Tech", "Игры", "Дети", "Здоровье", "Дом", "Питомцы"})
    @Owner("dmikhaylov")
    @Severity(BLOCKER)
    @Tags({@Tag("ui")})
    @ParameterizedTest(name="Проверка: количество основных статей в таб-меню {0} меньше либо равно 15")
    public void verifySizeOfRegularAutoContentItemsTest(String tabMenu) {
        step("Открываем главную страницу сайта", () -> {
            mailRuPage.openPage();
        });
        step("Нажимаем на таб-меню " + tabMenu, () -> {
            mailRuPage.clickOnTabMenu(tabMenu);
        });
        step("Проверяем, что количество основных новостных статей в таб-меню "
                + tabMenu + " меньше либо равно 15", () -> {
            mailRuPage.verifySizeOfContent(15);
        });
    }

    @Owner("dmikhaylov")
    @Severity(BLOCKER)
    @Tags({@Tag("ui"), @Tag("modal")})
    @DisplayName("Проверка появления модального окна при нажатии на кнопку Войти")
    @Test
    public void shouldBeModalAppearsByClickingEnterButtonTest() {
        step("Открываем главную страницу сайта", () -> {
            mailRuPage.openPage();
        });
        step("Нажимаем на кнопку Войти", () -> {
            mailRuPage.clickOnEnterButton();
        });
        step("Проверяем, что появилось модальное окно Войти в аккаунт", () -> {
            mailRuPage.checkModalAppears();
        });
    }

    @CsvSource({
            "Yandex, yandex, yandex.ru",
            "Google, google, gmail.com",
            "Mail.ru, mailru, mail.ru"
    })
    @Owner("dmikhaylov")
    @Severity(BLOCKER)
    @Tags({@Tag("ui"), @Tag("modal")})
    @ParameterizedTest(name="Проверка смены домена на {2} при изменении провайдера на {0}")
    public void shouldBeChangeDomainNameByClickingProviderTest(String providerTitle, String provider, String domain) {
        step("Открываем главную страницу сайта", () -> {
            mailRuPage.openPage();
        });
        step("Нажимаем на кнопку Войти", () -> {
            mailRuPage.clickOnEnterButton();
        });
        step("Переключаемся на модальное окно", () -> {
            mailRuPage.checkModalAppears();
        });
        step("Выбираем провайдера " + providerTitle, () -> {
            mailRuPage.selectProviderInModal(provider);
        });
        step("Проверяем, что домен сменился на @" + domain, () -> {
            mailRuPage.checkDomainInModal(domain);
        });
    }
}
