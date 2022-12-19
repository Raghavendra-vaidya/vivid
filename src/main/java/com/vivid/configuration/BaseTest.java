package com.vivid.configuration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
  //initiate mobile driver instance here

    public  ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public void setDriverInstance(AppiumDriver driver){
        this.driver.set(driver);
    }

    public AppiumDriver getDriverInstance(){
        return this.driver.get();
    }

    @BeforeMethod
    @Parameters({"deviceName","platformVersion","systemPort"})
    public void initiateMobileDriver(String deviceName, String platformVersion,String systemPort ){
        File root = new File(System.getProperty("user.dir"));
        File app = new File(root, "src/main/resources/Rappv1.apk");

        DesiredCapabilities desiredCapabilities =  new DesiredCapabilities();
        desiredCapabilities.setCapability("avd", deviceName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName","uiAutomator2");
        desiredCapabilities.setCapability("systemPort",Integer.parseInt(systemPort));
        desiredCapabilities.setCapability("app", app.getAbsolutePath());
        desiredCapabilities.setCapability("appPackage", "com.rapp");
        desiredCapabilities.setCapability("appActivity", "com.rapp.MainActivity");

        try
        {
            setDriverInstance(new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities));
        }
        catch (MalformedURLException e) {throw new RuntimeException(e);}
    }

    @AfterMethod
    public void closeDriver(){
        getDriverInstance().quit();
    }


    public void startAppiumService(String portNumber){
        AppiumDriverLocalService service;
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("0.0.0.0");
        builder.usingPort(Integer.parseInt(portNumber));
        builder.withTimeout(Duration.ofSeconds(5));
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("Appium service started");
    }

}
