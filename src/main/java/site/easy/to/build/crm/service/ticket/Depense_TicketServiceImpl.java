package site.easy.to.build.crm.service.ticket;

import java.util.List;

import org.springframework.stereotype.Service;
import site.easy.to.build.crm.repository.ticket.Depense_TicketRepository;
import site.easy.to.build.crm.entity.ticket.Depense_Ticket;

@Service
public class Depense_TicketServiceImpl implements Depense_TicketService {

    private final Depense_TicketRepository depense_ticketRepository;

    public Depense_TicketServiceImpl(Depense_TicketRepository depense_ticketRepository) {
        this.depense_ticketRepository = depense_ticketRepository;
    }


    @Override
    public Depense_Ticket save (Depense_Ticket depense_ticket) {
        return depense_ticketRepository.save(depense_ticket);
    }


    @Override
    public Depense_Ticket findByTicketTicketId (int ticketId) {
        return depense_ticketRepository.findByTicketTicketId(ticketId);
    }

}
