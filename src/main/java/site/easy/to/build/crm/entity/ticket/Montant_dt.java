package site.easy.to.build.crm.entity.ticket;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table (name = "montant_dt")
public class Montant_dt {
    
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
    private Depense_Ticket depenseTicket;
    
    @Transient private double depassement;


    public Montant_dt () {}
    public Montant_dt (BigDecimal montant, String daty, Depense_Ticket depenseTicket) {
        this.montant = montant;
        this.daty = daty;
        this.depenseTicket = depenseTicket;
    }
    

    public String getDaty() { return daty; }
    public Depense_Ticket getDepenseTicket() { return depenseTicket; }
    public Integer getId() { return id; }
    public BigDecimal getMontant() { return montant; }

    public void setDaty(String daty) { this.daty = daty; }
    public void setDepenseTicket(Depense_Ticket depenseTicket) { this.depenseTicket = depenseTicket; }
    public void setId(Integer id) { this.id = id; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    
    
    
    public double getDepassement() { return depassement; }
    public void setDepassement(double depassement) { this.depassement = depassement; }

}
