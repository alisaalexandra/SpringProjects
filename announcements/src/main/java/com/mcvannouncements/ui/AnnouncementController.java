package com.mcvannouncements.ui;

import com.mcvannouncements.dao.AnnouncementRepository;
import com.mcvannouncements.model.AnnouncementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @GetMapping
    public String viewAnnouncements(Model model){
        List<AnnouncementModel> announcements = (List<AnnouncementModel>) announcementRepository.findAll();
        model.addAttribute("announcements",announcements);
        return "index";
    }

    @GetMapping("/announcement/{id}")
    public String viewAnnouncement(Model model, @PathVariable(name = "id") String announcementID){
        System.out.println(announcementID);
        Optional<AnnouncementModel> announcementOptional = announcementRepository.findById(Integer.parseInt(announcementID));
        if(announcementOptional.isPresent()){
            AnnouncementModel announcement = announcementOptional.get();
            model.addAttribute(announcement);
        }
        else {
            System.out.println("ID not found");
        }
        return "announcement";
    }

    @GetMapping("/announcement/edit")
    public String editAnnouncement(Model model, @RequestParam(name="id",required = true)int id){
        Optional<AnnouncementModel> optionalAnnouncement = announcementRepository.findById(id);
        if(optionalAnnouncement.isPresent()){
            AnnouncementModel announcement = optionalAnnouncement.get();
            model.addAttribute("editAnnouncement", announcement);
        }
        return "announcement-edit";
    }

    @PostMapping("/announcement/update")
    public String updateAnnouncement(Model model, @ModelAttribute AnnouncementModel announcement){
        announcementRepository.save(announcement);
        model.addAttribute("editAnnouncement", announcement);
        return "announcement-edit";
    }

    @GetMapping("/announcement/add")
    public String addAnnouncement(Model model){
        model.addAttribute("editAnnouncement",new AnnouncementModel());
        return "announcement-add";
    }

    @PostMapping("/announcement/add")
    public String addAnnouncement(Model model, @ModelAttribute AnnouncementModel editAnnouncement){
        announcementRepository.save(editAnnouncement);
        List<AnnouncementModel> announcements = (List<AnnouncementModel>) announcementRepository.findAll();
        model.addAttribute("announcements",announcements);
        return "index";
    }

    @GetMapping("/announcement/delete")
    public String deleteAnnouncement(Model model, @RequestParam(name="id",required = true) int id){
        announcementRepository.deleteById(id);
        return "index";
    }
}
