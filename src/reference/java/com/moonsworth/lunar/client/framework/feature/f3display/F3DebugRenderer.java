package com.moonsworth.lunar.client.framework.feature.f3display;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.ProfilerResultBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule.Data;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import lombok.Generated;

public class F3DebugRenderer {
   public static final float field1 = 150.0F;
   public static final List<ProfilerResultBridge> field2 = List.of(
      new F3PieSlice(100.0, 100.0, "root"),
      new F3PieSlice(91.7, 91.7, "render"),
      new F3PieSlice(7.19, 7.19, "tick"),
      new F3PieSlice(0.36, 0.36, "scheduledPacketProcessing"),
      new F3PieSlice(0.26, 0.26, "scheduledExecutables"),
      new F3PieSlice(0.2, 0.2, "unspecified"),
      new F3PieSlice(0.12, 0.12, "fpsUpdate"),
      new F3PieSlice(0.05, 0.05, "mouse"),
      new F3PieSlice(0.05, 0.05, "sound"),
      new F3PieSlice(0.03, 0.03, "yield"),
      new F3PieSlice(0.02, 0.02, "wait_for_gpu"),
      new F3PieSlice(0.01, 0.01, "toasts")
   );
   private static final int[] field3 = new int[]{7698431, 9961333, 16751733, 16545279, 7733230, 16765060, 9078015, 16759382};
   protected final boolean field4;
   private final MixinHelper_4 field5;
   private final F3DisplayModule field6;
   private final float field7;
   private final float field8;
   private float y;
   private float field9 = 0.0F;
   private float field10 = 0.0F;

   public F3DebugRenderer(MixinHelper_4 mixinhelper_41, F3DisplayModule f3modulechildmod2, float value3, float value4, boolean flag5) {
      this.field6 = f3modulechildmod2;
      this.field7 = value3;
      this.y = value4;
      this.field8 = value4;
      this.field4 = flag5;
      this.field5 = mixinhelper_41;
   }

   public void method1(String... items1) {
      Bridge10_2 bridge10_22 = Ref.method10();
      float value3;
      if (items1.length == 1) {
         value3 = bridge10_22.bridge$getStringWidth(items1[0]);
      } else {
         String text4 = String.join("", items1);
         value3 = bridge10_22.bridge$getStringWidth(text4);
      }

      Data data10 = this.field6.getElement();
      float value5 = this.field7;
      switch (data10.method23()) {
         case RIGHT:
            value5 += data10.getWidth() - value3;
            break;
         case CENTER:
            value5 += (data10.getWidth() - value3) / 2.0F;
      }

      com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data data6 = this.field6.getDisplayOptions();
      float value7 = bridge10_22.method19();
      int number8 = (int)(value5 - 1.0F);
      int number9 = (int)(this.y - 1.0F);
      this.field5.method1(number8, number9, (int)(number8 + value3 + 2.0F), (int)(number9 + value7), data6.field3.method14(0.0F));
      this.y += value7;
   }

   public void method2(boolean flag1, String... items2) {
      Bridge10_2 bridge10_23 = Ref.method10();
      float value4;
      if (items2.length == 1) {
         value4 = bridge10_23.bridge$getStringWidth(items2[0]);
      } else {
         String text5 = String.join("", items2);
         value4 = bridge10_23.bridge$getStringWidth(text5);
      }

      Data data17 = this.field6.getElement();
      float value6 = this.field7;
      switch (data17.method23()) {
         case RIGHT:
            value6 += data17.getWidth() - value4;
            break;
         case CENTER:
            value6 += (data17.getWidth() - value4) / 2.0F;
      }

      com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data data7 = this.field6.getDisplayOptions();
      float value8 = bridge10_23.method19();
      boolean flag9 = (Boolean)data7.field1.get();
      float value10 = 0.0F;
      boolean flag11 = flag1;

      for (String text15 : items2) {
         int number16 = flag11 ? data7.field4.method14(0.0F) : data7.field5.method14(0.0F);
         this.field5.method22(bridge10_23, text15, (int)(value6 + value10), (int)this.y, number16, flag9);
         value10 += bridge10_23.bridge$getStringWidth(text15);
         flag11 = !flag11;
      }

      this.method8(value10, value8);
   }

