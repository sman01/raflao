import java.lang.*;
import java.util.logging.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.graalvm.compiler.lir.Variable;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import jdk.nashorn.internal.ir.CallNode.EvalArgs;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.ArrayList;
import java.util.List;

public class HelloSelenium {
    private String name;

    public HelloSelenium(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /// Companies with models
    public static String getURL() {

    }

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Add elements to an array
    public static String[] add(String[] arr, String... elements) {
        String[] tempArr = new String[arr.length + elements.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        for (int i = 0; i < elements.length; i++)
            tempArr[arr.length + i] = elements[i];
        return tempArr;

    }

    static public String[] Maruti_Suzuki = { "Swift", "Baleno", "Vitara-Brezza", "Dzire", "Ertiga", "Wagon-R",
            "Alto-800", "Celerio", "S-Presso", "S-Cross", "XL6", "Ignis", "Ciaz", "Eeco", "Celerio-X",
            "Swift-Dzire-Tour", "Baleno-RS", "Baleno-2014-2018", "S-Cross-2017-2020", "Vitara-Brezza-2016-2020",
            "Dzire-2017-2020", "Alto-K10", "Wagon-R-{2001-2018}", "Zen", "Esteem", "800", "Estilo", "Ritz", "A-Star",
            "SX4", "Omni", "Gypsy" };
    static public String[] MG_Motor = { "Hector", "Gloster", "ZS-EV", "Hector-Plus", "D90", "Hector-2019-2021" };
    static public String[] Hyundai = { "Creta", "i20", "Venue", "Grand-i10", "Verna", "Aura", "Santro", "Tucson",
            "Elantra", "Kona-Electric", "Grand-i10-Nios", "Xcent-2020", "Creta-2015-2020", "Elantra-2015-2019",
            "Verna-2017-2020", "i20-Active", "Elite-i20-2017-2020", "Tucson-2016-2020", "Xcent", "EON", "Santro-Xing",
            "i10", "Accent" };
    static public String[] Honda = { "City", "Amaze", "Civic", "Jazz", "WR-V", "CR-V", "City-4th-Generation", "Accord",
            "Jazz-2018-2020", "WRV-2017-2020", "Brio", "BRV", "Mobilio", "CR-V-{2009-2018}" };
    static public String[] Toyota = { "Fortuner", "Innova-Crysta", "Glanza", "Yaris", "Vellfire", "Camry",
            "Urban-Cruiser", "Tercel", "Starlet", "Sera", "Premio", "prado", "Majesta", "Estima", "Cynos", "Corona",
            "Fortuner-2016-2021", "Innova-Crysta-2016-2020", "Camry", "Etios-Liva", "Platinum-Etios", "Etios-Cross",
            "Land-Cruiser", "Qualis", "Prius", "Corolla-Altis", "Land-Cruiser-Prado", "Innova" };
    static public String[] Mahindra = { "Thar", "Scorpio", "XUV300", "Bolero", "XUV500", "Marazzo", "TUV-300",
            "KUV100-NXT", "Alturas-G4", "TUV-300-Plus", "E-Verito", "Bolero-Power-Plus", "Bolero-Pik-Up", "Marshal",
            "Jeep", "Supro", "e2oPlus", "NuvoSport", "Armada", "Verito-Vibe", "e2o", "Quanto", "Bolero-2011-2019",
            "Verito", "Xylo", "Logan", "Thar-2015-2019" };
    static public String[] Tata = { "Altroz", "Harrier", "Nexon", "Tiago", "Tigor", "Yodha-Pickup", "Tigor-EV",
            "Nexon-EV", "Tiago-2019-2020", "Tigor-JTP", "Tiago-JTP", "Tiago-NRG", "Hexa-2017-2020", "Nexon-2017-2020",
            "Tigor-2017-2020", "Safari-Storme", "Nano", "Bolt", "Zest", "Indica-eV2", "Sumo", "Indigo-eCS", "Manza",
            "Vista", "Safari-2005-2017", "Indica" };
    static public String[] Kia = { "Sonet", "Seltos", "Carnival" };
    static public String[] Volkswagen = { "Polo", "Vento", "T-Roc", "Tiguan-Allspace", "Polo-GTI", "Multivan",
            "T-Cross", "GTI", "Vento-2015-2019", "Polo-2015-2019", "Tiguan", "Passat", "Ameo", "Jetta", "Touareg",
            "Touareg" };
    static public String[] Ford = { "EcoSport", "Endeavour", "Figo", "Freestyle", "Aspire", "Endeavour-2015-2020",
            "Mustang", "Ecosport-2015-2021", "Fiesta", "Ikon" };
    static public String[] Renault = { "KWID", "Triber", "Duster", "Captur", "Kaptur", "Duster-2016-2019",
            "KWID-2015-2019", "Lodgy", "Scala", "Pulse", "Fluence" };
    static public String[] BMW = { "X1", "X5", "3-Series", "X6", "X3", "X7", "2-Series", "5-Series", "Z4", "7-Series",
            "M5", "6-Series", "X4", "M2", "8-Series", "3-Series-GT", "X3-M", "X5-M", "X1-2015-2020",
            "3-Series-2015-2019", "X6-2014-2019", "7-Series-2015-2019", "M-Series", "1-Series" };
    static public String[] Audi = { "A4", "A6", "Q2", "Q8", "A8", "RS7", "R8", "GTI", "RS-Q8", "A2", "S5",
            "A4-2015-2020", "A5", "Q3", "RS6-Avant", "Q7", "RS7-2015-2019", "TT", "A3-cabriolet", "A3", "S6", "RS5",
            "A8-2014-2019", "Q5" };
    static public String[] Mercedes_Benz = { "A-Class", "E-Class", "GLS", "GLE", "GLC", "V-Class", "G-Class",
            "AMG-GLE-53", "EQC", "CLS", "S-Class", "AMG-GT", "E-Class-All-Terrain", "C-Class", "AMG-GT-4-Door-Coupe",
            "GLC-Coupe", "Viano", "MB-Class", "GL-Class", "G", "CL-Class", "GLA-Class", "S-Class-Cabriolet", "SLC",
            "GLS-2016-2020", "GLC-2016-2019", "GLE-2015-2020", "B-Class", "GLA-45-AMG", "CLA", "AMG-GL", "M-Class",
            "SLS-AMG", "R-Class", "SLK", "SL-Class", "M-Class", "CLK-Class" };
    static public String[] Skoda = { "New-Rapid", "New-Superb", "Karoq", "Octavia", "Fabia", "Laura", "Kodiaq",
            "Superb-2016-2020", "Octavia-Combi", "Yeti" };
    static public String[] Datsun = { "redi-GO", "GO", "GO-Plus", "redi-GO-2016-2020" };
    static public String[] Mitsubishi = { "Pajero-Sport", "FTO", "Challenger", "Outlander", "EVO-XI",
            "Lancer-Evolution-X", "Montero", "Lancer", "Cedia" };
    static public String[] Volvo = { "XC90", "XC40", "S90", "XC60", "V90-Cross-Country", "S40", "S60-Cross-Country",
            "V40", "S60", "V40-Cross-Country", "S80" };
    static public String[] Nissan = { "Magnite", "Kicks", "GT-R", "Urvan", "Primera", "Jonga", "Gloria", "350Z",
            "Micra-Active", "Terrano", "Evalia", "Sunny", "370Z", "Micra", "Teana" };
    static public String[] Jaguar = { "F-PACE", "XE", "F-TYPE", "XF", "XJ", "XE-2016-2019", "F-TYPE-2013-2020", "XK" };
    static public String[] Lamborghini = { "Urus", "Aventador", "Huracan-EVO", "Huracan", "Murcielago", "Gallardo" };
    static public String[] Rolls_Royce = { "Phantom", "Ghost", "Wraith", "Cullinan", "Dawn", "Drophead",
            "Ghost-2009-2020" };
    static public String[] MINI = { "Countryman", "Cooper-Convertible", "Cooper-3-DOOR", "Clubman", "Cooper-5-DOOR",
            "Cooper" };
    static public String[] Bugatti = { "Chiron", "Divo", "Veyron" };
    static public String[] Porsche = { "Macan", "911", "Panamera", "Cayenne", "718", "Cayenne-Coupe", "Carrera-GT",
            "Macan-2013-2019", "Cayman", "Boxster", "911-2016-2019" };
    static public String[] Ferrari = { "Portofino", "Roma", "SF90-Stradale", "GTC4Lusso", "812", "F8-Tributo",
            "812-Superfast", "F620-GT", "F430", "Enzo", "612", "575-Superamerica", "458-Spider", "458-Speciale", "458",
            "488", "California-T", "FF", "599-GTB-Fiorano", "458-Italia" };
    static public String[] Land_Rover = { "Range-Rover", "Range-Rover-Velar", "Defender", "Range-Rover-Evoque",
            "Discovery", "Range-Rover-Sport", "Discovery-Sport", "Range-Rover-2010-2012", "Discovery-3",
            "Range-Rover-Evoque-2016-2020", "Discovery-Sport-2015-2020", "Freelander-2", "Discovery-4" };
    static public String[] Maserati = { "Levante", "Quattroporte", "Ghibli", "GranTurismo", "GranCabrio" };
    static public String[] Bentley = { "Continental", "Mulsanne", "Flying-Spur", "Bentayga", "Brookland", "Azure",
            "Arnage" };
    static public String[] ISUZU = { "MUX", "D-Max-V-Cross", "D-Max", "D-MAX-V-Cross-2015-2019", "MU-7" };
    static public String[] Force_Motors = { "Trax-Cruiser", "Gurkha", "One" };
    static public String[] DC = { "Avanti" };
    static public String[] Jeep = { "Compass", "Wrangler", "Compass-Trailhawk", "Wrangler-2016-2019",
            "Grand-Cherokee" };
    static public String[] Aston_Martin = { "Vantage", "DB11", "Virage", "One-77", "Vantage-2011-2019", "Vanquish",
            "DB11-2016-2020", "Rapide", "DB9" };
    static public String[] Lexus = { "LS", "ES", "NX", "LX", "RX", "LC-500h" };

    static public String[] companies = { "Maruti_Suzuki", "MG_Motor", "Hyundai", "Honda", "Toyota", "Mahindra", "Tata",
            "Kia", "Volkswagen", "Ford", "Renault", "BMW", "Audi", "Mercedes_Benz", "Skoda", "Datsun", "Mitsubishi",
            "Volvo", "Nissan", "Jaguar", "Lamborghini", "Rolls_Royce", "MINI", "Bugatti", "Porsche", "Ferrari",
            "Land_Rover", "Maserati", "Bentley", "ISUZU", "Force_Motors", "DC", "Jeep", "Aston_Martin", "Lexus" };

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

    public void main(String[] args) throws InterruptedException {
        var review_id = "";
        var usage_id = "";
        var subSpace_id = "";
        var superSub_id = "";
        var ratString_id = "";
        var date_id = "";
        var user_id = "";
        int reviewsNumber = 0;
        int breakPoint = 0;
        String[] review_ar = {};
        String[] usage_ar = {};
        String[] date_ar = {};
        String[] user_ar = {};
        String[] rating_ar = {};
        System.setProperty("webdriver.gecko.driver", "/home/sman/Desktop/raflao/sman/geckodriver");
        WebDriver driver = new FirefoxDriver();
        Dimension d = new Dimension(640, 720);
        driver.manage().window().setSize(d);

        for (int k = 0; k < companies.length; k++) {
            String company = companies[k].replace("_", "-");
            for (int l = 0; l < 10; l++) {
                int reviewno = numberOfReviews(driver);
                scrollEnd(driver);
                expandReviews(driver, reviewno);
                System.out.println("Number of reviews: " + reviewno);
                for (int i = 1; i < reviewno; i++) {
                    logger.log(Level.INFO, "---------------------------------------------------------------"
                            + String.valueOf(i) + "---------------------------------------------------------------");
                    review_id = "//div[@id='userReviews']/div[" + String.valueOf(i) + "]/div/div[2]/div/p";

                    ratString_id = "//div[@id='userReviews']/div[" + String.valueOf(i)
                            + "]/div/div[1]/div[1]/span[2]/span";
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
                ResultGenerator.addDataToCSV(review_ar, user_ar, date_ar, usage_ar, rating_ar);
            }
        }
        driver.quit();
    }

}