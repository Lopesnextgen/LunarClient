package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data2;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class GuiRewindhandlersHandler24 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final String field7 = "[❤\ue010]";
   private static final String field8 = "[❈\ue008]";
   private static final String field9 = "[✎\ue003]";
   private static final String field10 = "[ʬ\ue017]";
   private static final String field11 = "[♨\ue028]";
   private static final Pattern field12 = Pattern.compile("(?<current>[0-9,]+)/(?<total>[0-9,]+)[❤\ue010]");
   private static final Pattern field13 = Pattern.compile("(?<def>[0-9,]+)[❈\ue008]( Defense)?");
   private static final Pattern field14 = Pattern.compile("(?<stacks>[0-9]+)(?<type>[ѫ⁑ᝐ⚶҉])");
   private static final Pattern field15 = Pattern.compile("(?<current>[0-9,]+)/(?<total>[0-9,]+)[✎\ue003]( (§3(?<overflow>[0-9,]+)[ʬ\ue017]|Mana))?");
   private static final Pattern field16 = Pattern.compile("(?<current>[0-9,]+)/(?<total>[0-9,]+)[♨\ue028]");
   private int field17;
   private int field18;
   private int field19;
   private int field20;
   private int field21;
   private char field22;
   private int field23;
   private int field24;
   private int field25;
   private boolean field26;
   private int field27;
   private int field28;

   public GuiRewindhandlersHandler24() {
      this.method3(Data2.class, this::method2, 200);
   }

   protected boolean isEnabled() {
      return Click3.hasIsland();
   }

   public NamedTextColor method5() {
      float var1 = (float)this.field17 / this.field18;
      if (var1 > 0.5) {
         return NamedTextColor.GREEN;
      } else {
         return var1 > 0.2 ? NamedTextColor.YELLOW : NamedTextColor.RED;
      }
   }

   private void method2(Data2 var1) {
      if (Click3.hasIsland()) {
         if (!var1.isCancelled()) {
            Skyblock var2 = ThreadModuleDump63.method4().method40().method82();
            String var3 = AdventureTextBridge.asLegacyString(var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI());
            Matcher var4 = field12.matcher(var3);
            if (var4.find()) {
               boolean var5 = method3(var4, var3);
               String var6 = var5 ? var4.group("current").substring(1) : var4.group("current");
               String var7 = var4.group("total");
               ThreadModuleDump40.method8(var6.replace(",", "")).ifPresent(var1x -> this.field17 = var1x);
               ThreadModuleDump40.method8(var7.replace(",", "")).ifPresent(var1x -> this.field18 = var1x);
               if (var2.method82().isEnabled()) {
                  var3 = var3.replace(var4.group(0), "");
               }
            } else if (Click3.getIsland() == Gui2Extension3.RIFT) {
               this.field17 = 0;
               this.field18 = 0;
            }

            var4 = field13.matcher(var3);
            if (var4.find() && var2.method85().isEnabled()) {
               boolean var12 = method3(var4, var3);
               String var16 = var12 ? var4.group("def").substring(1) : var4.group("def");
               ThreadModuleDump40.method8(var16.replace(",", "")).ifPresent(var1x -> this.field19 = var1x);
               this.field20 = (int)(this.field17 * (1.0F + this.field19 / 100.0F));
               var3 = var3.replace(var4.group(0), "");
            } else if (Click3.getIsland() == Gui2Extension3.RIFT) {
               this.field19 = 0;
               this.field20 = 0;
            }

            var4 = field14.matcher(var3);
            if (var4.find() && var2.method84().isEnabled()) {
               boolean var13 = method3(var4, var3);
               String var17 = var13 ? var4.group("stacks").substring(1) : var4.group("stacks");
               ThreadModuleDump40.method8(var17.replaceAll(",", "")).ifPresent(var1x -> this.field21 = var1x);
               this.field22 = var4.group("type").charAt(0);
               var3 = var3.replace(var4.group(0), "");
            } else {
               this.field21 = 0;
               this.field22 = ' ';
            }

            var4 = field15.matcher(var3);
            if (var4.find() && var2.method83().isEnabled()) {
               boolean var14 = method3(var4, var3);
               String var18 = var14 ? var4.group("current").substring(1) : var4.group("current");
               ThreadModuleDump40.method8(var18.replaceAll(",", "")).ifPresent(var1x -> this.field23 = var1x);
               ThreadModuleDump40.method8(var4.group("total").replaceAll(",", "")).ifPresent(var1x -> this.field24 = var1x);
               String var20 = var4.group("overflow");
               this.field25 = var20 != null ? ThreadModuleDump40.method3(var20.replaceAll(",", "")) : 0;
               var3 = var3.replace(var4.group(0), "");
            }

            var4 = field16.matcher(var3);
            if (var4.find() && var2.method87().isEnabled()) {
               this.field26 = true;
               boolean var15 = method3(var4, var3);
               String var19 = var15 ? var4.group("current").substring(1) : var4.group("current");
               ThreadModuleDump40.method8(var19.replaceAll(",", "")).ifPresent(var1x -> this.field27 = var1x);
               ThreadModuleDump40.method8(var4.group("total").replaceAll(",", "")).ifPresent(var1x -> this.field28 = var1x);
               var3 = var3.replace(var4.group(0), "");
            } else {
               this.field26 = false;
            }

            var1.<init>(Component.text(var3));
            var1.method9(true);
         }
      }
   }

   private static boolean method3(Matcher var0, String var1) {
      int var2 = var0.start() - 1;
      boolean var3 = false;
      if (var2 >= 0 && var2 < var1.length()) {
         var3 = var1.charAt(var2) == 167;
      }

      return var3;
   }

   @Generated
   public int method6() {
      return this.field17;
   }

   @Generated
   public int method7() {
      return this.field18;
   }

   @Generated
   public int method8() {
      return this.field19;
   }

   @Generated
   public int method9() {
      return this.field20;
   }

   @Generated
   public int method10() {
      return this.field21;
   }

   @Generated
   public char method11() {
      return this.field22;
   }

   @Generated
   public int method12() {
      return this.field23;
   }

   @Generated
   public int method13() {
      return this.field24;
   }

   @Generated
   public int method15() {
      return this.field25;
   }

   @Generated
   public boolean method16() {
      return this.field26;
   }

   @Generated
   public int method17() {
      return this.field27;
   }

   @Generated
   public int method18() {
      return this.field28;
   }
}
