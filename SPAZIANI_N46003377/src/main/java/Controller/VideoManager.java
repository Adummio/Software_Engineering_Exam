package Controller;

import java.util.List;

import ControllerImpl.PersistenceException;
import Entity.Video;

/**
 * 
 */
public interface VideoManager {
	
	public List<Video> ricercaTestuale(String testo) throws PersistenceException;

}