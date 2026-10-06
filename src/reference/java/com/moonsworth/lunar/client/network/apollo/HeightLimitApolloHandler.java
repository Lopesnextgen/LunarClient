package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.heightlimit.v1.OverrideHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.RemoveHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.ResetHeightLimitsMessage;
import com.lunarclient.apollo.player.v1.UpdatePlayerWorldMessage;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.feature.heightlimit.Heightlimit;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.network.apollo.ApolloPacketUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.framework.Client;

public class HeightLimitApolloHandler extends ApolloModuleHandler {
   private final Map<String, Heightlimit> field4 = new HashMap<>();

   public HeightLimitApolloHandler() {
      super("height_limit", "Height Limit");
   }

   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideHeightLimitMessage.class, RemoveHeightLimitMessage.class, ResetHeightLimitsMessage.class);
   }

   protected void onEnable() {
      this.method3();
   }

   protected void onDisable() {
      this.method3();
   }

   public void method3(HighlightImpl_3 highlightimpl_31) {
      highlightimpl_31.unpack(OverrideHeightLimitMessage.class).ifPresent(arg1x -> {
         if (arg1x.getLimit() > 0) {
            String text2 = arg1x.getDisplayNameAdventureJsonLines();
            String text3 = null;
            if (!text2.isEmpty()) {
               Component component4 = ApolloPacketUtils.method4(text2);
               text3 = component4 == null ? null : TextBridge.asLegacyString(component4);
            }

            this.field4.put(arg1x.getWorld(), new Heightlimit(arg1x.getLimit(), text3));
            this.resolve();
         }
      });
      highlightimpl_31.unpack(RemoveHeightLimitMessage.class).ifPresent(arg1x -> {
         this.field4.remove(arg1x.getWorld());
         this.resolve();
      });
      highlightimpl_31.unpack(ResetHeightLimitsMessage.class).ifPresent(arg1x -> this.method3());
      if (this.isEnabled()) {
         highlightimpl_31.unpack(UpdatePlayerWorldMessage.class).ifPresent(arg1x -> this.resolve());
      }
   }

   private void resolve() {
      Heightlimit heightlimit1 = this.field4.get(Client.method109().getWorld());
      Client.method109().method40().method68().method14(heightlimit1);
   }

   private void method3() {
      this.field4.clear();
      this.resolve();
   }
}
