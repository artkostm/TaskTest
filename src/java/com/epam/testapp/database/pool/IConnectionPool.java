/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.pool;

/**
 *
 * @author Artsiom_Chuiko
 */
public interface IConnectionPool {
    
    public void init() throws DatabaseException;
    
    public ConnectionWrapper takeConnection() throws DatabaseException;
    
    public void releaseConnection(ConnectionWrapper connection);
    
    public void closePool();
}
