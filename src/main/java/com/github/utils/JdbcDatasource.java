package com.github.utils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by zk_chs on 16/11/11.
 */
public class JdbcDatasource implements DataSource {

    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/testdb";
    private static final String user = "root";
    private static final String password = "leizhimin";

    /** 连接池 */
    private static final LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());
    private static JdbcDatasource instance = new JdbcDatasource();

    static {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("cannot find datasource Driver...");
        }
    }

    public JdbcDatasource instance() {
        if (instance == null) instance = new JdbcDatasource();
        return instance;
    }

    private JdbcDatasource (){}

    @Override
    public Connection getConnection() throws SQLException {
        synchronized (pool) {
            if (pool.size() > 0) return pool.removeFirst();
            else return makeConnection();
        }
    }

    private Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private <T> void txCommit (Connection conn, Supplier<T> bussiness){
        try {
            conn.setAutoCommit(false);
            bussiness.get();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
