import java.util.*;

// Clase para el nodo del estudiante
class StudentNode {
    String name;
    double note;
    StudentNode left;
    StudentNode right;

    public StudentNode(String name, double note) {
        this.name = name;
        this.note = note;
        left = null;
        right = null;
    }
}

// Clase para el árbol binario de búsqueda
public class StudentBinarySearchTree {
    StudentNode root;
    HashMap<Double, List<String>> noteMap;

    public StudentBinarySearchTree() {
        root = null;
        noteMap = new HashMap<>();
    }

    // Método para insertar un nuevo estudiante en el árbol
    public void insert(String name, double note) {
        if (!isValidName(name)) {
            System.out.println("El nombre no puede contener numeros.");
            return;
        }
        if (note > 5.0) {
            System.out.println("La nota no puede ser mayor a 5.0");
            return;
        }
        root = insertRec(root, name, note);
        noteMap.computeIfAbsent(note, k -> new ArrayList<>()).add(name);
    }

    // Método para validar que el nombre no contenga números
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }

    // Función recursiva para insertar un nuevo estudiante en el árbol
    private StudentNode insertRec(StudentNode root, String name, double note) {
        if (root == null) {
            root = new StudentNode(name, note);
            return root;
        }

        if (name.compareTo(root.name) < 0) {
            root.left = insertRec(root.left, name, note);
        } else if (name.compareTo(root.name) > 0) {
            root.right = insertRec(root.right, name, note);
        }

        return root;
    }

    // Método para buscar un estudiante por nombre
    public Double searchByName(String name) {
        return searchByNameRec(root, name);
    }

    // Función recursiva para buscar un estudiante por nombre
    private Double searchByNameRec(StudentNode root, String name) {
        if (root == null) {
            return null;
        }

        if (root.name.equals(name)) {
            return root.note;
        }

        return name.compareTo(root.name) < 0 ? searchByNameRec(root.left, name) : searchByNameRec(root.right, name);
    }

    // Método para buscar estudiantes por nota
    public List<String> searchBynote(double note) {
        return noteMap.getOrDefault(note, Collections.emptyList());
    }

    // Método para listar los estudiantes en orden alfabético
    public void inorder() {
        inorderRec(root);
    }

    // Función recursiva para listar los estudiantes en orden alfabético
    private void inorderRec(StudentNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println("Nombre: " + root.name + ", Nota: " + root.note);
            inorderRec(root.right);
        }
    }

    // Método para calcular el promedio de las notas
    public double calculateAveragenote() {
        int[] count = new int[1]; // Usamos un array para permitir modificaciones dentro de la lambda
        double totalnote = calculateTotalnote(root, count);
        return count[0] == 0 ? 0 : totalnote / count[0];
    }

    private double calculateTotalnote(StudentNode root, int[] count) {
        if (root == null) {
            return 0;
        }
        double leftTotal = calculateTotalnote(root.left, count);
        double rightTotal = calculateTotalnote(root.right, count);
        count[0]++;
        return root.note + leftTotal + rightTotal;
    }
}
