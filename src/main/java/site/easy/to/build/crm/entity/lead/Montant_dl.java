package site.easy.to.build.crm.entity.lead;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// Historique du depense ... 

@Entity
@Table (name = "montant_dl")
public class Montant_dl {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "montant", nullable = false, precision = 15, scale = 2)
    @NotNull(message = "Montant is required")
    @Digits(integer = 15, fraction = 2, message = "Montant must be a valid number with up to 2 decimal places")
    @DecimalMin(value = "0.00", inclusive = true, message = "Montant must be greater than or equal to 0.00")
    @DecimalMax(value = "999999999999.99", inclusive = true, message = "Montant must be less than or equal to 999999999999.99")
    private BigDecimal montant;

    @Column(name = "daty", nullable = false)
    @NotBlank(message = "Daty is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
    private String daty; // considerer comme daty de modification

    @OneToOne
    @JoinColumn (name = "depense_id", referencedColumnName = "depense_id")
    private Depense_lead depenseLead;



    public Montant_dl () {}
    public Montant_dl (BigDecimal montant, String daty, Depense_lead depense_lead) {
        this.montant = montant;
        this.daty = daty;
        this.depenseLead = depense_lead;
    }



    public String getDaty() { return daty; }
    public Integer getId() { return id; }
    public BigDecimal getMontant() { return montant; }

    public Depense_lead getDepenseLead() { return depenseLead; }
    public void setDepenseLead(Depense_lead depenseLead) { this.depenseLead = depenseLead; }
    public void setDaty(String daty) { this.daty = daty; }
    public void setId(Integer id) { this.id = id; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }





}
