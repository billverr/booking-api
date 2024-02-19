package com.innovv8.booking;
// package com.innovv8.booking;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.ViewResolver;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import
// org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
// import org.thymeleaf.spring5.SpringTemplateEngine;
// import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//
// @Configuration
// @EnableWebMvc
// public class ThymeleafConfig extends WebMvcConfigurerAdapter {
//
// @Bean
// public SpringTemplateEngine templateEngine() {
// SpringTemplateEngine engine = new SpringTemplateEngine();
// // configure the engine if needed
// return engine;
// }
//
// @Bean
// public ViewResolver viewResolver() {
// ThymeleafViewResolver resolver = new ThymeleafViewResolver();
// resolver.setTemplateEngine(templateEngine());
// resolver.setCharacterEncoding("UTF-8");
// return resolver;
// }
// }