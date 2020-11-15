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
            // Se establece conexión con la database indicada
            Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
            // Se crea una instancia con la que podremos ejecutar las query
            Statement statement = connection.createStatement();
            // Se define la query para getReserve
            String sql = "SELECT * FROM TablaClientes WHERE usuario = '" + username + "'";
            // Se obtiene el resultado de la query
            ResultSet resultSet = statement.executeQuery(sql);
            // Objeto Reserve al que se asignarán los datos obtenidos en la database
            Reserve leReserve = null;

            while (resultSet.next()) {
                // Se obtiene en forma de String el valor de la columna especificada de la tupla actual
                String salt = resultSet.getString(5);
                String databaseConcatenatedStringSha = resultSet.getString(4);
                // Se concatenan los resultados anteriores
                String concatenatedString = salt + passwordSha;
                // Cond: valor columna 4 = bytes cifrados de la concatenacion de la columna 5 + passwordSha
                if (databaseConcatenatedStringSha.equals(sha1FromString(concatenatedString))) {
                    // Se realizan consultas sobre TablaReservas y TablaCamiones
                    leReserve = getReserveInfo(reference);
                    System.out.println("Autenticación OK");
                } else {
                    // TODO: Devolver error.
                    System.out.println("KO");
                }
                return leReserve;
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
    private Reserve getReserveInfo(String id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
        Statement statement = connection.createStatement();

        String sql = "SELECT * from TablaReservas where id = " + id;
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String refCamion = resultSet.getString(3);
            Date fecha = resultSet.getDate(4);

            System.out.println("Información de la reserva con id = " + id);
            System.out.println("Referencia del camión reservado: " + refCamion);
            System.out.println("Fecha de la reserva: " + fecha);

            Product product = getFullProduct(refCamion);
            Reserve reservation = new Reserve(product, fecha.getTime());

            return reservation;
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
