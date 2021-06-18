package com.exercise.configuration;

import com.exercise.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Import({BCryptPasswordEncoder.class, CustomUserDetailsService.class})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{

  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private UserDetailsService userDetailsService;

  private static final String[] AUTH_WHITELIST = {
      "/swagger-ui/**",
      "/v3/api-docs/**"
  };

  public WebSecurityConfiguration(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder)
  {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userDetailsService = customUserDetailsService;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception
  {
    httpSecurity.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers(HttpMethod.POST, "/v1/users/signup").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .anyRequest().authenticated()
        .and().addFilter(new AuthenticationFilter(authenticationManager()))
        .addFilter(new AuthorizationFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.headers().frameOptions().disable();

  }

  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
  {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource()
  {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
