package xyxle.pw;

import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class createPVL extends scriptBase {
    @Test
    public void newPVL() {
        createPVL.page.hover("text=Coop Private Labels");
        createPVL.page.click("text=Create private label");
        createPVL.page.fill("[name='gpc-brick-suggest']", "10000163");
        createPVL.page.click("text=10000163");
        PlaywrightAssertions.assertThat(createPVL.page.locator("[name='gpc_brick']")).hasValue("10000163");

        createPVL.page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create Item")).click();
        createPVL.page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Core Data")).waitFor();
        PlaywrightAssertions.assertThat(createPVL.page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Core Data"))).isVisible();

        createPVL.page.click("a:has-text('Save')");
        PlaywrightAssertions.assertThat(createPVL.page.locator("label[for='core.sap']")).isVisible();
        PlaywrightAssertions.assertThat(createPVL.page.locator("label[for='core.eanCode']")).isVisible();
        PlaywrightAssertions.assertThat(createPVL.page.locator("label[for='core.title']")).isVisible();
        createPVL.page.click("text=Contact Information");
        PlaywrightAssertions.assertThat(createPVL.page.locator("label[for='core.idSupplier']")).isVisible();

        createPVL.page.selectOption("[name='core.idSupplier']", "63ca2e6f1b90f24761170992");
        createPVL.page.click("text=Core Data");
        createPVL.page.fill("[name='core.sap']", "123654");
        createPVL.page.click("a:has-text('Generate GTIN')");
        createPVL.page.fill("[name='core.title']", "Playwright automation test");
        createPVL.page.fill("[name='core.coopLongText']", "Playwright automation test");
        createPVL.page.click("a:has-text('Save')");

        PlaywrightAssertions.assertThat(createPVL.page.locator(".toast-title")).containsText("Well Done!");

        createPVL.page.click("a:has-text('Return')");
        createPVL.page.click(".delete");
        createPVL.page.click(".swal2-confirm");
        PlaywrightAssertions.assertThat(createPVL.page.locator(".toast-title")).containsText("Well Done!");
    }
}