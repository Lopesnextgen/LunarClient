package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.mod.skyblock.dungeontimer.SkyblockDungeonTimer;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.hud.HudTimer.Data;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

public class DungeonSplit {
   private final TextComponent field1;
   private final Pattern field2;
   private long startTime = -1L;
   private long field3 = -1L;
   private double field4 = -1.0;
   private double field5 = -1.0;
   private HudTimer field6;
   private double field7 = -1.0;

   public DungeonSplit(TextComponent textComponent, String text2) {
      this.field1 = textComponent;
      this.field2 = Pattern.compile("^" + text2 + "$");
   }

   public DungeonSplit(DungeonSplit holograms4_41) {
      this.field1 = holograms4_41.method6();
      this.field2 = holograms4_41.method7();
      this.field4 = holograms4_41.method9();
      this.field5 = holograms4_41.method10();
   }

   public double method1() {
      if (this.startTime == -1L) {
         return 0.0;
      } else {
         return this.field3 == -1L ? (Ref.method3().bridge$getSystemTime() - this.startTime) / 1000.0 : (this.field3 - this.startTime) / 1000.0;
      }
   }

   public TextComponent method2(SkyblockDungeonTimer skyblockdungeontimer1) {
      double value2 = this.method1();
      String text4 = String.format("%." + skyblockdungeontimer1.method25().get() + "f", value2) + "s";
      Style style5;
      if (this.startTime == -1L) {
         style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method34());
      } else if (this.field3 == -1L) {
         if (skyblockdungeontimer1.method35().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field7 < 0.0) && !(value2 <= this.field7)) {
            if (skyblockdungeontimer1.method36().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field7 < 0.0) && !(value2 > this.field7)) {
               style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method34());
            } else {
               style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method36());
            }
         } else {
            style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method35());
         }
      } else if (skyblockdungeontimer1.method37().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field5 < 0.0) && !(value2 <= this.field5)) {
         if (skyblockdungeontimer1.method35().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field7 < 0.0) && !(value2 <= this.field7)) {
            if (skyblockdungeontimer1.method36().get() != com.moonsworth.lunar.client.config.option.NamedColorOption.OFF) {
               style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method36());
            } else {
               style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method34());
            }
         } else {
            style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method35());
         }
      } else {
         style5 = TextComponentFactory.styleOf(skyblockdungeontimer1.method37());
      }

      return (TextComponent)Component.text(text4).style(style5);
   }

   public TextComponent method3(SkyblockDungeonTimer skyblockdungeontimer1) {
      if (!(Boolean)skyblockdungeontimer1.method27().get()) {
         return Component.empty();
      }

      if (this.field7 < 0.0) {
         return Component.empty();
      }

      double value2 = this.method1();
      return value2 < this.field5 && this.field3 == -1L
         ? Component.empty()
         : Component.text(" (" + String.format("%+." + skyblockdungeontimer1.method25().get() + "f", value2 - this.field7) + "s)");
   }

   public TextComponent method4(SkyblockDungeonTimer skyblockdungeontimer1) {
      TextComponent text2 = (TextComponent)this.field1.append(Component.text(": "));
      TextComponent text3 = (TextComponent)this.method2(skyblockdungeontimer1).append(this.method3(skyblockdungeontimer1));
      TextComponent text4 = (TextComponent)((TextComponent)Component.empty().append(text2)).append(text3);
      if ((Boolean)skyblockdungeontimer1.method28().get()) {
         String text5 = this.field6 != null ? " (" + this.field6.method1() + ")" : " (0.00s)";
         text4 = (TextComponent)text4.append(Component.text(text5).color(NamedTextColor.GRAY));
      }

      return text4;
   }

   public void start(int number1) {
      this.startTime = Ref.method3().bridge$getSystemTime() + number1;
      if (this.field6 != null) {
         this.field6.destroy();
      }

      this.field6 = new Data().method3().method5(-number1).method6(true).method7().method2();
   }

   public void start() {
      this.start(0);
   }

   public void stop() {
      this.field3 = Ref.method3().bridge$getSystemTime();
      if (this.field6 != null) {
         this.field6.stop();
      }
   }

   public void reset() {
      this.startTime = -1L;
      this.field3 = -1L;
      if (this.field6 != null) {
         this.field6.destroy();
         this.field6 = null;
      }
   }

   public void method5(double value1) {
      this.field4 = value1;
      this.field7 = value1;
   }

   @Generated
   public TextComponent method6() {
      return this.field1;
   }

   @Generated
   public Pattern method7() {
      return this.field2;
   }

   @Generated
   public long getStartTime() {
      return this.startTime;
   }

   @Generated
   public long method8() {
      return this.field3;
   }

   @Generated
   public double method9() {
      return this.field4;
   }

   @Generated
   public double method10() {
      return this.field5;
   }

   @Generated
   public HudTimer method11() {
      return this.field6;
   }

   @Generated
   public double method12() {
      return this.field7;
   }

   @Generated
   public void setStartTime(long number1) {
      this.startTime = number1;
   }

   @Generated
   public void method14(long number1) {
      this.field3 = number1;
   }

   @Generated
   public void method15(double value1) {
      this.field4 = value1;
   }

   @Generated
   public void method16(double value1) {
      this.field5 = value1;
   }

   @Generated
   public void method17(HudTimer threadmoduledump451) {
      this.field6 = threadmoduledump451;
   }
}
