package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Inventario {
    private Producto[] productos;
    private int count;

    public Inventario() {
        productos = new Producto[10];
        count = 0;
    }

    public void addProduct(Producto producto) {
        if (count < productos.length) {
            productos[count] = producto;
            count++;
            System.out.println("Producto agregado al inventario.");
        } else {
            System.out.println("El inventario está lleno. No se pueden agregar más productos.");
        }
    }

    public void removeProduct(int productId) {
        for (int i = 0; i < count; i++) {
            if (productos[i].getId() == productId) {
                productos[i] = productos[count - 1];
                productos[count - 1] = null;
                count--;
                System.out.println("Producto eliminado del inventario.");
                return;
            }
        }
        System.out.println("No se encontró un producto con el ID especificado.");
    }

    public Producto searchProduct(int productId) {
        for (int i = 0; i < count; i++) {
            if (productos[i].getId() == productId) {
                return productos[i];
            }
        }
        return null;
    }

    public void displayInventory() {
        if (count == 0) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario:");
            for (int i = 0; i < count; i++) {
                System.out.println(productos[i]);
            }
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < count; i++) {
                Producto producto = productos[i];
                writer.write(producto.getId() + "," + producto.getNombre() + "," + producto.getPrecio() + "," +
                        producto.getCantidad());
                writer.newLine();
            }
            System.out.println("El inventario se ha guardado en el archivo: " + filename);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario en el archivo: " + filename);
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    double precio = Double.parseDouble(parts[2]);
                    int cantidad = Integer.parseInt(parts[3]);
                    Producto producto = new Producto(id, nombre, precio, cantidad);
                    addProduct(producto);
                }
            }
            System.out.println("El inventario se ha cargado desde el archivo: " + filename);
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario desde el archivo: " + filename);
        }
    }
}
