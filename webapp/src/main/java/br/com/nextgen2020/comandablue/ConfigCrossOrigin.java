package br.com.nextgen2020.comandablue;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ConfigCrossOrigin implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
                //.allowedMethods("GET", "POST", "PUT", "DELETE")
                //.allowedHeaders("COMANDA-BLUE-CLIENTE" /*, "header2"*/);
                //.exposedHeaders("header1", "header2")
                //.allowCredentials(false).maxAge(3600);
    }

}