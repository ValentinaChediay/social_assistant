package com.spring.springboot.social_assistant.exeption;

public class NoSuchMenteeException extends RuntimeException {
  public NoSuchMenteeException(String message) {
    super(message);
  }
}
