package site.easy.to.build.crm.repository.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Customers.V_ttl_budget;

@Repository
public interface V_ttl_budgetRepository extends JpaRepository <V_ttl_budget, Integer> {
    
    public V_ttl_budget findByCustomerId (Integer customerId);

}
