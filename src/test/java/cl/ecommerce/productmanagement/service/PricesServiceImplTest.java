package cl.ecommerce.productmanagement.service;

import cl.ecommerce.productmanagement.dto.PriceDTO;
import cl.ecommerce.productmanagement.mapper.Mapper;
import cl.ecommerce.productmanagement.model.Brand;
import cl.ecommerce.productmanagement.model.Price;
import cl.ecommerce.productmanagement.model.Product;
import cl.ecommerce.productmanagement.repository.PriceRepository;
import cl.ecommerce.productmanagement.response.PriceResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class PricesServiceImplTest {

    @MockBean
    private Mapper mapper;
    @MockBean
    private PriceRepository priceRepository;
    @Autowired
    private PricesService pricesService;


    private PriceDTO priceDTOCase1;
    private PriceDTO priceDTOCase2;
    private PriceDTO priceDTOCase3;
    private PriceDTO priceDTOCase4;
    private PriceDTO priceDTOCase5;

    private PriceResponse priceResponse1;
    private PriceResponse priceResponse2;
    private PriceResponse priceResponse3;
    private PriceResponse priceResponse4;

    @BeforeEach
    void setUp() {

        priceDTOCase1 = new PriceDTO(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 1L);
        priceDTOCase2 = new PriceDTO(LocalDateTime.parse("2020-06-14T16:00:00"), 35455L, 1L);
        priceDTOCase3 = new PriceDTO(LocalDateTime.parse("2020-06-14T21:00:00"), 35455L, 1L);
        priceDTOCase4 = new PriceDTO(LocalDateTime.parse("2020-06-15T10:00:00"), 35455L, 1L);
        priceDTOCase5 = new PriceDTO(LocalDateTime.parse("2020-06-16T21:00:00"), 35455L, 1L);

        Brand brand = new Brand(1L, "Product 1", null);
        Product product = new Product(1L, "ZARA", null);

        Price price1 = new Price(1L, brand, product, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 1, 0, 35.50f, "EUR");

        Price price2 = new Price(2L, brand, product, LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"), 2, 1, 25.45f, "EUR");

        Price price3 = new Price(3L, brand, product, LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-06-15T11:00:00"), 3, 1, 30.50f, "EUR");

        Price price4 = new Price(4L, brand, product, LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 4, 1, 38.95f, "EUR");

        priceResponse1 = new PriceResponse(price1.getProduct().getId(), price1.getBrand().getId(),
                price1.getPriority(), price1.getStartDate(), price1.getEndDate(),
                price1.getPrice());

        priceResponse2 = new PriceResponse(price2.getProduct().getId(), price2.getBrand().getId(),
                price2.getPriority(), price2.getStartDate(), price2.getEndDate(),
                price2.getPrice());

        priceResponse3 = new PriceResponse(price3.getProduct().getId(), price3.getBrand().getId(),
                price3.getPriority(), price3.getStartDate(), price3.getEndDate(),
                price3.getPrice());

        priceResponse4 = new PriceResponse(price4.getProduct().getId(), price4.getBrand().getId(),
                price4.getPriority(), price4.getStartDate(), price4.getEndDate(),
                price4.getPrice());

        when(mapper.toPriceResponse(price1)).thenReturn(priceResponse1);
        when(mapper.toPriceResponse(price2)).thenReturn(priceResponse2);
        when(mapper.toPriceResponse(price3)).thenReturn(priceResponse3);
        when(mapper.toPriceResponse(price4)).thenReturn(priceResponse4);

        when(priceRepository.findAllByBrandIdAndProductIdAndDateRange(priceDTOCase1.brandId(),
                priceDTOCase1.productId(), priceDTOCase1.dateTime())).thenReturn(List.of(price1));

        when(priceRepository.findAllByBrandIdAndProductIdAndDateRange(priceDTOCase2.brandId(),
                priceDTOCase2.productId(), priceDTOCase2.dateTime())).thenReturn(Arrays.asList(price1, price2));

        when(priceRepository.findAllByBrandIdAndProductIdAndDateRange(priceDTOCase3.brandId(),
                priceDTOCase3.productId(), priceDTOCase3.dateTime())).thenReturn(List.of(price1));

        when(priceRepository.findAllByBrandIdAndProductIdAndDateRange(priceDTOCase4.brandId(),
                priceDTOCase4.productId(), priceDTOCase4.dateTime())).thenReturn(Arrays.asList(price1, price3));

        when(priceRepository.findAllByBrandIdAndProductIdAndDateRange(priceDTOCase5.brandId(),
                priceDTOCase5.productId(), priceDTOCase5.dateTime())).thenReturn(Arrays.asList(price1, price4));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPrice() {

        // Para el caso 1, se espera que retorne el objeto precioResponse1
        PriceResponse priceResponseCase1 = pricesService.getPrice(priceDTOCase1);

        Assertions.assertEquals(this.priceResponse1.price(), priceResponseCase1.price());
        Assertions.assertEquals(this.priceResponse1.brandId(), priceResponseCase1.brandId());
        Assertions.assertEquals(this.priceResponse1.productId(), priceResponseCase1.productId());
        Assertions.assertEquals(this.priceResponse1.startDate(), priceResponseCase1.startDate());
        Assertions.assertEquals(this.priceResponse1.endDate(), priceResponseCase1.endDate());

        // Para el caso 2, se espera que retorne el objeto precioResponse2
        PriceResponse priceResponseCase2 = pricesService.getPrice(priceDTOCase2);

        Assertions.assertEquals(this.priceResponse2.startDate(), priceResponseCase2.startDate());
        Assertions.assertEquals(this.priceResponse2.endDate(), priceResponseCase2.endDate());

        // Para el caso 3, se espera que retorne el objeto precioResponse1
        PriceResponse priceResponseCase3 = pricesService.getPrice(priceDTOCase3);

        Assertions.assertEquals(this.priceResponse1.startDate(), priceResponseCase3.startDate());
        Assertions.assertEquals(this.priceResponse1.endDate(), priceResponseCase3.endDate());

        // Para el caso 4, se espera que retorne el objeto precioResponse3
        PriceResponse priceResponseCase4 = pricesService.getPrice(priceDTOCase4);

        Assertions.assertEquals(this.priceResponse3.startDate(), priceResponseCase4.startDate());
        Assertions.assertEquals(this.priceResponse3.endDate(), priceResponseCase4.endDate());

        // Para el caso 5, se espera que retorne el objeto precioResponse4
        PriceResponse priceResponseCase5 = pricesService.getPrice(priceDTOCase5);

        Assertions.assertEquals(this.priceResponse4.startDate(), priceResponseCase5.startDate());
        Assertions.assertEquals(this.priceResponse4.endDate(), priceResponseCase5.endDate());

    }
}