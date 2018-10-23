package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  public WelcomeController(@Value("${WELCOME_MESSAGE}") String welcomeMessage) {
    this.welcomeMessage = welcomeMessage;
    System.out.println("This is your welcome message:" + this.welcomeMessage);
  }

  private String welcomeMessage;

  @GetMapping("/")
  public String sayHello() {
    return this.welcomeMessage;
  }
}
