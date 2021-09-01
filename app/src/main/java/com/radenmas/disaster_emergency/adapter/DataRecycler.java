package com.radenmas.disaster_emergency.adapter;

public class DataRecycler {
    //data User/Admin
    String uid, role, username, email, phone, password, born;

    //data Artikel
    String title, category, isi, images;

    public DataRecycler() {
    }

    public DataRecycler(String uid, String role, String username, String email, String phone, String password, String born, String title, String category, String isi, String images) {
        this.uid = uid;
        this.role = role;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.born = born;
        this.title = title;
        this.category = category;
        this.isi = isi;
        this.images = images;
    }

    public String getUid() {
        return uid;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getBorn() {
        return born;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getIsi() {
        return isi;
    }

    public String getImages() {
        return images;
    }
}
