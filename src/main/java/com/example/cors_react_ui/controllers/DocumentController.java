package com.example.cors_react_ui.controllers;

import com.example.cors_react_ui.entities.Document;
import com.example.cors_react_ui.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {
      @Autowired
      private DocumentRepository repository;

      @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
      @GetMapping("/api/v1/document")
      public List<Document> getAll() {
            return repository.findAll();
      }
      @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
      @GetMapping("/api/v1/document/{id}")
      public Document getById(@PathVariable("id") Integer id) {
            return repository.getById(id);
      }

      @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
      @PutMapping("/api/v1/document/{id}")
      public Document edit(@PathVariable("id") Long id, @RequestBody Document document) {
            document.setId(id);
            return repository.save(document);
      }
}