   public void method3(List<ProfilerResultBridge> list1) {
      float value2 = switch (this.field6.getElement().method23()) {
         case RIGHT -> this.field7 + this.field6.getElement().getWidth() - 75.0F;
         case CENTER -> this.field7 + this.field6.getElement().getWidth() / 2.0F;
         case LEFT -> this.field7 + 75.0F;
         default -> throw new IncompatibleClassChangeError();
      };
      Bridge10_2 bridge10_23 = Ref.method10();
      com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data data4 = this.field6.getDisplayOptions();
      float value5 = this.y + 150.0F + 2.0F;
      int index6 = 0;

      for (ProfilerResultBridge mixinhelper_118 : list1) {
         String text9 = this.method5(mixinhelper_118, index6);
         float value10 = bridge10_23.bridge$getStringWidth(text9);
         int number11 = index6 == 0 ? 0 : 5;

         float value12 = switch (this.field6.getElement().method23()) {
            case RIGHT -> this.field7 + this.field6.getElement().getWidth() - value10 - number11;
            case CENTER -> value2 - value10 / 2.0F;
            case LEFT -> this.field7 + number11;
            default -> throw new IncompatibleClassChangeError();
         };
         int number13 = (int)(value12 - 1.0F);
         int number14 = (int)(value5 - 1.0F);
         this.field5.method1(number13, number14, (int)(number13 + value10 + 2.0F), number14 + bridge10_23.method19(), data4.field3.method14(0.0F));
         value5 += bridge10_23.method19();
         if (index6 == 0) {
            value5 += bridge10_23.method19();
         }

         index6++;
      }

      this.y += 150.0F;
   }

   public void method4(boolean flag1, List<ProfilerResultBridge> list2) {
      if (list2 != null && !list2.isEmpty()) {
         float value3 = this.y + 75.0F;

         float value4 = switch (this.field6.getElement().method23()) {
            case RIGHT -> this.field7 + this.field6.getElement().getWidth() - 75.0F;
            case CENTER -> this.field7 + this.field6.getElement().getWidth() / 2.0F;
            case LEFT -> this.field7 + 75.0F;
            default -> throw new IncompatibleClassChangeError();
         };
         float value5 = 75.0F;
         double[] items6;
         if (list2.size() == 1) {
            items6 = new double[]{((ProfilerResultBridge)list2.get(0)).method1()};
         } else {
            items6 = new double[list2.size() - 1];

            for (int index7 = 1; index7 < list2.size(); index7++) {
               items6[index7 - 1] = ((ProfilerResultBridge)list2.get(index7)).method1();
            }
         }

         int[] items25 = field3;
         com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data data8 = this.field6.getDisplayOptions();
         boolean flag9 = (Boolean)data8.field1.get();
         if (flag1) {
            LcuiScreen.method65(this.field5, value4, value3, value5, items6, items25, data8.field4.method14(0.0F));
         } else {
            LcuiScreen.method64(this.field5, value4, value3, value5, items6, items25);
         }

         float value10 = this.y + 150.0F + 2.0F;
         Bridge10_2 bridge10_211 = Ref.method10();
         float value12 = 150.0F;
         int index13 = 0;

         for (ProfilerResultBridge mixinhelper_1115 : list2) {
            boolean flag16 = this.field6.getElement().method23() == com.moonsworth.lunar.client.ui.hud.HudRowAlignment.RIGHT;
            String text17 = this.method5(mixinhelper_1115, index13);
            float value18 = bridge10_211.bridge$getStringWidth(text17);
            int number19 = index13 == 0 ? 0 : 5;

            float value20 = switch (this.field6.getElement().method23()) {
               case RIGHT -> this.field7 + this.field6.getElement().getWidth() - value18 - number19;
               case CENTER -> value4 - value18 / 2.0F;
               case LEFT -> this.field7 + number19;
               default -> throw new IncompatibleClassChangeError();
            };
            byte number21 = -1;
            if (list2.size() == 1) {
               number21 = (byte)(flag1 ? data8.field4.method14(0.0F) : items25[0]);
            } else if (index13 != 0) {
               number21 = (byte)(flag1 && index13 == 1 ? data8.field4.method14(0.0F) : items25[(index13 - 1) % items25.length]);
            }

            this.field5.method22(bridge10_211, text17, (int)value20, (int)value10, number21, flag9);
            if ((Boolean)this.field6.getShowGlobalPercent().get()) {
               String text22 = "(" + F3DebugInfo.method33(mixinhelper_1115.method2(), 2) + "%)";
               int number23 = ColorUtils.method22(number21, (int)(ColorUtils.method4(number21) * 0.8));
               if (flag16) {
                  float value24 = bridge10_211.bridge$getStringWidth(text22);
                  this.field5.method22(bridge10_211, text22, (int)(value20 - value24 - 5.0F), (int)value10, number23, false);
                  value18 += value24;
               } else {
                  float value26 = bridge10_211.bridge$getStringWidth(text17);
                  this.field5.method22(bridge10_211, text22, (int)(value20 + value18 + 5.0F), (int)value10, number23, false);
                  value18 += value26 + bridge10_211.bridge$getStringWidth(text22);
               }
            }

            value10 += bridge10_211.method19();
            if (index13 == 0) {
               value10 += bridge10_211.method19();
            }

            if (value18 > value12) {
               value12 = value18;
            }

            index13++;
         }

         this.method8(150.0F, 150.0F);
      } else {
         this.method8(150.0F, 150.0F);
      }
   }

