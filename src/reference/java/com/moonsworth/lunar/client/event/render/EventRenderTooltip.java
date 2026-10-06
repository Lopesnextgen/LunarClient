package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClickableText;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.mod.render.scrollabletooltips.ScrollableTooltips;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.DoubleRectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.event.LunarEvent;
import com.moonsworth.lunar.client.event.CancellableEvent;

public abstract class EventRenderTooltip extends CancellableEvent {
   private MixinHelper_4 field1;
   private int x;
   private int y;
   @Nullable
   private ItemStackBridge field2;

   public EventRenderTooltip(MixinHelper_4 mixinhelper_41, int number2, int number3, @Nullable ItemStackBridge bridgeextension_44) {
      this.field1 = mixinhelper_41;
      this.x = number2;
      this.y = number3;
      this.field2 = bridgeextension_44;
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
   public void method3(MixinHelper_4 mixinhelper_41) {
      this.field1 = mixinhelper_41;
   }

   @Generated
   public void setX(int number1) {
      this.x = number1;
   }

   @Generated
   public void setY(int number1) {
      this.y = number1;
   }

   @Generated
   public void method6(@Nullable ItemStackBridge bridgeextension_41) {
      this.field2 = bridgeextension_41;
   }

   public static class TooltipRender extends LunarEvent {
      private final int field1;
      private final int field2;
      private final int field3;
      private final int field4;
      private boolean field5;
      private int field6;
      private int field7;
      private float scale;

      public TooltipRender(int number1, int number2, int number3, int number4) {
         this.field1 = number1;
         this.field2 = number2;
         this.field3 = number3;
         this.field4 = number4;
         this.scale = 1.0F;
      }

      public int getX() {
         return this.field5 ? this.field6 : this.field1;
      }

      public int getY() {
         return this.field5 ? this.field7 : this.field2;
      }

      public void method1(int number1, int number2, float value3) {
         this.field5 = true;
         this.field6 = number1;
         this.field7 = number2;
         this.scale = value3;
      }

      public void updateCache() {
         ScrollableTooltips scrollabletooltips1 = Ref.method4().method40().method46();
         float value2 = scrollabletooltips1.isEnabled() ? (Float)scrollabletooltips1.getTooltipScale().get() : 1.0F;
         float value3 = this.field5 ? this.scale * value2 : value2;
         DoubleRectangle threadmoduledump954 = scrollabletooltips1.getTooltipController().method13();
         threadmoduledump954.setX(this.getX());
         threadmoduledump954.setY(this.getY());
         threadmoduledump954.setWidth(value3 * this.field3);
         threadmoduledump954.setHeight(value3 * this.field4);
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

   public static class EventRenderTooltipPre extends EventRenderTooltip {
      private final List<ClickableText> field3;
      private List<ClickableText> field4;
      private final float field5;
      private boolean field6;

      public EventRenderTooltipPre(MixinHelper_4 mixinhelper_41, List<ClickableText> list, int number3, int number4, @Nullable ItemStackBridge bridgeextension_45) {
         super(mixinhelper_41, number3, number4, bridgeextension_45);
         this.field3 = new ArrayList<>(list);
         Bridge10_2 bridge10_26 = Ref.method10();
         if (bridge10_26 == null) {
            CrashReporter.method5(new IllegalStateException("cannot assertain width because font is null"), "EventRenderTooltip.Pre");
            this.field5 = 0.0F;
         } else {
            float value7 = 0.0F;

            for (ClickableText mixinhelper_139 : list) {
               float value10 = mixinhelper_139.bridge$getWidth(bridge10_26);
               if (value10 > value7) {
                  value7 = value10;
               }
            }

            this.field5 = value7;
         }
      }

      public List<ClickableText> method3() {
         return this.isModified() ? this.field4 : this.field3;
      }

      public void method2(List<ClickableText> list) {
         this.field4 = list;
         this.field6 = true;
      }

      @Generated
      public List<ClickableText> method4() {
         return this.field3;
      }

      @Generated
      public List<ClickableText> method5() {
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
