package com.moonsworth.lunar.client.pkg;

import java.util.Optional;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Pkg6 {
   public static Pkg6 field1;
   public static Component field2;
   private final Pkg4 field3;
   private final Pkg5 field4;
   private final Pkg2 field5;

   Pkg6(Pkg4 var1, Pkg5 var2, Pkg2 var3) {
      this.field3 = var1;
      this.field4 = var2;
      this.field5 = var3;
   }

   public Optional<Pkg> method1(Pkg3 var1) {
      Optional var2 = this.method2(var1);
      Optional var3 = this.field4.lookupRedirect(var1);
      if (var3.isPresent()) {
         var2 = this.method2((Pkg3)var3.get());
      }

      return var2;
   }

   private Optional<Pkg> method2(Pkg3 var1) {
      return Optional.of(var1).filter(this.field5::method2).flatMap(this.field3::resolve).filter(this.field5::method1);
   }

   @Generated
   public Pkg2 method3() {
      return this.field5;
   }

   static {
      try {
         field1 = new Pkg6(Pkg4.SYSTEM, Pkg5.createDnsSrvRedirectHandler(), Pkg2.method3());
      } catch (Throwable var1) {
         var1.printStackTrace();
      }

      field2 = Component.text("This server is blocked by Mojang!").color(NamedTextColor.RED);
   }
}
