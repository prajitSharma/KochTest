package com.koch.flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.koch.enums.LocatorTypeEnum;
import com.koch.pages.FlightResultPage;
import com.koch.pages.HomePage;
import com.koch.utilities.SeleniumUtil;

public class HomePageFlow {
	
	private HomePage homePage = new HomePage();
	
	public boolean verifyHomePage(WebDriver driver) {
		
		if(SeleniumUtil.getWebElement(driver, homePage.ixigo_icon_xpath, LocatorTypeEnum.XPATH).isDisplayed() && SeleniumUtil.getWebElement(driver, homePage.search_button_xpath, LocatorTypeEnum.XPATH).isDisplayed())
			return true;
		else {
			System.out.println("HomePage Not Loaded");
			return false;	
		}
		
	}
	
	
	//Select a Source City 
	public void selectSourceCity(WebDriver driver, String City) throws InterruptedException {
		
		Actions ac = new Actions(driver);
		ac.click(SeleniumUtil.getWebElement(driver, homePage.from_textbox_xpath, LocatorTypeEnum.XPATH)).build().perform();
		SeleniumUtil.getWebElement(driver, homePage.from_textbox_xpath, LocatorTypeEnum.XPATH).sendKeys(City);
		Thread.sleep(2000);
		SeleniumUtil.pressEnter(driver);
		Thread.sleep(2000);
		
		
	}
	
	//Select a Destination City
    public void selectDestinationCity(WebDriver driver, String City) throws InterruptedException {
		
    	Actions ac = new Actions(driver);
		ac.click(SeleniumUtil.getWebElement(driver, homePage.to_textbox_xpath, LocatorTypeEnum.XPATH)).build().perform();
		SeleniumUtil.getWebElement(driver, homePage.to_textbox_xpath, LocatorTypeEnum.XPATH).sendKeys(City);
		Thread.sleep(2000);
		SeleniumUtil.pressEnter(driver);
		Thread.sleep(2000);
		
		
	}
    
    //Select a Departure Date
    public void selectDepartureDate(WebDriver driver, String date) {
		
		SeleniumUtil.getWebElement(driver, homePage.departure_date_textbox_xpath, LocatorTypeEnum.XPATH).click();
		SeleniumUtil.selectDateByJavascriptExecutor(driver, SeleniumUtil.getWebElement(driver, homePage.departure_date_textbox_xpath, LocatorTypeEnum.XPATH), date);
		
		
	}
    
    //Select a Return Date 
    public void selectReturnDate(WebDriver driver, String date) {
		
		SeleniumUtil.getWebElement(driver, homePage.return_date_textbox_xpath, LocatorTypeEnum.XPATH).click();
		SeleniumUtil.selectDateByJavascriptExecutor(driver, SeleniumUtil.getWebElement(driver, homePage.return_date_textbox_xpath, LocatorTypeEnum.XPATH), date);
		
		
	}
    
    //Select Passengers Stats
    public void selectPasengersWithType(WebDriver driver, String numberOfPassengers, String TypeOfPassengers) {
    	
    	SeleniumUtil.getWebElement(driver, homePage.travellers_class_textbox_xpath, LocatorTypeEnum.XPATH).click();
    	
    	String xpath1 = "//div[text()='";
    	String xpath2 = TypeOfPassengers;
    	String xpath3 = "']//parent::div//parent::div//span[text()='";
    	String xpath4 = numberOfPassengers;
    	String xpath5 = "']";
    	
    	String valueToBeSeleted = xpath1 + xpath2 + xpath3 + xpath4 + xpath5 ;
    	
    	SeleniumUtil.getWebElement(driver, valueToBeSeleted, LocatorTypeEnum.XPATH).click();
    	
    }
    
    
    //Search Flights
    public FlightResultPage searchFlights(WebDriver driver) {
    	
    	SeleniumUtil.getWebElement(driver, homePage.search_button_xpath, LocatorTypeEnum.XPATH).click();
    	return new FlightResultPage();
    	
    }
    

}
