package com.afundacionfp;

import com.afundacionfp.exception.HttpExceptionCode;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<Reserve> getReserves(String username, String passwordSha) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM TablaClientes WHERE usuario = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            // Este bucle itera sobre las filas de la respuesta de la base de datos
            while (resultSet.next()) {
                // Las dos siguientes líneas recuperan el 'salt' y el 'hash' almacenados en base de datos
                String salt = resultSet.getString(5);
                String databaseConcatenatedStringSha = resultSet.getString(4);
                // Aquí concatenamos el 'salt' al 'passwordSha' que nos han pasado en la petición HTTP
                String concatenatedString = salt + passwordSha;
                // En la siguiente línea calculamos el SHA-1 de dicha concatenación.
                String sha1 = sha1FromString(concatenatedString);
                // Comprobamos si coincide con el 'hash' almacenado en base de datos (autenticación)
                if (databaseConcatenatedStringSha.equals(sha1)) {
                    // En caso de que coincida, lanzamos la siguiente consulta para obtener todas las reservas
                    String sql2 = "SELECT * FROM TablaReservas WHERE idCliente = '" + username + "'";
                    ResultSet resultSet2 = statement.executeQuery(sql2);
                    List<Reserve> reserveList = new ArrayList<>();
                    // Iteramos sobre los resultados
                    while (resultSet2.next()) {
                        // La siguiente línea recupera el dato fechaReserva de la columna indicada
                        // Este dato viene como un String que tendremos que 'interpretar'
                        String dateString = resultSet2.getString(4);
                        // Con las siguientes líneas transformamos ese String en un objeto Date.
                        // Después, con ese 'date' obtenemos el timestamp (long) que representa
                        // los segundos desde 1/1/1970
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        java.util.Date date = formatter.parse(dateString);
                        long dateTimestamp = date.getTime();
                        // A continuación, reutilizamos el método getFullProduct(String reference) para
                        // recuperar de la base de datos la información del producto reservado
                        String reference = resultSet2.getString(3);
                        Product fullProduct = getFullProduct(reference);
                        Reserve reserve = new Reserve(fullProduct, dateTimestamp);
                        reserveList.add(reserve);
                    }
                    return reserveList;
                } else {
                    return null;
                }
            }
            return null;
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    @Override
    public void createReserve(String reference, String username, String passwordSha)throws HttpExceptionCode {
        try {
            // Autenticación
            Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM TablaClientes WHERE usuario = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String salt = resultSet.getString(5);
                String databaseConcatenatedStringSha = resultSet.getString(4);
                String concatenatedString = salt + passwordSha;
                String sha1 = sha1FromString(concatenatedString);
                if (databaseConcatenatedStringSha.equals(sha1)) {
                    // Si la autenticación es exitosa, lanzamos una sentencia INSERT como la especificada al final en
                    // https://gitlab.afundacionfp.com/data/acceso-a-datos/-/wikis/Tablas-de-la-base-de-datos-de-Monster-Trucks
                    String sql2 = "INSERT INTO TablaReservas(idCliente, refCamion) VALUES('" + username + "', '" + reference + "')";
                    // Usamos 'executeUpdate' en lugar de 'executeQuery', porque actualizamos la tabla
                    int rowsAffected = statement.executeUpdate(sql2);
                    if (rowsAffected == 1) {
                        System.out.println("OK,creado");
                        throw new HttpExceptionCode(201);
                    } else {
                        System.out.println("Autentificación fallida");
                        throw new HttpExceptionCode(401);
                    }
                } else {
                    System.out.println("Not Found");
                    throw new HttpExceptionCode(404);
                }
            }
            System.out.println("Inaccesible por temas legales");
            throw new HttpExceptionCode(418);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    @Override
    public void removeReserve(String reference, String username, String passwordSha) throws HttpExceptionCode{
        try {
            // Autenticación
            Connection connection = DriverManager.getConnection("jdbc:mysql://gitlab.afundacionfp.com:3306/mysql?serverTimezone=Europe/Madrid", "developer", "pass");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM TablaClientes WHERE usuario = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String salt = resultSet.getString(5);
                String databaseConcatenatedStringSha = resultSet.getString(4);
                String concatenatedString = salt + passwordSha;
                String sha1 = sha1FromString(concatenatedString);
                if (databaseConcatenatedStringSha.equals(sha1)) {
                    // Si la autenticación es exitosa, lanzamos una sentencia INSERT como la especificada al final en
                    // https://gitlab.afundacionfp.com/data/acceso-a-datos/-/wikis/Tablas-de-la-base-de-datos-de-Monster-Trucks
                    String sql2 = "DELETE FROM TablaReservas WHERE (idCliente = '" + username + "') AND (refCamion = '" + reference + "')";
                    // Usamos 'executeUpdate' en lugar de 'executeQuery', porque actualizamos la tabla
                    int rowsAffected = statement.executeUpdate(sql2);
                    if (rowsAffected > 0) {
                        System.out.println("OK,Fila eliminada");
                        throw new HttpExceptionCode(200);
                        // Éxito
                    } else {
                        System.out.println("Autentificación fallida");
                        throw new HttpExceptionCode(401);
                    }
                } else {
                    System.out.println("Not Found");
                    throw new HttpExceptionCode(404);
                }
            }
            System.out.println("Inaccesible por temas legales");
            throw new HttpExceptionCode(418);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
