package com.spring.springboot.social_assistant.controller;

import com.spring.springboot.social_assistant.entity.Mentee;
import com.spring.springboot.social_assistant.entity.SocialWorker;
import com.spring.springboot.social_assistant.service.MenteeService;
import com.spring.springboot.social_assistant.service.SocialWorkerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SocialController {

  private final SocialWorkerService socialWorkerService;
  private final MenteeService menteeService;

  // Получение всех соц. работников
  @GetMapping("/socialWorkers")
  public List<SocialWorker> showAllSocialWorkers() {
    return socialWorkerService.getAllSocialWorkers();
  }

  // Получение одного соц. работника
  @GetMapping("/socialWorkers/{id}")
  public SocialWorker getSocialWorker(@PathVariable int id) {
    return socialWorkerService.getSocialWorker(id);
  }

  // Добавление соц. работника
  @PostMapping("/socialWorkers")
  public SocialWorker addNewSocialWorker(@RequestBody SocialWorker socialWorker) {
    socialWorkerService.saveSocialWorker(socialWorker);
    return socialWorker;
  }

  // Изменение работника
  @PutMapping("/socialWorkers")
  public SocialWorker updateSocialWorker(@RequestBody SocialWorker socialWorker) {
    socialWorkerService.saveSocialWorker(socialWorker);
    return socialWorker;
  }

  // Удаление работника
  @DeleteMapping("/socialWorkers/{id}")
  public String deleteSocialWorker(@PathVariable int id) {
    SocialWorker socialWorker = socialWorkerService.getSocialWorker(id);
    List<Mentee> mentees = menteeService.findAllBySocialWorker(socialWorker);

    for (Mentee mentee : mentees) {
      mentee.setSocialWorker(null);
      menteeService.saveMentee(mentee);
    }
    socialWorkerService.deleteSocialWorker(id);
    return "SocialWorker with ID = " + id + " was deleted";
  }
}

