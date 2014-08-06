/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Artsiom_Chuiko
 */
public class DBUtil {

    private DBUtil() {
    }
    
    public static final String DB_URL =  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/database")
                  .getString("database.url");
    
    public static final String DB_DRIVER =  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/database")
                  .getString("database.driver");
    
    public static final String DB_USER=  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/database")
                  .getString("database.user");
    
    public static final String DB_PASSWORD=  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/database")
                  .getString("database.password");
            
    public static void close(Statement s, 
            PreparedStatement ps, ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
            if(s != null){
                s.close();
            }
            if(ps != null){
                ps.close();
            }
        }catch(SQLException ex){
            //Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
        }
    }
}
