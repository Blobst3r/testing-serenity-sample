package pages.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class OrderHistory {
    public void openDetailsForItemWithIndex(int itemIndex) {
        Selenide.getElements(By.cssSelector("#order-list tbody tr td.history_detail a.btn")).get(itemIndex).click();
    }

    public void addMessageToItem(String message, int itemIndex) {
        openDetailsForItemWithIndex(itemIndex);
        Selenide.getElement(By.cssSelector("#sendOrderMessage textarea[name='msgText']")).setValue(message);
        Selenide.getElement(By.cssSelector("button[name='submitMessage']")).click();
    }

    public void verifyLastMessageForItemWithIndex(String message, int itemIndex) {
        String lastMessageXpath = String.format("//tr[contains(@class,'first_item')]/td[normalize-space(.)='%s']", message);
        openDetailsForItemWithIndex(itemIndex);
        Selenide.getElement(By.xpath(lastMessageXpath)).should(Condition.appear);
    }
}
