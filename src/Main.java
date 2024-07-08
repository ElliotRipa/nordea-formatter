import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> file = FileReader.readFile("file.csv");

        ArrayList<String[]> rows = new ArrayList<>();

        String[] split;

        for(String line : file) {

            split = line.split(";");
            rows.add(split);

        }

        System.out.println(rows);
        ArrayList<String> result = convertFile(rows);
        for(String string : result) {
            System.out.println(string);
        }
        System.out.println(fixRow(rows.get(1)));
    }

    public static ArrayList<String> convertFile(ArrayList<String[]> rows) {

        String[] format = {"\uFEFFBokföringsdag", "Belopp", "Avsändare", "Mottagare", "Namn", "Ytterligare detaljer", "Meddelande", "Egna anteckningar", "Saldo", "Valuta"};

        /*if(rows.get(0).equals(format)) {
            System.out.println("WRONG!");
            return null;
        }*/

        ArrayList<String> result = new ArrayList<>();

        boolean first = true;
        for(String[] row : rows) {
            if(first) {
                result.add("Bokförd;Valutadatum;Text;Typ;Insättningar/uttag;Bokfört saldo");
                first = false;
            } else {
                result.add(fixRow(row));
            }

        }

        return result;
    }

    public static String fixRow(String[] badRow) {

        String bokford = badRow[0].replace('/', '-');
        String valutadatum = bokford;
        String text = badRow[5];
        String typ = "Annan";
        String insattningaruttag = badRow[1];
        String saldo = badRow[8];

        return bokford + ";" + valutadatum + ";" + text + ";" + typ + ";" + insattningaruttag + ";" + saldo;

    }



}