package com.ssm.test.shiromybatis.config.mybatis;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;


/**
 * @author ASUS
 */
//@Configuration
// 配置mybatis的扫描路径
//@MapperScan("com.ssm.test.shiromybatis.dao")
public class DataSourceConfiguration {

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUser;

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUser);
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setPassword(jdbcPassword);

        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }
}
