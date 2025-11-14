package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// ForCampus page object
public class ForCampusPage extends BasePage {

    // Ready to transform form fields (adjust the locators as per actual site)
    @FindBy(id = "name")

    private WebElement nameInput;
    @FindBy(id = "email")

    private WebElement emailInput;
    @FindBy(id = "institution")

    private WebElement institutionInput;
    @FindBy(id = "phone")

    private WebElement phoneInput;
    @FindBy(xpath = "//button[contains(text(),'Submit')]")

    private WebElement submitButton;

    // Error message after form submission
    @FindBy(xpath = "//div[contains(@class,'error-message')]")

    private WebElement errorMessage;
    public ForCampusPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Step 7: Fill and submit form with invalid data
    public void submitReadyToTransformForm(String name, String email, String institution, String phone) {
        nameInput.clear();      
        nameInput.sendKeys("Nishtha");
       
        emailInput.clear();     
        emailInput.sendKeys("abc@xyz.com");
        
        institutionInput.clear();
        institutionInput.sendKeys("xyz");
       
        phoneInput.clear();     
        phoneInput.sendKeys("1234567890");
        
        submitButton.click();

    }

    // Step 8: Capture error message after invalid submission
    public String getFormErrorMessage() {
        return errorMessage.getText();
    }
}
 