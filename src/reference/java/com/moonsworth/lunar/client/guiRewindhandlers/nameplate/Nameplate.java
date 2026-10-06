package com.moonsworth.lunar.client.guiRewindhandlers.nameplate;

import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.event.ClientEventBus;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;

public class Nameplate {
   private final List<Nameplate.Data9<? extends Highlight>> field1 = new ArrayList<>();

   public <T extends Highlight> void method1(Consumer<T> var1, int var2, Class<T> var3) {
      this.field1.add(new Nameplate.Data9<>(var1, var2, var3));
   }

   public void clear() {
      this.field1.clear();
   }

   public void method2() {
      for (Nameplate.Data9 var2 : this.field1) {
         var2.register();
      }
   }

   public void method3() {
      for (Nameplate.Data9 var2 : this.field1) {
         var2.method1();
      }
   }

   @Generated
   public List<Nameplate.Data9<? extends Highlight>> method4() {
      return this.field1;
   }

   private class Data9<T extends Highlight> {
      private final Consumer<T> field1;
      private final int field2;
      private final Class<T> field3;

      private Data9(Consumer<T> var1, int var2, Class<T> var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public void register() {
         ClientEventBus.method29().method4(this.field3, this.field1, this.field2);
      }

      public void method1() {
         ClientEventBus.method29().method6(this.field3, this.field1);
      }

      public Consumer<T> method2() {
         return this.field1;
      }

      public int priority() {
         return this.field2;
      }

      public Class<T> method3() {
         return this.field3;
      }
   }
}
