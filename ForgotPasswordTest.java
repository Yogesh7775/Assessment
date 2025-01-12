
package Automation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordTest {
    public static void main(String[] args) {
        // Set up WebDriver (ensure the path to ChromeDriver is correct)
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Visit the forgot password page
            driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");

            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(), 'Forgot Password')]")));
            forgotPasswordLink.click();

            // Wait for the forgot password form to appear
          WebElement emailField =driver.findElement(By.xpath("//*[@id=\"id_17366752202626918\"]"));// Replace with the actual ID or selector for the email field
            WebElement submitButton = driver.findElement(By.id("submit")); // Replace with the actual ID or selector

            // Test Case 1: Valid email
            emailField.clear();
            emailField.sendKeys("registered@example.com");
            submitButton.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.className("success-message"), "Reset link sent to your email"));

            // Test Case 2: Non-registered email
            emailField.clear();
            emailField.sendKeys("unregistered@example.com");
            submitButton.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.className("error-message"), "Email not registered"));

            // Test Case 3: Invalid email format
            emailField.clear();
            emailField.sendKeys("invalidemail.com");
            submitButton.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.className("error-message"), "Invalid email format"));

            // Test Case 4: Blank email field
            emailField.clear();
            submitButton.click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.className("error-message"), "Email is required"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
