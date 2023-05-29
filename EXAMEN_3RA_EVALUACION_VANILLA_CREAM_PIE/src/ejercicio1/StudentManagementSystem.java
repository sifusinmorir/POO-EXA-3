package ejercicio1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StudentManagementSystem {
    private ArrayList<Estudiante> estudiantes;

    public StudentManagementSystem() {//El constructor se la clase junto con la clase ArrayList nos permite almacenar los objetos estudiante
        estudiantes = new ArrayList<>();
    }

    public void addStudent(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public Estudiante getStudent(int rollNumber) {
        for (Estudiante estudiante : estudiantes) {//Uso de toString 
            if (estudiante.getNumeroLista() == rollNumber) {
                return estudiante;
            }
        }
        return null;
    }

    public void storeData(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(estudiantes);
            System.out.println("Datos de estudiantes almacenados en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al almacenar los datos de los estudiantes en el archivo: " + e.getMessage());
        }
    }

    public void loadData(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            estudiantes = (ArrayList<Estudiante>) inputStream.readObject();
            System.out.println("Datos de estudiantes cargados desde el archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos de los estudiantes desde el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Crear el sistema de gestión de estudiantes
        StudentManagementSystem sms = new StudentManagementSystem();

        // Crear objetos de Estudiante
        Estudiante estudiante1 = new Estudiante("Sinue Mendoza", 5, "Segundo");
        Estudiante estudiante2 = new Estudiante("Diego Quiroz",6, "Segundo");
        Estudiante estudiante3 = new Estudiante("Christian Flores", 7, "Segundo");

        // Agregar los estudiantes al sistema
        sms.addStudent(estudiante1);
        sms.addStudent(estudiante2);
        sms.addStudent(estudiante3);

        // Almacenar los datos de los estudiantes en un archivo
        sms.storeData("estudiantes.dat");

        // Borrar los datos de los estudiantes del sistema
        sms = new StudentManagementSystem();

        // Cargar los datos de los estudiantes desde el archivo
        sms.loadData("estudiantes.dat");

        // Recuperar y mostrar la información de un estudiante específico
        Estudiante estudianteRecuperado = sms.getStudent(6);
        
        if (estudianteRecuperado != null) {
            System.out.println("Información del estudiante recuperado: " + estudianteRecuperado);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}
