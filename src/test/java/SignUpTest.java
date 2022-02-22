import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void zipCode() {
        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Ввводим 5 цифр, например 12345
        4. Нажать Continue
        5. Проверить, что кнопка Register видна
        6. Закрыть браузер
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isDisplayed, "Error message is not displayed");
    }

    @Test
    public void zipCodeNegative1() {
        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Вводим 4 цифры, например 1234
        4. Нажать Continue
        5. Проверить, что надпись "Oops, error on page. ZIP code should have 5 digits" видна
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.className("error_message")).isDisplayed();
        assertTrue(isDisplayed);
    }

    @Test
    public void zipCodeShouldNotBeAcceptedWith6Digits() {
        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Вводим 6 цифр, например 123456
        4. Нажать Continue
        5. Проверить, что надпись "Oops, error on page. ZIP code should have 5 digits" видна
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.className("error_message")).isDisplayed();
        assertTrue(isDisplayed);
    }

    @Test
    public void registerPositive() {
        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345
        3. В поле First Name ввести Timofei
        4. В поле Last Name ввести Borodich
        5. В поле Email ввести aaa@aaa.aa
        6. В поле Password ввести 123456789
        7. В поле Confirm Password ввести 123456789
        4. Нажать Register
        5. Проверить, что надпись "Account is created!" видна
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("Timofei");
        driver.findElement(By.name("last_name")).sendKeys("Borodich");
        driver.findElement(By.name("email")).sendKeys("aaa@aaa.aa");
        driver.findElement(By.name("password1")).sendKeys("123456789");
        driver.findElement(By.name("password2")).sendKeys("123456789");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        boolean isDisplayed = driver.findElement(By.className("confirmation_message")).isDisplayed();
        assertTrue(isDisplayed);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
