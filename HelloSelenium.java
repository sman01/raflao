import java.lang.*;
import java.util.logging.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.ArrayList;
import java.util.List;

public class HelloSelenium {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    static int numberOfReviews(WebDriver driver) {
        String revs = "/html/body/div[15]/div/div[1]/section/div[2]/div[1]/div/div/span";
        WebElement reviews = driver.findElement(By.xpath(revs));
        String review = reviews.getText().replace(" reviews", "");
        int reviewno = Integer.parseInt(review);
        if (reviewno > 0) {
            return (reviewno);
        } else {
            return 0;
        }
    }

    static void scrollEnd(WebDriver driver) throws InterruptedException {
        String id = "//*[@id='loadMore']/span";
        boolean lmao = true;
        int f = 0;
        while (lmao == true) {

            try {
                driver.findElement(By.xpath(id)).click();
                Thread.sleep(500);
                f++;

            } catch (Exception e) {
                // TODO: handle exception
                lmao = false;

            }

        }
    }

    static void expandReviews(WebDriver driver, int reviewno) {

        for (int j = 1; j <= reviewno; j++) {
            var expand = "//div[@id='userReviews']/div[" + String.valueOf(j) + "]/div/div[2]/div/p/span[2]";

            try {
                driver.findElement(By.xpath(expand)).click();
            } catch (Exception e) {
                String elem = "not a big review";
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/home/sman/Desktop/raflao/geckodriver");
        WebDriver driver = new FirefoxDriver();
        Dimension d = new Dimension(640, 720);
        driver.manage().window().setSize(d);
        driver.get("https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Swift");
        int reviewno = numberOfReviews(driver);
        scrollEnd(driver);
        expandReviews(driver, reviewno);
        System.out.println("Number of reviews: " + reviewno);
        for (int i = 1; i < 20; i++) {
            logger.log(Level.INFO, "---------------------------------------------------------------" + String.valueOf(i)
                    + "---------------------------------------------------------------");
            var review_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p";

            var usage_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span[3]";
            var subSpace_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span[3]/span";
            var superSub_id = "//div[@id='userReviews']/div[" + String.valueOf(i)
                    + "]/div/div[2]/div/p/span[3]/span[2]";
            var ratString_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[1]/div[1]/span[2]/span";
            var date_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[1]/div[1]/span[3]";
            var user_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[1]/div[1]/span[1]/span";

            try {

                Thread.sleep(1000);
                logger.log(Level.INFO, "sleep complete");
                WebElement review = driver.findElement(By.xpath(review_id));
                try {
                    WebElement usage = driver.findElement(By.xpath(usage_id));
                    WebElement subSpace = driver.findElement(By.xpath(subSpace_id));
                    WebElement superSub = driver.findElement(By.xpath(superSub_id));
                    var lmao = usage.getText().replace(subSpace.getText(), "");
                } catch (Exception e) {
                    var usage_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span";
                    var subSpace_id = "//div[@id='userReviews']/div[" + String.valueOf(i)
                            + "]/div/div[2]/div/p/span/span[1]";
                    var superSub_id = "//div[@id='userReviews']/div[" + String.valueOf(i)
                            + "]/div/div[2]/div/p/span/span[2]";
                    try {
                        WebElement usage = driver.findElement(By.xpath(usage_id));
                    } catch (Exception e) {
                        logger.log(Level.INFO, "usage not found");
                    }
                }
                try {

                } catch (Exception e) {
                    logger.log(Level.INFO, "sub not found");
                }
                WebElement ratString = driver.findElement(By.xpath(ratString_id));
                WebElement date = driver.findElement(By.xpath(date_id));
                WebElement user = driver.findElement(By.xpath(user_id));
                System.out.println("Used for: " + lmao.replace(superSub.getText(), ""));
                System.out.println("Review: " + review.getText().replace(usage.getText(), ""));

                System.out.println("Rating: " + ratString.getText());
                System.out.println("Date: " + date.getText());
                System.out.println("User: " + user.getText());

            } catch (Exception e) {
                System.out.println("caught");
                logger.log(Level.INFO, "ayyyy");
            }
        }
        driver.quit();
    }

}