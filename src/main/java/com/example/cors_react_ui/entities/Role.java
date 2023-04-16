package com.example.cors_react_ui.entities;


import com.example.cors_react_ui.enums.RolesAll;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter

@Entity
@Table(name = "roles")
@SequenceGenerator(sequenceName = "roles_id_seq", name = "roles_id_seq", allocationSize = 1)
public class Role {
      @Id
      @GeneratedValue(strategy = GenerationType. SEQUENCE, generator =
            "roles_id_seq")
      @Column(nullable = false)
      private Long id;
      @Column(nullable = false)
      private String role;

      public Role(RolesAll role) {
            this.role = role.toString() ;
      }
}
