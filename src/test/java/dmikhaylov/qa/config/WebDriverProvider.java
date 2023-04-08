package dmikhaylov.qa.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverProvider {
    public static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static void setUp() {

        Configuration.driverManagerEnabled = true;
        if (config.getBrowserName().equalsIgnoreCase("chrome")) {
            Configuration.browser = "chrome";
            System.setProperty("chromeoptions.prefs","intl.accept_languages=ru");
        } else if (config.getBrowserName().equalsIgnoreCase("firefox")) {
            Configuration.browser = "firefox";
        } else {
            throw new RuntimeException(config.getBrowserName());
        }
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.pageLoadStrategy = "eager";
        String remoteUrl = config.getRemoteUrl();
        if (remoteUrl != null) {
            Configuration.remote = remoteUrl;
        }
        Configuration.baseUrl = config.getBaseUrl();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

//      Для chromedriver==111 и selenide<6.12.2
//        if (config.getBrowserName().equalsIgnoreCase("chrome")) {
//            capabilities.setCapability(
//                    ChromeOptions.CAPABILITY,
//                    new ChromeOptions().addArguments("--remote-allow-origins=*")
//            );
//        }

    }
}
