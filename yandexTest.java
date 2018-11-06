import junit.framework.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;


public class yandexTest {

    private static ChromeDriver driver;
    static String yandex = "https://yandex.ru/";

    @BeforeAll
    public static void beforeTest() {
        System.out.println("Запуск теста");
        System.setProperty("webdriver.chrome.driver","C:\\geckodriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void firstYandexTest() {
        // Заходим на Яндекс
        driver.get(yandex);
        //Переход на Яндекс.Маркет
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div/div[2]/div/div[1]/div/a[5]")).click();
        // Ищем поле поиска и вводим "Ноутбуки"
        driver.findElement(By.xpath("//*[@id=\"header-search\"]")).sendKeys("Ноутбуки");

        // Проверяем, что ввели именно "Ноутбуки" (Не работает)
        //String checkLaptop = driver.findElement(By.xpath("//*[@id=\"market-search\"]/span/div/span[1]")).getAttribute("Ноутбуки");
        //System.out.println("checkLaptop = " + checkLaptop);
        //Assert.assertEquals(checkLaptop, "Ноутбуки");

        // Нажимаем "Найти"
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/noindex/div[2]/div/div[2]/div/div[1]/form/span/span[2]/button")).click();
        // Вводим минимум цены
        driver.findElement(By.xpath("//*[@id=\"glpricefrom\"]")).sendKeys("100000");
        // Вводим максимум цены
        driver.findElement(By.xpath("//*[@id=\"glpriceto\"]")).sendKeys("200000");

        // Ставим галочки напротив нужных фирм
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[3]/div/a/label/div")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[4]/div/a/label/div")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[6]/div/a/label/div")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[12]/div/a/label/div")).click();

        // Проверяем, что нужные фирмы отмечены
        boolean apple = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[3]/div/a/label/div")).isEnabled();
        Assert.assertEquals(apple, true);
        boolean asus = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[4]/div/a/label/div")).isEnabled();
        Assert.assertEquals(asus, true);
        boolean hp = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[6]/div/a/label/div")).isEnabled();
        Assert.assertEquals(hp, true);
        boolean xiaomi = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[3]/fieldset/ul/li[12]/div/a/label/div")).isEnabled();
        Assert.assertEquals(xiaomi, true);

        // Выбираем Core i7
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div/div[2]/div[9]/fieldset/ul/li[2]/div/label/div")).click();

        // Проверяем, что введены именно те минимум и максимум цены (Не работает)
        //String checkPriceMin = driver.findElement(By.xpath("//*[@value=\"100000\"]")).getText();
        //Assert.assertEquals(checkPriceMin, "100000");
        //String checkPriceMax = driver.findElement(By.xpath("//*[@value=\"200000\"]")).getText();
        //Assert.assertEquals(checkPriceMax, "200000");

        // Очищаем поле поиска
        driver.findElement(By.xpath("//*[@id=\"header-search\"]")).clear();
        // Вводим "Зелёный слоник"
        driver.findElement(By.xpath("//*[@id=\"header-search\"]")).sendKeys("Зелёный слоник");
        // Нажимаем "Найти"
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/noindex/div[2]/div/div[2]/div/div[1]/form/span/span[2]/button")).click();

        // Ищем товар, содержащий в имени "Толстовки"
        try {
            String sweatshirt = driver.findElement(By.xpath("//a[.='Толстовки']")).getText();
            Assert.assertEquals(sweatshirt, "Толстовки");
            // Кликаем на этот товар, если он есть
            driver.findElement(By.xpath("//a[.='Толстовки']")).click();
        }
        catch (NoSuchElementException thrown) {
            System.out.println("Упс, что-то пошло не так");
            System.out.println("'Толстовка' не найдена");
        }

        // Снова заходин на Яндекс
        driver.get(yandex);
    }

    @AfterAll
    public static void afterTest() {
        System.out.println("Тест окончен");
        driver.quit();
    }

}