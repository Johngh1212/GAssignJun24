package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;

@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping(value = "results")
    public String displaySearchResults(Model model,
                                       @RequestParam("searchType") String searchType,
                                       @RequestParam("searchTerm") String searchTerm) {

        ArrayList<Job> jobs;

        if (searchType.equals("all") && searchTerm.isEmpty()) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }


//        model.addAttribute("columns", columnChoices);
//        model.addAttribute("jobs", jobs);

//        model.addAttribute("columns", ListController.columnChoices);
//        model.addAttribute("jobs", jobs);

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);


        return "search";
    }
}

