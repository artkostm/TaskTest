/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.pool;

import com.epam.testapp.util.DBUtil;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Artyom
 */
public final class ConnectionPool implements IConnectionPool{
    
    private static ConnectionPool instance;
    
    private static final ReentrantLock lock = new ReentrantLock();
    
    private String url;
    
    private String username;
    
    private String password;
    
    private int poolSize = 10;
    
    private boolean init;
    
    private BlockingQueue<ConnectionWrapper> freeConnections;
    
    private BlockingQueue<ConnectionWrapper> workingConnections;

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getPoolSize() {
        return poolSize;
    }
    
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
    
    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
    
    @Override
    public void init() throws DatabaseException {
        try {
            Class.forName(DBUtil.DB_DRIVER);
            if(url == null || password == null || username == null){
                url = DBUtil.DB_URL;
                password = DBUtil.DB_PASSWORD;
                username = DBUtil.DB_USER;
            }
            freeConnections = new ArrayBlockingQueue<ConnectionWrapper>(getPoolSize());
            workingConnections = new ArrayBlockingQueue<ConnectionWrapper>(getPoolSize());
            for (int i = 0; i < poolSize; i++) {
                ConnectionWrapper connection = new ConnectionWrapper(
                        DriverManager.getConnection(getUrl(), 
                                getUsername(),getPassword()));
                freeConnections.add(connection);
            }
            init = true;
//            logger.info(LoggingMessageConstants.SUCCESS_POOL_INIT);
        } catch (SQLException e) {
            throw new DatabaseException(LoggingMessageConstants.POOL_INIT_ERROR, e);
        } catch (ClassNotFoundException ex) {
            throw new DatabaseException(LoggingMessageConstants.POOL_INIT_ERROR, ex);
        }
    }
    
    @Override
    public ConnectionWrapper takeConnection() throws DatabaseException {
        ConnectionWrapper connection = null;
        try {
            connection = freeConnections.take();
            workingConnections.put(connection);
//            logger.info(LoggingMessageConstants.SUCCESS_POOL_TAKE_CONN);
        } catch (InterruptedException e) {
            throw new DatabaseException(LoggingMessageConstants.POOL_CONNECT_ERROR, e);
        }
        return connection;
    }
    
    @Override
    public void releaseConnection(ConnectionWrapper connection) {
        try {
            workingConnections.remove(connection);
            freeConnections.put(connection);			
//            logger.info(LoggingMessageConstants.SUCCESS_POOL_RELEASE_CONN);
        } catch (InterruptedException e) {
//            logger.error(e.getMessage());
        }
    }
    
    private void clearConnectionQueue() throws SQLException {
        ConnectionWrapper connection;
        while ((connection = freeConnections.poll()) != null) {
            connection.close();
        }
        while ((connection = workingConnections.poll()) != null) {
            connection.close();
        }
    }
    
    @Override
    public void closePool() {
        if (instance != null) {
            try {
                instance.clearConnectionQueue();
                instance = null;
//                logger.info(LoggingMessageConstants.SUCCESS_POOL_CLOSE);
            } catch (SQLException e) {
//                logger.error(e.getMessage());
            }
        }
        init = false;
    }
}