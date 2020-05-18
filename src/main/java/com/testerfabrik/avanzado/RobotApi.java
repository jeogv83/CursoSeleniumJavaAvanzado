package com.testerfabrik.avanzado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotApi {

    WebDriver driver;

    @Test
    public void robotAPITest () throws AWTException, InterruptedException {
        //SE abrira navegador Mozilla Firefox
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://notepad-plus-plus.org/downloads/v7.8.6/");

        driver.findElement(By.xpath("//*[@id=\"main\"]/p[5]/a/img")).click();
        Robot robot = new Robot();
        Thread.sleep(2000);
        //Presiona flecha hacia abajo como si fuera a darle guardar como,
        // en el caso de las ventanas que por defecto traen la opción "abrir"
        //robot.keyPress(KeyEvent.VK_DOWN); //en este ejercicio no sale esta ventana esta a manera de ejemplo

        //agrego 2 tab para llegar al botón guardar
        for(int i=0; i<=1; i++){
            robot.keyPress(KeyEvent.VK_TAB);
        }

        Thread.sleep(2000);
        //Presiono Enter
        robot.keyPress(KeyEvent.VK_ENTER);

        Thread.sleep(1000);
        driver.quit();

    }

}
