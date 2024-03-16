package tacos;
import java.util.List;
import lombok.Data;

/**
 * 卷饼类
 */
@Data
public class Taco {

  private String name;

  private List<Ingredient> ingredients;

}
