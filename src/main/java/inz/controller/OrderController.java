package inz.controller;

import inz.model.Order;
import inz.model.OrderProducts;
import inz.repository.OrderProductsRepository;
import inz.repository.OrderRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/favourite")
public class OrderController {

    @Autowired
    private OrderRepository favouriteRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;
    
    @Autowired
    private OrderProductsRepository fpRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addFavourite(@RequestBody Order order) {
        JSONObject response = new JSONObject();
        try {
            order.setFavouriteId(new Integer((int)favouriteRepository.count() + 1));
            favouriteRepository.saveAndFlush(order);

            response.put("status", "ok");

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        } catch(Exception e) {
            response.put("status","failure");
            response.put("msg", e.getMessage());

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        }
    }

    @PostMapping("/add2")
    public ResponseEntity<?> addFavouriteProduct(@RequestBody String body) {
        JSONObject response = new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(body);

            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setFavouriteId(new Integer(json.get("favouriteid").toString()));
            orderProducts.setFavouriteId(new Integer(json.get("productid").toString()));
            orderProducts.setId(new Integer((int) orderProductsRepository.count() + 1));

            orderProductsRepository.saveAndFlush(orderProducts);

            response.put("status", "ok");

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        } catch(Exception e) {
            response.put("status","failure");
            response.put("msg", e.getMessage());

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        }
    }
    
    @GetMapping("/favourites/{userid}")
    public ResponseEntity<?> getUserFavourites(@PathVariable("userid") int userid) {
    	JSONObject response = new JSONObject();
        try {
            response.put("status", "ok");
            response.put("data", favouriteRepository.getUserFavourites(userid));

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        } catch(Exception e) {
            response.put("status","failure");
            response.put("msg", e.getMessage());

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        }
    }
    
    @DeleteMapping("/delete/{favouriteid}/{productid}")
    public ResponseEntity<?> deleteProductFromFavourite(@PathVariable("favouriteid") int favouriteid, @PathVariable("productid") int productid) {
    	JSONObject response = new JSONObject();
        try {
        	orderProductsRepository.deleteProduct(productid, favouriteid);
        	
            response.put("status", "ok");

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        } catch(Exception e) {
            response.put("status","failure");
            response.put("msg", e.getMessage());

            return new ResponseEntity<String>(response.toJSONString(), HttpStatus.OK);
        }
    }
}
