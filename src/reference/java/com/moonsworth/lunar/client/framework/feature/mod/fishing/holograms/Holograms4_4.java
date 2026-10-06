package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click12;
import com.moonsworth.lunar.client.mod.skyblock.dungeontimer.SkyblockDungeonTimer;
import com.moonsworth.lunar.client.util.ThreadModuleDump45;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump45.Data;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

public class Holograms4_4 {
   private final TextComponent field1;
   private final Pattern field2;
   private long startTime = -1L;
   private long field3 = -1L;
   private double field4 = -1.0;
   private double field5 = -1.0;
   private ThreadModuleDump45 field6;
   private double field7 = -1.0;

   public Holograms4_4(TextComponent var1, String var2) {
      this.field1 = var1;
      this.field2 = Pattern.compile("^" + var2 + "$");
   }

   public Holograms4_4(Holograms4_4 var1) {
      this.field1 = var1.method6();
      this.field2 = var1.method7();
      this.field4 = var1.method9();
      this.field5 = var1.method10();
   }

   public double method1() {
      if (this.startTime == -1L) {
         return 0.0;
      } else {
         return this.field3 == -1L ? (ThreadModuleDump63.method3().bridge$getSystemTime() - this.startTime) / 1000.0 : (this.field3 - this.startTime) / 1000.0;
      }
   }

   public TextComponent method2(SkyblockDungeonTimer var1) {
      double var2 = this.method1();
      String var4 = String.format("%." + var1.method25().get() + "f", var2) + "s";
      Style var5;
      if (this.startTime == -1L) {
         var5 = Click12.getStyle(var1.method34());
      } else if (this.field3 == -1L) {
         if (var1.method35().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field7 < 0.0) && !(var2 <= this.field7)) {
            if (var1.method36().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field7 < 0.0) && !(var2 > this.field7)) {
               var5 = Click12.getStyle(var1.method34());
            } else {
               var5 = Click12.getStyle(var1.method36());
            }
         } else {
            var5 = Click12.getStyle(var1.method35());
         }
      } else if (var1.method37().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field5 < 0.0) && !(var2 <= this.field5)) {
         if (var1.method35().get() == com.moonsworth.lunar.client.config.option.NamedColorOption.OFF || !(this.field7 < 0.0) && !(var2 <= this.field7)) {
            if (var1.method36().get() != com.moonsworth.lunar.client.config.option.NamedColorOption.OFF) {
               var5 = Click12.getStyle(var1.method36());
            } else {
               var5 = Click12.getStyle(var1.method34());
            }
         } else {
            var5 = Click12.getStyle(var1.method35());
         }
      } else {
         var5 = Click12.getStyle(var1.method37());
      }

      return (TextComponent)Component.text(var4).style(var5);
   }

   public TextComponent method3(SkyblockDungeonTimer var1) {
      if (!(Boolean)var1.method27().get()) {
         return Component.empty();
      }

      if (this.field7 < 0.0) {
         return Component.empty();
      }

      double var2 = this.method1();
      return var2 < this.field5 && this.field3 == -1L
         ? Component.empty()
         : Component.text(" (" + String.format("%+." + var1.method25().get() + "f", var2 - this.field7) + "s)");
   }

   public TextComponent method4(SkyblockDungeonTimer var1) {
      TextComponent var2 = (TextComponent)this.field1.append(Component.text(": "));
      TextComponent var3 = (TextComponent)this.method2(var1).append(this.method3(var1));
      TextComponent var4 = (TextComponent)((TextComponent)Component.empty().append(var2)).append(var3);
      if ((Boolean)var1.method28().get()) {
         String var5 = this.field6 != null ? " (" + this.field6.method1() + ")" : " (0.00s)";
         var4 = (TextComponent)var4.append(Component.text(var5).color(NamedTextColor.GRAY));
      }

      return var4;
   }

   public void start(int var1) {
      this.startTime = ThreadModuleDump63.method3().bridge$getSystemTime() + var1;
      if (this.field6 != null) {
         this.field6.destroy();
      }

      this.field6 = new Data().method3().method5(-var1).method6(true).method7().method2();
   }

   public void start() {
      this.start(0);
   }

   public void stop() {
      this.field3 = ThreadModuleDump63.method3().bridge$getSystemTime();
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

   public void method5(double var1) {
      this.field4 = var1;
      this.field7 = var1;
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
   public ThreadModuleDump45 method11() {
      return this.field6;
   }

   @Generated
   public double method12() {
      return this.field7;
   }

   @Generated
   public void setStartTime(long var1) {
      this.startTime = var1;
   }

   @Generated
   public void method14(long var1) {
      this.field3 = var1;
   }

   @Generated
   public void method15(double var1) {
      this.field4 = var1;
   }

   @Generated
   public void method16(double var1) {
      this.field5 = var1;
   }

   @Generated
   public void method17(ThreadModuleDump45 var1) {
      this.field6 = var1;
   }
}
