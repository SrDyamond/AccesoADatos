package com.afundacionfp;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM TablaClientes WHERE usuario = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String salt = resultSet.getString(5);
                String databaseConcatenatedStringSha = resultSet.getString(4);
                String concatenatedString = salt + passwordSha;
                if (databaseConcatenatedStringSha.equals(sha1FromString(concatenatedString))) {
                    // TODO: Realizar consultas sobre TablaReservas y TablaCamiones.
                   /* Recuperar la fila de la TablaClientes donde el username en nuestro código corresponda con la columna 1 sacada de la base de datos.
                    Si existe la fila, extraeremos los datos salt y contrasenaSha1, es decir, las columnas 5 y 4.
                    Concatenaremos el salt de la base de datos al passwordSha recibido por parámetro. Obtendremos un String de 80 caracteres.
                    Calcularemos el hash Sha de dicho String. Puedes ver un ejemplo aquí.
                    // IMPORTANTE: Para saber el esquema de la TablaReservas, mira la siguiente tarea.

                    */
                    String name = resultSet.getString(1);
                    String salts = resultSet.getString(5);


                    System.out.println("Autenticacion OK");
                } else {
                    // TODO: Devolver error.
                    System.out.println("KO");
                }
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private String sha1FromString(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(string.getBytes("utf8"));
            return String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void createReserve(String reference, String username, String passwordSha) {

    }

    @Override
    public void removeReserve(String reference, String username, String passwordSha) {

    }
}
