package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    private final String emailInputTextBoxLoginPage = "input[data-qa='login-email']";
    private final String passwordInputTextBoxLoginPage = "input[data-qa='login-password']";
    private final String loginButton = "button[data-qa='login-button']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void enterEmail(String email) {
        page.locator(emailInputTextBoxLoginPage).fill(email);
    }

    public void enterPassword(String password) {
        page.locator(passwordInputTextBoxLoginPage).fill(password);
    }

    public void clickLoginButton() {
        page.locator(loginButton).click();
    }
/*    public void loginPageActions(String email, String password) {
        page.locator(emailInputTextBoxLoginPage).fill(email);
        page.locator(passwordInputTextBoxLoginPage).fill(password);
        page.locator(loginButton).click();
    }*/
}