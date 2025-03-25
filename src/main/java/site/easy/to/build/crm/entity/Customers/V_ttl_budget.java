 package  site.easy.to.build.crm.entity.Customers;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table (name = "v_ttl_budget")
public class V_ttl_budget {
    
    @Id
    @Column (name = "customer_id")
    private Integer customerId;
    @Column (name = "montant_total")
    private BigDecimal montantTtl;

    public V_ttl_budget () {}
    public V_ttl_budget (Integer customerId, BigDecimal montantTtl) {
        this.customerId = customerId;
        this.montantTtl = montantTtl;
    }


    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public BigDecimal getMontantTtl() {
        return montantTtl;
    }
    public void setMontantTtl(BigDecimal montantTtl) { this.montantTtl = montantTtl; }
}
