package com.koch.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.koch.enums.LocatorTypeEnum;

public class SeleniumUtil {
	
	
	
	 public static WebElement getWebElement(WebDriver driver, String css, LocatorTypeEnum byType) {
	        WebElement element = null;
	        Long waitTime = 20L;
	        try{
	            switch (byType) {
	                case CSSSELECTOR:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.cssSelector(css),waitTime);
	                    break;
	                case XPATH:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.xpath(css),waitTime);
	                    break;
	                case ID:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.id(css),waitTime);
	                    break;
	                case CLASSNAME:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.className(css),waitTime);
	                    break;
	                case NAME:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.name(css),waitTime);
	                    break;
	                case LINKTEXT:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.linkText(css),waitTime);
	                    break;
	                case PARTIALLINKTEXT:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.partialLinkText(css),waitTime);
	                    break;
	                case TAGNAME:
	                    element = SeleniumUtil.waitForElementVisible(driver, By.tagName(css),waitTime);
	                    break;
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println("Can't find the element by using "+ byType + ": " + css);
	        }
	        return element;
	    }
	 
	 
	 
	 
	   
	   public static String getClassPath() {
			String result = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			try {
				result = URLDecoder.decode(result, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return result;
		}
	 
	 
	 
	 
	 
	    public static WebElement waitForElementVisible(WebDriver driver, final By locator,  long waitTime) {
	        Function<WebDriver, WebElement> waitFunction = new Function<WebDriver, WebElement>() {
	            public WebElement apply(WebDriver driver) {
	                try {
	                    WebElement element = driver.findElement(locator);
	                    if(element.isDisplayed()) {
	                        return element;
	                    }
	                } catch (Exception var3) {
	                    ;
	                }
	                return null;
	            }
	        };
	        WebDriverWait wait = new WebDriverWait(driver,waitTime);
	        wait.withMessage("Cannot find Element: " + locator.toString());
	        return (WebElement)wait.until(waitFunction);
	    }
	    
	    
	    
	    public static boolean pressEnter(WebDriver driver){
	        Boolean isChangeSuccess = false; //name
	        Actions action = new Actions(driver);
	        try
	        {
	            action.sendKeys(Keys.RETURN).build().perform();
	            isChangeSuccess = true;
	        }
	        catch(Exception e){
	            isChangeSuccess = false;
	            e.printStackTrace();
	        }
	        return isChangeSuccess;
	    }
	    
	    
	    public static void selectDateByJavascriptExecutor(WebDriver driver, WebElement element, String date) {
	    	
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	js.executeScript("arguments[0].setAttribute(value','"+date+"');", element);
	    }


}
