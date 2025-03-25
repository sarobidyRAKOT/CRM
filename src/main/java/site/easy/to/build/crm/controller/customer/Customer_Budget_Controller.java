package site.easy.to.build.crm.controller.customer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.Customers.Budget;
import site.easy.to.build.crm.entity.Customers.Customer;
import site.easy.to.build.crm.service.customer.BudgetService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;

@Controller
@RequestMapping ("/budget-customer")
public class Customer_Budget_Controller {
    
    private final CustomerService customerService;
    private final BudgetService budgetService;
    private final AuthenticationUtils authenticationUtils;
    private final UserService userService;

    public Customer_Budget_Controller (CustomerService customerService,
        BudgetService budgetService, AuthenticationUtils authenticationUtils,
        UserService userService
    ) {
        this.userService = userService;
        this.authenticationUtils = authenticationUtils;
        this.customerService = customerService;
        this.budgetService = budgetService;

    }


    @GetMapping ("/manager/ajouterBudget-customer")
    public String page_ajouterBudget_customer (Model model, Authentication authentication) {
        
        if (! AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            return "redirect:/access-denied";
        }        

        
        List <Customer> customers = this.customerService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("budget", new Budget ());
        model.addAttribute("date_actu", LocalDate.now().toString());
        return "customer/ajouterBudget-customer";
    }


    @PostMapping("/manager/ajouterBudget-customer")
    public String ajouterBudget_customer(@ModelAttribute("budget") @Validated Budget budget, 
        BindingResult bindingResult, Authentication authentication,  Model model
    ) {
        
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        if(userId == -1) { return "error/not-found"; }
        if (! AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            return "redirect:/access-denied";
        }        
        if (bindingResult.hasErrors()) { // CREATION DE L'OBJECT Budget
            List <Customer> customers = this.customerService.findAll();
            model.addAttribute("customers", customers);
            return "customer/ajouterBudget-customer";
        }

        User user = userService.findById(userId);
        Customer customer = customerService.findByCustomerId(budget.getCustomer().getCustomerId());

        // System.out.println(user.getId());
        budget.setCustomer(customer);
        budget.setUser(user);

        budgetService.save(budget);

        return "redirect:/employee/customer/my-customers";
    }

}
