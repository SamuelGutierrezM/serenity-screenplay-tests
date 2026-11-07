package co.com.saucedemo.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.waits.WaitUntil.the;

/**
 * Espera a que el campo sea visible, lo limpia y escribe el valor indicado.
 */
public class LimpiarYEscribir implements Interaction {

    private final String valor;
    private final Target campo;

    public LimpiarYEscribir(String valor, Target campo) {
        this.valor = valor;
        this.campo = campo;
    }

    public static Constructor elValor(String valor) {
        return new Constructor(valor);
    }

    @Override
    @Step("{0} escribe '#valor' en #campo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                the(campo, isVisible()).forNoMoreThan(10).seconds(),
                Clear.field(campo),
                Enter.theValue(valor).into(campo)
        );
    }

    public static class Constructor {
        private final String valor;

        private Constructor(String valor) {
            this.valor = valor;
        }

        public LimpiarYEscribir en(Target campo) {
            return instrumented(LimpiarYEscribir.class, valor, campo);
        }
    }
}
