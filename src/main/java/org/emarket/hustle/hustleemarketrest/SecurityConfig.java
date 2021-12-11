package org.emarket.hustle.hustleemarketrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf()
				.disable()

				.authorizeRequests()
				.antMatchers("/")
				.permitAll();
	}

	@Bean
	public BcryptSecurity Bcrypt()
	{
		return new BcryptSecurity();
	}

}
