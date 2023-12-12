package quinbay.Inventory.servicesimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import quinbay.Inventory.dao.api.ProductRepository;
import quinbay.Inventory.model.vo.ProductVo;
import quinbay.Inventory.model.entity.Product;
import quinbay.Inventory.services.Services;

import java.util.*;
@Service
public class ServicesImpl implements Services {

//    @Override
//    @Cacheable(value = "springboot", key = "#key")
//    public String redisCache(String key, String value){
//        return value;
//    }


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    ProductRepository productRepository;



//    @KafkaListener(topics="com.quinbay.product.create", groupId = "group-id")
//    public void listen(String message){
//        System.out.println("Received "+message);
//    }
//
//    @KafkaListener(topics = "com.quinbay.product.create", groupId = "group-id")
//    public void postKafka(String details) throws JsonProcessingException {
////        ObjectMapper objectMapper = new ObjectMapper();
////        Product product = objectMapper.readValue(details, Product.class);
////        System.out.println(product.getProductId());
//        System.out.println("de");
//    }

    @Override
    public List<ProductVo> getProductList(){
        List<quinbay.Inventory.model.entity.Product> productList = productRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(productList, List.class);


    }
    @Override
    public List<ProductVo> getProductByName(String name){
        List<quinbay.Inventory.model.entity.Product> productList = productRepository.findByName(name);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(productList, List.class);


    }

    public List<ProductVo> getProductByCategory(int category){
        List<quinbay.Inventory.model.entity.Product> productList = productRepository.findByCategory(category);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(productList, List.class);


    }


    @Override
    public String postProductList(ProductVo productVo) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        productRepository.save(objectMapper.convertValue(productVo, quinbay.Inventory.model.entity.Product.class));
        return "Done";

    }

    public String putProductList(ProductVo productVo, long id) throws JsonProcessingException{
        Optional<quinbay.Inventory.model.entity.Product> productList = productRepository.findById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        Product productUpdate = objectMapper.convertValue(productVo, quinbay.Inventory.model.entity.Product.class);
//        System.out.println(productList);

        if(productList.isEmpty())
            return "not found";


        else {
            System.out.println(productVo.getPrice()+" "+productList.get().getPrice());
            if (productVo.getPrice() < productUpdate.getPrice()) {
                System.out.println("Kafka");
                sendPriceDropNotification(productUpdate);

            }
            sendPriceDropNotification(productUpdate);
            Product p = productList.get();
            p.setId(productUpdate.getId());
            p.setName(productUpdate.getName());
            p.setPrice(productUpdate.getPrice());
            p.setQuantity(productUpdate.getQuantity());
            p.setCategory(productUpdate.getCategory());

            productRepository.save(p);
            return "Done";
        }

    }
    @Override
    public String deleteProductList(ProductVo productVo) {
        ObjectMapper objectMapper = new ObjectMapper();

        productRepository.delete(objectMapper.convertValue(productVo, quinbay.Inventory.model.entity.Product.class));
        return "Done";


    }

    public boolean checkProduct(long id, int quantity){
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<quinbay.Inventory.model.entity.Product> productUpdate = productRepository.findById(id);
        Product p = productUpdate.get();
        if(productUpdate.get().getQuantity() >= quantity) {
            System.out.println(productUpdate.get().getQuantity() - quantity);
            p.setQuantity(productUpdate.get().getQuantity() - quantity);
            System.out.println(quantity+" "+productUpdate.get().getQuantity());

            productRepository.save(p);
            return true;
        }

        else return false;

    }
    @Override
    public void sendPriceDropNotification(Product prod) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send("com.quinbay.product.create", objectMapper.writeValueAsString(prod));
    }









}
