package com.moonsworth.lunar.client.framework.feature.crosshair;

import java.util.Set;

public class Crosshair3 {
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
   public static final Set<Crosshair2> field17 = Set.of(
      Crosshair2.method5("LCCH-5-vXx6AQ"),
      Crosshair2.method5("LCCH-7-+UTij0Q+AQ"),
      Crosshair2.method5("LCCH-5-l/7SAQ"),
      Crosshair2.method5("LCCH-7-TyTyn0jkAQ"),
      Crosshair2.method5("LCCH-7-Ar3Ch3qBAA"),
      Crosshair2.method5("LCCH-9-9u3b8O8ftm/fAAA")
   );

   public static boolean method1(Crosshair2 crosshair2) {
      label24:
      for (Crosshair2 var2 : field17) {
         int var3 = 3;
         boolean[] var4 = crosshair2.method13();
         boolean[] var5 = var2.method13();

         for (int var6 = 0; var6 < var4.length; var6++) {
            if (var5[var6] != crosshair2.method13()[var6] && var3-- <= 0) {
               continue label24;
            }
         }

         return true;
      }

      return false;
   }
}
