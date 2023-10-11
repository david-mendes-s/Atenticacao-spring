package edu.projetosecurity.projetocomsecurity.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private SecurityDatabaseService securityService;

    @Autowired
    PasswordEncodes passwordEncoders;


    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
    
        var passwordEncoder = passwordEncoders.passwordEncoder();
    
        auth.userDetailsService(securityService)
            .passwordEncoder(passwordEncoder);
    }
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET, "/products/teste").hasAnyRole("USERS", "ADMS")
                        .requestMatchers(HttpMethod.POST, "products/create").hasAnyRole("ADMS")
                        .requestMatchers(HttpMethod.GET, "products/list").hasAnyRole("ADMS")
                        .requestMatchers(HttpMethod.GET, "products").permitAll()
                        .requestMatchers(HttpMethod.POST, "users/create").permitAll()
                        .anyRequest()
                        .authenticated()

                )
                .httpBasic(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable());

        return http.build();
    }

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsService() {
     * UserDetails user = User.withUsername("user")
     * .password("{noop}qwe123")
     * .roles("USERS")
     * .build();
     * UserDetails admDetails = User.withUsername("adm")
     * .password("{noop}qwe123")
     * .roles("ADMS")
     * .build();
     * return new InMemoryUserDetailsManager(user, admDetails);
     * }
     */
}
