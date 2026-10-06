package com.moonsworth.lunar.client.framework.feature.killsounds.mixin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityArrowBridge;
import com.moonsworth.lunar.bridge.EntityFishHookBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectHitType;
import com.moonsworth.lunar.client.framework.feature.killsounds.KillsoundsType;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.event.combat.EventMouseOverAttack;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent.EventProjectileLaunch;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent.EventProjectileImpact;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent.EventProjectileRemoval;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldLoaded;
import com.moonsworth.lunar.client.event.mixin.gui.TitleEvent;
import com.moonsworth.lunar.client.mod.combat.killsounds.KillSounds;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3f;

public class Killsounds {
   private final Set<Integer> field1 = new HashSet<>();
   private final Cache<String, Killsounds2> field2 = CacheBuilder.newBuilder().expireAfterWrite(15L, TimeUnit.SECONDS).build();
   private final List<Killsounds4> field3 = new ArrayList<>();
   private final KillSounds field4;

   public Killsounds(KillSounds var1) {
      this.field4 = var1;
      Killsounds4Loader var2 = new Killsounds4Loader();
      this.field3.add(new Killsounds4Loader2());
      this.field3.add(var2);
      ThreadModuleDump37.method4(var2::method1);
   }

   public void method1(EventWorldLoaded var1) {
      this.field2.invalidateAll();
      this.field1.clear();
   }

   public void method2(EventProjectileLaunch var1) {
      BridgeExtension2_3 var2 = var1.method1();
      Bridge5Extension_5 var3 = ThreadModuleDump63.method3().bridge$getPlayer();
      if (var3.equals(var1.method2())) {
         this.field1.add(var2.bridge$getEntityId());
      } else {
         Vector3d var4 = new Vector3d(var2.bridge$getPosX(), var2.bridge$getPosY(), var2.bridge$getPosZ());
         Vector3d var5 = new Vector3d(var3.bridge$getPosX(), var3.bridge$getPosY(), var3.bridge$getPosZ());
         double var6 = 3.0;
         if (var5.distanceSquared(var4) <= var6 * var6) {
            this.field1.add(var2.bridge$getEntityId());
         }
      }
   }

   public void method3(EventProjectileImpact var1) {
      BridgeExtension2_3 var2 = var1.method1();
      if (this.field1.remove(var2.bridge$getEntityId())) {
         if (var1.method2() instanceof Bridge6_10 var4) {
            this.method11(var4, new Killsounds2(Killsounds2.Type.ARROW));
         } else {
            this.method9(var2);
         }
      }
   }

   public void method4(EventProjectileRemoval var1) {
      BridgeExtension2_3 var2 = var1.method1();
      if (this.field1.remove(var2.bridge$getEntityId())) {
         this.method9(var2);
      }
   }

   public void method5(EventMouseOverAttack var1) {
      MovingObjectPositionHitResult var2 = var1.method1();
      boolean var3 = var2 != null && var2.bridge$isTypeOfHit(MovingObjectHitType.ENTITY);
      if (var3 && var2.bridge$getEntityHit() instanceof Bridge6_10 var4 && var4.bridge$getHurtTime() == 0) {
         this.method11(var4, new Killsounds2(Killsounds2.Type.MELEE));
      }
   }

   public void method6(Data var1) {
      ThreadModuleDump37.method8()
         .execute(
            () -> this.method10(ThreadModuleDump63.method3().bridge$getPlayer())
               .stream()
               .map(var2 -> this.method8(var2, var1))
               .flatMap(Optional::stream)
               .forEach(this::method12)
         );
   }

   public void method7(TitleEvent var1) {
      String var2 = AdventureTextBridge.getTextContent(var1.getTitle());
      boolean var3 = GuiRewindhandlersHandler23.field7.method7().field2.equals("BEDWARS");
      if (var3 && var2.contains("VICTORY!")) {
         this.field4.method6(KillsoundsType.BEDWARS_WIN);
      }
   }

   private Optional<String> method8(String var1, Data var2) {
      return this.field3.stream().map(var2x -> var2x.parseTargetName(var1, var2)).flatMap(Optional::stream).findFirst();
   }

   private void method9(BridgeExtension2_3 var1) {
      Killsounds2.Type var2;
      if (var1 instanceof EntityArrowBridge) {
         var2 = Killsounds2.Type.ARROW;
      } else if (var1 instanceof EntityFishHookBridge) {
         var2 = Killsounds2.Type.ROD;
      } else {
         var2 = Killsounds2.Type.THROWABLE;
      }

      Killsounds2 var3 = new Killsounds2(var2);
      Vector3d var4 = new Vector3d(var1.bridge$getPosX(), var1.bridge$getPosY(), var1.bridge$getPosZ());
      Vector3d var5 = var4.add(var1.bridge$getMotionX(), var1.bridge$getMotionY(), var1.bridge$getMotionZ());
      AxisAlignedBBBridge var6 = var1.bridge$getBoundingBox().method7(new Vector3f((float)var5.x, (float)var5.y, (float)var5.z)).bridge$expand(1.0, 1.0, 1.0);

      for (Bridge6_10 var8 : ThreadModuleDump63.method3().bridge$getWorld().bridge$getPlayerEntities()) {
         AxisAlignedBBBridge var9 = var8.bridge$getBoundingBox().bridge$expand(1.0, 1.0, 1.0);
         if (var6.bridge$intersectsWith(var9)) {
            this.method11(var8, var3);
         }
      }
   }

   private List<String> method10(@Nullable Bridge6_10 var1) {
      if (var1 == null) {
         return Collections.emptyList();
      }

      ArrayList var2 = new ArrayList();
      ClientPacketListenerBridge var3 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
      if (var3 != null) {
         Bridge2_33 var4 = var3.bridge$getPlayerInfo(var1.bridge$getUniqueID());
         if (var4 != null) {
            String var5 = AdventureTextBridge.getTextContent(var4.bridge$getDisplayName());
            if (var5.isEmpty()) {
               var5 = var4.bridge$getGameProfile().getName();
            }

            var2.add(var5);
         }
      }

      String var6 = AdventureTextBridge.getTextContent(var1.bridge$getDisplayNameComponent());
      var2.add(var6);
      return var2;
   }

   private void method11(@NonNull Bridge6_10 var1, Killsounds2 var2) {
      if (var1 == null) {
         throw new NullPointerException("target is marked non-null but is null");
      }

      this.method10(var1).forEach(var2x -> this.field2.put(var2x, var2));
   }

   private void method12(String var1) {
      Killsounds2 var2 = (Killsounds2)this.field2.getIfPresent(var1);
      this.field2.invalidate(var1);
      if (var2 == null) {
         this.field4.method6(KillsoundsType.defaultType());
      } else {
         KillsoundsType var3 = this.method13(var2.method1());
         if (!this.field4.method6(var3)) {
            this.field4.method6(KillsoundsType.defaultType());
         }
      }
   }

   private KillsoundsType method13(Killsounds2.Type var1) {
      return switch (var1) {
         case MELEE -> KillsoundsType.MELEE_KILL;
         case ARROW -> KillsoundsType.BOW_KILL;
         case ROD -> KillsoundsType.ROD_KILL;
         case THROWABLE -> KillsoundsType.THROWABLE_KILL;
      };
   }
}
