package pages.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class LoginPage {
    private static final String SIGN_IN_CSS_LOCATOR = "a.login";
    private static final String EMAIL_INPUT_ID = "#email";
    private static final String PASSWORD_INPUT_ID = "#passwd";
    private static final String LOGIN_BUTTON_ID = "#SubmitLogin";

    public void logIn(String email, String password) {
        Selenide.getElement(By.cssSelector(SIGN_IN_CSS_LOCATOR)).click();
        Selenide.getElement(By.cssSelector(EMAIL_INPUT_ID)).should(Condition.appear).setValue(email);
        Selenide.getElement(By.cssSelector(PASSWORD_INPUT_ID)).should(Condition.appear).setValue(password);
        Selenide.getElement(By.cssSelector(LOGIN_BUTTON_ID)).click();
    }


    public void logOut() {
        Selenide.getElement(By.cssSelector(".logout")).click();
    }
}
