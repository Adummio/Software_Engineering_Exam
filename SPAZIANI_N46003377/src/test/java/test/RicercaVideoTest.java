package test;

import static org.junit.Assert.*;

import Controller.ISistema;
import ControllerImpl.SistemaImpl;
import ControllerImpl.PersistenceException;
import DAO.TransactionManager;
import DAO.TransactionManagerFactory;
import Entity.Video;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RicercaVideoTest {

  @BeforeClass
  public static void initializeDB() throws Exception {
    TransactionManager tm = TransactionManagerFactory.createTransactionManager();
    tm.beginTransaction();
    Statement stmt = tm.getConnection().createStatement();
    stmt.execute("DROP TABLE IF EXISTS VIDEO");
    
    stmt.execute("CREATE TABLE VIDEO ( ID INTEGER PRIMARY KEY, NOME VARCHAR(100), SPORT VARCHAR(20), DATA_REGISTRAZIONE DATE, TIPO VARCHAR(25))");
    stmt.execute("INSERT INTO VIDEO VALUES('1','Inter-Chievo','Calcio','2019-05-13', 'evento')");
    stmt.execute("INSERT INTO VIDEO VALUES('2','Bologna-Napoli','Calcio','2019-05-13', 'evento')");
    stmt.execute("INSERT INTO VIDEO VALUES('3','Napoli-Sampdoria','Calcio','2018-03-12', 'evento')");
    stmt.execute("INSERT INTO VIDEO VALUES('4','Abruzzese-Spal','Calcio','1997-12-24', 'evento')");
    stmt.execute("INSERT INTO VIDEO VALUES('5','Abruzzese-Napoli','Calcio','2019-05-16', 'evento')");
    stmt.execute("INSERT INTO VIDEO VALUES('6','Carlsen-Caruana','Scacchi','2018-11-11','evento')");
    stmt.execute("INSERT INTO VIDEO VALUES('7','Tal-Fisher','Scacchi','1978-04-10','evento')");
    stmt.execute("INSERT INTO VIDEO VALUES('8','Nadal-Federer','Tennis','2015-08-12','evento')");
    
    stmt.execute("INSERT INTO VIDEO VALUES('9','Fuorigioco','Calcio','2017-04-11','approfondimento')");
    stmt.execute("INSERT INTO VIDEO VALUES('10','Evviva il calcio','Calcio','2016-02-02','approfondimento')");
    stmt.execute("INSERT INTO VIDEO VALUES('11','Sport serio','Scacchi','2019-02-14','approfondimento')");
    stmt.execute("INSERT INTO VIDEO VALUES('12','Abbasso la violenza','MMA','2019-01-01','approfondimento')");
    stmt.execute("INSERT INTO VIDEO VALUES('13','Sport','Basket','2012-02-12','approfondimento')");
    stmt.execute("INSERT INTO VIDEO VALUES('14','Lungolinea','Tennis','2016-01-20','approfondimento')");
    stmt.execute("INSERT INTO VIDEO VALUES('15','Mi piace Tennis','MMA','2000-01-01','approfondimento')");
    
    tm.commitTransaction();
  }

  private ISistema instance;

  @Before
  public void setUpClass() throws Exception {
    instance = SistemaImpl.getInstance();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testRicercaZeroMatches() throws PersistenceException {
    List<Video> list = instance.ricercaTestuale("abcdefg");
    assertEquals(0, list.size());
  }

  @Test
  public void testRicercaNomeOneMatch() throws PersistenceException {
    List<Video> list = instance.ricercaTestuale("Tal");
    assertEquals(1, list.size());
  }

  @Test
  public void testRicercaNomeTwoMatches() throws PersistenceException {
    List<Video> list = instance.ricercaTestuale("Abruzzese");
    assertEquals(2, list.size());
  }
  
  @Test
  public void testRicercaNomeMoreMatches() throws PersistenceException {
	 List<Video> list = instance.ricercaTestuale("Napoli");
	 assertEquals(3, list.size());
  }

  @Test
  public void testRicercaSportOneMatch() throws PersistenceException {
    List<Video> list = instance.ricercaTestuale("Basket");
    assertEquals(1, list.size());
  }

  @Test
  public void testRicercaSportTwoMatches() throws PersistenceException {
    List<Video> list = instance.ricercaTestuale("MMA");
    assertEquals(2, list.size());
  }
  
  @Test
  public void testRicercaSportMoreMatches() throws PersistenceException {
	 List<Video> list = instance.ricercaTestuale("Calcio");
	 assertEquals(7, list.size());
  }
  
  @Test
  public void testRicercaMista() throws PersistenceException {
	  List<Video> list = instance.ricercaTestuale("Tennis");
	  assertEquals(3, list.size());
  }

}
