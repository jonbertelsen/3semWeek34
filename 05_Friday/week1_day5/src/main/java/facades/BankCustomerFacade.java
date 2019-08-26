package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getBankCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CustomerDTO getCustomerByID(long id){
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer bc = em.find(BankCustomer.class,id);
            CustomerDTO cDTO = new CustomerDTO(bc);
          return cDTO;
      } finally {
          em.close();
      }
    }
   
            
    public List<CustomerDTO> getCustomerByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = 
                       em.createQuery("Select bc from BankCustomer bc WHERE bc.firstName =:name", BankCustomer.class)
                        .setParameter("name", name);
            List<BankCustomer> lbc = query.getResultList();
            List<CustomerDTO> lcDTO = new LinkedList<>();
            for (BankCustomer bc: lbc){
                lcDTO.add(new CustomerDTO(bc));
            }
            return lcDTO;
        }finally {
            em.close();
        }
        
    }
          
    public BankCustomer addCustomer(BankCustomer cust){
        EntityManager em = emf.createEntityManager();
        try {
                em.getTransaction().begin();
                em.persist(cust);
                em.getTransaction().commit();
                return cust;
        } finally {
            em.close();
        }
    
    }
   
    public List<BankCustomer> getAllBankCustomers(){
           EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = 
                 em.createQuery("Select bc from BankCustomer bc", BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
       
    }


}
