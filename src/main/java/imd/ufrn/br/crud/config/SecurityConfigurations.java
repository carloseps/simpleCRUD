package imd.ufrn.br.crud.config;

import imd.ufrn.br.crud.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        //.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/alunos/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/alunos/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/alunos/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/alunos/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/professores/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/professores/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/professores/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/professores/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/turmas/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/turmas/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/turmas/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/turmas/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/alunos/getall").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/professores/getall").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/turmas/getall").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}