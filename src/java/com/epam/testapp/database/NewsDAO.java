/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database;

import com.epam.testapp.database.mapper.IJdbcTemplate;
import com.epam.testapp.database.mapper.JdbcTemplateException;
import com.epam.testapp.database.mapper.RowMapper;
import com.epam.testapp.model.News;
import com.epam.testapp.util.DAOUtil;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artsiom_Chuiko
 */
public class NewsDAO implements INewsDAO{

    public NewsDAO(/* DataSourceObject */) {
    }
    
    public NewsDAO(IJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private IJdbcTemplate jdbcTemplate;
    
    @Override
    public List<News> getList() throws JdbcTemplateException {
        return (List<News>)jdbcTemplate.simpleQuery(SQLQuery.NEWS_GET_ALL, 
                new RowMapper<List<News> >() {
            @Override
            public List<News> mapRow(ResultSet rs) throws SQLException {
                List<News> list = new ArrayList<News>();
                if(rs.first()){
                    do{
                        int id = rs.getInt(DAOUtil.NEWS_ID);
                        Date date = rs.getDate(DAOUtil.NEWS_NDATE);
                        String title = rs.getString(DAOUtil.NEWS_TITLE);
                        String brief = rs.getString(DAOUtil.NEWS_BRIEF);
                        String content = rs.getString(DAOUtil.NEWS_CONTENT);
                        News news = new News(id, content, brief, title, date);
                        list.add(news);
                    }while(rs.next());
                }
                return list;
            }
        });
    }

    @Override
    public boolean save(News news) throws JdbcTemplateException {
        return jdbcTemplate.queryForCUD(SQLQuery.NEWS_SAVE, news.getTitle(),
                news.getDate(), news.getBrief(), news.getContent(),
                news.getId());
    }

    @Override
    public boolean remowe(News news) throws JdbcTemplateException {
        return jdbcTemplate.queryForCUD(SQLQuery.NEWS_REMOVE, news.getId());
    }

    @Override
    public News fetchById(int id) throws JdbcTemplateException {
        return (News)jdbcTemplate.queryForObject(SQLQuery.NEWS_GET_BY_ID, 
                new RowMapper<News>() {
            @Override
            public News mapRow(ResultSet rs) throws SQLException {
                News news = null;
                if(rs.first()){
                    news = new News();
                    news.setBrief(rs.getString(DAOUtil.NEWS_BRIEF));
                    news.setTitle(rs.getString(DAOUtil.NEWS_TITLE));
                    news.setId(rs.getInt(DAOUtil.NEWS_ID));
                    news.setContent(rs.getString(DAOUtil.NEWS_CONTENT));
                    news.setDate(rs.getDate(DAOUtil.NEWS_NDATE));
                }
                return news;
            }
        }, id);
    }
    
    @Override
    public boolean add(News news) throws JdbcTemplateException {
        return jdbcTemplate.queryForCUD(SQLQuery.NEWS_ADD, news.getId(), 
                news.getTitle(), news.getDate(), 
                news.getBrief(), news.getContent());
    }
}
