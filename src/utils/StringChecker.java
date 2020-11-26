package utils;

public abstract class StringChecker {
	public static boolean containsWhiteSpace(final String str) {
	    if(str != null){
	        for(int i = 0; i < str.length(); i++){
	            if(Character.isWhitespace(str.charAt(i))){
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public static boolean isLong(final String str) {
		try {  
		    Long.parseLong(str);
		    return true;
		} 
		catch(NumberFormatException e){  
		    return false;  
		}  
	}
	
	public static boolean isInt(final String str) {
		try {  
			Integer.parseInt(str);
		    return true;
		} 
		catch(NumberFormatException e){  
		    return false;  
		}  
	}
	
	public static int calculatePasswordStrength(String password){
        
        //total score of password
        int iPasswordScore = 0;
        
        //if it has less than 8 char, it's not accpetable
        if( password.length() < 8 )
            return 0;
        
        //if it has more than 10 char, add 2 to total score. Unless, add 1
        else if( password.length() >= 10 )
            iPasswordScore += 2;
        else 
            iPasswordScore += 1;
        
        //if it contains one digit, add 2 to total score
        if( password.matches("(?=.*[0-9]).*") )
            iPasswordScore += 2;
        
        //if it contains one lower case letter, add 2 to total score
        if( password.matches("(?=.*[a-z]).*") )
            iPasswordScore += 2;
        
        //if it contains one upper case letter, add 2 to total score
        if( password.matches("(?=.*[A-Z]).*") )
            iPasswordScore += 2;    
        
        //if it contains one special character, add 2 to total score
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
            iPasswordScore += 2;
        
        return iPasswordScore;
        
    }
}
