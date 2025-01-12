package Automation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class LoginPageValidation {
	    public static void main(String[] args) {
	        WebDriver driver = new ChromeDriver();

	        try {
	            driver.get( "https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");
	            driver.manage().window().maximize();

	            // Wait for elements to load
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	            WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
	            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

	            // --- Test Case 1: Blank Fields Validation ---
	            usernameField.clear();
	            passwordField.clear();
	            loginButton.click();

	            WebElement usernameError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username-error")));
	            WebElement passwordError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password-error")));
	            if (usernameError.isDisplayed() && passwordError.isDisplayed()) {
	                System.out.println("Blank fields validation passed.");
	            } else {
	                System.out.println("Blank fields validation failed.");
	            }

	            // --- Test Case 2: Invalid Credentials ---
	            usernameField.clear();
	            passwordField.clear();
	            usernameField.sendKeys("invalid_user@example.com");
	            passwordField.sendKeys("WrongPassword123");
	            loginButton.click();
			

	            WebElement invalidLoginError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-error")));
	            if (invalidLoginError.isDisplayed()) {
	                System.out.println("Invalid credentials test passed.");
	            } else {
	                System.out.println("Invalid credentials test failed.");
	            }

	            // --- Test Case 3: Valid Login ---
	            usernameField.clear();
	            passwordField.clear();
	            usernameField.sendKeys("valid_user@example.com");
	            passwordField.sendKeys("ValidPassword123!");
	            loginButton.click();

	            WebElement dashboard = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dashboard")));
	            if (dashboard.isDisplayed()) {
	                System.out.println("Valid login test passed.");
	            } else {
	                System.out.println("Valid login test failed.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            driver.quit();
	        }
	    }
	}
