package site.easy.to.build.crm.service.ticket;

import org.springframework.stereotype.Service;
import site.easy.to.build.crm.repository.ticket.Ticket_supRepository;
import site.easy.to.build.crm.entity.ticket.Ticket_sup;

@Service
public class Ticket_supServiceImpl implements Ticket_supService {

    private final Ticket_supRepository ticket_supRepository;

    public Ticket_supServiceImpl (Ticket_supRepository ticket_supRepository) {
        this.ticket_supRepository = ticket_supRepository;
    }


    @Override
    public Ticket_sup save (Ticket_sup ticket_sup) {
        return ticket_supRepository.save (ticket_sup);
    }



}
