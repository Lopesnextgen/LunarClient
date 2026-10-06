package com.moonsworth.lunar.framework;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.framework.mixin.Ichor2Handler;
import com.moonsworth.lunar.framework.mixin.Ichor2Iterator;
import com.moonsworth.lunar.framework.mixin.Ichor2Iterator2;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.MixinInternal2;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.loader.Ichor4Type;
import com.moonsworth.lunar.loader.mixin.MixinInternal3Handler;
import java.io.File;
import java.util.List;
import lombok.Generated;

public class Ichor5Handler implements Ichor5 {
   private FrameworkType field1;

   @Override
   public void loadIchor(IchorTransformer var1) {
      try {
         IchorPipeline var2 = var1.method20();
         if (var2.method33().contains(Ichor4Type.OPTIFINE_PATCH)) {
            Config var3 = Config.method36(var2.method34().method6());
            boolean var4 = var2.method11("forge").isPresent();
            this.field1 = FrameworkType.from(var3, var4);
            var2.method19(com.moonsworth.lunar.ichor.Ichor5Handler.class).ifPresent(var1x -> var1x.method3(var2, "optifine", List.of("", "net/optifine/")));
            var1.method1(new Ichor2Handler());
            File var5 = var2.method11("optifine").orElseThrow().toFile();
            var1.method1(new Ichor2Iterator(this.field1, var5, var4));
            var1.method1(new Ichor2Iterator2());
            var1.method22().add(new Ichor5Handler.Data2(var3));
         }
      } catch (Throwable var6) {
         throw var6;
      }
   }

   @Generated
   public FrameworkType method1() {
      return this.field1;
   }

   public static final class Data2 extends MixinInternal3Handler {
      public Data2(Config var1) {
         super(var1);
      }

      @Override
      public void method1(Ichor4 var1, MixinInternal2 var2, URLClassLoader var3) {
         if (var1 == Ichor4Type.MIXIN) {
            var2.registerMixins(
               List.of(
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("optifine"),
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("optifine_combined"),
                  this.OCOOIIHCIHHORICIOHHRIHORRHRRHI("optifine_" + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId())
               )
            );
         }
      }
   }
}
