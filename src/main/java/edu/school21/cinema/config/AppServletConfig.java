package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:application.properties")
public class AppServletConfig {
    @Value("${db_url}")
    private String url;
    @Value("${db_user}")
    private String user;
    @Value("${db_pass}")
    private String pass;
    @Value("${driver}")
    private String driver;
    @Value("${schema}")
    private String schema;
    @Value("${img_storage}")
    private String imgStorage;

    @Bean
    public HikariDataSource dataSource() throws SQLException, IOException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        dataSource.setDriverClassName(driver);
        Statement st = dataSource.getConnection().createStatement();
        st.execute(new String(Files.readAllBytes(Paths.get(schema))));
        return dataSource;
    }

    @Bean
    public String imgStorage() {
        return imgStorage;
    }

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

}
