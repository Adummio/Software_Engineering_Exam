package ControllerImpl;

import Controller.ISistema;
import Entity.Video;
import java.util.List;

public final class SistemaImpl implements ISistema {

  private static ISistema INSTANCE;
  private final VideoManagerImpl defaultVideoManager;

  private SistemaImpl(VideoManagerImpl defaultVideoManager) {
    this.defaultVideoManager = defaultVideoManager;
  }

  public static synchronized ISistema getInstance() {
    if(INSTANCE == null) {
      INSTANCE = new SistemaImpl(new VideoManagerImpl());
    }

    return INSTANCE;
  }

  @Override
  public List<Video> ricercaTestuale(String testo) throws PersistenceException {
    return defaultVideoManager.ricercaTestuale(testo);
  }

}
