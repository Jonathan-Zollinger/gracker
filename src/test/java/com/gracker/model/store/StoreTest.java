package com.gracker.model.store;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

class StoreTest {
    String schoolLibraryURL = "https://library.ensign.edu/";
    Store defaultConstructorStore;
    Store constructorStoreWithURL;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        defaultConstructorStore = new Store();
        constructorStoreWithURL = new Store(schoolLibraryURL);
    }

    @org.junit.jupiter.api.Test
    void getHomeURL() {
        assert(Objects.equals(schoolLibraryURL, constructorStoreWithURL.getHomeURL()));
        assert(null == defaultConstructorStore.getHomeURL());
    }

    @Test
    void setHomeURL() {
        //tests constructors
        String shortestEnsignSiteReference = "ensign.edu";
        String shorterEnsignSiteReference = "www.ensign.edu";
        constructorStoreWithURL.setHomeURL(shortestEnsignSiteReference);
        defaultConstructorStore.setHomeURL(shorterEnsignSiteReference);
        assert(Objects.equals(shortestEnsignSiteReference, constructorStoreWithURL.getHomeURL()));
        assert(Objects.equals(shorterEnsignSiteReference, defaultConstructorStore.getHomeURL()));
    }

    @org.junit.jupiter.api.Test
    void click() {
        constructorStoreWithURL.Click(By.linkText("FAQs"));
        assert((schoolLibraryURL + "faq").equals(
                ((WebDriver) constructorStoreWithURL.getProperties().get("driver")).getCurrentUrl()));
        defaultConstructorStore.setHomeURL("https://www.ensign.edu/");
        defaultConstructorStore.Click(By.linkText("Degrees & Certificates"));
        assert(("https://www.ensign.edu/degrees-certificates").equals(
                ((WebDriver) defaultConstructorStore.getProperties().get("driver")).getCurrentUrl()));
    }

    @AfterEach
    void tearDown() {
        constructorStoreWithURL.closeBrowser();
        defaultConstructorStore.closeBrowser();
    }
}