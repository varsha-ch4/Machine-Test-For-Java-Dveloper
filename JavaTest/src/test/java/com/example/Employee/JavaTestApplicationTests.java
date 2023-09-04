package com.example.Employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class JavaTestApplicationTests {

	@Test
	void contextLoads() {
	}
	


    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testCreateNewOrder() throws Exception {
        Order order = createSampleOrder();
        
        when(orderService.createOrder(any(Order.class))).thenReturn(order);
        
        mockMvc.perform(MockMvcRequestBuilders
                .post("/orders")
                .content(asJsonString(order))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(order.getOrderId()));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Long orderId = 1L;
        Order order = createSampleOrder();
        order.setOrderId(orderId);

        when(orderService.getOrderById(orderId)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/orders/{orderId}", orderId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(createSampleOrder());
        orders.add(createSampleOrder());

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/orders")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(orders.get(0).getOrderId()))
                .andExpect(jsonPath("$[1].orderId").value(orders.get(1).getOrderId()));
    }

    private Order createSampleOrder() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setOrderStatus("New");
        
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setItemName("Item 1");
        item1.setItemUnitPrice(new BigDecimal("10.00"));
        item1.setItemQuantity(2);
        
        Item item2 = new Item();
        item2.setItemName("Item 2");
        item2.setItemUnitPrice(new BigDecimal("15.00"));
        item2.setItemQuantity(3);
        
        items.add(item1);
        items.add(item2);
        
        order.setItems(items);
        
        return order;
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

