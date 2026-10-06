package com.moonsworth.lunar.client.guiRewindhandlers;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler2_2 extends DynamicListener {
   private String field7 = null;
   private Bridge5Extension_3 field8 = null;

   public GuiRewindhandlersHandler2_2() {
      this.method11(ScreenChangeEvent.class, this::method1, 200);
   }

   private void method1(ScreenChangeEvent var1) {
      Bridge5Extension6 var2 = var1.method1();
      if (var2 instanceof Bridge5Extension_3 var3) {
         this.field8 = var3;
         this.field7 = this.method2(var3.bridge$title());
      } else if (var2 == null) {
         this.field8 = null;
         this.field7 = null;
      }
   }

   @Override
   protected void onEnable() {
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension_3 var1) {
         this.field8 = var1;
         this.field7 = this.method2(var1.bridge$title());
      }
   }

   @Override
   protected void onDisable() {
      this.field8 = null;
      this.field7 = null;
   }

   @Nullable
   private String method2(Bridge2_42 var1) {
      Component var2 = AdventureTextBridge.asAdventure(var1);
      if (var2 instanceof TranslatableComponent var3) {
         return var3.key().trim();
      } else {
         return var2 instanceof TextComponent ? AdventureTextBridge.getTextContent(var2).trim() : null;
      }
   }

   @Generated
   public String method5() {
      return this.field7;
   }

   @Generated
   public Bridge5Extension_3 method6() {
      return this.field8;
   }
}
