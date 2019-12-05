package inz.repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import inz.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

import inz.repository.OrderRepositoryCustom;

public class OrderRepositoryCustomImplementation implements OrderRepositoryCustom {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Order> getAllFavourites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFavourite(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getUserFavourites(int userid) {
		Query query = entityManager.createNativeQuery("SELECT * FROM inzynierka.favourite WHERE userid = ?", Order.class);
		query.setParameter(1, userid);
		
		return query.getResultList();
	}
	
	/*
	@Override
	List<Order> getAllFavourites() {
		Query query = entityManager.createNativeQuery("SELECT * FROM inzynierka.favourite", Order.class);
	}
	*/
}
