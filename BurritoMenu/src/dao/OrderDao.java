package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Order;

public class OrderDao {
  
  private Connection connection;
  private BurritoDao burritoDao;
  private final String GET_ORDERS_QUERY = "SELECT * FROM orders";
  private final String GET_ORDERS_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ?";
  private final String CREATE_NEW_ORDER_QUERY = "INSERT INTO orders (name) VALUES(?)";
  private final String DELETE_ORDER_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";
  

  public OrderDao() {
    connection = DBConnection.getConnection();
    burritoDao = new BurritoDao();
    }
  
  public List<Order> getOrders() throws SQLException {
    ResultSet rs = connection.prepareStatement(GET_ORDERS_QUERY).executeQuery();
    List<Order> orders = new ArrayList<Order>();
    
    while (rs.next()) {
      orders.add(populateOrder(rs.getInt(1), rs.getString(2)));
    }
    
    return orders;
  }
  
  public Order getOrderById(int id)throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_ORDERS_BY_ID_QUERY);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    rs.next();
    return populateOrder(rs.getInt(1), rs.getString(2));
  }
  
  public void createNewOrder(String orderName) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(CREATE_NEW_ORDER_QUERY);
    ps.setString(1, orderName);
    ps.executeUpdate();    
  }
  
  public void deleteOrderById(int id) throws SQLException {
    burritoDao.deleteBurritosByOrderId(id);
    PreparedStatement ps = connection.prepareStatement(DELETE_ORDER_BY_ID_QUERY);
    ps.setInt(1, id);
    ps.executeUpdate();
  }
  
  private Order populateOrder(int id, String name) throws SQLException {
    return new Order(id, name, burritoDao.getBurritosByOrderId(id));
  }
  
 }
