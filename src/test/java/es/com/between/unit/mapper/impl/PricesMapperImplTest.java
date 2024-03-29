package es.com.between.unit.mapper.impl;

import es.com.between.dto.response.PricesResponse;
import es.com.between.entity.PricesEntity;
import es.com.between.mapper.impl.PricesMapperImpl;
import es.com.between.unit.utils.MockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PricesMapperImplTest {

    @InjectMocks
    private PricesMapperImpl mapper;

    @Test
    public void whenMapperEntityToDto() {

        LocalDateTime consultationDate = LocalDateTime.now();
        PricesEntity pricesEntity = MockUtils.getPricesEntity(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);
        PricesResponse expectedResponse = MockUtils.getPricesResponse(consultationDate.minusMonths(5),consultationDate.plusMonths(5),500.00,1);

        PricesResponse dto = mapper.pricesEntityToPricesResponse(pricesEntity);
        assertEquals(dto,expectedResponse);

    }
}
