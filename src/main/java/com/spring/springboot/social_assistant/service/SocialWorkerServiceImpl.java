package com.spring.springboot.social_assistant.service;

import com.spring.springboot.social_assistant.dao.SocialWorkerRepository;
import com.spring.springboot.social_assistant.entity.SocialWorker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SocialWorkerServiceImpl implements SocialWorkerService {

  private final SocialWorkerRepository socialWorkerRepository;

  @Override
  public List<SocialWorker> getAllSocialWorkers() {
    return socialWorkerRepository.findAll();
  }

  @Override
  public void saveSocialWorker(SocialWorker socialWorker) {
    socialWorkerRepository.save(socialWorker);
  }

  @Override
  public SocialWorker getSocialWorker(int id) {
    SocialWorker socialWorker = null;
    Optional<SocialWorker> optional = socialWorkerRepository.findById(id);
    if (optional.isPresent()) {
      socialWorker = optional.get();
    }
    return socialWorker;
  }

  @Override
  public void deleteSocialWorker(int id) {
    socialWorkerRepository.deleteById(id);
  }
}
