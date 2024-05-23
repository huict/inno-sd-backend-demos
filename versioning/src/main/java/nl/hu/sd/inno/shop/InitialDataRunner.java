package nl.hu.sd.inno.shop;

import jakarta.transaction.Transactional;
import nl.hu.sd.inno.shop.data.OrderRepository;
import nl.hu.sd.inno.shop.data.PersonRepository;
import nl.hu.sd.inno.shop.data.ProductRepository;
import nl.hu.sd.inno.shop.domain.Order;
import nl.hu.sd.inno.shop.domain.Person;
import nl.hu.sd.inno.shop.domain.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@Profile("initial")
public class InitialDataRunner implements CommandLineRunner {

    private final ProductRepository products;
    private final PersonRepository persons;

    private final OrderRepository orders;

    public InitialDataRunner(ProductRepository products, PersonRepository persons, OrderRepository orders) {
        this.products = products;
        this.persons = persons;
        this.orders = orders;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //https://www.bol.com/nl/nl/p/sony-mdr-zx110-on-ear-koptelefoon-zwart/9200000032872507
        Product koptelefoon = new Product("Koptelefoon", 14.19);
        koptelefoon.deliver(50);

        //https://thegoodroll.com/en/product/621/the-dutch-choice, bronvermelding is belangrijk ;)
        Product wcPapier = new Product("WC papier (24 rollen)", 22.49);
        wcPapier.deliver(10);

        //https://bbqgreeneggstore.nl/product/big-green-egg-medium-standaard/, waaaat?
        Product bbq = new Product("Groene BBQ", 1304);
        bbq.deliver(3);

        Person tom = new Person("tom.kemper@hu.nl", "Tom", "Kemper");
        Person mirko = new Person("mirko.pelgrom@hu.nl","Mirko", "Pelgrom");
        Person hugo = new Person("hugo.helder@hu.nl", "Hugo", "Helder");


        products.saveAll(List.of(koptelefoon, wcPapier, bbq));
        persons.saveAll(List.of(tom, mirko, hugo));

        Order mirkosBbq = new Order(mirko);
        mirkosBbq.addProduct(bbq, 1);
        mirkosBbq.addProduct(wcPapier, 1);
        mirkosBbq.process();

        Order tomsKoptelefoon = new Order(tom);
        tomsKoptelefoon.addProduct(koptelefoon, 2);
        tomsKoptelefoon.addProduct(wcPapier, 3); //Corona-goud, weet je?
        tomsKoptelefoon.process();

        Order tomsBbq = new Order(tom);
        tomsBbq.addProduct(bbq, 1);
        tomsBbq.process();

        orders.saveAll(List.of(mirkosBbq, tomsKoptelefoon, tomsBbq));

    }
}
