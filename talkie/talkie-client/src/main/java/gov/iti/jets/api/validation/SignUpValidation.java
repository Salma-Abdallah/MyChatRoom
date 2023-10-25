package gov.iti.jets.api.validation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpValidation {
    public SignUpValidation(){

    }

    public static boolean userNameValidate(String name) {
        if (name == null)
            return false;

        String regex = "^[A-Za-z0-9]{3,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


    public static boolean phoneNumberValidate(String phoneNo) {
        if(phoneNo == null)
            return false;

        String regex = "^(01)(0|1|2|5)[0-9]{8}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNo);

        return matcher.matches();
    }


    public static boolean passwordValidate(String password) {
        if(password == null)
            return false;

        String regex = "(.+){4}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((password));

        return matcher.matches();
    }


    public static boolean confirmPasswordValidate(String password , String confirmPassword) {
        if(confirmPassword == null || password==null)
            return false;
        return password.equals(confirmPassword);
    }


    public static boolean emailValidate(String email) {
        if(email == null)
            return false;

        String regex = "^[a-zA-Z0-9.!#$%&'\\+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+){1,4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }


    public static boolean countryValidation(String country) {
        return country!=null;
    }


    public static boolean birthdateValidation(LocalDate birthdate) {
        if(birthdate == null)
            return false;
        if(birthdate.compareTo(LocalDate.now())>-1) return false;
        return true;
    }

    public static boolean genderValidate(String gender ) {

        return gender != null;
    }
}
