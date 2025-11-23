package src.app;
import java.util.Scanner;
import src.menus.principal.MenuPrincipal;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContextoSistema sistema = new ContextoSistema();
        // sistema.carregarDadosDeTeste(sistema);
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        
        menuPrincipal.abrirMenu(scanner, sistema);
        scanner.close();
    }

}
