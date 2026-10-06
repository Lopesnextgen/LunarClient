package com.moonsworth.lunar.client;

import com.google.protobuf.Message;
import com.lunarclient.apollo.heightlimit.v1.OverrideHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.RemoveHeightLimitMessage;
import com.lunarclient.apollo.heightlimit.v1.ResetHeightLimitsMessage;
import com.lunarclient.apollo.player.v1.UpdatePlayerWorldMessage;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.framework.feature.heightlimit.Heightlimit;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.framework.Client;

public class Highlight3Iterator23 extends ApolloModuleHandler {
   private final Map<String, Heightlimit> field4 = new HashMap<>();

   public Highlight3Iterator23() {
      super("height_limit", "Height Limit");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(OverrideHeightLimitMessage.class, RemoveHeightLimitMessage.class, ResetHeightLimitsMessage.class);
   }

   @Override
   protected void onEnable() {
      this.method3();
   }

   @Override
   protected void onDisable() {
      this.method3();
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(OverrideHeightLimitMessage.class).ifPresent(var1x -> {
         if (var1x.getLimit() > 0) {
            String var2 = var1x.getDisplayNameAdventureJsonLines();
            String var3 = null;
            if (!var2.isEmpty()) {
               Component var4 = Rewindhandlers3.method4(var2);
               var3 = var4 == null ? null : AdventureTextBridge.asLegacyString(var4);
            }

            this.field4.put(var1x.getWorld(), new Heightlimit(var1x.getLimit(), var3));
            this.resolve();
         }
      });
      var1.unpack(RemoveHeightLimitMessage.class).ifPresent(var1x -> {
         this.field4.remove(var1x.getWorld());
         this.resolve();
      });
      var1.unpack(ResetHeightLimitsMessage.class).ifPresent(var1x -> this.method3());
      if (this.isEnabled()) {
         var1.unpack(UpdatePlayerWorldMessage.class).ifPresent(var1x -> this.resolve());
      }
   }

   private void resolve() {
      Heightlimit var1 = this.field4.get(Client.method109().getWorld());
      Client.method109().method40().method68().method14(var1);
   }

   private void method3() {
      this.field4.clear();
      this.resolve();
   }
}
