package site.easy.to.build.crm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.easy.to.build.crm.service.init_base.Init_DBA;

@Controller
@RequestMapping("/database")
public class Database_init_Controller {
    
    @Autowired
    private Init_DBA init_dbaService;


    @GetMapping ("/init-database")
    public String init_dba () {
        // System.out.println("TONGA INIT DBA ___________");
        try {
            init_dbaService.init_database();
            return "redirect:/";
            // return "";
		} catch (IOException e) {
			e.printStackTrace();
            return "error/500";
		}
    }

}
