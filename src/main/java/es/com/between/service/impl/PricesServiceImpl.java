package es.com.between.service.impl;

import es.com.between.constant.ErrorEnum;
import es.com.between.dto.response.PricesResponse;
import es.com.between.entity.PricesEntity;
import es.com.between.exception.PricesNotFoundException;
import es.com.between.mapper.PricesMapper;
import es.com.between.repository.PricesRepository;
import es.com.between.service.PricesService;
import es.com.between.specification.PricesSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class PricesServiceImpl implements PricesService {

  @Autowired
  private PricesRepository pricesRepository;

  @Autowired
  private PricesMapper pricesMapper;

  @Autowired
  private MessageSource messageSource;

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

  @Override
  public PricesResponse getPriceByProductAndBrandAndDate(LocalDateTime consultationDate, Long brandId, Long productId) {

    log.debug("Entering getPriceByProductAndBrandAndDate [brandId]: {}, [productId]: {}, [consultationDate]: {}", brandId, productId, consultationDate);

    if(Objects.isNull(consultationDate)) {
      consultationDate = LocalDateTime.now();
    }

    PricesEntity pricesEntity = getPriceEntity(consultationDate, brandId, productId);
    PricesResponse pricesResponse = pricesMapper.pricesEntityToPricesResponse(pricesEntity);

    log.debug("Leaving getPriceByProductAndBrandAndDate [response]: {}", pricesResponse);
    return pricesResponse;

  }

  private PricesEntity getPriceEntity(LocalDateTime consultationDate, Long brandId, Long productId) {

    Specification<PricesEntity> spec = PricesSpecification.findPriceByCriteria(consultationDate,brandId,productId);
    Optional<PricesEntity> pricesEntity = pricesRepository.findOne(spec);

    if(pricesEntity.isEmpty()) {
      getPricesNotFoundException(productId.toString(), consultationDate.format(formatter));
    }
    return pricesEntity.get();
  }

  private void getPricesNotFoundException(String productId, String consultationDate) {

    String message = messageSource.getMessage(ErrorEnum.NOT_FOUND_PRICE.getMessage(), null, Locale.getDefault());
    String formattedMessage = MessageFormat.format(message, productId, consultationDate);

    throw new PricesNotFoundException(formattedMessage);
  }
}
