package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator;

import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.HashSet;
import org.joml.Vector2d;
import org.joml.Vector3i;

public abstract class Calculator2 extends AbstractFeature {
   private final HashSet<Vector3i> field8 = new HashSet<>();
   private final Vector2d field9;
   private final int field10;

   public Calculator2(Vector2d var1, int var2) {
      super(true);
      this.field9 = var1;
      this.field10 = var2;
      this.handle(EventClientTick.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method5);
      this.handle(EventParticleSpawn.class, this::method1);
      this.OHROCHICOIOICHOCRROORRCIIICIHO(this::onDisable);
   }

   protected abstract void method1(EventParticleSpawn var1);

   protected abstract boolean method2(Vector3i var1);

   private void onDisable() {
      this.field8.clear();
   }

   protected void method3(EventClientTick var1) {
      ArrayList var2 = new ArrayList();

      for (Vector3i var4 : this.field8) {
         if (!this.method2(var4)) {
            var2.add(var4);
         }
      }

      for (Vector3i var6 : var2) {
         this.field8.remove(var6);
      }
   }

   protected void method4(Vector3i var1) {
      this.field8.add(var1);
   }

   protected void method5(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent var1) {
      double var2 = this.field9.get(0);
      double var4 = this.field9.get(1);
      Bridge2_43 var6 = ThreadModuleDump63.method13();
      AbstractRenderContext var7 = var1.method3();
      var7.push();
      var7.translate(-var6.bridge$renderPosX(), -var6.bridge$renderPosY(), -var6.bridge$renderPosZ());

      for (Vector3i var9 : this.field8) {
         Click.drawBlockOutline(
            var1.method3(),
            AxisAlignedBBBridge.method2(
                  var9.x + 0.5 - var2 / 2.0, var9.y, var9.z + 0.5 - var2 / 2.0, var9.x + 0.5 + var2 / 2.0, var9.y + var4, var9.z + 0.5 + var2 / 2.0
               )
               .method11(0.01F),
            this.field10,
            false
         );
      }

      var7.pop();
   }
}
