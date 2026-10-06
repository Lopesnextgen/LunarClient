package com.moonsworth.lunar.client.framework.feature.killsounds;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.EntityArrowBridge;
import com.moonsworth.lunar.bridge.EntityFishHookBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectTypeBridge;
import com.moonsworth.lunar.client.framework.feature.killsounds.KillSoundType;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.combat.EventAttack;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileSpawn;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileHit;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileRemove;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldLoad;
import com.moonsworth.lunar.client.event.mixin.gui.EventTitle;
import com.moonsworth.lunar.client.mod.combat.killsounds.KillSounds;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
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

public class KillSoundTracker {
   private final Set<Integer> field1 = new HashSet<>();
   private final Cache<String, KillEvent> field2 = CacheBuilder.newBuilder().expireAfterWrite(15L, TimeUnit.SECONDS).build();
   private final List<KillMessageParser> field3 = new ArrayList<>();
   private final KillSounds field4;

   public KillSoundTracker(KillSounds killsounds1) {
      this.field4 = killsounds1;
      ChatPatternKillMessageParser killsounds4loader2 = new ChatPatternKillMessageParser();
      this.field3.add(new DeathMessageKillParser());
      this.field3.add(killsounds4loader2);
      BackgroundExecutor.method4(killsounds4loader2::method1);
   }

   public void method1(EventWorldLoad data41) {
      this.field2.invalidateAll();
      this.field1.clear();
   }

   public void method2(EventProjectileSpawn data71) {
      BridgeExtension2_3 bridgeextension2_32 = data71.IIHCRIOCIOCROOIRCHHORRORIOOCRC();
      Bridge5Extension_5 bridge5extension_53 = Ref.method3().bridge$getPlayer();
      if (bridge5extension_53.equals(data71.method2())) {
         this.field1.add(bridgeextension2_32.bridge$getEntityId());
      } else {
         Vector3d vector3d4 = new Vector3d(bridgeextension2_32.bridge$getPosX(), bridgeextension2_32.bridge$getPosY(), bridgeextension2_32.bridge$getPosZ());
         Vector3d vector3d5 = new Vector3d(bridge5extension_53.bridge$getPosX(), bridge5extension_53.bridge$getPosY(), bridge5extension_53.bridge$getPosZ());
         double value6 = 3.0;
         if (vector3d5.distanceSquared(vector3d4) <= value6 * value6) {
            this.field1.add(bridgeextension2_32.bridge$getEntityId());
         }
      }
   }

   public void method3(EventProjectileHit data81) {
      BridgeExtension2_3 bridgeextension2_32 = data81.IIHCRIOCIOCROOIRCHHORRORIOOCRC();
      if (this.field1.remove(bridgeextension2_32.bridge$getEntityId())) {
         if (data81.method2() instanceof Bridge6_10 bridge6_104) {
            this.method11(bridge6_104, new KillEvent(KillEvent.Type.ARROW));
         } else {
            this.method9(bridgeextension2_32);
         }
      }
   }

   public void method4(EventProjectileRemove data91) {
      BridgeExtension2_3 bridgeextension2_32 = data91.IIHCRIOCIOCROOIRCHHORRORIOOCRC();
      if (this.field1.remove(bridgeextension2_32.bridge$getEntityId())) {
         this.method9(bridgeextension2_32);
      }
   }

   public void method5(EventAttack highlightimpl21) {
      MovingObjectPositionBridge horsestats212 = highlightimpl21.method1();
      boolean flag3 = horsestats212 != null && horsestats212.bridge$isTypeOfHit(MovingObjectTypeBridge.ENTITY);
      if (flag3 && horsestats212.bridge$getEntityHit() instanceof Bridge6_10 bridge6_104 && bridge6_104.bridge$getHurtTime() == 0) {
         this.method11(bridge6_104, new KillEvent(KillEvent.Type.MELEE));
      }
   }

   public void method6(TypedChatMessage data1) {
      BackgroundExecutor.method8()
         .execute(
            () -> this.method10(Ref.method3().bridge$getPlayer())
               .stream()
               .map(arg2 -> this.method8(arg2, data1))
               .flatMap(Optional::stream)
               .forEach(this::method12)
         );
   }

