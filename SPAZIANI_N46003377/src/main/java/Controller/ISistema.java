package Controller;

import ControllerImpl.PersistenceException;
import Entity.Video;
import java.util.List;

public interface ISistema {
  List<Video> ricercaTestuale(String testo) throws PersistenceException;
  //isEmpty si trova in java.util.List

}
