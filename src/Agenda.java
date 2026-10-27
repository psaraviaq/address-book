import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Agenda {
    private final Map<String, Contacto> contactos = new TreeMap<>();
    private final Set<String> grupos = new TreeSet<>();
    private final Map<String, Set<String>> contactosPorGrupo = new TreeMap<>();
    private final List<Contacto> historial = new ArrayList<>();

    public void agregarContacto(Contacto contacto) {
        String nombre = contacto.getNombre();
        if (contactos.containsKey(nombre)) {
            System.out.println("Error: Ya existe un contacto con el nombre '" + nombre + "'");
            return;
        }

        contactos.put(nombre, contacto);
        historial.add(contacto);
        System.out.println("Contacto agregado: " + nombre);
    }

    public Contacto buscarContacto(String nombre) {
        return contactos.get(nombre);
    }

    public boolean eliminarContacto(String nombre) {
        Contacto contacto = contactos.remove(nombre);
        if (contacto == null) {
            return false;
        }

        historial.remove(contacto);
        for (Set<String> nombres : contactosPorGrupo.values()) {
            nombres.remove(nombre);
        }
        return true;
    }

    public void listarContactos() {
        System.out.println("--- LISTA DE CONTACTOS (orden alfabetico) ---");
        for (Contacto contacto : contactos.values()) {
            System.out.println(contacto);
        }
    }

    public void listarHistorial() {
        System.out.println("--- HISTORIAL (orden de insercion) ---");
        for (Contacto contacto : historial) {
            System.out.println(contacto);
        }
    }

    public void agregarGrupo(String nombreGrupo) {
        if (!grupos.add(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' ya existe");
            return;
        }

        contactosPorGrupo.put(nombreGrupo, new TreeSet<>());
        System.out.println("Grupo '" + nombreGrupo + "' creado");
    }

    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        if (!contactos.containsKey(nombreContacto)) {
            System.out.println("Error: El contacto '" + nombreContacto + "' no existe");
            return;
        }
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }

        contactosPorGrupo.get(nombreGrupo).add(nombreContacto);
        System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'");
    }

    public void listarGrupos() {
        System.out.println("--- GRUPOS (orden alfabetico) ---");
        for (String grupo : grupos) {
            System.out.println(grupo);
        }
    }

    public void mostrarContactosPorGrupo(String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }

        Set<String> nombres = contactosPorGrupo.get(nombreGrupo);
        if (nombres.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos");
            return;
        }

        System.out.println("Contactos en '" + nombreGrupo + "':");
        for (String nombre : nombres) {
            System.out.println("- " + contactos.get(nombre));
        }
    }
}
