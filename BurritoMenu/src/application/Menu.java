package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.BurritoDao;
import dao.OrderDao;
import entity.Burrito;
import entity.Order;

public class Menu {
  
  private OrderDao orderDao = new OrderDao();
  private BurritoDao burritoDao = new BurritoDao();
  private Scanner scanner = new Scanner(System.in);
  private List<String> options = Arrays.asList(
      "Display all Orders", 
      "Display an individual order", 
      "Create new order", 
      "Delete Order",
      "Add a Burrito!",
      "Delete a Burrito");
  
  public void start() {
    String selection = "";
    
    do {
        printMenu();
        selection = scanner.nextLine();
        
        try {
          if (selection.equals("1")) {
            displayOrders();
          } else if (selection.equals("2")) {
            displayOrder();
          }else if (selection.equals("3")) {
            createOrder();
          }else if (selection.equals("4")) {
            deleteOrder();
          }else if (selection.equals("5")) {
            createBurrito();
          }else if (selection.equals("6")) {
           deleteBurrito();
          }
        } catch(SQLException e) {
          e.printStackTrace();
        }
     
        System.out.println("Press enter to continue...........");
        scanner.nextLine();
        
    } while(!selection.equals("-1"));
  }
  
  private void printMenu() {
    System.out.println("Select an Option: \n------------------------------------------");
    for (int i = 0; i < options.size(); i++) {
      System.out.println(i+1 + ") " + options.get(i));
      
    }
  }
  
  private void displayOrders() throws SQLException {
    List<Order> orders = orderDao.getOrders();
    for (Order order : orders) {
      System.out.println(order.getOrderId() + ": " + order.getName());
    }
  }
  
  private void displayOrder() throws SQLException {
    System.out.print("Enter Order ID: ");
    int id = Integer.parseInt(scanner.nextLine());
    Order order = orderDao.getOrderById(id);
    System.out.println(order.getOrderId() + ": " + order.getName());
    for (Burrito burrito : order.getBurritos()) {
      System.out.println("\tBurrito ID: " + burrito.getBurritoId() + " This delicious burrito will be made with " + burrito.getMeat() + ", " + burrito.getRice() + ", " +  burrito.getBeans());
    }
  }

  private void createOrder() throws SQLException {
    System.out.print("Enter new Order name: ");
    String orderName = scanner.nextLine();
    orderDao.createNewOrder(orderName);
    
  }
  
  private void deleteOrder() throws SQLException {
    System.out.println("Enter order ID to delete: ");
    int id = Integer.parseInt(scanner.nextLine());
    orderDao.deleteOrderById(id);
  }
  
  private void createBurrito() throws SQLException {
    System.out.println("What type of meat would you like?");
    String meat = scanner.nextLine();
    System.out.println("What type of rice would you like?");
    String rice = scanner.nextLine();
    System.out.println("What type of beans would you like?");
    String beans = scanner.nextLine();
    System.out.println("Enter order ID for your new burrito");
    int orderId = Integer.parseInt(scanner.nextLine());
    burritoDao.createNewBurrito(meat, rice, beans, orderId);
    
  }
  
  private void deleteBurrito() throws SQLException {
    System.out.println("Enter burrito ID to delete: ");
    int id = Integer.parseInt(scanner.nextLine());
    burritoDao.deleteBurritoById(id);
  }
  
  
}
