package com.amazonia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;

@Configuration
public class ConfiguracionGlobal {
	@Bean
	String rutaRaiz(ServletContext servletContext) {
		return servletContext.getRealPath("/");
	}
}
