package ru.Vlad.Spring.PharmacyApp.AuthService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

}
