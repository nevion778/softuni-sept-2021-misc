package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserLoginController {

  private final UserService userService;

  public UserLoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/login")
  public String login() {
    return "auth-login";
  }

  @PostMapping("/users/login")
  public String login(UserLoginBindingModel userLoginBindingModel) {

    // Delegate the logic to the service layer
    boolean loginSuccessful = userService.
        login(new UserLoginServiceModel().
            setUsername(userLoginBindingModel.getUsername()).
            setRawPassword(userLoginBindingModel.getPassword()));

    return "redirect:/index";
  }

}
