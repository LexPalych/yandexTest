import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.PrintWriter;
import java.io.StringWriter;

public class secondYandexTest {

    private static ChromeDriver driver;
    private static String url = "https://yandex.ru/";

    @BeforeAll
    public static void beforeTest() {
        System.out.println("Запуск теста");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Alexandr\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();

        //Чтобы окошко развернулось на весь экран
        driver.manage().window().maximize();
    }

    @Test
    public void firstYandexTest() {
        YandexPageObjectTest test = new YandexPageObjectTest(driver);
        try {
            test.newYandexTest(url);
        } catch (Exception exception) {
            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw));
            String stackTrace = sw.toString();
            System.out.println("Ошибочка = " + stackTrace);
        }
    }

    @AfterAll
    public static void afterTest() {
        System.out.println("Тест окончен");
        driver.quit();
    }

}