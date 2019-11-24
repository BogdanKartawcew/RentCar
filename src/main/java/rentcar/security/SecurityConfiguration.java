package rentcar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import static rentcar.propertiesenums.ControlersTexts.Constants.*;
import static rentcar.propertiesenums.Links.Constants.*;
import static rentcar.model.UserProfileType.Constants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenRepository tokenRepository;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(COMMON_EMPTY).permitAll()
                .antMatchers(SUPPORT_ADMIN_GHOST + "/**", SUPPORT_USERIMAGE_SHOW).access(roleText(new String[]{ADMIN, SUPERUSER}))
                .antMatchers(SUPPORT_MYPAGE_IMAGE + "/**", SUPPORT_USERIMAGE_SHOW).access(roleText(new String[]{USER, SUPERUSER, TEMP}))
                .antMatchers(SUPPORT_MAIN + "/**").access(roleText(new String[]{USER, ADMIN, SUPERUSER, TEMP}))
                .and().formLogin().loginPage(COMMON_LOGIN)
                .loginProcessingUrl(COMMON_LOGIN).usernameParameter(LOW_LOGIN).passwordParameter(LOW_PASSWORD).and() //do not change!
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                .tokenValiditySeconds(604800).and().csrf().and().exceptionHandling().accessDeniedPage(COMMON_ACCESSDENIED);
    }

    private String roleText(String[] args) {
        StringBuilder result = new StringBuilder("hasRole('");
        int size = args.length;
        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                result.append(args[i]);
                result.append("') or hasRole('");
            }
        }
        result.append(args[size - 1]);
        result.append("')");
        return String.valueOf(result);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        return new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
