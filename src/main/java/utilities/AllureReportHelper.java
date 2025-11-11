package utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AllureReportHelper {

    private AllureReportHelper() {
    }

    public static void cleanAllureReport() {
        try {
            FileUtils.deleteDirectory(new File("target/allure-results")); // ضفت بس فى الاول dependency common io فى pom.xml
        } catch (IOException e) {
            System.out.println("Allure Report Already Cleaned");
        }
    }
}
