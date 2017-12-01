package com.hcl.exception;

/**
 * Custom exception class used to throw internal exception 
 * when any external API calls.  
 */
public class CustomException extends Exception {
    private static final long serialVersionUID = -8566228193593266379L;
    
    private int statusCode;
    private String statusMessage;

    /**
     * Default Constructor of CustomException
     */
    public CustomException() {
        super();
    }

    /**
     * Parameterised constructor of CustomException
     * @param statusCode Exception status code 
     * @param statusMessage Detailed message with reason
     */
    public CustomException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
