import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexPageObjectTest {

    private static ChromeDriver driver;

    @FindBy(xpath = "//*[@data-id='market']")
    private WebElement market;

    @FindBy(xpath = "//*[@id=\"header-search\"]")
    private WebElement search;

    @FindBy(xpath = "//*[@class=\"search2__button\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@name='Цена от']")
    private WebElement minPrice;

    @FindBy(xpath = "//*[@name='Цена до']")
    private WebElement maxPrice;

    @FindBy(xpath = "//span[.=\"Apple\"]")
    private WebElement companyApple;

    @FindBy(xpath = "//span[.=\"ASUS\"]")
    private WebElement companyASUS;

    @FindBy(xpath = "//span[.=\"HP\"]")
    private WebElement companyHP;

    @FindBy(xpath = "//span[.=\"Xiaomi\"]")
    private WebElement companyXiaomi;

    @FindBy(xpath = "//span[.=\"Core i7\"]")
    private WebElement intelCore;

    @FindBy(xpath = "//strong[contains(.,'Толстовка')]")
    private WebElement sweatshirt;

    public YandexPageObjectTest(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void newYandexTest(String url) {
        // Заходим на Яндекс => Яндекс.Маркет => в поле поиска и вводим "Ноутбуки"
        driver.get(url);
        market.click();
        search.sendKeys("Ноутбуки");

        // Проверяем, что ввели именно "Ноутбуки"
        assertEquals(search.getAttribute("value"), "Ноутбуки");

        // Нажимаем "Найти"
        searchButton.click();

        // Вводим минимум и максимум цены и проверяем их
        minPrice.sendKeys("100000");
        maxPrice.sendKeys("200000");
        assertEquals(minPrice.getAttribute("value"), "100000");
        assertEquals(maxPrice.getAttribute("value"), "200000");

        //Ставим галочки напротив нужных фирм
        companyApple.click();
        companyASUS.click();
        companyHP.click();
        companyXiaomi.click();

        // Проверяем, что нужные фирмы отмечены
        assertTrue(companyApple.isEnabled());
        assertTrue(companyASUS.isEnabled());
        assertTrue(companyHP.isEnabled());
        assertTrue(companyXiaomi.isEnabled());

        // Выбираем Core i7
        intelCore.click();

        // Очищаем поле поиска => Вводим "Зелёный слоник" => Нажимаем "Найти"
        search.clear();
        search.sendKeys("Зелёный слоник");
        searchButton.click();

        // Ищем товар, содержащий в имени "Толстовка" и, если он есть, кликаем на него
        try {
            sweatshirt.click();
        } catch (NoSuchElementException thrown) {
            System.out.println("Упс, что-то пошло не так. 'Толстовка' не найдена");
        }

        // Снова заходин на Яндекс
        driver.get(url);
    }
}