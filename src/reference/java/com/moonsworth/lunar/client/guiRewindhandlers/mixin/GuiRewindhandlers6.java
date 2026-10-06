package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.lunarclient.apollo.module.serverrule.ServerRuleModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge6Extension2;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectHitType;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.coordinates.Gui2Handler;
import com.moonsworth.lunar.client.network.server.PingEntryConfig;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.OutcomeEvent;
import com.moonsworth.lunar.client.event.combat.PotionThrowEvent;
import com.moonsworth.lunar.client.event.combat.EventPlayerDamaged;
import com.moonsworth.lunar.client.event.entity.EventEntityStatusUpdate;
import com.moonsworth.lunar.client.event.combat.OtherPlayerDamageEvent;
import com.moonsworth.lunar.client.event.player.EventItemRightClick;
import com.moonsworth.lunar.client.event.player.EventUseItemFinish;
import com.moonsworth.lunar.client.event.entity.EventEntityHealthChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle;
import com.moonsworth.lunar.client.event.mixin.gui.ScreenActionEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.CrosshairRenderEvent;
import com.moonsworth.lunar.client.hitcolor.FogHandler;
import com.moonsworth.lunar.client.profile.ModProfile;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.util.ThreadModuleDump3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Set;

public class GuiRewindhandlers6 implements EventRegistrar {
   public static boolean field1 = false;
   private final PingEntryConfig field2 = ThreadModuleDump63.method4().method80();
   private long field3 = 0L;
   private int field4 = 0;

   public GuiRewindhandlers6() {
      this.handle(EventWorldLifecycle.EventWorldChanged.class, var1 -> {
         long var2 = System.currentTimeMillis();
         if (this.field3 == 0L || var2 - this.field3 >= 150L) {
            this.field3 = var2;
         }
      });
      this.handle(
         CrosshairRenderEvent.class,
         var0 -> {
            if (ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView() != 0
               && !ThreadModuleDump63.method4().method40().method33().method13().get()) {
               var0.method2(OutcomeEvent.Type.DENY);
            }
         }
      );
      this.handle(ScreenActionEvent.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick.class, this::method4);
      this.handle(OtherPlayerDamageEvent.class, this::method6);
      this.handle(EventPlayerDamaged.class, this::method7);
      this.handle(com.moonsworth.lunar.client.event.combat.EventMouseOverAttack.class, this::method9);
      this.handle(EventEntityStatusUpdate.class, this::method8);
      this.handle(EventEntityHealthChange.class, this::method3);
      this.handle(EventUseItemFinish.class, this::method2);
      this.handle(PotionThrowEvent.class, this::method10);
      this.handle(EventItemRightClick.class, this::method1);
   }

   private void method1(EventItemRightClick var1) {
      if (var1.method1().equals(ThreadModuleDump63.method7())) {
         Bridge6_4 var2 = var1.method2().bridge$getItem();
         if (var2 == null) {
            return;
         }

         String var3 = var2.bridge$getRegistryName();
         if (var3.equals("minecraft:egg") || var3.equals("minecraft:snowball")) {
            this.field2.method5(var0 -> var0.method13().increment());
         }

         if (var3.equals("minecraft:ender_pearl")
            && (!var1.method1().bridge$getPlayerCapabilities().bridge$isCreativeMode() || Bridge.getMinecraftVersion().method20())) {
            this.field2.method5(var0 -> var0.method14().increment());
         }
      }
   }

   private void method2(EventUseItemFinish var1) {
      if (var1.method1().bridge$getWorld() instanceof Itemcounter6Extension && var1.method1().equals(ThreadModuleDump63.method7())) {
         if (var1.method2() == null) {
            return;
         }

         Bridge6_4 var2 = var1.method2().bridge$getItem();
         if (var2.bridge$getRegistryName().equals("minecraft:golden_apple") || var2.bridge$getRegistryName().equals("minecraft:enchanted_golden_apple")) {
            this.field2.method5(var0 -> var0.method8().increment());
         }

         var1.method2().bridge$getFood().ifPresent(var2x -> this.field2.method5(var2xx -> var2xx.method10().method1(var2x.bridge$getHealing(var1.method2()))));
      }
   }

