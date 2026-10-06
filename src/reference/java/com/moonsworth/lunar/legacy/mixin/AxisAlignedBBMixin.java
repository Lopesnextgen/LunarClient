package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AxisAlignedBB.class)
public abstract class AxisAlignedBBMixin implements AxisAlignedBBBridge {
   @Final
   @Shadow
   public double minX;
   @Final
   @Shadow
   public double minY;
   @Final
   @Shadow
   public double minZ;
   @Final
   @Shadow
   public double maxX;
   @Final
   @Shadow
   public double maxY;
   @Final
   @Shadow
   public double maxZ;

   public AxisAlignedBBMixin() {
   }

   @Shadow
   public abstract double calculateXOffset(AxisAlignedBB box1, double value2);

   @Shadow
   public abstract double calculateYOffset(AxisAlignedBB box1, double value2);

   @Shadow
   public abstract double calculateZOffset(AxisAlignedBB box1, double value2);

   @Shadow
   public abstract AxisAlignedBB offset(double value1, double value3, double value5);

   @Shadow
   public abstract AxisAlignedBB expand(double value1, double value3, double value5);

   @Shadow
   public abstract boolean intersectsWith(AxisAlignedBB box1);

   @Shadow
   public abstract boolean intersects$v1_12(AxisAlignedBB box1);

   @Shadow
   public abstract boolean hasNaN();

   @Shadow
   public abstract AxisAlignedBB union(AxisAlignedBB box1);

   public double bridge$getMinX() {
      return this.minX;
   }

   public double bridge$getMinY() {
      return this.minY;
   }

   public double bridge$getMinZ() {
      return this.minZ;
   }

   public double bridge$getMaxX() {
      return this.maxX;
   }

   public double bridge$getMaxY() {
      return this.maxY;
   }

   public double bridge$getMaxZ() {
      return this.maxZ;
   }

   public AxisAlignedBBBridge bridge$expand(double value1, double value3, double value5) {
      if (Ref.MC_VERSION >= 1) {
         return (AxisAlignedBBBridge)this.expand(value1, value3, value5);
      }

      double value7 = this.minX;
      double value9 = this.minY;
      double value11 = this.minZ;
      double value13 = this.maxX;
      double value15 = this.maxY;
      double value17 = this.maxZ;
      if (value1 < 0.0) {
         value7 += value1;
      } else if (value1 > 0.0) {
         value13 += value1;
      }

      if (value3 < 0.0) {
         value9 += value3;
      } else if (value3 > 0.0) {
         value15 += value3;
      }

      if (value5 < 0.0) {
         value11 += value5;
      } else if (value5 > 0.0) {
         value17 += value5;
      }

      return (AxisAlignedBBBridge)(new AxisAlignedBB(value7, value9, value11, value13, value15, value17));
   }

   public AxisAlignedBBBridge bridge$offset(double value1, double value3, double value5) {
      return (AxisAlignedBBBridge)this.offset(value1, value3, value5);
   }

   public AxisAlignedBBBridge bridge$union(AxisAlignedBBBridge horsestats121) {
      return (AxisAlignedBBBridge)this.union((AxisAlignedBB)horsestats121);
   }

   public boolean bridge$intersectsWith(AxisAlignedBBBridge horsestats121) {
      return Ref.MC_VERSION == 5 ? this.intersects$v1_12((AxisAlignedBB)horsestats121) : this.intersectsWith((AxisAlignedBB)horsestats121);
   }

   public double bridge$calculateXOffset(AxisAlignedBBBridge horsestats121, double value2) {
      return this.calculateXOffset((AxisAlignedBB)horsestats121, value2);
   }

   public double bridge$calculateYOffset(AxisAlignedBBBridge horsestats121, double value2) {
      return this.calculateYOffset((AxisAlignedBB)horsestats121, value2);
   }

   public double bridge$calculateZOffset(AxisAlignedBBBridge horsestats121, double value2) {
      return this.calculateZOffset((AxisAlignedBB)horsestats121, value2);
   }

   public boolean bridge$hasNaN() {
      return Ref.MC_VERSION >= 1 ? this.hasNaN() : false;
   }

   public double bridge$getSize() {
      double value1 = this.maxX - this.minX;
      double value3 = this.maxY - this.minY;
      double value5 = this.maxZ - this.minZ;
      return (value1 + value3 + value5) / 3.0;
   }

   @VersionGate(max = 1)
   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else if (!(object instanceof AxisAlignedBB box2)) {
         return false;
      } else if (Double.compare(box2.minX, this.minX) != 0) {
         return false;
      } else if (Double.compare(box2.minY, this.minY) != 0) {
         return false;
      } else if (Double.compare(box2.minZ, this.minZ) != 0) {
         return false;
      } else if (Double.compare(box2.maxX, this.maxX) != 0) {
         return false;
      } else {
         return Double.compare(box2.maxY, this.maxY) != 0 ? false : Double.compare(box2.maxZ, this.maxZ) == 0;
      }
   }

   @VersionGate(max = 1)
   @Override
   public int hashCode() {
      long number1 = Double.doubleToLongBits(this.minX);
      int number3 = (int)(number1 ^ number1 >>> 32);
      number1 = Double.doubleToLongBits(this.minY);
      number3 = 31 * number3 + (int)(number1 ^ number1 >>> 32);
      number1 = Double.doubleToLongBits(this.minZ);
      number3 = 31 * number3 + (int)(number1 ^ number1 >>> 32);
      number1 = Double.doubleToLongBits(this.maxX);
      number3 = 31 * number3 + (int)(number1 ^ number1 >>> 32);
      number1 = Double.doubleToLongBits(this.maxY);
      number3 = 31 * number3 + (int)(number1 ^ number1 >>> 32);
      number1 = Double.doubleToLongBits(this.maxZ);
      return 31 * number3 + (int)(number1 ^ number1 >>> 32);
   }
}
