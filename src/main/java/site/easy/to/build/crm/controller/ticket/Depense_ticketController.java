package site.easy.to.build.crm.controller.ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.controller.Mere_Controller;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.Customers.Budget;
import site.easy.to.build.crm.entity.Customers.Customer;
import site.easy.to.build.crm.entity.ticket.*;
import site.easy.to.build.crm.service.customer.BudgetService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.ticket.*;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;

@Controller
@RequestMapping ("/depense-ticket")
public class Depense_ticketController extends Mere_Controller {


    
    private final TicketService ticketService;
    private final CustomerService customerService;
    private final BudgetService budgetService;
    private final Depense_TicketService depense_TicketService;
    private final Montant_dtService montant_dtService;
    
    
    public Depense_ticketController(
        AuthenticationUtils authenticationUtils, UserService userService, TicketService ticketService, CustomerService customerService,
        BudgetService budgetService, Depense_TicketService depense_TicketService, Montant_dtService montant_dtService
    ) {
        super(authenticationUtils, userService); // SUPER

        this.ticketService = ticketService;
        this.customerService = customerService;
        this.budgetService = budgetService;
        this.depense_TicketService = depense_TicketService;
        this.montant_dtService = montant_dtService;
        
    }
    // public Depense_ticketController (
    //     TicketService ticketService, AuthenticationUtils authenticationUtils, UserService userService, CustomerService customerService,
    //     BudgetService budgetService, Depense_TicketService depense_TicketService, Montant_dtService montant_dtService
    // ) {
    //     this.authenticationUtils = authenticationUtils;
        // this.userService = userService;
    // }
    
    @GetMapping ("/ajouter/depense-ticket")
    public String page_depanseTicket (Model model) {
        List <Ticket> tickets = ticketService.findAll_nonDeleted();
        model.addAttribute("tickets", tickets);

        model.addAttribute("date_actu", LocalDate.now().toString());
        model.addAttribute("montant_dt", new Montant_dt());
        return "ticket/depense-ticket";
    }


    @PostMapping ("/ajouter/depense-ticket")
    public String ajouter_depenseTicket (@ModelAttribute ("montant_dt") @Validated Montant_dt montant_dt, BindingResult bindingResult, Model model, Authentication authentication) {
        
        // int userId = authenticationUtils.getLoggedInUserId(authentication);
        // User loggedInUser = userService.findById(userId);
        int ticketId = montant_dt.getDepenseTicket().getTicket().getTicketId();
        // // ************ begin VALIDATION ************
        // if(loggedInUser.isInactiveUser()) return "error/account-inactive";
        // if(! AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) return "error/access-denied";
        
        String path = super.MANAGER_ACCESS(authentication);
        if (path != null) return path;

        Ticket ticket = ticketService.findByTicketId(ticketId);
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        if (montant_dt.getDaty() != null && !montant_dt.getDaty().isEmpty()) {
            if (ticket.getCreatedAt().isAfter (LocalDateTime.parse(montant_dt.getDaty() + " 00:00"))) {
                bindingResult.rejectValue("daty", "error.daty", "DATE DOIS ETRE APRES LA CREATION DU TICKET, Apres "+ticket.getCreatedAt().toString());
            } 
        }
        
        if (bindingResult.hasErrors()) {
            List <Ticket> tickets = ticketService.findAll_nonDeleted();
            model.addAttribute("tickets", tickets);
            return "ticket/depense-ticket";
        }
        // ************ end VALIDATION ************

        

        return "";
    }

    private String insert (Montant_dt montant_dt, User user) {
        int ticketId = montant_dt.getDepenseTicket().getTicket().getTicketId();
        Ticket ticket = ticketService.findByTicketId(ticketId);
        Customer customer = customerService.findByCustomerId(ticket.getCustomer().getCustomerId());

        montant_dt.getDepenseTicket().setCustomer(customer);
        montant_dt.getDepenseTicket().setTicket(ticket);
        if (montant_dt.getDepassement() < 0) {
            Budget budget = new Budget();
            budget.setCustomer(customer);
            budget.setDaty(montant_dt.getDaty());
            budget.setMontant(BigDecimal.valueOf(montant_dt.getDepassement()));
            budget.setUser(user);
            budgetService.save(budget); // AJOUTER BUDGET NEGATIF POUR DEPASSEMENT
        }

        Depense_Ticket depense_ticket = depense_TicketService.save(montant_dt.getDepenseTicket());
        // montant_dl.setDepenseLead(depense_lead);
        montant_dt.setDepenseTicket(depense_ticket);
        montant_dtService.save(montant_dt);
        return "redirect:/employee/ticket/manager/all-tickets";
    }


    @PostMapping ("/valid-ajouterDepassement/depense-ticket")
    public String valid_depassement (@ModelAttribute ("montant_dt") @Validated Montant_dt montant_dt, BindingResult bindingResult, Authentication authentication) {
        
        String path = super.MANAGER_ACCESS(authentication);
        if (path != null) {
            return path;
        }
        return insert (montant_dt, super.getLoggedInUser());
    }


    @GetMapping ("/modifier/depense-ticket/{ticket_id}")
    public String page_modifDepense_ticket (Model model, @PathVariable ("ticket_id") int ticketId, Authentication authentication) {
        
        String pathErrorAccess = super.MANAGER_ACCESS(authentication);
        if (pathErrorAccess != null) {
            return pathErrorAccess;
        }
        Depense_Ticket depense_Ticket = depense_TicketService.findByTicketTicketId (ticketId);
        
        if (depense_Ticket == null) {
            // TSY MBOLA MISY DEPENSE MODIFIENA **************
            return "redirect:/employee/ticket/show-ticket/"+ticketId; // page pour afficher le detail du ticket
        }
        
        List <Montant_dt> montant_depTickets = montant_dtService.findByDepense_leadOrderDesc (depense_Ticket);
        Ticket ticket = ticketService.findByTicketId(ticketId);

        depense_Ticket.setTicket(ticket); 
        montant_depTickets.get(0).setDepenseTicket(depense_Ticket);

        // List <Ticket> tickets = ticketService.findAll_nonDeleted();

        // model.addAttribute("ticket", ticket);
        model.addAttribute("montant_dt", montant_depTickets.get(0));
        // // model.addAttribute("depense_lead", depense_leads.get(0));
        // // model.addAttribute("montant_dl", new Montant_dl());
        
        return "ticket/update-depense-ticket"; // page html 
    }

    @PostMapping ("/modifier/depense-ticket")
    public String modifDepense_ticket (@ModelAttribute ("montant_dt") @Validated Montant_dt montant_dt, BindingResult bindingResult, Model model, Authentication authentication) {

        String pathErrorAccess = super.MANAGER_ACCESS(authentication);
        if (pathErrorAccess != null) return pathErrorAccess;

        if (bindingResult.hasErrors()) {
            return "redirect:/modifier/depense-ticket/"+montant_dt.getDepenseTicket().getTicket().getTicketId();
        }


        // int ticketId = montant_dt.getDepenseTicket().getTicket().getTicketId();
        // Depense_Ticket depense_Ticket = depense_TicketService.findByTicketTicketId(ticketId);
        // montant_dt.setDepenseTicket(depense_Ticket);
        montant_dtService.save(montant_dt);
        return "";
    }




}
