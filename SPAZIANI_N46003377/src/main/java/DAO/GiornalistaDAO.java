package DAO;

import Entity.Giornalista;
import Entity.Video;
import Entity.Video.TIPO;
import Entity.Approfondimento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class GiornalistaDAO {

  public static Giornalista readGiornalista(TransactionManager tm, int ID) throws DAOException {
    tm.assertInTransaction();

    try (PreparedStatement ps = tm.getConnection().prepareStatement("SELECT * FROM GIORNALISTA WHERE ID=?"))
    {
      ps.setInt(1, ID);
      try (ResultSet rs = ps.executeQuery()) {
        if(rs.next()) {
          return new Giornalista(
              rs.getInt("ID"),
              rs.getString("NOME"),
              rs.getString("COGNOME")

          );
        } else {
          throw new DAOException("Giornalista non trovato"); }
      }
    }
    catch (SQLException e) {
      throw new DAOException("Impossibile leggere il giornalista "+ ID,e);
    }
  }
}