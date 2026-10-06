package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.Annotation5;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import com.moonsworth.lunar.loader.Ichor4Type;

public class Ichor2Handler_2 implements IchorInjection {
   private final MixinMisc field1;

   public Ichor2Handler_2(MixinMisc var1) {
      this.field1 = var1;
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.FORGE_PATCH};
   }

   @Annotation5
   public byte[] method2(String var1, byte[] items) {
      try {
         MixinMisc2 var3 = this.field1.method6().get(var1);
         if (var3 != null) {
            try {
               return var3.method2(items);
            } catch (Exception var5) {
               Ichor6Impl.field2.method5(FatalIchorError5.Type.FATAL, "Failed to patch " + var1 + " -> " + var3.field1);
               var5.printStackTrace();
            }
         }

         return items;
      } catch (Throwable var6) {
         throw var6;
      }
   }

   public MixinMisc method3() {
      return this.field1;
   }
}
