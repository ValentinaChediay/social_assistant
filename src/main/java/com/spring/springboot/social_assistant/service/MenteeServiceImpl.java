package com.spring.springboot.social_assistant.service;

import com.spring.springboot.social_assistant.dao.MenteeRepository;
import com.spring.springboot.social_assistant.entity.Mentee;
import com.spring.springboot.social_assistant.entity.SocialWorker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenteeServiceImpl implements MenteeService {

  private final MenteeRepository menteeRepository;

  @Override
  public List<Mentee> getAllMentees() {
    return menteeRepository.findAll();
  }

  @Override
  public void saveMentee(Mentee mentee) {
    menteeRepository.save(mentee);
  }

  @Override
  public Mentee getMentee(int id) {
    Mentee mentee = null;
    Optional<Mentee> optional = menteeRepository.findById(id);
    if (optional.isPresent()) {
      mentee = optional.get();
    }
    return mentee;
  }

  @Override
  public void deleteMentee(int id) {
    menteeRepository.deleteById(id);
  }

  @Override
  public List<Mentee> findAllBySocialWorker(SocialWorker socialWorker) {
    return menteeRepository.findAllBySocialWorker(socialWorker);
  }
}
