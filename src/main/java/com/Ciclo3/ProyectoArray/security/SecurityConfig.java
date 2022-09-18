package com.Ciclo3.ProyectoArray.security;

import com.Ciclo3.ProyectoArray.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select correo,password,estado from empleado where correo=?")
                .authoritiesByUsernameQuery("select correo, rol from empleado where correo=?");
    }

///Permisos y restricciones de acceso
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","VerEmpresas/**").hasRole("ADMIN")
                .antMatchers("/AgregarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/GuardarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/EditarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/ActualizarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/EliminarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/VerEmpleados/**").hasRole("ADMIN")
                .antMatchers("/AgregarEmpleado/**").hasRole("ADMIN")
                .antMatchers("/GuardarEmpleado/**").hasRole("ADMIN")
                .antMatchers("/EditarEmpleado/**").hasRole("ADMIN")
                .antMatchers("/ActualizarEmpleado/**").hasRole("ADMIN")
                .antMatchers("/EliminarEmpleado/**").hasRole("ADMIN")
                .antMatchers("/EliminarEmpleado/**").hasRole("ADMIN")
                .antMatchers("/VerMovimientos/**").hasRole("ADMIN")
                .antMatchers("/EditarMovimiento/**").hasRole("ADMIN")
                .antMatchers("/ActualizarMovimiento/**").hasRole("ADMIN")
                .antMatchers("/EliminarMovimiento/**").hasRole("ADMIN")


                .antMatchers("/Empresa/**").hasRole("ADMIN")
                .antMatchers("/Empleado/**").hasRole("ADMIN")

                .antMatchers("/AgregarMovimiento").hasAnyRole("ADMIN","USER")

                .and().formLogin().successHandler(customSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/Denegado")
                .and().logout().permitAll();
    }
}
//pendiente agregar mas restricciones