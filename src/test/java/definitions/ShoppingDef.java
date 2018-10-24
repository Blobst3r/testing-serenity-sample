package definitions;

import com.codeborne.selenide.Selenide;
import data.Configuration;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import pages.shoppingcart.ShoppingCartSummaryPage;
import pages.home.HomePage;
import pages.home.QuickViewProductPage;
import pages.login.LoginPage;

public class ShoppingDef {
    private double doubleDelta = 0.01;
    private LoginPage loginPage = new LoginPage();
    private Configuration conf = new Configuration();
    private HomePage homePage = new HomePage();
    private QuickViewProductPage quickViewProductPage = new QuickViewProductPage();
    private ShoppingCartSummaryPage shoppingCartSummaryPage = new ShoppingCartSummaryPage();

    public ShoppingDef() {
        conf.readConfigFile();
    }


    @Step("open page and sign in {0}")
    public void openPageAndSignIn() {
        openHomePage();
        loginPage.logIn(conf.getShoppingUser().getEmail(), conf.getShoppingUser().getPassword());
    }

    @Step("log out")
    public void logOut() {
        loginPage.logOut();
    }

    @Step("go to home")
    public void openHomePage() {
        homePage.openHomePage(conf);
    }

    @Step("use quick view to add the {0}. article")
    public void quickViewAddArticle(int itemIndex) {
        homePage.quickViewFeaturedItem(itemIndex - 1);
    }

    @Step("set quick view article size {0}, quantity {}")
    public void setQuickViewArticleSizeAndQuantity(String size, String quantity) {
        if (!size.equals("default")) {
            quickViewProductPage.setItemSize(size);
        }
        if (!quantity.equals("default")) {
            quickViewProductPage.setItemQuantity(quantity);
        }
    }

    @Step("add quick view item to cart and click shopping choice button {0}")
    public void addItemToCartAndSelectShoppingButton(String buttonName) {
        quickViewProductPage.addToCart();
        quickViewProductPage.clickButtonWithName(buttonName);
        quickViewProductPage.switchToDefault();
    }

    @Step("verify that items 1 and 2 have the price of {0} and {0}")
    public void verifyPricesOfItems(double item1, double item2) {
        double actualPriceItem1 = shoppingCartSummaryPage.getCartUnitPrice(0);
        double actualPriceItem2 = shoppingCartSummaryPage.getCartUnitPrice(1);

        Assert.assertEquals("price of item1 doesnt match expected value", item1, actualPriceItem1, doubleDelta);
        Assert.assertEquals("price of item2 doesnt match expected value", item2, actualPriceItem2, doubleDelta);
    }

    @Step("verify that shipping cost is {0}")
    public void verifyShippingCostr(double expectedShippingCost) {
        double actualShippingPrice = shoppingCartSummaryPage.getShippingCost();
        Assert.assertEquals("shipping price doesn't match expected value", expectedShippingCost, actualShippingPrice, doubleDelta);
    }

    @Step("verify that the sum of items 1 and 2 are {0} and {0} and that total sum is {0}")
    public void verifySumsOfItems(double sumOfItem1, double sumOfItem2, double totalSumOfAllPurchases) {
        double actualPriceSumOfItem1 = shoppingCartSummaryPage.getCardUnitSumPrice(0);
        double actualPriceSumOfItem2 = shoppingCartSummaryPage.getCardUnitSumPrice(1);
        double actualTotalSum = shoppingCartSummaryPage.getSumOfAlItems();

        Assert.assertEquals("sum of all items 1 doesn't match expected value", sumOfItem1, actualPriceSumOfItem1, doubleDelta);
        Assert.assertEquals("sum of all items 2 doesn't match expected value", sumOfItem2, actualPriceSumOfItem2, doubleDelta);
        Assert.assertEquals("total sum of all items doesn't match expected value", totalSumOfAllPurchases, actualTotalSum, doubleDelta);
    }

    @Step("verify that item {0} has the size {0}")
    public void verifySizeOfItem(int itemNumber, String sizeOfItem) {
        String itemDescription = shoppingCartSummaryPage.getCartDescriptionOfItem(itemNumber);
        Assert.assertTrue(String.format("item %d has the size %s", itemNumber, sizeOfItem), itemDescription.contains(String.format("Size : %s", sizeOfItem)));
    }

    @Step("successfully finish checkout and buy items with payment {0}")
    public void finishShopping(String paymentMethod) {
        shoppingCartSummaryPage.proceedToCHeckout();
        shoppingCartSummaryPage.proceedToCHeckout();
        shoppingCartSummaryPage.selectTermsOfService();
        shoppingCartSummaryPage.proceedToCHeckout();
        shoppingCartSummaryPage.performPayment(paymentMethod);
        shoppingCartSummaryPage.confirmOrder();
    }

    @Step("at the end of the shopping process user should see the messange {0}")
    public void finalMessageIs(String message) {
        shoppingCartSummaryPage.paragraphWithMessageIsVisible(message);
    }

    @Step("able to take a screenshot")
    public void customAssertWithScreenShot() {
        try {
            Assert.assertEquals(3.543, shoppingCartSummaryPage.getShippingCost(), doubleDelta);
        } catch (AssertionError error) {
//            TODO: link serenity with selenide screenshot to include it in the report index.html
            Selenide.screenshot("custom screenshot");
            Assert.fail(String.format("shipping costs not as expected: %s", error));
        }
    }
}
