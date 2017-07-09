package ru.kovladimir.springcars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kovladimir.springcars.persistence.repository.AdRepository;
import ru.kovladimir.springcars.persistence.models.*;

import java.util.List;

/**
 * Controller that render page to user.
 */
@Controller
public class PageController {

    private final AdRepository adRepository;

    @Autowired
    public PageController(AdRepository repository) {
        this.adRepository = repository;
    }

    /**
     * Main page with ads.
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.put("ads", adRepository.getAll());
        return "main";
    }

    /**
     * Login page.
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    /**
     * Show info about ad.
     * @param adID
     * @param model
     * @return
     */
    @RequestMapping(value = "/id{adID}", method = RequestMethod.GET)
    public String getById(@PathVariable("adID") int adID, ModelMap model) {
        Ad ad = adRepository.get(adID);
        if (ad != null) {
            model.put("currentUser", SecurityContextHolder.getContext().getAuthentication().getName());
            model.put("ad", ad);
            return "showAdInfo";
        } else {
            return "errorPage";
        }
    }

    /**
     * Get edit page by ad's id.
     * @param adID
     * @param model
     * @return
     */
    @RequestMapping(value = "/id{adID}/edit", method = RequestMethod.GET)
    public String editAd(@PathVariable("adID") int adID, ModelMap model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Ad ad = adRepository.get(adID);
        if (ad != null && ad.getUser().getLogin().equals(userLogin)) {
            model.put("ad", ad);
            return "editInfo";
        } else {
            return "errorPage";
        }
    }

    /**
     * Return page to create new ad.
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "createAd";
    }

}
