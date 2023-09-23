import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ecomm {
    WebDriver driver = new ChromeDriver();
    private WebDriverWait webDriverWait;


    @BeforeTest
    public void OpenBrwoser() throws InterruptedException {
        String chromepath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.out.println(chromepath);

        System.setProperty("webdriver.chrome.driver", chromepath);


        driver.navigate().to(utiliti.BASE_URL);
        driver.manage().window().maximize();
        Thread.sleep(3000);


    }
    @Test
    //  test case 1
    public void TC1() throws InterruptedException, IOException {

        driver.navigate().to(utiliti.BASE_URL);
        String demoSite  = driver.findElement(By.cssSelector("h2")).getText();

        Assert.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);

        driver.findElement(By.linkText("MOBILE")).click();
        new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");


        File f =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f.toPath(), new File("F:\\computer science\\TESTING\\guru_ecomm\\screeenshots\\TC1.png").toPath());


        Thread.sleep(3000);



    }
@Test
    public void TC2() throws InterruptedException{
        driver.navigate().to(utiliti.BASE_URL);
        driver.findElement(By.linkText("MOBILE")).click();
    //String expectedResults ="$100.00";
    String mobile_page_price=driver.findElement(By.id("product-price-1")).getText();
    //Assert.assertTrue(actualResults.contains(expectedResults),"Error Message: Text is wrong ");
    driver.findElement(By.xpath("//a[@title='Sony Xperia']")).click();

    String Xperia_page_details = driver.findElement(By.id("product-price-1")).getText();
    Assert.assertTrue(mobile_page_price.contains(Xperia_page_details),"Error Message: Text is wrong ");




}
@Test
public void TC3() throws InterruptedException {
    driver.navigate().to(utiliti.BASE_URL);
    driver.findElement(By.linkText("MOBILE")).click();
    driver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//input[@title='Qty']")).clear();
    driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("1000");
    driver.findElement(By.xpath("(//span[contains(text(),'Update')])[3]")).click();
    String error_msg_limit = driver.findElement(By.className("error-msg")).getText();
    Assert.assertTrue(utiliti.MAX_ERROR.contains(error_msg_limit));
    driver.findElement(By.id("empty_cart_button")).click();

    String cart_msg = driver.findElement(By.className("page-title")).getText();
    Assert.assertTrue(utiliti.CART_EMPTY_MSG.contains(cart_msg));







}
@Test
public void TC4() throws InterruptedException {

    driver.navigate().to(utiliti.BASE_URL);
    driver.findElement(By.linkText("MOBILE")).click();
    driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
    driver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
    String name_mobile1_main = driver.findElement(By.xpath("//a[@title='Sony Xperia']")).getText();
    String name_mobile2_main = driver.findElement(By.xpath("//a[@title='IPhone'][normalize-space()='IPhone']")).getText();


    driver.findElement(By.xpath("//button[@title='Compare']")).click();

    String mainWindow = driver.getWindowHandle();
    System.out.println("Main window handle is " + mainWindow);

    // To handle child window
    List<String> allWindows = driver.getWindowHandles().stream().toList();
    for (var currentWindow:allWindows) {
        if (!mainWindow.equalsIgnoreCase(currentWindow)) {
            var childWindow = driver.switchTo().window(currentWindow);

            //webDriverWait.until(ExpectedConditions.titleContains("yourTitle"));
            childWindow.manage().window().maximize();
            Thread.sleep(2000);
            String name_mobile1_child = driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']")).getText();
            String name_mobile2_child = driver.findElement(By.xpath("//a[normalize-space()='IPhone']")).getText();

            Assert.assertTrue(name_mobile1_main.contains(name_mobile1_child));

            Assert.assertTrue(name_mobile2_main.contains(name_mobile2_child));




            // close window
            driver.close();
        }
    }

    //  Switch back to the main window which is the parent window.
    driver.switchTo().window(mainWindow);





}
@Test
public void TC5() throws InterruptedException {
     driver.navigate().to(utiliti.BASE_URL);

     driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']")).click();
     driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
     driver.findElement(By.id("firstname")).sendKeys("TestFirst");
     driver.findElement(By.id("lastname")).sendKeys("TestFirst");
     driver.findElement(By.id("email_address")).sendKeys("rosonar96570@ipnuc.com");
     driver.findElement(By.id("password")).sendKeys("P@sswa0rd");
     driver.findElement(By.id("confirmation")).sendKeys("P@sswa0rd");
     driver.findElement(By.xpath("//button[@title='Register']")).click();

     String confirm_msg_reg_page = driver.findElement(By.xpath("//span[normalize-space()='Thank you for registering with Main Website Store.']")).getText();

     Assert.assertTrue(utiliti.confirmation_reg_MSG.contains(confirm_msg_reg_page));

     driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();

     driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();

     driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
     driver.findElement(By.id("email_address")).sendKeys("aymantharwat@gmail.com");
     driver.findElement(By.id("message")).sendKeys("Hello this is my wishlist");
     driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();

     String conf_wishlist_mainMSG = driver.findElement(By.xpath("//li[@class='success-msg']//ul//li")).getText();

     Assert.assertTrue(utiliti.confirmation_wishlist_MSG.contains(conf_wishlist_mainMSG));








}

