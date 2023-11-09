package Entity;

import java.util.Date;
import java.util.List;

public class Approfondimento extends Video {

  private List<Giornalista> giornalisti;

  public Approfondimento(int ID, String nome, String sport,
      Date dataRegistrazione, List<Giornalista> giornalisti) {
    super(ID, nome, sport, dataRegistrazione);
    this.giornalisti = giornalisti;
  }

  public List<Giornalista> getGiornalisti() {
    return giornalisti;
  }

  public void setGiornalisti(List<Giornalista> giornalisti) {
    this.giornalisti = giornalisti;
  }
}
