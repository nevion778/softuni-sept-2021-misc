package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.view.OfferSummaryView;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.OfferService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  public OfferServiceImpl(OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  @Override
  public void initializeOffers() {
    // TODO
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
