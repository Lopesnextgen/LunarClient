package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework3;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms10;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms8_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data10;
import com.moonsworth.lunar.client.highlight.mixin.HighlightImpl.Data;
import com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightBase4.Data3;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import org.joml.Vector3i;

public class SkyblockQuiz extends Framework7Extension2 {
   private final Instant field8 = Instant.parse("2019-06-11T17:55:00.00Z");
   private final List<String> field9 = new ArrayList<>();
   private Vector3i field10 = null;
   private char field11 = ' ';
   private int field12;

   public SkyblockQuiz(SkyblockDungeonPuzzles var1, LightingExtension443 var2) {
      super(true);
      this.method3(Framework.field16, Framework4.method4(false, var1));
      this.method3(Framework.field6, Framework3.method7(var2));
      this.method4(this::clear);
      this.handle(Data3.class, var1x -> this.clear());
      this.handle(Data.class, this::method5);
      this.handle(HighlightImpl2.class, this::method7);
      this.handle(Data10.class, this::method3);
   }

   public String getId() {
      return "SKYBLOCK_QUIZ";
   }

   protected void method1(boolean var1) {
   }

   public void method3(boolean var1) {
      if (var1) {
         SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
         var2.method13();
      }
   }

   private void method3(Data10 var1) {
      this.field12 = this.method13();
   }

   public void method4(Holograms4Iterator var1) {
      switch (this.field11) {
         case 'ⓐ':
            this.field10 = this.method9(var1);
            break;
         case 'ⓑ':
            this.field10 = this.method10(var1);
            break;
         case 'ⓒ':
            this.field10 = this.method11(var1);
      }
   }

   private void method5(Data var1) {
      if (Click3.method2() == Gui2Extension3.DUNGEON) {
         String var2 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH().trim();
         if (!var2.isEmpty()) {
            if (!this.method6(var1, var2)) {
               Skyblock var3 = ThreadModuleDump63.method4().method40().method82();
               Holograms10 var4 = var3.method15().method9();
               if (var4 != null) {
                  if (!var4.method4().matcher(var2).matches() && !var4.method5().matcher(var2).matches()) {
                     if (var4.method1().equals(var2)) {
                        this.field9.clear();
                        this.field9.add("Year " + this.field12);
                     } else if (var4.method2().equals(var2)) {
                        this.field9.clear();
                        this.field9.add(var4.method3());
                     } else {
                        for (Holograms8_2 var6 : var4.method6()) {
                           Matcher var7 = var6.method1().matcher(var2);
                           if (var7.matches()) {
                              this.field9.clear();
                              if (var6.method4() != -1) {
                                 String var8 = var7.group(var6.method4());
                                 this.field9.add((String)var6.method3().get(var8));
                              } else {
                                 this.field9.addAll(var6.method2());
                              }

                              return;
                           }
                        }
                     }
                  } else {
                     this.field10 = null;
                  }
               }
            }
         }
      }
   }

   private boolean method6(Data var1, String var2) {
      if (var2.charAt(0) == 9424 || var2.charAt(0) == 9425 || var2.charAt(0) == 9426) {
         char var3 = var2.charAt(0);
         var2 = var2.substring(2);
         if (this.field9.contains(var2)) {
            this.field11 = var3;
            SkyblockDungeonPuzzles var4 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
            Holograms4Iterator var5 = var4.method47();
            if (var5 != null && var5.method25() == HologramsType8.QUIZ) {
               switch (var3) {
                  case 'ⓐ':
                     this.field10 = this.method9(var5);
                     break;
                  case 'ⓑ':
                     this.field10 = this.method10(var5);
                     break;
                  case 'ⓒ':
                     this.field10 = this.method11(var5);
               }
            }

            var1.OHROCHICOIOICHOCRROORRCIIICIHO(Fishing2_2.method2(var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI(), "Correct answer!"));
            return true;
         }
      }

      return false;
   }

   private void clear() {
      this.field9.clear();
      this.field10 = null;
      this.field11 = ' ';
   }

   private void method7(HighlightImpl2 var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.QUIZ) {
         if (this.field10 != null) {
            BridgeExtension_9 var4 = var1.method3();
            Bridge2_43 var5 = ThreadModuleDump63.method13();
            var4.push();
            var4.translate(-var5.bridge$renderPosX(), -var5.bridge$renderPosY(), -var5.bridge$renderPosZ());
            Click.method1(var4, this.field10);
            var4.pop();
         }
      }
   }

   private int method13() {
      Duration var1 = Duration.between(this.field8, Instant.now());
      long var2 = var1.toHours();
      if (var2 <= 2147483647L && var2 >= 0L) {
         return (int)var2 / 124 + 1;
      }

      Inventorymod2.method5(new Exception("DurationOutOfBounds"), "SkyBlockQuizHelper");
      return -1;
   }

   private Vector3i method9(Holograms4Iterator var1) {
      return ((Holograms3)var1.method23().get()).method1(Bridge.method8().method4(20, 70, 6)).bridge$toJoml();
   }

   private Vector3i method10(Holograms4Iterator var1) {
      return ((Holograms3)var1.method23().get()).method1(Bridge.method8().method4(15, 70, 9)).bridge$toJoml();
   }

   private Vector3i method11(Holograms4Iterator var1) {
      return ((Holograms3)var1.method23().get()).method1(Bridge.method8().method4(10, 70, 6)).bridge$toJoml();
   }
}
