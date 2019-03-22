package com.login.login.controller;


import com.login.login.entity.UserForm;
import com.login.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLoginForm(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute(name = "registerForm") UserForm userForm, Model model){

        if(userService.findByUsernameAndPassword(userForm.getUsername(),userForm.getPassword())!= null){
            return "home";
        }
        else{
            model.addAttribute("invalidCredentials",true);
            return "welcome";
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegisterForm(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void doRegister(@ModelAttribute (name = "registerForm") UserForm userForm){

       String firstname = userForm.getFirstname();
       String lastname = userForm.getLastname();
       String username = userForm.getUsername();
       String password = userForm.getPassword();

       UserForm uf = new UserForm(firstname,lastname,username,password);

       userService.saveUser(uf);
    }

}
