package com.liamrankine.taskmanager.configurations;
import com.liamrankine.taskmanager.entities.AppUser;
import com.liamrankine.taskmanager.entities.Group;
import com.liamrankine.taskmanager.entities.GroupMembership;
import com.liamrankine.taskmanager.enumerations.GroupRole;
import com.liamrankine.taskmanager.repositories.AppUserRepository;
import com.liamrankine.taskmanager.repositories.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // fine for dev; re-enable later
                .cors(cors -> {})             // enable CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler((req, res, auth) -> res.setStatus(200))
                        .failureHandler((req, res, ex) -> res.setStatus(401))
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((req, res, auth) -> res.setStatus(200))
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(AppUserRepository userRepo) {
        return username -> {
            AppUser user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        };
    }

    @Bean
    CommandLineRunner seedData(
            AppUserRepository userRepo,
            GroupRepository groupRepo,
            PasswordEncoder encoder
    ) {
        return args -> {

            if (userRepo.findByUsername("Admin1").isPresent()) {

                if (userRepo.findByUsername("Admin2").isPresent()) {
                    return;
                }
                UserMaker(userRepo, groupRepo, encoder, "Admin2", "password");
                return;
            }

            UserMaker(userRepo, groupRepo, encoder, "Admin1", "password");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void UserMaker(AppUserRepository userRepo, GroupRepository groupRepo, PasswordEncoder encoder, String name, String password) {
        AppUser user = new AppUser();
        user.setUsername(name);
        user.setPassword(encoder.encode(password));

        userRepo.save(user);

        Group group = new Group();
        group.setName(name + "'s Group");

        groupRepo.save(group);

        GroupMembership membership = new GroupMembership(group, user, GroupRole.OWNER);
        group.addMembership(membership);
        user.addMembership(membership);
        userRepo.save(user);
    }
}

