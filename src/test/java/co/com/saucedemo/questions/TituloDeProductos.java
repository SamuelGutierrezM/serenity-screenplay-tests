package co.com.saucedemo.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.saucedemo.userinterfaces.PaginaProductos.TITULO_PAGINA;

public class TituloDeProductos {

    private TituloDeProductos() {
    }

    public static Question<String> mostrado() {
        return Text.of(TITULO_PAGINA).describedAs("el título de la página de productos");
    }
}
