package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError9;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class MixinExtra {
   private final Ichor4 field1;
   private final Map<String, byte[]> field2;
   private final Predicate<String> field3;

   public MixinExtra(Ichor4 var1, Map<String, byte[]> var2, Predicate<String> var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public Optional<MixinExtra2> method1(Collection<String> var1) {
      for (String var3 : var1) {
         String var4 = var3.replace("/", ".");
         if (this.field3.test(var4)) {
            byte[] var5 = this.field2.get(var4);
            if (var5 != null) {
               return Optional.of(new MixinExtra2(this.field1, var4, var5));
            }
         }
      }

      return Optional.empty();
   }

   public static MixinExtra method2(Ichor4 ichor4, Path var1, Predicate<String> var2) {
      Map var3 = FatalIchorError9.method7(var1, var2);
      return new MixinExtra(ichor4, var3, var2);
   }

   public Ichor4 method3() {
      return this.field1;
   }

   public Map<String, byte[]> method4() {
      return this.field2;
   }

   public Predicate<String> method5() {
      return this.field3;
   }
}
