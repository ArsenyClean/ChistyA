package crudservlet;

import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class DatePool {


    private final String url = "jdbc:postgresql://localhost:5432/userstore";
    private final String username = "postgres";
    private final String pasword = "1234";

    public PoolingDataSource poolInit() {
        org.apache.commons.dbcp2.ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, username, pasword);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        return new PoolingDataSource<>(connectionPool);
    }
}
