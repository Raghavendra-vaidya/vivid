package com.vivid.configuration;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestClass1 extends BaseTest{


    @Test
    public void firsttest(){

        System.out.println("calculator opened");
        Assert.assertTrue(true);
    }
}
