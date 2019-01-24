package kajitool.web.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClientHttpRequestFactory requestFactory;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().csrfTokenRepository(
                CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and().oauth2Login()
                .tokenEndpoint()
                    .accessTokenResponseClient(buildAccessTokenResponseClient())
                .and()
                .userInfoEndpoint()
                    .userService(buildUserService())
        .and()
        .and().authorizeRequests()
            .mvcMatchers(HttpMethod.POST, "/api/**/*").authenticated()
            .mvcMatchers(HttpMethod.PUT, "/api/**/*").authenticated()
            .mvcMatchers(HttpMethod.DELETE, "/api/**/*").authenticated()
            .anyRequest().permitAll()
        ;
        // @formatter:on
    }

    private DefaultAuthorizationCodeTokenResponseClient buildAccessTokenResponseClient() {

        DefaultAuthorizationCodeTokenResponseClient client =
                new DefaultAuthorizationCodeTokenResponseClient();
        RestTemplate restTemplate = new RestTemplate(
                Arrays.asList(
                        new FormHttpMessageConverter(),
                        new OAuth2AccessTokenResponseHttpMessageConverter()));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        restTemplate.setRequestFactory(requestFactory);
        client.setRestOperations(restTemplate);

        return client;
    }

    private DefaultOAuth2UserService buildUserService() {
        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
        RestTemplate restTemplate2 = new RestTemplate();
        restTemplate2.setRequestFactory(requestFactory);
        userService.setRestOperations(restTemplate2);

        return userService;
    }

    @Bean
    public ClientHttpRequestFactory requestFactory(
            @Value("${application.proxy.host}") String host,
            @Value("${application.proxy.port}") int port,
            @Value("${application.proxy.user}") String user,
            @Value("${application.proxy.password}") String password) {

        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setProxy(new HttpHost(host, port));

        if (StringUtils.hasText(user) && StringUtils.hasText(password)) {
            BasicCredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(
                    new AuthScope(host, port),
                    new UsernamePasswordCredentials(user, password));
            builder.setDefaultCredentialsProvider(provider);
        }
        return new HttpComponentsClientHttpRequestFactory(builder.build());
    }
}