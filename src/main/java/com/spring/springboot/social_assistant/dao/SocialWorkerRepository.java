package com.spring.springboot.social_assistant.dao;

import com.spring.springboot.social_assistant.entity.SocialWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialWorkerRepository extends JpaRepository<SocialWorker, Integer> {
}
