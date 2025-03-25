package site.easy.to.build.crm.service.customer;


import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.Customers.V_ttl_budget;
import site.easy.to.build.crm.repository.Customer.V_ttl_budgetRepository;

@Service
public class V_ttl_budgetService {
    
    private final V_ttl_budgetRepository ttl_budgetRepository;

    public V_ttl_budgetService (V_ttl_budgetRepository ttl_budgetRepository) {
        this.ttl_budgetRepository = ttl_budgetRepository;
    }

    public V_ttl_budget findByCustomerId (int customerId) {
        return ttl_budgetRepository.findByCustomerId(customerId);
    }
}
