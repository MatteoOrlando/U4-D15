package Matteo_Orlando;

import entities_model.Libri;
import entities_model.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Catalogo {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();


        Utente utente1 = new Utente();
        utente1.setNome("Mario");
        utente1.setCognome("Rossi");
        utente1.setDataDiNascita("1985-05-15");
        utente1.setNumeroTessera("UT001");
        entityManager.persist(utente1);

        Utente utente2 = new Utente();
        utente2.setNome("Laura");
        utente2.setCognome("Bianchi");
        utente2.setDataDiNascita("1990-10-25");
        utente2.setNumeroTessera("UT002");
        entityManager.persist(utente2);


        Libri libro1 = new Libri();
        libro1.setTitolo("Il signore degli anelli");
        libro1.setISBN("9788804487902");
        libro1.setAnnoPubblicazione(1954);
        libro1.setNumeroPagine(1170);
        libro1.setAutore("J.R.R. Tolkien");
        libro1.setGenere("Fantasy");
        entityManager.persist(libro1);
        
        
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
    
    // Metodo per aggiungere un utente 
    private static void aggiungiUtente(EntityManager entityManager, String nome, String cognome, String dataDiNascita, String numeroTessera) {
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataDiNascita);
        utente.setNumeroTessera(numeroTessera);
        entityManager.persist(utente);
    }

    // Metodo per rimuoverlo tramite ISBN
    private static void rimuoviLibroPerISBN(EntityManager entityManager, String ISBN) {
        Libri libro = entityManager.find(Libri.class, ISBN);
        if (libro != null) {
            entityManager.remove(libro);
        } else {
            System.out.println("Libro non trovato per ISBN: " + ISBN);
        }
    }

    
}
    

