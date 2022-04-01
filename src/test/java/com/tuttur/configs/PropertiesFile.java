package com.tuttur.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.tuttur.base.web.BaseTest;

public class PropertiesFile {


	static Properties properties = new Properties();
	static String path = System.getProperty("user.dir");
	
	public static void main(String[] args) {

		getProperties();
		setProperties();
		getProperties();

	}
	
	public static void getProperties() {

		try {
			InputStream input = new FileInputStream(path+"/src/test/java/com/tuttur/configs/config.properties");
			properties.load(input);
			String browser = properties.getProperty("browser");
			System.out.println(browser);
			BaseTest.browserName=browser;
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}	
	}

	
   public static void setProperties() {
	   try {
		   OutputStream output = new FileOutputStream(path+"/src/test/java/com/tuttur/configs/config.properties");
		   properties.setProperty("result", "pass");
		   properties.store(output, null);
	    } 
	   
	   catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(e.getCause());
		e.printStackTrace();
	  }	   
    }
   
   public static void loadData() throws IOException {
	   File file = new File(path+"/src/test/java/com/tuttur/configs/config.properties");
	   FileReader object = new FileReader(file);
	   properties.load(object);
   }
   
   public String getObject(String data) throws IOException {
	   loadData();
	   String parameter =properties.getProperty(data);
	   return parameter;
   }

}
