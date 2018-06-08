package demoLog;

import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Loggin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "YOUR_SITE";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoggin(String username, String password) throws Exception {
	  
    driver.get(baseUrl + "YOUR_SITE");
    driver.findElement(By.xpath("LINK_XPATH")).click();
    driver.findElement(By.id("Lusername")).clear();
    driver.findElement(By.id("Lusername")).sendKeys(username);
    driver.findElement(By.id("Lpassword")).clear();
    driver.findElement(By.id("Lpassword")).sendKeys(password);
    driver.findElement(By.id("btn-login")).click();
    driver.findElement(By.cssSelector("LINK_LOCATOR")).click();
    Thread.sleep(3000);
  
  Alert alert;
  try{
      alert = driver.switchTo().alert();
      alert.dismiss();
  }
  catch(Exception e) {
      e.printStackTrace();
  }
}

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  @DataProvider(name = "data")
    String[][] data () throws IOException {
        String[][] x = new String[5][];
        try (BufferedReader bfr = new BufferedReader(new FileReader(new File("YOUR_TEST_DATA.csv")))) {
          String line;
          int i = 0;
          while ((line = bfr.readLine()) != null) {
             x[i++] = (line.split(","));
             
          }
        }
        return x;
  }
}
