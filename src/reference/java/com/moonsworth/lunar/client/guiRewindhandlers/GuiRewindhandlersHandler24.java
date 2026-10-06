package com.moonsworth.lunar.client.guiRewindhandlers;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.TabListUpdateEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler24 extends DynamicListener {
   private ImmutableList<Component> field7 = ImmutableList.of();
   private ImmutableList<String> field8 = ImmutableList.of();

   public GuiRewindhandlersHandler24() {
      this.method11(TabListUpdateEvent.class, this::method1, 200);
   }

   private void method1(TabListUpdateEvent var1) {
      List var2 = ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getPlayerInfoList();
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();

      for (Bridge2_33 var6 : var2) {
         Component var7 = var6.bridge$getDisplayName();
         if (!var7.equals(Component.empty())) {
            var3.add(var7);
         }
      }

      for (Component var9 : var3) {
         var4.add(AdventureTextBridge.getTextContent(var9));
      }

      this.field7 = ImmutableList.copyOf(var3);
      this.field8 = ImmutableList.copyOf(var4);
   }

   @Override
   protected void onEnable() {
      Bridge5Extension9 var1 = ThreadModuleDump63.method3().bridge$getGuiIngame();
      if (var1 != null) {
         ClientEventBus.method29().method12(TabListUpdateEvent.class, TabListUpdateEvent::new);
      }
   }

   @Generated
   public ImmutableList<Component> method5() {
      return this.field7;
   }

   @Generated
   public ImmutableList<String> method6() {
      return this.field8;
   }
}
