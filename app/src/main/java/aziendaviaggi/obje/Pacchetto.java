package aziendaviaggi.obje;

/**
 * The Pacchetto class represents a package offered by a travel agency.
 * It contains information such as the code, name, description, price, and
 * associated entities (agency, guide, transportation, accommodation, and
 * destination).
 */
public class Pacchetto {
    private final String CodPacchetto;
    private final String Nome;
    private final String Descrizione;
    private final String Prezzo;
    private final String CodAgenzia;
    private final String CodGuida;
    private final String CodTrasporto;
    private final String CodAlloggio;
    private final String CodDestinazione;

    /**
     * Constructs a Pacchetto object with the specified parameters.
     *
     * @param codPacchetto    the code of the package
     * @param nome            the name of the package
     * @param descrizione     the description of the package
     * @param prezzo          the price of the package
     * @param codAgenzia      the code of the agency associated with the package
     * @param codGuida        the code of the guide associated with the package
     * @param codTrasporto    the code of the transportation associated with the
     *                        package
     * @param codAlloggio     the code of the accommodation associated with the
     *                        package
     * @param codDestinazione the code of the destination associated with the
     *                        package
     */
    public Pacchetto(final String codPacchetto, final String nome, final String descrizione, final String prezzo,
            final String codAgenzia,
            final String codGuida, final String codTrasporto, final String codAlloggio, final String codDestinazione) {
        this.CodPacchetto = codPacchetto;
        this.Nome = nome;
        this.Descrizione = descrizione;
        this.Prezzo = prezzo;
        this.CodAgenzia = codAgenzia;
        this.CodGuida = codGuida;
        this.CodTrasporto = codTrasporto;
        this.CodAlloggio = codAlloggio;
        this.CodDestinazione = codDestinazione;
    }

    /**
     * Returns the code of the package.
     *
     * @return the code of the package
     */
    public String getCodPacchetto() {
        return this.CodPacchetto;
    }

    /**
     * Returns the name of the package.
     *
     * @return the name of the package
     */
    public String getNome() {
        return this.Nome;
    }

    /**
     * Returns the description of the package.
     *
     * @return the description of the package
     */
    public String getDescrizione() {
        return this.Descrizione;
    }

    /**
     * Returns the price of the package.
     *
     * @return the price of the package
     */
    public String getPrezzo() {
        return this.Prezzo;
    }

    /**
     * Returns the code of the agency associated with the package.
     *
     * @return the code of the agency associated with the package
     */
    public String getCodAgenzia() {
        return this.CodAgenzia;
    }

    /**
     * Returns the code of the guide associated with the package.
     *
     * @return the code of the guide associated with the package
     */
    public String getCodGuida() {
        return this.CodGuida;
    }

    /**
     * Returns the code of the transportation associated with the package.
     *
     * @return the code of the transportation associated with the package
     */
    public String getCodTrasporto() {
        return this.CodTrasporto;
    }

    /**
     * Returns the code of the accommodation associated with the package.
     *
     * @return the code of the accommodation associated with the package
     */
    public String getCodAlloggio() {
        return this.CodAlloggio;
    }

    /**
     * Returns the code of the destination associated with the package.
     *
     * @return the code of the destination associated with the package
     */
    public String getCodDestinazione() {
        return this.CodDestinazione;
    }
}
