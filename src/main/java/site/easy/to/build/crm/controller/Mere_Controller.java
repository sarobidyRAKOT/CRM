package site.easy.to.build.crm.controller;

import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Controller;

import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;

// @Controller
public class Mere_Controller {
    
    protected final AuthenticationUtils authenticationUtils;
    protected final UserService userService;
    protected User loggedInUser;

    public Mere_Controller (AuthenticationUtils authenticationUtils, UserService userService) {
        this.authenticationUtils = authenticationUtils;
        this.userService = userService;
    }

    public String MANAGER_ACCESS (Authentication authentication) {

        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User loggedInUser = userService.findById(userId);
        this.setLoggedInUser(loggedInUser);

        if (loggedInUser.isInactiveUser()) {
            return "error/account-inactive";
        } 
        if(! AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            return "error/access-denied"; // Manager role ihany no afak manao modification
        } 

        return null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
    private void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
