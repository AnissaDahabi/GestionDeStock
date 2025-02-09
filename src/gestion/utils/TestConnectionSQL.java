package gestion.utils;

//import com.almasb.fxgl.ui.UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class TestConnectionSQL {

    //private record Person(String name, int age) {}

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projetjava", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("show databases;");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Partie 1 UI

      /*  var listPerson = List.of(
                new Person("John Frick", 21),
                new Person("John Ragar", 20),
                new Person("John Doe", 18)
        );

        var ui = new UI();

        ui.showPerson(listPerson);



        // Partie 2 DB

        List<Person> personsFromDb = query.getPersons(); // appel à la base de donnée


        ui.showPerson(personsFromDb);
*/
    }
}
