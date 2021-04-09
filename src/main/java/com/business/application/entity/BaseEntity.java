package com.business.application.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private String id;

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
