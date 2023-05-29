package ejercicio2;

public class Ejercicio2 {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        Producto p1 = new Producto(1, "Producto 1", 10.99, 5);
        Producto p2 = new Producto(2, "Producto 2", 19.99, 8);
        Producto p3 = new Producto(3, "Producto 3", 7.49, 3);

        inventario.addProduct(p1);
        inventario.addProduct(p2);
        inventario.addProduct(p3);

        inventario.displayInventory();

        Producto searchedProduct = inventario.searchProduct(2);
        if (searchedProduct != null) {
            System.out.println("Producto encontrado: " + searchedProduct);
        } else {
            System.out.println("Producto no encontrado.");
        }

        inventario.removeProduct(1);
        inventario.displayInventory();

        inventario.saveToFile("inventario.txt");

        Inventario nuevoInventario = new Inventario();
        nuevoInventario.loadFromFile("inventario.txt");
        nuevoInventario.displayInventory();
    }
    
}
