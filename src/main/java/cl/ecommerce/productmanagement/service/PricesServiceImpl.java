package cl.ecommerce.productmanagement.service;

import cl.ecommerce.productmanagement.dto.PriceDTO;
import cl.ecommerce.productmanagement.exception.NotFoundException;
import cl.ecommerce.productmanagement.mapper.Mapper;
import cl.ecommerce.productmanagement.model.Price;
import cl.ecommerce.productmanagement.repository.PriceRepository;
import cl.ecommerce.productmanagement.response.PriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PricesServiceImpl implements PricesService {

    private final PriceRepository priceRepository;

    private final Mapper mapper;

    @Override
    public PriceResponse getPrice(PriceDTO priceDTO) {

        List<Price> prices = priceRepository
                .findAllByBrandIdAndProductIdAndDateRange(priceDTO.brandId(),
                        priceDTO.productId(), priceDTO.dateTime());

        Price priceWithMaxPriority = prices
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(NotFoundException::new);

        return mapper.toPriceResponse(priceWithMaxPriority);
    }
}
