package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ProductViewPage {
    private static final String SIZE_DROPDOWN_XPATH = "#product #attributes .attribute_list select";
    private static final String QUANTITY_INPUT_XPATH = "#quantity_wanted_p input";

    public void setItemSize(String size) {
        SelenideElement sizeDropdown = Selenide.getElement(By.cssSelector(SIZE_DROPDOWN_XPATH));
        //TODO refactor to remove sleeps
        Selenide.sleep(200);
        sizeDropdown.selectOption(size);
        Selenide.sleep(200);

    }

    public void setItemQuantity(String quantity) {
        Selenide.getElement(By.cssSelector(QUANTITY_INPUT_XPATH)).should(Condition.appear).setValue(quantity);
    }

    public void addToCart() {
        Selenide.getElement(By.cssSelector("#add_to_cart")).click();
    }

    public void clickButtonWithName(String buttonName) {
        Selenide.getElement(By.xpath(String.format("//*[normalize-space(.)='%s']", buttonName)))
                .should(Condition.appear)
                .click();
    }
}
