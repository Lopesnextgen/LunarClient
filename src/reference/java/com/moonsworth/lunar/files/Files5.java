package com.moonsworth.lunar.files;

import java.util.Collection;

public class Files5 {
   private final String field1;
   private final String field2;
   public static final String field3 = "mcVer";
   public static final String field4 = "mcId";
   public static final String field5 = "mcpVer";
   public static final String field6 = "parchmentVer";
   public static final String field7 = "parchmentMcVer";
   public static final String field8 = "computeFrames";

   public Files5(String var1, String text) {
      this.field1 = var1;
      this.field2 = text;
   }

   public static Files5 method1(com.moonsworth.lunar.files.mixin.Files3 files3) {
      return new Files5("mcVer", files3.getId());
   }

   public String key() {
      return this.field1;
   }

   public String value() {
      return this.field2;
   }

   public interface Extension {
      Collection<Files5> method1();

      default String method2(String var1) {
         if (var1 != null && !var1.isEmpty()) {
            for (Files5 var3 : this.method1()) {
               String var4 = var3.value();
               if (var4 == null) {
                  var4 = "null";
               }

               var1 = var1.replace("${" + var3.key() + "}", var4);
            }

            return var1;
         } else {
            return var1;
         }
      }
   }
}
