/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.testapp.model;

import java.sql.Date;

/**
 *
 * @author Artsiom_Chuiko
 */
public class News {
    private int id;
    private String content;
    private String brief;
    private String title;
    private Date date;

    public News() {
    }

    public News(int id, String content, String brief, String title, Date date) {
        this.id = id;
        this.content = content;
        this.brief = brief;
        this.title = title;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.content != null ? this.content.hashCode() : 0);
        hash = 53 * hash + (this.brief != null ? this.brief.hashCode() : 0);
        hash = 53 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 53 * hash + (this.date != null ? this.date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final News other = (News) obj;
        if ((this.content == null) ? 
                (other.content != null) : !this.content.equals(other.content)) {
            return false;
        }
        if ((this.brief == null) ? 
                (other.brief != null) : !this.brief.equals(other.brief)) {
            return false;
        }
        if ((this.title == null) ? 
                (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if (this.date != other.date && 
                (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "News{" + "content=" + 
                content + ", brief=" + 
                brief + ", title=" + 
                title + ", date=" + 
                date + '}';
    }
    
    
}
