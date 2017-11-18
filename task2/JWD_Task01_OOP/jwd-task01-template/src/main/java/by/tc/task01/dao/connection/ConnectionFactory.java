package by.tc.task01.dao.connection;

import by.tc.task01.dao.connection.impl.FileConnection;

public final class ConnectionFactory {
    private static final ConnectionFactory instance = new ConnectionFactory();

    private final Connection connection = new FileConnection();

    private ConnectionFactory() {}

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
