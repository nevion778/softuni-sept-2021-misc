package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.UserLoginServiceModel;

public interface UserService {

  void initializeUsersAndRoles();

  boolean login(UserLoginServiceModel loginServiceModel);

  void logout();
}
