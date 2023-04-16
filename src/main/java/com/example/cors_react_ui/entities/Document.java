package com.example.cors_react_ui.entities;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.util.Objects;

@Entity
@Table(name = "documents")
@SequenceGenerator(sequenceName = "documents_id_seq", name = "documents_id_seq", allocationSize = 1)
public class Document {
      @Id
      @GeneratedValue(strategy = GenerationType. SEQUENCE, generator =
            "documents_id_seq")
      @Column(nullable = false)
      private Long id;

      @Column(nullable = false)
      private String content;

      public Document() {
      }

      public Document(String content) {
            this.content = content;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Document)) return false;
            Document document = (Document) o;
            return id.equals(document.id) && content.equals(document.content);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, content);
      }

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getContent() {
            return content;
      }

      public void setContent(String content) {
            this.content = content;
      }
}
