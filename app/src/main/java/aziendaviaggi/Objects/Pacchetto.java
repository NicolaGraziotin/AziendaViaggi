package aziendaviaggi.Objects;

import javafx.beans.property.SimpleStringProperty;

public class Pacchetto {
    private SimpleStringProperty nome;
    private SimpleStringProperty descrizione;
    private SimpleStringProperty prezzo;

    public Pacchetto(String nome, String descrizione, String prezzo) {
        this.nome = new SimpleStringProperty(nome);
        this.descrizione = new SimpleStringProperty(descrizione);
        this.prezzo = new SimpleStringProperty(prezzo);
    }

    public String getNome() {
        return nome.get();
    }

    public String getDescrizione() {
        return descrizione.get();
    }

    public String getPrezzo() {
        return prezzo.get();
    }
}
