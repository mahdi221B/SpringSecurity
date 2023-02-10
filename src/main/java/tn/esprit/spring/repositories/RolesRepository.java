package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
     @Query("Select r FROM Roles r where r.name = :roleName")
     Roles findRolesByName(@Param("roleName") String roleName);
}