package tacos;

import lombok.Data;

/**
 * 配料类
 */
@Data
public class Ingredient {
  
  private final String id;
  
  private final String name;
  
  /**
   * 配料类型
   */
  private final Type type;
  
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
