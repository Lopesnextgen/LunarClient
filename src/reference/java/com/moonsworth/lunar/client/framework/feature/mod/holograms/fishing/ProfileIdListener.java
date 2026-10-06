package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerTick;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class ProfileIdListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^Profile ID: ([0-9a-f-]{36})$");
   private String field8;
   private String field9;
   private int field10;

   public ProfileIdListener() {
      this.handle(TypedChatMessage.class, this::method1);
      this.handle(EventServerTick.class, this::method2);
   }

   private void method1(TypedChatMessage data1) {
      String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
      Matcher matcher3 = field7.matcher(text2);
      if (matcher3.matches()) {
         String text4 = this.field8;
         String text5 = matcher3.group(1);
         String text6 = this.field9;
         Memory memory7 = Ref.method4().method31();
         if (memory7 != null) {
            String text8 = memory7.method10().toString();
            this.field8 = text5;
            this.field9 = text8;
            LunarEventBus.method29().method12(SkyblockProfileEvents.SkyblockProfileIdEvent.class, () -> new SkyblockProfileEvents.SkyblockProfileIdEvent(text5));
            if (!text5.equals(text4) || !text8.equals(text6)) {
               LunarEventBus.method29().method12(SkyblockProfileEvents.SkyblockProfileChangeEvent.class, () -> new SkyblockProfileEvents.SkyblockProfileChangeEvent(text4, text5));
            }
         }
      }
   }

   private void method2(EventServerTick highlightimpl91) {
      if (this.field10 > 0 && --this.field10 <= 0) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            if (this.field8 == null && IslandUtils.isOnIsland()) {
               bridge5extension_52.bridge$sendChatMessage("/profileid");
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

   public String method3(String text1) {
      return this.field9 + " - " + text1;
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
