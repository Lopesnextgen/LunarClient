package com.moonsworth.lunar.loader.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.MixinInternal2;
import com.moonsworth.lunar.ichor.URLClassLoader;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Ichor5Loader implements Ichor5 {
   private final Ichor4 field1;
   private final Function<Config, String> field2;
   private final BiConsumer<IchorTransformer, Config> field3;

   public Ichor5Loader(Ichor4 var1, Function<Config, String> var2) {
      this(var1, var2, null);
   }

   public Ichor5Loader(Ichor4 var1, Function<Config, String> var2, BiConsumer<IchorTransformer, Config> var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      if (!var1.hasMixinRuntime()) {
         throw new IllegalArgumentException("InjectPhase must be a mixin stage");
      }
   }

   @Override
   public void loadIchor(IchorTransformer var1) {
      Config var2 = Config.method36(var1.method20().method34().method6());
      var1.method22().add(new Ichor5Loader.Data(var2));
      if (this.field3 != null) {
         this.field3.accept(var1, var2);
      }
   }

   public class Data extends MixinInternal3Handler {
      public Data(Config var2) {
         super(var2);
      }

      @Override
      public void method1(Ichor4 var1, MixinInternal2 var2, URLClassLoader var3) {
         if (var1 == Ichor5Loader.this.field1) {
            var2.registerMixins(List.of(Ichor5Loader.this.field2.apply(this.IORIRHROIIIICHHIICIHHRHRORHRCI)));
         }
      }
   }
}
