package org.gfg.junitmock.demo.Configuration;

import org.gfg.junitmock.demo.Repository.MyUserRepository;
import org.gfg.junitmock.demo.Repository.UserRepository;
import org.gfg.junitmock.demo.model.MyUser;
import org.gfg.junitmock.demo.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.ldapAuthentication().
//                userDnPatterns(dataSource).
//                usersByUsernameQuery("select email, password, enabled from user where email = ?").
//                authoritiesByUsernameQuery("select email, authorities from user where email = ?");
//
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.jdbcAuthentication().
//                dataSource(dataSource).
//                usersByUsernameQuery("select email, password, enabled from user where email = ?").
//                authoritiesByUsernameQuery("select email, authorities from user where email = ?");
//
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(myUserService);

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.inMemoryAuthentication().
//        withUser("John").
//        password("john123").
//        authorities("dev").
//        and().
//        withUser("David").
//        password("david123").
//        authorities("qa");
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                csrf().disable().
                httpBasic().and().
                authorizeRequests().
                antMatchers("/testCode/**","/userHome/**").hasAnyAuthority("qa").
                antMatchers("/developCode/**").hasAnyAuthority("dev").
                antMatchers("/deployCode").hasAnyAuthority("qa", "dev").
                antMatchers("/home").permitAll().
                and().
                formLogin();
    }

    @Bean
    public PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder getPE(){
//        return NoOpPasswordEncoder.getInstance();
//    }



}
