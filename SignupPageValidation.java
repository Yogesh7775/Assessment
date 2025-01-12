package Automation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPageValidation {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the signup page
            String signupUrl = "https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login";
            driver.get(signupUrl);
            driver.manage().window().maximize();

            // Wait for the Signup button and click it
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
            WebElement signupButton = driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/div[5]/div/a"));
            signupButton.click();

            // --- Test Case 1: Blank Fields Validation ---
            WebElement nameField = driver.findElement(By.id("name"));
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement confirmPasswordField = driver.findElement(By.id("confirm-password"));
            WebElement submitButton = driver.findElement(By.id("submit-button"));

            nameField.clear();
            emailField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            submitButton.click();

            WebElement nameError = driver.findElement(By.id("name-error"));
            WebElement emailError = driver.findElement(By.id("email-error"));
            WebElement passwordError = driver.findElement(By.id("password-error"));
            WebElement confirmPasswordError = driver.findElement(By.id("confirm-password-error"));

            if (nameError.isDisplayed() && emailError.isDisplayed() && passwordError.isDisplayed() && confirmPasswordError.isDisplayed()) {
                System.out.println("Blank fields validation passed.");
            } else {
                System.out.println("Blank fields validation failed.");
            }

            // --- Test Case 2: Invalid Email Format ---
            nameField.sendKeys("John Doe");
            emailField.sendKeys("invalid-email");
            passwordField.sendKeys("ValidPassword123!");
            confirmPasswordField.sendKeys("ValidPassword123!");
            submitButton.click();

            WebElement emailFormatError = driver.findElement(By.id("email-format-error"));
            if (emailFormatError.isDisplayed()) {
                System.out.println("Invalid email format validation passed.");
            } else {
                System.out.println("Invalid email format validation failed.");
            }

            // --- Test Case 3: Passwords Not Matching ---
            emailField.clear();
            emailField.sendKeys("john.doe@example.com");
            passwordField.clear();
            confirmPasswordField.clear();
            passwordField.sendKeys("Password123!");
            confirmPasswordField.sendKeys("DifferentPassword!");
            submitButton.click();

            WebElement passwordMismatchError = driver.findElement(By.id("password-mismatch-error"));
            if (passwordMismatchError.isDisplayed()) {
                System.out.println("Password mismatch validation passed.");
            } else {
                System.out.println("Password mismatch validation failed.");
            }

            // --- Test Case 4: Valid Signup ---
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys("Password123!");
            submitButton.click();

            WebElement successMessage =driver.findElement(By.id("success-message"));
            if (successMessage.isDisplayed()) {
                System.out.println("Valid signup test passed. Account created successfully!");
            } else {
                System.out.println("Valid signup test failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
