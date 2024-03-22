package entities_model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class gestione_prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Ragionamento delle relazioni:
    //1)ogni oggetto prestito puó essere associato ad un solo utente,
    // ma possono esserci piú "prestiti", cioé piu oggetti prestati diversi associati ad un solo utente
    @ManyToOne
    private Utente utente;



    //2)piú prestiti possono essere associati al libro,
    // ma ogni prestito é legato, di volta in volta, ad un singolo libro
    @ManyToOne
    private Libri libro;



    //3)piú prestiti possono essere associati al libro,
    // ma ogni prestito é legato, ad una singola rivista
    @ManyToOne
    private Riviste rivista;


    //Questo valore indica che il campo rappresenta solo una data (senza orario) nel database.
    //volendo si puó inserire .TIME, oppure .TIMESTAMP,
    // per rappresentare nel db rispettivamente, solo l'ora senza data,
    // oppure rappresentare entrambe le cose (google)
    @Temporal(TemporalType.DATE)
    private Date dataInizioPrestito;

    @Temporal(TemporalType.DATE)
    private Date dataRestituzionePrevista;

    @Temporal(TemporalType.DATE)
    private Date dataRestituzioneEffettiva;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Libri getLibro() {
        return libro;
    }

    public void setLibro(Libri libro) {
        this.libro = libro;
    }

    public Riviste getRivista() {
        return rivista;
    }

    public void setRivista(Riviste rivista) {
        this.rivista = rivista;
    }

    public Date getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(Date dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }


    public Date getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(Date dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public Date getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(Date dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }
}
