package site.easy.to.build.crm.entity.ticket;


import jakarta.persistence.*;
import site.easy.to.build.crm.entity.Customers.Customer;

@Entity
@Table (name = "depense_ticket")
public class Depense_Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depense_id")
    private int id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn (name = "ticket_id")
    private Ticket ticket;

    @OneToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;


    public Depense_Ticket () {}
    public Depense_Ticket (String description, Ticket ticket, Customer customer) {
        this.description = description;
        this.ticket = ticket;
        this.customer = customer;
    }

    public Customer getCustomer() { return customer; }
    public String getDescription() { return description; }
    public int getId() { return id; }
    public Ticket getTicket() { return ticket; }
    


    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setDescription(String description) { this.description = description; }
    public void setId(int id) { this.id = id; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
}
