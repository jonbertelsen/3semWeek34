/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jobe
 */
public class CustomerFacadeTest {
    
    private static Customer c1;
    private static Customer c2;
    private static EntityManagerFactory emf;
    private static CustomerFacade facade;
    
    public CustomerFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("pu");
        facade = CustomerFacade.getCustomerFacade(emf);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         System.out.println("Before each");
         facade.deleteAllCostumers();
         c1 = facade.addCustomer("Jønke","Bønke");
         c2 = facade.addCustomer("Ole","Fehår");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetCustomerFacade() {
        System.out.println("getCustomerFacade");
        assertNotNull(facade);
    }

    @Test
    public void testFindByID() {
        System.out.println("findByID");
         
        int id = c1.getId();
        Customer expResult = c1;
        Customer result = facade.findByID(id);
        System.out.println(result.toString());
        assertEquals(expResult, result);
      
    }

    @Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        String name = "Bønke";
        String expResult = c1.getLastname();
        List<Customer> result = facade.findByLastName("Bønke");
        assertTrue(result.size() > 0);
        String resultName = result.get(0).getLastname();
        assertEquals(expResult, resultName);
    }

    @Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");       
        int expResult = 2;
        int result = facade.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    @Test
    public void testAllCustomers() {
        System.out.println("allCustomers");
        int expResult = 2;
        List<Customer> result = facade.allCustomers();
        assertEquals(expResult, result.size());
        for (Customer c: result){
           assertTrue(c.equals(c1) || c.equals(c2));
        }
    }

    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String fName = "Charles";
        String lName = "Bronson";
        int expResultSize = 3;
        Customer result = facade.addCustomer(fName, lName);
        assertEquals(fName, result.getFirstname());
        assertEquals(lName, result.getLastname());
        assertEquals(expResultSize, facade.getNumberOfCustomers());
    }

    @Test
    public void testDeleteAllCostumers() {
        System.out.println("deleteAllCostumers");
        int expResultSize = 0;
        facade.deleteAllCostumers();
        assertEquals(expResultSize, facade.getNumberOfCustomers());
        
    }
    
}
