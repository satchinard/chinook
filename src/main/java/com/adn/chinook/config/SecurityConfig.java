package com.adn.chinook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 *
 * @author cagecfi
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                //                .antMatchers("/login").access("hasRole('ADMIN')")
//                //                .anyRequest().authenticated().accessDecisionManager(unanimous())
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .loginProcessingUrl("/doLogin")
//                .and()
//                .logout().permitAll().logoutUrl("/logout")
//                .and()
//                .rememberMe()
//                .and()
//                .formLogin().and().httpBasic()
//                .and()
//                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/home.html").permitAll()
                .antMatchers("/chinook-documentation.html").permitAll()
                .antMatchers("/monitoring").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                //                .antMatchers(HttpMethod.GET).permitAll()
                //                .antMatchers(HttpMethod.POST).permitAll()
                //                .antMatchers(HttpMethod.PUT).permitAll()
                //                .antMatchers(HttpMethod.DELETE).permitAll()
                //                .anyRequest().permitAll()
                .and().formLogin().disable();

        http
                .headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");

        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

//        http.requiresChannel().anyRequest().requiresSecure();
//        http.requiresChannel()
//                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
//                .requiresSecure();
//
//        http.authorizeRequests()
//                .antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
//                .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//                .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//                .antMatchers("/delete/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN");
//    }
//    @Bean
//    public AccessDecisionManager unanimous() {
//        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(
//                new RoleVoter(), new AuthenticatedVoter(), new WebExpressionVoter());
//        return new UnanimousBased(decisionVoters);
//    }
//
//    class RealTimeLockVoter implements AccessDecisionVoter<Object> {
//
//        @Override
//        public boolean supports(ConfigAttribute ca) {
//            return true;
//        }
//
//        @Override
//        public boolean supports(Class<?> type) {
//            return true;
//        }
//
//        @Override
//        public int vote(Authentication a, Object s, Collection<ConfigAttribute> clctn) {
//            if (LockedUsers.isLocked(a.getName())) {
//                return ACCESS_DENIED;
//            }
//            return ACCESS_GRANTED;
//        }
//
//    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
