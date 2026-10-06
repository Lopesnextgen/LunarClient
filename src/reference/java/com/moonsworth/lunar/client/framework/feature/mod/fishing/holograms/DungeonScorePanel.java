package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.HologramTextRenderer;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

@VersionGate(min = 33)
public class DungeonScorePanel {
   private final DungeonStats field1;
   protected final DungeonMapOverlay field2;

   public DungeonScorePanel(DungeonMapOverlay holograms3_21, DungeonStats holograms_62) {
      this.field1 = holograms_62;
      this.field2 = holograms3_21;
   }

   public void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      Component component7;
      Component component8;
      Component component9;
      if (holograms2_53.method39()) {
         component7 = this.method8(279, false);
         component8 = this.method4(4, true, false);
         component9 = this.method5(true, holograms_92.method44());
      } else {
         component7 = this.method8(this.field1.method2(), false);
         component8 = this.method4(this.field1.method10(), this.field1.method13(), false);
         component9 = this.method5(this.field1.method12(), holograms_92.method44());
      }

      boolean flag10 = holograms2_53.method34() >= 6;
      this.field2.method12(mixinhelper_41, component7, value4 + (flag10 ? 20 : 25), value5 + 6.5F, 1.0F, HologramTextRenderer.Type.SHADOW);
      this.field2.method12(mixinhelper_41, component8, value4 + (flag10 ? 43 : 75), value5 + 6.5F, 1.0F, HologramTextRenderer.Type.SHADOW);
      if (flag10) {
         this.field2.method12(mixinhelper_41, component9, value4 + 75.0F, value5 + 6.5F, 1.0F, HologramTextRenderer.Type.SHADOW);
      }

