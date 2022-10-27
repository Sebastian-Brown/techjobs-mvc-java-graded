package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Locale;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String  searchType, @RequestParam String searchTerm) {
      ArrayList<Job> searchJobs;

        if (searchType.toLowerCase(Locale.ROOT) == "all" || searchTerm == "") {
            searchJobs = JobData.findAll();
        } else {
            searchJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", searchJobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }
}
