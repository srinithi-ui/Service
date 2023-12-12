package quinbay.Inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quinbay.Inventory.model.constant.FieldNames;

import javax.persistence.*;

@Entity
@Table(name = FieldNames.PRODUCT_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @Id
    @Column(name  = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long id;

    @Column(name = FieldNames.NAME, nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  String name;

    @Column(name = FieldNames.PRICE, nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private double price ;


    @Column(name = FieldNames.QUANTITY, nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)

    private  int quantity;

    @ManyToOne
    @JoinColumn(name = FieldNames.CATEGORY_ID, nullable = false)
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
