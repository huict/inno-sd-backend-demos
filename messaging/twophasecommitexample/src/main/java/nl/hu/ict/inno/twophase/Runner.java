package nl.hu.ict.inno.twophase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    MessageProducer producer;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Startup!");
        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1 * 1000; i++) {
//
//            try {
//                producer.sendMessage("Hello World");
//            } catch (Exception ex) {
////                ex.printStackTrace();
//            }
//        }
        Scanner s = new Scanner(System.in);
        while(true){
            try{
                producer.sendMessage("Hello World");
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                System.out.println("Nog een keer? (ja/nee)");
                String read = s.nextLine();
                if(read.trim().equals("nee")){
                    break;
                }
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("DONE: " + (end - start));
    }
}
