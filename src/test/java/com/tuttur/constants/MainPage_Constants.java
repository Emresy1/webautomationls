package com.tuttur.constants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tuttur.util.BasePageUtil;

public class MainPage_Constants extends BasePageUtil{

	public MainPage_Constants(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public String loginBlankErrorText = "Giriş alanlarını boş bırakmayınız.";
	public String loginFalseErrorText = "Hatalı giriş yaptınız. Lütfen bilgilerinizi kontrol edin.";
	public String loginWarningText = "En çok kazandıran kullanıcılarımızın Popüler Kuponlarına ulaşmak için hemen";
	public String kingOfBetText = "Bahsin Kralları'nın oynanabilir kuponlarına ulaşmak için hemen giriş yapın!";
	public String winnerText = "Kazandıranlar'ın oynanabilir kuponlarına ulaşmak için hemen giriş yapın!";
	public String socialErrorText = "Hatalı giriş yaptınız. Lütfen bilgilerinizi kontrol edin.";
	public String registerModalText = "Hemen Üye Ol";
	public String nameErrorText = "Adınız giriniz.";
	public String surnameErrorText = "Soyadınızı giriniz.";
	public String emailErrorText = "Geçerli bir e-posta adresi giriniz.";
	public String usernameErrorText = "Kullanıcı adı giriniz.";
	public String passwordErrorText = "En az 6 karakter uzunluğunda bir şifre giriniz. Güvenliğiniz için kişisel bilgilerinizi içeren şifreler kullanmayınız.";
	public String currentlyEmailText = "E-posta adresi kullanımda. Lütfen e-posta adresini kontrol ederek yeniden deneyiniz.";
	public String regexUsernameText = "Kullanıcı adı 6-15 karakter uzunluğunda olmalı. Küçük harf, sayı, - ve _ karakterlerinden oluşabilir. Türkçe karakter kullanmayınız.";
	public String currentlyUsername = "Kullanıcı adı kullanımda. Başka bir kullanıcı adı deneyiniz.";
	public String falsePassword = "En az 6 karakter uzunluğunda bir şifre giriniz. Güvenliğiniz için kişisel bilgilerinizi içeren şifreler kullanmayınız.";
	public String dateErrorText = "Seçtiğiniz tarih geçerli değil.";
	public String birthdayErrorText = "Kayıt olabilmen için yasalar gereği 18 yaşından büyük olmalısın";
	public String invalidDateErrorText = "Seçtiğiniz tarih geçerli değil.";
	public String gsmErrorText = "Eksik rakam girdiniz. Kontrol edip, tekrar deneyiniz.";
	public String gsmPlaceHolder = "5XX XXX XX XX";
	public String ssnErrorText = "T.C. kimlik numaranızı giriniz.";
	public String acceptedText = "Açık Rıza Onay Metni’ni onaylamalısınız.";
	public String securityCodeText = "Güvenlik kodu cep telefonuna SMS olarak tekrar gönderildi.";
	public String welcomePageText = "Üyelik işlemin başarılı bir şekilde tamamlandı.";
		
	
	


	public By USERNAME = By.id("username");
	public By PASSWORD = By.id("password");
	public By BUTTON_LOGIN = By.cssSelector(".button.button-secondary.mr-2.login");
	public By BUTTON_LOGIN_ON_POPUP = By.cssSelector(".tButton.large.blue.submit");
	public By ACCOUNT_NO = By.className("memberNumber");







	
	

	

}
