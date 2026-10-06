package com.moonsworth.lunar.client.guiRewindhandlers.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArrowBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_3;
import com.moonsworth.lunar.bridge.LocalPlayerMarker;
import com.moonsworth.lunar.client.network.server.PingEntryConfig;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.combat.ProjectileBaseEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.HashSet;
import java.util.Set;

public class GuiRewindhandlers2 implements EventRegistrar {
   private final PingEntryConfig field1 = ThreadModuleDump63.method4().method80();
   public static Set<BridgeExtension> field2 = new HashSet<>();
   private static final IntSet field3 = new IntOpenHashSet();

   public GuiRewindhandlers2() {
      this.handle(ProjectileBaseEvent.EventProjectileImpact.class, this::method2);
      this.handle(ProjectileBaseEvent.EventProjectileLaunch.class, this::method3);
      this.handle(ProjectileBaseEvent.EventProjectileRemoval.class, this::method4);
   }

   public static boolean method1(BridgeExtension var0) {
      return field3.contains(var0.bridge$getEntityId());
   }

   private void method2(ProjectileBaseEvent.EventProjectileImpact var1) {
      field3.remove(var1.method1().bridge$getEntityId());
      if (!(var1.method1() instanceof LocalPlayerMarker)
         && var1.method1() instanceof EntityArrowBridge
         && field2.remove(var1.method1())) {
         if (var1.method2() == null) {
            this.field1.method5(var0 -> var0.method11().method3());
         } else {
            this.field1.method5(var0 -> var0.method11().method2());
         }
      }
   }

   private void method3(ProjectileBaseEvent.EventProjectileLaunch var1) {
      BridgeExtension2_3 var2 = var1.method1();
      BridgeExtension var3 = var1.method2();
      if (var3 != null) {
         double var4 = Math.abs(var2.bridge$getPosX() - var3.bridge$getPosX());
         double var6 = Math.abs(var2.bridge$getPosZ() - var3.bridge$getPosZ());
         if (!(var4 > 4.0) && !(var6 > 4.0)) {
            if (var3.equals(ThreadModuleDump63.method7()) && var2 instanceof EntityArrowBridge) {
               field2.add(var2);
               field3.add(var2.bridge$getEntityId());
            }
         }
      }
   }

   private void method4(ProjectileBaseEvent.EventProjectileRemoval var1) {
      field3.remove(var1.method1().bridge$getEntityId());
      if (!(var1.method1() instanceof LocalPlayerMarker) && var1.method1() instanceof EntityArrowBridge) {
         field2.remove(var1.method1());
      }
   }
}
