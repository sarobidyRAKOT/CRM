package site.easy.to.build.crm.controller.lead;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.controller.Mere_Controller;
import site.easy.to.build.crm.entity.Customers.*;
import site.easy.to.build.crm.entity.lead.*;
import site.easy.to.build.crm.service.customer.*;
import site.easy.to.build.crm.service.lead.*;
import site.easy.to.build.crm.service.ticket.V_depense_ticketService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;

@Controller
@RequestMapping ("/depense-lead")
public class Depense_LeadController extends Mere_Controller {
    
    private final Depense_LeadService depense_LeadService;
    private final LeadService leadService;
    private final CustomerService customerService;
    private final Montant_dlService montant_dlService;
    private final V_depense_leadService v_depense_leadService;
    private final V_depense_ticketService v_depense_ticketService;
    private final V_ttl_budgetService v_ttl_budgetService;
    private final Taux_AlertService taux_AlertService;

    
    public Depense_LeadController (
        Depense_LeadService depense_LeadService, LeadService leadService, CustomerService customerService, AuthenticationUtils authenticationUtils,
        UserService userService, Montant_dlService montant_dlService, Taux_AlertService taux_AlertService,
        V_depense_leadService v_depense_leadService, V_depense_ticketService v_depense_ticketService, V_ttl_budgetService v_ttl_budgetService
    ) {
        super(authenticationUtils, userService);

        this.leadService = leadService;
        this.depense_LeadService = depense_LeadService;
        this.customerService = customerService;
        this.montant_dlService = montant_dlService;
        this.v_depense_leadService = v_depense_leadService;
        this.v_depense_ticketService = v_depense_ticketService;
        this.v_ttl_budgetService = v_ttl_budgetService;
        this.taux_AlertService = taux_AlertService;
    }


    @GetMapping ("/ajouter/depense-lead")
    public String page_depanseLead (Model model, Authentication authentication) {

        String pathErrorAccess = super.MANAGER_ACCESS(authentication);
        if (pathErrorAccess != null) {
            return pathErrorAccess;
        }

        List <Lead> leads = leadService.findAll_nonDeleted ();
        model.addAttribute("leads", leads);
        model.addAttribute("date_actu", LocalDate.now().toString());
        model.addAttribute("montant_dl", new Montant_dl ());
        return "lead/depense-lead";
    }

