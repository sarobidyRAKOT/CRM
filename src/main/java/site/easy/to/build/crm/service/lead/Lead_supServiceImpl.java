package site.easy.to.build.crm.service.lead;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.lead.Lead_sup;
import site.easy.to.build.crm.repository.lead.Lead_supRepository;

@Service
public class Lead_supServiceImpl implements Lead_supService {

    private final Lead_supRepository lead_supRepository;

    public Lead_supServiceImpl (Lead_supRepository lead_supRepository) {
        this.lead_supRepository = lead_supRepository;
    }

    @Override
    public Lead_sup save (Lead_sup lead_sup) {
        return lead_supRepository.save(lead_sup);
    }
}
