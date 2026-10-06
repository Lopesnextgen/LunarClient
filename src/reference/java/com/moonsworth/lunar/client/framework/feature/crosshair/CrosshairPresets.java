package com.moonsworth.lunar.client.framework.feature.crosshair;

import java.util.Set;

public class CrosshairPresets {
   public static final String field1 = "LCCH-9-ECBAgPAfAgQIEAA";
   public static final String field2 = "LCCH-9-EKxJgvAfgiRrEAA";
   public static final String field3 = "LCCH-8-AAAAABA4bMY";
   public static final String field4 = "LCCH-5-7v/vAA";
   public static final String field5 = "LCCH-7-HFEwGRRxAA";
   public static final String field6 = "LCCH-5-P9b4AQ";
   public static final String field7 = "LCCH-7-HETyn0RwAA";
   public static final String field8 = "LCCH-7-HERwHERwAA";
   public static final String field9 = "LCCH-9-EKxJAnAdgCRrEAA";
   public static final String field10 = "LCCH-9-EKxJAnAcgCRrEAA";
   public static final String field11 = "LCCH-9-AADgIHKdCA4AAAA";
   public static final String field12 = "LCCH-7-47uP47uPAQ";
   public static final String field13 = "LCCH-7-47sNYLuPAQ";
   public static final String field14 = "LCCH-7-CI5tbOMgAA";
   public static final String field15 = "LCCH-13-eNpjYKifsTlAgss5UYOndZGHSKAIGzMDA8N/IABSDBoggsEeiDsYIAAswsgAISGKYMoYGFgZHEAUiGAEABP2DMc=#";
   public static final String[] field16 = new String[]{
      "LCCH-9-ECBAgPAfAgQIEAA",
      "LCCH-9-EKxJgvAfgiRrEAA",
      "LCCH-8-AAAAABA4bMY",
      "LCCH-5-7v/vAA",
      "LCCH-7-HFEwGRRxAA",
      "LCCH-5-P9b4AQ",
      "LCCH-7-HETyn0RwAA",
      "LCCH-7-HERwHERwAA",
      "LCCH-9-EKxJAnAdgCRrEAA",
      "LCCH-9-EKxJAnAcgCRrEAA",
      "LCCH-9-AADgIHKdCA4AAAA",
      "LCCH-7-47uP47uPAQ",
      "LCCH-7-47sNYLuPAQ",
      "LCCH-7-CI5tbOMgAA"
   };
   public static final Set<CrosshairPattern> field17 = Set.of(
      CrosshairPattern.method5("LCCH-5-vXx6AQ"),
      CrosshairPattern.method5("LCCH-7-+UTij0Q+AQ"),
      CrosshairPattern.method5("LCCH-5-l/7SAQ"),
      CrosshairPattern.method5("LCCH-7-TyTyn0jkAQ"),
      CrosshairPattern.method5("LCCH-7-Ar3Ch3qBAA"),
      CrosshairPattern.method5("LCCH-9-9u3b8O8ftm/fAAA")
   );

   public CrosshairPresets() {
   }

   public static boolean method1(CrosshairPattern crosshair20) {
      label24:
      for (CrosshairPattern crosshair22 : field17) {
         int index3 = 3;
         boolean[] items4 = crosshair20.method13();
         boolean[] items5 = crosshair22.method13();

         for (int index6 = 0; index6 < items4.length; index6++) {
            if (items5[index6] != crosshair20.method13()[index6] && index3-- <= 0) {
               continue label24;
            }
         }

         return true;
      }

      return false;
   }
}
