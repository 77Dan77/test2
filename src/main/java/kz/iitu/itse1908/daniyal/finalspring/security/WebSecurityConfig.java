package kz.iitu.itse1908.daniyal.finalspring.security;

import kz.iitu.itse1908.daniyal.finalspring.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@DependsOn("encoder")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;
    private AuthenticationEntryPoint authEntryPoint;
    @Autowired
    public void setAuthEntryPoint(AuthenticationEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    PasswordEncoder passwordEncoder;


//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // All requests send to the Web Server request must be authenticated
        http.authorizeRequests().antMatchers("/api/admin/getAllUserDetails")
                .permitAll().and().authorizeRequests()
                .anyRequest().authenticated();


        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        String password = "123";

        //String encrytedPassword = this.passwordEncoder().encode(password);
        //System.out.println("Encoded password of 123=" + encrytedPassword);
        String encrytedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password of 123=" + encrytedPassword);
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
                mngConfig = auth.inMemoryAuthentication();

        UserDetails u1 = User.withUsername("daniyal").password(encrytedPassword).roles("ADMIN").build();
        UserDetails u2 = User.withUsername("client").password(encrytedPassword).roles("CLIENT").build();

//        List<kz.iitu.itse1908.daniyal.finalspring.models.UserDetails> users = userDetailsService.findAll();
//        List<UserDetails> userDetailsSecurity = new ArrayList<>();

//        for (int i = 0; i < users.size(); i++){
//            userDetailsSecurity.add(User.withUsername(users.get(i).getFname()).password(encrytedPassword).roles("CLIENT").build());
//        }

        mngConfig.withUser(u1);
        mngConfig.withUser(u2);

//        for (UserDetails userDetails: userDetailsSecurity){
//            mngConfig.withUser(userDetails);
//        }

    }

}