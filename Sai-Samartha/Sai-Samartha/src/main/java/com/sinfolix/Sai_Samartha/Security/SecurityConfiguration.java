package com.sinfolix.Sai_Samartha.Security;


import com.sinfolix.Sai_Samartha.Service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {


    private final CustomUserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(CustomUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/register").permitAll();
                authorize.requestMatchers("/admin/**").hasRole("ADMIN");
                authorize.requestMatchers("/orders/**").hasAnyRole("ADMIN", "USER");
                authorize.anyRequest().fullyAuthenticated();
                })
                .formLogin(auth->{
                    auth.loginProcessingUrl("/login");
                    auth.defaultSuccessUrl("/hello",true);
                })

//                .oauth2Login(oauth2login -> {
//                oauth2login.successHandler(( request,  response,  authentication) -> response.sendRedirect("/home"));
//                })
//                 .oauth2Login(oauth2login -> {
//                     oauth2login.defaultSuccessUrl("/loginSuccess",true);
//                 })
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/login")
//                        .successHandler((request, response, authentication) -> {
//                            // Redirect to the home page on success
//                            response.sendRedirect("/home");
//                        })
//                )
                .sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                })
                .rememberMe(remember->remember.disable())
                .logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        )
//                .exceptionHandling(exception->{
//                   exception.authenticationEntryPoint((request, response, authException) -> {
//                       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                       response.getWriter().write("Unauthorized: Authentication token required.");
//                   });
//                })
                 .build();

    }


}