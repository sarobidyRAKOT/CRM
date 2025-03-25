package site.easy.to.build.crm.entity.lead.views;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table (name = "v_depense_lead")
public class V_depense_lead {
    
    @Id
    @Column (name = "lead_id")
    private Integer leadId;
    @Column (name = "customer_id")
    private Integer customerId;
    @Column (name = "depense_id")
    private Integer depenseId;
    @Column (name = "montant")
    private BigDecimal montant;
    @Column (name = "daty")
    private Date daty;

    public V_depense_lead () {}
    public V_depense_lead (Integer leadId, Integer customerId, Integer depenseId, BigDecimal montant, Date daty) {
        this.leadId = leadId;
        this.customerId = customerId;
        this.depenseId = depenseId;
        this.montant = montant;
        this.daty = daty;
    }




    public Integer getCustomerId() { return customerId; }
    public Date getDaty() { return daty; }
    public Integer getDepenseId() { return depenseId; }
    public Integer getLeadId() { return leadId; }
    public BigDecimal getMontant() { return montant; }

    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setDaty(Date daty) { this.daty = daty; }
    public void setDepenseId(Integer depenseId) { this.depenseId = depenseId; }
    public void setLeadId(Integer leadId) { this.leadId = leadId; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

}
