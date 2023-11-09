package Entity;

public class Giornalista {

  private int ID;
  private String nome;
  private String cognome;

  public Giornalista(int ID, String nome, String cognome) {
    this.ID = ID;
    this.nome = nome;
    this.cognome = cognome;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }


  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

}
