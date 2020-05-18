package com.testerfabrik.avanzado;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogsTest {

    WebDriver driver;
    String baseURL = "https://healthunify.com/bmicalculator/";
    Logger log = Logger.getLogger(LogsTest.class);

    @BeforeClass
    public void initializeComponent (){
        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\resources\\log.properties");
    }

    @Test
    public void launchBrowser () {
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(baseURL);
            log.info("Openig webedith: " + baseURL); //agrego mi log

        } catch (WebDriverException we) {
            log.error("WebDriver fallo: " + we.getMessage());

        }catch (Exception e){
            log.fatal(e.getMessage());
        }
    }

    //Una vez que se haya ejecutado satisfactoriamente el metodo BMICalculator se ejecutara este "tearDown"
    @Test(dependsOnMethods = { "BMICalculator" })
    public void tearDown () {
        driver.quit();
        log.info("Se cerro el navegador");
    }

    @Test (dependsOnMethods = { "launchBrowser" })
    public void BMICalculator () {
        try {
            log.info("Escribiendo el peso");
            driver.findElement(By.name("wg")).sendKeys("87");

            log.info("Seleccionando los kilogramos");
            new Select(driver.findElement(By.name("opt1"))).selectByVisibleText("kilograms");

            log.info("Se indica la altura en pies");
            driver.findElement(By.name("opt2")).sendKeys("5"); //sendKeys hace lo mismo que select

            log.info("Se indican las pulgadas");
            driver.findElement(By.name("opt3")).sendKeys("10");

            log.info("Se hace clic en el btón calculate");
            driver.findElement(By.name("cc")).click();

            //Como son inputBOx en la página no se pude usar getText por eso se usa  getAttribute
            String SIUnit = driver.findElement(By.name("si")).getAttribute("value");
            String USUnit = driver.findElement(By.name("us")).getAttribute("value");
            String UKUnit = driver.findElement(By.name("uk")).getAttribute("value");
            String note = driver.findElement(By.name("desc")).getAttribute("value");

            log.info("SI unit " + SIUnit);
            log.info("US unit " + USUnit);
            log.info("Uk unit " + UKUnit);
            log.info("Descripcion" + note);


        }catch (NoSuchElementException ne){
            log.error("WebElement no encontrado: " + ne.getMessage());
        }catch (WebDriverException we){
            log.error("WebDriver fallo: " + we.getAdditionalInformation());
        }catch (Exception e){
            log.fatal(e.getMessage());
        }

    }




}
