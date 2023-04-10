package nl.tomkemper.bep3.hellomongo.fabriek3;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Fabriek3Main {

    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb://localhost");
        MongoOperations mongoOps = new MongoTemplate(client, "fabriek3");

        mongoOps.dropCollection("klant");
        mongoOps.dropCollection("artikel");
        mongoOps.dropCollection("bestelling");

        Klant smit = new Klant(121, "Smit");
        Klant staal = new Klant(122, "Staal");

        Artikel postits = new Artikel(121, "post-its", 2.75);
        Artikel pennen = new Artikel(122, "high light pen", 1.50);
        Artikel diskettes = new Artikel(123, "diskettes 10pk", 3.10);
        Artikel nietmachine = new Artikel(124, "nietmachine", 4.75);

        for (Klant k : new Klant[]{smit, staal}) {
            mongoOps.save(k);
        }

        for(Artikel a: new Artikel[]{ postits, pennen, diskettes, nietmachine}){
            mongoOps.save(a);
        }

        mongoOps.save(new Bestelling(smit)
                .add(10, postits)
                .add(3, diskettes)
                .add(nietmachine));
    }
}
