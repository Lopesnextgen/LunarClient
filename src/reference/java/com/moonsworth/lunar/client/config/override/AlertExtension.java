package com.moonsworth.lunar.client.config.override;

import com.moonsworth.lunar.client.config.option.trait.TraitReader;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.alert.Alert;

public interface AlertExtension<Child extends TraitReader> extends Alert<Child, Child> {
   <HOLDER extends AlertExtension<Child>> TraitType<HOLDER> method1();

   default void method2(Consumer<Child> var1) {
      for (TraitReader var3 : this.getChildren()) {
         var1.accept(var3);
         AlertExtension var4 = var3.method1(this.method1());
         if (var4 != null) {
            var4.method2(var1);
         }
      }
   }

   default void method3(Predicate<Child> var1) {
      for (TraitReader var3 : this.getChildren()) {
         if (var1.test(var3)) {
            AlertExtension var4 = var3.method1(this.method1());
            if (var4 != null) {
               var4.method3(var1);
            }
         }
      }
   }

   @Nullable
   default Child method4(Predicate<Child> var1) {
      for (TraitReader var3 : this.getChildren()) {
         if (var1.test(var3)) {
            return (Child)var3;
         }

         AlertExtension var4 = var3.method1(this.method1());
         if (var4 != null) {
            TraitReader var5 = var4.method4(var1);
            if (var5 != null) {
               return (Child)var5;
            }
         }
      }

      return null;
   }

   default <Build> Build method5(Supplier<Build> var1, AlertExtension.Extension<Build, Child> var2) {
      Object var3 = var1.get();

      for (TraitReader var5 : this.getChildren()) {
         AlertExtension var6 = var5.method1(this.method1());
         Object var7;
         if (var6 != null) {
            var7 = var6.method5(var1, var2);
         } else {
            var7 = null;
         }

         var2.accept(var3, var5, var7);
      }

      return (Build)var3;
   }

   @FunctionalInterface
   interface Extension<Build, Child> {
      void accept(Build var1, Child var2, @Nullable Build var3);
   }
}
