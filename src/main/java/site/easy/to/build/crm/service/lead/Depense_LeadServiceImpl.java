package site.easy.to.build.crm.service.lead;


import java.util.List;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.lead.Depense_lead;
import site.easy.to.build.crm.repository.lead.Depense_LeadRepository;

@Service
public class Depense_LeadServiceImpl implements Depense_LeadService {

    private final Depense_LeadRepository depense_leadRepository;

    public Depense_LeadServiceImpl (Depense_LeadRepository depense_leadRepository) {
        this.depense_leadRepository = depense_leadRepository;
    }

    @Override
    public Depense_lead save(Depense_lead depense_lead) {
        return depense_leadRepository.save(depense_lead);
    }

    @Override
    public Depense_lead findByLeadLeadId (int leadId) {
        return depense_leadRepository.findByLeadLeadId (leadId);
    }

    public double get_depense () {
        

        return 0;
    }

    
    
}
