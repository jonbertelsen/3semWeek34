package facades;

import entities.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BookFacade {

    private static BookFacade instance;
    private static EntityManagerFactory emf;

    private BookFacade() {}
    
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Book> getAllBooks(){
        EntityManager em = getEntityManager();
        try {
            List<Book> books = em.createQuery("SELECT b FROM Book b").getResultList();
            return books;
        }
        finally {
            em.close();
        }
        
    }
    
}
