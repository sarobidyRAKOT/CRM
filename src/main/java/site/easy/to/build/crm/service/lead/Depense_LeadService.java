package site.easy.to.build.crm.service.lead;



import site.easy.to.build.crm.entity.lead.Depense_lead;

public interface Depense_LeadService {

    public Depense_lead save(Depense_lead depense_lead);
    public Depense_lead findByLeadLeadId (int leadId);
}
