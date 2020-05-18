package com.testerfabrik.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    By txtFirstName = By.name("firstName");
    By listCountry = By.name("country");
    By txtUserName = By.id("email");
    By txtPass = By.name("password");
    By txtConfirmPass = By.name("confirmPassword");
    By btnSummit = By.xpath("//input[@type ='image' and @name='register']");

    public RegisterPage (WebDriver driver){
        this.driver = driver;
    }

    public void setTxtFirstName (String strFirstName){
       //Se llama el objeto y se le envia el texto
        driver.findElement(txtFirstName).sendKeys(strFirstName);
    }

    public void selectCountry (String strCountry){
        new Select(driver.findElement(listCountry)).selectByVisibleText(strCountry);
    }

    public void setTxtUserName (String strUserName){
        driver.findElement(txtUserName).sendKeys(strUserName);
    }

    public void setTxtPass (String strPass){
        driver.findElement(txtPass).sendKeys(strPass);
    }

    public void setTxtConfirmPass (String strConfirm){
        driver.findElement(txtConfirmPass).sendKeys(strConfirm);
    }

    public void clickOnSubmitButton(){
        driver.findElement(btnSummit).click();
    }

   public void submitUserInformation (String strUser, String strPassword){
        //Llamo el metodo que se encarga de ingresar el userName
        this.setTxtUserName(strUser);
        this.setTxtPass(strPassword);
        this.setTxtConfirmPass(strPassword);
        this.clickOnSubmitButton();
    }
}
