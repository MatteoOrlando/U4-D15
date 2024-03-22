package entities_model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Riviste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String ISBN;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;
    private String periodicità;
}
