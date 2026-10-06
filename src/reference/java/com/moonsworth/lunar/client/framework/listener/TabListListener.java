package com.moonsworth.lunar.client.framework.listener;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class TabListListener extends DynamicListener {
   private ImmutableList<Component> field7 = ImmutableList.of();
   private ImmutableList<String> field8 = ImmutableList.of();

   public TabListListener() {
      this.method12(EventTabListUpdate.class, this::method1, 200);
   }

   private void method1(EventTabListUpdate highlightimpl31) {
      List list2 = Ref.method3().bridge$getGuiIngame().bridge$getPlayerInfoList();
      ArrayList list3 = new ArrayList();
      ArrayList list4 = new ArrayList();

      for (PlayerInfoBridge bridge2_336 : list2) {
         Component component7 = bridge2_336.bridge$getDisplayName();
         if (!component7.equals(Component.empty())) {
            list3.add(component7);
         }
      }

      for (Component component9 : list3) {
         list4.add(TextBridge.getTextContent(component9));
      }

      this.field7 = ImmutableList.copyOf(list3);
      this.field8 = ImmutableList.copyOf(list4);
   }

   @Override
   protected void onEnable() {
      GuiIngameBridge bridge5extension91 = Ref.method3().bridge$getGuiIngame();
      if (bridge5extension91 != null) {
         LunarEventBus.method29().method12(EventTabListUpdate.class, EventTabListUpdate::new);
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
