package site.easy.to.build.crm.repository.lead;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Customers.Customer;
import site.easy.to.build.crm.entity.lead.Lead;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
    
    public Lead findByLeadId(int id);
    public List<Lead> findByCustomerCustomerId(int customerId);
    public List<Lead> findByManagerId(int userId);
    public List<Lead> findByEmployeeId(int userId);
    public List<Lead> findByEmployeeIdOrderByCreatedAtDesc(int employeeId, Pageable pageable);
    public List<Lead> findByManagerIdOrderByCreatedAtDesc(int managerId, Pageable pageable);
    public List<Lead> findByCustomerCustomerIdOrderByCreatedAtDesc(int customerId, Pageable pageable);
    @Query(
        value = "SELECT l.* FROM `trigger_lead` AS l \r\n"+
        "WHERE NOT EXISTS (\r\n"+
        "    SELECT 1 FROM `lead_sup` AS ls WHERE ls.lead_id = l.lead_id\r\n"+
        ");", 
        nativeQuery = true
    )
    public List <Lead> findAll_nonDeleted ();
    
    Lead findByMeetingId(String meetingId);
    long countByEmployeeId(int employeeId);
    long countByManagerId(int managerId);
    long countByCustomerCustomerId(int customerId);
    void deleteAllByCustomer(Customer customer);


}
