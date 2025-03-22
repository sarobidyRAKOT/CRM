package site.easy.to.build.crm.service.init_base;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Init_DBA {
    

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // public void init_database () throws IOException {

    //     jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
    //     // jdbcTemplate.execute("DROP DATABASE IF EXISTS crm");
    //     // jdbcTemplate.execute("CREATE DATABASE crm");
        
    //     // SUPRESSION DES TABLES
    //     String script_tables = new String(Files.readAllBytes(Paths.get("src/main/resources/delete_tables.sql")));
    //     // jdbcTemplate.batchUpdate (script_tables); 
        
    //     String[] drops = script_tables.split(";\n");
    //     for (String string : drops) {
    //         // System.err.println(string);
    //         jdbcTemplate.execute(string.trim()+";");
    //     }

    //     jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
        
    //     Path path = Paths.get("src/main/resources/schema.sql");
    //     String sqlScript = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    //     jdbcTemplate.execute(sqlScript);

    // }

    public void init_database () throws IOException {
        try {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
            String script_tables = new String(Files.readAllBytes(Paths.get("src/main/resources/delete_tables.sql")));
            String[] drops = script_tables.split(";\n");
            for (String string : drops) {
                jdbcTemplate.execute(string.trim() + ";");
            }
    
            // Exécution du script de création de schéma
            // Path path = Paths.get("src/main/resources/schema.sql");
            // String sqlScript = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            // jdbcTemplate.execute(sqlScript);
        } catch (Exception e) {
            e.printStackTrace();  // Affichez l'exception pour mieux comprendre l'erreur
        } finally {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
        }
    }
    
}
