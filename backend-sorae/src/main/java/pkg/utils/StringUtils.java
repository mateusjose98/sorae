package pkg.utils;

import java.text.Normalizer;

public class StringUtils {
	
	public static String removerAcentos(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	
}
