package es.com.between.unit.dto.response;

import es.com.between.dto.BrandDto;
import es.com.between.dto.CurrencyDto;
import es.com.between.dto.ProductDto;
import es.com.between.dto.response.PricesResponse;
import es.com.between.unit.utils.MockUtils;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PricesResponseTest {

    PricesResponse pricesResponse;
    LocalDateTime startDate;
    LocalDateTime endDate;

    @Before
    public void setUp() {
        ProductDto product = MockUtils.getProductDto();
        BrandDto brand = MockUtils.getBrandDto();
        CurrencyDto currency = MockUtils.getCurrencyDto();
        startDate = LocalDateTime.now().minusMonths(5);
        endDate = LocalDateTime.now().plusMonths(5);

        pricesResponse = PricesResponse.builder()
                .product(product)
                .brand(brand)
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price(500.0)
                .currency(currency)
                .build();
    }

    @Test
    public void testPricesResponseConstructorAndGetters() {

        PricesResponse otherPriceResponse = PricesResponse.builder().build();

        otherPriceResponse.setProduct(pricesResponse.getProduct());
        otherPriceResponse.setBrand(pricesResponse.getBrand());
        otherPriceResponse.setPriceList(pricesResponse.getPriceList());
        otherPriceResponse.setStartDate(pricesResponse.getStartDate());
        otherPriceResponse.setEndDate(pricesResponse.getEndDate());
        otherPriceResponse.setPrice(pricesResponse.getPrice());
        otherPriceResponse.setCurrency(pricesResponse.getCurrency());

        Boolean isEqual = otherPriceResponse.equals(otherPriceResponse);

        assertEquals(pricesResponse, otherPriceResponse);
        assertEquals(pricesResponse.hashCode(), otherPriceResponse.hashCode());
        assertTrue(isEqual);
    }

    @Test
    public void testToString() {
        String expected = "PricesResponse(product=ProductDto(id=35455, name=Product), brand=BrandDto(id=1, name=Zara), priceList=1, startDate="+startDate+", endDate="+endDate+", price=500.0, currency=CurrencyDto(id=2, name=EURO, iso=EUR))";
        assertEquals(expected, pricesResponse.toString());
    }

    @Test
    public void testNotEquals() {
        PricesResponse otherPriceResponse = PricesResponse.builder()
                .product(MockUtils.getProductDto())
                .brand(MockUtils.getBrandDto())
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price(400.0)
                .currency(MockUtils.getCurrencyDto())
                .build();

        Boolean isEqual = pricesResponse.equals(otherPriceResponse);

        assertNotEquals(pricesResponse, otherPriceResponse);
        assertNotEquals(pricesResponse.hashCode(), otherPriceResponse.hashCode());
        assertFalse(isEqual);
    }
}
