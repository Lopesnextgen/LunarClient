package com.moonsworth.lunar.framework;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.IchorInjector;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.loader.PipelineStage;
import com.moonsworth.lunar.loader.mixin.ConfigRegistrationTask;
import java.util.List;

public final class OptifineMixinHandler extends ConfigRegistrationTask {
   public OptifineMixinHandler(Config config1) {
      super(config1);
   }

   public void method1(IchorStage ichor41, IchorInjector mixininternal22, URLClassLoader urlclassloader3) {
      if (ichor41 == PipelineStage.MIXIN) {
         mixininternal22.registerMixins(
            List.of(
               this.method2("optifine"),
               this.method2("optifine_combined"),
               this.method2("optifine_" + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId())
            )
         );
      }
   }
}
