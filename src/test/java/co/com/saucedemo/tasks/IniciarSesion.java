package co.com.saucedemo.tasks;

import co.com.saucedemo.interactions.LimpiarYEscribir;
import co.com.saucedemo.models.Usuario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.saucedemo.userinterfaces.PaginaInicioSesion.BOTON_INGRESAR;
import static co.com.saucedemo.userinterfaces.PaginaInicioSesion.CAMPO_CONTRASENA;
import static co.com.saucedemo.userinterfaces.PaginaInicioSesion.CAMPO_USUARIO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IniciarSesion implements Task {

    private final Usuario usuario;

    public IniciarSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public static IniciarSesion como(Usuario usuario) {
        return instrumented(IniciarSesion.class, usuario);
    }

    @Override
    @Step("{0} inicia sesión como #usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                LimpiarYEscribir.elValor(usuario.nombreUsuario()).en(CAMPO_USUARIO),
                LimpiarYEscribir.elValor(usuario.contrasena()).en(CAMPO_CONTRASENA),
                Click.on(BOTON_INGRESAR)
        );
    }
}
