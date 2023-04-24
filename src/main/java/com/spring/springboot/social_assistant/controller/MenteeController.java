package com.spring.springboot.social_assistant.controller;

import com.spring.springboot.social_assistant.entity.Mentee;
import com.spring.springboot.social_assistant.entity.SocialWorker;
import com.spring.springboot.social_assistant.exeption.MenteeIncorrectData;
import com.spring.springboot.social_assistant.exeption.NoSuchMenteeException;
import com.spring.springboot.social_assistant.exeption.NoSuchSocialWorkerException;
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
    Optional<Mentee> optional = menteeService.getMentee(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    throw new NoSuchMenteeException("There is no mentee with ID = " + id + ".");
  }

  @ExceptionHandler
  public ResponseEntity<MenteeIncorrectData> handleException (NoSuchMenteeException exception) {
    MenteeIncorrectData data = new MenteeIncorrectData();
    data.setInfo(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler
  public ResponseEntity<MenteeIncorrectData> handleException (Exception exception) {
    MenteeIncorrectData data = new MenteeIncorrectData();
    data.setInfo(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
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
    SocialWorker socialWorker = getSocialWorkerFromOptional(socialWorkerId);
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
    SocialWorker socialWorker = getSocialWorkerFromOptional(socialWorkerId);
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

  public SocialWorker getSocialWorkerFromOptional(int id) {
    Optional<SocialWorker> optional = socialWorkerService.getSocialWorker(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    throw new NoSuchSocialWorkerException("There is no social worker with ID = " + id + ".");
  }
}
