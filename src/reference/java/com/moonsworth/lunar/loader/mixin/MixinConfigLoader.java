package com.moonsworth.lunar.loader.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.IchorLoader;
import com.moonsworth.lunar.ichor.IchorInjector;
import com.moonsworth.lunar.ichor.URLClassLoader;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MixinConfigLoader implements IchorLoader {
   private final IchorStage field1;
   private final Function<Config, String> field2;
   private final BiConsumer<IchorTransformer, Config> field3;

   public MixinConfigLoader(IchorStage ichor41, Function<Config, String> function2) {
      this(ichor41, function2, null);
   }

   public MixinConfigLoader(IchorStage ichor41, Function<Config, String> function2, BiConsumer<IchorTransformer, Config> biconsumer3) {
      this.field1 = ichor41;
      this.field2 = function2;
      this.field3 = biconsumer3;
      if (!ichor41.hasMixinRuntime()) {
         throw new IllegalArgumentException("InjectPhase must be a mixin stage");
      }
   }

   public void loadIchor(IchorTransformer autocloseableiterator21) {
      Config config2 = Config.method36(autocloseableiterator21.method20().method34().method6());
      autocloseableiterator21.method22().add(new MixinConfigLoader.Data(config2));
      if (this.field3 != null) {
         this.field3.accept(autocloseableiterator21, config2);
      }
   }

   public class Data extends ConfigRegistrationTask {
      public Data(Config config2) {
         super(config2);
      }

      public void method1(IchorStage ichor41, IchorInjector mixininternal22, URLClassLoader urlclassloader3) {
         if (ichor41 == MixinConfigLoader.this.field1) {
            mixininternal22.registerMixins(List.of(MixinConfigLoader.this.field2.apply(this.IORIRHROIIIICHHIICIHHRHRORHRCI)));
         }
      }
   }
}
