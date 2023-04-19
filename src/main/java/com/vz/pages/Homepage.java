
package com.vz.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.vz.base.TestBase;

public class Homepage extends TestBase {

	// PageFactory = OR

	@FindBy(how = How.CSS, using = "#main-nav")
	@CacheLookup
	WebElement Header;
	
	@FindBy(how = How.CSS, using = "#logo > a")
	WebElement Title;
	
	@FindBy(how = How.NAME, using = "widget-type")
	@CacheLookup
	List<WebElement> RBtn_Adventure;
	
	@FindBy(how= How.CLASS_NAME, using = "add-traveler")
	WebElement Btn_AddATraveler;
	
	@FindBy(how= How.XPATH, using ="//*[contains(@id,'r-passenger') and @class = 'passenger added-passenger']")
	@CacheLookup
	WebElement Dropdown_AddedPassenger;
	
	@FindBy(how= How.XPATH, using = "//i[@class='vid-next icon-video-right-arrow']")
	WebElement Btn_RightArrow;
	
	@FindBy(how= How.ID, using = "video-text")
	WebElement Txt_Video;
    

	// Initializing the Page Objects
	public Homepage() {

		// To Initialize the @FindBy Using Below
		PageFactory.initElements(driver, this);

	}
	
	public void verifyHeader() {
		 // String getHeaderText = Header.getText();
		  assertEquals(true,Header.isEnabled());
		  
	  }

	public void verifyTitlle() {
		  String getHeaderText = Title.getText();
		  assertEquals("Travel Agents\r\n"
		  		+ "Your country\r\n"
		  		+ "Your language",getHeaderText);
	  }
	
	public int verifyAdventureRadioButtons() {
		int numOfRadioButtons = RBtn_Adventure.size();
		return numOfRadioButtons ;
		
	}
	
	public void clickAddaTraveler() {
		Btn_AddATraveler.click();
	}
	
	public boolean verifyAddedPassengerDropdown() {
		return Dropdown_AddedPassenger.isDisplayed();
	}
	
	public String getVideoText() {
		return Txt_Video.getText();
	}
	
	public void clickRightArrow() {
		Btn_RightArrow.click();
	}
}
