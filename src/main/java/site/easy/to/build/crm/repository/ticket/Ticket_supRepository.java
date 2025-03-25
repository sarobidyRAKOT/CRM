package site.easy.to.build.crm.repository.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.ticket.Ticket_sup;


@Repository
public interface Ticket_supRepository extends  JpaRepository <Ticket_sup, Integer> {
    
}
