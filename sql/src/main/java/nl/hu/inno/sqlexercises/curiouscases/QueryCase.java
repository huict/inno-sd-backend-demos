package nl.hu.inno.sqlexercises.curiouscases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class QueryCase implements CuriousCase {

    @Autowired
    private SeparateQueries queries;

    @Override
    public void run() {
        var lefties = queries.findLefties();
        var tallFolk = queries.findTallFolks();

        var tallLefties1 = lefties.stream().filter(tallFolk::contains).toList();
        System.out.println(tallLefties1.size());

        var tallLefties2 = queries.findTallLefties();
        System.out.println(tallLefties2.size());
    }
}
