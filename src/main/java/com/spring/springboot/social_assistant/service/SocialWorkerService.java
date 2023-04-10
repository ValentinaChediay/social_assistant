package com.spring.springboot.social_assistant.service;

import com.spring.springboot.social_assistant.entity.SocialWorker;

import java.util.List;

public interface SocialWorkerService {
  public List<SocialWorker> getAllSocialWorkers();

  public void saveSocialWorker(SocialWorker socialWorker);

  public SocialWorker getSocialWorker(int id);

  public void deleteSocialWorker(int id);
}
