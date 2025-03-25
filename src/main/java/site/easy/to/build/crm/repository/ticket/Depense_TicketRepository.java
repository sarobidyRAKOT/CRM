package site.easy.to.build.crm.repository.ticket;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.ticket.Depense_Ticket;

@Repository
public interface Depense_TicketRepository extends JpaRepository <Depense_Ticket, Integer> {
    
    public Depense_Ticket findByTicketTicketId (int ticketId);
}
