/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jobe
 */
public class MakeTestData {
    
    public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        try{
            BankCustomer b1 = new BankCustomer("Jens","Jensen","2354",3500.0,2,"Usally positive bank account");
            BankCustomer b2 = new BankCustomer("Palle","Holm","1337",1300.0,1,"guy is leet!");
            BankCustomer b3 = new BankCustomer("Kim","Larsen","5557",800.0,1,"Great but dead now");

            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.getTransaction().commit();
        } finally{
            em.close();
        }
        
    }
    
}
