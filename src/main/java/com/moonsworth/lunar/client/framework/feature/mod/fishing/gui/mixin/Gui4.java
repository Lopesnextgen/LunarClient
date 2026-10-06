package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class Gui4 {
   private final Map<String, Gui4.Data> field1;
   private final int field2;

   public Gui4(Map<String, Gui4.Data> var1, int var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Nullable
   public Gui4.Data method1(String var1) {
      return this.field1.get(var1);
   }

   public Map<String, Gui4.Data> method2() {
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

      public Data(String var1, @Nullable Integer var2, @Nullable String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
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
