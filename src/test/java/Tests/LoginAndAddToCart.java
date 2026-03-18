package Tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static utilities.Hooks.page;

@Listeners({utilities.Hooks.class})


public class LoginAndAddToCart {

     @Test
    void  loginAndAddToCart() throws InterruptedException {
         Locator userName = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username"));
         userName.fill("standard_user");
         Thread.sleep(1000);
         Locator password = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
         password.fill("secret_sauce");
         Thread.sleep(1000);
         Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions());
         loginButton.click();
         Thread.sleep(1000);
         Locator addToCart = page.locator("#add-to-cart-sauce-labs-backpack");

         addToCart.click();
    }


}
