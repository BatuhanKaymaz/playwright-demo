package utilities;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.internal.ObjectBag.cleanup;

public class Hooks extends TestListenerAdapter {
    private Playwright playwright ;
    private Browser browser ;
    public static Page page;
    private BrowserContext browserContext;



    @Override

    @BeforeTest
    public void onTestStart (ITestResult result){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        BrowserFactory browserFactory = new BrowserFactory();
        String browserName = PropertyUtils.getProperty("browser");
        this.browser = browserFactory.getBrowser(browserName);
        this.browserContext = browserFactory.createPageAndGetContext(this.browser,result);
        this.page= browserContext.newPage();
        page.setViewportSize(width,height);
        page.navigate(PropertyUtils.getProperty("url"));
    }

    @Override
    @AfterTest
    public void onTestFailure (ITestResult result) {
        //Basarisizlik durumunda kaydi durdur ve kaydet
        String tracePath = getTraceFilePath(result);
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(tracePath)));
        cleanup();
    }

    public String getTraceFilePath(ITestResult result){
        String baseDir = "/src/test/java/utilities/traceViewer";
        String methodName = result.getMethod().getMethodName();
        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        return baseDir + methodName + date + "-trace,zip";
    }

    public void  cleanup () {
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

}