package com.moonsworth.lunar.client.framework.feature.mod.impl.burrow;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.mixin.BurrowKind;
import com.moonsworth.lunar.client.mod.skyblock.burrowlocating.SkyblockBurrowLocating;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Objects;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.joml.Vector3i;

public class Burrow {
   private final Vector3i field1;
   private BurrowKind field2;
   private long field3;

   public Burrow(Vector3i vector3i1) {
      this.field1 = vector3i1;
      this.field2 = BurrowKind.MOB;
      this.field3 = Ref.method3().bridge$getSystemTime();
   }

   public Burrow(Vector3i vector3i1, ParticleType particleType) {
      this(vector3i1);
      this.method4(particleType);
   }

   public boolean method1() {
      return Ref.method3().bridge$getSystemTime() - this.field3 > 1000L;
   }

   public double method2(Vector3i vector3i1) {
      return this.field1.distanceSquared(vector3i1);
   }

   public void method3(AbstractRenderContext bridgeextension_91, SkyblockBurrowLocating skyblockburrowlocating2) {
      EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
      bridgeextension_91.push();
      bridgeextension_91.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
      int number4 = BurrowKind.getColor(skyblockburrowlocating2, this.field2);
      WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_91, this.field1, ColorUtils.method22(number4, 34), true);
      if ((Boolean)skyblockburrowlocating2.method24().get()) {
         WorldRenderUtils.fadingBoxBeam(bridgeextension_91, this.field1, number4);
      }

      String text5 = "Unknown";
      if (this.field2 != null) {
         text5 = switch (this.field2) {
            case START -> "Start";
            case MOB -> "Mob";
            case TREASURE -> "Treasure";
         };
      }

      double value6 = this.field1.x() + 0.5;
      double value8 = this.field1.y() + 1.5;
      double value10 = this.field1.z() + 0.5;
      WorldRenderUtils.drawComponent(bridgeextension_91, Component.text(text5), value6, value8, value10, true, 1.0F, true);
      bridgeextension_91.pop();
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else if (object != null && this.getClass() == object.getClass()) {
         Burrow burrow2 = (Burrow)object;
         return this.field1.equals(burrow2.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1);
   }

   public void method4(ParticleType particleType) {
      switch (particleType) {
         case CRIT:
            this.field3 = Ref.method3().bridge$getSystemTime();
            break;
         case DRIPPING_LAVA:
            this.field3 = Ref.method3().bridge$getSystemTime();
            this.field2 = BurrowKind.TREASURE;
            break;
         case ENCHANTED_HIT:
            this.field3 = Ref.method3().bridge$getSystemTime();
            this.field2 = BurrowKind.START;
      }
   }

   @Generated
   public Vector3i method5() {
      return this.field1;
   }

   @Generated
   public BurrowKind method6() {
      return this.field2;
   }

   @Generated
   public long method7() {
      return this.field3;
   }
}
