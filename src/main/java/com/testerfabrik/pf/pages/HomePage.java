package com.testerfabrik.pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    //Luego se agregaran más objetos en la clase register
    //Para usar Page Factory sustituyo esta linea By lnkRegister = By.linkText("REGISTER") por la que esta abajo
    @FindBy(linkText = "REGISTER")
    WebElement lnkRegister;

    //En el Costructor se debe agregar el PageFactory, el metodo initElements, indicarle el driver
    // y la clase que tiene los elementos web(se pone this ya q es esta misma clase)
    public HomePage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Metodo para hacer clic en Register
    public void clickRegisterLink (){
        //Para hacer clic a un objeto que tiene un link llamado register, tampoco usare el driver.findElement(lnkRegister)
        lnkRegister.click();
    }

    //Objeto de tipo string para obtener el titulo de la pagina y me devuelve un string(con el titulo)
    public String getHomePageTitle (){
        return driver.getTitle();
    }
}