   private String method5(ProfilerResultBridge mixinhelper_111, int number2) {
      String text3 = mixinhelper_111.bridge$getName();
      if (number2 == 0) {
         text3 = text3.replace('\u001e', '/');
      }

      boolean flag4 = this.field6.getElement().method23() == com.moonsworth.lunar.client.ui.hud.HudRowAlignment.RIGHT;
      if (number2 == 0) {
         return flag4 ? text3 + " [0]" : "[0] " + text3;
      } else {
         return flag4
            ? F3DebugInfo.method33(mixinhelper_111.method1(), 2) + "% " + text3 + " [" + number2 + "]"
            : "[" + number2 + "] " + text3 + " " + F3DebugInfo.method33(mixinhelper_111.method1(), 2) + "%";
      }
   }

   public void method6(com.moonsworth.lunar.client.framework.feature.f3display.chart.F3Chart f3display1) {
      if (!f3display1.method10() || this.field4) {
         com.moonsworth.lunar.client.mod.hud.f3display.F3Display.Data data2 = this.field6.getDisplayOptions();
         int number3 = data2.field3.method14(0.0F);
         byte number4 = 50;
         short number5 = 240;
         float value6 = this.field7 + 1.0F;
         float value7 = this.y + 1.0F;
         this.field5.method1((int)value6, (int)value7, Math.round(value6 + number5), Math.round(value7 + number4), number3);
         this.y += 52.0F;
      }
   }

   public void method7(com.moonsworth.lunar.client.framework.feature.f3display.chart.F3Chart f3display1) {
      Data data2 = this.field6.getElement();
      float value3 = this.field7;
      switch (data2.method23()) {
         case RIGHT:
            value3 += data2.getWidth() - 242.0F;
            break;
         case CENTER:
            value3 += (data2.getWidth() - 242.0F) / 2.0F;
      }

      boolean flag4 = f3display1.method13(this, this.field6, value3, this.y, this.field4);
      if (flag4) {
         this.method8(242.0F, 52.0F);
      }
   }

   private void method8(float value1, float value2) {
      this.y += value2;
      this.field10 += value2;
      this.field9 = Math.max(value1, this.field9);
   }

   public float method9() {
      return this.field10 == 0.0F ? Ref.method10().method19() : this.field10;
   }

   public float method10() {
      return Math.max(this.field9, 40.0F);
   }

   public void method11() {
      this.y = this.field8;
   }

   public boolean method12() {
      return (Boolean)this.field6.getDisplayOptions().field2.get();
   }

   @Generated
   public MixinHelper_4 method13() {
      return this.field5;
   }
}
