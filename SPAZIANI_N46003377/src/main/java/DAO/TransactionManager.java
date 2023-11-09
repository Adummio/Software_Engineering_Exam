package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;



public class TransactionManager {
  private final String DATABASE_PATH;
  private final String DATABASE_USERNAME;
  private final String DATABASE_PASSWORD;
  private Connection connection;
  private boolean inTransaction;

  protected TransactionManager(String databasePath, String databaseUsername, String databasePassword) {
    this.DATABASE_PATH = databasePath;
    this.DATABASE_USERNAME = databaseUsername;
    this.DATABASE_PASSWORD = databasePassword;
  }

  public static class TransactionStateException extends RuntimeException {
    public TransactionStateException(String message) {
      super(message);
    }
  }



  public void beginTransaction() throws TransactionStateException {
    assertNotInTransaction();

    try {
      connection = DriverManager.getConnection(DATABASE_PATH, DATABASE_USERNAME, DATABASE_PASSWORD);
      connection.setAutoCommit(false);

      inTransaction = true;
    } catch (SQLException e) {
      throw new RuntimeException("Impossible to create a new connection!", e);
    }
  }

  public Connection getConnection() {
    return connection;
  }


  public void assertInTransaction() throws TransactionStateException {
    if (isInTransaction() == false) {
      throw new TransactionStateException("Transaction not active!");
    }
  }

  public void assertNotInTransaction() throws TransactionStateException {
    if (isInTransaction() == true) {
      throw new TransactionStateException("Transaction is active!");
    }
  }


  public boolean isInTransaction() {
    return inTransaction;
  }



  public void commitTransaction() throws TransactionStateException, DAOException {
    try {
      assertInTransaction();

      connection.commit();
      connection.close();
      connection = null;
      inTransaction = false;
    } catch (SQLException e) {
      throw new DAOException("Impossible to commit the transaction!", e);
    }
  }

  public void rollbackTransaction() throws TransactionStateException {
    try {
      assertInTransaction();

      connection.rollback();
      connection.close();
      connection = null;
      inTransaction = false;
    } catch (SQLException e) {
      throw new RuntimeException("Unexpected exception during rollback!", e);
    }
  }

}


