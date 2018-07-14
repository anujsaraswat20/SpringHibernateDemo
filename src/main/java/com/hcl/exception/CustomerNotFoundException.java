package com.hcl.exception;

public class CustomerNotFoundException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int statusCode;
  private String statusMessage;

  
  public CustomerNotFoundException() {
    super();
  }
  
  /**
   * Parameterised constructor of CustomException
   * @param statusCode Exception status code 
   * @param statusMessage Detailed message with reason
   */
  public CustomerNotFoundException(int statusCode, String statusMessage) {
      super(statusMessage);
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
  }
}
