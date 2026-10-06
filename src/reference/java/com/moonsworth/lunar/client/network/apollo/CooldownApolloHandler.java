package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.common.icon.AdvancedResourceLocationIcon;
import com.lunarclient.apollo.common.icon.Icon;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.icon.SimpleResourceLocationIcon;
import com.lunarclient.apollo.cooldown.v1.CooldownStyle;
import com.lunarclient.apollo.cooldown.v1.DisplayCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.RemoveCooldownMessage;
import com.lunarclient.apollo.cooldown.v1.ResetCooldownsMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.cooldowns.ItemCooldown;
import com.moonsworth.lunar.client.framework.feature.cooldowns.IconCooldown;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.combat.cooldowns.Cooldowns;
import com.moonsworth.lunar.client.network.apollo.ApolloPacketUtils;
import java.util.Set;

public class CooldownApolloHandler extends ApolloModuleHandler {
   public CooldownApolloHandler() {
      super("cooldown", "Cooldown");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(DisplayCooldownMessage.class, RemoveCooldownMessage.class, ResetCooldownsMessage.class);
   }

   protected void onEnable() {
      Cooldowns.method13();
   }

   protected void onDisable() {
      Cooldowns.method13();
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(DisplayCooldownMessage.class).ifPresent(arg1x -> {
         if (arg1x.hasIcon()) {
            String text2 = arg1x.getName();
            long number3 = NetworkTypes.fromProtobuf(arg1x.getDuration()).toMillis();
            Icon icon5 = NetworkTypes.fromProtobuf(arg1x.getIcon());
            this.method3(text2, number3, icon5, arg1x.hasStyle() ? arg1x.getStyle() : null);
         }
      });
      highlightimpl_31.unpack(RemoveCooldownMessage.class).ifPresent(arg0 -> Cooldowns.method5(arg0.getName()));
      highlightimpl_31.unpack(ResetCooldownsMessage.class).ifPresent(arg0 -> Cooldowns.method13());
   }

   private void method3(String text, long value, Icon icon4, CooldownStyle cooldownstyle5) {
      if (icon4 instanceof ItemStackIcon itemstackicon11) {
         ItemStackBridge bridgeextension_412 = ApolloPacketUtils.method2(itemstackicon11);
         if (bridgeextension_412 != null) {
            Cooldowns.method4(new ItemCooldown(text, value, bridgeextension_412, cooldownstyle5));
         }
      } else {
         String text7 = null;
         if (icon4 instanceof AdvancedResourceLocationIcon advancedresourcelocationicon8) {
            text7 = advancedresourcelocationicon8.getResourceLocation();
         } else if (icon4 instanceof SimpleResourceLocationIcon simpleresourcelocationicon9) {
            text7 = simpleresourcelocationicon9.getResourceLocation();
         }

         if (text7 == null) {
            ApolloPacketUtils.method3(null, text7);
         } else {
            ResourceLocationBridge horsestats146;
            try {
               horsestats146 = ResourceLocationBridge.create(text7);
            } catch (Exception exception10) {
               ApolloPacketUtils.method3(exception10, text7);
               return;
            }

            Cooldowns.method4(new IconCooldown(text, value, horsestats146, icon4, cooldownstyle5));
         }
      }
   }
}
