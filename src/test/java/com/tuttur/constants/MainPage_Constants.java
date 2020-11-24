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
	public By ACCOUNT_NO = By.className("playerNumber");
	public By MP_COLLECTIVE_TICKET = By.cssSelector(".topRightMenu.topMenuRightMPOrtakBilet");
	public By LOGIN_ERROR_TEXT = By.className("error");
	public By BUTTON_REGISTER = By.cssSelector(".button.newUser");
	public By BUTTON_ALREADY_LOGIN = By.cssSelector(".btn.btn-text");
	public By SURNAME_INPUT = By.name("lastName");
	public By EMAIL = By.name("email");
	public By USERNAME_INPUT = By.name("username");
	public By PASSWORD_INPUT = By.name("password");
	public By TCKN_INPUT = By.name("ssn");
	public By REGISTER_POLICY_CHECKBOX = By.name("agree1");
	public By PLAYER_NUMBER = By.className("playerNumber");
	public By REGISTER_NAME = By.name("name");
	public By REGISTERED_CHECKBOX = By.cssSelector(".form-control.agree1");
	public By BUTTON_REGISTER_ON_MODAL = By.cssSelector(".btn.btn-primary");

	public By USER_AGREEMENT_CONTENT = By.className("userAgreementContainerText");
	public By REGISTER_POLICY_LINK = By.cssSelector(".openColorbox");
	public By REGISTER_POLICY_SELECTED = By.cssSelector(".custom-checkbox.selected");
	public By ACCEPTED_TEXT_LINK = By.cssSelector(".openColorbox");
	public By ACCEPTED_TEXT__SELECTED = By.cssSelector(".custom-checkbox.selected");




	public By BIRTHDAY_DROPDOWN = By.className("dateSelect");
	public By BUTTON_SUBMIT = By.id("submit");
	public By PHONE_INPUT = By.cssSelector(".form-control.phone");
	public By ACTIVATION_CODE_INPUT = By.name("activationCode");
	public By ACTIVATION_BUTTON_CLOSE = By.id("cboxClose");
	public By FATHER_NAME_INPUT = By.id("fatherNameInput");
	public By MOTHER_NAME_INPUT = By.id("motherNameInput");
	public By IDENTITY_SERIAL_INPUT = By.id("identityCardSerialInput");
	public By IDENTITY_NO_INPUT = By.id("identityCardNumberInput");
	public By ACTIVATION_BUTTON_LOGIN = By.cssSelector(".btn.btn-primary");
	public By BUTTON_SAVE = By.id("submitAdditionalInfo");
	public By WELCOME_MODAL_BUTTON_CLOSE = By.id("cboxClose");
	public By WELCOME_MODAL = By.id("otomatik-takip");
	public By BUTTON_RESEND_CODE = By.id("requestSMSButton");
	public By SMS_TEXT = By.cssSelector(".invalid-feedback.default-color");
	public By CURRENT_BALANCE = By.className("currentBalance");
	public By AVATAR = By.className("avatarHolder");
	public By POPULAR_TAB = By.className("topMenuLeftFirstPopular");
	public By POPULAR_USERNAME = By.id("username");
	public By POPULAR_PASSWORD = By.id("password");
	public By SOCIAL_BETS_TAB = By.cssSelector(".tartTab.latestFeedTabs");
	public By SOCIAL_BETS_TEXT = By.className("loginWarning");
	public By SOCIAL_BETS_LOGIN = By.cssSelector(".tLink.loginWarningLink");
	public By SOCIAL_ERROR_TEXT = By.className("error");
	public By BUTTON_ALREADY_REGISTERED = By.cssSelector(".btn.btn-text");
	public By REGISTER_MODAL_TEXT = By.id("cboxTitleContent");
	public By NAME_INPUT_ERROR = By.id("nameInputError");
	public By SURNAME_INPUT_ERROR = By.id("surnameInputError");
	public By EMAIL_INPUT_ERROR = By.id("emailError");
	public By USERNAME_INPUT_ERROR = By.id("usernameError");
	public By PASSWORD_INPUT_ERROR = By.cssSelector(".passwordInputAreaError.error");
	public By USERNAME_HELP = By.className("usernameHelp");
	public By PASSWORD_TOOGLE = By.className("visibilityToggler");
	public By DATE_ERROR_TEXT = By.cssSelector(".dateSelectError.error");
	public By GSM_ERROR_TEXT = By.id("gsmError");
	public By SSN_ERROR_TEXT = By.id("ssnInputError");
	public By LEARN_SSN = By.cssSelector(".tLink.ssn.ssnV1");
	public By FORGOT_PASS_ON_SSN = By.cssSelector(".blue.forgotPasswordLinkSsnError.forgotPass");
	public By BUTTON_DECLINE = By.id("decline");
	public By BUTTON_ACCEPT = By.id("accept");
	public By BUTTON_REGISTER_ON_ALREADY_REGISTERED = By.cssSelector(".signup.tLink");
	public By CATEGORIES = By.className("hasChilds");
	public By REGISTER_SUBMIT = By.cssSelector(".btn.btn-primary");
	public By BUTTON_ACTIVE = By.cssSelector(".btn.btn-primary.disabled");
	
	
	

	

}
