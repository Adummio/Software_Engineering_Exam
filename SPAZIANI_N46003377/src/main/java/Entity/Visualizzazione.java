package Entity;

import java.util.Date;

public class Visualizzazione {

  private Date giorno;
  private String ID;

  public Visualizzazione(Date giorno, String ID) {
    this.giorno = giorno;
    this.ID = ID;
  }

  public Date getGiorno() {
    return giorno;
  }

  public void setGiorno(Date giorno) {
    this.giorno = giorno;
  }
  
  public String getID() {
	  return ID;
  }
  
  public void setID(String ID) {
	  this.ID = ID;
  }
}
