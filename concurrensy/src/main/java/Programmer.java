import java.util.ArrayList;
import java.util.List;

class Prog {
    List<String> languages = new ArrayList<>();

    Prog(Agency agency) {
        languages.add("Java");
    }
}

class SeniorP extends Prog {
    SeniorP(Agency a) {
        super(a);
        languages.add("Angular");

    }
}

class Agency {
    public void register(Prog p) {
        System.out.println("Prog registered. Languages: " + p.languages);
    }
}
public class Programmer {
    public static void main(String[] args) {
        Agency agency = new Agency();
        //Prog p = new Prog(agency);
        Prog p = new SeniorP(agency);
    }
}
