package aziendaviaggi.Objects;

public class Pacchetto {
    private String nome;
    private String descrizione;
    private String prezzo;

    public Pacchetto(String nome, String descrizione, String prezzo) {
        this.nome = new String(nome);
        this.descrizione = new String(descrizione);
        this.prezzo = new String(prezzo);
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
}