   public void method7(EventTitle highlightimpl41) {
      String text2 = TextBridge.getTextContent(highlightimpl41.getTitle());
      boolean flag3 = HypixelLocationListener.field7.method7().field2.equals("BEDWARS");
      if (flag3 && text2.contains("VICTORY!")) {
         this.field4.method6(KillSoundType.BEDWARS_WIN);
      }
   }

   private Optional<String> method8(String text1, TypedChatMessage data2) {
      return this.field3.stream().map(arg2x -> arg2x.parseTargetName(text1, data2)).flatMap(Optional::stream).findFirst();
   }

   private void method9(BridgeExtension2_3 bridgeextension2_31) {
      KillEvent.Type type2;
      if (bridgeextension2_31 instanceof EntityArrowBridge) {
         type2 = KillEvent.Type.ARROW;
      } else if (bridgeextension2_31 instanceof EntityFishHookBridge) {
         type2 = KillEvent.Type.ROD;
      } else {
         type2 = KillEvent.Type.THROWABLE;
      }

      KillEvent killsounds23 = new KillEvent(type2);
      Vector3d vector3d4 = new Vector3d(bridgeextension2_31.bridge$getPosX(), bridgeextension2_31.bridge$getPosY(), bridgeextension2_31.bridge$getPosZ());
      Vector3d vector3d5 = vector3d4.add(bridgeextension2_31.bridge$getMotionX(), bridgeextension2_31.bridge$getMotionY(), bridgeextension2_31.bridge$getMotionZ());
      AxisAlignedBBBridge horsestats126 = bridgeextension2_31.bridge$getBoundingBox().method7(new Vector3f((float)vector3d5.x, (float)vector3d5.y, (float)vector3d5.z)).bridge$expand(1.0, 1.0, 1.0);

      for (Bridge6_10 bridge6_108 : Ref.method3().bridge$getWorld().bridge$getPlayerEntities()) {
         AxisAlignedBBBridge horsestats129 = bridge6_108.bridge$getBoundingBox().bridge$expand(1.0, 1.0, 1.0);
         if (horsestats126.bridge$intersectsWith(horsestats129)) {
            this.method11(bridge6_108, killsounds23);
         }
      }
   }

   private List<String> method10(@Nullable Bridge6_10 bridge6_101) {
      if (bridge6_101 == null) {
         return Collections.emptyList();
      }

      ArrayList list2 = new ArrayList();
      NetHandlerPlayClientBridge bridgeextension_73 = Ref.method3().bridge$getClientPacketListener();
      if (bridgeextension_73 != null) {
         PlayerInfoBridge bridge2_334 = bridgeextension_73.bridge$getPlayerInfo(bridge6_101.bridge$getUniqueID());
         if (bridge2_334 != null) {
            String text5 = TextBridge.getTextContent(bridge2_334.bridge$getDisplayName());
            if (text5.isEmpty()) {
               text5 = bridge2_334.bridge$getGameProfile().getName();
            }

            list2.add(text5);
         }
      }

      String text6 = TextBridge.getTextContent(bridge6_101.bridge$getDisplayNameComponent());
      list2.add(text6);
      return list2;
   }

   private void method11(@NonNull Bridge6_10 bridge6_101, KillEvent killsounds22) {
      if (bridge6_101 == null) {
         throw new NullPointerException("target is marked non-null but is null");
      }

      this.method10(bridge6_101).forEach(arg2x -> this.field2.put(arg2x, killsounds22));
   }

   private void method12(String text1) {
      KillEvent killsounds22 = (KillEvent)this.field2.getIfPresent(text1);
      this.field2.invalidate(text1);
      if (killsounds22 == null) {
         this.field4.method6(KillSoundType.defaultType());
      } else {
         KillSoundType killsoundstype3 = this.method13(killsounds22.method1());
         if (!this.field4.method6(killsoundstype3)) {
            this.field4.method6(KillSoundType.defaultType());
         }
      }
   }

   private KillSoundType method13(KillEvent.Type type1) {
      return switch (type1) {
         case MELEE -> KillSoundType.MELEE_KILL;
         case ARROW -> KillSoundType.BOW_KILL;
         case ROD -> KillSoundType.ROD_KILL;
         case THROWABLE -> KillSoundType.THROWABLE_KILL;
      };
   }
}
