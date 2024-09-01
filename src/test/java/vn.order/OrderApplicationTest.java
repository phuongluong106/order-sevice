package vn.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import vn.order.application.controller.OrderController;
import vn.order.application.request.CreateOrderRequest;
import vn.order.application.response.OnlineResponse;
import vn.order.domain.model.Order;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class OrderApplicationTest {
    @Autowired
    OrderController orderController;
    @Test
    public void contextLoads() {
        ResponseEntity<Order> onlineResponseResponseEntity = orderController.createOrder(new CreateOrderRequest(1, new ArrayList(1), 1));
        assertTrue(onlineResponseResponseEntity.getStatusCode().value() == 200);
    }
}
