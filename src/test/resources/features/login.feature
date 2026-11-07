Feature: Inicio de sesión en SauceDemo
  Como cliente de SauceDemo
  Quiero iniciar sesión con mis credenciales
  Para poder consultar el catálogo de productos

  Background:
    Given que el cliente está en la página de inicio de sesión de SauceDemo

  @smoke @login @Regresion
  Scenario: Inicio de sesión exitoso con un usuario estándar
    When el cliente inicia sesión con el usuario "standard_user" y la contraseña "secret_sauce"
    Then el cliente debería ver la página de productos

  @login @Regresion
  Scenario Outline: Inicio de sesión fallido muestra un mensaje de error
    When el cliente inicia sesión con el usuario "<usuario>" y la contraseña "<contrasena>"
    Then el cliente debería ver el mensaje de error "<mensaje>"

    Examples:
      | usuario         | contrasena    | mensaje                                                                   |
      | locked_out_user | secret_sauce  | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | clave_errada  | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce  | Epic sadface: Username is required                                        |
