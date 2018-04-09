package store;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class DatePool {

    public DatePool(String url, String username, String pasword) {
        this.url = url;
        this.pasword = pasword;
        this.username = username;
    }

    private String url;
    private String username;
    private String pasword;

    public PoolingDataSource poolInit() {
        org.apache.commons.dbcp2.ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, username, pasword);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        return new PoolingDataSource<>(connectionPool);
    }
}
