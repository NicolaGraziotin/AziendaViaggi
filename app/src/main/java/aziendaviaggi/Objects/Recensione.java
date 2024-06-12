package aziendaviaggi.objects;

/**
 * The Recensione class represents a review for a package.
 */
public class Recensione {
    private final String CodPacchetto;
    private final String Email;
    private final String Voto;
    private final String Commento;

    /**
     * Constructs a Recensione object with the specified parameters.
     *
     * @param codPacchetto The code of the package.
     * @param email The email of the reviewer.
     * @param voto The rating given by the reviewer.
     * @param commento The comment provided by the reviewer.
     */
    public Recensione(String codPacchetto, String email, String voto, String commento) {
        CodPacchetto = codPacchetto;
        Email = email;
        Voto = voto;
        Commento = commento;
    }

    /**
     * Gets the code of the package.
     *
     * @return The code of the package.
     */
    public String getCodPacchetto() {
        return CodPacchetto;
    }

    /**
     * Gets the email of the reviewer.
     *
     * @return The email of the reviewer.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Gets the rating given by the reviewer.
     *
     * @return The rating given by the reviewer.
     */
    public String getVoto() {
        return Voto;
    }

    /**
     * Gets the comment provided by the reviewer.
     *
     * @return The comment provided by the reviewer.
     */
    public String getCommento() {
        return Commento;
    }
}
