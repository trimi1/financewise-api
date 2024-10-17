package be.helmo.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO : Sur les formulaires, il faut rajoouter un token CSRF / Passer en HTTPS à l'avenir
        // version sans protection CSRF : POST Valide mais non protégé
        //http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/users").permitAll().requestMatchers(HttpMethod.GET, "/users/**").permitAll().anyRequest().authenticated());
        // Version minimale : Pour les POST, il faut un token CSRF
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/users").permitAll().requestMatchers(HttpMethod.GET, "/users/**").permitAll().anyRequest().authenticated());

        return http.build();
    }


}