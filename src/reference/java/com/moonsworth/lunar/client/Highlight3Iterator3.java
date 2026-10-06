package com.moonsworth.lunar.client;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.bridge.fog.Fog3;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.client.gui.CompetitiveDisconnectScreen;
import com.moonsworth.lunar.client.event.options.OptionUpdateEvent;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ScreenActionEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Collection;
import java.util.List;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;

public class Highlight3Iterator3 extends ApolloModuleHandler {
   private boolean field4;

   public Highlight3Iterator3() {
      super("server_rule", "Server Rule");
      this.handle(OptionUpdateEvent.class, this::method5);
      this.handle(DisconnectEvent.class, this::method6);
      this.handle(ScreenChangeEvent.class, this::method7);
      this.handle(EventCommandLegacy.class, this::method8);
      this.handle(ScreenActionEvent.class, this::method9);
      this.handle(KeybindEvent.class, this::method10);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond.class, var1 -> this.method11());
      this.handle(ServerChangeEvent.class, var1 -> this.method11());
   }

   @Override
   protected void onEnable() {
      this.field4 = false;
   }

   @Override
   protected void onDisable() {
      this.field4 = false;
   }

   @Override
   public Collection<Option<?, ?, ?>> method1() {
      return List.of(
         ServerRuleModule.COMPETITIVE_GAME,
         ServerRuleModule.COMPETITIVE_COMMANDS,
         ServerRuleModule.DISABLE_SHADERS,
         ServerRuleModule.DISABLE_CHUNK_RELOADING,
         ServerRuleModule.DISABLE_BROADCASTING,
         ServerRuleModule.ANTI_PORTAL_TRAPS,
         ServerRuleModule.OVERRIDE_BRIGHTNESS,
         ServerRuleModule.BRIGHTNESS,
         ServerRuleModule.OVERRIDE_NAMETAG_RENDER_DISTANCE,
         ServerRuleModule.NAMETAG_RENDER_DISTANCE,
         ServerRuleModule.OVERRIDE_MAX_CHAT_LENGTH,
         ServerRuleModule.MAX_CHAT_LENGTH,
         ServerRuleModule.CRYSTAL_OPTIMIZER
      );
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
   }

   public int method3() {
      return ((Number)this.getOptions().get(ServerRuleModule.NAMETAG_RENDER_DISTANCE)).intValue();
   }

   private Bridge5Extension62 method4(Runnable var1) {
      CompetitiveDisconnectScreen var2 = new CompetitiveDisconnectScreen(ThreadModuleDump63.method3().bridge$getCurrentScreen(), var1);
      return Bridge.method8().method18(var2);
   }

   private void method5(OptionUpdateEvent var1) {
      Option var2 = var1.getOption();
      Object var3 = var1.getValue();
      if (var2.equals(ServerRuleModule.OVERRIDE_BRIGHTNESS)) {
         GameOptionsBridge var4 = ThreadModuleDump63.method3().bridge$getGameSettings();
         if ((Boolean)var3) {
            int var5 = ((Number)this.getOptions().get(ServerRuleModule.BRIGHTNESS)).intValue();
            var4.bridge$setGammaOverride(var5 / 100.0F);
         } else {
            var4.bridge$removeGammaOverride();
         }
      }

      if (var2.equals(ServerRuleModule.BRIGHTNESS) && (Boolean)this.getOptions().get(ServerRuleModule.OVERRIDE_BRIGHTNESS)) {
         GameOptionsBridge var6 = ThreadModuleDump63.method3().bridge$getGameSettings();
         var6.bridge$setGammaOverride(((Number)var3).intValue() / 100.0F);
      }
   }

   private void method6(DisconnectEvent var1) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.OVERRIDE_BRIGHTNESS)) {
         ThreadModuleDump63.method3().bridge$getGameSettings().bridge$removeGammaOverride();
      }
   }

   private void method7(ScreenChangeEvent var1) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.COMPETITIVE_GAME)) {
         boolean var2 = var1.method1() instanceof Bridge5Extension62 var3 && var3.method2() instanceof CompetitiveDisconnectScreen;
         if (this.field4 && !var2) {
            this.field4 = false;
         }
      }
   }

   private void method8(EventCommandLegacy var1) {
      Options var2 = this.getOptions();
      if ((Boolean)var2.get(ServerRuleModule.COMPETITIVE_GAME)) {
         String var3 = var1.method3().toLowerCase();
         if (((List)var2.get(ServerRuleModule.COMPETITIVE_COMMANDS)).contains(var3)) {
            if (this.field4) {
               this.field4 = false;
            } else {
               var1.cancel();
               this.field4 = true;
               Bridge5_12 var4 = ThreadModuleDump63.method3();
               Bridge5Extension62 var5 = this.method4(() -> {
                  var4.bridge$displayScreen(null);
                  this.field4 = true;
                  ThreadModuleDump63.method7().bridge$sendCommand(var1.getCommand());
               });
               ThreadModuleDump63.method3().bridge$submit(() -> var4.bridge$displayScreen(var5));
            }
         }
      }
   }

   private void method9(ScreenActionEvent var1) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.COMPETITIVE_GAME)) {
         var1.cancel();
         Bridge5_12 var2 = ThreadModuleDump63.method3();
         Bridge5Extension62 var3 = this.method4(var1::method1);
         var2.bridge$displayScreen(var3);
      }
   }

   private void method10(KeybindEvent var1) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.DISABLE_BROADCASTING)) {
         if (ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isStreamKey(var1.method10())) {
            var1.cancel();
         }
      }
   }

   private void method11() {
      if ((Boolean)this.getOptions().get(ServerRuleModule.DISABLE_SHADERS)) {
         Bridge.method5().ifPresent(var0 -> {
            Slayer3 var1 = var0.getShaders();
            String var2 = var1.getShaderPack();
            if (var2 != null && !var2.equalsIgnoreCase(var1.getPackNone()) && !var2.equalsIgnoreCase(var1.getPackDefault())) {
               var1.setShaderPack(var1.getPackNone());
            }
         });
      }
   }

   @Annotation2(min = 6)
   public boolean method12(Bridge6_10 var1, ItemStackBridge var2) {
      Fog3 var3 = Bridge.method36();
      Fog2 var4 = var3.method6();
      if (!var1.bridge$isPotionActive(var4)) {
         return true;
      }

      Fog var5 = var1.bridge$getActivePotionEffect(var4);
      double var6 = 4.0 * (var5.bridge$getAmplifier() + 1);
      double var8 = var1.bridge$getAttackDamageAttribute();
      if (var8 > var6 + 5.0) {
         return true;
      }

      double var10 = 0.0;
      Fog2 var12 = var3.method7();
      if (var1.bridge$isPotionActive(var12)) {
         Fog var13 = var1.bridge$getActivePotionEffect(var12);
         var10 = 3.0 * (var13.bridge$getAmplifier() + 1);
      }

      double var15 = var2.bridge$getAttackDamage();
      return var8 + var15 + var10 - var6 > 0.0;
   }
}
