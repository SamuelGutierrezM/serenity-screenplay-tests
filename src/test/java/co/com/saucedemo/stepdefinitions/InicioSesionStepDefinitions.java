package co.com.saucedemo.stepdefinitions;

import co.com.saucedemo.models.Usuario;
import co.com.saucedemo.questions.MensajeDeError;
import co.com.saucedemo.questions.TituloDeProductos;
import co.com.saucedemo.tasks.AbrirLaAplicacion;
import co.com.saucedemo.tasks.IniciarSesion;
import co.com.saucedemo.utils.Constantes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class InicioSesionStepDefinitions {

    @Given("que el cliente está en la página de inicio de sesión de SauceDemo")
    public void estaEnLaPaginaDeInicioDeSesion() {
        theActorCalled(Constantes.NOMBRE_ACTOR).attemptsTo(AbrirLaAplicacion.enLaPaginaDeInicioDeSesion());
    }

    @When("el cliente inicia sesión con el usuario {string} y la contraseña {string}")
    public void iniciaSesionCon(String nombreUsuario, String contrasena) {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.como(new Usuario(nombreUsuario, contrasena)));
    }

    @Then("el cliente debería ver la página de productos")
    public void deberiaVerLaPaginaDeProductos() {
        theActorInTheSpotlight().should(seeThat(TituloDeProductos.mostrado(), equalTo(Constantes.TITULO_PRODUCTOS)));
    }

    @Then("el cliente debería ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        theActorInTheSpotlight().should(seeThat(MensajeDeError.mostrado(), containsString(mensajeEsperado)));
    }
}
