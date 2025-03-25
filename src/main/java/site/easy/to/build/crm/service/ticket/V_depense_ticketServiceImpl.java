package site.easy.to.build.crm.service.ticket;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.repository.ticket.V_depense_ticketRepository;

@Service
public class V_depense_ticketServiceImpl implements V_depense_ticketService {
    
    private final V_depense_ticketRepository depense_ticketRepository;

    public V_depense_ticketServiceImpl (V_depense_ticketRepository depense_ticketRepository) {
        this.depense_ticketRepository = depense_ticketRepository;
    }

    @Override
    public double sumMontantByCustomerId (int customerId) {
        BigDecimal m = depense_ticketRepository.sumMontantByCustomerId(customerId);

        if (m == null) return 0;
        return m.doubleValue();
    }

    
}
