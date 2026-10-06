package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class VanillaItemRegistry {
   private final Map<String, VanillaItemRegistry.Data> field1;
   private final int field2;

   public VanillaItemRegistry(Map<String, VanillaItemRegistry.Data> map, int number2) {
      this.field1 = map;
      this.field2 = number2;
   }

   @Nullable
   public VanillaItemRegistry.Data method1(String text1) {
      return this.field1.get(text1);
   }

   public Map<String, VanillaItemRegistry.Data> method2() {
      return this.field1;
   }

   public int method3() {
      return this.field2;
   }

   public class Data {
      private final String field1;
      @Nullable
      private final Integer field2;
      @Nullable
      private final String field3;

      public Data(String text1, @Nullable Integer number2, @Nullable String text3) {
         this.field1 = text1;
         this.field2 = number2;
         this.field3 = text3;
      }

      public String item() {
         return this.field1;
      }

      @Nullable
      public Integer method1() {
         return this.field2;
      }

      @Nullable
      public String method2() {
         return this.field3;
      }
   }
}
