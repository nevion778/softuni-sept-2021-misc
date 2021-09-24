package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

  private final BrandRepository brandRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public DBInit(BrandRepository brandRepository,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    this.brandRepository = brandRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) throws Exception {
    initializeBrandAndModels();
    initializeUsers();

  }

  private void initializeUsers() {
    if (userRepository.count() == 0) {
      UserEntity admin = new UserEntity();
      admin.
          setActive(true).
          setUsername("admin").
          setFirstName("Admin").setLastName("Adminov").
          setPassword(passwordEncoder.encode("test"));

      userRepository.save(admin);
    }
  }

  private void initializeBrandAndModels() {
    if (brandRepository.count() == 0) {
      BrandEntity ford = new BrandEntity();
      ford.setName("Ford").setCreated(Instant.now());

      ModelEntity fiesta = new ModelEntity();
      fiesta.setBrand(ford).
          setCategory(CategoryEnum.CAR).
          setName("Fiesta").
          setStartYear(1976).
          setImageUrl(
              "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/1920px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg");

      ModelEntity escort = new ModelEntity();
      escort.setBrand(ford).
          setCategory(CategoryEnum.CAR).
          setName("Escort").
          setStartYear(1967).
          setEndYear(2004).
          setImageUrl(
              "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg");

      ford.setModels(List.of(escort, fiesta));

      brandRepository.save(ford);
    }
  }
}
