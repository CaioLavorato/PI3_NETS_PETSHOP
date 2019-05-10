/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NETS.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

    //Armazena o objeto de conexão
    private static Connection connection = null;

    //Obtém uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        //Só tenta abrir uma conexão se esta já não
        //existir ou estiver fechada
        if (connection == null || connection.isClosed()) {

            //Declaração de endereço de conexão com o banco de dados
            String dbUrl = "jdbc:mysql://localhost:3307/PetShop_Netsuser=root";

            //Propriedades para armazenamento do usuário e da senh a do banco
            Properties properties = new Properties();
           //properties.put("user", "root");
            //properties.put("password", "");

            //Realiza a conexão com o banco
            connection = DriverManager.getConnection(dbUrl, properties);
        }

        //Retorna a conexão a quem chamou
        return connection;
    }
}
