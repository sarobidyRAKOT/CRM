package site.easy.to.build.crm.repository.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Customers.Taux_Alert;

@Repository
public interface Taux_AlertRepository extends JpaRepository <Taux_Alert, Integer> {

    public Taux_Alert findFirstByOrderByDatyDesc ();
    public Optional <Taux_Alert> findTopByDatyLessThanEqualOrderByDatyDesc (String daty);
    
}
