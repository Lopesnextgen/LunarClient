package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ActionBarStatsListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
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

   public ActionBarStatsListener() {
      this.method3(EventActionBarMessage.class, this::method2, 200);
   }

   protected boolean isEnabled() {
      return IslandUtils.isOnIsland();
   }

   public NamedTextColor method5() {
      float value1 = (float)this.field17 / this.field18;
      if (value1 > 0.5) {
         return NamedTextColor.GREEN;
      } else {
         return value1 > 0.2 ? NamedTextColor.YELLOW : NamedTextColor.RED;
      }
   }

   private void method2(EventActionBarMessage data21) {
      if (IslandUtils.isOnIsland()) {
         if (!data21.isCancelled()) {
            Skyblock skyblock2 = Ref.method4().method40().method82();
            String text3 = TextBridge.asLegacyString(data21.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI());
            Matcher matcher4 = field12.matcher(text3);
            if (matcher4.find()) {
               boolean flag5 = method3(matcher4, text3);
               String text6 = flag5 ? matcher4.group("current").substring(1) : matcher4.group("current");
               String text7 = matcher4.group("total");
               NumberUtils.method8(text6.replace(",", "")).ifPresent(arg1x -> this.field17 = arg1x);
               NumberUtils.method8(text7.replace(",", "")).ifPresent(arg1x -> this.field18 = arg1x);
               if (skyblock2.method82().isEnabled()) {
                  text3 = text3.replace(matcher4.group(0), "");
               }
            } else if (IslandUtils.getIsland() == SkyblockIsland.RIFT) {
               this.field17 = 0;
               this.field18 = 0;
            }

            matcher4 = field13.matcher(text3);
            if (matcher4.find() && skyblock2.method85().isEnabled()) {
               boolean flag12 = method3(matcher4, text3);
               String text16 = flag12 ? matcher4.group("def").substring(1) : matcher4.group("def");
               NumberUtils.method8(text16.replace(",", "")).ifPresent(arg1x -> this.field19 = arg1x);
               this.field20 = (int)(this.field17 * (1.0F + this.field19 / 100.0F));
               text3 = text3.replace(matcher4.group(0), "");
            } else if (IslandUtils.getIsland() == SkyblockIsland.RIFT) {
               this.field19 = 0;
               this.field20 = 0;
            }

            matcher4 = field14.matcher(text3);
            if (matcher4.find() && skyblock2.method84().isEnabled()) {
               boolean flag13 = method3(matcher4, text3);
               String text17 = flag13 ? matcher4.group("stacks").substring(1) : matcher4.group("stacks");
               NumberUtils.method8(text17.replaceAll(",", "")).ifPresent(arg1x -> this.field21 = arg1x);
               this.field22 = matcher4.group("type").charAt(0);
               text3 = text3.replace(matcher4.group(0), "");
            } else {
               this.field21 = 0;
               this.field22 = ' ';
            }

            matcher4 = field15.matcher(text3);
            if (matcher4.find() && skyblock2.method83().isEnabled()) {
               boolean flag14 = method3(matcher4, text3);
               String text18 = flag14 ? matcher4.group("current").substring(1) : matcher4.group("current");
               NumberUtils.method8(text18.replaceAll(",", "")).ifPresent(arg1x -> this.field23 = arg1x);
               NumberUtils.method8(matcher4.group("total").replaceAll(",", "")).ifPresent(arg1x -> this.field24 = arg1x);
               String text20 = matcher4.group("overflow");
               this.field25 = text20 != null ? NumberUtils.method3(text20.replaceAll(",", "")) : 0;
               text3 = text3.replace(matcher4.group(0), "");
            }

            matcher4 = field16.matcher(text3);
            if (matcher4.find() && skyblock2.method87().isEnabled()) {
               this.field26 = true;
               boolean flag15 = method3(matcher4, text3);
               String text19 = flag15 ? matcher4.group("current").substring(1) : matcher4.group("current");
               NumberUtils.method8(text19.replaceAll(",", "")).ifPresent(arg1x -> this.field27 = arg1x);
               NumberUtils.method8(matcher4.group("total").replaceAll(",", "")).ifPresent(arg1x -> this.field28 = arg1x);
               text3 = text3.replace(matcher4.group(0), "");
            } else {
               this.field26 = false;
            }

            data21.OHROCHICOIOICHOCRROORRCIIICIHO(Component.text(text3));
            data21.OOOHICCHHHRHCORIRCRHOCROROIOCR(true);
         }
      }
   }

   private static boolean method3(Matcher matcher0, String text1) {
      int index2 = matcher0.start() - 1;
      boolean flag3 = false;
      if (index2 >= 0 && index2 < text1.length()) {
         flag3 = text1.charAt(index2) == 167;
      }

      return flag3;
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
