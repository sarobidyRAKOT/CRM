package site.easy.to.build.crm.repository.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Customers.Budget;

@Repository
public interface BudgetRepository extends JpaRepository <Budget, Integer> {
    

}
