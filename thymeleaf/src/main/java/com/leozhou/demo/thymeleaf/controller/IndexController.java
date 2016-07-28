package com.leozhou.demo.thymeleaf.controller;

import com.leozhou.demo.thymeleaf.dto.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouchunjie on 16/7/28.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model){
        Person single = new Person("aa",11);

        List<Person> people = new ArrayList<>();
        Person p1 = new Person("xx",11);
        Person p2 = new Person("yy",22);
        Person p3 = new Person("zz",33);

        people.add(p1);
        people.add(p2);
        people.add(p3);

        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);

        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Person json(){
        Person person = new Person("aa",11);

        return person;
    }
}
