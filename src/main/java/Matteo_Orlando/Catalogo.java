package Matteo_Orlando;

import entities_model.Libri;
import entities_model.Utente;

import javax.persistence.*;
import java.util.List;

public class Catalogo {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();

        aggiungiUtente(entityManager, "Giovanni", "Storti", "1954-02-10", "UT004");
        aggiungiUtente(entityManager, "Renato", "Zero", "1943-10-23", "UT005");

        rimuoviLibroPerISBN(entityManager, "9788804487902");

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

        Utente utente3 = new Utente();
        utente3.setNome("Gianni");
        utente3.setCognome("Neri");
        utente3.setDataDiNascita("1965-12-16");
        utente3.setNumeroTessera("UT003");
        entityManager.persist(utente3);
        entityManager.refresh(utente3);


        Libri libro1 = new Libri();
        libro1.setTitolo("Harry Potter e la pietra Filosofale");
        libro1.setISBN("9788804487902");
        libro1.setAnnoPubblicazione(1997);
        libro1.setNumeroPagine(304);
        libro1.setAutore("J.K. Rowling");
        libro1.setGenere("Fantasy");
        entityManager.persist(libro1);

        Libri libro2 = new Libri();
        libro2.setTitolo("Harry Potter e la camera dei segreti");
        libro2.setISBN("9788804487903");
        libro2.setAnnoPubblicazione(1998);
        libro2.setNumeroPagine(336);
        libro2.setAutore("J.K. Rowling");
        libro2.setGenere("Fantasy");
        entityManager.persist(libro2);

        String ISBN = "9788804487903";
        List<Libri> risultatiRicerca = ricercaPerISBN(entityManager, ISBN);
        if (!risultatiRicerca.isEmpty()) {
            System.out.println("Libro trovato:");
            for (Libri libro : risultatiRicerca) {
                System.out.println("Titolo: " + libro.getTitolo());
                System.out.println("Autore: " + libro.getAutore());

            }
        } else {
            System.out.println("Nessun libro trovato per l'ISBN: " + ISBN);
        }
        
        
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
    // Metodo per ricerca per ISBN
    private static List<Libri> ricercaPerISBN(EntityManager entityManager, String ISBN) {
        String jpql = "SELECT l FROM Libri l WHERE l.ISBN = :ISBN";
        TypedQuery<Libri> query = entityManager.createQuery(jpql, Libri.class);
        query.setParameter("ISBN", ISBN);
        return query.getResultList();
    }

    // Metodo per ricerca per anno di pubblicazione
    private static List ricercaPerAnnoPubblicazione(EntityManager entityManager, int anno) {
        Query query = entityManager.createQuery("SELECT l FROM Libri l WHERE l.annoPubblicazione = :anno");
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    // Metodo per ricerca per autore
    private static List ricercaPerAutore(EntityManager entityManager, String autore) {
        Query query = entityManager.createQuery("SELECT l FROM Libri l WHERE l.autore = :autore");
        query.setParameter("autore", autore);
        return query.getResultList();
    }
    
}
    

