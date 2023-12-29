import java.util.*;

public class Main {
    static class Handlekurv {
        public List<Vare> vareList = new ArrayList<>();

        public String kunde;
        public Handlekurv(String kunde) {
            this.kunde = kunde;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(kunde).append("\n");
            for (Vare vare : vareList) {
                sb.append(vare).append("\n");
            }
            return sb.toString();
        }
    }

    record Vare(Identifyer id, int pris, String navn, boolean ubrukelig) {
    }

    record Identifyer(int a, int b) {
    }
    static List<Identifyer> possibleIdentifyers = new ArrayList<>(
            List.of(new Identifyer(1, 1),
                    new Identifyer(1, 2),
                    new Identifyer(1, 3),
                    new Identifyer(1, 4),
                    new Identifyer(2, 1),
                    new Identifyer(2, 2))
    );

    public static void main(String[] args) {

        var liste = createHandlenett();
        for (Handlekurv handlekurv : liste) {
            System.out.println(handlekurv+"\n");
        }

        List<List<Vare>> rader = new ArrayList<>();
        for (Handlekurv handlekurv : liste) {

            Map<Identifyer, Vare> map = new HashMap<>();
            for (Vare vare : handlekurv.vareList) {
                map.put(vare.id, vare);
            }

            List<Vare> kolonner = new ArrayList<>();
            for (Identifyer possibleIdentifyer : possibleIdentifyers) {
                if (map.containsKey(possibleIdentifyer)) {
                    kolonner.add(map.get(possibleIdentifyer));
                } else {
                    kolonner.add(new Vare(new Identifyer(0, 0), 0, "", false));
                }
            }
            rader.add(kolonner);
        }



        // Print of result
        for (List<Vare> vares : rader) {
            for (Vare vare : vares) {
                System.out.print(vare.pris+" | ");
            }
            System.out.println();
        }
    }

    static List<Handlekurv> createHandlenett(){
        String[] kunder = {
                "Henrik",
                "Krister",
                "Olav",
                "Adrian"
        };

        List<Handlekurv> handlekurvs = new ArrayList<>();
        for (String kunde : kunder) {
            Random r = new Random();
            int rand = r.nextInt(5) + 1;
            Handlekurv hk = new Handlekurv(kunde);
            for (int i = 0; i < rand; i++) {
                hk.vareList.add(
                        new Vare(possibleIdentifyers.get(i),
                                r.nextInt(1000),
                                "VareNR" + r.nextInt(10_000),
                                r.nextBoolean()));
            }
            handlekurvs.add(hk);
        }
        return handlekurvs;
    }
}
