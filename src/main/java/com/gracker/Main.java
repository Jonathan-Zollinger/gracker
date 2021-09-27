package com.gracker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        //TODO download geckodriver and assign system property
        driver.get("https://ensign.edu");
    }
}
