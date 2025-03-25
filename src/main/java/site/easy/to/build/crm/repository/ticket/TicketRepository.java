package site.easy.to.build.crm.repository.ticket;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Customers.Customer;
import site.easy.to.build.crm.entity.ticket.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    public Ticket findByTicketId(int ticketId);
    public List<Ticket> findByManagerId(int id);
    public List<Ticket> findByEmployeeId(int id);

    @Query(
        value = "SELECT t.* FROM trigger_ticket AS t \r\n"+
        "WHERE NOT EXISTS (\r\n"+
        "    SELECT 1 FROM ticket_sup AS ts WHERE ts.ticket_id = t.ticket_id\r\n"+
        ");", 
        nativeQuery = true
    )
    public List <Ticket> findAll_nonDeleted ();


    List<Ticket> findByCustomerCustomerId(Integer customerId);
    List<Ticket> findByManagerIdOrderByCreatedAtDesc(int managerId, Pageable pageable);
    List<Ticket> findByEmployeeIdOrderByCreatedAtDesc(int managerId, Pageable pageable);
    List<Ticket> findByCustomerCustomerIdOrderByCreatedAtDesc(int customerId, Pageable pageable);
    long countByEmployeeId(int employeeId);
    long countByManagerId(int managerId);
    long countByCustomerCustomerId(int customerId);
    void deleteAllByCustomer(Customer customer);
}
