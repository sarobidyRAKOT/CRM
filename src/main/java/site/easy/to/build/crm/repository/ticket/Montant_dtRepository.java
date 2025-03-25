package site.easy.to.build.crm.repository.ticket;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.ticket.Depense_Ticket;
import site.easy.to.build.crm.entity.ticket.Montant_dt;


@Repository
public interface Montant_dtRepository extends JpaRepository <Montant_dt, Integer> {
    

    List <Montant_dt> findByDepenseTicket (Depense_Ticket depense_Ticket, Sort sort);

}
