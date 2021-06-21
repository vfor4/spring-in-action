package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tacos.service.IngredientByIdConverter;

@SpringBootApplication
@ComponentScan
public class TacoCloudApplication implements WebMvcConfigurer {

//	@Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new IngredientByIdConverter());
//    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login");
		registry.addViewController("/logout");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
