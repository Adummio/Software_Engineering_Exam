package Entity;

import java.util.List;

public class Playlist {
  private int ID;
  private String nome;
  private List<Video> lista;

  public Playlist(int ID, String nome, List<Video> lista) {
	this.ID = ID;
    this.nome = nome;
    this.lista = lista;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Video> getLista() {
    return lista;
  }

  public void setLista(List<Video> lista) {
    this.lista = lista;
  }
  
  public int getID() {
	  return ID;
  }
  
  public void setID(int ID) {
	  this.ID = ID;
  }
  
}
