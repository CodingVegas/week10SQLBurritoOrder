package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Burrito;

public class BurritoDao {
  
  private Connection connection;
  private final String GET_BURRITOS_BY_ORDER_ID_QUERY = "SELECT * FROM burritos WHERE order_id = ?";
  private final String DELETE_BURRITOS_BY_ORDER_ID_QUERY = "DELETE FROM burritos WHERE order_id = ?";
  private final String CREATE_NEW_BURRITO_QUERY = "INSERT INTO burritos(meat, rice, beans, order_id) VALUES(?, ?, ?, ?)";
  private final String DELETE_BURRITO_BY_ID_QUERY = "DELETE FROM burritos WHERE id = ?";
  
  public BurritoDao() {
    connection = DBConnection.getConnection();
  }

  public List<Burrito> getBurritosByOrderId(int orderId) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_BURRITOS_BY_ORDER_ID_QUERY);
    ps.setInt(1, orderId);
    ResultSet rs = ps.executeQuery();
    List<Burrito> burritos = new ArrayList<Burrito>();
    
    while (rs.next()) {
      burritos.add(new Burrito(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
    }
    return burritos;
  }
  
  public void createNewBurrito(String meat, String rice, String beans, int orderId) throws SQLException{
    PreparedStatement ps = connection.prepareStatement(CREATE_NEW_BURRITO_QUERY);
    ps.setString(1, meat);
    ps.setString(2, rice);
    ps.setString(3, beans);
    ps.setInt(4, orderId);
    ps.executeUpdate();
  }

  public void deleteBurritosByOrderId(int orderId) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(DELETE_BURRITOS_BY_ORDER_ID_QUERY);
    ps.setInt(1, orderId);
    ps.executeUpdate();
  }
    
  public void deleteBurritoById(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(DELETE_BURRITO_BY_ID_QUERY);
    ps.setInt(1, id);
    ps.executeUpdate();
  }
  
}
