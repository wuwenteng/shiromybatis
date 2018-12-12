package com.ssm.test.shiromybatis.config.mybatis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author ASUS
 */
//@Configuration
public class SessionFactoryConfiguration {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    private static String mybatisConfigPathResource;
    @Value("${mybatis-config-file}")
    public void setMybatisConfigPathResource(String mybatisConfigPathResource) {
        SessionFactoryConfiguration.mybatisConfigPathResource = mybatisConfigPathResource;
    }
    private static String mapperPath;
    @Value("${mapper-path}")
    public void setMapperPath(String mapperPath) {
        SessionFactoryConfiguration.mapperPath = mapperPath;
    }

    @Value("${bean-package}")
    private String beanPath;

//    @Bean
//    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigPathResource));
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        String path = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
//
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(path));
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setTypeAliasesPackage(beanPath);
//        return sqlSessionFactoryBean;
//    }
}
