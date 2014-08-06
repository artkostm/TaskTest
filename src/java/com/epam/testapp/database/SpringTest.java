/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.database;

import com.epam.testapp.database.mapper.JdbcTemplateException;
import com.epam.testapp.database.pool.DatabaseException;
import com.epam.testapp.model.News;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Artsiom_Chuiko
 */
public class SpringTest {
    public static void main(String[] args) throws JdbcTemplateException, DatabaseException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        INewsDAO dao = (NewsDAO)context.getBean("newsDAO");
        System.out.println(dao.getList());
        //News news = new News(0, "Hello epta", "Epta", "H1", new Date(2014, 6, 8));
        //System.out.println("Added - "+dao.add(news));
        News news = dao.fetchById(22);
        dao.remowe(news);
        System.out.println(dao.getList());
    }
}
