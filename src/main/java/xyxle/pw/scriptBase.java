package xyxle.pw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public class scriptBase {
    static Playwright pw;
    static Browser bw;
    protected static BrowserContext context;
    protected static Page page;

    @BeforeAll
    static void setupAuthBrowser() {
        scriptBase.pw = Playwright.create();
        scriptBase.bw = scriptBase.pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000.0));
        scriptBase.context = scriptBase.bw.newContext();
        (scriptBase.page = scriptBase.context.newPage()).navigate("https://testing.xyxle.com/de", new Page.NavigateOptions().setTimeout(0.0));
        scriptBase.page.click("'Kunden Login'");
        scriptBase.page.fill("[name='email']", "jetrudihopo-9322@yopmail.com");
        scriptBase.page.fill("[name='password']", "alex123!");
        scriptBase.page.click("'Login'");
        scriptBase.page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome, Alex Test Co!")).waitFor();
        PlaywrightAssertions.assertThat(scriptBase.page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome, Alex Test Co!"))).isVisible();
    }

    @AfterAll
    static void closeBrowser() {
        scriptBase.pw.close();
    }

    @AfterEach
    void closeContext() {
        scriptBase.context.close();
    }
}