@Test
public void TC6 () throws InterruptedException {
    driver.navigate().to(utiliti.BASE_URL);

    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']")).click();
    driver.findElement(By.xpath("//input[@id='email']")).clear();
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("rosonar9657011@ipnuc.com");
    driver.findElement(By.xpath("//input[@id='pass']")).clear();
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("P@sswa0rd");
    driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[@class='block-content']//a[normalize-space()='My Wishlist']")).click();

    driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();
    driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]")).click();
    driver.findElement(By.id("billing:street1")).clear();
    driver.findElement(By.id("billing:street1")).sendKeys("ABC");
    driver.findElement(By.id("billing:city")).clear();
    driver.findElement(By.id("billing:city")).sendKeys("New York");
    Select state = new Select( driver.findElement(By.xpath("//select[@id='billing:region_id']")));
    state.selectByValue("43");
    driver.findElement(By.id("billing:postcode")).clear();
    driver.findElement(By.id("billing:postcode")).sendKeys("542896");

    Select country = new Select( driver.findElement(By.xpath("//select[@id='billing:country_id']")));
    country.selectByValue("US");

    driver.findElement(By.id("billing:telephone")).clear();
    driver.findElement(By.id("billing:telephone")).sendKeys("123456789");

    driver.findElement(By.xpath("//button[@onclick='billing.save()']//span//span[contains(text(),'Continue')]")).click();

    Thread.sleep(2000);

    String flat_price ="$5.00";
    String flat_price_Shipping_page = driver.findElement(By.xpath("//span[normalize-space()='$5.00']")).getText();
    Assert.assertTrue(flat_price.contains(flat_price_Shipping_page));

    driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]")).click();
    Thread.sleep(2000);

    driver.findElement(By.xpath("//label[normalize-space()='Check / Money order']")).click();

    driver.findElement(By.xpath("//button[@onclick='payment.save()']//span//span[contains(text(),'Continue')]")).click();

    String expected_total_price ="$620.00";
    Thread.sleep(2000);
    String total_price = driver.findElement(By.xpath("//span[normalize-space()='$620.00']")).getText();

    Assert.assertTrue(total_price.contains(expected_total_price));

    driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();
    Thread.sleep(1000);

    String order_number = driver.findElement(By.xpath("//div[@class='main-container col1-layout']//p[1]")).getText();

    System.out.println(order_number);



}

   @Test
   public void TC7 () throws InterruptedException {

        driver.navigate().to(utiliti.BASE_URL);

       driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']")).click();


       driver.findElement(By.xpath("//input[@id='email']")).clear();
       driver.findElement(By.xpath("//input[@id='email']")).sendKeys("rosonar9657011@ipnuc.com");
       driver.findElement(By.xpath("//input[@id='pass']")).clear();
       driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("P@sswa0rd");
       driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();

       driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).click();

       driver.findElement(By.xpath("//a[normalize-space()='View Order']")).click();


       String order_num = "ORDER #100020008";
       String get_order_num= driver.findElement(By.xpath("//h1[normalize-space()='Order #100020008 - Pending']")).getText();
       System.out.println( get_order_num);
       Assert.assertTrue(get_order_num.contains(order_num));











   }

   @Test
   public void TC8 () throws InterruptedException {
       driver.navigate().to(utiliti.BASE_URL);

       driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']")).click();


       driver.findElement(By.xpath("//input[@id='email']")).clear();
       driver.findElement(By.xpath("//input[@id='email']")).sendKeys("rosonar9657011@ipnuc.com");
       driver.findElement(By.xpath("//input[@id='pass']")).clear();
       driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("P@sswa0rd");
       driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
       driver.findElement(By.xpath("//a[normalize-space()='Reorder']")).click();
       Thread.sleep(2000);
       driver.findElement(By.xpath("//input[@title='Qty']")).clear();
       driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("10");
       driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
       Thread.sleep(2000);

       String grand_final_10qt = "$6,200.00";
       String new_grand_final_price = driver.findElement(By.xpath("//span[normalize-space()='$6,200.00']")).getText();
       Assert.assertTrue(new_grand_final_price.contains(grand_final_10qt));

       driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]")).click();

       driver.findElement(By.xpath("//button[@onclick='billing.save()']//span//span[contains(text(),'Continue')]")).click();
       Thread.sleep(2000);
       driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]")).click();

       Thread.sleep(2000);

       driver.findElement(By.xpath("//label[normalize-space()='Check / Money order']")).click();

       driver.findElement(By.xpath("//button[@onclick='payment.save()']")).click();
       Thread.sleep(1000);

       driver.findElement(By.xpath("//button[@title='Place Order']")).click();
       Thread.sleep(1000);

       String order_number_10q = driver.findElement(By.xpath("//div[@class='main-container col1-layout']//p[1]")).getText();

       System.out.println(order_number_10q);












   }
   @Test

   public void TC9() throws InterruptedException {
        driver.navigate().to(utiliti.BASE_URL);
       driver.findElement(By.linkText("MOBILE")).click();
       driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]")).click();
       driver.findElement(By.id("coupon_code")).clear();
       driver.findElement(By.id("coupon_code")).sendKeys("GURU50");
       driver.findElement(By.xpath("//span[contains(text(),'Apply')]")).click();
       Thread.sleep(1000);

       String discount = "-$25.00";
       String gen_discount = driver.findElement(By.xpath("//span[normalize-space()='-$25.00']")).getText();
       Assert.assertTrue(gen_discount.contains(discount));

       String real_price = "$575.00";
       String final_price= driver.findElement(By.xpath("//strong//span[@class='price'][normalize-space()='$500.00']")).getText();

       Assert.assertTrue(final_price.contains(real_price));




   }

   @Test
   public void TC10() throws InterruptedException {





   }





    @AfterTest
    public void CloseDriver()
    {
        driver.quit();

    }
}