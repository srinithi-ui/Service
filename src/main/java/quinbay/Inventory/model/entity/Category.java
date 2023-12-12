package quinbay.Inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import quinbay.Inventory.model.constant.FieldNames;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = FieldNames.CATEGORY_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Category {

    @Id
    @Column(name  = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private Long id;

    @Column(name = FieldNames.NAME, nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  String name;

//
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<quinbay.Inventory.model.entity.Product>  products;



}
