package com.moonsworth.lunar.client.replay.replaymod.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.IchorLoader;
import com.moonsworth.lunar.ichor.IchorInjector;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.loader.PipelineStage;
import com.moonsworth.lunar.loader.mixin.ConfigRegistrationTask;
import com.moonsworth.lunar.client.replay.replaymod.forge.ichor.Ichor2Handler2;
import java.util.List;

public class Ichor5Loader implements IchorLoader {
   public Ichor5Loader() {
   }

   public void loadIchor(IchorTransformer autocloseableiterator21) {
      autocloseableiterator21.method22().add(new Ichor5Loader.ReplayModMixinHandler(Config.method36(autocloseableiterator21.method20().method34().method6())));
      autocloseableiterator21.method1(new Ichor2Handler2());
   }

   public static void registerMixins(List<String> list0) {
      list0.add("mixins.jgui.json");
      list0.add("mixins.compat.mapwriter.replaymod.json");
      list0.add("mixins.compat.shaders.replaymod.json");
      list0.add("mixins.core.replaymod.json");
      list0.add("mixins.extras.playeroverview.replaymod.json");
      list0.add("mixins.recording.replaymod.json");
      list0.add("mixins.render.blend.replaymod.json");
      list0.add("mixins.render.replaymod.json");
      list0.add("mixins.replay.replaymod.json");
   }

   public static class ReplayModMixinHandler extends ConfigRegistrationTask {
      public ReplayModMixinHandler(Config config1) {
         super(config1);
      }

      public void method1(IchorStage ichor41, IchorInjector mixininternal22, URLClassLoader urlclassloader3) {
         if (ichor41 == PipelineStage.META_MIXIN) {
            mixininternal22.registerMixins(List.of("mixins.meta.ichor.replaymod." + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId() + ".json"));
         } else if (ichor41 == PipelineStage.MIXIN) {
            mixininternal22.registerMixins(List.of("mixins.ichor.replaymod." + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId() + ".json"));
         }
      }
   }
}
