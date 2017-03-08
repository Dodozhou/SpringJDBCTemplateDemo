package com.star.controller;

import com.star.domain.User;
import com.star.repository.UserRepository;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.modelmbean.ModelMBean;

/**
 * Created by hp on 2017/3/7.
 */
@Controller
public class baseController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(@ModelAttribute("user") User user, RedirectAttributes model){
        userRepository.addUser(user);
        model.addAttribute("username",user.getUsername());
        model.addFlashAttribute("user",user);
        return "redirect:/user_detail/{username}";
    }

    @RequestMapping(value = "/user_detail/{username}")
    public String user_detail(@PathVariable("username") String username,Model model){
        //因使用了flash属性来保存重定向之前的user属性，所以下面的查询操作不需要使用了
        /*User user=userRepository.findUserByUserName(username);
        model.addAttribute("user",user);
System.out.println(model.containsAttribute("user"));*/
        return "user_detail";
    }

    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String registForm(Model model){
        model.addAttribute("user",new User());
        return "regist";
    }
}
