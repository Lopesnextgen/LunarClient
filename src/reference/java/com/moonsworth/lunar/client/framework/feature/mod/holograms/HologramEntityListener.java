package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.collection.EmptyIterator;
import java.util.Iterator;
import lombok.Generated;

public class HologramEntityListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final IterableEntityTracker field7 = new IterableEntityTracker(
      () -> (Iterator<BridgeExtension>)(Ref.method8() == null
         ? new EmptyIterator()
         : Ref.method8().bridge$getEntities().iterator())
   );
   private final EntityQuery<EntityArmorStandBridge> field8 = this.method6().method1(EntityArmorStandBridge.class);

   public HologramEntityListener() {
      this.handle(EventEntityJoinWorld.class, this::method1);
      this.handle(EventEntityRemove.class, this::method2);
      this.handle(EventPlayerRemove.class, this::method3);
      this.handle(EventEntitySpawn.class, this::method4);
      this.handle(EventWorldChange.class, this::method5);
   }

   protected void onEnable() {
      if (Ref.method8() != null && this.field7.isEnabled()) {
         for (BridgeExtension bridgeextension2 : Ref.method8().bridge$getEntities()) {
            this.field7.method1(bridgeextension2);
         }
      }
   }

   protected void onDisable() {
      this.field7.clear();
   }

   private void method1(EventEntityJoinWorld highlightimpl201) {
      this.field7.method1(highlightimpl201.field1);
   }

   private void method2(EventEntityRemove highlightimpl121) {
      this.field7.method2(highlightimpl121.method1());
   }

   private void method3(EventPlayerRemove highlightimpl151) {
      this.field7.method2(highlightimpl151.method1());
   }

   private void method4(EventEntitySpawn highlightimpl6_21) {
      this.field7.method3(highlightimpl6_21.field1);
   }

   private void method5(EventWorldChange data31) {
      this.field7.clear();
   }

   public EntityQuery<BridgeExtension> method6() {
      return this.field7;
   }

   @Generated
   public EntityQuery<EntityArmorStandBridge> method7() {
      return this.field8;
   }
}
