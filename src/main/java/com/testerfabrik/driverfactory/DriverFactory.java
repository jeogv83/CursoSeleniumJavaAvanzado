package com.testerfabrik.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    //Constructor
    private DriverFactory (){

    }

    //Instancia estatica de DriverFactory
    private static DriverFactory instance = new DriverFactory();

    //Creo un metodo para regresar esa instancia
    public static DriverFactory getInstance(){
        return instance;
    }

    //Creo un objeto del tipo ThreadLocal que va a contener mis WebDrivers, este ThreadLocal nos permite asociar
    //objetos a un hilo de ejecución de forma q todos los metodos que se han invocado desde ese hilo tendran
    //acceso a dicho objeto, que en este caso es WebDriver
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(){
        //Sobre escribe un metodo de tipo WebDriver
        //una vez que tenga que cambiar d evalor el browser se sobreescribe dicho valor
        @Override
        protected WebDriver initialValue(){
            return null;
        }
    };

    //Con este metodo se regresa el valor que tenga el driver, ya se Chrome, Firefox o IE
    public WebDriver getDriver(){
        return driver.get();
    }

    //Con este metodo agrego la logica para abrir los navegadores
    public WebDriver setDriver(browserType browser){
        //creo una variable string para preguntar si el sistem operativo es mac o windows
        String osName = System.getProperty("os.name").toLowerCase().contains("mac")?"mac":"windows";
        //creo otra variable
        String driverPath = System.getProperty("user.dir") + "\\drivers\\";
        switch (browser.toString()){
            case "CHROME":
                if(osName.equals("windows")){
                    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                }else{
                    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
                }
                driver.set(new ChromeDriver());
                break;
            case "IE":
                if(osName.equals("windows")){
                    System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
                }
                driver.set(new InternetExplorerDriver());
                break;
            case "FIREFOX":
                if(osName.equals("windows")){
                    System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                }else{
                    System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver");
                }
                driver.set(new FirefoxDriver());
                break;
        }
        //Para que intente maximizar el navegador 10 veces, ya q en ciertas ocaciones no se puede maximizar
        int i = 10;
        for (int j = i; j <= i; i++){
            try {
                driver.get().manage().window().maximize();
                break;

            }catch (WebDriverException we){
                driver.set(new ChromeDriver());
                driver.get().manage().window().maximize();
            }
            if (i == j){
                Assert.fail("Fallo maximisando la vetana " + j + "veces");
            }
        }
        driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver.get();
    }

    //Agrego metodo para remover el driver
    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }

}
