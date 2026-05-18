import java.util.Scanner;

public class Footloose {
    public static void main(String[] args) {
        Scanner escaner = new Scanner(System.in);
        String nombreAcceso = "";
        boolean acceso = false;
      while (!acceso) {
            menuInicio();
            int opcionInicio = escaner.nextInt();
            escaner.nextLine();
            if (opcionInicio == 1) {
                System.out.println("----- INICIAR SESION -----");
                System.out.println("Ingrese su nombre:");
                nombreAcceso = escaner.nextLine();
                bienvenida(nombreAcceso);
                acceso = true;

            } else if (opcionInicio == 2) {
                System.out.println("----- REGISTRO -----");
                System.out.println("Ingrese su nombre:");
                nombreAcceso = escaner.nextLine();
                System.out.println("Ingrese su correo:");
                String correoRegistro = escaner.nextLine();
                System.out.println("Ingrese su contraseña:");
                String clave = escaner.nextLine();
                System.out.println("Registro exitoso");
                bienvenida(nombreAcceso);
                acceso = true;
            } else if (opcionInicio == 3) {
                nombreAcceso = "Invitado";
                bienvenida(nombreAcceso);
                acceso = true;
            } else if (opcionInicio == 4) {
                System.out.println("Saliendo de Footloose...");
                System.exit(0);
            } else {
                System.out.println("Opcion no valida");
            }
        }
      boolean compraFinalizada = false;
        while (!compraFinalizada) {
            menuPrincipal();
            int opcionGenero = escaner.nextInt();
            escaner.nextLine();
            double total_pagar = 0;
            String producto = "";
            if (opcionGenero == 1) {
                menuVaron();
                System.out.println("Ingrese la opcion del producto:");
                int opcionProducto = escaner.nextInt();
                System.out.println("Ingrese la cantidad:");
                int cantidad = escaner.nextInt();
                total_pagar = calcularTotalVaron(opcionProducto, cantidad);
                producto = obtenerProductoVaron(opcionProducto);
                if (total_pagar <= 0) {
                    System.out.println("Opcion no valida");
                    continue;
                }
            } else if (opcionGenero == 2) {
                menuMujer();
                System.out.println("Ingrese la opcion del producto:");
                int opcionProducto = escaner.nextInt();
                System.out.println("Ingrese la cantidad:");
                int cantidad = escaner.nextInt();
                total_pagar = calcularTotalMujer(opcionProducto, cantidad);
                producto = obtenerProductoMujer(opcionProducto);
                if (total_pagar <= 0) {
                    System.out.println("Opcion no valida");
                    continue;
                }
            } else {
                System.out.println("Opcion no valida");
                continue;
            }
          metodoPago();
            int opcionPago = escaner.nextInt();
            escaner.nextLine();
            String pago = "";
            if (opcionPago == 1) {
                pago = "Tarjeta";
            } else if (opcionPago == 2) {
                pago = "Efectivo";
            } else {
                System.out.println("Opcion no valida");
                continue;
            }
            System.out.println("El total a pagar es: " + total_pagar);
            System.out.println("Ingrese el monto pagado:");
            double montoPagado = escaner.nextDouble();
            escaner.nextLine();

            if (montoPagado < total_pagar) {
                System.out.println("Monto insuficiente, regresa al menu principal");
                continue;
            }

            generarBoleta(nombre, apellido, correo, dni, telefono, producto, entrega, pago, total_pagar, montoPagado);

            compraFinalizada = true;
        }

        escaner.close();
    }
  public static void bienvenida(String nombre) {
        System.out.println("Bienvenido a Footloose: " + nombre);
    }

    public static void menuInicio() {
        System.out.println("------ FOOTLOOSE ------");
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Entrar como invitado");
        System.out.println("4. Salir");
    }

    public static void menuPrincipal() {
        System.out.println("------ MENU PRINCIPAL ------");
        System.out.println("1. Varon");
        System.out.println("2. Mujer");
    }
  public static void menuVaron() {
        System.out.println("------ PRODUCTOS VARON ------");
        System.out.println("1. Zapatilla sport");
        System.out.println("2. Zapatilla running");
        System.out.println("3. Zapatilla casual");
        System.out.println("4. Zapato urbano");
        System.out.println("5. Zapatilla promo 2 pares");
        System.out.println("6. Zapato formal");
        System.out.println("7. Zapatilla trekking");
        System.out.println("8. Zapato casual");
        System.out.println("9. Zapatilla urban pro");
        System.out.println("10. Zapato escolar");
        System.out.println("11. Zapatilla basica");
        System.out.println("12. Zapato promo 2 pares");
        System.out.println("13. Zapatilla street");
        System.out.println("14. Zapato modelo 14");
        System.out.println("15. Zapatilla modelo 15");
        System.out.println("16. Zapato modelo 16");
        System.out.println("17. Zapatilla modelo 17");
        System.out.println("18. Zapato modelo 18");
        System.out.println("19. Zapatilla modelo 19");
        System.out.println("20. Zapato modelo 20");
    }

    public static void menuMujer() {
        System.out.println("------ PRODUCTOS MUJER ------");
        System.out.println("1. Zapatilla sport");
        System.out.println("2. Zapatilla running");
        System.out.println("3. Zapatilla casual");
        System.out.println("4. Zapato urbano");
        System.out.println("5. Zapatilla moda");
        System.out.println("6. Zapato formal");
        System.out.println("7. Zapatilla promo 2 pares");
        System.out.println("8. Zapato casual");
        System.out.println("9. Zapatilla urban pro");
        System.out.println("10. Zapato escolar");
        System.out.println("11. Zapatilla basica");
        System.out.println("12. Zapato elegante");
        System.out.println("13. Zapatilla street");
        System.out.println("14. Zapato modelo 14");
        System.out.println("15. Zapatilla modelo 15");
        System.out.println("16. Zapato modelo 16");
        System.out.println("17. Zapatilla modelo 17");
        System.out.println("18. Zapato promo 2 pares");
        System.out.println("19. Zapatilla modelo 19");
        System.out.println("20. Zapato modelo 20");
    }
