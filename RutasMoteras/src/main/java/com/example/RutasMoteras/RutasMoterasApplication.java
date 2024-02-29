package com.example.RutasMoteras;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RutasMoterasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RutasMoterasApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("RutasMoteras API")
						.description("RutasMoteras API")
						.contact(new Contact()
								.name("Ivan Cabrera")
								.email("ivanca2003@gmail.com")
								.url("http://portal.edu.gva.es/iesmaciaabela"))
						.version("1.0"));
	}

}
