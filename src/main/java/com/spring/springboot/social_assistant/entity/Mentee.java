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
@Table(name = "mentees")
public class Mentee {
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

  @ManyToOne(fetch = FetchType.EAGER)
  private SocialWorker socialWorker;
}
