import java.util.Scanner;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Footloosee {

    public static void main(String[] args) {
        Scanner escaner = new Scanner(System.in);
        boolean programaCorriendo = true;

        while (programaCorriendo) {
            mostrarBienvenida();
            int opcionAcceso = escaner.nextInt();
            escaner.nextLine();

            if (opcionAcceso == 4) {
                System.out.println("Gracias por visitar Footloose. Hasta luego!");
                programaCorriendo = false;
                continue;
            }

            if (opcionAcceso == 2) {
                registrarse(escaner);
                continue;
            }

            if (!verificarAccesoUsuario(opcionAcceso, escaner)) {
                continue;
            }

            boolean irAlMenuPrincipal = true;
            while (irAlMenuPrincipal) {
                mostrarMenuPrincipal();
                int opcionGenero = escaner.nextInt();
                escaner.nextLine();

                if (opcionGenero == 3) {
                    irAlMenuPrincipal = false;
                    break;
                }

                if (opcionGenero != 1 && opcionGenero != 2) {
                    System.out.println("Opcion incorrecta.");
                    continue;
                }

                int prod = 0;
                if (opcionGenero == 1) {
                    menuVaron();
                    System.out.println("Elija el numero de producto (1-20): ");
                    prod = escaner.nextInt();
                    escaner.nextLine();
                } else {
                    menuMujer();
                    System.out.println("Elija el numero de producto (1-20): ");
                    prod = escaner.nextInt();
                    escaner.nextLine();
                }

                if (prod < 1 || prod > 20) {
                    System.out.println("Producto no existente.");
                    continue;
                }

                String productoSeleccionado = "";
                String colorSeleccionado = "";
                int tallaSeleccionada = 0;

                if (opcionGenero == 1) {
                    productoSeleccionado = obtenerProductoVaron(prod);
                    colorSeleccionado = obtenerColorVaron(prod);
                    tallaSeleccionada = obtenerTallaVaron(prod);
                } else {
                    productoSeleccionado = obtenerProductoMujer(prod);
                    colorSeleccionado = obtenerColorMujer(prod);
                    tallaSeleccionada = obtenerTallaMujer(prod);
                }

                int cantidadPares = pedirCantidad(escaner);

                double totalAPagar = 0;
                if (opcionGenero == 1) {
                    totalAPagar = calcularTotalVaron(prod, cantidadPares);
                } else {
                    totalAPagar = calcularTotalMujer(prod, cantidadPares);
                }

                System.out.println("Total a pagar: S/. " + totalAPagar);

                System.out.println("------------------------------------------");
                System.out.println("        REGISTRO DE DATOS DE CLIENTE       ");
                System.out.println("------------------------------------------");

                System.out.println("Nombre: ");
                String nombreCliente = escaner.nextLine();
                System.out.println("Apellido: ");
                String apellidoCliente = escaner.nextLine();

                String dniCliente = "";
                boolean dniValido = false;
                while (!dniValido) {
                    System.out.println("Documento DNI (Debe tener 8 caracteres) o presione '0' para regresar al menu: ");
                    dniCliente = escaner.nextLine();

                    if (dniCliente.equals("0")) {
                        System.out.println("Registro cancelado. Volviendo al menu...");
                        break;
                    }

                    if (dniCliente.length() == 8) {
                        dniValido = true;
                    } else {
                        System.out.println("[ERROR] El DNI ingresado no es valido (debe tener exactamente 8 caracteres). Intente nuevamente.");
                    }
                }

                if (!dniValido) {
                    continue;
                }

                String telefonoCliente = "";
                boolean telefonoValido = false;
                while (!telefonoValido) {
                    System.out.println("Telefono / Celular (Debe tener 9 caracteres) o presione '0' para regresar al menu: ");
                    telefonoCliente = escaner.nextLine();

                    if (telefonoCliente.equals("0")) {
                        System.out.println("Registro cancelado. Volviendo al menu...");
                        break;
                    }

                    if (telefonoCliente.length() == 9) {
                        telefonoValido = true;
                    } else {
                        System.out.println("[ERROR] El numero de celular no es valido (debe tener exactamente 9 caracteres). Intente nuevamente.");
                    }
                }

                if (!telefonoValido) {
                    continue;
                }

                String tiendaRecojo = seleccionarTienda(escaner);

                String[] correoContenedor = {"No requerido (Pago en Efectivo)"};
                String metodoPagoTexto = procesarFlujoPago(totalAPagar, escaner, correoContenedor);

                if (metodoPagoTexto.isEmpty()) {
                    continue;
                }

                generarBoleta(correoContenedor[0], nombreCliente, apellidoCliente, dniCliente, telefonoCliente,
                        productoSeleccionado, colorSeleccionado, tallaSeleccionada, cantidadPares,
                        totalAPagar, metodoPagoTexto, tiendaRecojo);

                irAlMenuPrincipal = false;
            }
        }
        escaner.close();
    }

    public static void mostrarBienvenida() {
        System.out.println("==========================================");
        System.out.println("             BIENVENIDO A FOOTLOOSE!          ");
        System.out.println("==========================================");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Entrar como Invitado");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opcion: ");
    }

    public static boolean verificarAccesoUsuario(int opcionAcceso, Scanner escaner) {
        switch (opcionAcceso) {
            case 1:
                String usuario = loginUsuario(escaner);
                return !usuario.isEmpty();
            case 3:
                System.out.println("Entrando como Invitado...");
                return true;
            default:
                System.out.println("Opcion no valida. Intente de nuevo.");
                return false;
        }
    }

    public static String loginUsuario(Scanner escaner) {
        String correoIn, passIn;
        boolean ingresoExitoso = false;
        String nombre = " Prueba ";
        System.out.println(" -----INICIAR SESION------");
        do {
            System.out.println("Ingrese su correo ");
            correoIn = escaner.nextLine();
            System.out.println("Ingrese su contraseña");
            passIn = escaner.nextLine();
            boolean estructuraCorreoOk = correoIn.contains("@") && correoIn.endsWith(".com");
            boolean structurePassOk = passIn.length() == 8;

            if (!estructuraCorreoOk || !structurePassOk) {
                System.out.println("Error, los datos no cumplen requisitos:");
                if (!estructuraCorreoOk) {
                    System.out.println(" El correo debe contener @ y terminar en .com ");
                }
                if (!structurePassOk) {
                    System.out.println("La contraseña debe tener exactamente 8 caracteres.");
                }
            } else if (correoIn.equals("admin@gmail.com") && passIn.equals("12345678")) {
                System.out.println("Ingreso exitoso a Footloose");
                ingresoExitoso = true;
                System.out.println("Bienvenido " + nombre);
            } else {
                System.out.println("Credenciales no válidas");
            }

            if (!ingresoExitoso) {
                System.out.println("¿Desea cancelar el inicio de sesion (S/N)?");
                String cancelar = escaner.nextLine();
                if (cancelar.equalsIgnoreCase("S")) {
                    nombre = "";
                    break;
                }
            }
        } while (!ingresoExitoso);
        return nombre;
    }

    public static void registrarse(Scanner escaner) {
        System.out.println("--- REGISTRARSE ---");
        String correo = "";
        boolean correoValido = false;

        while (!correoValido) {
            System.out.println("Cree su correo de usuario (Debe contener '@' y terminar en '.com'): ");
            correo = escaner.nextLine();
            if (correo.contains("@") && correo.endsWith(".com")) {
                correoValido = true;
            } else {
                System.out.println("[ERROR] El correo debe contener '@' y terminar en '.com'. Intente nuevamente.");
            }
        }

        String pass = "";
        boolean passValido = false;
        while (!passValido) {
            System.out.println("Cree su contrasena (Exactamente 8 caracteres): ");
            pass = escaner.nextLine();
            if (pass.length() == 8) {
                passValido = true;
            } else {
                System.out.println("[ERROR] La contrasena debe tener exactamente 8 caracteres. Intente nuevamente.");
            }
        }

        System.out.println("Registro completado con exito! Ahora puede iniciar sesion.");
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("==========================================");
        System.out.println("                  MENU PRINCIPAL               ");
        System.out.println("==========================================");
        System.out.println("1. Seccion Varon");
        System.out.println("2. Seccion Mujer");
        System.out.println("3. Regresar al Menu de Acceso / Salir");
        System.out.println("Seleccione el genero que desea explorar: ");
    }

    public static void menuVaron() {
        System.out.println("------ PRODUCTOS VARON ------");
        System.out.println("-------  ZAPATILLAS  ---------");
        System.out.println("1. R18 Hombres R18-Xw00245 | Gris | T41 | S/. 167.90 [Lleva 2 y obtén 30% desc.]");
        System.out.println("2. Puma Caven III | Blanco total | T42 | S/. 229.00");
        System.out.println("3. Puma Transport | Negro/suela blanca | T41 | S/. 189.00");
        System.out.println("4. Puma Jada Classic Sd | Beige | T40 | S/. 199.00");
        System.out.println("5. Puma Blaze Lite Nbk | Negro | T42 | S/. 199.00");
        System.out.println("6. Skechers Bobs Squad 4 | Blanco | T41 | S/. 279.00");
        System.out.println("7. Skechers Uno Savvy | Blanco | T42 | S/. 249.00");
        System.out.println("8. Puma Court Lally Metallic | Blanco | T40 | S/. 179.00");
        System.out.println("9. Puma Carina 3 | Negro | T42 | S/. 229.00");
        System.out.println("10. Skechers Bobs Arch | Negro | T41 | S/. 239.00");
        System.out.println("-------  ZAPATOS  ---------");
        System.out.println("11. Renzo Renzini Rrz-Wo0001 | Marron oscuro | T41 | S/. 135.90 [Lleva 2 y obtén 20% desc.]");
        System.out.println("12. Renzo Renzini Rrz-Cf00006 | Azul oscuro | T42 | S/. 249.90");
        System.out.println("13. Renzo Renzini Rrz-Cf00002 | Azul | T41 | S/. 249.90");
        System.out.println("14. Renzo Renzini Rrz-Cf00004 | Marron | T43 | S/. 249.90");
        System.out.println("15. Renzo Renzini Rrz-Fv00003 | Chocolate | T42 | S/. 249.90");
        System.out.println("16. Renzo Renzini Rrz-Jl00002 | Negro | T41 | S/. 249.90");
        System.out.println("17. Renzo Renzini Rrz-Lk00001 | Camel | T42 | S/. 249.90");
        System.out.println("18. Renzo Renzini Rrz-Cc00004 | Marron oscuro | T43 | S/. 229.90");
        System.out.println("19. Renzo Renzini Rfv-002 | Marron claro | T42 | S/. 239.90");
        System.out.println("20. Renzo Renzini Rrz-Jq00004 | Azul oscuro | T41 | S/. 199.90");
    }

    public static void menuMujer() {
        System.out.println("------ PRODUCTOS MUJER ------");
        System.out.println("-------  ZAPATILLAS  ---------");
        System.out.println("1. R18 Mujeres R18-Xw00245 | Gris | T37 | S/. 167.90 [Lleva 2  y obtén 30% desc.]");
        System.out.println("2. Puma Caven III Wns | Blanco total | T38 | S/. 229.00");
        System.out.println("3. Puma Transport | Negro/suela blanca | T37 | S/. 189.00");
        System.out.println("4. Puma Jada Classic Sd | Beige | T36 | S/. 199.00");
        System.out.println("5. Puma Blaze Lite Nbk Wns | Negro | T38 | S/. 199.00");
        System.out.println("6. Skechers Bobs Squad 4 | Blanco | T37 | S/. 279.00");
        System.out.println("7. Skechers Uno Savvy | Blanco | T38 | S/. 249.00");
        System.out.println("8. Puma Court Lally Metallic | Blanco | T36 | S/. 179.00");
        System.out.println("9. Puma Carina 3 | Negro | T38 | S/. 229.00");
        System.out.println("10. Skechers Bobs Arch | Negro | T37 | S/. 239.00");
        System.out.println("-------  ZAPATOS  ---------");
        System.out.println("11. Top Model Tmo-N0013 | Negro | T36 | S/. 109.90 [Lleva 2 y obtén 50% desc.]");
        System.out.println("12. Vizzano 6428.101.7286 | Nude | T37 | S/. 139.90");
        System.out.println("13. Footloose Ftl-Yz00077 | Negro | T38 | S/. 99.90");
        System.out.println("14. Footloose Ftl-Yq00007 | Negro gamuza | T36 | S/. 79.90");
        System.out.println("15. Footloose Ftl-I0025 | Dorado | T37 | S/. 69.90");
        System.out.println("16. Footloose Ftl-Yz00078 | Negro track | T38 | S/. 149.90");
        System.out.println("17. Footloose Ftl-Yq00008 | Animal print | T36 | S/. 69.90");
        System.out.println("18. Footloose Ftl-Rs00015 | Negro textura | T37 | S/. 129.90");
        System.out.println("19. Footloose Ftl-I0041 | Nude oscuro | T38 | S/. 99.00");
        System.out.println("20. Footloose Ftl-I0026 | Crema | T36 | S/. 79.90");
    }

    public static String obtenerProductoVaron(int opcion) {
        switch (opcion) {
            case 1: return "R18 Hombres R18-Xw00245";
            case 2: return "Puma Caven III";
            case 3: return "Puma Transport";
            case 4: return "Puma Jada Classic Sd";
            case 5: return "Puma Blaze Lite Nbk";
            case 6: return "Skechers Bobs Squad 4";
            case 7: return "Skechers Uno Savvy";
            case 8: return "Puma Court Lally Metallic";
            case 9: return "Puma Carina 3";
            case 10: return "Skechers Bobs Arch";
            case 11: return "Renzo Renzini Rrz-Wo0001";
            case 12: return "Renzo Renzini Rrz-Cf00006";
            case 13: return "Renzo Renzini Rrz-Cf00002";
            case 14: return "Renzo Renzini Rrz-Cf00004";
            case 15: return "Renzo Renzini Rrz-Fv00003";
            case 16: return "Renzo Renzini Rrz-Jl00002";
            case 17: return "Renzo Renzini Rrz-Lk00001";
            case 18: return "Renzo Renzini Rrz-Cc00004";
            case 19: return "Renzo Renzini Rfv-002";
            case 20: return "Renzo Renzini Rrz-Jq00004";
            default: return "";
        }
    }

    public static String obtenerProductoMujer(int opcion) {
        switch (opcion) {
            case 1: return "R18 Mujeres R18-Xw00245";
            case 2: return "Puma Caven III Wns";
            case 3: return "Puma Transport";
            case 4: return "Puma Jada Classic Sd";
            case 5: return "Puma Blaze Lite Nbk Wns";
            case 6: return "Skechers Bobs Squad 4";
            case 7: return "Skechers Uno Savvy";
            case 8: return "Puma Court Lally Metallic";
            case 9: return "Puma Carina 3";
            case 10: return "Skechers Bobs Arch";
            case 11: return "Top Model Tmo-N0013";
            case 12: return "Vizzano 6428.101.7286";
            case 13: return "Footloose Ftl-Yz00077";
            case 14: return "Footloose Ftl-Yq00007";
            case 15: return "Footloose Ftl-I0025";
            case 16: return "Footloose Ftl-Yz00078";
            case 17: return "Footloose Ftl-Yq00008";
            case 18: return "Footloose Ftl-Rs00015";
            case 19: return "Footloose Ftl-I0041";
            case 20: return "Footloose Ftl-I0026";
            default: return "";
        }
    }

    public static String obtenerColorVaron(int opcion) {
        switch (opcion) {
            case 1: return "Gris"; case 2: return "Blanco total"; case 3: return "Negro/suela blanca";
            case 4: return "Beige"; case 5: return "Negro"; case 6: return "Blanco";
            case 7: return "Blanco"; case 8: return "Blanco"; case 9: return "Negro";
            case 10: return "Negro"; case 11: return "Marron oscuro"; case 12: return "Azul oscuro";
            case 13: return "Azul"; case 14: return "Marron"; case 15: return "Chocolate";
            case 16: return "Negro"; case 17: return "Camel"; case 18: return "Marron oscuro";
            case 19: return "Marron claro"; case 20: return "Azul oscuro"; default: return "";
        }
    }

    public static int obtenerTallaVaron(int opcion) {
        switch (opcion) {
            case 1: case 3: case 6: case 10: case 11: case 13: case 16: case 20: return 41;
            case 2: case 5: case 7: case 9: case 12: case 15: case 17: case 19: return 42;
            case 4: case 8: return 40;
            case 14: case 18: return 43;
            default: return 0;
        }
    }

    public static String obtenerColorMujer(int opcion) {
        switch (opcion) {
            case 1: return "Gris"; case 2: return "Blanco total"; case 3: return "Negro/suela blanca";
            case 4: return "Beige"; case 5: return "Negro"; case 6: return "Blanco";
            case 7: return "Blanco"; case 8: return "Blanco"; case 9: return "Negro";
            case 10: return "Negro"; case 11: return "Negro"; case 12: return "Nude";
            case 13: return "Negro"; case 14: return "Negro gamuza"; case 15: return "Dorado";
            case 16: return "Negro track"; case 17: return "Animal print"; case 18: return "Negro textura";
            case 19: return "Nude oscuro"; case 20: return "Crema"; default: return "";
        }
    }

    public static int obtenerTallaMujer(int opcion) {
        switch (opcion) {
            case 1: case 3: case 6: case 10: case 12: case 15: case 18: return 37;
            case 2: case 5: case 7: case 9: case 13: case 16: case 19: return 38;
            case 4: case 8: case 11: case 14: case 17: case 20: return 36;
            default: return 0;
        }
    }

    public static double calcularTotalVaron(int opcion, int cantidad) {
        if (cantidad <= 0) return 0;
        double total = 0;
        switch (opcion) {
            case 1:
                if (cantidad >= 2) {
                    total = ((167.90 * 2) * 0.70) + (167.90 * (cantidad - 2));
                    System.out.println("[PROMO] Se aplicó un 30% de descuento solo a los 2 primeros pares.");
                } else {
                    total = 167.90 * cantidad;
                }
                break;
            case 2: total = 229.00 * cantidad; break;
            case 3: total = 189.00 * cantidad; break;
            case 4: total = 199.00 * cantidad; break;
            case 5: total = 199.00 * cantidad; break;
            case 6: total = 279.00 * cantidad; break;
            case 7: total = 249.00 * cantidad; break;
            case 8: total = 179.00 * cantidad; break;
            case 9: total = 229.00 * cantidad; break;
            case 10: total = 239.00 * cantidad; break;
            case 11:
                if (cantidad >= 2) {
                    total = ((135.90 * 2) * 0.80) + (135.90 * (cantidad - 2));
                    System.out.println("[PROMO] Se aplicó un 20% de descuento solo a los 2 primeros pares.");
                } else {
                    total = 135.90 * cantidad;
                }
                break;
            case 12: total = 249.90 * cantidad; break;
            case 13: total = 249.90 * cantidad; break;
            case 14: total = 249.90 * cantidad; break;
            case 15: total = 249.90 * cantidad; break;
            case 16: total = 249.90 * cantidad; break;
            case 17: total = 249.90 * cantidad; break;
            case 18: total = 229.90 * cantidad; break;
            case 19: total = 239.90 * cantidad; break;
            case 20: total = 199.90 * cantidad; break;
            default: total = 0;
        }
        return total;
    }

    public static double calcularTotalMujer(int opcion, int cantidad) {
        if (cantidad <= 0) return 0;
        double total = 0;
        switch (opcion) {
            case 1:
                if (cantidad >= 2) {
                    total = ((167.90 * 2) * 0.70) + (167.90 * (cantidad - 2));
                    System.out.println("[PROMO] Se aplicó un 30% de descuento solo a los 2 primeros pares.");
                } else {
                    total = 167.90 * cantidad;
                }
                break;
            case 2: total = 229.00 * cantidad; break;
            case 3: total = 189.00 * cantidad; break;
            case 4: total = 199.00 * cantidad; break;
            case 5: total = 199.00 * cantidad; break;
            case 6: total = 279.00 * cantidad; break;
            case 7: total = 249.00 * cantidad; break;
            case 8: total = 179.00 * cantidad; break;
            case 9: total = 229.00 * cantidad; break;
            case 10: total = 239.00 * cantidad; break;
            case 11:
                if (cantidad >= 2) {
                    total = ((109.90 * 2) * 0.50) + (109.90 * (cantidad - 2));
                    System.out.println("[PROMO] Se aplicó un 50% de descuento solo a los 2 primeros pares.");
                } else {
                    total = 109.90 * cantidad;
                }
                break;
            case 12: total = 139.90 * cantidad; break;
            case 13: total = 99.90 * cantidad; break;
            case 14: total = 79.90 * cantidad; break;
            case 15: total = 69.90 * cantidad; break;
            case 16: total = 149.90 * cantidad; break;
            case 17: total = 69.90 * cantidad; break;
            case 18: total = 129.90 * cantidad; break;
            case 19: total = 99.00 * cantidad; break;
            case 20: total = 79.90 * cantidad; break;
            default: total = 0;
        }
        return total;
    }

    public static int pedirCantidad(Scanner escaner) {
        System.out.println("Ingrese la cantidad de pares que desea llevar: ");
        int cantidad = escaner.nextInt();
        escaner.nextLine();
        return cantidad;
    }

    public static String seleccionarTienda(Scanner escaner) {
        System.out.println("--- SELECCIONE LA TIENDA DE RECOJO ---");
        System.out.println("1. Tienda Real Plaza Juliaca (Av. Nueva Zelanda)");
        System.out.println("2. Tienda Jiron Huancane (Centro de Juliaca)");
        System.out.println("3. Tienda Real Plaza Arequipa (Av. Ejercito)");
        System.out.println("4. Tienda Mall Plaza Bellavista (Callao - Lima)");
        System.out.println("5. Tienda Plaza Norte (Independencia - Lima)");
        System.out.println("Seleccione una tienda (1-5): ");
        int opcion = escaner.nextInt();
        escaner.nextLine();
        switch (opcion) {
            case 1: return "Tienda Real Plaza Juliaca (Puno)";
            case 2: return "Tienda Jiron Huancane (Juliaca - Puno)";
            case 3: return "Tienda Real Plaza Arequipa (Arequipa)";
            case 4: return "Tienda Mall Plaza Bellavista (Callao)";
            case 5: return "Tienda Plaza Norte (Independencia - Lima)";
            default: return "Tienda Central Footloose (Lima)";
        }
    }

    public static String procesarFlujoPago(double total, Scanner escaner, String[] correoContenedor) {
        System.out.println("--- SELECCIONE METODO DE PAGO ---");
        System.out.println("1. Tarjeta de Credito");
        System.out.println("2. En Efectivo");
        System.out.println("Seleccione su metodo de pago: ");
        int metodo = escaner.nextInt();
        escaner.nextLine();

        if (metodo == 1) {
            validaPagoTarjeta(escaner, total, correoContenedor);
            return "Tarjeta de Credito";
        } else if (metodo == 2) {
            if (pagarEnEfectivo(total, escaner)) {
                return "Efectivo";
            }
        } else {
            System.out.println("Metodo de pago invalido. Operacion cancelada.");
        }
        return "";
    }

    public static void validaPagoTarjeta(Scanner escaner, double total, String[] correoContenedor) {
        String nroTarjeta, fechaVencimiento, cvv, correoIn;
        boolean pAprobado = false;
        System.out.println("Monto total a pagar con tarjeta es: S/. " + total);

        boolean correoOk = false;
        do {
            System.out.println("Ingrese su correo electronico para el envio de la boleta (Debe contener '@' y terminar en '.com'):");
            correoIn = escaner.nextLine();
            if (correoIn.contains("@") && correoIn.endsWith(".com")) {
                correoOk = true;
                correoContenedor[0] = correoIn;
            } else {
                System.out.println("[ERROR] Formato incorrecto. El correo debe contener '@' y terminar en '.com'");
            }
        } while (!correoOk);

        do {
            System.out.println("Ingrese los 16 digitos de la tarjeta");
            nroTarjeta = escaner.nextLine();
            System.out.println("Ingrese la fecha de caducidad (MM/AA) ");
            fechaVencimiento = escaner.nextLine();
            System.out.println("Ingrese el código de seguridad CVV");
            cvv = escaner.nextLine();

            boolean tarjetaOk = (nroTarjeta.length() == 16);
            boolean cvvOk = (cvv.length() == 3);
            boolean fechaEstructuraOk = (fechaVencimiento.length() == 5 && fechaVencimiento.contains("/"));
            boolean fechaNoVencida = false;

            if (fechaEstructuraOk) {
                try {
                    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("MM/yy");
                    YearMonth fechaTarjeta = YearMonth.parse(fechaVencimiento, formateador);
                    YearMonth fechaActual = YearMonth.now();
                    if (fechaTarjeta.isAfter(fechaActual) || fechaTarjeta.equals(fechaActual)) {
                        fechaNoVencida = true;
                    }
                } catch (DateTimeParseException e) {
                    fechaEstructuraOk = false;
                }
            }

            if (tarjetaOk && fechaEstructuraOk && fechaNoVencida && cvvOk) {
                System.out.println(" Autorizando fondos... transaccion exitosa");
                pAprobado = true;
            } else {
                System.out.println("Operación denegada por la pasarela bancaria");

                if (!tarjetaOk) {
                    System.out.println("El número de tarjeta debe contener exactamente  16 dígitos");
                }
                if (!fechaEstructuraOk) {
                    System.out.println("Formato de fecha incorrecto");
                }
                if (fechaEstructuraOk && !fechaNoVencida) {
                    System.out.println("Tarjeta caducada");
                }
                if (!cvvOk) {
                    System.out.println("El CVV debe contener 3 dígitos");
                }
                System.out.println("Intente nuevamente el pago electrónico");
            }

        } while (!pAprobado);
    }

    public static boolean pagarEnEfectivo(double totalAPagar, Scanner escaner) {
        System.out.println("--- PAGO EN EFECTIVO ---");
        System.out.println("Monto requerido: S/. " + totalAPagar);
        System.out.println("Ingrese la cantidad de dinero con la que va a pagar: S/. ");
        double efectivo = escaner.nextDouble();
        escaner.nextLine();

        if (efectivo < totalAPagar) {
            System.out.println("[ERROR] El dinero ingresado es insuficiente.");
            System.out.println("Cancelando transaccion y regresando al Menu Principal...");
            return false;
        } else {
            double vuelto = efectivo - totalAPagar;
            System.out.println("Pago aceptado. Su vuelto es: S/. " + vuelto);
            return true;
        }
    }

    public static void generarBoleta(String correo, String nombre, String apellido, String dni, String telefono,
                                     String producto, String color, int talla, int cantidad, double total,
                                     String metodoPago, String tienda) {

        double subtotal = total / 1.18;
        double igv = total - subtotal;

        System.out.println("==================================================");
        System.out.println("                BOLETA DE VENTA DIGITAL             ");
        System.out.println("          COMERCIAL FOOTLOOSE PERU S.A.C.         ");
        System.out.println("                RUC: 20511378491                  ");
        System.out.println("==================================================");
        System.out.println("CLIENTE: " + nombre + " " + apellido);
        System.out.println("DNI: " + dni + "     TELEFONO: " + telefono);
        System.out.println("CORREO: " + correo);
        System.out.println("--------------------------------------------------");
        System.out.println("LUGAR DE RECOJO: " + tienda);
        System.out.println("--------------------------------------------------");
        System.out.println("DETALLE DE COMPRA:");
        System.out.println("Producto: " + producto);
        System.out.println("Color: " + color + " | Talla: " + talla);
        System.out.println("Cantidad: " + cantidad + " pares");
        System.out.println("--------------------------------------------------");
        System.out.println("Subtotal: S/. " + subtotal);
        System.out.println();
        System.out.println("IGV (18%%): S/. " + igv);
        System.out.println();
        System.out.println("TOTAL PAGADO: S/. " + total);
        System.out.println();
        System.out.println("METODO DE PAGO: " + metodoPago);
        System.out.println("==================================================");
        System.out.println("      Gracias por tu compra en Footloose!        ");
        System.out.println("==================================================");
    }
}
