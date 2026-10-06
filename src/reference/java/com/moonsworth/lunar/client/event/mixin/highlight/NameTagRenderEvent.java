package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.BridgeExtension2_2;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

public class NameTagRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final BridgeExtension2_2 field1;
   private double x;
   private double y;
   private double z;
   private List<Component> lines;
   private @Nullable Component field2;

   public void method1(Component var1) {
      if (!this.lines.isEmpty() && this.field2 != null) {
         int var2 = Math.max(0, this.lines.indexOf(this.field2));
         this.lines.set(var2, var1);
         this.field2 = var1;
      }
   }

   @Generated
   public BridgeExtension2_2 method2() {
      return this.field1;
   }

   @Generated
   public double getX() {
      return this.x;
   }

   @Generated
   public double getY() {
      return this.y;
   }

   @Generated
   public double getZ() {
      return this.z;
   }

   @Generated
   public List<Component> getLines() {
      return this.lines;
   }

   @Generated
   public @Nullable Component method3() {
      return this.field2;
   }

   @Generated
   public NameTagRenderEvent(BridgeExtension2_2 var1, double var2, double value, double value2, List<Component> list, @Nullable Component var9) {
      this.field1 = var1;
      this.x = var2;
      this.y = value;
      this.z = value2;
      this.lines = list;
      this.field2 = var9;
   }

   @Generated
   public void setX(double var1) {
      this.x = var1;
   }

   @Generated
   public void setY(double var1) {
      this.y = var1;
   }

   @Generated
   public void setZ(double var1) {
      this.z = var1;
   }

   @Generated
   public void setLines(List<Component> var1) {
      this.lines = var1;
   }
}
