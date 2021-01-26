
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
        String[] url = { "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Swift",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Baleno",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Vitara-Brezza",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Dzire",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Ertiga",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Wagon-R",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Alto-800",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Celerio",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/S-Presso",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/S-Cross",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/XL6",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Ignis",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Ciaz",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Eeco",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Celerio-X",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Swift-Dzire-Tour",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Baleno-RS",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Baleno-2014-2018",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/S-Cross-2017-2020",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Vitara-Brezza-2016-2020",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Dzire-2017-2020",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Alto-K10",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Wagon-R-2001-2018",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Zen",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Esteem",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/800",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Estilo",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Ritz",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/A-Star",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/SX4",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Omni",
                "https://www.zigwheels.com/user-reviews/Maruti-Suzuki/Gypsy",
                "https://www.zigwheels.com/user-reviews/MG-Motor/Hector",
                "https://www.zigwheels.com/user-reviews/MG-Motor/Gloster",
                "https://www.zigwheels.com/user-reviews/MG-Motor/ZS-EV",
                "https://www.zigwheels.com/user-reviews/MG-Motor/Hector-Plus",
                "https://www.zigwheels.com/user-reviews/MG-Motor/D90",
                "https://www.zigwheels.com/user-reviews/MG-Motor/Hector-2019-2021",
                "https://www.zigwheels.com/user-reviews/Hyundai/Creta",
                "https://www.zigwheels.com/user-reviews/Hyundai/i20",
                "https://www.zigwheels.com/user-reviews/Hyundai/Venue",
                "https://www.zigwheels.com/user-reviews/Hyundai/Grand-i10",
                "https://www.zigwheels.com/user-reviews/Hyundai/Verna",
                "https://www.zigwheels.com/user-reviews/Hyundai/Aura",
                "https://www.zigwheels.com/user-reviews/Hyundai/Santro",
                "https://www.zigwheels.com/user-reviews/Hyundai/Tucson",
                "https://www.zigwheels.com/user-reviews/Hyundai/Elantra",
                "https://www.zigwheels.com/user-reviews/Hyundai/Kona-Electric",
                "https://www.zigwheels.com/user-reviews/Hyundai/Grand-i10-Nios",
                "https://www.zigwheels.com/user-reviews/Hyundai/Xcent-2020",
                "https://www.zigwheels.com/user-reviews/Hyundai/Creta-2015-2020",
                "https://www.zigwheels.com/user-reviews/Hyundai/Elantra-2015-2019",
                "https://www.zigwheels.com/user-reviews/Hyundai/Verna-2017-2020",
                "https://www.zigwheels.com/user-reviews/Hyundai/i20-Active",
                "https://www.zigwheels.com/user-reviews/Hyundai/Elite-i20-2017-2020",
                "https://www.zigwheels.com/user-reviews/Hyundai/Tucson-2016-2020",
                "https://www.zigwheels.com/user-reviews/Hyundai/Xcent",
                "https://www.zigwheels.com/user-reviews/Hyundai/EON",
                "https://www.zigwheels.com/user-reviews/Hyundai/Santro-Xing",
                "https://www.zigwheels.com/user-reviews/Hyundai/i10",
                "https://www.zigwheels.com/user-reviews/Hyundai/Accent",
                "https://www.zigwheels.com/user-reviews/Honda/City",
                "https://www.zigwheels.com/user-reviews/Honda/Amaze",
                "https://www.zigwheels.com/user-reviews/Honda/Civic",
                "https://www.zigwheels.com/user-reviews/Honda/Jazz",
                "https://www.zigwheels.com/user-reviews/Honda/WR-V",
                "https://www.zigwheels.com/user-reviews/Honda/CR-V",
                "https://www.zigwheels.com/user-reviews/Honda/City-4th-Generation",
                "https://www.zigwheels.com/user-reviews/Honda/Accord",
                "https://www.zigwheels.com/user-reviews/Honda/Jazz-2018-2020",
                "https://www.zigwheels.com/user-reviews/Honda/WRV-2017-2020",
                "https://www.zigwheels.com/user-reviews/Honda/Brio", "https://www.zigwheels.com/user-reviews/Honda/BRV",
                "https://www.zigwheels.com/user-reviews/Honda/Mobilio",
                "https://www.zigwheels.com/user-reviews/Honda/CR-V-2009-2018",
                "https://www.zigwheels.com/user-reviews/Toyota/Fortuner",
                "https://www.zigwheels.com/user-reviews/Toyota/Innova-Crysta",
                "https://www.zigwheels.com/user-reviews/Toyota/Glanza",
                "https://www.zigwheels.com/user-reviews/Toyota/Yaris",
                "https://www.zigwheels.com/user-reviews/Toyota/Vellfire",
                "https://www.zigwheels.com/user-reviews/Toyota/Camry",
                "https://www.zigwheels.com/user-reviews/Toyota/Urban-Cruiser",
                "https://www.zigwheels.com/user-reviews/Toyota/Tercel",
                "https://www.zigwheels.com/user-reviews/Toyota/Starlet",
                "https://www.zigwheels.com/user-reviews/Toyota/Sera",
                "https://www.zigwheels.com/user-reviews/Toyota/Premio",
                "https://www.zigwheels.com/user-reviews/Toyota/prado",
                "https://www.zigwheels.com/user-reviews/Toyota/Majesta",
                "https://www.zigwheels.com/user-reviews/Toyota/Estima",
                "https://www.zigwheels.com/user-reviews/Toyota/Cynos",
                "https://www.zigwheels.com/user-reviews/Toyota/Corona",
                "https://www.zigwheels.com/user-reviews/Toyota/Fortuner-2016-2021",
                "https://www.zigwheels.com/user-reviews/Toyota/Innova-Crysta-2016-2020",
                "https://www.zigwheels.com/user-reviews/Toyota/Camry",
                "https://www.zigwheels.com/user-reviews/Toyota/Etios-Liva",
                "https://www.zigwheels.com/user-reviews/Toyota/Platinum-Etios",
                "https://www.zigwheels.com/user-reviews/Toyota/Etios-Cross",
                "https://www.zigwheels.com/user-reviews/Toyota/Land-Cruiser",
                "https://www.zigwheels.com/user-reviews/Toyota/Qualis",
                "https://www.zigwheels.com/user-reviews/Toyota/Prius",
                "https://www.zigwheels.com/user-reviews/Toyota/Corolla-Altis",
                "https://www.zigwheels.com/user-reviews/Toyota/Land-Cruiser-Prado",
                "https://www.zigwheels.com/user-reviews/Toyota/Innova",
                "https://www.zigwheels.com/user-reviews/Mahindra/Thar",
                "https://www.zigwheels.com/user-reviews/Mahindra/Scorpio",
                "https://www.zigwheels.com/user-reviews/Mahindra/XUV300",
                "https://www.zigwheels.com/user-reviews/Mahindra/Bolero",
                "https://www.zigwheels.com/user-reviews/Mahindra/XUV500",
                "https://www.zigwheels.com/user-reviews/Mahindra/Marazzo",
                "https://www.zigwheels.com/user-reviews/Mahindra/TUV-300",
                "https://www.zigwheels.com/user-reviews/Mahindra/KUV100-NXT",
                "https://www.zigwheels.com/user-reviews/Mahindra/Alturas-G4",
                "https://www.zigwheels.com/user-reviews/Mahindra/TUV-300-Plus",
                "https://www.zigwheels.com/user-reviews/Mahindra/E-Verito",
                "https://www.zigwheels.com/user-reviews/Mahindra/Bolero-Power-Plus",
                "https://www.zigwheels.com/user-reviews/Mahindra/Bolero-Pik-Up",
                "https://www.zigwheels.com/user-reviews/Mahindra/Marshal",
                "https://www.zigwheels.com/user-reviews/Mahindra/Jeep",
                "https://www.zigwheels.com/user-reviews/Mahindra/Supro",
                "https://www.zigwheels.com/user-reviews/Mahindra/e2oPlus",
                "https://www.zigwheels.com/user-reviews/Mahindra/NuvoSport",
                "https://www.zigwheels.com/user-reviews/Mahindra/Armada",
                "https://www.zigwheels.com/user-reviews/Mahindra/Verito-Vibe",
                "https://www.zigwheels.com/user-reviews/Mahindra/e2o",
                "https://www.zigwheels.com/user-reviews/Mahindra/Quanto",
                "https://www.zigwheels.com/user-reviews/Mahindra/Bolero-2011-2019",
                "https://www.zigwheels.com/user-reviews/Mahindra/Verito",
                "https://www.zigwheels.com/user-reviews/Mahindra/Xylo",
                "https://www.zigwheels.com/user-reviews/Mahindra/Logan",
                "https://www.zigwheels.com/user-reviews/Mahindra/Thar-2015-2019",
                "https://www.zigwheels.com/user-reviews/Tata/Altroz",
                "https://www.zigwheels.com/user-reviews/Tata/Harrier",
                "https://www.zigwheels.com/user-reviews/Tata/Nexon",
                "https://www.zigwheels.com/user-reviews/Tata/Tiago",
                "https://www.zigwheels.com/user-reviews/Tata/Tigor",
                "https://www.zigwheels.com/user-reviews/Tata/Yodha-Pickup",
                "https://www.zigwheels.com/user-reviews/Tata/Tigor-EV",
                "https://www.zigwheels.com/user-reviews/Tata/Nexon-EV",
                "https://www.zigwheels.com/user-reviews/Tata/Tiago-2019-2020",
                "https://www.zigwheels.com/user-reviews/Tata/Tigor-JTP",
                "https://www.zigwheels.com/user-reviews/Tata/Tiago-JTP",
                "https://www.zigwheels.com/user-reviews/Tata/Tiago-NRG",
                "https://www.zigwheels.com/user-reviews/Tata/Hexa-2017-2020",
                "https://www.zigwheels.com/user-reviews/Tata/Nexon-2017-2020",
                "https://www.zigwheels.com/user-reviews/Tata/Tigor-2017-2020",
                "https://www.zigwheels.com/user-reviews/Tata/Safari-Storme",
                "https://www.zigwheels.com/user-reviews/Tata/Nano", "https://www.zigwheels.com/user-reviews/Tata/Bolt",
                "https://www.zigwheels.com/user-reviews/Tata/Zest",
                "https://www.zigwheels.com/user-reviews/Tata/Indica-eV2",
                "https://www.zigwheels.com/user-reviews/Tata/Sumo",
                "https://www.zigwheels.com/user-reviews/Tata/Indigo-eCS",
                "https://www.zigwheels.com/user-reviews/Tata/Manza",
                "https://www.zigwheels.com/user-reviews/Tata/Vista",
                "https://www.zigwheels.com/user-reviews/Tata/Safari-2005-2017",
                "https://www.zigwheels.com/user-reviews/Tata/Indica",
                "https://www.zigwheels.com/user-reviews/Kia/Sonet", "https://www.zigwheels.com/user-reviews/Kia/Seltos",
                "https://www.zigwheels.com/user-reviews/Kia/Carnival",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Polo",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Vento",
                "https://www.zigwheels.com/user-reviews/Volkswagen/T-Roc",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Tiguan-Allspace",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Polo-GTI",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Multivan",
                "https://www.zigwheels.com/user-reviews/Volkswagen/T-Cross",
                "https://www.zigwheels.com/user-reviews/Volkswagen/GTI",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Vento-2015-2019",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Polo-2015-2019",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Tiguan",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Passat",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Ameo",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Jetta",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Touareg",
                "https://www.zigwheels.com/user-reviews/Volkswagen/Touareg",
                "https://www.zigwheels.com/user-reviews/Ford/EcoSport",
                "https://www.zigwheels.com/user-reviews/Ford/Endeavour",
                "https://www.zigwheels.com/user-reviews/Ford/Figo",
                "https://www.zigwheels.com/user-reviews/Ford/Freestyle",
                "https://www.zigwheels.com/user-reviews/Ford/Aspire",
                "https://www.zigwheels.com/user-reviews/Ford/Endeavour-2015-2020",
                "https://www.zigwheels.com/user-reviews/Ford/Mustang",
                "https://www.zigwheels.com/user-reviews/Ford/Ecosport-2015-2021",
                "https://www.zigwheels.com/user-reviews/Ford/Fiesta",
                "https://www.zigwheels.com/user-reviews/Ford/Ikon",
                "https://www.zigwheels.com/user-reviews/Renault/KWID",
                "https://www.zigwheels.com/user-reviews/Renault/Triber",
                "https://www.zigwheels.com/user-reviews/Renault/Duster",
                "https://www.zigwheels.com/user-reviews/Renault/Captur",
                "https://www.zigwheels.com/user-reviews/Renault/Kaptur",
                "https://www.zigwheels.com/user-reviews/Renault/Duster-2016-2019",
                "https://www.zigwheels.com/user-reviews/Renault/KWID-2015-2019",
                "https://www.zigwheels.com/user-reviews/Renault/Lodgy",
                "https://www.zigwheels.com/user-reviews/Renault/Scala",
                "https://www.zigwheels.com/user-reviews/Renault/Pulse",
                "https://www.zigwheels.com/user-reviews/Renault/Fluence",
                "https://www.zigwheels.com/user-reviews/BMW/X1", "https://www.zigwheels.com/user-reviews/BMW/X5",
                "https://www.zigwheels.com/user-reviews/BMW/3-Series", "https://www.zigwheels.com/user-reviews/BMW/X6",
                "https://www.zigwheels.com/user-reviews/BMW/X3", "https://www.zigwheels.com/user-reviews/BMW/X7",
                "https://www.zigwheels.com/user-reviews/BMW/2-Series",
                "https://www.zigwheels.com/user-reviews/BMW/5-Series", "https://www.zigwheels.com/user-reviews/BMW/Z4",
                "https://www.zigwheels.com/user-reviews/BMW/7-Series", "https://www.zigwheels.com/user-reviews/BMW/M5",
                "https://www.zigwheels.com/user-reviews/BMW/6-Series", "https://www.zigwheels.com/user-reviews/BMW/X4",
                "https://www.zigwheels.com/user-reviews/BMW/M2", "https://www.zigwheels.com/user-reviews/BMW/8-Series",
                "https://www.zigwheels.com/user-reviews/BMW/3-Series-GT",
                "https://www.zigwheels.com/user-reviews/BMW/X3-M", "https://www.zigwheels.com/user-reviews/BMW/X5-M",
                "https://www.zigwheels.com/user-reviews/BMW/X1-2015-2020",
                "https://www.zigwheels.com/user-reviews/BMW/3-Series-2015-2019",
                "https://www.zigwheels.com/user-reviews/BMW/X6-2014-2019",
                "https://www.zigwheels.com/user-reviews/BMW/7-Series-2015-2019",
                "https://www.zigwheels.com/user-reviews/BMW/M-Series",
                "https://www.zigwheels.com/user-reviews/BMW/1-Series", "https://www.zigwheels.com/user-reviews/Audi/A4",
                "https://www.zigwheels.com/user-reviews/Audi/A6", "https://www.zigwheels.com/user-reviews/Audi/Q2",
                "https://www.zigwheels.com/user-reviews/Audi/Q8", "https://www.zigwheels.com/user-reviews/Audi/A8",
                "https://www.zigwheels.com/user-reviews/Audi/RS7", "https://www.zigwheels.com/user-reviews/Audi/R8",
                "https://www.zigwheels.com/user-reviews/Audi/GTI", "https://www.zigwheels.com/user-reviews/Audi/RS-Q8",
                "https://www.zigwheels.com/user-reviews/Audi/A2", "https://www.zigwheels.com/user-reviews/Audi/S5",
                "https://www.zigwheels.com/user-reviews/Audi/A4-2015-2020",
                "https://www.zigwheels.com/user-reviews/Audi/A5", "https://www.zigwheels.com/user-reviews/Audi/Q3",
                "https://www.zigwheels.com/user-reviews/Audi/RS6-Avant",
                "https://www.zigwheels.com/user-reviews/Audi/Q7",
                "https://www.zigwheels.com/user-reviews/Audi/RS7-2015-2019",
                "https://www.zigwheels.com/user-reviews/Audi/TT",
                "https://www.zigwheels.com/user-reviews/Audi/A3-cabriolet",
                "https://www.zigwheels.com/user-reviews/Audi/A3", "https://www.zigwheels.com/user-reviews/Audi/S6",
                "https://www.zigwheels.com/user-reviews/Audi/RS5",
                "https://www.zigwheels.com/user-reviews/Audi/A8-2014-2019",
                "https://www.zigwheels.com/user-reviews/Audi/Q5",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/A-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/E-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLS",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLE",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLC",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/V-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/G-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/AMG-GLE-53",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/EQC",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/CLS",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/S-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/AMG-GT",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/E-Class-All-Terrain",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/C-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/AMG-GT-4-Door-Coupe",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLC-Coupe",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/Viano",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/MB-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GL-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/G",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/CL-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLA-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/S-Class-Cabriolet",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/SLC",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLS-2016-2020",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLC-2016-2019",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLE-2015-2020",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/B-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/GLA-45-AMG",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/CLA",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/AMG-GL",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/M-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/SLS-AMG",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/R-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/SLK",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/SL-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/M-Class",
                "https://www.zigwheels.com/user-reviews/Mercedes-Benz/CLK-Class",
                "https://www.zigwheels.com/user-reviews/Skoda/New-Rapid",
                "https://www.zigwheels.com/user-reviews/Skoda/New-Superb",
                "https://www.zigwheels.com/user-reviews/Skoda/Karoq",
                "https://www.zigwheels.com/user-reviews/Skoda/Octavia",
                "https://www.zigwheels.com/user-reviews/Skoda/Fabia",
                "https://www.zigwheels.com/user-reviews/Skoda/Laura",
                "https://www.zigwheels.com/user-reviews/Skoda/Kodiaq",
                "https://www.zigwheels.com/user-reviews/Skoda/Superb-2016-2020",
                "https://www.zigwheels.com/user-reviews/Skoda/Octavia-Combi",
                "https://www.zigwheels.com/user-reviews/Skoda/Yeti",
                "https://www.zigwheels.com/user-reviews/Datsun/redi-GO",
                "https://www.zigwheels.com/user-reviews/Datsun/GO",
                "https://www.zigwheels.com/user-reviews/Datsun/GO-Plus",
                "https://www.zigwheels.com/user-reviews/Datsun/redi-GO-2016-2020",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/Pajero-Sport",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/FTO",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/Challenger",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/Outlander",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/EVO-XI",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/Lancer-Evolution-X",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/Montero",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/Lancer",
                "https://www.zigwheels.com/user-reviews/Mitsubishi/Cedia",
                "https://www.zigwheels.com/user-reviews/Volvo/XC90",
                "https://www.zigwheels.com/user-reviews/Volvo/XC40", "https://www.zigwheels.com/user-reviews/Volvo/S90",
                "https://www.zigwheels.com/user-reviews/Volvo/XC60",
                "https://www.zigwheels.com/user-reviews/Volvo/V90-Cross-Country",
                "https://www.zigwheels.com/user-reviews/Volvo/S40",
                "https://www.zigwheels.com/user-reviews/Volvo/S60-Cross-Country",
                "https://www.zigwheels.com/user-reviews/Volvo/V40", "https://www.zigwheels.com/user-reviews/Volvo/S60",
                "https://www.zigwheels.com/user-reviews/Volvo/V40-Cross-Country",
                "https://www.zigwheels.com/user-reviews/Volvo/S80",
                "https://www.zigwheels.com/user-reviews/Nissan/Magnite",
                "https://www.zigwheels.com/user-reviews/Nissan/Kicks",
                "https://www.zigwheels.com/user-reviews/Nissan/GT-R",
                "https://www.zigwheels.com/user-reviews/Nissan/Urvan",
                "https://www.zigwheels.com/user-reviews/Nissan/Primera",
                "https://www.zigwheels.com/user-reviews/Nissan/Jonga",
                "https://www.zigwheels.com/user-reviews/Nissan/Gloria",
                "https://www.zigwheels.com/user-reviews/Nissan/350Z",
                "https://www.zigwheels.com/user-reviews/Nissan/Micra-Active",
                "https://www.zigwheels.com/user-reviews/Nissan/Terrano",
                "https://www.zigwheels.com/user-reviews/Nissan/Evalia",
                "https://www.zigwheels.com/user-reviews/Nissan/Sunny",
                "https://www.zigwheels.com/user-reviews/Nissan/370Z",
                "https://www.zigwheels.com/user-reviews/Nissan/Micra",
                "https://www.zigwheels.com/user-reviews/Nissan/Teana",
                "https://www.zigwheels.com/user-reviews/Jaguar/F-PACE",
                "https://www.zigwheels.com/user-reviews/Jaguar/XE",
                "https://www.zigwheels.com/user-reviews/Jaguar/F-TYPE",
                "https://www.zigwheels.com/user-reviews/Jaguar/XF", "https://www.zigwheels.com/user-reviews/Jaguar/XJ",
                "https://www.zigwheels.com/user-reviews/Jaguar/XE-2016-2019",
                "https://www.zigwheels.com/user-reviews/Jaguar/F-TYPE-2013-2020",
                "https://www.zigwheels.com/user-reviews/Jaguar/XK",
                "https://www.zigwheels.com/user-reviews/Lamborghini/Urus",
                "https://www.zigwheels.com/user-reviews/Lamborghini/Aventador",
                "https://www.zigwheels.com/user-reviews/Lamborghini/Huracan-EVO",
                "https://www.zigwheels.com/user-reviews/Lamborghini/Huracan",
                "https://www.zigwheels.com/user-reviews/Lamborghini/Murcielago",
                "https://www.zigwheels.com/user-reviews/Lamborghini/Gallardo",
                "https://www.zigwheels.com/user-reviews/Rolls-Royce/Phantom",
                "https://www.zigwheels.com/user-reviews/Rolls-Royce/Ghost",
                "https://www.zigwheels.com/user-reviews/Rolls-Royce/Wraith",
                "https://www.zigwheels.com/user-reviews/Rolls-Royce/Cullinan",
                "https://www.zigwheels.com/user-reviews/Rolls-Royce/Dawn",
                "https://www.zigwheels.com/user-reviews/Rolls-Royce/Drophead",
                "https://www.zigwheels.com/user-reviews/Rolls-Royce/Ghost-2009-2020",
                "https://www.zigwheels.com/user-reviews/MINI/Countryman",
                "https://www.zigwheels.com/user-reviews/MINI/Cooper-Convertible",
                "https://www.zigwheels.com/user-reviews/MINI/Cooper-3-DOOR",
                "https://www.zigwheels.com/user-reviews/MINI/Clubman",
                "https://www.zigwheels.com/user-reviews/MINI/Cooper-5-DOOR",
                "https://www.zigwheels.com/user-reviews/MINI/Cooper",
                "https://www.zigwheels.com/user-reviews/Bugatti/Chiron",
                "https://www.zigwheels.com/user-reviews/Bugatti/Divo",
                "https://www.zigwheels.com/user-reviews/Bugatti/Veyron",
                "https://www.zigwheels.com/user-reviews/Porsche/Macan",
                "https://www.zigwheels.com/user-reviews/Porsche/911",
                "https://www.zigwheels.com/user-reviews/Porsche/Panamera",
                "https://www.zigwheels.com/user-reviews/Porsche/Cayenne",
                "https://www.zigwheels.com/user-reviews/Porsche/718",
                "https://www.zigwheels.com/user-reviews/Porsche/Cayenne-Coupe",
                "https://www.zigwheels.com/user-reviews/Porsche/Carrera-GT",
                "https://www.zigwheels.com/user-reviews/Porsche/Macan-2013-2019",
                "https://www.zigwheels.com/user-reviews/Porsche/Cayman",
                "https://www.zigwheels.com/user-reviews/Porsche/Boxster",
                "https://www.zigwheels.com/user-reviews/Porsche/911-2016-2019",
                "https://www.zigwheels.com/user-reviews/Ferrari/Portofino",
                "https://www.zigwheels.com/user-reviews/Ferrari/Roma",
                "https://www.zigwheels.com/user-reviews/Ferrari/SF90-Stradale",
                "https://www.zigwheels.com/user-reviews/Ferrari/GTC4Lusso",
                "https://www.zigwheels.com/user-reviews/Ferrari/812",
                "https://www.zigwheels.com/user-reviews/Ferrari/F8-Tributo",
                "https://www.zigwheels.com/user-reviews/Ferrari/812-Superfast",
                "https://www.zigwheels.com/user-reviews/Ferrari/F620-GT",
                "https://www.zigwheels.com/user-reviews/Ferrari/F430",
                "https://www.zigwheels.com/user-reviews/Ferrari/Enzo",
                "https://www.zigwheels.com/user-reviews/Ferrari/612",
                "https://www.zigwheels.com/user-reviews/Ferrari/575-Superamerica",
                "https://www.zigwheels.com/user-reviews/Ferrari/458-Spider",
                "https://www.zigwheels.com/user-reviews/Ferrari/458-Speciale",
                "https://www.zigwheels.com/user-reviews/Ferrari/458",
                "https://www.zigwheels.com/user-reviews/Ferrari/488",
                "https://www.zigwheels.com/user-reviews/Ferrari/California-T",
                "https://www.zigwheels.com/user-reviews/Ferrari/FF",
                "https://www.zigwheels.com/user-reviews/Ferrari/599-GTB-Fiorano",
                "https://www.zigwheels.com/user-reviews/Ferrari/458-Italia",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Range-Rover",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Range-Rover-Velar",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Defender",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Range-Rover-Evoque",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Discovery",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Range-Rover-Sport",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Discovery-Sport",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Range-Rover-2010-2012",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Discovery-3",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Range-Rover-Evoque-2016-2020",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Discovery-Sport-2015-2020",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Freelander-2",
                "https://www.zigwheels.com/user-reviews/Land-Rover/Discovery-4",
                "https://www.zigwheels.com/user-reviews/Maserati/Levante",
                "https://www.zigwheels.com/user-reviews/Maserati/Quattroporte",
                "https://www.zigwheels.com/user-reviews/Maserati/Ghibli",
                "https://www.zigwheels.com/user-reviews/Maserati/GranTurismo",
                "https://www.zigwheels.com/user-reviews/Maserati/GranCabrio",
                "https://www.zigwheels.com/user-reviews/Bentley/Continental",
                "https://www.zigwheels.com/user-reviews/Bentley/Mulsanne",
                "https://www.zigwheels.com/user-reviews/Bentley/Flying-Spur",
                "https://www.zigwheels.com/user-reviews/Bentley/Bentayga",
                "https://www.zigwheels.com/user-reviews/Bentley/Brookland",
                "https://www.zigwheels.com/user-reviews/Bentley/Azure",
                "https://www.zigwheels.com/user-reviews/Bentley/Arnage",
                "https://www.zigwheels.com/user-reviews/ISUZU/MUX",
                "https://www.zigwheels.com/user-reviews/ISUZU/D-Max-V-Cross",
                "https://www.zigwheels.com/user-reviews/ISUZU/D-Max",
                "https://www.zigwheels.com/user-reviews/ISUZU/D-MAX-V-Cross-2015-2019",
                "https://www.zigwheels.com/user-reviews/ISUZU/MU-7",
                "https://www.zigwheels.com/user-reviews/Force-Motors/Trax-Cruiser",
                "https://www.zigwheels.com/user-reviews/Force-Motors/Gurkha",
                "https://www.zigwheels.com/user-reviews/Force-Motors/One",
                "https://www.zigwheels.com/user-reviews/DC/Avanti",
                "https://www.zigwheels.com/user-reviews/Jeep/Compass",
                "https://www.zigwheels.com/user-reviews/Jeep/Wrangler",
                "https://www.zigwheels.com/user-reviews/Jeep/Compass-Trailhawk",
                "https://www.zigwheels.com/user-reviews/Jeep/Wrangler-2016-2019",
                "https://www.zigwheels.com/user-reviews/Jeep/Grand-Cherokee",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/Vantage",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/DB11",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/Virage",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/One-77",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/Vantage-2011-2019",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/Vanquish",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/DB11-2016-2020",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/Rapide",
                "https://www.zigwheels.com/user-reviews/Aston-Martin/DB9",
                "https://www.zigwheels.com/user-reviews/Lexus/LS", "https://www.zigwheels.com/user-reviews/Lexus/ES",
                "https://www.zigwheels.com/user-reviews/Lexus/NX", "https://www.zigwheels.com/user-reviews/Lexus/LX",
                "https://www.zigwheels.com/user-reviews/Lexus/RX",
                "https://www.zigwheels.com/user-reviews/Lexus/LC-500h" };
        for (int k = 0; k < url.length; k++) {

            var review_id = "";

            var ratString_id = "";
            var date_id = "";
            var user_id = "";
            var company = "";
            var model = "";
            int reviewsNumber = 0;
            int breakPoint = 0;
            String[] review_ar = {};
            String[] usage_ar = {};
            String[] date_ar = {};
            String[] user_ar = {};
            String[] rating_ar = {};
            driver.get(url[k]);
            Thread.sleep(8000);
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

            System.out.println(review_ar.length);
            System.out.println(usage_ar.length);
            System.out.println(date_ar.length);
            System.out.println(rating_ar.length);
            System.out.println(user_ar.length);
            ResultGenerator.addDataToCSV(company, model, review_ar, user_ar, date_ar, usage_ar, rating_ar);

        }
        driver.quit();
    }

}