      if (markers6.method13() >= value5 && markers6.method13() <= value5 + 15.0F && markers6.method12() >= value4 && markers6.method12() <= value4 + 100.0F) {
         this.field2.method26(this.method9(holograms2_53));
      }
   }

   public void method2(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      TextComponent text9 = Component.text("    ");
      Component component7;
      Component component8;
      if (holograms2_53.method39()) {
         Component component10 = this.method3(20, 9, 29);
         Component component11 = this.method4(4, true, true);
         Component component12 = this.method5(true, true);
         Component component13 = this.method6(26, false);
         Component component14 = this.method7(0);
         Component component15 = this.method8(279, true);
         component7 = component10.append(text9).append(component11).append(text9).append(component12);
         component8 = component13.append(text9).append(component14).append(text9).append(component15);
      } else {
         int index17 = this.field1.method11() * 2;
         if (this.field1.method14()) {
            index17--;
         }

         Component component18 = this.method3(this.field1.method7(), this.field1.method8() - this.field1.method7(), this.field1.method8());
         Component component19 = this.method4(this.field1.method10(), this.field1.method13(), true);
         Object obj20 = holograms2_53.method34() >= 6 ? this.method5(this.field1.method12(), true) : Component.empty();
         Component component21 = this.field1.method7() == 0 ? this.method6(-1, false) : this.method6(this.field1.method9(), this.field1.method2() >= 300);
         Component component22 = this.method7(index17);
         Component component16 = this.method8(this.field1.method2(), true);
         component7 = component18.append(text9).append(component19).append(text9).append((Component)obj20);
         component8 = component21.append(text9).append(component22).append(text9).append(component16);
      }

      this.field2.method12(mixinhelper_41, component7, value4 + 50.0F, value5 + 5.0F, 0.4F, HologramTextRenderer.Type.SHADOW);
      this.field2.method12(mixinhelper_41, component8, value4 + 50.0F, value5 + 10.0F, 0.4F, HologramTextRenderer.Type.SHADOW);
      if (markers6.method13() >= value5 && markers6.method13() <= value5 + 15.0F && markers6.method12() >= value4 && markers6.method12() <= value4 + 100.0F) {
         this.field2.method26(this.method9(holograms2_53));
      }
   }

   private Component method3(int number1, int number2, int number3) {
      return number1 == 0
         ? ((TextComponent)Component.text("Secrets: ").color(NamedTextColor.GRAY)).append(Component.text("?").color(NamedTextColor.AQUA))
         : ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text("Secrets: ").color(NamedTextColor.GRAY))
                        .append(Component.text(number1).color(NamedTextColor.AQUA)))
                     .append(Component.text("-").color(NamedTextColor.DARK_GRAY)))
                  .append(Component.text(number2).color(NamedTextColor.YELLOW)))
               .append(Component.text("-").color(NamedTextColor.GRAY)))
            .append(Component.text(number3).color(NamedTextColor.RED));
   }

   private Component method4(int number1, boolean flag2, boolean flag) {
      NamedTextColor namedtextcolor4;
      if (number1 >= 5) {
         namedtextcolor4 = NamedTextColor.GREEN;
      } else if (number1 >= 1) {
         namedtextcolor4 = NamedTextColor.YELLOW;
      } else {
         namedtextcolor4 = NamedTextColor.RED;
      }

      Component component5 = flag
         ? ((TextComponent)Component.text("Crypts: ").color(NamedTextColor.GRAY)).append(Component.text(number1).color(namedtextcolor4))
         : ((TextComponent)Component.text(number1).color(namedtextcolor4)).append(Component.text("c"));
      return flag2 ? component5.append(Component.text('♕').color(NamedTextColor.GOLD)) : component5;
   }

   private Component method5(boolean flag, boolean flag2) {
      Component component3 = Component.text("Mimic: ").color(NamedTextColor.GRAY);
      Component component4 = flag ? Component.text('✔').color(NamedTextColor.GREEN) : Component.text('✖').color(NamedTextColor.RED);
      return flag2 ? component3.append(component4) : component4;
   }

   private Component method6(int number1, boolean flag2) {
      Component component3 = Component.text("Min Secrets: ").color(NamedTextColor.GRAY);
      if (number1 == -1) {
         return component3.append(Component.text("?").color(NamedTextColor.AQUA));
      }

      NamedTextColor namedtextcolor4 = flag2 ? NamedTextColor.GREEN : NamedTextColor.YELLOW;
      return component3.append(Component.text(number1).color(namedtextcolor4));
   }

   private Component method7(int number1) {
      Component component2 = Component.text("Deaths: ").color(NamedTextColor.GRAY);
      return number1 > 0
         ? component2.append(Component.text("-").color(NamedTextColor.RED)).append(Component.text(number1).color(NamedTextColor.RED))
         : component2.append(Component.text(number1).color(NamedTextColor.GREEN));
   }

   private Component method8(int number1, boolean flag2) {
      NamedTextColor namedtextcolor3;
      if (number1 >= 300) {
         namedtextcolor3 = NamedTextColor.GREEN;
      } else if (number1 >= 270) {
         namedtextcolor3 = NamedTextColor.YELLOW;
      } else {
         namedtextcolor3 = NamedTextColor.RED;
      }

      Component component4 = Component.text(number1).color(namedtextcolor3);
      return flag2 ? ((TextComponent)Component.text("Score: ").color(NamedTextColor.GRAY)).append(component4) : component4;
   }

   private List<Component> method9(DungeonStateTracker holograms2_51) {
      if (holograms2_51.method39()) {
         return this.method10(holograms2_51, 279, 100, 84, 84, 7, 20, 9, 29, 4, true, true, 26, 0);
      }

      int index2 = this.field1.method11() * 2;
      if (this.field1.method14()) {
         index2--;
      }

      return this.method10(
         holograms2_51,
         this.field1.method2(),
         this.field1.method3(),
         this.field1.method4(),
         this.field1.method5(),
         this.field1.method6(),
         this.field1.method7(),
         this.field1.method8() - this.field1.method7(),
         this.field1.method8(),
         this.field1.method10(),
         this.field1.method12(),
         this.field1.method13(),
         this.field1.method9(),
         index2
      );
   }

   private List<Component> method10(
      DungeonStateTracker holograms2_51,
      int number2,
      int number3,
      int value,
      int value2,
      int value3,
      int value4,
      int value5,
      int value6,
      int value7,
      boolean flag,
      boolean flag2,
      int value8,
      int value9
   ) {
      Component component15 = Component.text("?").color(NamedTextColor.AQUA);
      ArrayList list16 = new ArrayList();
      list16.add(Component.text("Total Score: ").append(Component.text(number2).color(NamedTextColor.AQUA)));
      list16.add(Component.text("Skill Score: ").append(Component.text(number3).color(NamedTextColor.GREEN)));
      list16.add(Component.text("Exploration Score: ").append(Component.text(value).color(NamedTextColor.GREEN)));
      list16.add(Component.text("Time Score: ").append(Component.text(value2).color(NamedTextColor.GREEN)));
      list16.add(Component.text("Bonus Score: ").append(Component.text(value3).color(NamedTextColor.GREEN)));
      list16.add(Component.empty());
      list16.add(Component.text("Secrets Found: ").append(Component.text(value4).color(NamedTextColor.AQUA)));
      TextComponent text17 = Component.text("Secrets Left: ");
      TextComponent text18 = Component.text("Total Secrets: ");
      if (value4 == 0) {
         list16.add(text17.append(component15));
         list16.add(text18.append(component15));
      } else {
         list16.add(text17.append(Component.text(value5).color(NamedTextColor.YELLOW)));
         list16.add(text18.append(Component.text(value6).color(NamedTextColor.RED)));
      }

      list16.add(Component.empty());
      list16.add(Component.text("Crypts: ").append(Component.text(value7).color(NamedTextColor.GREEN)));
      if (holograms2_51.method34() >= 6) {
         Component component19 = flag ? Component.text('✔').color(NamedTextColor.GREEN) : Component.text('✖').color(NamedTextColor.RED);
         list16.add(Component.text("Mimic: ").append(component19));
      }

      if (flag2) {
         String text20 = Ref.method4().method67().method2("features.SKYBLOCK.info", "dungeonPrince", new Object[0]);
         list16.add(Component.text(text20 + ": ").append(Component.text('✔').color(NamedTextColor.GREEN)));
      }

      list16.add(Component.empty());
      TextComponent text21 = Component.text("Min Secrets (S+): ");
      if (value4 == 0) {
         list16.add(text21.append(component15));
      } else {
         list16.add(text21.append(Component.text(value8).color(NamedTextColor.GREEN)));
      }

      list16.add(Component.text("Death Penalty: ").append(Component.text(value9).color(NamedTextColor.RED)));
      return list16;
   }
}
