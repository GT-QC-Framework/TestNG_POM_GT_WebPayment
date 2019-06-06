package com.payzing.testcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.payzing.actions.LogInPage;

import CommonPage.CommonTestcase;

public class LoginScript extends CommonTestcase {
	WebDriver driver;

	LogInPage loginPage;

	@Parameters({ "browser", "version", "url" })

	@BeforeClass

	public void beforeClass(String browser, String version, String url) {
		driver = openMultiBrowser(browser, version, url);

		loginPage = PageFactory.initElements(driver, LogInPage.class);

	}

//	@BeforeMethod

	public void beforeMethod() throws InterruptedException {
		loginPage.openUrl("https://pay.zing.vn/");

		loginPage.inputTXT_TIMKIEMGAME("vo lam truyen ky mobile");

		Thread.sleep(3000);

		loginPage.clickKETQUA_TIMKIEM();

	}

	@Test
	public void testcase_01_Dont_input_username_password() throws InterruptedException {

		loginPage.openUrl("https://pay.zing.vn/");

		loginPage.inputTXT_TIMKIEMGAME("vo lam truyen ky mobile");

		Thread.sleep(3000);

		loginPage.clickKETQUA_TIMKIEM();

		loginPage.clickBTT_DangNhap();

		verifyEqual(loginPage.getText_Null_UserName_Password(), "Vui lòng nhập tài khoản ZingID");
	}

	@Test
	public void testcase_02_Only_Type_Input_UserName() {

		loginPage.input_Username_TXT("giinboo1");

		loginPage.clickBTT_DangNhap();

		verifyEqual(loginPage.getText_OnlyType_Username(), "Vui lòng nhập mật khẩu");

		loginPage.clearByJs("$('input#u').val(\"\")");
	}

	@Test
	public void testcase_03_Only_Type_Input_Password() {

		loginPage.input_Password_TXT("Aa123456!");

		loginPage.clickBTT_DangNhap();

		verifyEqual(loginPage.getText_OnlyType_Password(), "Vui lòng nhập tài khoản ZingID");

		loginPage.clearByJs("$('input#p').val(\"\")");
	}

	@Test
	public void testcase_04_Type_Invalid_Account() throws InterruptedException {

		loginPage.input_Username_TXT("giinboo123456");

		loginPage.input_Password_TXT("Aa123456!");

		loginPage.clickBTT_DangNhap();

		Thread.sleep(3000);

		verifyEqual(loginPage.getText_Invalid_Account(), "Thông tin đăng nhập không chính xác");

		loginPage.clearByJs("$('input#u').val(\"\")");

		loginPage.clearByJs("$('input#p').val(\"\")");

	}

	@Test
	public void testcase_05_Login_account_ZingID_success() throws InterruptedException {

		loginPage.input_Username_TXT("giinboo1");

		loginPage.input_Password_TXT("Aa123456!");

		loginPage.clickBTT_DangNhap();

		Thread.sleep(3000);

		verifyEqual(loginPage.getText_Title_Game(), "Võ Lâm Truyền Kỳ Mobile");

		Thread.sleep(1000);

		loginPage.clickBTT_Thoat();
	}

//		// get all Selector
//		List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class,'am-selected am-dropdown')]"));
//		WebElement eleCumMayChu = null;
//		WebElement eleMayChu = null;
//		WebElement eleNhanVat = null;
//
//		if (list.size() == 3) {
//			eleCumMayChu = list.get(0);
//			eleMayChu = list.get(1);
//			eleNhanVat = list.get(2);
//		} else if (list.size() == 2) {
//			eleMayChu = list.get(0);
//			eleNhanVat = list.get(1);
//		} else {
//			eleNhanVat = list.get(0);
//		}
//
//		if (eleCumMayChu != null) {
//			loginPage.clickDropDown_CumMayChu(eleCumMayChu);
//			Thread.sleep(500);
//			String locator = String.format(LogInPageUI.LIST_ITEM_CUMMAYCHU, "Cụm máy chủ 41 - 50");
//			loginPage.selectDropdown_CumMayChu(eleCumMayChu, locator);
//		}
//
//		Thread.sleep(1000);
//
//		if (eleMayChu != null) {
//			loginPage.clickDropDown_MayChu(eleMayChu);
//			Thread.sleep(500);
//			String locator = String.format(LogInPageUI.LIST_ITEM_MAYCHU, "S44-Bắc Sơn");
//			loginPage.selectDropdown_MayChu(eleMayChu, locator);
//		}
//
//		Thread.sleep(2000);
//
//		if (eleNhanVat != null) {
//
//			loginPage.clickDropDown_NhanVat(eleNhanVat);
//			Thread.sleep(500);
//			String locator = String.format(LogInPageUI.LIST_ITEM_NHANVAT, "GiinVD");
//			loginPage.selectDropdown_NhanVat(eleNhanVat, locator);
//		}

	@Test
	public void testcase_06_select_quicklogin() {
		loginPage.clickQuickLogin_Icon();
	}

	@Test
	public void testcase_07_QuickLogin_Dont_type_Username_and_password() {

		loginPage.clickBTT_DangNhap();
		verifyEqual(loginPage.getText_Null_UserName_Password(), "Vui lòng nhập email bảo vệ tài khoản");
	}

	@Test
	public void testcase_08_QuickLogin_Only_type_Username() {

		loginPage.input_Username_TXT("nhutne123456@gmail.com");

		loginPage.clickBTT_DangNhap();

		verifyEqual(loginPage.getText_OnlyType_Username(), "Vui lòng nhập mật khẩu");

		loginPage.clearByJs("$('input#u').val(\"\")");

	}

	@Test
	public void testcase_09_QuickLogin_Only_type_Password() {

		loginPage.input_Password_TXT("1234534646");

		loginPage.clickBTT_DangNhap();

		verifyEqual(loginPage.getText_OnlyType_Password(), "Vui lòng nhập email bảo vệ tài khoản");

		loginPage.clearByJs("$('input#p').val(\"\")");
	}

	@Test
	public void testcase_10_QuickLogin_Login_account_invalid() throws InterruptedException {
		
		loginPage.input_Username_TXT("nhutneTVXQ@gmail.com");
		
		loginPage.input_Password_TXT("1234534646");

		loginPage.clickBTT_DangNhap();
		
		Thread.sleep(2000);

		verifyEqual(loginPage.getText_Invalid_Account(), "Thông tin đăng nhập không chính xác");


	}

	@AfterClass
	public void afterClass() {

		CloseBrowser();
	}
}
