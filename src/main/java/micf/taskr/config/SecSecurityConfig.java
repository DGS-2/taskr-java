package micf.taskr.config;

import micf.taskr.repository.user.*;
import micf.taskr.security.auth.*;
import micf.taskr.security.login.*;
import micf.taskr.security.logout.*;
import micf.taskr.security.user.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
@ComponentScan(basePackages = { "micf.taskr.security.*" })
// @ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

    @Autowired
    private UserRepository userRepository;

    public SecSecurityConfig() {
        super();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Create different roles that will be used to authenticate API endpoints
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // auth.authenticationProvider(authProvider());
        auth.inMemoryAuthentication()
            .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
            .and()
            .withUser("user").password(encoder().encode("userPass")).roles("USER");
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                .antMatchers(HttpMethod.TRACE, "/**").denyAll()
                .antMatchers(HttpMethod.PATCH, "/**").denyAll()
                .antMatchers(HttpMethod.HEAD, "/**").denyAll()
                .antMatchers("/built/**", "/*.scss", "/*.css", "/login**", "/*.js", "/static/**", "/images/**", "/js/**", "/style/**", "/*.ico", "/*.pdf").permitAll()
                .antMatchers("/user/updatePassword*","/user/savePassword*","/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .anyRequest().hasAuthority("READ_PRIVILEGE")
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
            .permitAll()
                .and()
            .sessionManagement()
                .invalidSessionUrl("/login")
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(3).sessionRegistry(sessionRegistry()).and()
                .sessionFixation().none()
            .and()
            .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll();
            //  .and()
            //     .rememberMe().rememberMeServices(rememberMeServices()).key("theKey");
    // @formatter:on
    }

    // beans

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    // @Bean
    // public RememberMeServices rememberMeServices() {
    //     CustomRememberMeServices rememberMeServices = new CustomRememberMeServices("theKey", userDetailsService, new InMemoryTokenRepositoryImpl());
    //     return rememberMeServices;
    // }
}