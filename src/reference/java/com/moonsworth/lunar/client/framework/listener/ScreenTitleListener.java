package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class ScreenTitleListener extends DynamicListener {
   private String field7 = null;
   private GuiContainerBridge field8 = null;

   public ScreenTitleListener() {
      this.method12(EventScreenChange.class, this::method1, 200);
   }

   private void method1(EventScreenChange highlightimpl71) {
      GuiScreenBridge bridge5extension62 = highlightimpl71.method1();
      if (bridge5extension62 instanceof GuiContainerBridge bridge5extension_33) {
         this.field8 = bridge5extension_33;
         this.field7 = this.method2(bridge5extension_33.bridge$title());
      } else if (bridge5extension62 == null) {
         this.field8 = null;
         this.field7 = null;
      }
   }

   @Override
   protected void onEnable() {
      if (Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_31) {
         this.field8 = bridge5extension_31;
         this.field7 = this.method2(bridge5extension_31.bridge$title());
      }
   }

   @Override
   protected void onDisable() {
      this.field8 = null;
      this.field7 = null;
   }

   @Nullable
   private String method2(Bridge2_42 bridge2_421) {
      Component component2 = TextBridge.asAdventure(bridge2_421);
      if (component2 instanceof TranslatableComponent translatablecomponent3) {
         return translatablecomponent3.key().trim();
      } else {
         return component2 instanceof TextComponent ? TextBridge.getTextContent(component2).trim() : null;
      }
   }

   @Generated
   public String method5() {
      return this.field7;
   }

   @Generated
   public GuiContainerBridge method6() {
      return this.field8;
   }
}
