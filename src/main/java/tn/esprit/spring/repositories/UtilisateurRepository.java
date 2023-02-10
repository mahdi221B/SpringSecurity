package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.User;
@Repository
public interface UtilisateurRepository extends JpaRepository<User, Integer> {
    @Query("Select u FROM User u where u.name = :userName")
    User findUtilisateurByName(@Param("userName") String userName);

    @Query("Select u FROM User u where u.email = :email")
    User findUtilisateurByEmail(@Param("email") String email);

}