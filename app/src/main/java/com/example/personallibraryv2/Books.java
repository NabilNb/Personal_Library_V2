package com.example.personallibraryv2;

public class  Books {
    private String name;
    private String author;
    private String hasRead;


    public Books() {
    }

    public String getHasRead() {
        return hasRead;
    }

    public void setHasRead(String hasRead) {
        this.hasRead = hasRead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Books(String name, String author, String hasRead) {
        this.name = name;
        this.author = author;
        this.hasRead = hasRead;
    }
}
