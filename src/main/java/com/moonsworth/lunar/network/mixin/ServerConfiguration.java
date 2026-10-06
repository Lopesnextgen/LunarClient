package com.moonsworth.lunar.network.mixin;

import java.util.Map;
import java.util.Map.Entry;

public class ServerConfiguration {
   public String field1;
   public String description;
   public Map<String, ServerVariable> field2;

   public ServerConfiguration(String text, String text2, Map<String, ServerVariable> map) {
      this.field1 = text;
      this.description = text2;
      this.field2 = map;
   }

   public String method1(Map<String, String> map) {
      String text2 = this.field1;

      for (Entry entry4 : this.field2.entrySet()) {
         String text5 = (String)entry4.getKey();
         ServerVariable mixinhelper66 = (ServerVariable)entry4.getValue();
         String text7 = mixinhelper66.field1;
         if (map != null && map.containsKey(text5)) {
            text7 = (String)map.get(text5);
            if (mixinhelper66.field2.size() > 0 && !mixinhelper66.field2.contains(text7)) {
               throw new IllegalArgumentException("The variable " + text5 + " in the server URL has invalid value " + text7 + ".");
            }
         }

         text2 = text2.replace("{" + text5 + "}", text7);
      }

      return text2;
   }

   public String method2() {
      return this.method1(null);
   }
}
