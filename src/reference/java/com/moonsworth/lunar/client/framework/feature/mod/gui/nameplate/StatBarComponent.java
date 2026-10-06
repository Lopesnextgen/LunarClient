package com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public class StatBarComponent extends HudElementBase {
   private static final float field9 = 100.0F;
   private static final float field10 = 8.0F;
   private static final float field11 = 1.5F;
   private static final float field12 = 97.0F;
   private static final float field13 = 5.0F;
   private static final int field14 = 5;
   private final StatBarComponent.StatBarOptions field15;
   private final Supplier<List<StatBarComponent.Data>> field16;
   private List<StatBarComponent.Data> field17;

   public StatBarComponent(float value1, float value2, @NotNull HudAnchor gui2extension23, StatBarComponent.StatBarOptions data24, Supplier<List<StatBarComponent.Data>> supplier5) {
      super(value1, value2, gui2extension23);
      this.field15 = data24;
      this.field16 = supplier5;
   }

   @Override
   public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
      this.method2(highlightimpl1, value2, value3, flag4 ? this.field16.get() : this.field17);
   }

   private void method2(EventRenderHudBase highlightimpl1, float value2, float value3, List<StatBarComponent.Data> list4) {
      List list5 = this.method5(list4);
      this.method6(list4, list5);
      MixinHelper_4 mixinhelper_46 = highlightimpl1.method2();
      Bridge10_2 bridge10_27 = Ref.method10();
      float value8 = value2 + 5.0F;
      float value9 = value3 + 5.0F;
      if ((Boolean)this.field15.field4.get()) {
         this.field15.field5.method11(mixinhelper_46, value2, value3, this.getWidth(), this.getHeight());
      }

      for (Component component11 : list5) {
         float value12 = bridge10_27.bridge$getStringWidth(component11);
         float value13 = this.method4(value8, value12);
         mixinhelper_46.method11(bridge10_27, component11, value13, value9, -1, true);
         value9 += bridge10_27.method19() + 2;
      }

      if (this.method7(list4)) {
         StatBarComponent.Data data14 = (StatBarComponent.Data)list4.get(0);
         float value15 = this.method4(value8, 100.0F);
         this.method3(mixinhelper_46, value15, value9, data14);
      }
   }

   private void method3(MixinHelper_4 mixinhelper_41, float value2, float value3, StatBarComponent.Data data4) {
      LcuiScreen.method117(mixinhelper_41, value2, value3, 100.0F, 8.0F, 7.0F, 267386880);
      float value5 = value2 + 1.5F;
      float value6 = value3 + 1.5F;
      LcuiScreen.method117(mixinhelper_41, value5, value6, 97.0F, 5.0F, 5.0F, -13421773);
      LcuiScreen.method117(mixinhelper_41, value5, value6, 97.0F * data4.method2(), 5.0F, 5.0F, 0xFF000000 | data4.field2);
   }

   private float method4(float value1, float value2) {
      float value3 = this.getWidth() - 10.0F;

      return switch ((HudRowAlignment)this.field15.field6.get()) {
         case LEFT -> value1;
         case CENTER -> value1 + (value3 - value2) / 2.0F;
         case RIGHT -> value1 + value3 - value2;
         default -> throw new IncompatibleClassChangeError();
      };
   }

   private List<Component> method5(List<StatBarComponent.Data> list1) {
      if (!(Boolean)this.field15.field1.get()) {
         return List.of();
      }

      ArrayList list2 = new ArrayList();
      NumberFormat numberformat3 = NumberFormat.getIntegerInstance(Locale.ROOT);

      for (StatBarComponent.Data data5 : list1) {
         Object obj6 = Component.text(numberformat3.format(data5.field1));
         if (data5.method1()) {
            obj6 = obj6.append(Component.text("/" + numberformat3.format(data5.max)));
         }

         if ((Boolean)this.field15.field2.get()) {
            obj6 = obj6.append(Component.text(data5.field3));
         }

         Component component10 = obj6.color(TextColor.color(data5.field2));
         list2.add(component10);
      }

      if ((Boolean)this.field15.field7.get()) {
         Object obj7 = Component.empty();

         for (int index9 = 0; index9 < list2.size(); index9++) {
            obj7 = obj7.append((Component)list2.get(index9));
            if (index9 != list2.size() - 1) {
               obj7 = obj7.append(Component.text(" "));
            }
         }

         Component component8 = obj7.compact();
         return List.of(component8);
      } else {
         return list2;
      }
   }

   private void method6(List<StatBarComponent.Data> list1, List<Component> list2) {
      float value3 = 0.0F;
      float value4 = 0.0F;
      boolean flag5 = this.method7(list1);
      if (flag5) {
         value3 = 100.0F;
         value4 = 8.0F;
      }

      Bridge10_2 bridge10_26 = Ref.method10();

      for (int index7 = 0; index7 < list2.size(); index7++) {
         Component component8 = (Component)list2.get(index7);
         float value9 = bridge10_26.bridge$getStringWidth(component8);
         if (value9 > value3) {
            value3 = value9;
         }

         value4 += bridge10_26.method19();
         if (flag5 || index7 != list2.size() - 1) {
            value4 += 2.0F;
         }
      }

      this.method16(value3 + 10.0F, value4 + 10.0F);
   }

   private boolean method7(List<StatBarComponent.Data> list1) {
      return (Boolean)this.field15.field3.get() && !list1.isEmpty() && ((StatBarComponent.Data)list1.get(0)).method1();
   }

   @Override
   public boolean method4(boolean flag1) {
      return flag1 ? true : this.field17 != null && !this.field17.isEmpty();
   }

   @Generated
   public void method9(List<StatBarComponent.Data> list1) {
      this.field17 = list1;
   }

   public class Data {
      private final int field1;
      private final int max;
      private final int field2;
      private final char field3;

      public Data(int number1, int number2, int number3, char character4) {
         this.field1 = number1;
         this.max = number2;
         this.field2 = number3;
         this.field3 = character4;
      }

      public boolean method1() {
         return this.max != -1;
      }

      public float method2() {
         return this.field1 > this.max ? 1.0F : (float)this.field1 / this.max;
      }

      public int method3() {
         return this.field1;
      }

      public int method4() {
         return this.field2;
      }

      public char method5() {
         return this.field3;
      }
   }

   public class StatBarOptions {
      private final ToggleOption field1;
      private final ToggleOption field2;
      private final ToggleOption field3;
      private final ToggleOption field4;
      private final ColorOption field5;
      private final EnumOption<HudRowAlignment> field6;
      private final ToggleOption field7;

      public StatBarOptions(
         ToggleOption lightingextension4431,
         ToggleOption lightingextension4432,
         ToggleOption lightingextension4433,
         ToggleOption lightingextension4434,
         ColorOption lightingextension42225,
         EnumOption<HudRowAlignment> lightingextension4976,
         ToggleOption lightingextension4437
      ) {
         this.field1 = lightingextension4431;
         this.field2 = lightingextension4432;
         this.field3 = lightingextension4433;
         this.field4 = lightingextension4434;
         this.field5 = lightingextension42225;
         this.field6 = lightingextension4976;
         this.field7 = lightingextension4437;
      }

      public ToggleOption method1() {
         return this.field1;
      }

      public ToggleOption method2() {
         return this.field2;
      }

      public ToggleOption method3() {
         return this.field3;
      }

      public ToggleOption method4() {
         return this.field4;
      }

      public ColorOption method5() {
         return this.field5;
      }

      public EnumOption<HudRowAlignment> method6() {
         return this.field6;
      }

      public ToggleOption method7() {
         return this.field7;
      }
   }
}
