package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashSet;
import org.joml.Vector2d;
import org.joml.Vector3i;

public abstract class TrackedBlockHighlight extends AbstractFeature {
   private final HashSet<Vector3i> field8 = new HashSet<>();
   private final Vector2d field9;
   private final int field10;

   public TrackedBlockHighlight(Vector2d vector2d1, int number2) {
      super(true);
      this.field9 = vector2d1;
      this.field10 = number2;
      this.handle(EventTick.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method5);
      this.handle(EventSpawnParticle.class, this::method1);
      this.method51(this::onDisable);
   }

   protected abstract void method1(EventSpawnParticle highlightimpl151);

   protected abstract boolean method2(Vector3i vector3i1);

   private void onDisable() {
      this.field8.clear();
   }

   protected void method3(EventTick highlightimpl21) {
      ArrayList list2 = new ArrayList();

      for (Vector3i vector3i4 : this.field8) {
         if (!this.method2(vector3i4)) {
            list2.add(vector3i4);
         }
      }

      for (Vector3i vector3i6 : list2) {
         this.field8.remove(vector3i6);
      }
   }

   protected void method4(Vector3i vector3i1) {
      this.field8.add(vector3i1);
   }

   protected void method5(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      double value2 = this.field9.get(0);
      double value4 = this.field9.get(1);
      EntityRenderDispatcherBridge bridge2_436 = Ref.method13();
      AbstractRenderContext bridgeextension_97 = highlightimpl21.method3();
      bridgeextension_97.push();
      bridgeextension_97.translate(-bridge2_436.bridge$renderPosX(), -bridge2_436.bridge$renderPosY(), -bridge2_436.bridge$renderPosZ());

      for (Vector3i vector3i9 : this.field8) {
         WorldRenderUtils.drawFancyBox(
            highlightimpl21.method3(),
            AxisAlignedBBBridge.method2(
                  vector3i9.x + 0.5 - value2 / 2.0, vector3i9.y, vector3i9.z + 0.5 - value2 / 2.0, vector3i9.x + 0.5 + value2 / 2.0, vector3i9.y + value4, vector3i9.z + 0.5 + value2 / 2.0
               )
               .method11(0.01F),
            this.field10,
            false
         );
      }

      bridgeextension_97.pop();
   }
}
