package com.afundacionfp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDataProvider implements DataProvider {
    @Override
    public List<Product> getProducts() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM TablaCamiones";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String reference = resultSet.getString(1);
                String urlImagen = resultSet.getString(3);
                Product product = new Product(name, reference, urlImagen, null);
                products.add(product);
            }
            return products;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public Product getFullProduct(String reference) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM TablaCamiones WHERE referencia = '" + reference + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String ref = resultSet.getString(1);
                String urlImagen = resultSet.getString(3);
                String description = resultSet.getString(4);
                Double price = resultSet.getDouble(5);
                int availableAmount = resultSet.getInt(6);
                ProductInfo info = new ProductInfo(description, (int) (price*100), availableAmount);
                return new Product(name, ref, urlImagen, info);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public Reserve getReserve(String reference, String username, String passwordSha) {
        return null;
    }

    @Override
    public void createReserve(String reference, String username, String passwordSha) {

    }

    @Override
    public void removeReserve(String reference, String username, String passwordSha) {

    }
}