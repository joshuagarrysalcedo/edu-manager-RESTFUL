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
    public static boolean isNameValid(String name){
        String regex = "^[A-Za-z\\u00C0-\\u017F\\u00E0-\\u00FF][A-Za-z0-9&\\s.'()\\-\\u00C0-\\u017F\\u00E0-\\u00FF\\u00BF-\\u00FF]{2,98}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }
}
