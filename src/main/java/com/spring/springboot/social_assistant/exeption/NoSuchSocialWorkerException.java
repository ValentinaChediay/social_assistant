package com.spring.springboot.social_assistant.exeption;

public class NoSuchSocialWorkerException extends RuntimeException {
  public NoSuchSocialWorkerException(String message) {
    super(message);
  }
}
