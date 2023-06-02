/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.distrifoods.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
//    @Autowired
//    private UserDetailsService userDetailsService;
//    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
                // Definir los permisos de acceso a los endpoints
//                .mvcMatchers(HttpMethod.POST, "/api/login**").anonymous()
//                .antMatchers(HttpMethod.POST, "/api/login**").permitAll()
//                .antMatchers("/api/pedido*").authenticated() // Endpoint requiere autenticación
//                .anyRequest().authenticated(); // Cualquier otro endpoint requiere autenticación
//            .and()
//            .formLogin()
//                .permitAll()
//            .and()
//            .logout()
//                .logoutUrl("/api/logout") // URL de procesamiento del formulario de cierre de sesión
//                .logoutSuccessUrl("/api/login?logout") // URL de redirección después de un cierre de sesión exitoso
//                .permitAll();
       http.csrf()
            .disable()
            .authorizeRequests()
                .antMatchers("/validar**").permitAll()
               .antMatchers("/registro**").permitAll()
                .antMatchers("/save**").hasRole("ADMIN")
                .antMatchers("/update**").hasRole("ADMIN")
                .antMatchers("/delete**").hasRole("ADMIN")
                .antMatchers("/pedido**").hasRole("ADMIN")
                .antMatchers("/estado**").hasRole("ADMIN")
                .antMatchers("/usuario**").hasRole("ADMIN")
                .antMatchers("/cliente**").hasRole("ADMIN")
                .antMatchers("/credito/all/**").hasAnyRole("ADMIN","CLIENTE" )
                .antMatchers("/credito/byid/**").hasAnyRole("ADMIN","CLIENTE" )
                .antMatchers("/credito/byidcliente/**").hasAnyRole("ADMIN","CLIENTE" )
//                .antMatchers("/estado**").hasRole("ADMIN")
                .antMatchers("/login**").permitAll()
                .antMatchers("/restaurante/all/**").permitAll()
                .antMatchers("/restaurante/byid/**").permitAll()
                .antMatchers("/tipoproducto/all/**").permitAll()
                .antMatchers("/adicion/all/**").permitAll()
                .antMatchers("/adicion/byid/**").permitAll()
                .antMatchers("/producto/all/**").permitAll()
                .antMatchers("/producto/byid/**").permitAll()
                .antMatchers("/sucursal/all/**").permitAll()
                .antMatchers("/metodopago/all/**").permitAll()
                .anyRequest().authenticated()
                .and()
//            .formLogin()
//                .and()
                .cors()
                .and()
                .httpBasic();  //Esta linea es la que permite que mande en el header autenticacion basica
//            .logout()
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
                
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("anonimo")
                .password("{noop}0000")
                .roles("ANONIMO")
                .and()
                .withUser("client")
                .password("{noop}Client123$")
                .roles("CLIENTE")
                .and()
                .withUser("admin")
                .password("{noop}Admin123$")
                .roles("ADMIN");
        auth.parentAuthenticationManager(authenticationManagerBean());
//        auth.userDetailsService(userDetailsService);

    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}password")
//                .roles("CLIENTE")
//                .and()
//                .withUser("admin")
//                .password("{noop}password")
//                .roles("ADMIN");
//    }
}
