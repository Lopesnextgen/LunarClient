package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArrowBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.LocalPlayerBridge;
import com.moonsworth.lunar.client.network.server.PingEntryConfig;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.HashSet;
import java.util.Set;

public class ProjectileTracker implements EventBusAccess {
   private final PingEntryConfig field1 = Ref.method4().method80();
   public static Set<BridgeExtension> field2 = new HashSet<>();
   private static final IntSet field3 = new IntOpenHashSet();

   public ProjectileTracker() {
      this.handle(EventProjectileBase.EventProjectileHit.class, this::method2);
      this.handle(EventProjectileBase.EventProjectileSpawn.class, this::method3);
      this.handle(EventProjectileBase.EventProjectileRemove.class, this::method4);
   }

   public static boolean method1(BridgeExtension bridgeextension0) {
      return field3.contains(bridgeextension0.bridge$getEntityId());
   }

   private void method2(EventProjectileBase.EventProjectileHit data81) {
      field3.remove(data81.IIHCRIOCIOCROOIRCHHORRORIOOCRC().bridge$getEntityId());
      if (!(data81.IIHCRIOCIOCROOIRCHHORRORIOOCRC() instanceof LocalPlayerBridge)
         && data81.IIHCRIOCIOCROOIRCHHORRORIOOCRC() instanceof EntityArrowBridge
         && field2.remove(data81.IIHCRIOCIOCROOIRCHHORRORIOOCRC())) {
         if (data81.method2() == null) {
            this.field1.method5(arg0 -> arg0.method11().method3());
         } else {
            this.field1.method5(arg0 -> arg0.method11().method2());
         }
      }
   }

   private void method3(EventProjectileBase.EventProjectileSpawn data71) {
      BridgeExtension2_3 bridgeextension2_32 = data71.IIHCRIOCIOCROOIRCHHORRORIOOCRC();
      BridgeExtension bridgeextension3 = data71.method2();
      if (bridgeextension3 != null) {
         double value4 = Math.abs(bridgeextension2_32.bridge$getPosX() - bridgeextension3.bridge$getPosX());
         double value6 = Math.abs(bridgeextension2_32.bridge$getPosZ() - bridgeextension3.bridge$getPosZ());
         if (!(value4 > 4.0) && !(value6 > 4.0)) {
            if (bridgeextension3.equals(Ref.method7()) && bridgeextension2_32 instanceof EntityArrowBridge) {
               field2.add(bridgeextension2_32);
               field3.add(bridgeextension2_32.bridge$getEntityId());
            }
         }
      }
   }

   private void method4(EventProjectileBase.EventProjectileRemove data91) {
      field3.remove(data91.IIHCRIOCIOCROOIRCHHORRORIOOCRC().bridge$getEntityId());
      if (!(data91.IIHCRIOCIOCROOIRCHHORRORIOOCRC() instanceof LocalPlayerBridge) && data91.IIHCRIOCIOCROOIRCHHORRORIOOCRC() instanceof EntityArrowBridge) {
         field2.remove(data91.IIHCRIOCIOCROOIRCHHORRORIOOCRC());
      }
   }
}
