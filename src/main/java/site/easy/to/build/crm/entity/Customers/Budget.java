package site.easy.to.build.crm.entity.Customers;

import java.math.BigDecimal;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import site.easy.to.build.crm.entity.User;

@Entity
@Table (name = "budget")
public class Budget {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;



    @Column(name = "daty", nullable = false)
    @NotBlank(message = "Daty is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
    private String daty;


    @Column(name = "montant", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Montant is required")
    @Digits(integer = 10, fraction = 2, message = "Montant must be a valid number with up to 2 decimal places")
    @DecimalMin(value = "0.00", inclusive = true, message = "Montant must be greater than or equal to 0.00")
    @DecimalMax(value = "9999999.99", inclusive = true, message = "Montant must be less than or equal to 9999999.99")
    private BigDecimal montant;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User user;



    public Budget () { }

    public Budget (String daty, BigDecimal montant, Customer customer, User user) {
        this.daty = daty;
        this.montant = montant;
        this.customer = customer;
        this.user = user;
    }


    public Integer getId() { return id; }
    
    public void setId(Integer id) { this.id = id; }


    public String getDaty() { return daty; }
    public BigDecimal getMontant() { return montant; }
    public void setDaty(String daty) { this.daty = daty; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

}
