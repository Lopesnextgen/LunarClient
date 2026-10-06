package com.moonsworth.lunar.network.mixin;

import java.util.Map;
import java.util.Map.Entry;

public class MixinHelper5 {
   public String field1;
   public String description;
   public Map<String, MixinHelper6> field2;

   public MixinHelper5(String var1, String var2, Map<String, MixinHelper6> map) {
      this.field1 = var1;
      this.description = var2;
      this.field2 = map;
   }

   public String method1(Map<String, String> var1) {
      String var2 = this.field1;

      for (Entry var4 : this.field2.entrySet()) {
         String var5 = (String)var4.getKey();
         MixinHelper6 var6 = (MixinHelper6)var4.getValue();
         String var7 = var6.field1;
         if (var1 != null && var1.containsKey(var5)) {
            var7 = (String)var1.get(var5);
            if (var6.field2.size() > 0 && !var6.field2.contains(var7)) {
               throw new IllegalArgumentException("The variable " + var5 + " in the server URL has invalid value " + var7 + ".");
            }
         }

         var2 = var2.replace("{" + var5 + "}", var7);
      }

      return var2;
   }

   public String method2() {
      return this.method1(null);
   }
}
