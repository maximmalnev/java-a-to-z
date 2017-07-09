package ru.kovladimir.springcars.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kovladimir.springcars.persistence.repository.AdRepository;
import ru.kovladimir.springcars.persistence.models.Ad;
import ru.kovladimir.springcars.persistence.models.Car;
import ru.kovladimir.springcars.persistence.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Controller that receive data from user.
 */
@Controller
public class JsonController {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final ServletContext context;

    @Autowired
    public JsonController(AdRepository repository, UserRepository userRepository, ServletContext context) {
        this.adRepository = repository;
        this.userRepository = userRepository;
        this.context = context;
    }

    /**
     * Create new ad.
     * @param ad
     * @param car
     * @param errors
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Ad ad, @Valid Car car, Errors errors,
                      @RequestPart("photo-file") MultipartFile file) {
        String result;
        if (errors.hasErrors()) {
            result = "error" + errors.toString();
        } else {
            setPhoto(ad, file);
            setCar(ad, car);
            ad.setCreateDate(new Timestamp(System.currentTimeMillis()));
            ad.setUser(userRepository.get(
                    SecurityContextHolder.getContext().getAuthentication().getName()
            ));
            adRepository.create(ad);
            result = "success";
        }
        return result;
    }

    /**
     * Edit ad.
     * @param adID
     * @param description
     * @param car
     * @param errors
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/id{adID}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable("adID") int adID,
                       String description, @Valid Car car, Errors errors,
                       @RequestPart("photo-file") MultipartFile file) {
        String result;
        if (!errors.hasErrors()) {
            String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            Ad ad = adRepository.get(adID);
            if (ad != null && ad.getUser().getLogin().equals(userLogin)) {
                ad.setDescription(description);
                car.setId(ad.getCar().getId());
                setCar(ad, car);
                setPhoto(ad, file);
                adRepository.update(ad);
                result = "success";
            } else {
                result = "access error or ad == null";
            }
        } else {
            result = "error with validation";
        }
        return result;
    }

    /**
     * Delete ad.
     * @param adID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/id{adID}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("adID") int adID) {
        String result;
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        Ad ad = adRepository.get(adID);
        if (ad != null && ad.getUser().getLogin().equals(userLogin)) {
            adRepository.delete(ad);
            result = "success";
        } else {
            result = "access error or ad == null";
        }
        return result;
    }

    /**
     * Set car in ad and set ad in car.
     * @param ad
     * @param car
     */
    private void setCar(Ad ad, Car car) {
        car.setAd(ad);
        ad.setCar(car);
    }

    /**
     * Check if user uploaded photo and set it if he did.
     * @param ad
     * @param file
     */
    private void setPhoto(Ad ad, MultipartFile file) {
        if (!file.isEmpty()) {
            String generatedName = createFile(file);
            ad.setPhoto(generatedName);
        }
    }

    /**
     * Create file on server.
     * @param file
     * @return
     */
    private String createFile(MultipartFile file) {
        String generatedName = String.format("%s.%s", UUID.randomUUID().toString(),
                FilenameUtils.getExtension(file.getOriginalFilename()));
        String fullPath = String.format("%simages/%s", context.getRealPath("/"), generatedName);
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generatedName;
    }
}
