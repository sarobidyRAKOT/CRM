package site.easy.to.build.crm.entity.lead;

import jakarta.persistence.*;

@Entity
@Table (name = "lead_sup")
public class Lead_sup {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn (name = "lead_id")
    private Lead lead;

    public Lead_sup () {
    
    }
    public Lead_sup (Integer id, Lead lead) {
        this.id = id;
        this.lead = lead;
    }

    public Lead getLead() { return lead; }
    public void setLead(Lead lead) { this.lead = lead; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

}
