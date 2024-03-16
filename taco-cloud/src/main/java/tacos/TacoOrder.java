package tacos;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;

/**
 * 卷饼订单类
 */
@Data
public class TacoOrder {

  private String deliveryName;

  private String deliveryStreet;

  private String deliveryCity;

  private String deliveryState;

  private String deliveryZip;

  private String ccNumber;

  private String ccExpiration;

  private String ccCVV;
  
  /**
   * 包含的卷饼
   */
  private List<Taco> tacos = new ArrayList<>();
  
  /**
   * 添加卷饼
   * @param taco 卷饼类
   */
  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }
}
