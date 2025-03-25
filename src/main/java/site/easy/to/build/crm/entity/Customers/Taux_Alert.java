package site.easy.to.build.crm.entity.Customers;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table (name = "taux_alert")
public class Taux_Alert {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idTaux;

    @Column(name="taux", nullable=false, precision=5, scale=2)
    @NotNull(message = "Taux is required")
    @Digits(integer = 5, fraction = 2, message = "Taux must be a valid number with up to 2 decimal places")
    @DecimalMin(value = "0.00", inclusive = true, message = "Taux must be greater than or equal to 0.00")
    @DecimalMax(value = "99.99", inclusive = true, message = "Taux must be less than or equal to 99.99")
    private BigDecimal taux;


    @Column(name = "daty", nullable = false)
    @NotBlank(message = "Daty is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
    private String daty;


    public Taux_Alert () {}
    public Taux_Alert (BigDecimal taux, String daty) {
        this.taux = taux;
        this.daty = daty;
    }

    public String getDaty() { return daty; }
    public BigDecimal getTaux() { return taux; }
    public void setDaty(String daty) { this.daty = daty; }
    public void setTaux(BigDecimal taux) { this.taux = taux; }

    public Integer getIdTaux() {
        return idTaux;
    }
    public void setIdTaux(Integer idTaux) {
        this.idTaux = idTaux;
    }
}