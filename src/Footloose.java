import java.util.Scanner;

public class Footloose {

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

                String[] datosProducto = seleccionarProducto(opcionGenero, escaner);
                if (datosProducto == null) continue;

                String productoSeleccionado = datosProducto[0];
                String colorSeleccionado    = datosProducto[1];
                int tallaSeleccionada       = Integer.parseInt(datosProducto[2]);
                double precioSeleccionado   = Double.parseDouble(datosProducto[3]);
                String tieneDescuento       = datosProducto[4]; 

                int cantidadPares = pedirCantidad(escaner);
                
                double totalAPagar = calcularMontoTotal(precioSeleccionado, cantidadPares, tieneDescuento);
                System.out.println("Total a pagar: S/. " + totalAPagar);

                String[] datosCliente = registrarDatosCliente(escaner);
                if (datosCliente == null) {
                    continue; 
                }

                String tiendaRecojo = seleccionarTienda(escaner);

                String metodoPagoTexto = procesarFlujoPago(totalAPagar, escaner);
                
                if (metodoPagoTexto.isEmpty()) {
                    continue;
                }

                generarBoleta(datosCliente, productoSeleccionado, colorSeleccionado,
                        tallaSeleccionada, cantidadPares, totalAPagar,
                        metodoPagoTexto, tiendaRecojo);

