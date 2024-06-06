package aziendaviaggi.Objects;

public class Attivita {
    private String CodAttivita;
    private String Nome;
    private String Descrizione;
    private String Orario;
    private String Durata;

    public Attivita(String codAttivita, String nome, String descrizione, String orario, String durata) {
        this.CodAttivita = codAttivita;
        this.Nome = nome;
        this.Descrizione = descrizione;
        this.Orario = orario;
        this.Durata = durata;
    }

    public String getCodAttivita() {
        return this.CodAttivita;
    }

    public String getNome() {
        return this.Nome;
    }

    public String getDescrizione() {
        return this.Descrizione;
    }

    public String getOrario() {
        return this.Orario;
    }

    public String getDurata() {
        return this.Durata;
    }
}
