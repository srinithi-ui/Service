package quinbay.Inventory.httpconnector;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quinbay.Inventory.model.entity.Product;
import quinbay.Inventory.model.vo.ProductVo;
import quinbay.Inventory.services.Services;
import java.util.List;
import java.util.jar.JarException;

@RestController
@RequestMapping("/httpmethod")
public class HttpConnectoryInventory {

    @Autowired
    Services service;
//    @GetMapping("/redisCache")
//    public String RedisCache(@RequestParam String key, @RequestParam String value){
//        return service.redisCache(key, value);
//    }
    @GetMapping("/product")
    public List<ProductVo> getProductDetails(){

        return service.getProductList();
    }
    @GetMapping("/product/name")
    public List<ProductVo> getProductByName(@RequestParam String name){

        return service.getProductByName(name);
    }
    @GetMapping("/product/category/{category}")
    public List<ProductVo> getProductByCategory(@PathVariable int category){
        return service.getProductByCategory(category);
    }

    @PostMapping("/product/add")
    public String postProductDetails(@RequestBody ProductVo product) throws JsonProcessingException {
        return service.postProductList(product);
    }

    @PutMapping("/product/update")
    public String putProductDetails(@RequestBody ProductVo product, @RequestParam long id) throws JsonProcessingException{
        return service.putProductList(product, id);
    }

    @DeleteMapping("product/delete")
    public String deleteProductDetails(@RequestBody ProductVo product)  {
        return service.deleteProductList(product);
    }

    @GetMapping("product/check")
    public boolean checkProduct(@RequestParam long id, @RequestParam int quantity){
        return service.checkProduct(id, quantity);
    }
    @PostMapping("/publish")
    public void publishKafka(@RequestBody Product prod) throws JsonProcessingException {
        service.sendPriceDropNotification(prod);
    }















}
