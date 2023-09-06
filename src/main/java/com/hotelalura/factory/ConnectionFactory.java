package com.hotelalura.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.jasypt.util.text.BasicTextEncryptor;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;

/**
 * @author jdmon on 4/09/2023.
 * @project challenge-one-alura-hotel-latam
 */
public class ConnectionFactory {
    private String jdbcUrl;
    private String username;
    private String password;
    private final DataSource dataSource;

    public ConnectionFactory(){
        getProperties();
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl(jdbcUrl);
        pooledDataSource.setUser(username);
        pooledDataSource.setPassword(password);
        pooledDataSource.setMaxPoolSize(10);
        this.dataSource=pooledDataSource;
    }

    public Connection conectarDataBase() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void getProperties(){

        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            this.jdbcUrl=properties.getProperty("database.url");

            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(properties.getProperty("keystore.password"));
            this.username=textEncryptor.decrypt(
                    properties.getProperty("database.username")
            );
            this.password=textEncryptor.decrypt(
                    properties.getProperty("database.password")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
