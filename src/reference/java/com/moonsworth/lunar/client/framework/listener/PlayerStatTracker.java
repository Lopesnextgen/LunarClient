package com.moonsworth.lunar.client.framework.listener;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.ItemPotionBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectTypeBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldPlayer;
import com.moonsworth.lunar.client.network.server.PingEntryConfig;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.ResultEvent;
import com.moonsworth.lunar.client.event.combat.EventPotionThrow;
import com.moonsworth.lunar.client.event.combat.EventPlayerReceiveDamage;
import com.moonsworth.lunar.client.event.entity.EventEntityStatus;
import com.moonsworth.lunar.client.event.combat.EventOtherPlayerDamage;
import com.moonsworth.lunar.client.event.player.EventItemUse;
import com.moonsworth.lunar.client.event.player.EventItemUseFinish;
import com.moonsworth.lunar.client.event.entity.EventEntityHealthUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld;
import com.moonsworth.lunar.client.event.mixin.gui.EventScreenAction;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderCrosshair;
import com.moonsworth.lunar.client.mod.combat.PlayerCombatState;
import com.moonsworth.lunar.client.config.profile.ModProfile;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.util.net.ServerUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Set;

public class PlayerStatTracker implements EventBusAccess {
   public static boolean field1 = false;
   private final PingEntryConfig field2 = Ref.method4().method80();
   private long field3 = 0L;
   private int field4 = 0;

   public PlayerStatTracker() {
      this.handle(EventWorld.EventWorldChange.class, arg1 -> {
         long number2 = System.currentTimeMillis();
         if (this.field3 == 0L || number2 - this.field3 >= 150L) {
            this.field3 = number2;
         }
      });
      this.handle(
         EventRenderCrosshair.class,
         arg0 -> {
            if (Ref.method3().bridge$getGameSettings().bridge$getThirdPersonView() != 0
               && !(Boolean)Ref.method4().method40().method33().method13().get()) {
               arg0.HORHROIOIOICIRHIOCOICHHHIHCIIO(ResultEvent.Outcome.DENY);
            }
         }
      );
      this.handle(EventScreenAction.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventTick.class, this::method4);
      this.handle(EventOtherPlayerDamage.class, this::method6);
      this.handle(EventPlayerReceiveDamage.class, this::method7);
      this.handle(com.moonsworth.lunar.client.event.combat.EventAttack.class, this::method9);
      this.handle(EventEntityStatus.class, this::method8);
      this.handle(EventEntityHealthUpdate.class, this::method3);
      this.handle(EventItemUseFinish.class, this::method2);
      this.handle(EventPotionThrow.class, this::method10);
      this.handle(EventItemUse.class, this::method1);
   }

   private void method1(EventItemUse highlightimpl181) {
      if (highlightimpl181.method1().equals(Ref.method7())) {
         ItemBridge bridge6_42 = highlightimpl181.method2().bridge$getItem();
         if (bridge6_42 == null) {
            return;
         }

         String text3 = bridge6_42.bridge$getRegistryName();
         if (text3.equals("minecraft:egg") || text3.equals("minecraft:snowball")) {
            this.field2.method5(arg0 -> arg0.method13().increment());
         }

         if (text3.equals("minecraft:ender_pearl")
            && (!highlightimpl181.method1().bridge$getPlayerCapabilities().bridge$isCreativeMode() || Bridge.getMinecraftVersion().method20())) {
            this.field2.method5(arg0 -> arg0.method14().increment());
         }
      }
   }

   private void method2(EventItemUseFinish highlightimpl231) {
      if (highlightimpl231.method1().bridge$getWorld() instanceof WorldBridgeExtension && highlightimpl231.method1().equals(Ref.method7())) {
         if (highlightimpl231.method2() == null) {
            return;
         }

         ItemBridge bridge6_42 = highlightimpl231.method2().bridge$getItem();
         if (bridge6_42.bridge$getRegistryName().equals("minecraft:golden_apple") || bridge6_42.bridge$getRegistryName().equals("minecraft:enchanted_golden_apple")) {
            this.field2.method5(arg0 -> arg0.method8().increment());
         }

         highlightimpl231.method2().bridge$getFood().ifPresent(arg2x -> this.field2.method5(arg2xx -> arg2xx.method10().method1(arg2x.bridge$getHealing(highlightimpl231.method2()))));
      }
   }

