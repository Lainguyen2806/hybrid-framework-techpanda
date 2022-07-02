package javaBasic;

public class Topic01_Dynamic_Locator {
	public static final String ACCOUNT_INFO_LINK = "xpath=//a[text()='Account Information']";
	public static final String MY_APPLICATION_LINK = "xpath=//a[text()='My Applications']";
	public static final String MY_ORDER_LINK = "xpath=//a[text()='My Orders'] ";

	// 1 tham số động
	public static final String DYNAMIC_SIDEBAR_LINK = "xpath=//a[text()='%s']";

	public static final String DYNAMIC_COUNTRY_TOTAL = "xpath=//td[@data-key='country' and text()='%s']//following-sibling::td[@data-key='total' and text()='%s']";

	public static void main(String[] args) {
		clickToElement(DYNAMIC_SIDEBAR_LINK, "My Applications");
		clickToElement(DYNAMIC_SIDEBAR_LINK, "My Orders");
		clickToElement(DYNAMIC_SIDEBAR_LINK, "Account Information");

		clickToElement(DYNAMIC_COUNTRY_TOTAL, "Afghanistan", "791312");
		clickToElement(DYNAMIC_COUNTRY_TOTAL, "Argentina", "687522");
		clickToElement(DYNAMIC_COUNTRY_TOTAL, "Aruba", "1504");
	}

	// Locator cố định
	public static void clickToElement(String locator) {
		System.out.println(locator);

	}

//	// 1 tham số động
//	public static void clickToElement(String locator, String pageName) {
//		locator = String.format(locator, pageName);
//		System.out.println(locator);
//
//	}
//
//	// 2 tham số động
//	public static void clickToElement(String locator, String countryName, String total) {
//		locator = String.format(locator, countryName, total);
//		System.out.println(locator);
//	}

	// 1-n tham động: Rest parameter
	public static void clickToElement(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		System.out.println(locator);

	}

}
