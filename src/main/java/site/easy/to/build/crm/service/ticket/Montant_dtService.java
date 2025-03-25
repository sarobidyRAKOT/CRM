package site.easy.to.build.crm.service.ticket;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.ticket.Depense_Ticket;
import site.easy.to.build.crm.entity.ticket.Montant_dt;
import site.easy.to.build.crm.repository.ticket.Montant_dtRepository;

@Service
public class Montant_dtService {
    
    private final Montant_dtRepository montant_dtRepository;

    public Montant_dtService (Montant_dtRepository montant_dtRepository) {
        this.montant_dtRepository = montant_dtRepository;
    }
    

    public Montant_dt save (Montant_dt montant_dt) {
        return montant_dtRepository.save(montant_dt);
    }

    public List <Montant_dt> findByDepense_leadOrderDesc (Depense_Ticket depense_Ticket) {
        Sort sort = Sort.by(Sort.Order.desc ("daty"));
        return montant_dtRepository.findByDepenseTicket(depense_Ticket, sort);
    }

}
