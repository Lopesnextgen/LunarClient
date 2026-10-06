package com.moonsworth.lunar.ichor;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.service.IGlobalPropertyService;
import org.spongepowered.asm.service.IPropertyKey;

public class IGlobalPropertyServiceImpl implements IGlobalPropertyService {
   private final Map<String, Object> blackboard = new HashMap<>();

   public IPropertyKey resolveKey(String var1) {
      return new IGlobalPropertyServiceImpl.Data(var1);
   }

   public <T> T getProperty(IPropertyKey var1) {
      return (T)this.blackboard.get(var1.toString());
   }

   public void setProperty(IPropertyKey var1, Object var2) {
      this.blackboard.put(var1.toString(), var2);
   }

   public <T> T getProperty(IPropertyKey var1, T var2) {
      return (T)this.blackboard.getOrDefault(var1.toString(), var2);
   }

   public String getPropertyString(IPropertyKey var1, String var2) {
      return this.getProperty(var1, var2);
   }

   static class Data implements IPropertyKey {
      private final String field1;

      Data(String var1) {
         this.field1 = var1;
      }

      @Override
      public String toString() {
         return this.field1;
      }
   }
}
