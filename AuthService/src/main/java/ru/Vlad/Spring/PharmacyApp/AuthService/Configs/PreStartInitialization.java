package ru.Vlad.Spring.PharmacyApp.AuthService.Configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.Vlad.Spring.PharmacyApp.AuthService.Model.Role;
import ru.Vlad.Spring.PharmacyApp.AuthService.Repository.RoleRepository;


@RequiredArgsConstructor
@Component
public class PreStartInitialization implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setName("ROLE_USER");
        role.setDescription("USERS ROLE GRANTS");
        roleRepository.save(role);

    }
}
