package com.example.mustache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class ConnectionPool
{
    private static Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static HikariDataSource ds=null;
        @Value("${spring.datasource.url}")
        private String dbUrl;
        @Value("${spring.datasource.username}")
        private String userName;
        @Value("${spring.datasource.password}")
        private String pwd;
        public DataSource getDataSource()
         {

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setUsername(userName);
            config.setPassword(pwd);
            HikariDataSource dataSource = new HikariDataSource(config);
                if (ds == null)
                {
                   synchronized (com.example.mustache.ConnectionPool.class)
                   {
                     if (ds == null)
                     {
                        ds = new HikariDataSource(config);
                     }
                   }
                 }
                  LOG.info("Datasource created successfully..");
                  return ds;
         }
 }


