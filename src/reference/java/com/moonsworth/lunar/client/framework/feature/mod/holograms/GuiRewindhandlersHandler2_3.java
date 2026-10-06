package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ArmorStandBridge;
import com.moonsworth.lunar.client.event.entity.EventEntityRemoval;
import com.moonsworth.lunar.client.event.player.EventPlayerRemoval;
import com.moonsworth.lunar.client.event.entity.EventEntityWorldJoin;
import com.moonsworth.lunar.client.event.entity.EntitySpawnEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpIterator;
import java.util.Iterator;
import lombok.Generated;

public class GuiRewindhandlersHandler2_3 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final ParentTImpl3 field7 = new ParentTImpl3(
      () -> (Iterator<BridgeExtension>)(ThreadModuleDump63.method8() == null
         ? new ThreadModuleDumpIterator()
         : ThreadModuleDump63.method8().bridge$getEntities().iterator())
   );
   private final Holograms2<ArmorStandBridge> field8 = this.method6().method1(ArmorStandBridge.class);

   public GuiRewindhandlersHandler2_3() {
      this.handle(EventEntityWorldJoin.class, this::method1);
      this.handle(EventEntityRemoval.class, this::method2);
      this.handle(EventPlayerRemoval.class, this::method3);
      this.handle(EntitySpawnEvent.class, this::method4);
      this.handle(EventWorldChanged.class, this::method5);
   }

   protected void onEnable() {
      if (ThreadModuleDump63.method8() != null && this.field7.isEnabled()) {
         for (BridgeExtension var2 : ThreadModuleDump63.method8().bridge$getEntities()) {
            this.field7.method1(var2);
         }
      }
   }

   protected void onDisable() {
      this.field7.clear();
   }

   private void method1(EventEntityWorldJoin var1) {
      this.field7.method1(var1.field1);
   }

   private void method2(EventEntityRemoval var1) {
      this.field7.method2(var1.method1());
   }

   private void method3(EventPlayerRemoval var1) {
      this.field7.method2(var1.method1());
   }

   private void method4(EntitySpawnEvent var1) {
      this.field7.method3(var1.field1);
   }

   private void method5(EventWorldChanged var1) {
      this.field7.clear();
   }

   public Holograms2<BridgeExtension> method6() {
      return this.field7;
   }

   @Generated
   public Holograms2<ArmorStandBridge> method7() {
      return this.field8;
   }
}
