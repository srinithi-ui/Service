package quinbay.Inventory.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import quinbay.Inventory.data.*;
import quinbay.Inventory.model.vo.ProductVo;

import java.util.List;


public interface Services {

    List<ProductVo> getProductList();
    List<ProductVo> getProductByCategory(int category);
    List<ProductVo> getProductByName(String name);
    String postProductList(ProductVo product) throws JsonProcessingException;

    String putProductList(ProductVo product, long id) throws JsonProcessingException;
    String deleteProductList(ProductVo product);
    boolean checkProduct(long id, int quantity);
    void sendPriceDropNotification(quinbay.Inventory.model.entity.Product prod) throws JsonProcessingException;

//    String redisCache(String key, String value);
//    void listen(String message);
//    void postKafka(String message) throws JsonProcessingException;
}
