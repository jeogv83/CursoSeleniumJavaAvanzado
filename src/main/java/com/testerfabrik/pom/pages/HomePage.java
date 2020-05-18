package com.testerfabrik.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    //Luego se agregaran más objetos en la clase register
    By lnkRegister = By.linkText("REGISTER");

    //Costructor con el se llama la clase (HomePage) y se identifican todos los objetos
    // que se encuentran en ella
    public HomePage (WebDriver driver){
        this.driver = driver;
    }

    //Metodo para hacer clic en Register
    public void clickRegisterLink (){
        //Para hacer clic a un objeto que tiene un link llamado register
        driver.findElement(lnkRegister).click();
    }

    //Objeto de tipo string para obtener el titulo de la pagina y me devuelve un string(con el titulo)
    public String getHomePageTitle (){
        return driver.getTitle();
    }
}
