package com.spring.springboot.social_assistant.controller;

import com.spring.springboot.social_assistant.entity.Mentee;
import com.spring.springboot.social_assistant.entity.SocialWorker;
import com.spring.springboot.social_assistant.exeption.NoSuchSocialWorkerException;
import com.spring.springboot.social_assistant.exeption.SocialWorkerIncorrectData;
import com.spring.springboot.social_assistant.service.MenteeService;
import com.spring.springboot.social_assistant.service.SocialWorkerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    return getSocialWorkerFromOptional(id);
  }

  @ExceptionHandler
  public ResponseEntity<SocialWorkerIncorrectData> handleException(NoSuchSocialWorkerException exception) {
    SocialWorkerIncorrectData data = new SocialWorkerIncorrectData();
    data.setInfo(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<SocialWorkerIncorrectData> handleException(Exception exception) {
    SocialWorkerIncorrectData data = new SocialWorkerIncorrectData();
    data.setInfo(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
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
    SocialWorker socialWorker = getSocialWorkerFromOptional(id);
    List<Mentee> mentees = menteeService.findAllBySocialWorker(socialWorker);

    for (Mentee mentee : mentees) {
      mentee.setSocialWorker(null);
      menteeService.saveMentee(mentee);
    }
    socialWorkerService.deleteSocialWorker(id);
    return "SocialWorker with ID = " + id + " was deleted";
  }

  public SocialWorker getSocialWorkerFromOptional(int id) {
    Optional<SocialWorker> optional = socialWorkerService.getSocialWorker(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    throw new NoSuchSocialWorkerException("There is no social worker with ID = " + id + ".");
  }
}