   private void method3(EventEntityHealthUpdate highlightimpl31) {
      if (highlightimpl31.method1().equals(Ref.method7())) {
         if (highlightimpl31.method3() < highlightimpl31.method2()) {
            this.field2.method5(arg1x -> arg1x.method6().method1(highlightimpl31.method2() - highlightimpl31.method3()));
         } else {
            this.field2.method5(arg1x -> arg1x.method7().method1(highlightimpl31.method3() - highlightimpl31.method2()));
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.fishing.EventTick highlightimpl21) {
      if (Ref.method7() != null && NickHider.realSkinLocation == null) {
         Ref.method7().bridge$loadAndGetRealSkinType().ifPresent(arg0 -> NickHider.realSkinLocation = arg0);
      }

      if (this.field3 != 0L && System.currentTimeMillis() - this.field3 >= 1000L) {
         this.field3 = 0L;
         ServerUtils.updateServer();
         if (Ref.method3().bridge$getCurrentServerData() != null) {
            String text2 = Ref.method3().bridge$getCurrentServerData().bridge$serverIP().toLowerCase();
            if (ServerUtils.getServer() != null) {
               text2 = ServerUtils.getServer();
            }

            text2 = text2.toLowerCase();
            ModProfile horsestats3 = null;

            for (ModProfile horsestats5 : Client.method109().method61().method2()) {
               if (!horsestats5.getServer().isEmpty()) {
                  if (ServerBrandWatcher.method8(KeystrokesType.HYPIXEL)) {
                     horsestats3 = horsestats5;
                  } else if (text2.endsWith(horsestats5.getServer().toLowerCase())) {
                     Client.method109().method61().method3(horsestats5);
                     horsestats3 = null;
                     break;
                  }
               }
            }

            if (horsestats3 != null) {
               Client.method109().method61().method3(horsestats3);
            }
         }
      }
   }

   private void method5(EventScreenAction highlightimpl141) {
      if (!Ref.method4()
         .method84()
         .method3(ServerRuleModule.class)
         .filter(arg0 -> (Boolean)arg0.getOptions().get(ServerRuleModule.COMPETITIVE_GAME))
         .isPresent()) {
         boolean flag2 = Ref.method4().method81().method28() != null;
         String text3 = "gui.closeWorldPrompt";
         long number4 = 0L;
         if (flag2) {
            Set set6 = Ref.method4().method81().method30();
            synchronized (set6) {
               number4 = set6.stream().filter(HostedWorldPlayer::method2).count() - 1L;
            }

            if (number4 <= 0L) {
               flag2 = false;
            } else if (number4 > 1L) {
               text3 = "gui.closeWorldPromptPlural";
            }
         }

         if ((Boolean)Ref.method4().method41().method6().method53().get() || flag2) {
            GuiScreenBridge bridge5extension610 = Ref.method3().bridge$getCurrentScreen();
            highlightimpl141.setCancelled(true);
            ConfirmScreen bridge7iterator_27 = new ConfirmScreen(flag2 ? text3 : "gui.smartDisconnect", arg2x -> {
               if (!arg2x) {
                  Ref.method3().bridge$displayScreen(bridge5extension610);
               } else {
                  highlightimpl141.method1();
               }
            });
            if (flag2) {
               bridge7iterator_27.method7(new Object[]{number4});
            }

            Ref.method3().bridge$displayScreen(Bridge.method8().method18(bridge7iterator_27));
         }
      }
   }

   private void method6(EventOtherPlayerDamage highlightimpl15_21) {
      PlayerCombatState.method1(highlightimpl15_21.method1()).method3(Ref.method14());
   }

   private void method7(EventPlayerReceiveDamage highlightimpl101) {
      PlayerCombatState.method1(highlightimpl101.method1()).method3(Ref.method14());
      if (highlightimpl101.method1().equals(Ref.method7()) && (highlightimpl101.method2().bridge$isGeneric() || highlightimpl101.method2().bridge$isPlayerAttack())) {
         this.field2.method5(arg0 -> arg0.method3().increment());
         this.field2.method5(arg1x -> arg1x.method4().method1(this.field4));
         this.field4 = 0;
      }
   }

   private void method8(EventEntityStatus highlightimpl111) {
      if (highlightimpl111.method1() instanceof Bridge6_10
         && Ref.method7().bridge$getLastAttacker().isPresent()
         && ((EntityLivingBridge)Ref.method7().bridge$getLastAttacker().get()).equals(highlightimpl111.method1())
         && Math.abs(Ref.method7().bridge$getLastAttackerTime() - Ref.method7().OOHOIHICOCRORHHRHCRRROIHHIHHOH()) <= 4) {
         this.field4++;
         this.field2.method5(arg1x -> arg1x.method4().method1(this.field4));
      }
   }

   private void method9(com.moonsworth.lunar.client.event.combat.EventAttack highlightimpl21) {
      this.field2.method5(arg1x -> {
         MovingObjectPositionBridge horsestats212 = highlightimpl21.method1();
         boolean flag3 = horsestats212 == null || horsestats212.bridge$isTypeOfHit(MovingObjectTypeBridge.BLOCK) || horsestats212.bridge$isTypeOfHit(MovingObjectTypeBridge.MISS);
         if (flag3) {
            arg1x.method2().method3();
         } else if (horsestats212.bridge$getEntityHit() instanceof Bridge6_10) {
            arg1x.method2().method2();
         }

         if (field1) {
            if (flag3) {
               arg1x.method5().method3();
            } else if (horsestats212.bridge$getEntityHit() instanceof Bridge6_10) {
               arg1x.method5().method2();
            }
         }
      });
      field1 = false;
   }

   private void method10(EventPotionThrow highlightimpl1) {
      if (highlightimpl1.method1().bridge$getWorld() instanceof WorldBridgeExtension
         && highlightimpl1.method2() != null
         && highlightimpl1.method2().bridge$getItem() instanceof ItemPotionBridge bridge6extension22
         && bridge6extension22.bridge$hasEffect(highlightimpl1.method2())
         && highlightimpl1.method1().equals(Ref.method7())) {
         boolean flag4 = bridge6extension22.bridge$getEffects(highlightimpl1.method2())
            .stream()
            .anyMatch(arg1x -> arg1x.bridge$getPotionID() == Bridge.method36().method3().bridge$getID() && Bridge.method36().method14(highlightimpl1.method2()));
         if (flag4) {
            this.field2.method5(arg0 -> arg0.method9().increment());
         }
      }
   }
}
