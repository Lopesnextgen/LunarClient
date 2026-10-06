package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickableTextExtension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump95;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.highlight.Highlight;
import com.moonsworth.lunar.client.highlight.HighlightImpl;

public abstract class TooltipRenderEvent extends HighlightImpl {
   private MixinHelper_4 field1;
   private int x;
   private int y;
   @Nullable
   private ItemStackBridge field2;

   public TooltipRenderEvent(MixinHelper_4 var1, int var2, int var3, @Nullable ItemStackBridge var4) {
      this.field1 = var1;
      this.x = var2;
      this.y = var3;
      this.field2 = var4;
   }

   public Optional<ItemStackBridge> method1() {
      return Optional.ofNullable(this.field2);
   }

   @Generated
   public MixinHelper_4 method2() {
      return this.field1;
   }

   @Generated
   public int getX() {
      return this.x;
   }

   @Generated
   public int getY() {
      return this.y;
   }

   @Generated
   public void method3(MixinHelper_4 var1) {
      this.field1 = var1;
   }

   @Generated
   public void setX(int var1) {
      this.x = var1;
   }

   @Generated
   public void setY(int var1) {
      this.y = var1;
   }

   @Generated
   public void method6(@Nullable ItemStackBridge var1) {
      this.field2 = var1;
   }

   public static class Data extends Highlight {
      private final int field1;
      private final int field2;
      private final int field3;
      private final int field4;
      private boolean field5;
      private int field6;
      private int field7;
      private float scale;

      public Data(int var1, int var2, int var3, int var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.scale = 1.0F;
      }

      public int getX() {
         return this.field5 ? this.field6 : this.field1;
      }

      public int getY() {
         return this.field5 ? this.field7 : this.field2;
      }

      public void method1(int var1, int var2, float var3) {
         this.field5 = true;
         this.field6 = var1;
         this.field7 = var2;
         this.scale = var3;
      }

      public void updateCache() {
         ScrollableTooltips var1 = ThreadModuleDump63.method4().method40().method46();
         float var2 = var1.isEnabled() ? (Float)var1.getTooltipScale().get() : 1.0F;
         float var3 = this.field5 ? this.scale * var2 : var2;
         ThreadModuleDump95 var4 = var1.getTooltipController().method13();
         var4.setX(this.getX());
         var4.setY(this.getY());
         var4.setWidth(var3 * this.field3);
         var4.setHeight(var3 * this.field4);
      }

      @Generated
      public int method2() {
         return this.field1;
      }

      @Generated
      public int method3() {
         return this.field2;
      }

      @Generated
      public int getWidth() {
         return this.field3;
      }

      @Generated
      public int getHeight() {
         return this.field4;
      }

      @Generated
      public boolean isModified() {
         return this.field5;
      }

      @Generated
      public int method4() {
         return this.field6;
      }

      @Generated
      public int method5() {
         return this.field7;
      }

      @Generated
      public float getScale() {
         return this.scale;
      }
   }

   public static class TooltipPreRenderEvent extends TooltipRenderEvent {
      private final List<ClickableTextExtension> field3;
      private List<ClickableTextExtension> field4;
      private final float field5;
      private boolean field6;

      public TooltipPreRenderEvent(MixinHelper_4 var1, List<ClickableTextExtension> var2, int var3, int var4, @Nullable ItemStackBridge var5) {
         super(var1, var3, var4, var5);
         this.field3 = new ArrayList<>(var2);
         Bridge10_2 var6 = ThreadModuleDump63.method10();
         if (var6 == null) {
            Inventorymod2.method5(new IllegalStateException("cannot assertain width because font is null"), "EventRenderTooltip.Pre");
            this.field5 = 0.0F;
         } else {
            float var7 = 0.0F;

            for (ClickableTextExtension var9 : var2) {
               float var10 = var9.bridge$getWidth(var6);
               if (var10 > var7) {
                  var7 = var10;
               }
            }

            this.field5 = var7;
         }
      }

      public List<ClickableTextExtension> method3() {
         return this.isModified() ? this.field4 : this.field3;
      }

      public void method2(List<ClickableTextExtension> var1) {
         this.field4 = var1;
         this.field6 = true;
      }

      @Generated
      public List<ClickableTextExtension> method4() {
         return this.field3;
      }

      @Generated
      public List<ClickableTextExtension> method5() {
         return this.field4;
      }

      @Generated
      public float method6() {
         return this.field5;
      }

      @Generated
      public boolean isModified() {
         return this.field6;
      }
   }
}
