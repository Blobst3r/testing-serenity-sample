import net.thucydides.core.annotations.Step;
import pages.account.MyAccountPage;
import pages.account.OrderHistory;

public class AccountDef {
    private MyAccountPage myAccountPage = new MyAccountPage();
    private OrderHistory orderHistory = new OrderHistory();

    @Step("go to my account")
    public void goToMyAccount() {
        myAccountPage.goToMyAccount();
    }

    @Step("open {0}")
    public void openMyAccountOption(String buttonName) {
        myAccountPage.selectOption(buttonName);
    }

    @Step("add message {0} to {0}. item")
    public void addMessageToItemNumber(String message, int item) {
        orderHistory.addMessageToItem(message, item - 1);
    }

    @Step("verify that the last message is {0} for {0}. item")
    public void verifyLastMessageForItemNumber(String message, int item) {
        orderHistory.verifyLastMessageForItemWithIndex(message, item - 1);
    }

}
