package com.example.termplannerapp.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "terms")
public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private String termTitle;
    private String termStartDate;

    public String getTermStartDate() {
        return termStartDate;
    }

    //for creating term and assigning values individually
    @Ignore
    public TermEntity(Date date, String trim) {
    }

    //for editing an existing term
    public TermEntity(int id, Date date, String termTitle, String termStartDate) {
        this.id = id;
        this.date = date;
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
    }

    //for creating new term and assigning id individually
    @Ignore
    public TermEntity(Date date, String termTitle, String termStartDate) {
        this.date = date;
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStartDate(String termStartDate) {
        return this.termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    //for debugging
    @Override
    public String toString() {
        return "TermEntity{" +
                "id=" + id +
                ", date=" + date +
                ", termTitle='" + termTitle + '\'' +
                ", termStartDate'" + termStartDate +
                '}';
    }

}
