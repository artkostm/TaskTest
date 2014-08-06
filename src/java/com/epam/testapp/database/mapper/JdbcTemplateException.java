/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.mapper;

import com.epam.testapp.database.pool.DatabaseException;

/**
 *
 * @author Artsiom_Chuiko
 */
public class JdbcTemplateException extends DatabaseException{

    public JdbcTemplateException() {
        super();
    }
    
    public JdbcTemplateException(String msg) {
        super(msg);
    }
    
    public JdbcTemplateException(String msg, Throwable t) {
        super(msg, t);
    }
}
