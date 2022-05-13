import java.util.*;

public class Task_2_2 {
    public static void main(String[] args) {

        UnluckyVassal1 unluckyVassal = new UnluckyVassal1();
        unluckyVassal.printReportForKing(pollResults);
    }

    private static List<String> pollResults = List.of(
            "служанка Аня", // xizmatkor
            "управляющий Семен Семеныч: крестьянин Федя, доярка Нюра", // boshqaruvchi
            "дворянин Кузькин: управляющий Семен Семеныч, жена Кузькина, экономка Лидия Федоровна", // zodagon
            "экономка Лидия Федоровна: дворник Гена, служанка Аня", //uy bekasi
            "доярка Нюра", // soguvchi
            "кот Василий: человеческая особь Катя", // mushuk
            "дворник Гена: посыльный Тошка", //ko'cha tozalovchi
            "киллер Гена", // qotil
            "зажиточный холоп: крестьянка Таня", //
            "секретарь короля: зажиточный холоп, шпион Т", // qirol kotibi
            "шпион Т: кучер Д", // josus
            "посыльный Тошка: кот Василий", //xabarchi
            "аристократ Клаус", // boyvacha
            "просветленный Антон"// xazrat qori

    );

}
class UnluckyVassal1 {
    public void printReportForKing(List<String> pollResults) {
        Map<String, List<String>> everyone = new HashMap<String, List<String>>();
        HashSet<String> indirects = new HashSet<String>();
        for (String result : pollResults) {
            String[] names = result.split(":\\s*|,\\s*");
            if (names.length > 1) {
                ArrayList<String> servants = new ArrayList<String>();
                for (int i = 1; i < names.length; ++i) {
                    servants.add(names[i]);
                    indirects.add(names[i]);
                }
                everyone.put(names[0], servants);
            }
            else
                everyone.put(names[0], null);
        }

        List<String> kingsServants = new ArrayList<String>();
        for (String name : everyone.keySet())
            if (!indirects.contains(name))
                kingsServants.add(name);

        everyone.put("король", kingsServants);
        printServants(everyone, "король", "");

    }

    public static void printServants(Map<String, List<String>> everyone, String name, String prefix)
    {
        System.out.println(prefix + name);
        List<String> servants = everyone.get(name);
        if (servants != null) {
            Collections.sort(servants);
            for(String servant : servants)
                printServants(everyone, servant, prefix + "    ");
        }
    }
}