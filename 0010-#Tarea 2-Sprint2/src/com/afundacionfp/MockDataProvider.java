package com.afundacionfp;

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
    public Product getFullProduct(String reference) {
        ProductInfo info = new ProductInfo("Producto de ejemplo", 9999, 5);
        Product product = new Product("Producto", "N/A", "/images/producto.jpg", info);

        return product;
    }

    @Override
    public Reserve getReserve(String reference, String username, String passwordSha) {
        ProductInfo info = new ProductInfo("Producto de ejemplo", 9999, 5);
        Product product = new Product("Producto", "N/A", "/images/producto.jpg", info);
        Reserve reserve = new Reserve(product, (new java.util.Date()).getTime());

        return reserve;
    }

    @Override
    public void createReserve(String reference, String username, String passwordSha) { }

    @Override
    public void removeReserve(String reference, String username, String passwordSha) { }
}
