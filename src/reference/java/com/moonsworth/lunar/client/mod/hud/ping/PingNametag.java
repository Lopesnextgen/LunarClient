package com.moonsworth.lunar.client.mod.hud.ping;

import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class PingNametag extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("pingAbove").method4(true))
      .method31();

   public PingNametag(Ping ping1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(ping1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.handle(EventRenderNameTag.class, arg1x -> {
         if (Ref.method7() != null && !arg1x.isCancelled()) {
            if (arg1x.method2() instanceof EntityPlayerBridge bridgeextension2222) {
               if (NpcUtils.method3(bridgeextension2222, ServerBrandWatcher.method8(KeystrokesType.HYPIXEL))) {
                  return;
               }

               int number8 = 0;
               boolean flag4 = false;
               Ping ping5 = (Ping)((ChildModBinding)this.method7(ModTraits.field16)).method1();
               if (bridgeextension2222.bridge$isSelf()) {
                  number8 = ping5.method14();
                  flag4 = true;
               } else {
                  Component component6 = bridgeextension2222.bridge$getCustomName();
                  if (component6 != null && component6.hasDecoration(TextDecoration.OBFUSCATED)) {
                     return;
                  }

                  PlayerInfoBridge bridge2_337 = ping5.method11(bridgeextension2222.bridge$getUniqueID());
                  if (bridge2_337 != null) {
                     number8 = Math.max(bridge2_337.bridge$getLatency(), 0);
                  }
               }

               Object obj9 = ping5.method3(number8, flag4, null);
               if ((Boolean)ping5.field26.get()) {
                  String text10 = (String)ping5.field27.get();
                  if (text10 != null && !text10.trim().isEmpty()) {
                     obj9 = Component.text(text10, TextColor.color(ping5.field28.method13())).append((Component)obj9);
                  }
               }

               if ((Boolean)this.field8.get()) {
                  arg1x.getLines().add(obj9);
               } else {
                  arg1x.getLines().add(0, obj9);
               }
            }
         }
      });
   }

   public String getId() {
      return "PING_NAMETAG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   @Generated
   public ToggleOption method13() {
      return this.field8;
   }
}
