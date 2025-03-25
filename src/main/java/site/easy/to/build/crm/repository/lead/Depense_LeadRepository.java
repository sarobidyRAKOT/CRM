package site.easy.to.build.crm.repository.lead;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.lead.Depense_lead;

@Repository
public interface Depense_LeadRepository extends JpaRepository <Depense_lead, Integer> {
    

    public Depense_lead findByLeadLeadId (int leadId);
    // public Depense_lead findTopByIdLeadOrderByDatyDesc(Long idLead);



}
