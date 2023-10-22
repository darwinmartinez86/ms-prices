package es.com.between.mapper;


import es.com.between.dto.response.PricesResponse;
import es.com.between.entity.PricesEntity;

public interface PricesMapper {

    PricesResponse pricesEntityToPricesResponse(PricesEntity pricesEntity);

}
