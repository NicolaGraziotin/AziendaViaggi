package aziendaviaggi.objects;

public class Recensione {
    private final String CodPacchetto;
    private final String Email;
    private final String Voto;
    private final String Commento;

    public Recensione(String codPacchetto, String email, String voto, String commento) {
        CodPacchetto = codPacchetto;
        Email = email;
        Voto = voto;
        Commento = commento;
    }

    public String getCodPacchetto() {
        return CodPacchetto;
    }

    public String getEmail() {
        return Email;
    }

    public String getVoto() {
        return Voto;
    }

    public String getCommento() {
        return Commento;
    }
}
