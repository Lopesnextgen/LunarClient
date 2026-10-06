package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.gui.ServerTickEvent;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class GuiRewindhandlersHandler212 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^Profile ID: ([0-9a-f-]{36})$");
   private String field8;
   private String field9;
   private int field10;

   public GuiRewindhandlersHandler212() {
      this.handle(Data.class, this::method1);
      this.handle(ServerTickEvent.class, this::method2);
   }

   private void method1(Data var1) {
      String var2 = AdventureChatFormatting.getTextWithoutFormattingCodes(var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
      Matcher var3 = field7.matcher(var2);
      if (var3.matches()) {
         String var4 = this.field8;
         String var5 = var3.group(1);
         String var6 = this.field9;
         Memory var7 = ThreadModuleDump63.method4().method31();
         if (var7 != null) {
            String var8 = var7.method10().toString();
            this.field8 = var5;
            this.field9 = var8;
            ClientEventBus.method29().method12(Rewindhandlers.Data13.class, () -> new Rewindhandlers.Data13(var5));
            if (!var5.equals(var4) || !var8.equals(var6)) {
               ClientEventBus.method29().method12(Rewindhandlers.Data15.class, () -> new Rewindhandlers.Data15(var4, var5));
            }
         }
      }
   }

   private void method2(ServerTickEvent var1) {
      if (this.field10 > 0 && --this.field10 <= 0) {
         Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
         if (var2 != null) {
            if (this.field8 == null && Click3.hasIsland()) {
               var2.bridge$sendChatMessage("/profileid");
            }
         }
      }
   }

   public void onEnable() {
      this.field10 = 80;
   }

   public String getKey() {
      return this.field9 + " - " + this.field8;
   }

   public String method3(String var1) {
      return this.field9 + " - " + var1;
   }

   @Generated
   public String method5() {
      return this.field8;
   }

   @Generated
   public String method6() {
      return this.field9;
   }

   @Generated
   public int method7() {
      return this.field10;
   }
}
