package com.moonsworth.lunar.client.framework.feature.nickhider;

import com.moonsworth.lunar.bridge.GuiEditSignBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiScreenBookBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class NicknameListener implements EventBusAccess {
   private final Pattern field1 = Pattern.compile("you will be nicked as ?(?:[\\[\\]a-zA-Z0-9+]+)? (?<name>[a-zA-Z0-9_]{3,16}).");
   private final Pattern field2 = Pattern.compile("You are now nicked as (?<name>[a-zA-Z0-9_]{3,16})!");
   private String field3 = "";
   private final NickHider field4;

   public NicknameListener(NickHider nickhider1) {
      this.field4 = nickhider1;
      this.handle(EventScreenChange.class, this::method2);
      this.handle(TypedChatMessage.class, this::method1);
   }

   private void method1(TypedChatMessage data1) {
      if (ServerBrandWatcher.method8(KeystrokesType.HYPIXEL)) {
         String text2 = data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
         if (text2.equalsIgnoreCase("Your nick has been reset!")) {
            this.method4(true);
         } else {
            Matcher matcher3 = this.field2.matcher(text2);
            if (matcher3.find()) {
               String text4 = matcher3.group("name");
               this.method5(text4);
            }
         }
      }
   }

   private void method2(EventScreenChange highlightimpl71) {
      if (highlightimpl71.method1() != null) {
         if (highlightimpl71.method1().method1(GuiScreenBookBridge.class)) {
            GuiScreenBookBridge bridge5extension82 = (GuiScreenBookBridge)highlightimpl71.method1();
            String text3 = bridge5extension82.bridge$getPageContents(bridge5extension82.bridge$getCurrentPage());
            Matcher matcher4 = this.field1.matcher(ChatFormatting.getTextWithoutFormattingCodes(text3));
            if (matcher4.find()) {
               this.method5(matcher4.group("name"));
            }
         }
      } else {
         GuiScreenBridge bridge5extension65 = Ref.method3().bridge$getCurrentScreen();
         if (bridge5extension65 != null && bridge5extension65.method1(GuiEditSignBridge.class)) {
            GuiEditSignBridge bridge5extension3_26 = (GuiEditSignBridge)bridge5extension65;
            if ((bridge5extension3_26.bridge$getLine(2) + " " + bridge5extension3_26.bridge$getLine(3)).equalsIgnoreCase("Enter your desired username here")) {
               String text7 = bridge5extension3_26.bridge$getLine(0);
               this.method5(text7);
            }
         }
      }
   }

   public void method3() {
      if (!this.field3.isEmpty()) {
         this.field4.addNickname(this.field3, true);
      }
   }

   public void method4(boolean flag1) {
      if (!this.field3.isEmpty()) {
         this.field4.removeNickname(this.field3);
         if (flag1) {
            this.field3 = "";
         }
      }
   }

   public void method5(String text1) {
      this.method4(true);
      this.field3 = text1;
      this.method3();
   }

   @Generated
   public String method6() {
      return this.field3;
   }

   @Generated
   public void method7(String text1) {
      this.field3 = text1;
   }
}
