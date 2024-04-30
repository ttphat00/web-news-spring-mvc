package com.example.util;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	public static String convertToSlug(String input) {
        // Loai bo dau tieng Viet, dau cau va ky tu dac biet
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9\\s]", "");
        
        //Thay the khoang trang thanh dau gach noi
        return normalizedString.toLowerCase().replaceAll("\\s+", "-");
    }
	
	public static String formatDateTime(Date inputDateTime) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        
        return outputFormat.format(inputDateTime);
    }
}
