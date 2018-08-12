package pages.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class MyAccountPage {
    private static final String OPTION_BUTON_XPATH = "//a//span[contains(normalize-space(.),'%s')]";

    public void goToMyAccount() {
        Selenide.getElement(By.cssSelector("a[title='View my customer account']")).click();
        Selenide.getElement(By.cssSelector("#columns > div.breadcrumb.clearfix > span.navigation_page")).should(Condition.appear);
    }

    public void selectOption(String buttonName) {
        Selenide.getElement(By.xpath(String.format(OPTION_BUTON_XPATH, buttonName))).click();
    }
}
