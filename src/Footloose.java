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
            System.out.println("----- REGISTRO DE CLIENTE -----");
            System.out.println("Ingrese su correo:");
            String correo = escaner.nextLine();
            System.out.println("Ingrese su nombre:");
            String nombre = escaner.nextLine();
            System.out.println("Ingrese su apellido:");
            String apellido = escaner.nextLine();
            System.out.println("Ingrese su DNI:");
            String dni = escaner.nextLine();
            System.out.println("Ingrese su telefono:");
            String telefono = escaner.nextLine();

            metodoEntrega();
            int opcionEntrega = escaner.nextInt();
            escaner.nextLine();

            String entrega = "";
            if (opcionEntrega == 1) {
                entrega = "Lima";
            } else if (opcionEntrega == 2) {
                entrega = "Arequipa";
            } else if (opcionEntrega == 3) {
                entrega = "Cusco";
            } else if (opcionEntrega == 4) {
                entrega = "Trujillo";
            } else if (opcionEntrega == 5) {
                entrega = "Piura";
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

    public static String obtenerProductoVaron(int opcion) {
        switch (opcion) {
            case 1: return "Zapatilla sport";
            case 2: return "Zapatilla running";
            case 3: return "Zapatilla casual";
            case 4: return "Zapato urbano";
            case 5: return "Zapatilla promo 2 pares";
            case 6: return "Zapato formal";
            case 7: return "Zapatilla trekking";
            case 8: return "Zapato casual";
            case 9: return "Zapatilla urban pro";
            case 10: return "Zapato escolar";
            case 11: return "Zapatilla basica";
            case 12: return "Zapato promo 2 pares";
            case 13: return "Zapatilla street";
            case 14: return "Zapato modelo 14";
            case 15: return "Zapatilla modelo 15";
            case 16: return "Zapato modelo 16";
            case 17: return "Zapatilla modelo 17";
            case 18: return "Zapato modelo 18";
            case 19: return "Zapatilla modelo 19";
            case 20: return "Zapato modelo 20";
            default: return "";
        }
    }

    public static String obtenerProductoMujer(int opcion) {
        switch (opcion) {
            case 1: return "Zapatilla sport";
            case 2: return "Zapatilla running";
            case 3: return "Zapatilla casual";
            case 4: return "Zapato urbano";
            case 5: return "Zapatilla moda";
            case 6: return "Zapato formal";
            case 7: return "Zapatilla promo 2 pares";
            case 8: return "Zapato casual";
            case 9: return "Zapatilla urban pro";
            case 10: return "Zapato escolar";
            case 11: return "Zapatilla basica";
            case 12: return "Zapato elegante";
            case 13: return "Zapatilla street";
            case 14: return "Zapato modelo 14";
            case 15: return "Zapatilla modelo 15";
            case 16: return "Zapato modelo 16";
            case 17: return "Zapatilla modelo 17";
            case 18: return "Zapato promo 2 pares";
            case 19: return "Zapatilla modelo 19";
            case 20: return "Zapato modelo 20";
            default: return "";
        }
    }

    public static double calcularTotalVaron(int opcion, int cantidad) {
        if (cantidad <= 0) {
            return 0;
        }

        double total = 0;

        switch (opcion) {
            case 1: total = 140 * cantidad; break;
            case 2: total = 150 * cantidad; break;
            case 3: total = 160 * cantidad; break;
            case 4: total = 170 * cantidad; break;
            case 5:
                if (cantidad >= 2) {
                    total = (cantidad / 2) * 320 + (cantidad % 2) * 180;
                } else {
                    total = 180 * cantidad;
                }
                break;
            case 6: total = 190 * cantidad; break;
            case 7: total = 200 * cantidad; break;
            case 8: total = 210 * cantidad; break;
            case 9: total = 220 * cantidad; break;
            case 10: total = 230 * cantidad; break;
            case 11: total = 175 * cantidad; break;
            case 12:
                if (cantidad >= 2) {
                    total = (cantidad / 2) * 360 + (cantidad % 2) * 200;
                } else {
                    total = 200 * cantidad;
                }
                break;
            case 13: total = 195 * cantidad; break;
            case 14: total = 205 * cantidad; break;
            case 15: total = 215 * cantidad; break;
            case 16: total = 225 * cantidad; break;
            case 17: total = 235 * cantidad; break;
            case 18: total = 245 * cantidad; break;
            case 19: total = 255 * cantidad; break;
            case 20: total = 265 * cantidad; break;
            default: total = 0;
        }

        return total;
    }

    public static double calcularTotalMujer(int opcion, int cantidad) {
        if (cantidad <= 0) {
            return 0;
        }

        double total = 0;

        switch (opcion) {
            case 1: total = 145 * cantidad; break;
            case 2: total = 155 * cantidad; break;
            case 3: total = 165 * cantidad; break;
            case 4: total = 175 * cantidad; break;
            case 5: total = 185 * cantidad; break;
            case 6: total = 195 * cantidad; break;
            case 7:
                if (cantidad >= 2) {
                    total = (cantidad / 2) * 380 + (cantidad % 2) * 205;
                } else {
                    total = 205 * cantidad;
                }
                break;
            case 8: total = 215 * cantidad; break;
            case 9: total = 225 * cantidad; break;
            case 10: total = 235 * cantidad; break;
            case 11: total = 180 * cantidad; break;
            case 12: total = 240 * cantidad; break;
            case 13: total = 195 * cantidad; break;
            case 14: total = 205 * cantidad; break;
            case 15: total = 215 * cantidad; break;
            case 16: total = 225 * cantidad; break;
            case 17: total = 235 * cantidad; break;
            case 18:
                if (cantidad >= 2) {
                    total = (cantidad / 2) * 420 + (cantidad % 2) * 225;
                } else {
                    total = 225 * cantidad;
                }
                break;
            case 19: total = 255 * cantidad; break;
            case 20: total = 265 * cantidad; break;
            default: total = 0;
        }

        return total;
    }

    public static void metodoEntrega() {
        System.out.println("------ METODO DE ENTREGA ------");
        System.out.println("1. Lima");
        System.out.println("2. Arequipa");
        System.out.println("3. Cusco");
        System.out.println("4. Trujillo");
        System.out.println("5. Piura");
    }
    public static void metodoPago() {
        System.out.println("------ METODO DE PAGO ------");
        System.out.println("1. Tarjeta");
        System.out.println("2. Efectivo");
    }

    public static void generarBoleta(String nombre, String apellido, String correo, String dni, String telefono,
                                     String producto, String entrega, String pago, double total_pagar, double montoPagado) {
        double igv = total_pagar * 0.18;
        double subtotal = total_pagar - igv;
        double vuelto = montoPagado - total_pagar;

        System.out.println("=================== BOLETA DE VENTA ===================");
        System.out.println("Empresa: FOOTLOOSE S.A.");
        System.out.println("RUC: 20123456789");
        System.out.println("Cliente: " + nombre + " " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("DNI: " + dni);
        System.out.println("Telefono: " + telefono);
        System.out.println("Producto: " + producto);
        System.out.println("Entrega: " + entrega);
        System.out.println("Pago: " + pago);
        System.out.println("Subtotal: " + subtotal);
        System.out.println("IGV: " + igv);
        System.out.println("Total a pagar: " + total_pagar);
        System.out.println("Monto pagado: " + montoPagado);
        System.out.println("Vuelto: " + vuelto);
        System.out.println("========================================================");
    }
}
