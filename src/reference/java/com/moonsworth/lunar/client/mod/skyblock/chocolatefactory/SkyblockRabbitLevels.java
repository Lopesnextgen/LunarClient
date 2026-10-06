package com.moonsworth.lunar.client.mod.skyblock.chocolatefactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.RabbitLevel;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemStackSize.ItemStackSize;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class SkyblockRabbitLevels extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final LoadingCache<ItemStackBridge, Optional<RabbitLevel>> field9 = CacheBuilder.newBuilder()
      .maximumSize(100L)
      .softValues()
      .build(new CacheLoader<ItemStackBridge, Optional<RabbitLevel>>() {
         public Optional<RabbitLevel> method1(ItemStackBridge bridgeextension_41) {
            return RabbitLevel.method3(bridgeextension_41);
         }
      });

   public SkyblockRabbitLevels(SkyblockChocolateFactory skyblockchocolatefactory1, ToggleOption lightingextension4432) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method4(false, skyblockchocolatefactory1));
      this.method2(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(ItemStackSize.class, this::method2);
      this.handle(EventRenderItemStackSize.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_RABBIT_LEVELS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventRenderItemStackSize highlightimpl121) {
      if (this.field8.method7() == SkyblockMenuType.CHOCOLATE_FACTORY) {
         ItemStackBridge bridgeextension_42 = highlightimpl121.getItem();
         if (bridgeextension_42 != null && !highlightimpl121.method1()) {
            try {
               Optional optional3 = (Optional)this.field9.get(highlightimpl121.getItem());
               if (optional3.isEmpty()) {
                  return;
               }

               RabbitLevel fishing54 = (RabbitLevel)optional3.get();
               if (fishing54.getLevel() >= 100 && highlightimpl121 instanceof ItemStackSize data5) {
                  float value6 = Ref.method10().bridge$getStringWidth(fishing54.getLevel() + "") * 0.8F;
                  MixinHelper_4 mixinhelper_47 = data5.method2();
                  mixinhelper_47.push();
                  mixinhelper_47.method38(data5.getX() + 17 - value6, data5.getY() + 10, 301.0F);
                  mixinhelper_47.method40(0.8F, 0.8F);
                  mixinhelper_47.method18(Ref.method10(), fishing54.method1(), 0, 0, -1, true);
                  mixinhelper_47.pop();
               } else {
                  highlightimpl121.setText(fishing54.method1());
               }
            } catch (ExecutionException executionexception8) {
               CrashReporter.method5(executionexception8, "SkyblockChocolateFactory");
            }
         }
      }
   }
}
