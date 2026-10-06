package com.moonsworth.lunar.client.framework.feature.nickhider;

import com.moonsworth.lunar.bridge.Bridge5Extension3_2;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension8;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class Highlight3Handler implements EventRegistrar {
   private final Pattern field1 = Pattern.compile("you will be nicked as ?(?:[\\[\\]a-zA-Z0-9+]+)? (?<name>[a-zA-Z0-9_]{3,16}).");
   private final Pattern field2 = Pattern.compile("You are now nicked as (?<name>[a-zA-Z0-9_]{3,16})!");
   private String field3 = "";
   private final NickHider field4;

   public Highlight3Handler(NickHider var1) {
      this.field4 = var1;
      this.handle(ScreenChangeEvent.class, this::method2);
      this.handle(Data.class, this::method1);
   }

   private void method1(Data var1) {
      if (Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
         String var2 = var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
         if (var2.equalsIgnoreCase("Your nick has been reset!")) {
            this.method4(true);
         } else {
            Matcher var3 = this.field2.matcher(var2);
            if (var3.find()) {
               String var4 = var3.group("name");
               this.method5(var4);
            }
         }
      }
   }

   private void method2(ScreenChangeEvent var1) {
      if (var1.method1() != null) {
         if (var1.method1().method1(Bridge5Extension8.class)) {
            Bridge5Extension8 var2 = (Bridge5Extension8)var1.method1();
            String var3 = var2.bridge$getPageContents(var2.bridge$getCurrentPage());
            Matcher var4 = this.field1.matcher(AdventureChatFormatting.getTextWithoutFormattingCodes(var3));
            if (var4.find()) {
               this.method5(var4.group("name"));
            }
         }
      } else {
         Bridge5Extension6 var5 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         if (var5 != null && var5.method1(Bridge5Extension3_2.class)) {
            Bridge5Extension3_2 var6 = (Bridge5Extension3_2)var5;
            if ((var6.bridge$getLine(2) + " " + var6.bridge$getLine(3)).equalsIgnoreCase("Enter your desired username here")) {
               String var7 = var6.bridge$getLine(0);
               this.method5(var7);
            }
         }
      }
   }

   public void method3() {
      if (!this.field3.isEmpty()) {
         this.field4.addNickname(this.field3, true);
      }
   }

   public void method4(boolean var1) {
      if (!this.field3.isEmpty()) {
         this.field4.removeNickname(this.field3);
         if (var1) {
            this.field3 = "";
         }
      }
   }

   public void method5(String var1) {
      this.method4(true);
      this.field3 = var1;
      this.method3();
   }

   @Generated
   public String method6() {
      return this.field3;
   }

   @Generated
   public void method7(String var1) {
      this.field3 = var1;
   }
}
