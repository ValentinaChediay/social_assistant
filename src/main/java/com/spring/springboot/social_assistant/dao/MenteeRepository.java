package com.spring.springboot.social_assistant.dao;

import com.spring.springboot.social_assistant.entity.Mentee;
import com.spring.springboot.social_assistant.entity.SocialWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenteeRepository extends JpaRepository<Mentee, Integer> {
  public List<Mentee> findAllBySocialWorker(SocialWorker socialWorker);
}