                irAlMenuPrincipal = false;
            }
        }
        escaner.close();
    }

    public static void mostrarBienvenida() {
        System.out.println("==========================================");
        System.out.println("         BIENVENIDO A FOOTLOOSE!          ");
        System.out.println("==========================================");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Entrar como Invitado");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opcion: ");
    }

    public static boolean procesarAcceso(int opcion, Scanner escaner) {
        switch (opcion) {
            case 1:
                return iniciarSesion(escaner);
            case 3:
                System.out.println("Entrando como Invitado...");
                return true;
            default:
                System.out.println("Opcion no valida. Intente de nuevo.");
                return false;
        }
    }

    public static boolean verificarAccesoUsuario(int opcionAcceso, Scanner escaner) {
        boolean accesoOk = procesarAcceso(opcionAcceso, escaner);
        if (!accesoOk) {
            return false;
        }
        return true;
    }

    public static boolean iniciarSesion(Scanner escaner) {
        System.out.println("--- INICIAR SESION ---");
        System.out.println("Ingrese correo: ");
        String correo = escaner.nextLine();
        System.out.println("Ingrese contrasena: ");
        String pass = escaner.nextLine();
        if (!correo.isEmpty() && !pass.isEmpty()) {
            System.out.println("Inicio de sesion exitoso!");
            return true;
        } else {
            System.out.println("Error: Credenciales incorrectas.");
            return false;
        }
    }

    public static void registrarse(Scanner escaner) {
        System.out.println("--- REGISTRARSE ---");
        System.out.println("Cree su correo de usuario: ");
        String correo = escaner.nextLine(); 
        System.out.println("Cree su contrasena: ");
        String pass = escaner.nextLine();   
        System.out.println("Registro completado con exito! Ahora puede iniciar sesion.");
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("==========================================");
        System.out.println("              MENU PRINCIPAL               ");
        System.out.println("==========================================");
        System.out.println("1. Seccion Varon");
        System.out.println("2. Seccion Mujer");
        System.out.println("3. Regresar al Menu de Acceso / Salir");
        System.out.println("Seleccione el genero que desea explorar: ");
    }

    public static String[] seleccionarProducto(int genero, Scanner escaner) {
        if (genero == 1) {
            return mostrarCatalogoVaron(escaner);
        } else {
            return mostrarCatalogoMujer(escaner);
        }
    }

    public static String[] mostrarCatalogoVaron(Scanner escaner) {
        System.out.println("--- CATALOGO DE VARON (TALLAS UNICAS) ---");
        System.out.println("1.  Zapatilla Adidas R18        | Color: Azul       | Talla: 41 | Precio: S/. 189.90");
        System.out.println("2.  Zapatilla Puma Street       | Color: Negro      | Talla: 41 | Precio: S/. 159.90");
        System.out.println("3.  Zapatilla Nike Runner       | Color: Anaranjado | Talla: 41 | Precio: S/. 219.90");
        System.out.println("4.  Zapatilla Footloose Urban   | Color: Beige      | Talla: 41 | Precio: S/. 129.90");
        System.out.println("5.  Zapato Casual Cat           | Color: Marron     | Talla: 42 | Precio: S/. 249.90");
        System.out.println("6.  Zapato Casual Calimod       | Color: Negro      | Talla: 42 | Precio: S/. 179.90");
        System.out.println("7.  Zapato Casual Footloose     | Color: Azul       | Talla: 42 | Precio: S/. 139.90");
        System.out.println("8.  Zapatilla R18 Sport         | Color: Varios     | Talla: 41 | Precio: S/. 149.90");
        System.out.println("9.  Zapato Casual Bruno Ferrini | Color: Beige      | Talla: 42 | Precio: S/. 299.90");
        System.out.println("10. Zapatilla Stand Urban       | Color: Blanco     | Talla: 41 | Precio: S/. 119.90");
        System.out.println("11. [PROMO] Botas Timberland    | Color: Camel      | Talla: 42 | Precio: S/. 349.90 (15% DSCTO COMPRANDO 1 PAR)");
        System.out.println("Elija el numero de producto (1-11): ");
        int prod = escaner.nextInt();
        escaner.nextLine(); 

        switch (prod) {
            case 1:  return new String[]{"Zapatilla Adidas R18",        "Azul",        "41", "189.90", "NO"};
            case 2:  return new String[]{"Zapatilla Puma Street",       "Negro",       "41", "159.90", "NO"};
            case 3:  return new String[]{"Zapatilla Nike Runner",       "Anaranjado",  "41", "219.90", "NO"};
            case 4:  return new String[]{"Zapatilla Footloose Urban",   "Beige",       "41", "129.90", "NO"};
            case 5:  return new String[]{"Zapato Casual Cat",           "Marron",      "42", "249.90", "NO"};
            case 6:  return new String[]{"Zapato Casual Calimod",       "Negro",       "42", "179.90", "NO"};
            case 7:  return new String[]{"Zapato Casual Footloose",     "Azul",        "42", "139.90", "NO"};
            case 8:  return new String[]{"Zapatilla R18 Sport",         "Varios",      "42", "149.90", "NO"};
            case 9:  return new String[]{"Zapato Casual Bruno Ferrini", "Beige",       "42", "299.90", "NO"};
            case 10: return new String[]{"Zapatilla Stand Urban",       "Blanco",      "41", "119.90", "NO"};
            case 11: return new String[]{"Botas Timberland",            "Camel",       "42", "349.90", "SI"};
            default: System.out.println("Producto no existente."); return null;
        }
    }

    public static String[] mostrarCatalogoMujer(Scanner escaner) {
        System.out.println("--- CATALOGO DE MUJER (TALLAS UNICAS) ---");
        System.out.println("1.  Zapatilla Adidas R18       | Color: Trans-Blanco | Talla: 37 | Precio: S/. 179.90");
        System.out.println("2.  Zapatilla Puma Nova        | Color: Anaranjado   | Talla: 37 | Precio: S/. 169.90");
        System.out.println("3.  Zapatilla Footloose Active | Color: Azul         | Talla: 37 | Precio: S/. 129.90");
        System.out.println("4.  Zapatilla R18 Street       | Color: Varios       | Talla: 37 | Precio: S/. 139.90");
        System.out.println("5.  Zapato Casual Vizzano      | Color: Beige        | Talla: 38 | Precio: S/. 159.90");
        System.out.println("6.  Zapato Casual Moleca       | Color: Negro        | Talla: 38 | Precio: S/.  99.90");
        System.out.println("7.  Zapato Casual Footloose    | Color: Marron       | Talla: 38 | Precio: S/. 119.90");
        System.out.println("8.  Zapatilla Nike Air         | Color: Blanco       | Talla: 37 | Precio: S/. 259.90");
        System.out.println("9.  Zapato Casual Beira Rio    | Color: Azul         | Talla: 38 | Precio: S/. 109.90");
        System.out.println("10. Zapato Casual Lady         | Color: Beige        | Talla: 38 | Precio: S/. 149.90");
        System.out.println("11. [PROMO] Tacones Gacela     | Color: Rojo         | Talla: 36 | Precio: S/. 199.90 (15% DSCTO COMPRANDO 1 PAR)");
        System.out.println("Elija el numero de producto (1-11): ");
        int prod = escaner.nextInt();
        escaner.nextLine(); 

        switch (prod) {
            case 1:  return new String[]{"Zapatilla Adidas R18",       "Trans-Blanco", "37", "179.90", "NO"};
            case 2:  return new String[]{"Zapatilla Puma Nova",        "Anaranjado",   "37", "169.90", "NO"};
            case 3:  return new String[]{"Zapatilla Footloose Active", "Azul",         "37", "129.90", "NO"};
            case 4:  return new String[]{"Zapatilla R18 Street",       "Varios",       "37", "139.90", "NO"};
            case 5:  return new String[]{"Zapato Casual Vizzano",      "Beige",        "38", "159.90", "NO"};
            case 6:  return new String[]{"Zapato Casual Moleca",       "Negro",        "38",  "99.90", "NO"};
            case 7:  return new String[]{"Zapato Casual Footloose",    "Marron",       "38", "119.90", "NO"};
            case 8:  return new String[]{"Zapatilla Nike Air",         "Blanco",       "37", "259.90", "NO"};
            case 9:  return new String[]{"Zapato Casual Beira Rio",    "Azul",         "38", "109.90", "NO"};
            case 10: return new String[]{"Zapato Casual Lady",         "Beige",        "38", "149.90", "NO"};
            case 11: return new String[]{"Tacones Gacela",             "Rojo",         "36", "199.90", "SI"};
            default: System.out.println("Producto no existente."); return null;
        }
    }

    public static int pedirCantidad(Scanner escaner) {
        System.out.println("Ingrese la cantidad de pares que desea llevar: ");
        int cantidad = escaner.nextInt();
        escaner.nextLine(); 
        return cantidad;
    }

    public static double calcularMontoTotal(double precio, int cantidad, String tienePromo) {
        double subtotalCalculado = precio * cantidad;
        if (tienePromo.equals("SI") && cantidad == 1) {
            double rebaja = subtotalCalculado * 0.15; 
            subtotalCalculado = subtotalCalculado - rebaja;
            System.out.println("[PROMO] ¡Se aplico un 15% de descuento por la compra de 1 par!");
        }
        return subtotalCalculado;
    }

    public static String[] registrarDatosCliente(Scanner escaner) {
        System.out.println("------------------------------------------");
        System.out.println("       REGISTRO DE DATOS DE CLIENTE       ");
        System.out.println("------------------------------------------");
        System.out.println("Correo electronico: ");
        String correo = escaner.nextLine();
        System.out.println("Nombre: ");
        String nombre = escaner.nextLine();
        System.out.println("Apellido: ");
        String apellido = escaner.nextLine();
        
        String dni = "";
        boolean dniValido = false;
        while (!dniValido) {
            System.out.println("Documento DNI (Debe tener 8 digitos) o presione '0' para regresar al menu: ");
            dni = escaner.nextLine();
            
            if (dni.equals("0")) {
                System.out.println("\n[!] Registro cancelado. Volviendo al menu...\n");
                return null; 
            }
            
            if (dni.length() == 8) {
                dniValido = true;
            } else {
                System.out.println("[ERROR] El DNI ingresado no es valido. Intente nuevamente.");
            }
        }
        
        System.out.println("Telefono: ");
        String telefono = escaner.nextLine();
        return new String[]{correo, nombre, apellido, dni, telefono};
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

    public static String procesarFlujoPago(double total, Scanner escaner) {
        System.out.println("--- SELECCIONE METODO DE PAGO ---");
        System.out.println("1. Tarjeta de Credito");
        System.out.println("2. En Efectivo");
        System.out.println("Seleccione su metodo de pago: ");
        int metodo = escaner.nextInt();
        escaner.nextLine(); 

        if (metodo == 1) {
            if (pagarConTarjeta(escaner)) {
                return "Tarjeta de Credito";
            }
        } else if (metodo == 2) {
            if (pagarEnEfectivo(total, escaner)) {
                return "Efectivo";
            }
        } else {
            System.out.println("Metodo de pago invalido. Operacion cancelada.");
        }
        return ""; 
    }

    public static boolean pagarConTarjeta(Scanner escaner) {
        System.out.println("--- PASARELA DE TARJETA ---");
        System.out.println("Ingrese el numero de tarjeta (16 digitos): ");
        escaner.nextLine();
        System.out.println("Ingrese fecha de vencimiento (MM/AA): ");
        escaner.nextLine();
        System.out.println("Ingrese codigo de seguridad CVV: ");
        escaner.nextLine();
        System.out.println("Ingrese nombre completo del titular: ");
        escaner.nextLine();
        System.out.println("Procesando pago con tarjeta... Autorizado exitosamente!");
        return true;
    }

    public static boolean pagarEnEfectivo(double totalAPagar, Scanner escaner) {
        System.out.println("--- PAGO EN EFECTIVO ---");
        System.out.println("Monto requerido: S/. " + totalAPagar);
        System.out.println("Ingrese la cantidad de dinero con la que va a pagar: S/. ");
        double efectivo = escaner.nextDouble();
        escaner.nextLine(); 
        
        if (efectivo < totalAPagar) {
            System.out.println("[ERROR] El dinero ingresado es insuficiente.");
            System.out.println("Cancelando transaccion y regresando al Menu Principal...\n");
            return false;
        } else {
            double vuelto = efectivo - totalAPagar;
            System.out.println("Pago aceptado. Su vuelto es: S/. " + vuelto);
            return true;
        }
    }

    public static void generarBoleta(String[] datosCliente, String producto, String color,
                                     int talla, int cantidad, double total,
                                     String metodoPago, String tienda) {
        String correo   = datosCliente[0];
        String nombre   = datosCliente[1];
        String apellido = datosCliente[2];
        String dni      = datosCliente[3];
        String telefono = datosCliente[4];

        double igv      = total * 0.18;
        double subtotal = total - igv;

        System.out.println("==================================================");
        System.out.println("              BOLETA DE VENTA DIGITAL             ");
        System.out.println("          COMERCIAL FOOTLOOSE PERU S.A.C.         ");
        System.out.println("                RUC: 20511378491                  ");
        System.out.println("==================================================");
        System.out.println("CLIENTE: " + nombre.toUpperCase() + " " + apellido.toUpperCase());
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
        System.out.println("IGV (18%): S/. " + igv);
        System.out.println("TOTAL PAGADO: S/. " + total);
        System.out.println("METODO DE PAGO: " + metodoPago);
        System.out.println("==================================================");
        System.out.println("      Gracias por tu compra en Footloose!        ");
        System.out.println("==================================================");
    }
}
