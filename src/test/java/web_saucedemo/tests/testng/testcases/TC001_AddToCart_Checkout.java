package web_saucedemo.tests.testng.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import web_saucedemo.contexts.CheckoutYourInfoData;
import web_saucedemo.pages.CheckoutPage;
import web_saucedemo.pages.HeaderPage;
import web_saucedemo.pages.LoginPage;
import web_saucedemo.pages.ProductsPage;
import web_saucedemo.pages.ShoppingCartPage;
import java.util.List;

public class TC001_AddToCart_Checkout extends BaseTest {

    // TODO: Data provider
    String dtUsername = "standard_user";
    String dtPassword = "secret_sauce";

    String prod1 = "Sauce Labs Backpack";
    String prod2 = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void TC001_AddToCart_Checkout() throws InterruptedException {

        // TODO: Data provider
        CheckoutYourInfoData dtYourInfo = new CheckoutYourInfoData();
        dtYourInfo.setFirstName("John");
        dtYourInfo.setLastName("Doe");
        dtYourInfo.setZip("3000");

        // 1.1 Login to page
        new LoginPage(driver).login(dtUsername, dtPassword);

        // 1.2 Verify user successfully logged in to page
        HeaderPage pgHeader = new HeaderPage(driver);
        Assert.assertTrue(pgHeader.isLoggedIn());

        // 2.1 Before filter, capture the prices
        ProductsPage productsPage = new ProductsPage(driver);
        for (Double p : productsPage.getPricesBeforeFilter()) {
            System.out.println(p);
        }

        // 2.3 Filter the price from the dropdown
        productsPage.filterPricesByDescendingOrder();

        // 2.4 After filter, capture the prices
        for (Double p : productsPage.getPricesAfterFilter()) {
            System.out.println(p);
        }

        // 2.6 Compare the values/Assert the values
        List<Double> afterFilterPriceList = productsPage.getPricesAfterFilter();
        List<Double> beforeFilterPriceList = productsPage.getPricesAfterFilter();
        Assert.assertTrue(productsPage.arePricesSortedDescending(beforeFilterPriceList,
                afterFilterPriceList));

        new ProductsPage(driver)
                .add(prod1);

        CheckoutPage pgCheckout = new ShoppingCartPage(driver)
                .open()
                .checkout()
                .setInformation(dtYourInfo)
                .finish();
        Assert.assertTrue(pgCheckout.isCheckoutComplete());
    }
}
