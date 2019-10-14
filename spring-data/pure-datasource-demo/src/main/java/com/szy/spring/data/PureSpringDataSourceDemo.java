package com.szy.spring.data;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈spring 数据源 非自动配置〉
 *
 * @author steamedfish
 * @create 2019/9/11
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
public class PureSpringDataSourceDemo {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        showBeans(applicationContext);
        pureSpringDataSourceDemo(applicationContext);
    }


    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("driverClassName", "org.h2.Driver");
        properties.setProperty("url", "jdbc:h2:mem:testdb");
        properties.setProperty("username", "sa");

        return BasicDataSourceFactory.createDataSource(properties);
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    private static void showBeans(ApplicationContext applicationContext) {
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }

    private static void pureSpringDataSourceDemo(ApplicationContext applicationContext) throws SQLException {
        PureSpringDataSourceDemo pureSpringDataSourceDemo = applicationContext.getBean("pureSpringDataSourceDemo",
                PureSpringDataSourceDemo.class);
        pureSpringDataSourceDemo.showDataSource();
    }

    private void showDataSource() throws SQLException {
        System.out.println(dataSource.toString());
        Connection connection = dataSource.getConnection();
        System.out.println(connection.toString());
        connection.close();
    }
}
