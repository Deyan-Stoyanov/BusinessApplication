package com.business.application.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private Integer id;

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
