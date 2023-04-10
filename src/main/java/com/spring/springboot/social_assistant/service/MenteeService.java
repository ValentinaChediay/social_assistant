package com.spring.springboot.social_assistant.service;

import com.spring.springboot.social_assistant.entity.Mentee;
import com.spring.springboot.social_assistant.entity.SocialWorker;

import java.util.List;

public interface MenteeService {
  public List<Mentee> getAllMentees();

  public void saveMentee(Mentee mentee);

  public Mentee getMentee(int id);

  public void deleteMentee(int id);

  public List<Mentee> findAllBySocialWorker(SocialWorker socialWorker);
}
