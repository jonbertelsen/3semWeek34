/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jobe
 */
public class FacadeRunner {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        BookFacade facade = BookFacade.getBookFacade(emf);
        
        Book b1 = facade.addBook("title 1", "Ole Olsen");
        Book b2 = facade.addBook("title 2", "Jønke");
        Book b3 = facade.addBook("title 3", "Svend Svin");
        
        List<Book> books = facade.getAllBooks();
        
        for (Book b: books){
            System.out.println(b.toString());
        }
        
    }
    
}
