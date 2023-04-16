package com.example.cors_react_ui.repositories;

import com.example.cors_react_ui.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

      @PostFilter("hasPermission(filterObject, 'READ')")
      List<Document> findAll();

      @PostAuthorize("hasPermission(returnObject, 'READ')")
      Document getById(Integer id);

      Document save(@Param("document") Document document);
}
