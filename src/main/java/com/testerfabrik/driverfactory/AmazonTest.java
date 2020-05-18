package com.testerfabrik.driverfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest {
    WebDriver driver;
    WebDriverWait waitElement;

    @BeforeTest
    public void setUpTest (){
        DriverFactory.getInstance().setDriver(browserType.CHROME);
        //Para manipular la variable driver todas las veces que se necesite
        driver = DriverFactory.getInstance().getDriver();
        driver.get("https://www.amazon.com");
        waitElement = new WebDriverWait(driver, 15); //15 seg antes de cualquier exepción
    }

    @AfterTest
    public void tearDown(){
        DriverFactory.getInstance().removeDriver();
    }

    @Test (priority = 0)
    public void searchPlasticCaseIpadAir(){
        WebElement txtSearch = driver.findElement(By.id("twotabsearchtextbox"));
        //Voy a esperar hasta que el objeto aparezca
        waitElement.until(ExpectedConditions.visibilityOf(txtSearch));
        txtSearch.sendKeys("ipad air 2 case");
        //Hago clic en el objeto de busqueda (la lupita del campo buscar)
        driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
        //Busco objeto para seleccionar plastic(checkbox) necesito hacer Scroll
        WebElement chkPlastic = driver.findElement(By.xpath("//*[@id=\"p_n_feature_keywords_four_browse-bin/8080061011\"]/span/a/span"));
        //Uso JavaScritp para hacer el scroll
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", chkPlastic);
        chkPlastic.click();
    }

    @Test (priority = 1)
    public void searchFirtsFiveItems () {
        /*for (int i = 0; i < 5; i++){
            String name = driver.findElement(By.xpath("//*[@id='search" + i + "']/div[1]/div[2]/div/span[4]/div[2]/div[2]/div/span/div/div/div[2]/h2/a/span")).getText();
            String score = driver.findElement(By.xpath("//*[@id='search" + i +"']/div[1]/div[2]/div/span[4]/div[2]/div[3]/div/span/div/div/div[3]/div/span[1]/span/a/i[1]")).getAttribute("innertext");
            System.out.println("Name: " + name + ", Score: " + score);
        }*/
        String name = driver.findElement(By.xpath("//*[@id='search']/div[1]/div[2]/div/span[4]/div[2]/div[2]/div/span/div/div/div[2]/h2/a/span")).getText();
        System.out.println("Name: " + name);
    }
}
