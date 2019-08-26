/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
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
public class BankCustomerFacadeTest {
    
    private static EntityManagerFactory emf;
    private static BankCustomerFacade facade;
    
    public BankCustomerFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("pu");
        facade = BankCustomerFacade.getBankCustomerFacade(emf);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetBankCustomerFacade() {
        System.out.println("getBankCustomerFacade");
        assertNotNull(facade);
    }

    @Test
    public void testGetCustomerByID() {
        System.out.println("getCustomerByID");
        long id = 1;
        CustomerDTO cDTO = facade.getCustomerByID(id);
        assertEquals("Palle Holm", cDTO.getFullName());
    }

    @Test
    public void testGetCustomerByName() {
        System.out.println("getCustomerByName");
        String name = "Jens";
        List<CustomerDTO> result = facade.getCustomerByName(name);
        assertTrue(result.size() > 0);
    }

    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        List<BankCustomer> init = facade.getAllBankCustomers();
        int initSize = init.size();
        BankCustomer cust = new BankCustomer("Jon","Bertelsen","3254",4700.0,2,"Watch out for this guy");
        BankCustomer bc = facade.addCustomer(cust);
        List<BankCustomer> updated = facade.getAllBankCustomers();
        int updatedSize = updated.size();
        assertEquals(initSize, updatedSize - 1);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from BankCustomer bc where bc.firstName = 'Jon'").executeUpdate();
        em.getTransaction().commit();
    }
    
    @Test
    public void testGetAllBankCustomers() {
        System.out.println("getAllBankCustomers");
        List<BankCustomer> init = facade.getAllBankCustomers();
        int initSize = init.size();
        int expSize = 6;
        assertEquals(expSize, initSize);
    }
 
}
