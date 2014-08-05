/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database;

/**
 *
 * @author Artsiom_Chuiko
 */
public class SQLQuery {
    public static final String NEWS_ADD =  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/sql")
                  .getString("sql.news_table.add");
    
    public static final String NEWS_SAVE =  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/sql")
                  .getString("sql.news_table.save");
    
    public static final String NEWS_REMOVE =  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/sql")
                  .getString("sql.news_table.remove");
    
    public static final String NEWS_GET_BY_ID =  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/sql")
                  .getString("sql.news_table.get_by_id");
    
    public static final String NEWS_GET_ALL =  java.util.ResourceBundle
                  .getBundle("com/epam/testapp/properties/sql")
                  .getString("sql.news_table.get_all");
}
