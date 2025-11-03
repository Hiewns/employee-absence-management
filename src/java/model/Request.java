/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author ultsu
 */
public class Request extends BaseModel{
    private int createdby;
    private java.sql.Timestamp createdtime;
    private java.sql.Date from;
    private java.sql.Date to;
    private String reason;
    private int status;
    private int reviewedby;
    private java.sql.Timestamp reviewedtime;

    public int getReviewedby() {
        return reviewedby;
    }

    public void setReviewedby(int reviewedby) {
        this.reviewedby = reviewedby;
    }

    public Timestamp getReviewedtime() {
        return reviewedtime;
    }

    public void setReviewedtime(Timestamp reviewedtime) {
        this.reviewedtime = reviewedtime;
    }

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    public Timestamp getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Timestamp createdtime) {
        this.createdtime = createdtime;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
