package co.com.saucedemo.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaProductos extends PageObject {

    public static final Target TITULO_PAGINA = Target.the("título de la página de productos")
            .located(By.cssSelector("[data-test='title']"));
    public static final Target LISTA_PRODUCTOS = Target.the("lista de productos")
            .located(By.cssSelector("[data-test='inventory-list']"));
}