   private void method3(EventEntityHealthChange var1) {
      if (var1.method1().equals(ThreadModuleDump63.method7())) {
         if (var1.method3() < var1.method2()) {
            this.field2.method5(var1x -> var1x.method6().method1(var1.method2() - var1.method3()));
         } else {
            this.field2.method5(var1x -> var1x.method7().method1(var1.method3() - var1.method2()));
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick var1) {
      if (ThreadModuleDump63.method7() != null && NickHider.realSkinLocation == null) {
         ThreadModuleDump63.method7().bridge$loadAndGetRealSkinType().ifPresent(var0 -> NickHider.realSkinLocation = var0);
      }

      if (this.field3 != 0L && System.currentTimeMillis() - this.field3 >= 1000L) {
         this.field3 = 0L;
         ThreadModuleDump3.method1();
         if (ThreadModuleDump63.method3().bridge$getCurrentServerData() != null) {
            String var2 = ThreadModuleDump63.method3().bridge$getCurrentServerData().bridge$serverIP().toLowerCase();
            if (ThreadModuleDump3.getServer() != null) {
               var2 = ThreadModuleDump3.getServer();
            }

            var2 = var2.toLowerCase();
            ModProfile var3 = null;

            for (ModProfile var5 : Client.method109().method61().method2()) {
               if (!var5.getServer().isEmpty()) {
                  if (Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
                     var3 = var5;
                  } else if (var2.endsWith(var5.getServer().toLowerCase())) {
                     Client.method109().method61().method3(var5);
                     var3 = null;
                     break;
                  }
               }
            }

            if (var3 != null) {
               Client.method109().method61().method3(var3);
            }
         }
      }
   }

   private void method5(ScreenActionEvent var1) {
      if (!ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(ServerRuleModule.class)
         .filter(var0 -> (Boolean)var0.getOptions().get(ServerRuleModule.COMPETITIVE_GAME))
         .isPresent()) {
         boolean var2 = ThreadModuleDump63.method4().method81().method28() != null;
         String var3 = "gui.closeWorldPrompt";
         long var4 = 0L;
         if (var2) {
            Set var6 = ThreadModuleDump63.method4().method81().method30();
            synchronized (var6) {
               var4 = var6.stream().filter(Gui2Handler::method2).count() - 1L;
            }

            if (var4 <= 0L) {
               var2 = false;
            } else if (var4 > 1L) {
               var3 = "gui.closeWorldPromptPlural";
            }
         }

         if (ThreadModuleDump63.method4().method41().method6().method53().get() || var2) {
            Bridge5Extension6 var10 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
            var1.setCancelled(true);
            ConfirmScreen var7 = new ConfirmScreen(var2 ? var3 : "gui.smartDisconnect", var2x -> {
               if (!var2x) {
                  ThreadModuleDump63.method3().bridge$displayScreen(var10);
               } else {
                  var1.method1();
               }
            });
            if (var2) {
               var7.method7(var4);
            }

            ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var7));
         }
      }
   }

   private void method6(OtherPlayerDamageEvent var1) {
      FogHandler.method1(var1.method1()).method3(ThreadModuleDump63.method14());
   }

   private void method7(EventPlayerDamaged var1) {
      FogHandler.method1(var1.method1()).method3(ThreadModuleDump63.method14());
      if (var1.method1().equals(ThreadModuleDump63.method7()) && (var1.method2().bridge$isGeneric() || var1.method2().bridge$isPlayerAttack())) {
         this.field2.method5(var0 -> var0.method3().increment());
         this.field2.method5(var1x -> var1x.method4().method1(this.field4));
         this.field4 = 0;
      }
   }

   private void method8(EventEntityStatusUpdate var1) {
      if (var1.method1() instanceof Bridge6_10
         && ThreadModuleDump63.method7().bridge$getLastAttacker().isPresent()
         && ThreadModuleDump63.method7().bridge$getLastAttacker().get().equals(var1.method1())
         && Math.abs(ThreadModuleDump63.method7().bridge$getLastAttackerTime() - ThreadModuleDump63.method7().OOHOIHICOCRORHHRHCRRROIHHIHHOH()) <= 4) {
         this.field4++;
         this.field2.method5(var1x -> var1x.method4().method1(this.field4));
      }
   }

   private void method9(com.moonsworth.lunar.client.event.combat.EventMouseOverAttack var1) {
      this.field2.method5(var1x -> {
         MovingObjectPositionHitResult var2 = var1.method1();
         boolean var3 = var2 == null || var2.bridge$isTypeOfHit(MovingObjectHitType.BLOCK) || var2.bridge$isTypeOfHit(MovingObjectHitType.MISS);
         if (var3) {
            var1x.method2().method3();
         } else if (var2.bridge$getEntityHit() instanceof Bridge6_10) {
            var1x.method2().method2();
         }

         if (field1) {
            if (var3) {
               var1x.method5().method3();
            } else if (var2.bridge$getEntityHit() instanceof Bridge6_10) {
               var1x.method5().method2();
            }
         }
      });
      field1 = false;
   }

   private void method10(PotionThrowEvent var1) {
      if (var1.method1().bridge$getWorld() instanceof Itemcounter6Extension
         && var1.method2() != null
         && var1.method2().bridge$getItem() instanceof Bridge6Extension2 var2
         && var2.bridge$hasEffect(var1.method2())
         && var1.method1().equals(ThreadModuleDump63.method7())) {
         boolean var4 = var2.bridge$getEffects(var1.method2())
            .stream()
            .anyMatch(var1x -> var1x.bridge$getPotionID() == Bridge.method36().method3().bridge$getID() && Bridge.method36().method14(var1.method2()));
         if (var4) {
            this.field2.method5(var0 -> var0.method9().increment());
         }
      }
   }
}
