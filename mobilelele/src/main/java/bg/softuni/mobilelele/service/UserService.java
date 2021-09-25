package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.UserLoginServiceModel;

public interface UserService {

  void initializeUsers();

  boolean login(UserLoginServiceModel loginServiceModel);
}
