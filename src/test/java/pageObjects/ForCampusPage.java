package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



public class ForCampusPage extends BasePage {

	@FindBy(xpath="//img[@alt='Coursera for Campus']")
	private WebElement heading;
	
    @FindBy(id = "FirstName")
    private WebElement firstName;
    
    @FindBy(id = "LastName")
    private WebElement lastName;
    
    @FindBy(id = "Email")
    private WebElement email;
    
    @FindBy(id = "Phone")
    private WebElement phone;
    
    @FindBy(id="Institution_Type__c")
    private WebElement institutionType;
    
    @FindBy(id = "Company")
    private WebElement institutionName;
    
    @FindBy(id = "Title")
    private WebElement jobRole;
    
    @FindBy(id = "Department")
    private WebElement department;
    
    @FindBy(id="What_the_lead_asked_for_on_the_website__c")
    private WebElement needsDesc;
    
    @FindBy(id="Country")
    private WebElement country;
    
    @FindBy(id="State")
    private WebElement state;
    
    @FindBy(xpath = "//button[@class='mktoButton']")
    private WebElement submitButton;

    // Error message after form submission
    @FindBy(id = "ValidMsgEmail")
    private WebElement validEmailErr;
    
    
    
    public ForCampusPage(WebDriver driver) {
        super(driver);
    }
    
    
    public String getHeading() {
    	   return heading.getAttribute("alt");
    }

    //Fill and submit form with invalid data
    public void submitReadyToTransformForm(String fn,String ln, String emailId,String phoneNo, String instType,String instName,String job, String dept,String needs,String countryName,String stateName) {
    	       firstName.sendKeys(fn);
    	       lastName.sendKeys(ln);
    	       email.sendKeys(emailId);
    	       phone.sendKeys(phoneNo);
    	       
    	       Select select = new Select(institutionType);
    	       select.selectByVisibleText(instType);
    	       
    	       institutionName.sendKeys(instName);
    	       
    	       Select select1 = new Select(jobRole);
    	       select1.selectByVisibleText(job);
    	       
    	       Select select2 = new Select(department);
    	       select2.selectByVisibleText(dept);
    	       
    	       Select select3 = new Select(needsDesc);
    	       select3.selectByVisibleText(needs);
    	       
    	       Select select4 = new Select(country);
    	       select4.selectByVisibleText(countryName); 
    	       
    	       Select select5 = new Select(state);
    	       select5.selectByVisibleText(stateName);
    	       submitButton.click();

    }

    //Capture error message after invalid submission
    public String getFormErrorMessage() {
        return validEmailErr.getText();
    }
}
 