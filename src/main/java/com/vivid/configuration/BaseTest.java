package com.vivid.configuration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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
    @Parameters({"deviceName","platformVersion","portNumber"})
    public void initiateMobileDriver(String deviceName, String platformVersion,String portNumber ){
        DesiredCapabilities desiredCapabilities =  new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
        desiredCapabilities.setCapability("appActivity", ".Calculator");

        try
        {
            setDriverInstance(new AppiumDriver(new URL("http://0.0.0.0:"+portNumber+"/wd/hub"),desiredCapabilities));
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
