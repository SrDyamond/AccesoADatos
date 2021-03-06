package com.afundacionfp.resource;

import com.afundacionfp.DataProvider;
import com.afundacionfp.Product;
import com.afundacionfp.ProductInfo;
import com.afundacionfp.Reserve;

import java.util.ArrayList;
import java.util.List;

class MockDataProvider implements DataProvider {
    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Primer producto", "1", "/images/primer_producto.jpg", null));
        products.add(new Product("Segundo producto", "2", "/images/segundo_producto.jpg", null));
        products.add(new Product("Tercer producto", "3", "/images/tercer_producto.jpg", null));

        return products;
    }

    @Override
    public Reserve getReserve(String reference, String username, String passwordSha) {
        return null;
    }

    @Override
    public Product getFullProduct(String reference) {
        ProductInfo info = new ProductInfo("Producto de ejemplo", 9999, 5);
        Product product = new Product("Producto", "N/A", "/images/producto.jpg", info);

        return product;
    }

    @Override
    public List<Reserve> getReserves(String username, String passwordSha) {
        // TODO: Devolver una lista de reservas si en algún momento se quiere usar
        // MockDataProvider para probar
        return null;
    }


    @Override
    public void createReserve(String reference, String username, String passwordSha) { }

    @Override
    public void removeReserve(String reference, String username, String passwordSha) { }
}
