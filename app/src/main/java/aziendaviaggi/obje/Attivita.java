package aziendaviaggi.obje;

/**
 * The Attivita class represents an activity in a travel agency.
 * It contains information about the code, name, description, schedule, and
 * duration of the activity.
 */
public class Attivita {
    private final String CodAttivita;
    private final String Nome;
    private final String Descrizione;
    private final String Orario;
    private final String Durata;

    /**
     * Constructs a new Attivita object with the specified parameters.
     *
     * @param codAttivita the code of the activity
     * @param nome        the name of the activity
     * @param descrizione the description of the activity
     * @param orario      the schedule of the activity
     * @param durata      the duration of the activity
     */
    public Attivita(final String codAttivita, final String nome, final String descrizione, final String orario,
            final String durata) {
        this.CodAttivita = codAttivita;
        this.Nome = nome;
        this.Descrizione = descrizione;
        this.Orario = orario;
        this.Durata = durata;
    }

    /**
     * Returns the code of the activity.
     *
     * @return the code of the activity
     */
    public String getCodAttivita() {
        return this.CodAttivita;
    }

    /**
     * Returns the name of the activity.
     *
     * @return the name of the activity
     */
    public String getNome() {
        return this.Nome;
    }

    /**
     * Returns the description of the activity.
     *
     * @return the description of the activity
     */
    public String getDescrizione() {
        return this.Descrizione;
    }

    /**
     * Returns the schedule of the activity.
     *
     * @return the schedule of the activity
     */
    public String getOrario() {
        return this.Orario;
    }

    /**
     * Returns the duration of the activity.
     *
     * @return the duration of the activity
     */
    public String getDurata() {
        return this.Durata;
    }
}
