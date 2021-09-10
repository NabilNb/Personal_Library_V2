package com.example.personallibraryv2;

public class bookLendInfo {
    private String bookName;
    private String bookAuthor;
    private String lName;
    private String lNumber;
    private String lMail;

    public bookLendInfo() {
    }

    public bookLendInfo(String bookName, String bookAuthor, String lName, String lNumber, String lMail) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.lName = lName;
        this.lNumber = lNumber;
        this.lMail = lMail;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlNumber() {
        return lNumber;
    }

    public void setlNumber(String lNumber) {
        this.lNumber = lNumber;
    }

    public String getlMail() {
        return lMail;
    }

    public void setlMail(String lMail) {
        this.lMail = lMail;
    }
}
