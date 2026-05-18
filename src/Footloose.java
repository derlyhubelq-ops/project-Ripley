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
            int cantidad = 0;

            if (opcionGenero == 1) {
                menuVaron();
                System.out.println("Ingrese la opcion del producto:");
                int opcionProducto = escaner.nextInt();
                System.out.println("Ingrese la cantidad:");
                cantidad = escaner.nextInt();
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
                cantidad = escaner.nextInt();
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

            escaner.nextLine();
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

            generarBoleta(nombre, apellido, correo, dni, telefono, producto, entrega, pago, total_pagar, montoPagado, cantidad);
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
        System.out.println("1. R18 Hombres R18-Xw00245 | Gris | T41 | S/. 167.90 [Lleva 1 par y obtén 30% desc.]");
        System.out.println("2. Puma Caven III | Blanco total | T42 | S/. 229.00");
        System.out.println("3. Puma Transport | Negro/suela blanca | T41 | S/. 189.00");
        System.out.println("4. Puma Jada Classic Sd | Beige | T40 | S/. 199.00");
        System.out.println("5. Puma Blaze Lite Nbk | Negro | T42 | S/. 199.00");
        System.out.println("6. Skechers Bobs Squad 4 | Blanco | T41 | S/. 279.00");
        System.out.println("7. Skechers Uno Savvy | Blanco | T42 | S/. 249.00");
        System.out.println("8. Puma Court Lally Metallic | Blanco | T40 | S/. 179.00");
        System.out.println("9. Puma Carina 3 | Negro | T42 | S/. 229.00");
        System.out.println("10. Skechers Bobs Arch | Negro | T41 | S/. 239.00");
        System.out.println("11. Renzo Renzini Rrz-Wo0001 | Marron oscuro | T41 | S/. 135.90 [Lleva 1 par y obtén 20% desc.]");
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
        System.out.println("1. R18 Mujeres R18-Xw00245 | Gris | T37 | S/. 167.90 [Lleva 1 par y obtén 30% desc.]");
        System.out.println("2. Puma Caven III Wns | Blanco total | T38 | S/. 229.00");
        System.out.println("3. Puma Transport | Negro/suela blanca | T37 | S/. 189.00");
        System.out.println("4. Puma Jada Classic Sd | Beige | T36 | S/. 199.00");
        System.out.println("5. Puma Blaze Lite Nbk Wns | Negro | T38 | S/. 199.00");
        System.out.println("6. Skechers Bobs Squad 4 | Blanco | T37 | S/. 279.00");
        System.out.println("7. Skechers Uno Savvy | Blanco | T38 | S/. 249.00");
        System.out.println("8. Puma Court Lally Metallic | Blanco | T36 | S/. 179.00");
        System.out.println("9. Puma Carina 3 | Negro | T38 | S/. 229.00");
        System.out.println("10. Skechers Bobs Arch | Negro | T37 | S/. 239.00");
        System.out.println("11. Top Model Tmo-N0013 | Negro | T36 | S/. 109.90 [Lleva 1 par y obtén 50% desc.]");
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

    public static double calcularTotalVaron(int opcion, int cantidad) {
        if (cantidad <= 0) return 0;
        double total = 0;
        switch (opcion) {
            case 1:
                if (cantidad >= 2) total = (167.90 * cantidad) * 0.70;
                else total = 167.90 * cantidad;
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
                if (cantidad >= 2) total = (135.90 * cantidad) * 0.80;
                else total = 135.90 * cantidad;
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
                if (cantidad >= 2) total = (167.90 * cantidad) * 0.70;
                else total = 167.90 * cantidad;
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
                if (cantidad >= 2) total = (109.90 * cantidad) * 0.50;
                else total = 109.90 * cantidad;
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
                                     String producto, String entrega, String pago,
                                     double total_pagar, double montoPagado, int cantidad) {

        double igv = total_pagar * 0.18;
        double subtotal = total_pagar - igv;
        double vuelto = montoPagado - total_pagar;

        String descuento = "No aplica";

        if (cantidad >= 2) {
            if (producto.equals("R18 Hombres R18-Xw00245") || producto.equals("R18 Mujeres R18-Xw00245")) {
                descuento = "30% por compra de 2 pares";
            } else if (producto.equals("Renzo Renzini Rrz-Wo0001")) {
                descuento = "20% por compra de 2 pares";
            } else if (producto.equals("Top Model Tmo-N0013")) {
                descuento = "50% por compra de 2 pares";
            }
        }

        System.out.println("=================== BOLETA DE VENTA ===================");
        System.out.println("Empresa: FOOTLOOSE S.A.");
        System.out.println("RUC: 20123456789");
        System.out.println("Cliente: " + nombre + " " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("DNI: " + dni);
        System.out.println("Telefono: " + telefono);
        System.out.println("Producto: " + producto + " (x" + cantidad + ")");
        System.out.println("Descuento: " + descuento);
        System.out.println("Entrega: " + entrega);
        System.out.println("Pago: " + pago);
        System.out.printf("Subtotal: ", subtotal);
        System.out.printf("IGV: ", igv);
        System.out.printf("Total a pagar: ", total_pagar);
        System.out.printf("Monto pagado: ", montoPagado);
        System.out.printf("Vuelto: ", vuelto);
        System.out.println("========================================================");
    }
}
