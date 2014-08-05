/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database.mapper;

import com.epam.testapp.database.pool.ConnectionWrapper;
import com.epam.testapp.database.pool.DatabaseException;
import com.epam.testapp.database.pool.IConnectionPool;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Artsiom_Chuiko
 */
public class JdbcTemplate implements IJdbcTemplate{

    //take connection and prepared statement
    public JdbcTemplate() {
    }
    
    IConnectionPool pool;
    
    @Override
    public boolean queryForCUD(String sql, Object...param)
            throws SQLException{
        ConnectionWrapper con = null; 
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            for(int i = 1; i <= param.length; i++){
                ps.setObject(i, param[i]);
            }
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (DatabaseException ex) {
            return false;
        }finally{
            forFinally(null, ps, null, con);
        }
    }
    
    @Override
    public Object queryForObject(String sql, RowMapper rm, Object...param)
            throws SQLException{
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(sql);
            for(int i = 1; i <= param.length; i++){
                ps.setObject(i, param[i]);
            }
            rs = ps.executeQuery();
            return rm.mapRow(rs);
        } catch (DatabaseException ex) {
            return null;
        }finally{
            forFinally(null, ps, rs, con);
        }
    }
    
    @Override
    public Object simpleQuery(String sql, RowMapper rm) 
            throws SQLException{
        ConnectionWrapper con = null;
        Statement s = null;
        ResultSet rs = null;
        try{
            con = pool.takeConnection();
            s = con.createStatement();
            rs = s.executeQuery(sql);
            return rm.mapRow(rs);
        } catch (DatabaseException ex) {
            return null;
        }finally{
            forFinally(s, null, rs, con);
        }
    }
    
    private void forFinally(Statement s, 
            PreparedStatement ps, ResultSet rs, 
            ConnectionWrapper con){
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
            if(con != null){
                pool.releaseConnection(con);
            }
        }catch(SQLException ex){
            //Logger.getLogger(DBConfig.LOGGER_NAME).error(ex, ex);//////////////////////
        }
    }
}
