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

    static WebElement find_usage(int i, WebDriver driver) {
        var usage_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span[3]";
        try {
            WebElement usage = driver.findElement(By.xpath(usage_id));
            return usage;
        } catch (Exception ex) {
            usage_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span";
            try {
                WebElement usage = driver.findElement(By.xpath(usage_id));
                return usage;
            } catch (Exception exc) {
                logger.log(Level.INFO, "usage not found");
                return null;

            }
        }
    }

    static WebElement find_subSpace(int i, WebDriver driver) {
        var subSpace_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span[3]/span";
        try {
            WebElement subSpace = driver.findElement(By.xpath(subSpace_id));
            return subSpace;
        } catch (Exception e) {
            subSpace_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span/span[1]";
            try {
                WebElement subSpace = driver.findElement(By.xpath(subSpace_id));
                return subSpace;
            } catch (Exception e1) {
                subSpace_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span/span";
                try {
                    WebElement subSpace = driver.findElement(By.xpath(subSpace_id));
                    return subSpace;
                } catch (Exception exp) {
                    logger.log(Level.INFO, "subSpace not found");
                    return null;
                }
            }
        }

    }

    static String review_final(WebElement review, WebElement usage) {
        return review.getText().replace(usage.getText(), "");
    }

    static String usage_text(WebElement usage, WebElement subSpace, WebElement superSub) {
        var lmao = "";
        if (superSub != null) {
            lmao = usage.getText().replace(subSpace.getText(), "");
            return lmao.replace(superSub.getText(), "");
        } else {
            return usage.getText().replace(subSpace.getText(), "");

        }
    }

    static WebElement find_superSub(int i, WebDriver driver) {
        var superSub_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span[3]/span[2]";
        try {
            WebElement superSub = driver.findElement(By.xpath(superSub_id));
            return superSub;
        } catch (Exception e) {
            superSub_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p/span/span[2]";
            try {
                WebElement superSub = driver.findElement(By.xpath(superSub_id));
                return superSub;
            } catch (Exception e1) {
                logger.log(Level.INFO, "superSub not found");
                return null;
            }
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var review_id = "";
        var usage_id = "";
        var subSpace_id = "";
        var superSub_id = "";
        var ratString_id = "";
        var date_id = "";
        var user_id = "";
        int reviewsNumber = 0;

        System.setProperty("webdriver.gecko.driver", "/home/sman/Desktop/raflao/geckodriver");
        WebDriver driver = new FirefoxDriver();
        Dimension d = new Dimension(640, 720);
        driver.manage().window().setSize(d);
        driver.get("https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Swift");
        int reviewno = numberOfReviews(driver);
        scrollEnd(driver);
        expandReviews(driver, reviewno);
        System.out.println("Number of reviews: " + reviewno);
        for (int i = 1; i < 1707; i++) {
            logger.log(Level.INFO, "---------------------------------------------------------------" + String.valueOf(i)
                    + "---------------------------------------------------------------");
            review_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p";

            ratString_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[1]/div[1]/span[2]/span";
            date_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[1]/div[1]/span[3]";
            user_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[1]/div[1]/span[1]/span";

            try {

                Thread.sleep(100);
                WebElement review = driver.findElement(By.xpath(review_id));
                WebElement usage = find_usage(i, driver);
                WebElement subSpace = find_subSpace(i, driver);
                WebElement superSub = find_superSub(i, driver);
                WebElement ratString = driver.findElement(By.xpath(ratString_id));
                WebElement date = driver.findElement(By.xpath(date_id));
                WebElement user = driver.findElement(By.xpath(user_id));
                System.out.println("Review: " + review_final(review, usage));
                System.out.println("Usage: " + usage_text(usage, subSpace, superSub));
                System.out.println("Rating: " + ratString.getText());
                System.out.println("Date: " + date.getText());
                System.out.println("User: " + user.getText());
                reviewsNumber++;

            } catch (Exception e) {
                logger.log(Level.INFO, "issue with element");
            }
        }
        logger.log(Level.INFO, String.valueOf(reviewsNumber));
        driver.quit();
    }

}