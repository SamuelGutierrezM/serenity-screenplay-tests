package co.com.saucedemo.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.saucedemo.userinterfaces.PaginaInicioSesion.MENSAJE_ERROR;

public class MensajeDeError {

    private MensajeDeError() {
    }

    public static Question<String> mostrado() {
        return Text.of(MENSAJE_ERROR).describedAs("el mensaje de error del inicio de sesión");
    }
}
