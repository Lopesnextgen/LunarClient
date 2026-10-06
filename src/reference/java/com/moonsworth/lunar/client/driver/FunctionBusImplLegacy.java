package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.webosr.javascript.FunctionBus;
import java.util.Objects;

public class FunctionBusImplLegacy extends FunctionBus {
   protected Object stringToObject(String var1, Class<?> var2) {
      if (!Objects.equals(var1, "null") && !Objects.equals(var1, "undefined")) {
         try {
            return super.stringToObject(var1, var2);
         } catch (Exception var4) {
            return ThreadModuleDump48.field22.fromJson(var1, var2);
         }
      } else {
         return null;
      }
   }

   protected String objectToString(Object var1, Class<?> var2) {
      try {
         return super.objectToString(var1, var2);
      } catch (Exception var4) {
         return ThreadModuleDump48.field22.toJson(var1);
      }
   }
}
