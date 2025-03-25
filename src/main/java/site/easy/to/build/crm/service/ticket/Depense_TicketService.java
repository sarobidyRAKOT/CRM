package site.easy.to.build.crm.service.ticket;


import site.easy.to.build.crm.entity.ticket.Depense_Ticket;


public interface Depense_TicketService {

    public Depense_Ticket save (Depense_Ticket depense_ticket);
    public Depense_Ticket findByTicketTicketId (int ticketId);

}
