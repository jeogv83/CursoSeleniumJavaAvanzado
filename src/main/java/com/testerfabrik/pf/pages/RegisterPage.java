package com.testerfabrik.pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    @FindBy(name= "firstName")
    WebElement txtFirstName;

    @FindBy(name ="country")
    WebElement listCountry;

    @FindBy(id = "email")
    WebElement txtUserName;

    @FindBy(name = "password")
    WebElement txtPass;

    @FindBy(name = "confirmPassword")
    WebElement txtConfirmPass;

    @FindBy(xpath = "//input[@type ='image' and @name='register']")
    WebElement btnSummit;


    public RegisterPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this); //este this hace referencia a la clase actual es decir donde me encuentro
    }

    public void setTxtFirstName (String strFirstName){
       //Se llama el objeto y se le envia el texto
        txtFirstName.sendKeys(strFirstName);
    }

    public void selectCountry (String strCountry){
        new Select(listCountry).selectByVisibleText(strCountry);
    }

    public void setTxtUserName (String strUserName){

        txtUserName.sendKeys(strUserName);
    }

    public void setTxtPass (String strPass){ txtPass.sendKeys(strPass);  }

    public void setTxtConfirmPass (String strConfirm){ txtConfirmPass.sendKeys(strConfirm); }

    public void clickOnSubmitButton(){ btnSummit.click(); }

   public void submitUserInformation (String strUser, String strPassword){
        //Llamo el metodo que se encarga de ingresar el userName
        this.setTxtUserName(strUser);
        this.setTxtPass(strPassword);
        this.setTxtConfirmPass(strPassword);
        this.clickOnSubmitButton();
    }
}
