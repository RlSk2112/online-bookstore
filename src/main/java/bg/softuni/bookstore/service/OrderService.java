package bg.softuni.bookstore.service;

import bg.softuni.bookstore.domain.dto.order.ExportOrderDto;
import bg.softuni.bookstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    public List<ExportOrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, ExportOrderDto.class))
                .toList();
    }

    public long getCount() {
        return orderRepository.count();
    }
}
