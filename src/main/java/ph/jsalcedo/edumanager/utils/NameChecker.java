package ph.jsalcedo.edumanager.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1> Name Checker Helper Class</h1>
 * <b>Note:</b> * <p>Helper class verify/validate if a name is valid or is a duplicate</p>
 * <h2>@created 16/02/2023 - 7:06 pm</h2>
 * <h2>@author Joshua Salcedo</h2>
 */
public class NameChecker {


    private NameChecker(){}


   /**
     * <h1>Checks If Name is Valid</h1>
     * <p>Since the application needs to check from time to time whether or not a name is valid</p>
     * <p>Best we use a static method in order to have the same standard thru-out the whole application</p>
     * <b>Note:</b> The condition is not Dynamic yet. We should create another method that accepts a regex pattern
     *
     * @return
     * <dl>
     *     <dt>True</dt>
     *     <dd>Must start with a letter</dd>
     *     <dd>Min of 2 characters</dd>
     *     <dd>Max of 100 characters</dd>
     *     <dd>The only special characters it accepts are '()-'</dd>
     *
     * </dl>
     * @author Joshua Salcedo
     * @created 16/02/2023 - 7:09 pm
     * @// TODO 16/02/2023  - add a dynamic method that allows and accept a valid name
     */
    public static boolean isNameInvalid(String name){
        String regex = "^[A-Za-z\u00C0-\u017F\u00E0-\u00FF][A-Za-z0-9& .'()\\-\u00C0-\u017F\u00E0-\u00FF\u00BF-\u00FF]{1,98}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        return !matcher.find();
    }


    /**
     * <h1>Duplicate Name Checker</h1>
     * <p>This Utility method ensures that the program has the same standards for what is considered a duplicate String!.</p>
     * <p>this is a helper method to compare the Strings</p>
     * <b>@returns</b>
     *<dl>
     *     <dt>True</dt>
     *     <dd>'  xx' and 'XX'</dd>
     *     <dd>'x_x' and 'xx'</dd>
     *     <dd>'xx' and 'x x'</dd>
     *     <dt>False</dt>
     *     <dd>'xy' and 'xx'</dd>
     *     <dd>'x school' and `x university`</dd>
     *</dl>
     *
     * @author Joshua Salcedo
     * @created 16/02/2023 - 3:11 pm
     */
    public static boolean isNameDuplicate(String var1, String var2) {
        var1 = var1.replace("[^a-zA-Z0-9]", "").trim();
        var2 = var2.replace("[^a-zA-Z0-9]", "").trim();
        return var1.equalsIgnoreCase(var2);
    }
}
