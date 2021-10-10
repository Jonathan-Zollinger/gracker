package com.gracker.model.store;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.*;


public class Store {
    /* --------------------------------------------------- Fields -------------------------------------------------- */
    private String homeURL;
    private final WebDriver driver;
    private final Logger log = LogManager.getLogger(this.getClass());
    /* ------------------------------------------------ Constructors ----------------------------------------------- */

    /**
     * Default Constructor with void fields
     */
    Store() {
        System.setProperty("webdriver.gecko.driver","pathtofile");
        driver = new FirefoxDriver();
    }

    /**
     * Constructor which assigns all available fields.
     *
     * @param homeURL current value for the homeURL field.
     */
    public Store(String homeURL) {
        driver = new FirefoxDriver();
        this.homeURL = homeURL;
    }
    /* -------------------------------------------------- Getters -------------------------------------------------- */

    /**
     * Returns HashMap which contains each field's current value within this class
     *
     * @return Hashmap whose key is a String and value is the field in question.
     */
    public HashMap<String, Object> getProperties() {
        HashMap<String,Object> properties = new HashMap<>();
        Field[] fields = this.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                properties.put(field.getName(),field.get(this));
            } catch (IllegalAccessException illegalAccessException) {
                log.warn(String.format("failed to add the %s field to the properties map.",field.getName()));
            }});
        return properties;
    }//end getProperties()

    /**
     * Returns the current value for the homeURL. The default constructor defaults this string to "zollylobby.com".
     *
     * @return current value for the homeURL variable.
     */
    public String getHomeURL() {
        return homeURL;
    }

    /* -------------------------------------------------- Setters -------------------------------------------------- */

    /**
     * Sets the provided String as the Store object's homeURL field.
     *
     * @param homeURL value to be assigned to the homeURL field.
     */
    public void setHomeURL(String homeURL) {
        this.homeURL = homeURL;
    }

    /* -------------------------------------------------- Methods -------------------------------------------------- */

    public String toString() {
        HashMap<String, Object> properties = this.getProperties();
        StringBuilder stringBuilder = new StringBuilder();
        properties.keySet().forEach(property ->
                stringBuilder.append(String.format("\n%20s:%s", property, properties.get(property))));
        return stringBuilder.toString();
    }

    /**
     * Clicks a web element (technically a By) after waiting for the element to be clickable
     *
     * @param by By object used to identify the Element to be clicked.
     */
    public void Click(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        log.info(String.format("waiting for the following to be clickable:\n%30s : %s",
                "By", by));
        wait.until(webDriver -> driver.findElement(by));
        driver.findElement(by).click();
        log.info(String.format("Successfully clicked the following:\n%30s : %s",
                "By", by));
    }//end Click(By)

    /**
     * A more verbose alternative to the Click(By) method call. Uses the Selector class in order to provide more
     * information in log messages.
     *
     * @param selector Selecotr object used to identify the Element to be clicked.
     */
    public void Click(Selector selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        log.info(String.format("waiting for the following to be clickable:%s", selector.toString()));
        wait.until(webDriver -> driver.findElement(selector.getBy()));
        driver.findElement(selector.getBy()).click();
        log.info(String.format("Successfully clicked the following:%s", selector));
    }//end Click(Selector)


    public void headerSetUp() {
        //fields
        List<Object> rulesOnDisk = new ArrayList<>();
        File fiddlerCustomRulesFile =
                new File("C:\\Users\\jonat\\OneDrive\\Documents\\Fiddler2\\Scripts\\CustomRules.js");
        Scanner existingRules = null;
        try {
            existingRules = new Scanner(fiddlerCustomRulesFile);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        while (true) {
            assert existingRules != null;
            if (!existingRules.hasNextLine()) break;
            rulesOnDisk.add(existingRules.nextLine());
        }
        existingRules.close();
        PrintStream newRules = null;
        try {
            newRules = new PrintStream(fiddlerCustomRulesFile);
        } catch (FileNotFoundException fileNotFoundException) {

            fileNotFoundException.printStackTrace();
        }


        for (Object obj : rulesOnDisk) {
            if (obj.toString().matches(".*static.*function.*OnBeforeRequest.*")) {
                String headerName = String.format("oSession.oRequest[\"headerName\"] = \"%s\";", "headerValue");
                assert newRules != null;
                newRules.println(headerName);
            }
            assert newRules != null;
            newRules.println(obj);
        }
        assert newRules != null;
        newRules.close();
    }//end headerSetUp()

    private static class Selector {
        /* --------------------------------------------------- Fields ---------------------------------------------- */
        private By by;
        private String displayName;
        /* ------------------------------------------------ Constructors ------------------------------------------- */

        public Selector() {
        }

        public Selector(By by, String displayName) {
            this.by = by;
            this.displayName = displayName;
        }
        /* -------------------------------------------------- Getters ---------------------------------------------- */

        /**
         * Gets current value assigned to the by field.
         *
         * @return current value for the by field.
         */
        public By getBy() {
            return by;
        }//end getBy()

        /**
         * Gets current value assigned to the displayName field.
         *
         * @return current value for the displayName field.
         */
        public String getDisplayName() {
            return displayName;
        }//end getDisplayName()


//        @Override
//        public String toString() {
//            HashMap<String, Object> properties = this.getProperties();
//            StringBuilder stringBuilder = new StringBuilder();
//            properties.keySet().forEach(property -> {
//                stringBuilder.append(String.format("\n%20s:%s", property, properties.get(property)));
//            });
//            return stringBuilder.toString();
//        }

        /* -------------------------------------------------- Setters ---------------------------------------------- */

        /**
         * Assigns given value to the by field.
         *
         * @param by value to be assigned to the #field.name field.
         */
        public void setBy(By by) {
            this.by = by;
        }//end setBy()

        /**
         * Assigns given value to the displayName field.
         *
         * @param displayName value to be assigned to the #field.name field.
         */
        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }//end setDisplayName()
    }// end Selector class
}
