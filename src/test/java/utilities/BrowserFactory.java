package utilities;

import com.microsoft.playwright.*;
import org.testng.ITestResult;

public class BrowserFactory {
    private Playwright playwright;

    public BrowserFactory (){
        playwright = Playwright.create();
    }

    public Browser getBrowser(String browserName){
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
        BrowserType browserType  ;
        switch (browserName.toLowerCase()){
            case "chromium":
                browserType = playwright.chromium();
                break;
            case "chrome":
                browserType = playwright.chromium();
                launchOptions.setChannel("chrome");
                break;
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "safari":
                browserType = playwright.webkit();
                break;
            case "edge":
                browserType = playwright.chromium();
                launchOptions.setChannel("msedge");
                break;
            default:
                String message = "Browser name" + browserName + "Error";
                message += "Lutfen Browser ismini kontrol edin!!!!!!";
                throw new IllegalArgumentException(message);
        }
        return browserType.launch(launchOptions);
    }

    public BrowserContext createPageAndGetContext(Browser browser, ITestResult result){
        BrowserContext context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
                .setName(result.getMethod().getMethodName()));
        return context;
    }
}
