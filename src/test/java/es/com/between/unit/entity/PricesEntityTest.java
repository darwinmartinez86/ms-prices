package es.com.between.unit.entity;

import es.com.between.entity.BrandEntity;
import es.com.between.entity.CurrencyEntity;
import es.com.between.entity.PricesEntity;
import es.com.between.entity.ProductEntity;
import es.com.between.unit.utils.MockUtils;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PricesEntityTest {

    PricesEntity pricesEntity;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @Before
    public void setUp() {
        ProductEntity product = MockUtils.getProductEntity();
        BrandEntity brand = MockUtils.getBrandEntity();
        CurrencyEntity currency = MockUtils.getCurrencyEntity();
        startDate = LocalDateTime.now().minusMonths(5);
        endDate = LocalDateTime.now().plusMonths(5);

        pricesEntity = PricesEntity.builder()
                .product(product)
                .brand(brand)
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price(500.0)
                .currency(currency)
                .id(1l)
                .priority(1)
                .priceList(1)
                .build();
    }

    @Test
    public void testPricesEntityConstructorAndGetters() {

        PricesEntity otherPriceEntity = PricesEntity.builder().build();

        otherPriceEntity.setProduct(pricesEntity.getProduct());
        otherPriceEntity.setBrand(pricesEntity.getBrand());
        otherPriceEntity.setPriceList(pricesEntity.getPriceList());
        otherPriceEntity.setStartDate(pricesEntity.getStartDate());
        otherPriceEntity.setEndDate(pricesEntity.getEndDate());
        otherPriceEntity.setPrice(pricesEntity.getPrice());
        otherPriceEntity.setCurrency(pricesEntity.getCurrency());
        otherPriceEntity.setId(pricesEntity.getId());
        otherPriceEntity.setPriority(pricesEntity.getPriority());
        otherPriceEntity.setPriceList(pricesEntity.getPriceList());

        Boolean isEqual = otherPriceEntity.equals(otherPriceEntity);

        assertEquals(pricesEntity, otherPriceEntity);
        assertEquals(pricesEntity.hashCode(), otherPriceEntity.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "PricesEntity(id=1, brand=BrandEntity(id=1, brandName=Zara), product=ProductEntity(id=35455, productName=Product), currency=CurrencyEntity(id=2, currencyIso=EUR, currencyName=EURO), startDate="+startDate+", endDate="+endDate+", priceList=1, priority=1, price=500.0)";
        assertEquals(expected, pricesEntity.toString());
    }

    @Test
    public void testNotEquals() {
        PricesEntity otherPriceResponse = PricesEntity.builder()
                .product(MockUtils.getProductEntity())
                .brand(MockUtils.getBrandEntity())
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price(400.0)
                .currency(MockUtils.getCurrencyEntity())
                .id(1l)
                .priority(1)
                .priceList(1)
                .build();

        Boolean isEqual = pricesEntity.equals(otherPriceResponse);

        assertNotEquals(pricesEntity, otherPriceResponse);
        assertNotEquals(pricesEntity.hashCode(), otherPriceResponse.hashCode());
        assertFalse(isEqual);
    }
}
