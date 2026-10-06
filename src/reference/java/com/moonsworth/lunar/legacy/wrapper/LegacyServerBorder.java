package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.base.Preconditions;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBorderExtensionBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.world.WorldBorderBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

@VersionGate(max = 0)
public class LegacyServerBorder extends LegacyWorldBorder implements WorldBorderBridge, WorldBorderExtensionBridge {
   private final String field11;
   private int color;
   private boolean cancelExit;
   private boolean cancelEntry;
   private boolean field12;
   private AxisAlignedBBBridge field13;
   private final List<AxisAlignedBBBridge> field14 = new ArrayList<>(4);

   public LegacyServerBorder(String text1, int number2) {
      this.field11 = text1;
      this.color = number2;
   }

   public boolean isStatic() {
      return this.field13 != null;
   }

   @Override
   public void setTransition(double value1, double value3, long number5) {
      Preconditions.checkArgument(!this.isStatic(), "Cannot transition static border");
      super.setTransition(value1, value3, number5);
   }

   @Override
   public void method8(double value1) {
      super.method8(value1);
   }

   @Override
   public double minX() {
      return this.isStatic() ? this.field13.bridge$getMinX() : super.minX();
   }

   @Override
   public double minZ() {
      return this.isStatic() ? this.field13.bridge$getMinZ() : super.minZ();
   }

   @Override
   public double maxX() {
      return this.isStatic() ? this.field13.bridge$getMaxX() : super.maxX();
   }

   @Override
   public double maxZ() {
      return this.isStatic() ? this.field13.bridge$getMaxZ() : super.maxZ();
   }

   public List<AxisAlignedBBBridge> method8() {
      if (!this.field14.isEmpty() && this.isStatic()) {
         return this.field14;
      }

      double value1 = this.minX();
      double value3 = this.minZ();
      double value5 = this.maxX();
      double value7 = this.maxZ();
      this.field14.clear();
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(value1, -2.1474836E9F, value3, value5, 2.147483647E9, value3)));
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(value1, -2.1474836E9F, value3, value1, 2.147483647E9, value7)));
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(value5, -2.1474836E9F, value3, value5, 2.147483647E9, value7)));
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(value1, -2.1474836E9F, value7, value5, 2.147483647E9, value7)));
      return this.field14;
   }

   public boolean shouldRender() {
      return Ref.method4().method16(this.field11);
   }

   @Override
   public boolean contains(double value1, double value3) {
      return super.contains(value1, value3);
   }

   public void method1(double value1, double value3, double value5, double value7, int number9) {
      double value10 = (value5 - value1) / 2.0;
      double value12 = (value7 - value3) / 2.0;
      double value14 = value10 + value12;
      if (number9 != 0 && this.field12) {
         this.setTransition(this.getDiameter(), value14, number9 * 50L);
      } else {
         this.method8(value14);
      }
   }

   public void method9(double value1, double value3) {
      this.method5(value1, value3);
   }

   public double method19() {
      return this.getCenterX();
   }

   public double method20() {
      return this.method10();
   }

   public void method12(double value1) {
      this.method8(value1);
   }

   public double method1(BridgeExtension bridgeextension1) {
      return this.method1((Entity)bridgeextension1);
   }

   public double method2(double value1, double value3) {
      return this.method3(value1, value3);
   }

   public double method3() {
      return this.minX();
   }

   public double method24() {
      return this.minZ();
   }

   public double method5() {
      return this.maxX();
   }

   public double method25() {
      return this.maxZ();
   }

   @Generated
   public String getWorld() {
      return this.field11;
   }

   @Generated
   public int getColor() {
      return this.color;
   }

   @Generated
   public boolean isCancelExit() {
      return this.cancelExit;
   }

   @Generated
   public boolean isCancelEntry() {
      return this.cancelEntry;
   }

   @Generated
   public boolean method21() {
      return this.field12;
   }

   @Generated
   public AxisAlignedBBBridge method22() {
      return this.field13;
   }

   @Generated
   public List<AxisAlignedBBBridge> method23() {
      return this.field14;
   }

   @Generated
   public void setColor(int number1) {
      this.color = number1;
   }

   @Generated
   public void setCancelExit(boolean flag1) {
      this.cancelExit = flag1;
   }

   @Generated
   public void setCancelEntry(boolean flag1) {
      this.cancelEntry = flag1;
   }

   @Generated
   public void method4(boolean flag1) {
      this.field12 = flag1;
   }

   @Generated
   public void method6(AxisAlignedBBBridge horsestats121) {
      this.field13 = horsestats121;
   }
}
