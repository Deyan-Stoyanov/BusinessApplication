package com.business.application.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public abstract class BaseEntity {
    private String id;

    public BaseEntity() {
    }

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
