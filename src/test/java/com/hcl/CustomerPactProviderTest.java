package com.hcl;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hcl.controller.CustomerController;
import com.hcl.controller.OrderController;
import com.hcl.exception.CustomerNotFoundException;
import com.hcl.model.Customer;
import com.hcl.service.CustomerManager;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;

/**
 * Test case to verify PACT file date and response when hit URI
 * @author Anuj Saraswat
 *
 */
//@RunWith(RestPactRunner.class)
//@Provider("Customer-Service")
//@PactFolder("pacts")
public class CustomerPactProviderTest {

  @Mock
  private CustomerManager customerManager;

  // @InjectMocks
  // private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerController customerController = new CustomerController();

  @InjectMocks
  private OrderController orderController = new OrderController();

  @TestTarget
  public final MockMvcTarget target = new MockMvcTarget();

  @Before
  public void setup() throws Exception {
    customerManager = new CustomerManager();

    MockitoAnnotations.initMocks(this);

    target.setControllers(customerController, orderController);

    target.setServletPath("/customer/1");

  }

//  @State("customer-exists")
  public void getOneCustomer() {
    // when (customerRepository.findOne(anyInt())).thenReturn(anyObject());
    when(customerManager.getCustomer(1))
      .thenReturn(new Customer(1, "test1", "10", "manager"));
  }

//  @State("customer-not-found")
  public void customerNotFound() {
    when(customerManager.getCustomer(2))
      .then(i -> {
        throw new CustomerNotFoundException();
      });
  }
}
