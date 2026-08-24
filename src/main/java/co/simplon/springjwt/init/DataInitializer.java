package co.simplon.springjwt.init;

import co.simplon.springjwt.entity.UserEntity;
import co.simplon.springjwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.simplon.springjwt.entity.RoleEntity;
import co.simplon.springjwt.entity.TodoEntity;
import co.simplon.springjwt.repository.RoleRepository;
import co.simplon.springjwt.repository.TodoRepository;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TodoRepository todoRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            TodoRepository todoRepositoryInjected, RoleRepository roleRepositoryInjected, UserRepository userRepositoryInjected, PasswordEncoder passwordEncoderInjected) {
        this.todoRepository = todoRepositoryInjected;
        this.roleRepository = roleRepositoryInjected;
        this.userRepository = userRepositoryInjected;
        this.passwordEncoder = passwordEncoderInjected;
    }

    @Override
    public void run(String... args) throws Exception {
        this.todoRepository.save(new TodoEntity("Clone the project", true));
        this.todoRepository.save(new TodoEntity("Test the API", true));
        this.todoRepository.save(new TodoEntity("Add basic authentication"));

        RoleEntity roleUser = new RoleEntity();
        roleUser.setAuthority("ROLE_USER");
        roleRepository.save(roleUser);

        RoleEntity roleAdmin = new RoleEntity();
        roleAdmin.setAuthority("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        // ajout du user de bastien pour faire fonctionner le front

        UserEntity bastien = new UserEntity();
        bastien.setUsername("bastien@example.com");
        bastien.setPassword(passwordEncoder.encode("tacostacos"));
        bastien.setAuthorities(Set.of(roleUser));
        userRepository.save(bastien);
    }

}
