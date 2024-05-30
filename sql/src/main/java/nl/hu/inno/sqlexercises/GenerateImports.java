package nl.hu.inno.sqlexercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Helaas, dit is nog niet helemaal af. Dit importeert de points als CSV, maar vervolgens is het matchen tussen de repositories
 * niet triviaal:
 *  - Ids zijn anders
 *  - Toernooien hebben andere aantallen wedstrijden (??!)
 */
public class GenerateImports {
    private static final String templateMatch = "copy import_matches\n" +
            "    from program 'cut -d '','' -f 1,5,6,17,18 /raw/tennis_slam_pointbypoint/%s'\n" +
            "    delimiter ','\n" +
            "    csv header;\n";

    private static final String templateDoubleMatch = "copy import_matches_doubles\n" +
            "    from program 'cut -d '','' -f 1,5,6,17,18 /raw/tennis_slam_pointbypoint/%s'\n" +
            "    delimiter ','\n" +
            "    csv header;\n";


    private static final String templatePoints ="copy import_matches_points\n" +
            "    from program 'cut -d '','' -f 1,2,9,10 /raw/tennis_slam_pointbypoint/%s'\n" +
            "    delimiter ','\n" +
            "    csv header;";

    public static void main(String[] args) throws IOException {
        //Dit is een beetje een hacky hackmans oplossing om een import-sql te genereren voor matches/points import

        Stream<Path> paths = Files.list(Path.of("./raw/tennis_slam_pointbypoint")).sorted();
        paths.forEach(p -> {
            Path filename = p.getFileName().getName(0);

            if(filename.toString().endsWith(".csv")){
                if(filename.toString().contains("points")){
                    System.out.println(String.format(templatePoints, filename));
                }else{
                    if(filename.toString().contains("double") || filename.toString().contains("mixe")) {
                        System.out.println(String.format(templateDoubleMatch, filename));
                    }else{
                        System.out.println(String.format(templateMatch, filename));
                    }

                }
            }

        });
    }
}
