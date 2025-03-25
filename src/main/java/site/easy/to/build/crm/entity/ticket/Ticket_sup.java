package site.easy.to.build.crm.entity.ticket;

import jakarta.persistence.*;

@Entity
@Table (name = "ticket_sup")
public class Ticket_sup {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn (name = "ticket_id")
    private Ticket ticket;

    public Ticket_sup () {
    
    }
    public Ticket_sup (Integer id, Ticket ticket) {
        this.id = id;
        this.ticket = ticket;
    }

    public Ticket getTicket () { return ticket; }
    public void setTicket (Ticket ticket) { this.ticket = ticket; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

}
