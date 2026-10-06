package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.base.Preconditions;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.Itemcounter4Extension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter4_3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

@Annotation2(max = 0)
public class LegacyServerBorderState extends LegacyWorldBorderBase implements Itemcounter4_3, Itemcounter4Extension {
   private final String field11;
   private int color;
   private boolean cancelExit;
   private boolean cancelEntry;
   private boolean field12;
   private AxisAlignedBBBridge field13;
   private final List<AxisAlignedBBBridge> field14 = new ArrayList<>(4);

   public LegacyServerBorderState(String var1, int var2) {
      this.field11 = var1;
      this.color = var2;
   }

   public boolean isStatic() {
      return this.field13 != null;
   }

   @Override
   public void setTransition(double var1, double var3, long var5) {
      Preconditions.checkArgument(!this.isStatic(), "Cannot transition static border");
      super.setTransition(var1, var3, var5);
   }

   @Override
   public void method8(double var1) {
      super.method8(var1);
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

      double var1 = this.minX();
      double var3 = this.minZ();
      double var5 = this.maxX();
      double var7 = this.maxZ();
      this.field14.clear();
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(var1, -2.1474836E9F, var3, var5, 2.147483647E9, var3)));
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(var1, -2.1474836E9F, var3, var1, 2.147483647E9, var7)));
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(var5, -2.1474836E9F, var3, var5, 2.147483647E9, var7)));
      this.field14.add((AxisAlignedBBBridge)(new AxisAlignedBB(var1, -2.1474836E9F, var7, var5, 2.147483647E9, var7)));
      return this.field14;
   }

   public boolean shouldRender() {
      return ThreadModuleDump63.method4().method16(this.field11);
   }

   @Override
   public boolean contains(double var1, double var3) {
      return super.contains(var1, var3);
   }

   public void method1(double var1, double var3, double var5, double var7, int var9) {
      double var10 = (var5 - var1) / 2.0;
      double var12 = (var7 - var3) / 2.0;
      double var14 = var10 + var12;
      if (var9 != 0 && this.field12) {
         this.setTransition(this.getDiameter(), var14, var9 * 50L);
      } else {
         this.method8(var14);
      }
   }

   public void method9(double var1, double var3) {
      this.method9(var1, var3);
   }

   public double method19() {
      return this.getCenterX();
   }

   public double method20() {
      return this.method10();
   }

   public void method12(double var1) {
      this.method8(var1);
   }

   public double method1(BridgeExtension var1) {
      return this.method1((Entity)var1);
   }

   public double method2(double var1, double var3) {
      return this.method2(var1, var3);
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
   public void setColor(int var1) {
      this.color = var1;
   }

   @Generated
   public void setCancelExit(boolean var1) {
      this.cancelExit = var1;
   }

   @Generated
   public void setCancelEntry(boolean var1) {
      this.cancelEntry = var1;
   }

   @Generated
   public void method4(boolean var1) {
      this.field12 = var1;
   }

   @Generated
   public void method6(AxisAlignedBBBridge var1) {
      this.field13 = var1;
   }
}
