package com.spring.springboot.social_assistant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "social_workers")
public class SocialWorker {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "surname")
  private String surname;

  @Column(name = "name")
  private String name;

  @Column(name = "patronymic")
  private String patronymic;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "code")
  private String code;
}
