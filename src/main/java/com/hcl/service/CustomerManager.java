package com.hcl.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.api.CustomerService;
import com.hcl.exception.CustomException;
import com.hcl.model.Customer;
import com.hcl.repository.CustomerRepository;

/*
 * Class responsible to provide all implementation of customer service
 */
@Service
public class CustomerManager implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerManager.class);

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *  {@inheritDoc}
     */
    @Override
    public Customer createCustomer(Customer customerRequest) {
        LOGGER.debug("Processing to create a new customer");
        return customerRepository.save(customerRequest);
    }

    /**
     *  {@inheritDoc}
     * @throws CustomException 
     */
    @Override
    public Customer updateCustomer(Customer customer) throws CustomException {
        LOGGER.debug("Processing to update existing customer");
        if(!customerRepository.exists(customer.getId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Entity with specified not exists");
        }
        
        return customerRepository.save(customer);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomers() {
        LOGGER.debug("Processing to get all customers");
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customers::add);
        LOGGER.debug("Processing finished with list of customers as {}", customers);
        return customers;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomersByName(String name) {
        LOGGER.debug("Processing starts to get all customers associated to specfied name");
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findByName(name).forEach(customers::add);
        LOGGER.debug("Processing finished with all customers associated to specfied name are {}", customers);
        return customers;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public List<Customer> getAllCustomersDesignation(String designation) {
        if(designation == null) {
            LOGGER.error("Designation found null");
        }

        LOGGER.debug("Processing starts to get all customers associated to specfied designation");
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findByDesignation(designation).forEach(customers::add);
        LOGGER.debug("Processing finished with all customers associated to specfied designation are {}", customers);
        return customers;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public Customer getCustomer(Integer customerId) {
        LOGGER.debug("Processing starts to get customer associated to specfied id");
        if(customerId == null) {
            LOGGER.error("Customer id found null");
        }
        return customerRepository.findOne(customerId);
    }

    /**
     *  {@inheritDoc}
     * @throws CustomException 
     */
    @Override
    public void deleteCustomer(Integer customerId) throws CustomException {
        if(customerId == null) {
            LOGGER.error("Customer id found null");
        }
        try {
            customerRepository.delete(customerId);    
        } catch(ConstraintViolationException ex) {
            LOGGER.error("Delete customer failed. Detail is {}", ex.getMessage());
            throw new CustomException(ex.getErrorCode(), ex.getMessage());
        }
    }

	@Override
	public void exportAllCustomersList() throws CustomException {

		List<Customer> customers = getAllCustomers();
		HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet();
        HSSFRow headingRow = sheet.createRow(0);
        headingRow.createCell((short)0).setCellValue("ID");
        headingRow.createCell((short)1).setCellValue("AGE");
        headingRow.createCell((short)3).setCellValue("DESIGNATION");
        headingRow.createCell((short)2).setCellValue("NAME");
        short rowNo = 1;
        
        for (Customer customer : customers) {
            HSSFRow row = sheet.createRow(rowNo);
            row.createCell((short)0).setCellValue(customer.getId());
            row.createCell((short)1).setCellValue(customer.getAge());
            row.createCell((short)2).setCellValue(customer.getDesignation());
            row.createCell((short)3).setCellValue(customer.getName());
            rowNo++;
        }
        
        String file = "D:/Customer_details"+LocalDate.now()+".xls";
        try{
            FileOutputStream fos = new FileOutputStream(file);
            workBook.write(fos);
            fos.flush();
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Invalid directory or file not found");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error occurred while writting excel file to directory");
        }
	}

	@Override
	public void importCustomerList() throws CustomException {
		String filePath = "D:/Customer_import.xls";
		List<Customer> customerList = new ArrayList<>();
		
		FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            Workbook workbook = new HSSFWorkbook(fis);
            
            int numberOfSheets = workbook.getNumberOfSheets();
 
            //looping over each workbook sheet
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.iterator();
 
                //iterating over each row
                while (rowIterator.hasNext()) {
 
                    Customer customer = new Customer();
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
 
                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {
 
                        Cell cell = cellIterator.next();
                        
                        if (cell.getColumnIndex() == 1) {
                        	if(StringUtils.isEmpty( String.valueOf(cell.getNumericCellValue()))) {
                        		String statusMessage = "Empty cell found at cell number : "+cell.getColumnIndex();
                            	throw new CustomException(HttpStatus.BAD_REQUEST.value(), statusMessage);
                            } else {
                            	customer.setAge(String.valueOf(cell.getNumericCellValue()));	
                            }
                        }
                        else if (cell.getColumnIndex() == 2) {
                        	if(StringUtils.isEmpty( cell.getStringCellValue())) {
                        		String statusMessage = "Empty cell found at cell number : "+cell.getColumnIndex();
                            	throw new CustomException(HttpStatus.BAD_REQUEST.value(), statusMessage);
                            } else {
                            	customer.setName(cell.getStringCellValue());	
                            }
                        }
                        else if (cell.getColumnIndex() == 3) {
                        	if(StringUtils.isEmpty( cell.getStringCellValue())) {
                        		String statusMessage = "Empty cell found at cell number : "+cell.getColumnIndex();
                            	throw new CustomException(HttpStatus.BAD_REQUEST.value(), statusMessage);
                            } else {
                            	customer.setDesignation(cell.getStringCellValue());	
                            }
                        }
                    }
                    createCustomer(customer);
                }
            }
 
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
