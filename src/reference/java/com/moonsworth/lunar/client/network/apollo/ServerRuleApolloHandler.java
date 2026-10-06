package com.moonsworth.lunar.client.network.apollo;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.option.Options;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.bridge.potion.PotionRegistryBridge;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.client.ui.CompetitiveDisconnectScreen;
import com.moonsworth.lunar.client.event.options.ApolloOptionUpdateEvent;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.EventCommand;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventScreenAction;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collection;
import java.util.List;

public class ServerRuleApolloHandler extends ApolloModuleHandler {
   private boolean field4;

   public ServerRuleApolloHandler() {
      super("server_rule", "Server Rule");
      this.handle(ApolloOptionUpdateEvent.class, this::method5);
      this.handle(EventDisconnect.class, this::method6);
      this.handle(EventScreenChange.class, this::method7);
      this.handle(EventCommand.class, this::method8);
      this.handle(EventScreenAction.class, this::method9);
      this.handle(EventKeybind.class, this::method10);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond.class, arg1 -> this.method11());
      this.handle(EventServerChange.class, arg1 -> this.method11());
   }

   protected void onEnable() {
      this.field4 = false;
   }

   protected void onDisable() {
      this.field4 = false;
   }

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

   public void method3(HighlightImpl_3 highlightimpl_31) {
   }

   public int method3() {
      return ((Number)this.getOptions().get(ServerRuleModule.NAMETAG_RENDER_DISTANCE)).intValue();
   }

   private Bridge5Extension62 method4(Runnable runnable1) {
      CompetitiveDisconnectScreen bridge7iterator2 = new CompetitiveDisconnectScreen(Ref.method3().bridge$getCurrentScreen(), runnable1);
      return Bridge.method8().method18(bridge7iterator2);
   }

   private void method5(ApolloOptionUpdateEvent highlightimpl2_21) {
      Option option2 = highlightimpl2_21.getOption();
      Object obj3 = highlightimpl2_21.getValue();
      if (option2.equals(ServerRuleModule.OVERRIDE_BRIGHTNESS)) {
         GameOptionsBridge mixinhelper2_84 = Ref.method3().bridge$getGameSettings();
         if ((Boolean)obj3) {
            int number5 = ((Number)this.getOptions().get(ServerRuleModule.BRIGHTNESS)).intValue();
            mixinhelper2_84.bridge$setGammaOverride(number5 / 100.0F);
         } else {
            mixinhelper2_84.bridge$removeGammaOverride();
         }
      }

      if (option2.equals(ServerRuleModule.BRIGHTNESS) && (Boolean)this.getOptions().get(ServerRuleModule.OVERRIDE_BRIGHTNESS)) {
         GameOptionsBridge mixinhelper2_86 = Ref.method3().bridge$getGameSettings();
         mixinhelper2_86.bridge$setGammaOverride(((Number)obj3).intValue() / 100.0F);
      }
   }

   private void method6(EventDisconnect event) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.OVERRIDE_BRIGHTNESS)) {
         Ref.method3().bridge$getGameSettings().bridge$removeGammaOverride();
      }
   }

   private void method7(EventScreenChange event) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.COMPETITIVE_GAME)) {
         boolean flag2 = event.method1() instanceof Bridge5Extension62 bridge5extension623 && bridge5extension623.method2() instanceof CompetitiveDisconnectScreen;
         if (this.field4 && !flag2) {
            this.field4 = false;
         }
      }
   }

   private void method8(EventCommand event) {
      Options options2 = this.getOptions();
      if ((Boolean)options2.get(ServerRuleModule.COMPETITIVE_GAME)) {
         String text3 = event.method3().toLowerCase();
         if (((List)options2.get(ServerRuleModule.COMPETITIVE_COMMANDS)).contains(text3)) {
            if (this.field4) {
               this.field4 = false;
            } else {
               event.cancel();
               this.field4 = true;
               MinecraftBridge bridge5_124 = Ref.method3();
               Bridge5Extension62 bridge5extension625 = this.method4(() -> {
                  bridge5_124.bridge$displayScreen(null);
                  this.field4 = true;
                  Ref.method7().bridge$sendCommand(event.getCommand());
               });
               Ref.method3().bridge$submit(() -> bridge5_124.bridge$displayScreen(bridge5extension625));
            }
         }
      }
   }

   private void method9(EventScreenAction event) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.COMPETITIVE_GAME)) {
         event.cancel();
         MinecraftBridge bridge5_122 = Ref.method3();
         Bridge5Extension62 bridge5extension623 = this.method4(event::method1);
         bridge5_122.bridge$displayScreen(bridge5extension623);
      }
   }

   private void method10(EventKeybind event) {
      if ((Boolean)this.getOptions().get(ServerRuleModule.DISABLE_BROADCASTING)) {
         if (Ref.method3().bridge$getGameSettings().bridge$isStreamKey(event.method10())) {
            event.cancel();
         }
      }
   }

   private void method11() {
      if ((Boolean)this.getOptions().get(ServerRuleModule.DISABLE_SHADERS)) {
         Bridge.method5().ifPresent(arg0 -> {
            ShadersBridge slayer31 = arg0.getShaders();
            String text2 = slayer31.getShaderPack();
            if (text2 != null && !text2.equalsIgnoreCase(slayer31.getPackNone()) && !text2.equalsIgnoreCase(slayer31.getPackDefault())) {
               slayer31.setShaderPack(slayer31.getPackNone());
            }
         });
      }
   }

   @VersionGate(min = 6)
   public boolean method12(Bridge6_10 bridge6_101, ItemStackBridge bridgeextension_42) {
      PotionRegistryBridge fog33 = Bridge.method36();
      PotionBridge fog24 = fog33.method6();
      if (!bridge6_101.bridge$isPotionActive(fog24)) {
         return true;
      }

      com.moonsworth.lunar.bridge.fog.Fog fog5 = bridge6_101.bridge$getActivePotionEffect(fog24);
      double value6 = 4.0 * (fog5.bridge$getAmplifier() + 1);
      double value8 = bridge6_101.bridge$getAttackDamageAttribute();
      if (value8 > value6 + 5.0) {
         return true;
      }

      double value10 = 0.0;
      PotionBridge fog212 = fog33.method7();
      if (bridge6_101.bridge$isPotionActive(fog212)) {
         com.moonsworth.lunar.bridge.fog.Fog fog13 = bridge6_101.bridge$getActivePotionEffect(fog212);
         value10 = 3.0 * (fog13.bridge$getAmplifier() + 1);
      }

      double value15 = bridgeextension_42.bridge$getAttackDamage();
      return value8 + value15 + value10 - value6 > 0.0;
   }
}
