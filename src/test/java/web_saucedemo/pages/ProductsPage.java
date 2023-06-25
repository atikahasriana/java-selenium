package web_saucedemo.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {

    By drpSort = By.xpath("//select[@data-test='product_sort_container']");
    By lstProduct = By.xpath("//div[contains(@class,'inventory_list')]/div");
    By lstProduct_title = By.xpath(".//a[contains(@id,'title_link')]");
    By lstProduct_add = By.xpath(".//button[contains(@class,'btn_inventory')][contains(@id,'add-to-cart')]");
    By lstProduct_remove = By.xpath(".//button[contains(@class,'btn_inventory')][contains(@id,'remove')]");
    By lstProduct_addbutton = By.xpath("//button[contains(@id,'add-to-cart-sauce-labs-backpack')][1]");
    By itemPrice = By.xpath("//div[contains(@class,'inventory_item_price')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private WebElement getProduct(String title) {
        return driver.findElements(lstProduct)
                .stream()
                .filter(element -> element.findElement(lstProduct_title).getText().equals(title))
                .findFirst()
                .orElseThrow();
    }

    public ProductsPage add(String title) {
        getProduct(title)
                .findElement(lstProduct_add)
                .click();
        return this;
    }

    public List<Double> getPricesBeforeFilter() {
        // 2.1 Before filter, capture the prices
        List<WebElement> beforeFilterPrice = driver.findElements(itemPrice);
        // 2.2 remove the $ symbol from the price and convert the string into double
        List<Double> beforeFilterPriceList = new ArrayList<>();

        for (WebElement p : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.parseDouble(p.getText().replace("$", "")));
        }
        return beforeFilterPriceList;
    }

    public void filterPricesByDescendingOrder() {
        List<WebElement> dropdownElements = driver.findElements((By.className("product_sort_container")));

        if (!dropdownElements.isEmpty()) {
            Select dropdown = new Select(dropdownElements.get(0));
            dropdown.selectByVisibleText("Price (high to low)");
        } else {
            throw new NoSuchElementException("Filter dropdown element not found");
        }
    }

    public List<Double> getPricesAfterFilter() {
        // 2.1 Before filter, capture the prices
        List<WebElement> afterFilterPrice = driver.findElements(itemPrice);
        // 2.2 remove the $ symbol from the price and convert the string into double
        List<Double> afterFilterPriceList = new ArrayList<>();

        for (WebElement p : afterFilterPrice) {
            afterFilterPriceList.add(Double.parseDouble(p.getText().replace("$", "")));
        }
        return afterFilterPriceList;
    }

    public boolean arePricesSortedDescending(List<Double> prices, List<Double> afterFilterPriceList) {
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Collections.reverse(sortedPrices);
        return prices.equals(sortedPrices);
    }

    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String testRun() {
        return "Test Tika";
    }
}
