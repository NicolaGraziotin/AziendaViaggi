package aziendaviaggi.Objects;

public class Pacchetto {
    private String codPacchetto;
    private String nome;
    private String descrizione;
    private String prezzo;
    private String codAgenzia;

    public Pacchetto(String codPacchetto, String nome, String descrizione, String prezzo, String codAgenzia) {
        this.codPacchetto = codPacchetto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.codAgenzia = codAgenzia;
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
        return codAgenzia;
    }
}
