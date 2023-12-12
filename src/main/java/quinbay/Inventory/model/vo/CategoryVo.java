package quinbay.Inventory.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import quinbay.Inventory.*;
import quinbay.Inventory.model.entity.Product;
import java.util.List;
@Data
public class CategoryVo {
    private Long id;
    private String name;
//
//    @JsonManagedReference
//    public List<Product> productsList;

}
