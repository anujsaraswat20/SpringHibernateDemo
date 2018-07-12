package com.hcl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.controller.CustomerServiceClient;
import com.hcl.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(CustomerServiceClient.class)
public class CustomerServiceClientTest {

  @Autowired
  private CustomerServiceClient client;
  
  @Autowired
  private MockRestServiceServer server;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @Before
  public void setUp() throws Exception {
      String detailsString = 
        objectMapper.writeValueAsString(new Customer(1, "John Smith", "10", "manager"));
       
      this.server.expect(requestTo("/customer/1"))
        .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
  }
  
  @Test
  public void whenCallingGetCustomer_thenClientMakesCorrectCall() 
    throws Exception {

      Customer customer = this.client.getCustomer(1);

      assertThat(customer.getName()).isEqualTo("John Smith");
      assertThat(customer.getDesignation()).isEqualTo("manager");
  }
  
  
}
