package co.com.saucedemo.models;

public record Usuario(String nombreUsuario, String contrasena) {

    @Override
    public String toString() {
        return nombreUsuario;
    }
}
