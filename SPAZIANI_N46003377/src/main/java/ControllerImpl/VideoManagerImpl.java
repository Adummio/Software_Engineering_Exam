package ControllerImpl;

import DAO.DAOException;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import DAO.VideoDAO;
import Entity.Video;
import java.util.List;
import Controller.VideoManager;

public class VideoManagerImpl implements VideoManager {

  public List<Video> ricercaTestuale(String testo) throws PersistenceException {
    TransactionManager tm = TransactionManagerFactory.createTransactionManager();
    tm.beginTransaction();
    try
    {
      List<Video> list = VideoDAO.readVideoNameORSport(tm,testo);
      tm.commitTransaction();
      return list;
    }
    catch(DAOException e)
    {
      tm.rollbackTransaction();
      throw new PersistenceException("Impossibile ottenere i video.", e);
    }
  }
}
