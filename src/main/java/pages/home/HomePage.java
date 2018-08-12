package pages.home;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLog;
import data.Configuration;
import org.openqa.selenium.By;

public class HomePage {
    private static final String FEATURED_ITEMS_XPATH = "#homefeatured>li";

    public void openHomePage(Configuration conf) {
        Selenide.open(conf.getBaseUrl());
        Selenide.getElement(By.cssSelector("#home-page-tabs")).should(Condition.appear);
    }

    public void quickViewFeaturedItem(int index) {
        ElementsCollection featuredItems = Selenide.getElements(By.cssSelector(FEATURED_ITEMS_XPATH));
        featuredItems.get(index).should(Condition.appear).hover();
        Selenide.getElements(By.cssSelector(".quick-view")).get(index).click();
//        SelenideElement iframeElement = Selenide.getElement(By.cssSelector("iframe.fancybox-iframe"));
        String iframeId = Selenide.getElement(By.cssSelector("iframe.fancybox-iframe")).getAttribute("id");
        Selenide.switchTo().frame(iframeId);
    }
}
