package entity;

import java.util.List;

public class Order {
  
  private int orderId;
  private String name;
  private List<Burrito> burritos;
  
  public Order(int orderId, String name, List<Burrito> burritos) {
    this.setOrderId(orderId);
    this.setName(name);
    this.setBurritos(burritos);
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Burrito> getBurritos() {
    return burritos;
  }

  public void setBurritos(List<Burrito> burritos) {
    this.burritos = burritos;
  }
 }