import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean salir = false;
            while (!salir) {
                mostrarMenu();
                if (!scanner.hasNextInt()) {
                    if (!scanner.hasNextLine()) {
                        break;
                    }
                    scanner.nextLine();
                    System.out.println("Opcion invalida, intenta de nuevo");
                    continue;
                }

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> agregarContacto(scanner, agenda);
                    case 2 -> buscarContacto(scanner, agenda);
                    case 3 -> eliminarContacto(scanner, agenda);
                    case 4 -> agenda.listarContactos();
                    case 5 -> agenda.listarHistorial();
                    case 6 -> agenda.agregarGrupo(leer(scanner, "Nombre del grupo: "));
                    case 7 -> agregarContactoAGrupo(scanner, agenda);
                    case 8 -> agenda.listarGrupos();
                    case 9 -> agenda.mostrarContactosPorGrupo(leer(scanner, "Nombre del grupo: "));
                    case 10 -> {
                        System.out.println("Saliendo de la agenda...");
                        salir = true;
                    }
                    default -> System.out.println("Opcion invalida, intenta de nuevo");
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== AGENDA DE CONTACTOS ===");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Listar contactos (orden alfabetico)");
        System.out.println("5. Listar historial (orden de insercion)");
        System.out.println("6. Agregar grupo");
        System.out.println("7. Agregar contacto a grupo");
        System.out.println("8. Listar grupos");
        System.out.println("9. Mostrar contactos por grupo");
        System.out.println("10. Salir");
        System.out.println("==========================");
        System.out.print("Elige una opcion: ");
    }

    private static void agregarContacto(Scanner scanner, Agenda agenda) {
        System.out.println("--- AGREGAR CONTACTO ---");
        String nombre = leer(scanner, "Nombre: ");
        String telefono = leer(scanner, "Telefono: ");
        String email = leer(scanner, "Email: ");
        agenda.agregarContacto(new Contacto(nombre, telefono, email));
    }

    private static void buscarContacto(Scanner scanner, Agenda agenda) {
        String nombre = leer(scanner, "Nombre del contacto: ");
        Contacto contacto = agenda.buscarContacto(nombre);
        if (contacto == null) {
            System.out.println("Contacto no encontrado: " + nombre);
        } else {
            System.out.println(contacto);
        }
    }

    private static void eliminarContacto(Scanner scanner, Agenda agenda) {
        String nombre = leer(scanner, "Nombre del contacto: ");
        if (agenda.eliminarContacto(nombre)) {
            System.out.println("Contacto eliminado: " + nombre);
        } else {
            System.out.println("Contacto no encontrado: " + nombre);
        }
    }

    private static void agregarContactoAGrupo(Scanner scanner, Agenda agenda) {
        String nombreContacto = leer(scanner, "Nombre del contacto: ");
        String nombreGrupo = leer(scanner, "Nombre del grupo: ");
        agenda.agregarContactoAGrupo(nombreContacto, nombreGrupo);
    }

    private static String leer(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}
