package listeners.testng;

import driverfactory.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ScreenShotManager;

import java.io.IOException;
import java.lang.reflect.Field;

import static utilities.properties.PropertiesManager.ReportConfig;
import static utilities.properties.PropertiesManager.initializeProperties;

public class TestNGListener implements ITestListener, IExecutionListener {


    @Override
    public void onExecutionStart() {
        System.out.println("********* Welcome to Selenium Framework *************");
        initializeProperties();
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Generating Report......");
        if (ReportConfig.getProperty("OpenAllureReportAfterExecution").equalsIgnoreCase("true")) {
            try {
                System.out.println("Opening Allure Report");
                Runtime.getRuntime().exec("reportGeneration.bat");
            } catch (IOException e) {
                System.out.println("Unable to Generate Allure Report, may be there's an issue in the batch file/commands");
            }
        }
        System.out.println("********* End of Execution *************");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("********* Starting TEST: " + result.getName() + " *************");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("********* Success of TEST: " + result.getName() + " *************");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");
        System.out.println("Taking screenshot ....");

        Driver driver = null;
        ThreadLocal<Driver> driverThreadLocal;
        Object currentClass = result.getInstance();
//عايزة ارجع كل ال fields اللي فى ال testClass
        Field[] fields = result.getTestClass().getRealClass().getDeclaredFields();//«هات كل الفيلدات اللي متعرفة جوه الكلاس اللي اتنفذ منه التست الحالي.»
        try {
            for (Field field : fields) {            // عايز ارجع ال type بتاع ال fields
                if (field.getType() == Driver.class) {
                    driver = (Driver) field.get(currentClass);
                }
                if (field.getType() == ThreadLocal.class) {
                    driverThreadLocal = (ThreadLocal<Driver>) field.get(currentClass);

                    driver = driverThreadLocal.get();  // ⤵⤵ ⤵ هنا ساوتهم ببعض علشان ال screenshot بتاخد ال  driver raw
                }
            }
        } catch (IllegalAccessException exception) {
            System.out.println("Unable to get field , Field should be public");

        }
        assert driver != null;
        ScreenShotManager.captureScreenShot(driver.get(), result.getName());
        System.out.println("**************** Failure of Test: " + result.getName() + " *******************");


        // علشان ال  field فى ال test class ممكن ميبقاش اسمها driver و يكون ليها اسم تانى علشان كدة استخدمت الطريقى اللى فوق علشان اجيب field محدد فى ال test class
//        try {
//            driver = (Driver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//        ScreenShotManager.captureScreenShot(driver.get(), result.getName());
//        System.out.println("**************** Failure of Test: " + result.getName() + " *******************");


    }


}
