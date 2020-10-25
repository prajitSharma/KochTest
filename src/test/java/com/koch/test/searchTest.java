package com.koch.test;

import org.testng.annotations.Test;

import com.koch.baseTest.BaseTest;
import com.koch.flow.HomePageFlow;

public class searchTest extends BaseTest{
	
	private HomePageFlow homePageFlow = new HomePageFlow();
	
	@Test(enabled = true)
	public void searchFlightsOnIxigo() throws InterruptedException {
		
		homePageFlow.verifyHomePage(driver);
		homePageFlow.selectSourceCity(driver, "Pune");
		homePageFlow.selectDestinationCity(driver, "Hyderabad");
		homePageFlow.selectDepartureDate(driver, "30102020");
		homePageFlow.selectReturnDate(driver, "05112020");
		
		
	}
	

}
