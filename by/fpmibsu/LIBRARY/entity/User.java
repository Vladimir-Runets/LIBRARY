package by.fpmibsu.LIBRARY.entity;

import java.util.List;
public class User {
    private int UserID;
    private String login;
    private String password;
    private String mail;
    public List<Complaints> complaints;
    public List<Bookmark> bookmarksOfUser;
    public Literature literatureOfAuthor;
    public User(){}
    public User(String login){this.login = login;}

    public User(String login, String password, String mail) {
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public User(String login, String password, String mail, List<Complaints> complaints,
                List<Bookmark> bookmarksOfUser, Literature literatureOfAuthor) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.complaints = complaints;
        this.bookmarksOfUser = bookmarksOfUser;
        this.literatureOfAuthor = literatureOfAuthor;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Complaints> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaints> complaints) {
        this.complaints = complaints;
    }

    public List<Bookmark> getBookmarksOfUser() {
        return bookmarksOfUser;
    }

    public void setBookmarksOfUser(List<Bookmark> bookmarksOfUser) {
        this.bookmarksOfUser = bookmarksOfUser;
    }

    public Literature getLiteratureOfAuthor() {
        return literatureOfAuthor;
    }

    public void setLiteratureOfAuthor(Literature literatureOfAuthor) {
        this.literatureOfAuthor = literatureOfAuthor;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID of User='" + UserID + '\'' +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", mail='" + mail + '\'' +
                '}';
    }
}