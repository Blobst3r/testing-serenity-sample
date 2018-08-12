package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class QuickViewProductPage extends ProductViewPage {
    //    private static final String SIZE_DROPDOWN_XPATH = "//iframe[contains(@id, 'fancybox-frame')]";
//    private static final String QUANTITY_INPUT_XPATH = "#quantity_wanted_p input";


    public void switchToQuickViewIframe() {
        String iframeId = Selenide.getElement(By.cssSelector("iframe.fancybox-iframe")).getAttribute("id");
        Selenide.switchTo().frame(iframeId);
    }

    public void switchToDefault() {
        Selenide.switchTo().defaultContent();
    }
}
