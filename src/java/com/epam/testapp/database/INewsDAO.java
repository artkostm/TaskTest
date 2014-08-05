/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database;

import com.epam.testapp.model.News;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Artsiom_Chuiko
 */
public interface INewsDAO {
    
    public List<News> getList()  throws SQLException;
    
    public boolean save(News news) throws SQLException;
    
    public boolean remowe(News news) throws SQLException;
    
    public News fetchById(int id) throws SQLException;
    
    public boolean add(News news) throws SQLException;
}
