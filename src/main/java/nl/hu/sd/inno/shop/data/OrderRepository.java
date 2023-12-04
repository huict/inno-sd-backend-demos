package nl.hu.sd.inno.shop.data;

import nl.hu.sd.inno.shop.domain.Order;
import nl.hu.sd.inno.shop.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByPerson(Person p);
}
