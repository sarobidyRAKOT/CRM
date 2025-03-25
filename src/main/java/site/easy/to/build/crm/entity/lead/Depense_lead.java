package site.easy.to.build.crm.entity.lead;


import jakarta.persistence.*;
import site.easy.to.build.crm.entity.Customers.Customer;

@Entity
@Table (name = "depense_lead")
public class Depense_lead {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depense_id")
    private Integer depenseId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn (name = "lead_id", referencedColumnName = "lead_id")
    private Lead lead;

    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;

    // @OneToOne
    // @JoinColumn (name = "depense_id")
    // private Montant_dl montant_dl;

    public Depense_lead () {}
    public Depense_lead (String description, Lead lead, Customer customer) {
        this.description = description;
        this.lead = lead;
        this.customer = customer;
        // this.montant_dl = montant_dl;
    }



    public String getDescription() { return description; }
    public Lead getLead() { return lead; }
    public Customer getCustomer() { return customer; }
    public Integer getDepenseId() { return depenseId; }
    // public Montant_dl getMontant_dl() { return montant_dl; }
    
    public void setDescription(String description) { this.description = description; }
    public void setLead(Lead lead) { this.lead = lead; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setDepenseId(Integer depenseId) { this.depenseId = depenseId; }
    // public void setMontant_dl(Montant_dl montant_dl) { this.montant_dl = montant_dl; }

}
