package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

public class EventRenderNameTag extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final EntityLivingStateBridge field1;
   private double x;
   private double y;
   private double z;
   private List<Component> lines;
   private @Nullable Component field2;

   public void method1(Component component1) {
      if (!this.lines.isEmpty() && this.field2 != null) {
         int index2 = Math.max(0, this.lines.indexOf(this.field2));
         this.lines.set(index2, component1);
         this.field2 = component1;
      }
   }

   @Generated
   public EntityLivingStateBridge method2() {
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
   public EventRenderNameTag(EntityLivingStateBridge bridgeextension2_21, double value, double value2, double value3, List<Component> list, @Nullable Component component9) {
      this.field1 = bridgeextension2_21;
      this.x = value;
      this.y = value2;
      this.z = value3;
      this.lines = list;
      this.field2 = component9;
   }

   @Generated
   public void setX(double value1) {
      this.x = value1;
   }

   @Generated
   public void setY(double value1) {
      this.y = value1;
   }

   @Generated
   public void setZ(double value1) {
      this.z = value1;
   }

   @Generated
   public void setLines(List<Component> list) {
      this.lines = list;
   }
}
