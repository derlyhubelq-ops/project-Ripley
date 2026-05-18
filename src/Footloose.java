  import java.util.Scanner;
  
public class Footloose {
      public static void main(String[] args) {
          Scanner escaner = new Scanner(System.in);
          System.out.println("--- Sistema Footloose Inicializado ---");
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
}
