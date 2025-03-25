package site.easy.to.build.crm.entity.ticket.views;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table (name = "v_depense_ticket")
public class V_depense_ticket {
    
    @Id
    @Column (name = "ticket_id")
    private Integer ticketId;
    @Column (name = "customer_id")
    private Integer customerId;
    @Column (name = "depense_id")
    private Integer depenseId;
    @Column (name = "montant")
    private BigDecimal montant;
    @Column (name = "daty")
    private Date daty;

    public V_depense_ticket () {}
    public V_depense_ticket (Integer ticketId, Integer customerId, Integer depenseId, BigDecimal montant, Date daty) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.depenseId = depenseId;
        this.montant = montant;
        this.daty = daty;
    }




    public Integer getCustomerId() { return customerId; }
    public Date getDaty() { return daty; }
    public Integer getDepenseId() { return depenseId; }
    public Integer getTicketId() { return ticketId; }
    public BigDecimal getMontant() { return montant; }

    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setDaty(Date daty) { this.daty = daty; }
    public void setDepenseId(Integer depenseId) { this.depenseId = depenseId; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public void setTicketId(Integer ticketId) { this.ticketId = ticketId; }
}
