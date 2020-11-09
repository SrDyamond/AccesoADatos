package com.afundacionfp;

import java.util.List;

public interface DataProvider {
    // Obtener toda la lista de productos.
    List<Product> getProducts();

    // Obtener la información completa de un producto. Esto incluye el atributo productInfo.
    Product getFullProduct(String reference);

    // Obtener la información sobre una reserva hecha.
    Reserve getReserve(String reference, String username, String passwordSha);

    // Crear una reserva para un producto.
    void createReserve(String reference, String username, String passwordSha);

    // Cancelar la reserva de un producto.
    void removeReserve(String reference, String username, String passwordSha);
}