package bg.softuni.bookstore.web;

import bg.softuni.bookstore.domain.dto.order.ExportOrderDto;
import bg.softuni.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Map<List<ExportOrderDto>, Long> getAllOrders() {
        return Map.of(orderService.getAllOrders(), orderService.getCount());
    }
}
