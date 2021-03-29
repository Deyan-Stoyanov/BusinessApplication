package com.business.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "documents")
public class Document extends BaseEntity {

    private String documentName;
    private byte[] documentContent;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(name = "documentName", nullable = false)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Lob
    @Column(name = "documentContent", columnDefinition="BLOB")
    public byte[] getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(byte[] documentContent) {
        this.documentContent = documentContent;
    }
}
