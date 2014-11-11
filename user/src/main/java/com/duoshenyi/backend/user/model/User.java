package com.duoshenyi.backend.user.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private String id;
    private String name;        //用户名
    private String account;     //帐号
    private byte[] password;    //密码

    //getters

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 128)
    public String getId() {
        return id;
    }
    @Column(name = "name",length = 100)
    public String getName() {
        return name;
    }
    @Column(name = "account",length = 100)
    public String getAccount() {
        return account;
    }
    @Column(name = "password")
    public byte[] getPassword() {
        return password;
    }

    @Transient
    public String getStringPassword(){
        return new String(password);
    }
    //setters

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (account != null ? !account.equals(user.account) : user.account != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return account != null ? account.hashCode() : 0;
    }
}
