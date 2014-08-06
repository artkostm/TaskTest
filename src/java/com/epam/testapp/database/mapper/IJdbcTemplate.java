/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.mapper;

import com.epam.testapp.database.mapper.RowMapper;
import java.sql.SQLException;

/**
 *
 * @author Artsiom_Chuiko
 */
public interface IJdbcTemplate {
    
    public boolean queryForCUD(String sql, Object...param)
            throws JdbcTemplateException;
    
    public Object queryForObject(String sql, RowMapper rm, Object...param)
            throws JdbcTemplateException;
    
    public Object simpleQuery(String sql, RowMapper rm) 
            throws JdbcTemplateException;
}
