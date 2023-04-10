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
public class MenteeController {

  private final MenteeService menteeService;
  private final SocialWorkerService socialWorkerService;

  // получение всех подопечных
  @GetMapping("/mentees")
  public List<Mentee> showAllMentees() {
    return menteeService.getAllMentees();
  }

  // получение одного подопечного
  @GetMapping("/mentees/{id}")
  public Mentee getMentee(@PathVariable int id) {
    return menteeService.getMentee(id);
  }

  // добавление подопечного
  @PostMapping("/mentees")
  public Mentee addNewMentee(@RequestBody Mentee mentee) {
    menteeService.saveMentee(mentee);
    return mentee;
  }

  // доп метод: добавление подопечного и проставление связи с соц. работником, айди которого передается в адресе
  @PostMapping("/mentees/{socialWorkerId}")
  public Mentee addNewMenteeAndLinkHisToSocialWorker(@RequestBody Mentee mentee, @PathVariable int socialWorkerId) {
    SocialWorker socialWorker = socialWorkerService.getSocialWorker(socialWorkerId);
    mentee.setSocialWorker(socialWorker);
    menteeService.saveMentee(mentee);
    return mentee;
  }

  // изменение подопечного
  @PutMapping("/mentees")
  public Mentee updateMentee(@RequestBody Mentee mentee) {
    menteeService.saveMentee(mentee);
    return mentee;
  }

  // доп метод: изменение подопечного и проставление связи с соц. работником, айди которого передается в адресе
  @PutMapping("/mentees/{socialWorkerId}")
  public Mentee updateMenteeAndLinkHisToSocialWorker(@RequestBody Mentee mentee, @PathVariable int socialWorkerId) {
    SocialWorker socialWorker = socialWorkerService.getSocialWorker(socialWorkerId);
    mentee.setSocialWorker(socialWorker);
    menteeService.saveMentee(mentee);
    return mentee;
  }

  // удаление подопечного
  @DeleteMapping("/mentees/{id}")
  public String deleteMentee(@PathVariable int id) {
    menteeService.deleteMentee(id);
    return "Mentee with ID = " + id + " was deleted";
  }
}
