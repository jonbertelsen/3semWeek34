/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jobe
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Customer findByID(int id){
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = em.find(Customer.class,id);
            return c;
        } finally {
            em.close();
        }
        
    };
    
    public List<Customer> findByLastName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastname = :lastname",Customer.class)
                    .setParameter("lastname", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    };
    
    public int getNumberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        int res = 0;
        try {
            Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");
            try {
                res = Integer.parseInt(q1.getSingleResult().toString());
            } catch (Exception e){
                throw(e);
            }
            return res;
            
        } finally {
            em.close();
        }
    };
    
    public List<Customer> allCustomers(){
     EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    };
    
    public Customer addCustomer(String fName, String lName){
        EntityManager em = emf.createEntityManager();
        try {
            Customer c1 = new Customer(fName, lName);
            em.getTransaction().begin();
            em.persist(c1);
            em.getTransaction().commit();
            return c1;
        } finally {
            em.close();
        }
    }
    
    public void deleteAllCostumers(){
        EntityManager em = emf.createEntityManager();
        try {
                em.getTransaction().begin();
                Query query = em.createQuery("DELETE FROM Customer c");
                query.executeUpdate();
                em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
  /*  public static void main(String[] args) {
         
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    Customer c1 = facade.addCustomer("Jon","Bertelsen");
    Customer c2 = facade.addCustomer("Ole","Olsen");
    
    //Find customer by ID
    System.out.println("Customer1: "+facade.findByID(c1.getId()).getFirstname());
    System.out.println("Customer2: "+facade.findByID(c2.getId()).getFirstname());
    //Find all books
    System.out.println("Number of books: "+facade.allCustomers().size());
    System.out.println("Number of rows in DB: " + facade.getNumberOfCustomers());
    List<Customer> customers = facade.findByLastName("Bertelsen");
    for (Customer c: customers){
        System.out.println(c.getFirstname() + " " + c.getLastname());
    }


    }
    */
}
