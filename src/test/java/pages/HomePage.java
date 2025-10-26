package pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;
    private final String signUpLoginLink = "a[href='/login']";
    private final String homePageLogo = "img[alt='Website for automation practice']";

    public HomePage(Page page) {
        this.page = page;
    }
    public void clickSignUpLoginLink() {
        page.locator(signUpLoginLink).click();
    }
    public void verifyHomepageLogoIsVisible() {
        page.locator(homePageLogo).isVisible();
    }
}
