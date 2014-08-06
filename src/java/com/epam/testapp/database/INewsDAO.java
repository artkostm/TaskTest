/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database;

import com.epam.testapp.database.mapper.JdbcTemplateException;
import com.epam.testapp.model.News;
import java.util.List;

/**
 *
 * @author Artsiom_Chuiko
 */
public interface INewsDAO {
    
    public List<News> getList()  throws JdbcTemplateException;
    
    public boolean save(News news) throws JdbcTemplateException;
    
    public boolean remowe(News news) throws JdbcTemplateException;
    
    public News fetchById(int id) throws JdbcTemplateException;
    
    public boolean add(News news) throws JdbcTemplateException;
}
