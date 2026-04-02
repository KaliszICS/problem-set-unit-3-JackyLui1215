/**
	* Problem Set Unit 3: Email Validator and Parser
	* Author: Jacky Lui
	* Date Created: March 27, 2026
	* Date Last Modified: April 2, 2026
	*/

import java.util.Scanner;
public class ProblemSet {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter two email addresses: ");
		String email = sc.nextLine();
        email = email.trim(); //Removes any leading or trailing spaces from the input
        if (!(email.contains(","))) { //Checks if the two emails are seperated with a comma
            System.out.println("Error. Input must contain a comma seperating the two emails.");
        }
        else if (email.indexOf(",") == email.lastIndexOf(",")) { //Determines if more than one comma exists
            String firstEmail = email.substring(0, (email.indexOf(","))).trim();
            String secondEmail = email.substring(email.indexOf(",") + 1).trim();
            System.out.println(validOrInvalid(firstEmail));
            System.out.println(validOrInvalid(secondEmail));
        }
		else {
            System.out.println("Formatting Error. Example format: john.doe@gmail.com, john.doe@gmail.com");
        }
		sc.close();
        
	}

	//Determines if Email is validOrInvalid (Follows rules and Exceptions)
	public static String validOrInvalid(String email) {

	 if(email.length() <= 1) { //Checks if the email length is above 1 character before starting(prevent error)
     	return (email + ": Invalid: Email is too short.");
    }

   	 if(!email.contains("@")) { //Checks for @ symbol
        return (email + ": Invalid: Email is missing an @ symbol.");
    }

    if(email.indexOf("@") != email.lastIndexOf("@")) { // Checks for multiple @ symbols
        return (email + ": Invalid: Email contains more than one @ symbol.");
    }

    if((email.startsWith(".") || (email.endsWith(".")))) { //Checks the location of the period
        return (email + ": Invalid: Email contains a period at the beginning or the end of the email.");
    }

    if(email.contains(" ")) { //Checks for spaces
        return (email + ": Invalid: Email contains spaces.");
    }

    if(local(email).length() < 1 || local(email).length() > 64) { //Checks the local length (between 1-64 characters)
        return (email + ": Invalid: Local part of the Email is not between 1-64 characters");
    }

    if(!(domain(email)).contains(".")) { //Checks if the domain contains a "."
        return (email + ": Invalid: Domain part of Email does not contain a period.");
    }

    if(email.contains("..")) { //Checks for consecutive periods
        return (email + ": Invalid: Email contains consecutive periods.");
    }

    if((email.length() - email.lastIndexOf(".") - 1) < 2 || (email.length() - email.lastIndexOf(".") - 1) > 6) { //Checks the domain length even if email has a subdomain (2-6 characters)
        return (email + ": Invalid: Domain extension of the email is not between 2-6 characters.");
    }

    if(email.startsWith("+") || (email.endsWith("+") || email.startsWith("_")) || email.endsWith("_")) { //Exception B
        return (email + ": Invalid: Email contains a + or _ at the ning of the Email.");
    }
	if(!containsOnlyLetters(domainExtension(email), 0)) { //Checks if Domain extension only contains letters
		return (email+ ": Invalid: Domain extension contains symbols or numbers.");
	}
    if((domain(email).contains("+")) || (domain(email)).contains("_")) { //Checks if there are + or _ in the domain part of the Email 
        return (email + ": Invalid: Email contains a + or _ in the domain part of the Email.");
    }
    if((email.contains("++") || (email.contains("__"))) || (email.contains("_+")) || (email.contains("+_"))) { //Checks if consecutive + or _ are present
        return (email + ": Invalid: Email contains consecutive + or _.");
    }

    if(domain(email).equalsIgnoreCase("gmail.com")) { //Exception C
        String gmail = (local(email)); //Begins to normalize gmail
        gmail = gmail.replace(".", "");
        gmail = gmail.replace("+", "");
        gmail = gmail.replace("_", "");
        String normalizeGmail = gmail.toLowerCase();
        gmail = (gmail + (domain(email))).toLowerCase(); //Normalizes gmail
        return (gmail + (": Valid (Gmail Normalized) | ") + ("Local: ") + (normalizeGmail) + " | " + ("Domain: ") + (domain(email)).toLowerCase()); //Displays local and domain of the email
    }

    return (email + (": Valid | ") + ("Local: ") + (local(email)) + " | " + ("Domain: ") + (domain(email))); //Displays local and domain of the email
}
    public static String domain(String email) { //seperates the domain of the email (Exception A, allows for subdomains.)
        return (email.substring((email.indexOf("@") + 1)));
    }

    public static String local(String email) { //seperates the local of the email
        return (email.substring(0, (email.indexOf("@"))));
    }
    public static String domainExtension(String email) {
        return (email.substring(email.lastIndexOf(".") + 1));
    }
    public static boolean containsOnlyLetters(String domainExtension, int value) { //Checks to see if domain extension contains only letters
        if (value == domainExtension.length()) {
            return true;
        }
        int character = domainExtension.charAt(value);  
        if (!((character >= 60 && character <= 90) || (character >= 97 && character <= 122))) { //Uses ASCII to determine if the character is only letters
            return false;
        }
        return containsOnlyLetters(domainExtension, value + 1); //Calls itself until it can return true or false
    }
}


