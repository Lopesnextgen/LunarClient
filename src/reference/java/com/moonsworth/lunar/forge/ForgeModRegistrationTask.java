package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.IchorInjector;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.loader.PipelineStage;
import com.moonsworth.lunar.loader.mixin.ConfigRegistrationTask;
import java.util.List;

public class ForgeModRegistrationTask extends ConfigRegistrationTask {
   public ForgeModRegistrationTask(Config config1) {
      super(config1);
   }

   public void method1(IchorStage ichor41, IchorInjector mixininternal22, URLClassLoader urlclassloader3) {
      if (ichor41 == PipelineStage.MIXIN) {
         mixininternal22.registerMixins(
            List.of(
               "mixins.ichor.forge." + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId() + ".json",
               "mixins." + this.IORIRHROIIIICHHIICIHHRHRORHRCI.method47() + "_forge_" + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId() + ".json"
            )
         );
      }
   }
}
