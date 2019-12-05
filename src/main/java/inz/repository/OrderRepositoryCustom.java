package inz.repository;

import java.util.List;

import inz.model.Order;

public interface OrderRepositoryCustom {

	List<Order> getAllFavourites();
    void addFavourite(Order order);
    List<Order> getUserFavourites(int userid);
}
