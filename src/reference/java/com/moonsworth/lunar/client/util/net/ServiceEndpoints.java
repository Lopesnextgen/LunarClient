package com.moonsworth.lunar.client.util.net;

import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.network.AnalyticsBatcher.Type;
import lombok.Generated;

public class ServiceEndpoints {
   public static final String field1 = "https://textures.lunarclientcdn.com/file/";
   public static final String field2 = "https://store.lunarclient.com/";
   public static final String field3;
   private static final String field4;
   private static final String field5;
   private static final String field6;
   private static final String field7;
   private static final String field8;
   private static final String field9;
   private static final boolean field10;
   private static final Type field11;

   private static String method1(String var0, String var1) {
      if (LunarBuildData.field4) {
         return var1;
      }

      String var2 = System.getenv("serviceOverride" + var0);
      return var2 != null ? var2 : System.getProperty("serviceOverride" + var0, var1);
   }

   @Generated
   public static String method2() {
      return field4;
   }

   @Generated
   public static String method3() {
      return field5;
   }

   @Generated
   public static String method4() {
      return field6;
   }

   @Generated
   public static String method5() {
      return field7;
   }

   @Generated
   public static String method6() {
      return field8;
   }

   @Generated
   public static String method7() {
      return field9;
   }

   @Generated
   public static boolean method8() {
      return field10;
   }

   @Generated
   public static Type method9() {
      return field11;
   }

   static {
      String var0 = System.getenv("devServices");
      var0 = var0 == null ? "no" : var0;
      if ((LunarBuildData.field4 || !var0.equals("yes")) && !System.getProperty("devServices", "no").equals("yes")) {
         String var3 = "lunarclientprod.com";
         field3 = "mcstats.com";
         field4 = method1("Styngr", "https://api.styngr.com/api");
         field5 = method1("Skins", "https://skins." + field3);
         field6 = method1("ThirdPartyCache", "https://thirdpartycache." + var3);
         field7 = method1("Authenticator", "wss://authenticator." + var3);
         field8 = method1("AssetServer", "wss://websocket." + var3);
         field9 = method1("Api", "https://api." + var3);
         field11 = Type.PRODUCTION;
         field10 = false;
      } else {
         String var1 = "lunarclientdev.com";
         field3 = "mcstats.cloud";
         field4 = method1("Styngr", "https://stg.api.styngr.com/api");
         field5 = method1("Skins", "https://skins." + field3);
         field6 = method1("ThirdPartyCache", "https://thirdpartycache." + var1);
         field7 = method1("Authenticator", "wss://authenticator." + var1);
         field8 = method1("AssetServer", "wss://websocket." + var1);
         field9 = method1("Api", "https://api." + var1);
         field11 = Type.PRODUCTION;
         field10 = true;
      }
   }
}
