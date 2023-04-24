package com.spring.springboot.social_assistant.service;

import com.spring.springboot.social_assistant.entity.Mentee;
import com.spring.springboot.social_assistant.entity.SocialWorker;

import java.util.List;
import java.util.Optional;

public interface MenteeService {
  List<Mentee> getAllMentees();

  void saveMentee(Mentee mentee);

  Optional<Mentee> getMentee(int id);

  void deleteMentee(int id);

  List<Mentee> findAllBySocialWorker(SocialWorker socialWorker);
}
