package com.gang.tkunion.utils;

import java.util.Locale;

public class UrlUtils {

    public static String createCategoryContentUrl(int materialId, int page){
        return String.format(Locale.CHINA, "discovery/%d/%d", materialId, page);
    }

}
