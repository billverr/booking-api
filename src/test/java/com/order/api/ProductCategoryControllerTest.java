package com.order.api;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.booking.SpringBookingApplication;

@SpringBootTest(classes = SpringBookingApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ProductCategoryControllerTest {

}
