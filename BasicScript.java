package Automation;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicScript {
	public static void main(String[] args) {

        ChromeDriver driver =new ChromeDriver();

        try {
           
            driver.manage().window().maximize();

            driver.get("https://app-staging.nokodr.com/");

            Thread.sleep(2000);
           
            System.out.println("Page title is: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }


}
