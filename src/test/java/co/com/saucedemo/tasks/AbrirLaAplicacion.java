package co.com.saucedemo.tasks;

import co.com.saucedemo.userinterfaces.PaginaInicioSesion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AbrirLaAplicacion implements Task {

    public static AbrirLaAplicacion enLaPaginaDeInicioDeSesion() {
        return instrumented(AbrirLaAplicacion.class);
    }

    @Override
    @Step("{0} abre la página de inicio de sesión de SauceDemo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().the(PaginaInicioSesion.class));
    }
}
