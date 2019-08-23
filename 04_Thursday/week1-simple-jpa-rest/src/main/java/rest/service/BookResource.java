package rest.service;

import com.google.gson.Gson;
import entities.Book;
import facades.BookFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("book")
public class BookResource {

    private static EntityManagerFactory emf  = Persistence.createEntityManagerFactory("pu");
    private static BookFacade facade = BookFacade.getBookFacade(emf);
    private static Gson gson = new Gson();
            
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBooks() {
        List<Book> books = facade.getAllBooks();
        return gson.toJson(books);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(entities.Book entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(entities.Book entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
