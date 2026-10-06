package com.moonsworth.lunar.files;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class Files4_2 {
   public Files4$Data method1(Collection<Files5> var1, Files_3 var2, Consumer<Files6> var3) {
      Files6 var4 = this.method3(var1, var2);
      var3.accept(var4);
      if (!var4.method7().isEmpty()) {
         return new Files4$Data(null, var4.method7());
      }

      boolean var5 = false;
      Collection var6 = new ArrayList();

      for (Files3 var8 : var2.method2(var4)) {
         if (var8.method3(var4)) {
            Optional var9 = this.method4(var4, var8);
            if (!var9.isPresent()) {
               var5 = true;
               break;
            }

            Files2_2 var10 = (Files2_2)var9.get();
            var6.add(var10);
         }
      }

      if (var5) {
         var6 = var2.method1(var4);
      }

      for (Files2_2 var12 : var6) {
         this.method5(var4, var12);
      }

      return new Files4$Data(var6, null);
   }

   public boolean method2(Collection<Files5> var1, Files_3 var2, Consumer<Files6> var3) {
      Files6 var4 = this.method3(var1, var2);
      var3.accept(var4);

      for (Files3 var6 : var2.method2(var4)) {
         if (var6.method3(var4)) {
            Optional var7 = this.method4(var4, var6);
            if (var7.isEmpty()) {
               return true;
            }
         }
      }

      return false;
   }

   public Files6 method3(Collection<Files5> var1, Files_3 var2) {
      Files6 var3 = new Files6(var1, var2);
      if (this instanceof Files4Base3 var4) {
         var3.method9(var4.method4()::method10);
      }

      var2.method3().forEach(var2x -> {
         Files3 var3x = var2x.method1(var3);
         if (var3x.method3(var3)) {
            Optional var4x = this.method4(var3, var3x);
            var4x.ifPresentOrElse(var3::method1, () -> {
               if (!var3x.optional()) {
                  var3.method7().add(var3x);
               }
            });
         }
      });
      return var3;
   }

   public abstract Optional<Files2_2> method4(Files6 var1, Files3 var2);

   public abstract void method5(Files6 var1, Files2_2 var2);
}
