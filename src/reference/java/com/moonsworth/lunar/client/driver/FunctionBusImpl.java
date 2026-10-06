package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.webosr.javascript.FunctionBus;
import java.util.Objects;

public class FunctionBusImplLegacy extends FunctionBus {
   public FunctionBusImplLegacy() {
   }

   protected Object stringToObject(String text, Class<?> clazz2) {
      if (!Objects.equals(text, "null") && !Objects.equals(text, "undefined")) {
         try {
            return super.stringToObject(text, clazz2);
         } catch (Exception exception4) {
            return LunarConstants.field22.fromJson(text, clazz2);
         }
      } else {
         return null;
      }
   }

   protected String objectToString(Object object, Class<?> clazz2) {
      try {
         return super.objectToString(object, clazz2);
      } catch (Exception exception4) {
         return LunarConstants.field22.toJson(object);
      }
   }
}
