package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.RegisterPage_Constants;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegisterPage extends RegisterPage_Constants {

    public RegisterPage (WebDriver driver)  {
        super(driver);
    }
    PropertiesFile prop = new PropertiesFile(driver);

    public void setName (){
       setObjectBy(NAME,"GÖKHAN");
    }

}
