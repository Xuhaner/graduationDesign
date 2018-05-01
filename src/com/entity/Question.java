package com.entity;

import java.util.Date;

public class Question {
    public int getQId() {
        return QId;
    }

    public void setQId(int QId) {
        this.QId = QId;
    }

    public String getQName() {
        return QName;
    }

    public void setQName(String QName) {
        this.QName = QName;
    }

    public String getQDesc() {
        return QDesc;
    }

    public void setQDesc(String QDesc) {
        this.QDesc = QDesc;
    }

    public String getQAuthor() {
        return QAuthor;
    }

    public void setQAuthor(String QAuthor) {
        this.QAuthor = QAuthor;
    }

    public Boolean getQIsRepeat() {
        return QIsRepeat;
    }

    public void setQIsRepeat(Boolean QIsRepeat) {
        this.QIsRepeat = QIsRepeat;
    }

    public Date getQCreateDate() {
        return QCreateDate;
    }

    public void setQCreateDate(Date QCreateDate) {
        this.QCreateDate = QCreateDate;
    }

    public String getQPassword() {
        return QPassword;
    }

    public void setQPassword(String QPassword) {
        this.QPassword = QPassword;
    }

    public Boolean getQIsOpen() {
        return QIsOpen;
    }

    public void setQIsOpen(Boolean QIsOpen) {
        this.QIsOpen = QIsOpen;
    }

    public Date getQExpireDate() {
        return QExpireDate;
    }

    public void setQExpireDate(Date QExpireDate) {
        this.QExpireDate = QExpireDate;
    }

    public int getQHits() {
        return QHits;
    }

    public void setQHits(int QHits) {
        this.QHits = QHits;
    }

    public int getQAnswer() {
        return QAnswer;
    }

    public void setQAnswer(int QAnswer) {
        this.QAnswer = QAnswer;
    }

    private int QId;
    private String QName;
    private String QDesc;
    private String QAuthor;
    private Boolean QIsRepeat;
    private Date QCreateDate;
    private String QPassword;
    private Boolean QIsOpen;
    private Date QExpireDate;
    private int QHits;
    private int QAnswer;
}
