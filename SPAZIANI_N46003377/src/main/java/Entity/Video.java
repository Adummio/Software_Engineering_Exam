package Entity;

import java.util.Date;

public class Video {
  private int ID;
  private String nome;
  private String sport;
  private Date dataRegistrazione;

  public enum TIPO {
    APPROFONDIMENTO, EVENTO
  }

  public Video(int ID, String nome, String sport, Date dataRegistrazione) {
    this.ID = ID;
    this.nome = nome;
    this.sport = sport;
    this.dataRegistrazione = dataRegistrazione;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSport() {
    return sport;
  }

  public void setSport(String sport) {
    this.sport = sport;
  }

  public Date getDataRegistrazione() {
    return dataRegistrazione;
  }

  public void setDataRegistrazione(Date dataRegistrazione) {
    this.dataRegistrazione = dataRegistrazione;
  }
}
