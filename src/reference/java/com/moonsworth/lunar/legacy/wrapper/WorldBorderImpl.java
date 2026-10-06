package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.base.Preconditions;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.WorldBorderExtensionBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.border.WorldBorder;

@VersionGate(min = 1)
public class WorldBorderImpl extends WorldBorder implements WorldBorderExtensionBridge {
   private final String field1;
   private int color;
   private boolean cancelExit;
   private boolean cancelEntry;
   private boolean field2;
   private AxisAlignedBBBridge field3;
   private final List<AxisAlignedBBBridge> field4 = new ArrayList<>(4);

   public WorldBorderImpl(String text, int value) {
      this.field1 = text;
      this.color = value;
      this.addListener(new IBorderImpl());
   }

   public boolean isStatic() {
      return this.field3 != null;
   }

   public void setTransition(double value1, double value3, long value) {
      Preconditions.checkArgument(!this.isStatic(), "Cannot transition static border");
      super.setTransition(value1, value3, value);
   }

   public double minX() {
      return this.isStatic() ? this.field3.bridge$getMinX() : super.minX();
   }

   public double minZ() {
      return this.isStatic() ? this.field3.bridge$getMinZ() : super.minZ();
   }

   public double maxX() {
      return this.isStatic() ? this.field3.bridge$getMaxX() : super.maxX();
   }

   public double maxZ() {
      return this.isStatic() ? this.field3.bridge$getMaxZ() : super.maxZ();
   }

   public List<AxisAlignedBBBridge> method8() {
      if (!this.field4.isEmpty() && this.isStatic()) {
         return this.field4;
      }

      double value1 = this.minX();
      double value3 = this.minZ();
      double value5 = this.maxX();
      double value7 = this.maxZ();
      this.field4.clear();
      this.field4.add((AxisAlignedBBBridge)(new AxisAlignedBB(value1, -2.1474836E9F, value3, value5, 2.147483647E9, value3)));
      this.field4.add((AxisAlignedBBBridge)(new AxisAlignedBB(value1, -2.1474836E9F, value3, value1, 2.147483647E9, value7)));
      this.field4.add((AxisAlignedBBBridge)(new AxisAlignedBB(value5, -2.1474836E9F, value3, value5, 2.147483647E9, value7)));
      this.field4.add((AxisAlignedBBBridge)(new AxisAlignedBB(value1, -2.1474836E9F, value7, value5, 2.147483647E9, value7)));
      return this.field4;
   }

   public boolean shouldRender() {
      return Client.method109().method16(this.field1);
   }

   public boolean contains(double value1, double value3) {
      return this.contains(new BlockPos(value1, 100.0, value3));
   }

   public void method1(double value1, double value3, double value5, double value7, int value) {
      double value10 = (value5 - value1) / 2.0;
      double value12 = (value7 - value3) / 2.0;
      double value14 = value10 + value12;
      if (value != 0 && this.field2) {
         this.setTransition(this.getDiameter(), value14, value * 50L);
      } else {
         this.setTransition(value14);
      }
   }

   public void method12(double value1) {
      this.setTransition(value1);
   }

   public void method9(double value1, double value3) {
      this.setCenter(value1, value3);
   }

   public double method19() {
      return this.getCenterX();
   }

   public double method20() {
      return this.getCenterZ();
   }

   public double method1(BridgeExtension bridge) {
      return this.getClosestDistance((Entity)bridge);
   }

   public double method2(double value1, double value3) {
      return this.getClosestDistance(value1, value3);
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
      return this.field1;
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
   public boolean method13() {
      return this.field2;
   }

   @Generated
   public AxisAlignedBBBridge method14() {
      return this.field3;
   }

   @Generated
   public List<AxisAlignedBBBridge> method15() {
      return this.field4;
   }

   @Generated
   public void setColor(int value) {
      this.color = value;
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
      this.field2 = flag1;
   }

   @Generated
   public void method6(AxisAlignedBBBridge horsestats121) {
      this.field3 = horsestats121;
   }
}
