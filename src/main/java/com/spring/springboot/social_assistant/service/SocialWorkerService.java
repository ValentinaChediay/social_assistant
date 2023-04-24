package com.spring.springboot.social_assistant.service;

import com.spring.springboot.social_assistant.entity.SocialWorker;

import java.util.List;
import java.util.Optional;

public interface SocialWorkerService {
  List<SocialWorker> getAllSocialWorkers();

  void saveSocialWorker(SocialWorker socialWorker);

  Optional<SocialWorker> getSocialWorker(int id);

  void deleteSocialWorker(int id);
}
