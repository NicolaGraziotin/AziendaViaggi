package aziendaviaggi.Objects;

public class Pacchetto {
    private String codPacchetto;
    private String nome;
    private String descrizione;
    private String prezzo;
    private String codAgenzia;
    private String codGuida;
    private String codTrasporto;
    private String codAlloggio;
    private String codDestinazione;

    public Pacchetto(String codPacchetto, String nome, String descrizione, String prezzo, String codAgenzia,
            String codGuida, String codTrasporto, String codAlloggio, String codDestinazione) {
        this.codPacchetto = codPacchetto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.codAgenzia = codAgenzia;
        this.codGuida = codGuida;
        this.codTrasporto = codTrasporto;
        this.codAlloggio = codAlloggio;
        this.codDestinazione = codDestinazione;
    }

    public String getCodPacchetto() {
        return this.codPacchetto;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public String getPrezzo() {
        return this.prezzo;
    }

    public String getCodAgenzia() {
        return this.codAgenzia;
    }

    public String getCodGuida() {
        return this.codGuida;
    }

    public String getCodTrasporto() {
        return this.codTrasporto;
    }

    public String getCodAlloggio() {
        return this.codAlloggio;
    }

    public String getCodDestinazione() {
        return this.codDestinazione;
    }
}
