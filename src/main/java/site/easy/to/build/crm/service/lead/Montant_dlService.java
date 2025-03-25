package site.easy.to.build.crm.service.lead;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.lead.Depense_lead;
import site.easy.to.build.crm.entity.lead.Montant_dl;
import site.easy.to.build.crm.repository.lead.Montant_dlRepository;

@Service
public class Montant_dlService {
    
    private final Montant_dlRepository montant_dlRepository;

    @Autowired
    public Montant_dlService (Montant_dlRepository montant_dlRepository) {
        this.montant_dlRepository = montant_dlRepository;
    }

    public Montant_dl save (Montant_dl montant_dl) {
        return montant_dlRepository.save(montant_dl);
    }

    public List<Montant_dl> findByDepense_leadOrderDesc (Depense_lead depense_lead) {
        Sort sort = Sort.by(Sort.Order.desc ("daty"));
        return montant_dlRepository.findByDepenseLead (depense_lead, sort);
    }

    // public List<Montant_dl> findByDepense_leadOrderDesc(Depense_lead depense_lead) {
    //     List<Montant_dl> montants = montant_dlRepository.findByDepenseLead(depense_lead);

    //     montants.sort((m1, m2) -> {
    //         try {
    //             LocalDate date1 = LocalDate.parse(m1.getDaty());
    //             LocalDate date2 = LocalDate.parse(m2.getDaty());
    //             return date2.compareTo(date1); // DESC
    //         } catch (DateTimeParseException e) {
    //             return 0; // Si erreur, ne pas changer l'ordre
    //         }
    //     });

    //     return montants;
    // }

    
    public List <Montant_dl> findAll () {
        return montant_dlRepository.findAll();
    }

}
