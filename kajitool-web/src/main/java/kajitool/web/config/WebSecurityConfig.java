package kajitool.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().csrfTokenRepository(
                CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and().oauth2Login()
        .and().authorizeRequests()
            .mvcMatchers(HttpMethod.POST, "/api/**/*").authenticated()
            .mvcMatchers(HttpMethod.PUT, "/api/**/*").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/api/**/*").authenticated()
            .anyRequest().permitAll()
        ;
        // @formatter:on
    }
}