package site.easy.to.build.crm.service.file;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class Import_fichier_Service {
    
    //  IMPORTATION FICHIER CSV ...

    // @Autowired
    // private JdbcTemplate jdbcTemplate;

    public void importCsvData (String path_fichier) throws IOException, CsvValidationException {

        try (CSVReader csvReader = new CSVReader(new FileReader(path_fichier))) {
            String[] ligne;
            while ((ligne = csvReader.readNext()) != null) {
                // Assurez-vous que les données sont valides et correspondantes
                String name = ligne [0]; // Supposons que le fichier CSV a une colonne "name"
                
                // Insertion dans la base de données
                // String insertSql = "INSERT INTO roles (name) VALUES (?)";
                // jdbcTemplate.update(insertSql, name); // Insertion avec JdbcTemplate
            }
        }
        
    }
}
