package site.easy.to.build.crm;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class CrmApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(CrmApplication.class, args);
//	}
//
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CrmApplication extends SpringBootServletInitializer {

    static {
        Dotenv dotenv = Dotenv.configure()
            .directory("src/main/resources") // Charge le fichier .env
            .ignoreIfMissing() // Évite les erreurs si .env est manquant
            .load();

        // Définit les variables d'environnement
        System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
        System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CrmApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}
}
