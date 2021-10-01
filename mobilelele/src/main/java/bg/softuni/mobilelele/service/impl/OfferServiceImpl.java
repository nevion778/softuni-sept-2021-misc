package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.view.OfferSummaryView;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.ModelService;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelService modelService;
    private final UserService userService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelService modelService, UserService userService) {
        this.offerRepository = offerRepository;
        this.modelService = modelService;
        this.userService = userService;
    }

    @Override
    public void initializeOffers() {
        OfferEntity offer1 = new OfferEntity();
        offer1
                .setModel(modelService.findById(1L))
                .setEngine(EngineEnum.GASOLINE)
                .setTransmission(TransmissionEnum.MANUAL)
                .setMileage(22500)
                .setPrice(14300)
                .setYear(2019)
                .setDescription("Used, but well services and in good condition.")
                .setSeller(userService.findByUsername("pesho")) // or currentUser.getUserName()
                .setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcXp1KBpDKgYs6VqndkBpX8twjPOZbHV86yg&usqp=CAU");

        OfferEntity offer2 = new OfferEntity();
        offer2
                .setModel(modelService.findById(2L))
                .setEngine(EngineEnum.DIESEL)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setMileage(209000)
                .setPrice(5500)
                .setYear(2000)
                .setDescription("After full maintenance, insurance, new tires...")
                .setSeller(userService.findByUsername("admin")) // or currentUser.getUserName()
                .setImageUrl("https://www.picclickimg.com/d/l400/pict/283362908243_/FORD-ESCORT-MK5-16L-DOHC-16v-ZETEC.jpg");

        offerRepository.saveAll(List.of(offer1, offer2));
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return offerRepository.
                findAll().
                stream().
                map(this::map).
                collect(Collectors.toList());
    }

    private OfferSummaryView map(OfferEntity offerEntity) {
        //TODO
        return new OfferSummaryView();
    }
}
