package gov.iti.jets.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import gov.iti.jets.App;

public enum DBConnection {
    INSTANCE;
    MysqlConnectionPoolDataSource dataSource;
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    DBConnection(){
                try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("db.properties")) {

         Properties props = new Properties();
         props.load(inputStream);
         dataSource = new MysqlConnectionPoolDataSource();
         dataSource.setURL(props.getProperty("MYSQL_DB_URL"));
         dataSource.setUser(props.getProperty("MYSQL_DB_USERNAME"));
         dataSource.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
         } catch (IOException e) {
            e.printStackTrace();
        }

    
    }
}
