package site.easy.to.build.crm.service.lead;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.repository.lead.V_depense_leadRepository;

@Service
public class V_depense_leadServiceImpl implements V_depense_leadService {

    private final V_depense_leadRepository depense_leadRepository;

    public V_depense_leadServiceImpl (V_depense_leadRepository depense_leadRepository) {
        this.depense_leadRepository = depense_leadRepository;
    }

    @Override
    public double sumMontantByCustomerId (int customerId) {
        BigDecimal m = depense_leadRepository.sumMontantByCustomerId(customerId);

        if (m == null) return 0;
        return m.doubleValue();
    }

    
        
}
