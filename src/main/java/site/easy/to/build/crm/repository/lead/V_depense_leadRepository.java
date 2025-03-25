package site.easy.to.build.crm.repository.lead;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.lead.views.V_depense_lead;

@Repository
public interface V_depense_leadRepository extends JpaRepository <V_depense_lead, Integer> {

    @Query("SELECT SUM(v.montant) FROM V_depense_lead v WHERE v.customerId = :customerId")
    public BigDecimal sumMontantByCustomerId(Integer customerId);
    
}