    @PostMapping ("/ajouter/depense-lead")
    public String ajouter_depenseLead (@ModelAttribute ("montant_dl") @Validated Montant_dl montant_dl, BindingResult bindingResult, Authentication authentication, Model model) {

        String pathErrorAccess = super.MANAGER_ACCESS(authentication);
        if (pathErrorAccess != null) return pathErrorAccess;


        // ************ begin VALIDATION ************
        Lead lead = leadService.findByLeadId(montant_dl.getDepenseLead().getLead().getLeadId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //  VERIFIER S'IL Y A UNE DEPENSE ...
        if (montant_dl.getDaty() != null && !montant_dl.getDaty().isEmpty()) {
            LocalDateTime dateTime = LocalDateTime.parse(montant_dl.getDaty() + " 00:00", formatter);
            if (lead.getCreatedAt().isAfter (dateTime)) { bindingResult.rejectValue("daty", "error.daty", "DATE DOIS ETRE APRES LA CREATION DU LEAD, Apres "+lead.getCreatedAt().toString()); }
        }
        if (bindingResult.hasErrors()) {
            List <Lead> leads = leadService.findAll();
            model.addAttribute("leads", leads);
            return "lead/depense-lead";
        }

        // ************ end VALIDATION ************


        double depense = v_depense_leadService.sumMontantByCustomerId(lead.getCustomer().getCustomerId());
        depense += v_depense_ticketService.sumMontantByCustomerId(lead.getCustomer().getCustomerId());
        V_ttl_budget ttl_budget = v_ttl_budgetService.findByCustomerId(lead.getCustomer().getCustomerId());
        double reste_budget = ttl_budget.getMontantTtl().doubleValue() - depense;
        Taux_Alert taux_Alert = taux_AlertService.find_before(montant_dl.getDaty());
        String m_depassement = null; 
        String m_taux = null;
        
        // // ************ DEPASSEMENT BUDGET ************
        if (reste_budget - montant_dl.getMontant().doubleValue() < 0) {
            m_depassement = " *** IL Y A DEPASSEMENT BUDGET : "+(reste_budget - montant_dl.getMontant().doubleValue()+" ARIARY");
        }
        // // ************ TAUX ATTEINT (Affichage fotsiny) ************
        if (taux_Alert != null) {
            double montant_alert = (reste_budget * taux_Alert.getTaux().doubleValue()) / 100;
            if (montant_alert <= montant_dl.getMontant().doubleValue()) {
                m_taux = " *** TAUX D'ALERT ATTEINT !!!! RESTE BUDGET : "+reste_budget;
            }
        }
        
        
        model.addAttribute("message_taux", m_taux);
        model.addAttribute("message_depassement", m_depassement);
        montant_dl.getDepenseLead().setLead(lead);
        model.addAttribute("montant_dl", montant_dl);
        return "lead/Valid-depense-lead"; 
    }

    


    @PostMapping ("/valid-ajouterDepassement/depense-lead")
    public String valid_depassement (@ModelAttribute ("montant_dl") @Validated Montant_dl montant_dl, BindingResult bindingResult, Authentication authentication, Model model) {
        
        String pathErrorAccess = super.MANAGER_ACCESS(authentication);
        if (pathErrorAccess != null) return pathErrorAccess;

        int leadId = montant_dl.getDepenseLead().getLead().getLeadId();
        
        Lead lead = leadService.findByLeadId(leadId);
        if (depense_LeadService.findByLeadLeadId(leadId) != null) {
            montant_dl.getDepenseLead().setLead(lead);
            model.addAttribute("depense_existed", "EFA MANANA DEPENSE IO LEAD IO !!");
            model.addAttribute("montant_dl", montant_dl);
            return "lead/Valid-depense-lead"; 
        }

        
        Customer customer = customerService.findByCustomerId(lead.getCustomer().getCustomerId());
        montant_dl.getDepenseLead().setLead(lead);
        montant_dl.getDepenseLead().setCustomer(customer);
        
        Depense_lead depense_leadSaved = depense_LeadService.save(montant_dl.getDepenseLead()); // SAVE
        montant_dl.setDepenseLead(depense_leadSaved);
        montant_dlService.save(montant_dl); // SAVE MONTANT DEPENSE LEAD ...


        return "redirect:/employee/lead/manager/all-leads";

    }




    @GetMapping ("/modifier/depense-lead/{lead_id}")
    public String page_modifDepense_lead (Model model, @PathVariable ("lead_id") int leadId, Authentication authentication) {
        
        String pathErrorAccess = super.MANAGER_ACCESS(authentication);
        if (pathErrorAccess != null) return pathErrorAccess;
        
        
        Depense_lead depense_lead = depense_LeadService.findByLeadLeadId (leadId);
        if (depense_lead == null) {
            return "redirect:/employee/lead/show/"+leadId;
        }
        
        
        List <Montant_dl> montant_dls = montant_dlService.findByDepense_leadOrderDesc (depense_lead);
        Lead lead = leadService.findByLeadId(leadId);
        model.addAttribute("lead", lead);
        model.addAttribute("montant_dl", montant_dls.get(0));
        
        return "lead/update-depense-lead";
    }



    @PostMapping ("/modifier/depense-lead")
    public String modifDepense_lead (@ModelAttribute ("montant_dl") @Validated Montant_dl montant_dl, BindingResult bindingResult, Authentication authentication, Model model) {
        
        String pathErrorAccess = super.MANAGER_ACCESS(authentication);
        if (pathErrorAccess != null) return pathErrorAccess;
        

        if (bindingResult.hasErrors()) {
            List <Lead> leads = leadService.findAll();
            model.addAttribute("leads", leads);
            return "lead/depense-lead";
        }

        // System.out.println(montant_dl.getDepense_lead().getLead().getLeadId());
        // Lead lead = leadService.findByLeadId(montant_dl.getDepenseLead().getLead().getLeadId());
        // Customer customer = customerService.findByCustomerId(lead.getCustomer().getCustomerId());
        
        // montant_dl.getDepenseLead().setCustomer(customer);
        // montant_dl.getDepenseLead().setLead(lead);
        int leadId = montant_dl.getDepenseLead().getLead().getLeadId();
        Depense_lead depense_lead = depense_LeadService.findByLeadLeadId (leadId);
        montant_dl.setDepenseLead(depense_lead);
        montant_dlService.save(montant_dl);

        // System.out.println(montant_dl.getDaty()+" "+montant_dl.getMontant()+" "+montant_dl.getDepenseLead().getLead().getLeadId());


        return "redirect:/employee/lead/manager/all-leads";
    }

}
