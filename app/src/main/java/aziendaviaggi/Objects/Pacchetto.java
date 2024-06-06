package aziendaviaggi.Objects;

public class Pacchetto {
    private String CodPacchetto;
    private String Nome;
    private String Descrizione;
    private String Prezzo;
    private String CodAgenzia;
    private String CodGuida;
    private String CodTrasporto;
    private String CodAlloggio;
    private String CodDestinazione;

    public Pacchetto(String codPacchetto, String nome, String descrizione, String prezzo, String codAgenzia,
            String codGuida, String codTrasporto, String codAlloggio, String codDestinazione) {
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

    public String getCodPacchetto() {
        return this.CodPacchetto;
    }

    public String getNome() {
        return this.Nome;
    }

    public String getDescrizione() {
        return this.Descrizione;
    }

    public String getPrezzo() {
        return this.Prezzo;
    }

    public String getCodAgenzia() {
        return this.CodAgenzia;
    }

    public String getCodGuida() {
        return this.CodGuida;
    }

    public String getCodTrasporto() {
        return this.CodTrasporto;
    }

    public String getCodAlloggio() {
        return this.CodAlloggio;
    }

    public String getCodDestinazione() {
        return this.CodDestinazione;
    }
}
