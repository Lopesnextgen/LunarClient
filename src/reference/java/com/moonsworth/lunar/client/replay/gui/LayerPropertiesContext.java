package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class LayerPropertiesContext {
   private Map<String, PropertyGroup> field1 = new HashMap<>();
   private Map<String, PropertyGroup> field2 = new HashMap<>();

   public void method1(PropertyGroup propertyGroup) {
      this.field2.put(propertyGroup.type(), propertyGroup);
   }

   public void method2() {
      this.field1 = this.field2;
      this.field2 = new HashMap<>();
   }

   public void cleanup() {
      for (PropertyGroup fishing2iterator2 : this.field1.values()) {
         if (!this.field2.containsKey(fishing2iterator2.type())) {
            fishing2iterator2.method7();
         }
      }
   }

   @Generated
   public LayerPropertiesContext() {
   }

   @Generated
   public Map<String, PropertyGroup> method3() {
      return this.field1;
   }

   @Generated
   public Map<String, PropertyGroup> method4() {
      return this.field2;
   }

   @Generated
   public void method5(Map<String, PropertyGroup> map1) {
      this.field1 = map1;
   }

   @Generated
   public void method6(Map<String, PropertyGroup> map1) {
      this.field2 = map1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof LayerPropertiesContext nameplate2)) {
         return false;
      } else if (!nameplate2.canEqual(this)) {
         return false;
      } else {
         Map map3 = this.method3();
         Map map4 = nameplate2.method3();
         if (map3 == null ? map4 == null : map3.equals(map4)) {
            Map map5 = this.method4();
            Map map6 = nameplate2.method4();
            return map5 == null ? map6 == null : map5.equals(map6);
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof LayerPropertiesContext;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      Map map3 = this.method3();
      number2 = number2 * 59 + (map3 == null ? 43 : map3.hashCode());
      Map map4 = this.method4();
      return number2 * 59 + (map4 == null ? 43 : map4.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "LayerPropertiesContext(previouslyAppliedModProperties=" + this.method3() + ", appliedModProperties=" + this.method4() + ")";
   }
}
