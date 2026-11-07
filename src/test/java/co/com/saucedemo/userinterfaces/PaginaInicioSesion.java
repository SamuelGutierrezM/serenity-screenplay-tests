package co.com.saucedemo.userinterfaces;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("page:pagina.inicio.sesion")
public class PaginaInicioSesion extends PageObject {

    public static final Target CAMPO_USUARIO = Target.the("campo de usuario")
            .located(By.id("user-name"));
    public static final Target CAMPO_CONTRASENA = Target.the("campo de contraseña")
            .located(By.id("password"));
    public static final Target BOTON_INGRESAR = Target.the("botón de ingresar")
            .located(By.id("login-button"));
    public static final Target MENSAJE_ERROR = Target.the("mensaje de error")
            .located(By.cssSelector("[data-test='error']"));
}
