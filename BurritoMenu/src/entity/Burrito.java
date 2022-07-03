package entity;

public class Burrito {
  
  private int burritoId;
  private String meat;
  private String rice;
  private String beans;
  
  public Burrito(int burritoId, String meat, String rice, String beans) {
    this.setBurritoId(burritoId);
    this.setMeat(meat);
    this.setRice(rice);
    this.setBeans(beans);
  }

  public int getBurritoId() {
    return burritoId;
  }

  public void setBurritoId(int burritoId) {
    this.burritoId = burritoId;
  }

  public String getMeat() {
    return meat;
  }

  public void setMeat(String meat) {
    this.meat = meat;
  }

  public String getRice() {
    return rice;
  }

  public void setRice(String rice) {
    this.rice = rice;
  }

  public String getBeans() {
    return beans;
  }

  public void setBeans(String beans) {
    this.beans = beans;
  }
}
