
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PG2 {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Add elements to an array
    public static String[] add(String[] arr, String... elements) {
        String[] tempArr = new String[arr.length + elements.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        for (int i = 0; i < elements.length; i++)
            tempArr[arr.length + i] = elements[i];
        return tempArr;

    }

    public static String[] compMod(String url) {
        String urle = url.replace("-", "");
        String[] lmfao = {};
        Pattern pattern = Pattern.compile("https://www.zigwheels.com/userreviews/(\\w+/\\w+)");
        Matcher matcher = pattern.matcher(urle);
        try {
            matcher.find();
            lmfao = matcher.group(1).split("/");
            System.out.println(lmfao[0]);

            return lmfao;
        } catch (Exception e) {
            System.out.println(e);
        }
        return lmfao;

    }

    // Number of reviews of a vehicle
    static int numberOfReviews(WebDriver driver) {
        try {
            String revs = "/html/body/div[13]/div/div[1]/section/div[2]/div[1]/div/div/span";
            WebElement reviews = driver.findElement(By.xpath(revs));
            String review = reviews.getText().replace(" reviews", "");
            int reviewno = Integer.parseInt(review);
            if (reviewno > 0) {
                return (reviewno);
            } else {
                return 0;
            }
        } catch (Exception e35) {
            System.out.println("NANDA OMAi");
        }
        return 0;

    }

    // clicks on view more till the last review has appeared
    static void scrollEnd(WebDriver driver) throws InterruptedException {
        String id = "//*[@id='loadMore']/span";
        boolean lmao = true;
        while (lmao == true) {

            try {
                driver.findElement(By.xpath(id)).click();
                Thread.sleep(500);

            } catch (Exception e) {
                // TODO: handle exception
                lmao = false;

            }

        }
    }

    // expands the reviews that have exceeded the character limits on display
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

    // Finds the usage of the car from the review
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

    // USed for section is filtered here
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

    // Prints final review text
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

    // Finds the milage and enigne section if it exists for a review
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

    public static void main(String[] aStrings) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "/home/sman/Desktop/raflao/sman/geckodriver");
        WebDriver driver = new FirefoxDriver();
        Dimension d = new Dimension(640, 720);
        driver.manage().window().setSize(d);
        String[] review_ar = {};
        String[] usage_ar = {};
        String[] date_ar = {};
        String[] user_ar = {};
        String[] rating_ar = {};
        String[] companies = {};
        String[] models = {};
        String[] url = { "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Splendor-Plus",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/HF-Deluxe",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Passion-Pro",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Pleasure-Plus",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Super-Splendor",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Glamour",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Xtreme-160R",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/XPulse-200",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Maestro-Edge-125",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Xtreme-200S",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Splendor-iSmart",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Destini-125",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Maestro-Edge-110-BS6",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/CBZ",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Splendor-iSmart-BS4",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Karizma-ZMR",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Duet",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Maestro-Edge-BS4",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Xtreme-Sports",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Splendor-PRO",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/HF-Dawn",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Passion-XPro",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/HF-Deluxe-BS4",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Pleasure",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Passion-Pro-BS4",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Impulse",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Hunk",
                "https://www.zigwheels.com/user-reviews/Hero-Moto-Corp/Achiever",
                "https://www.zigwheels.com/user-reviews/Honda/Activa-6G",
                "https://www.zigwheels.com/user-reviews/Honda/Shine",
                "https://www.zigwheels.com/user-reviews/Honda/Dio",
                "https://www.zigwheels.com/user-reviews/Honda/Activa-125",
                "https://www.zigwheels.com/user-reviews/Honda/Hness-CB350",
                "https://www.zigwheels.com/user-reviews/Honda/SP-125",
                "https://www.zigwheels.com/user-reviews/Honda/Unicorn",
                "https://www.zigwheels.com/user-reviews/Honda/Hornet-2.0",
                "https://www.zigwheels.com/user-reviews/Honda/Livo",
                "https://www.zigwheels.com/user-reviews/Honda/XBlade",
                "https://www.zigwheels.com/user-reviews/Honda/Grazia",
                "https://www.zigwheels.com/user-reviews/Honda/CD-110-Dream",
                "https://www.zigwheels.com/user-reviews/Honda/Gold-Wing",
                "https://www.zigwheels.com/user-reviews/Honda/CRF1100L-Africa-Twin",
                "https://www.zigwheels.com/user-reviews/Honda/Gold-Wing-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/CBR650R-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/CBR300R-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/CB300R-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/Honda-CB1000R-Plus-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/CB-Hornet-160R-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/CBR250R",
                "https://www.zigwheels.com/user-reviews/Honda/Activa-5G",
                "https://www.zigwheels.com/user-reviews/Honda/XBlade-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/Grazia-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/Cliq",
                "https://www.zigwheels.com/user-reviews/Honda/CRF1000L-Africa-Twin",
                "https://www.zigwheels.com/user-reviews/Honda/Navi",
                "https://www.zigwheels.com/user-reviews/Honda/CB-Shine-SP",
                "https://www.zigwheels.com/user-reviews/Honda/Livo-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/Aviator",
                "https://www.zigwheels.com/user-reviews/Honda/Activa-i",
                "https://www.zigwheels.com/user-reviews/Honda/Activa-4G",
                "https://www.zigwheels.com/user-reviews/Honda/CBR650F",
                "https://www.zigwheels.com/user-reviews/Honda/CB-Unicorn-160",
                "https://www.zigwheels.com/user-reviews/Honda/CD-110-Dream-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/Activa-125-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/Dream-Neo",
                "https://www.zigwheels.com/user-reviews/Honda/Dream-Yuga",
                "https://www.zigwheels.com/user-reviews/Honda/Dio-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/CBR1000RR",
                "https://www.zigwheels.com/user-reviews/Honda/CB-Unicorn-150",
                "https://www.zigwheels.com/user-reviews/Honda/CB-Shine-BS4",
                "https://www.zigwheels.com/user-reviews/Honda/CB1000R",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Classic-350",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Bullet-350",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Meteor-350",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Himalayan",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Interceptor-650",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Continental-GT-650",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Bullet-Trials-500",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Bullet-Trials-350",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Thunderbird-350X",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Thunderbird-500X",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Himalayan-BS4",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Bullet-500",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Thunderbird-350",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Thunderbird-500",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Classic-500",
                "https://www.zigwheels.com/user-reviews/Royal-Enfield/Continental-GT",
                "https://www.zigwheels.com/user-reviews/Yamaha/YZF-R15-V3",
                "https://www.zigwheels.com/user-reviews/Yamaha/MT-15",
                "https://www.zigwheels.com/user-reviews/Yamaha/Fascino-125",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZS-FI-V3",
                "https://www.zigwheels.com/user-reviews/Yamaha/RayZR-125",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZ-FI-Version-3.0",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZ-25",
                "https://www.zigwheels.com/user-reviews/Yamaha/YZF-R1M",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZS-25",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZ-25-BS4",
                "https://www.zigwheels.com/user-reviews/Yamaha/Fazer-153",
                "https://www.zigwheels.com/user-reviews/Yamaha/YZF-R125",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZ-S-Fi-Version-3.0-BS4",
                "https://www.zigwheels.com/user-reviews/Yamaha/RX-135",
                "https://www.zigwheels.com/user-reviews/Yamaha/RX-100",
                "https://www.zigwheels.com/user-reviews/Yamaha/YZF-R3",
                "https://www.zigwheels.com/user-reviews/Yamaha/Fazer-25",
                "https://www.zigwheels.com/user-reviews/Yamaha/Saluto-RX",
                "https://www.zigwheels.com/user-reviews/Yamaha/Ray-ZR",
                "https://www.zigwheels.com/user-reviews/Yamaha/MT-09(2016-2020)",
                "https://www.zigwheels.com/user-reviews/Yamaha/YZF-R15S",
                "https://www.zigwheels.com/user-reviews/Yamaha/Fascino",
                "https://www.zigwheels.com/user-reviews/Yamaha/Saluto",
                "https://www.zigwheels.com/user-reviews/Yamaha/Alpha",
                "https://www.zigwheels.com/user-reviews/Yamaha/SZ-RR",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZ-S-FI-(V-2.0)",
                "https://www.zigwheels.com/user-reviews/Yamaha/Ray-Z",
                "https://www.zigwheels.com/user-reviews/Yamaha/Gladiator",
                "https://www.zigwheels.com/user-reviews/Yamaha/FZ-FI",
                "https://www.zigwheels.com/user-reviews/Yamaha/Fazer-FI",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-150",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-NS200",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-125-Neon",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-220-F",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-RS200",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-NS160",
                "https://www.zigwheels.com/user-reviews/Bajaj/CT-100",
                "https://www.zigwheels.com/user-reviews/Bajaj/Dominar-400",
                "https://www.zigwheels.com/user-reviews/Bajaj/Platina-100",
                "https://www.zigwheels.com/user-reviews/Bajaj/Chetak",
                "https://www.zigwheels.com/user-reviews/Bajaj/Avenger-Cruise-220",
                "https://www.zigwheels.com/user-reviews/Bajaj/Avenger-Street-160",
                "https://www.zigwheels.com/user-reviews/Bajaj/Dominar-250",
                "https://www.zigwheels.com/user-reviews/Bajaj/Platina-110-H-Gear",
                "https://www.zigwheels.com/user-reviews/Bajaj/CT110",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-180F",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-150(2009-2014)",
                "https://www.zigwheels.com/user-reviews/Bajaj/Avenger-Street-220-BS4",
                "https://www.zigwheels.com/user-reviews/Bajaj/Avenger-Street-180",
                "https://www.zigwheels.com/user-reviews/Bajaj/Discover-110",
                "https://www.zigwheels.com/user-reviews/Bajaj/V12",
                "https://www.zigwheels.com/user-reviews/Bajaj/V15-Power-Up",
                "https://www.zigwheels.com/user-reviews/Bajaj/Discover-125",
                "https://www.zigwheels.com/user-reviews/Bajaj/Platina-110",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-135LS",
                "https://www.zigwheels.com/user-reviews/Bajaj/Pulsar-180",
                "https://www.zigwheels.com/user-reviews/Bajaj/Boxer",
                "https://www.zigwheels.com/user-reviews/TVS/Apache-RTR-160",
                "https://www.zigwheels.com/user-reviews/TVS/Apache-RTR-160-4V",
                "https://www.zigwheels.com/user-reviews/TVS/Apache-RTR-200-4V",
                "https://www.zigwheels.com/user-reviews/TVS/Apache-RR-310",
                "https://www.zigwheels.com/user-reviews/TVS/Apache-RTR-180",
                "https://www.zigwheels.com/user-reviews/TVS/Jupiter",
                "https://www.zigwheels.com/user-reviews/TVS/NTORQ-125",
                "https://www.zigwheels.com/user-reviews/TVS/XL100", "https://www.zigwheels.com/user-reviews/TVS/Sport",
                "https://www.zigwheels.com/user-reviews/TVS/Radeon",
                "https://www.zigwheels.com/user-reviews/TVS/Scooty-Pep-Plus",
                "https://www.zigwheels.com/user-reviews/TVS/Star-City-Plus",
                "https://www.zigwheels.com/user-reviews/TVS/Scooty-Zest",
                "https://www.zigwheels.com/user-reviews/TVS/iQube-Electric",
                "https://www.zigwheels.com/user-reviews/TVS/Star",
                "https://www.zigwheels.com/user-reviews/TVS/Star-City-Plus-BS4",
                "https://www.zigwheels.com/user-reviews/TVS/Velocity",
                "https://www.zigwheels.com/user-reviews/TVS/Jupiter-Grande",
                "https://www.zigwheels.com/user-reviews/TVS/Scooty-Streak",
                "https://www.zigwheels.com/user-reviews/TVS/Wego",
                "https://www.zigwheels.com/user-reviews/Jawa-Motorcycles/Perak",
                "https://www.zigwheels.com/user-reviews/Jawa-Motorcycles/42",
                "https://www.zigwheels.com/user-reviews/Jawa-Motorcycles/Jawa",
                "https://www.zigwheels.com/user-reviews/KTM/200-Duke",
                "https://www.zigwheels.com/user-reviews/KTM/125-Duke",
                "https://www.zigwheels.com/user-reviews/KTM/390-Duke",
                "https://www.zigwheels.com/user-reviews/KTM/RC-200",
                "https://www.zigwheels.com/user-reviews/KTM/RC-125",
                "https://www.zigwheels.com/user-reviews/KTM/RC-390",
                "https://www.zigwheels.com/user-reviews/KTM/250-Duke",
                "https://www.zigwheels.com/user-reviews/KTM/390-Adventure",
                "https://www.zigwheels.com/user-reviews/KTM/250-Adventure",
                "https://www.zigwheels.com/user-reviews/KTM/1050-Adventure",
                "https://www.zigwheels.com/user-reviews/Suzuki/Access-125",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer-SF",
                "https://www.zigwheels.com/user-reviews/Suzuki/Burgman-Street",
                "https://www.zigwheels.com/user-reviews/Suzuki/Intruder",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer-SF-250",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer-250",
                "https://www.zigwheels.com/user-reviews/Suzuki/V-Strom-650XT",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer-SF-250-BS4",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer-250-BS4",
                "https://www.zigwheels.com/user-reviews/Suzuki/Access-125-BS4",
                "https://www.zigwheels.com/user-reviews/Suzuki/GSX-S1000F",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer-SF-(2015-2018)",
                "https://www.zigwheels.com/user-reviews/Suzuki/Gixxer-(2014-2018)",
                "https://www.zigwheels.com/user-reviews/Suzuki/V-Strom-1000",
                "https://www.zigwheels.com/user-reviews/Suzuki/Hayate",
                "https://www.zigwheels.com/user-reviews/Revolt-Motors/RV400",
                "https://www.zigwheels.com/user-reviews/Revolt-Motors/RV300",
                "https://www.zigwheels.com/user-reviews/CFMoto/300NK",
                "https://www.zigwheels.com/user-reviews/CFMoto/650MT",
                "https://www.zigwheels.com/user-reviews/CFMoto/650NK",
                "https://www.zigwheels.com/user-reviews/CFMoto/650GT",
                "https://www.zigwheels.com/user-reviews/CFMoto/650NK-BS4",
                "https://www.zigwheels.com/user-reviews/CFMoto/650MT-BS4",
                "https://www.zigwheels.com/user-reviews/CFMoto/650GT-BS4",
                "https://www.zigwheels.com/user-reviews/CFMoto/300NK-BS4",
                "https://www.zigwheels.com/user-reviews/22Kymco/Flow",
                "https://www.zigwheels.com/user-reviews/22Kymco/iFlow",
                "https://www.zigwheels.com/user-reviews/Vespa/VXL-150",
                "https://www.zigwheels.com/user-reviews/Vespa/SXL-125",
                "https://www.zigwheels.com/user-reviews/Vespa/Notte-125",
                "https://www.zigwheels.com/user-reviews/Vespa/VXL-125",
                "https://www.zigwheels.com/user-reviews/Vespa/LX-125",
                "https://www.zigwheels.com/user-reviews/Vespa/SXL-150",
                "https://www.zigwheels.com/user-reviews/Vespa/Elegante-150",
                "https://www.zigwheels.com/user-reviews/Vespa/ZX-125",
                "https://www.zigwheels.com/user-reviews/Vespa/Urban-Club-125",
                "https://www.zigwheels.com/user-reviews/Vespa/Connectivity",
                "https://www.zigwheels.com/user-reviews/Vespa/RED-125",
                "https://www.zigwheels.com/user-reviews/Vespa/946-Emporio-Armani-Edition",
                "https://www.zigwheels.com/user-reviews/Vespa/SXL-150-BS4",
                "https://www.zigwheels.com/user-reviews/Vespa/Elegante-125",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-1000SX",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z900",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Vulcan-S",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z-H2",
                "https://www.zigwheels.com/user-reviews/Kawasaki/KLX-140",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z650",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Versys-650",
                "https://www.zigwheels.com/user-reviews/Kawasaki/KLX-110",
                "https://www.zigwheels.com/user-reviews/Kawasaki/KX-100",
                "https://www.zigwheels.com/user-reviews/Kawasaki/KX-250",
                "https://www.zigwheels.com/user-reviews/Kawasaki/KX-450F",
                "https://www.zigwheels.com/user-reviews/Kawasaki/KLX-450R",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Versys-1000",
                "https://www.zigwheels.com/user-reviews/Kawasaki/W800-Street",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-650",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z900RS-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z1000-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/W800-Street-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Vulcan-S-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Versys-X-300-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Versys-650-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Kawasaki-Versys-1000-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-ZX-6R-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-ZX-10R-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-ZX-14R-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-H2-SX-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-H2-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-400-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-300-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-1000-BS4",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-ZX-10RR",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Ninja-650-(2011-2020)",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z900-(2018-2020)",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z650-(2017-2020)",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Versys-1000-(2014-2018)",
                "https://www.zigwheels.com/user-reviews/Kawasaki/ER---6n",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Eliminator",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z250",
                "https://www.zigwheels.com/user-reviews/Kawasaki/Z800",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Iron-883",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Street-750",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Fat-Boy",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Forty-Eight",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Street-Rod",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/1200-Custom",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Road-King",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Low-Rider",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Forty-Eight-Special",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Low-Rider-S",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Iron-1200",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Harley-Davidson-Dyna",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/FXDC-SUPER-GLIDE",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Fat-Boy-Anniversary",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Deluxe",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Fat-Bob",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Heritage-Classic",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Road-Glide-Special",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Street-Bob",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Street-Glide-Special",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/Roadster",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/CVO-Limited",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/DYNA",
                "https://www.zigwheels.com/user-reviews/Harley-Davidson/SOFTAIL",
                "https://www.zigwheels.com/user-reviews/Ducati/Scrambler-800",
                "https://www.zigwheels.com/user-reviews/Ducati/Panigale-V2",
                "https://www.zigwheels.com/user-reviews/Ducati/Scrambler-1100",
                "https://www.zigwheels.com/user-reviews/Ducati/Multistrada-950",
                "https://www.zigwheels.com/user-reviews/Ducati/XDiavel-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/SuperSport-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Scrambler-Desert-Sled-BS",
                "https://www.zigwheels.com/user-reviews/Ducati/Scrambler-1100-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Scrambler-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Panigale-V4-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Multistrada-950-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Multistrada-1260-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-821-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-797-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Hypermotard-950-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Diavel-1260-BS4",
                "https://www.zigwheels.com/user-reviews/Ducati/Hyperstrada",
                "https://www.zigwheels.com/user-reviews/Ducati/1199-Panigale",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-1200-S-Stripe",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-1200-S",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-796",
                "https://www.zigwheels.com/user-reviews/Ducati/1198",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-821-Dark",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-795",
                "https://www.zigwheels.com/user-reviews/Ducati/Panigale-R",
                "https://www.zigwheels.com/user-reviews/Ducati/1299-Panigale-S",
                "https://www.zigwheels.com/user-reviews/Ducati/Diavel-Diesel",
                "https://www.zigwheels.com/user-reviews/Ducati/1299-Superleggera",
                "https://www.zigwheels.com/user-reviews/Ducati/959-Panigale",
                "https://www.zigwheels.com/user-reviews/Ducati/Monster-1200",
                "https://www.zigwheels.com/user-reviews/Ducati/1299-Panigale",
                "https://www.zigwheels.com/user-reviews/Ducati/Panigale",
                "https://www.zigwheels.com/user-reviews/Ducati/899-Panigale",
                "https://www.zigwheels.com/user-reviews/Ducati/Streetfighter",
                "https://www.zigwheels.com/user-reviews/Ducati/848",
                "https://www.zigwheels.com/user-reviews/Ducati/Multistrada",
                "https://www.zigwheels.com/user-reviews/Ducati/Hypermotard-939",
                "https://www.zigwheels.com/user-reviews/Ducati/Diavel",
                "https://www.zigwheels.com/user-reviews/Triumph/Rocket-3",
                "https://www.zigwheels.com/user-reviews/Triumph/Bonneville-T120",
                "https://www.zigwheels.com/user-reviews/Triumph/Street-Triple",
                "https://www.zigwheels.com/user-reviews/Triumph/Street-Twin",
                "https://www.zigwheels.com/user-reviews/Triumph/Bonneville-T100",
                "https://www.zigwheels.com/user-reviews/Triumph/Thunderbird-LT",
                "https://www.zigwheels.com/user-reviews/Triumph/Bonneville-Speedmaster",
                "https://www.zigwheels.com/user-reviews/Triumph/Tiger-900",
                "https://www.zigwheels.com/user-reviews/Triumph/Tiger-Sport",
                "https://www.zigwheels.com/user-reviews/Triumph/Rocket-III",
                "https://www.zigwheels.com/user-reviews/Triumph/Tiger-800",
                "https://www.zigwheels.com/user-reviews/Triumph/Tiger-Explorer",
                "https://www.zigwheels.com/user-reviews/Triumph/Daytona-675",
                "https://www.zigwheels.com/user-reviews/Triumph/Speed-Triple",
                "https://www.zigwheels.com/user-reviews/Triumph/2017-Thruxton",
                "https://www.zigwheels.com/user-reviews/BMW/G-310-R",
                "https://www.zigwheels.com/user-reviews/BMW/S-1000-RR",
                "https://www.zigwheels.com/user-reviews/BMW/G-310-GS",
                "https://www.zigwheels.com/user-reviews/BMW/R-1250-GS",
                "https://www.zigwheels.com/user-reviews/BMW/R-1250-GS-Adventure",
                "https://www.zigwheels.com/user-reviews/BMW/F-900-R",
                "https://www.zigwheels.com/user-reviews/BMW/F-900-XR",
                "https://www.zigwheels.com/user-reviews/BMW/R-1250-RT",
                "https://www.zigwheels.com/user-reviews/BMW/R-1250-R",
                "https://www.zigwheels.com/user-reviews/BMW/S-1000-XR",
                "https://www.zigwheels.com/user-reviews/BMW/R-18",
                "https://www.zigwheels.com/user-reviews/BMW/F-850-GS-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/F-750-GS-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/S-1000-RR-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/R-NineT-Scrambler-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-R-NineT-Racer-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-R-nineT-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-R-1250-RT",
                "https://www.zigwheels.com/user-reviews/BMW/R-1250-R-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-R-1250-GS-Adventure-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-R-1250-GS-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-K-1600-GTL-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-K-1600-B-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-G-310-R-BS4",
                "https://www.zigwheels.com/user-reviews/BMW/HP2-Sport",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-K1300S",
                "https://www.zigwheels.com/user-reviews/BMW/R-1200-RS",
                "https://www.zigwheels.com/user-reviews/BMW/R-1200-RT",
                "https://www.zigwheels.com/user-reviews/BMW/R-1200-GS-Adventure",
                "https://www.zigwheels.com/user-reviews/BMW/R-1200-R",
                "https://www.zigwheels.com/user-reviews/BMW/S-1000-XR-(2017-2020)",
                "https://www.zigwheels.com/user-reviews/BMW/S-1000-R",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-F700",
                "https://www.zigwheels.com/user-reviews/BMW/BMW-F650",
                "https://www.zigwheels.com/user-reviews/BMW/K-1300-R",
                "https://www.zigwheels.com/user-reviews/BMW/R-1200-GS",
                "https://www.zigwheels.com/user-reviews/Benelli/Imperiale-400",
                "https://www.zigwheels.com/user-reviews/Benelli/BMW-G-310-GS-BS4",
                "https://www.zigwheels.com/user-reviews/Benelli/TRK-502-BS4",
                "https://www.zigwheels.com/user-reviews/Benelli/TNT-600i-BS4",
                "https://www.zigwheels.com/user-reviews/Benelli/TNT-300-BS4",
                "https://www.zigwheels.com/user-reviews/Benelli/Leoncino-500-BS4",
                "https://www.zigwheels.com/user-reviews/Benelli/Leoncino-250-BS4",
                "https://www.zigwheels.com/user-reviews/Benelli/302R-BS4",
                "https://www.zigwheels.com/user-reviews/Benelli/Trek-1130",
                "https://www.zigwheels.com/user-reviews/Benelli/TNT-25",
                "https://www.zigwheels.com/user-reviews/Benelli/TNT-899",
                "https://www.zigwheels.com/user-reviews/Benelli/TNT-600-GT",
                "https://www.zigwheels.com/user-reviews/Benelli/TNT-R",
                "https://www.zigwheels.com/user-reviews/Husqvarna/Vitpilen-250",
                "https://www.zigwheels.com/user-reviews/Husqvarna/Svartpilen-250",
                "https://www.zigwheels.com/user-reviews/YObykes/Drift",
                "https://www.zigwheels.com/user-reviews/YObykes/Edge",
                "https://www.zigwheels.com/user-reviews/Palatino/Angel",
                "https://www.zigwheels.com/user-reviews/Palatino/Sunshine",
                "https://www.zigwheels.com/user-reviews/Palatino/Princess",
                "https://www.zigwheels.com/user-reviews/Palatino/Spyker",
                "https://www.zigwheels.com/user-reviews/Palatino/Ryan",
                "https://www.zigwheels.com/user-reviews/Okinawa/Praise",
                "https://www.zigwheels.com/user-reviews/Okinawa/i-Praise",
                "https://www.zigwheels.com/user-reviews/Okinawa/PraisePro",
                "https://www.zigwheels.com/user-reviews/Okinawa/R30-electric-scooter",
                "https://www.zigwheels.com/user-reviews/Okinawa/Lite",
                "https://www.zigwheels.com/user-reviews/Okinawa/Ridge",
                "https://www.zigwheels.com/user-reviews/Okinawa/Raise",
                "https://www.zigwheels.com/user-reviews/Okinawa/Dual",
                "https://www.zigwheels.com/user-reviews/MV-Agusta/Brutale-1090",
                "https://www.zigwheels.com/user-reviews/MV-Agusta/F4",
                "https://www.zigwheels.com/user-reviews/MV-Agusta/F3",
                "https://www.zigwheels.com/user-reviews/MV-Agusta/Brutale",
                "https://www.zigwheels.com/user-reviews/Lohia/Oma-Star",
                "https://www.zigwheels.com/user-reviews/Lohia/Oma-Star-Li",
                "https://www.zigwheels.com/user-reviews/Lohia/Elit-3000",
                "https://www.zigwheels.com/user-reviews/Lohia/Genius",
                "https://www.zigwheels.com/user-reviews/Lohia/Fame",
                "https://www.zigwheels.com/user-reviews/Li-ions-Elektrik-Solutions/Spock-Electric-Scooter",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/E-Zephyr-TX",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/Essentia-TX",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/Glide-TX",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/E-Zephyr",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/Glide-Unisex",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/Glide-Lady",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/Essentia",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/Townmaster",
                "https://www.zigwheels.com/user-reviews/Lectro-Electric/EHX20",
                "https://www.zigwheels.com/user-reviews/Essel-Energy/GET-7",
                "https://www.zigwheels.com/user-reviews/Essel-Energy/GET-1",
                "https://www.zigwheels.com/user-reviews/BGauss/B8", "https://www.zigwheels.com/user-reviews/BGauss/A2",
                "https://www.zigwheels.com/user-reviews/Avon/E-Scoot",
                "https://www.zigwheels.com/user-reviews/Avon/E-Plus",
                "https://www.zigwheels.com/user-reviews/Avon/E-Lite",
                "https://www.zigwheels.com/user-reviews/Avon/E-Mate",
                "https://www.zigwheels.com/user-reviews/Avon/E-Star",
                "https://www.zigwheels.com/user-reviews/Avon/Avon-E-Bike-VX",
                "https://www.zigwheels.com/user-reviews/Avon/E-Magic",
                "https://www.zigwheels.com/user-reviews/Avon/E-Bike",
                "https://www.zigwheels.com/user-reviews/Ather-Energy/450X",
                "https://www.zigwheels.com/user-reviews/Ather-Energy/450",
                "https://www.zigwheels.com/user-reviews/Ather-Energy/340",
                "https://www.zigwheels.com/user-reviews/Ampere/Reo",
                "https://www.zigwheels.com/user-reviews/Ampere/V48",
                "https://www.zigwheels.com/user-reviews/Ampere/Zeal",
                "https://www.zigwheels.com/user-reviews/Ampere/Reo-Elite",
                "https://www.zigwheels.com/user-reviews/Ampere/Magnus",
                "https://www.zigwheels.com/user-reviews/Aftek-Motors/Scorpion",
                "https://www.zigwheels.com/user-reviews/Aftek-Motors/Royal-Plus",
                "https://www.zigwheels.com/user-reviews/Aftek-Motors/Skipper",
                "https://www.zigwheels.com/user-reviews/Aftek-Motors/Turbo-170",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Optima-LA",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Flash",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/NYX",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Optima-HS500-ER",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Dash",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Photon",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Optima-E5",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Optima-Li",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Photon-48V",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Hero-Electric-Zion",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Hero-Electric-Zippy",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Optima-DX",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Optima-Plus",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Cruz",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Wave",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Maxi",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/E-Sprint",
                "https://www.zigwheels.com/user-reviews/Hero-Electric/Avior-E-Cycle",
                "https://www.zigwheels.com/user-reviews/Mahindra/Mojo-300-BS6",
                "https://www.zigwheels.com/user-reviews/Mahindra/Mojo-300-BS4",
                "https://www.zigwheels.com/user-reviews/Mahindra/MOJO-UT-300",
                "https://www.zigwheels.com/user-reviews/Mahindra/Genze-2.0",
                "https://www.zigwheels.com/user-reviews/Mahindra/Gusto-125",
                "https://www.zigwheels.com/user-reviews/Mahindra/MOJO-XT-300",
                "https://www.zigwheels.com/user-reviews/Mahindra/Gusto",
                "https://www.zigwheels.com/user-reviews/Mahindra/Stallio",
                "https://www.zigwheels.com/user-reviews/Mahindra/Centuro",
                "https://www.zigwheels.com/user-reviews/Mahindra/Pantero",
                "https://www.zigwheels.com/user-reviews/Mahindra/SYM-Flyte",
                "https://www.zigwheels.com/user-reviews/Mahindra/Duro",
                "https://www.zigwheels.com/user-reviews/Mahindra/Rodeo",
                "https://www.zigwheels.com/user-reviews/Mahindra/kine",
                "https://www.zigwheels.com/user-reviews/Indian/Springfield-Dark-Horse",
                "https://www.zigwheels.com/user-reviews/Indian/Springfield-Dark-Horse-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Springfield-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Scout-Sixty-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Scout-Bobber-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/scout-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Roadmaster-Elite-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Roadmaster-Classic-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Roadmaster-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/FTR-1200-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chieftain-Limited-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chieftain-Elite-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chieftain-Dark-Horse-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Indian-Chieftain-Classic-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chieftain-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chief-Vintage-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chief-Dark-Horse-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chief-Classic-BS4",
                "https://www.zigwheels.com/user-reviews/Indian/Chief",
                "https://www.zigwheels.com/user-reviews/Indian/Chief-Dark-Horse",
                "https://www.zigwheels.com/user-reviews/Indian/Chief",
                "https://www.zigwheels.com/user-reviews/Moto-Guzzi/California-1400-Touring",
                "https://www.zigwheels.com/user-reviews/Moto-Guzzi/Audace-1400",
                "https://www.zigwheels.com/user-reviews/Aprilia/SXR-160",
                "https://www.zigwheels.com/user-reviews/Aprilia/SR-160",
                "https://www.zigwheels.com/user-reviews/Aprilia/SR-125",
                "https://www.zigwheels.com/user-reviews/Aprilia/Storm-125",
                "https://www.zigwheels.com/user-reviews/Aprilia/Tuono-BS4",
                "https://www.zigwheels.com/user-reviews/Aprilia/Facelift",
                "https://www.zigwheels.com/user-reviews/Aprilia/SRV-850",
                "https://www.zigwheels.com/user-reviews/Aprilia/RS4",
                "https://www.zigwheels.com/user-reviews/Aprilia/SR-150-Race",
                "https://www.zigwheels.com/user-reviews/Aprilia/SRV-850",
                "https://www.zigwheels.com/user-reviews/Aprilia/SR-150",
                "https://www.zigwheels.com/user-reviews/Aprilia/RSV4-(2012-2019)",
                "https://www.zigwheels.com/user-reviews/Aprilia/Caponord-1200",
                "https://www.zigwheels.com/user-reviews/Aprilia/Dorsoduro-1200",
                "https://www.zigwheels.com/user-reviews/Aprilia/Mana" };
        for (int k = 0; k < url.length; k++) {

            var review_id = "";

            var ratString_id = "";
            var date_id = "";
            var user_id = "";
            var company = "";
            var model = "";
            int reviewsNumber = 0;
            int breakPoint = 0;

            driver.get(url[k]);
            Thread.sleep(3000);
            String[] lmfao = compMod(url[k]);
            company = lmfao[0];
            model = lmfao[1];
            int reviewno = numberOfReviews(driver);
            scrollEnd(driver);
            expandReviews(driver, reviewno);
            System.out.println("Number of reviews: " + reviewno);
            for (int i = 1; i < reviewno; i++) {
                logger.log(Level.INFO, "---------------------------------------------------------------"
                        + String.valueOf(i) + "---------------------------------------------------------------");
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

                    review_ar = add(review_ar, review_final(review, usage));
                    try {
                        usage_ar = add(usage_ar, usage_text(usage, subSpace, superSub).replace("\n", " "));

                    } catch (Exception e) {
                        // TODO: handle exception
                        usage_ar = add(usage_ar, usage_text(usage, subSpace, superSub));

                    }
                    rating_ar = add(rating_ar, ratString.getText());
                    date_ar = add(date_ar, date.getText());
                    user_ar = add(user_ar, user.getText());
                    companies = add(companies, company);
                    models = add(models, model);

                    System.out.println("collecting");
                    breakPoint = 0;
                    reviewsNumber++;

                } catch (Exception e) {
                    breakPoint++;
                    if (breakPoint > 20) {
                        break;
                    }

                    logger.log(Level.INFO, "issue with element");
                }
            }
            logger.log(Level.INFO, "Number of actual reviews :: " + String.valueOf(reviewsNumber));

        }
        System.out.println(review_ar.length);
        System.out.println(usage_ar.length);
        System.out.println(date_ar.length);
        System.out.println(rating_ar.length);
        System.out.println(user_ar.length);
        ResultGenerator.addDataToCSV(companies, models, review_ar, user_ar, date_ar, usage_ar, rating_ar);

        driver.quit();
    }

}