package com.jmp.model;

import javax.persistence.*;

/**
 * Created by Ales on 28.05.2017.
 */
@Entity
@Table(name="USER_DOCUMENT")
public class UserDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "document_auto_increment")
    @SequenceGenerator(name = "document_auto_increment", sequenceName = "DOC_SEQ")
    @Column(name = "DOCUMENT_ID")
    private int id;

    @Column(name="NAME")
    private String name;

    @Lob
    @Column(name="content", nullable=false)
    private byte[] content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    public UserDocument() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

