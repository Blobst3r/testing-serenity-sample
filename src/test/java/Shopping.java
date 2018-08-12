import framework.BasePage;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Shopping {
    private BasePage basePage = new BasePage();

    @Steps
    private ShoppingDef shoppingDef;
    @Steps
    private AccountDef accountDef;

    @Before
    public void testSetup() {
        basePage.setDriver();
        shoppingDef.openPageAndSignIn();
        shoppingDef.openHomePage();
    }

    @After
    public void testTeardown() {
        shoppingDef.logOut();
        basePage.closeDriver();
    }

    @Test
    public void shoppingPurchase2Items() {
        shoppingDef.quickViewAddArticle(1);
        shoppingDef.setQuickViewArticleSizeAndQuantity("M", "default");
        shoppingDef.addItemToCartAndSelectShoppingButton("Continue shopping");
        shoppingDef.quickViewAddArticle(2);
        shoppingDef.setQuickViewArticleSizeAndQuantity("default", "2");
        shoppingDef.addItemToCartAndSelectShoppingButton("Proceed to checkout");
        shoppingDef.verifyPricesOfItems(16.51, 27);
        shoppingDef.verifySumsOfItems(16.51, 54, 72.51);
        shoppingDef.verifyShippingCostr(2);
        shoppingDef.verifySizeOfItem(1, "M");
        shoppingDef.verifySizeOfItem(2, "S");
        shoppingDef.finishShopping("pay by bank wire");
        shoppingDef.finalMessageIs("Your order on My Store is complete");
    }

    @Test
    public void reviewOrderAndAddMessage() {
        String itemMessage = RandomStringUtils.randomAlphabetic(10);
        accountDef.goToMyAccount();
        accountDef.openMyAccountOption("Order history and details");
        accountDef.addMessageToItemNumber(itemMessage, 1);
        accountDef.verifyLastMessageForItemNumber(itemMessage, 1);
    }

    @Test
    public void captureImage() {
        shoppingDef.quickViewAddArticle(1);
        shoppingDef.setQuickViewArticleSizeAndQuantity("M", "default");
        shoppingDef.addItemToCartAndSelectShoppingButton("Proceed to checkout");
        shoppingDef.customAssertWithScreenShot();
    }
}
