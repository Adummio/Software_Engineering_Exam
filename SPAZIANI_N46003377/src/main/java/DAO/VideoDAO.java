package DAO;

import Entity.Giornalista;
import Entity.Video;
import Entity.Video.TIPO;
import Entity.Approfondimento;
import Entity.Evento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class VideoDAO {

  public VideoDAO() {}

  public static void createVideo(TransactionManager tm, Video v) throws DAOException {
    tm.assertInTransaction();
    try(PreparedStatement ps = tm.getConnection().prepareStatement("INSERT INTO VIDEO(ID, NOME, SPORT, DATA_REGISTRAZIONE, TIPO) VALUES(?,?,?,?,?)"))
    {
      ps.setInt(1, v.getID( ));
      ps.setString(2, v.getNome());
      ps.setString(3, v.getSport());
      ps.setDate(4, new java.sql.Date(v.getDataRegistrazione().getTime()));
      ps.setString(5, (v instanceof Approfondimento) ? TIPO.APPROFONDIMENTO.name() : TIPO.EVENTO.name());
      ps.executeUpdate();

    }
    catch(SQLException e)
    {
      throw new DAOException("Impossibile aggiungere il video.",e);
    }

    if (v instanceof Approfondimento) {
      Approfondimento approfondimento = (Approfondimento) v;

      for (Giornalista giornalista: approfondimento.getGiornalisti()) {
        try(PreparedStatement ps = tm.getConnection().prepareStatement("INSERT INTO VIDEO_APPROFONDIMENTO(VIDEO_ID, GIORNALISTA_NOME, GIORNALISTA_COGNOME) VALUES(?, ?, ?)"))
        {
          ps.setInt(1, approfondimento.getID( ));
          ps.setString(2, giornalista.getNome());
          ps.setString(3, giornalista.getCognome());
          ps.executeUpdate();

        }
        catch(SQLException e)
        {
          throw new DAOException("Impossibile aggiungere il video.",e);
        }
      }
    }


  }

  public static Video readVideo(TransactionManager tm, int ID) throws DAOException {
    tm.assertInTransaction();

    try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM VIDEO WHERE ID=?"))
    {
      ps.setInt(1, ID);
      try (ResultSet rs = ps.executeQuery()) {
        if(rs.next()) {
          return VideoDAO.loadVideo(tm, rs);
        } else {
          throw new DAOException("Video non trovato"); }
      }
    }
    catch (SQLException e) {
      throw new DAOException("Impossibile leggere il video "+ ID,e);
    }
  }

  public static List<Video> readVideoNameORSport(TransactionManager tm, String testo) throws DAOException {
    tm.assertInTransaction();
    List<Video> list = new ArrayList<>();
    try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM VIDEO WHERE (nome LIKE ?) OR (sport = ?)"))
    {
      ps.setString(1, "%" + testo + "%");
      ps.setString(2, testo);
      try (ResultSet rs = ps.executeQuery())
      {
        while(rs.next())
        {
          list.add(loadVideo(tm, rs));
        }
      }
    }
    catch (SQLException e) {
      throw new DAOException("Impossibile leggere i video", e);
    }
    return list;
  }

  public static Video loadVideo(TransactionManager tm, ResultSet rs)
      throws SQLException, DAOException {
    int ID = rs.getInt("ID");
    String nome = rs.getString("NOME");
    String sport = rs.getString("SPORT");
    java.sql.Date dataRegistrazione = rs.getDate("DATA_REGISTRAZIONE");
    String tipo = rs.getString("TIPO");

    if (tipo == "EVENTO") {
      return new Evento(ID, nome, sport, new Date(dataRegistrazione.getTime()));
    } else {
      try (PreparedStatement ps2 = tm.getConnection().prepareStatement("SELECT * FROM VIDEO_APPROFONDIMENTO WHERE VIDEO_ID=?")) {
        List<Giornalista> giornalisti = new ArrayList<>();

        ps2.setInt(1, ID);
        try (ResultSet results = ps2.executeQuery()) {
          while(results.next()) {
            giornalisti.add(
                GiornalistaDAO.readGiornalista(tm, results.getInt("GIORNALISTA_ID"))
            );
          }
          return new Approfondimento(ID, nome, sport, new Date(dataRegistrazione.getTime()), giornalisti);
        }
      }
    }
  }

  public static void updateVideo(TransactionManager tm, Video v) throws DAOException {

  }

  public static void deleteVideo(TransactionManager tm, int ID) throws DAOException {

  }
}