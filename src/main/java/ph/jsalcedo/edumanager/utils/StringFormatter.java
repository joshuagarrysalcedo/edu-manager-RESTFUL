package ph.jsalcedo.edumanager.utils;

public class StringFormatter {

    public static String screamingSnakeCaseToCapitalize(String input){
        StringBuilder titleCase = new StringBuilder();
        String[] inputs = input.toLowerCase().split("_");

        for(String s : inputs){
            titleCase.append(s.substring(0, 1).toUpperCase()).append(s.substring(1))
                    .append(" ");
        }
        titleCase.trimToSize();
        return titleCase.toString();
    }
}
