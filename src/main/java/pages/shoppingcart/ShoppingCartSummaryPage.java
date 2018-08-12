package pages.shoppingcart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.ibm.icu.impl.Assert;
import helpers.Util;
import org.openqa.selenium.By;

public class ShoppingCartSummaryPage {

    public double getCartUnitPrice(int unitIndex) {
        return Util.extractDoubleFromString(Selenide.getElements(By.cssSelector(".cart_unit>.price")).get(unitIndex).getText());
    }

    public double getCardUnitSumPrice(int unitIndex) {
        return Util.extractDoubleFromString(Selenide.getElements(By.cssSelector(".cart_total>.price")).get(unitIndex).getText());
    }

    public double getShippingCost() {
        return Util.extractDoubleFromString(Selenide.getElement(By.id("total_shipping")).getText());
    }

    public double getSumOfAlItems() {
        return Util.extractDoubleFromString(Selenide.getElement(By.id("total_price")).getText());
    }

    public String getCartDescriptionOfItem(int index) {
        return Selenide.getElements(By.cssSelector(".cart_description")).get(index).getText();
    }

    public void proceedToCHeckout() {
        Selenide.getElement(By.xpath("//*[contains(@class,'cart_navigation')]//span[normalize-space(.)='Proceed to checkout']")).should(Condition.appear).click();
    }

    public void selectTermsOfService() {
        Selenide.getElement(By.cssSelector("p.checkbox label")).click();
    }

    public void performPayment(String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "pay by bank wire":
                Selenide.getElement(By.cssSelector(".payment_module .bankwire")).click();
                break;
            case "pay by check":
                Selenide.getElement(By.cssSelector(".payment_module .cheque")).click();
                break;
            default:
                Assert.fail(String.format("not supported payment selected: %s", paymentMethod));
        }
    }

    public void confirmOrder() {
        Selenide.getElement(By.xpath("//*[contains(@class,'cart_navigation')]//span[normalize-space(.)='I confirm my order']")).should(Condition.appear).click();
    }

    public void paragraphWithMessageIsVisible(String message) {
        Selenide.getElement(By.xpath(String.format("//p[contains(normalize-space(.),'%s')]", message))).should(Condition.appear);
    }
}
