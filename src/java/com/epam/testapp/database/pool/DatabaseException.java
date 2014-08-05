/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.pool;

/**
 *
 * @author Artyom
 */
public class DatabaseException extends Exception{

    public DatabaseException() {
    }
    
    public DatabaseException(String msg) {
        super(msg);
    }
    
    public DatabaseException(String msg, Throwable t) {
        super(msg, t);
    }
}
