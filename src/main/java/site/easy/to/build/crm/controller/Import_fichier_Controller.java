package site.easy.to.build.crm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvValidationException;

import site.easy.to.build.crm.service.file.Import_fichier_Service;

@Controller
@RequestMapping ("/fichier/CSV")
public class Import_fichier_Controller {
    

    
    @Autowired
    private Import_fichier_Service importfichier_Service;


    @PostMapping("/import")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Le fichier est vide", HttpStatus.BAD_REQUEST);
        }

        try {
            // Enregistrez temporairement le fichier CSV sur le serveur
            String filePath = "/tmp/" + file.getOriginalFilename();
            file.transferTo(new java.io.File(filePath));

            // Appeler le service pour importer les données CSV dans la base de données
            importfichier_Service.importCsvData(filePath);

            // Retourner une réponse après l'importation
            return new ResponseEntity<>("Données importées avec succès", HttpStatus.OK);
        } catch (IOException | CsvValidationException e) {
            return new ResponseEntity<>("Erreur lors de l'importation du fichier CSV", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}

