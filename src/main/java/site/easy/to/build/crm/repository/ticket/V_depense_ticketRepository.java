package site.easy.to.build.crm.repository.ticket;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.ticket.views.V_depense_ticket;

@Repository
public interface V_depense_ticketRepository extends JpaRepository <V_depense_ticket, Integer> {

    @Query("SELECT SUM(v.montant) FROM V_depense_ticket v WHERE v.customerId = :customerId")
    public BigDecimal sumMontantByCustomerId(Integer customerId);
    
}
