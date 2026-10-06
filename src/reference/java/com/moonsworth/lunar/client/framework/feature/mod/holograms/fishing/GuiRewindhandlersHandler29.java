package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightImpl;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class GuiRewindhandlersHandler29 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final List<String> field7 = ImmutableList.of(
      "THEORETICAL_HOE", "PUMPKIN_DICER", "MELON_DICER", "COCO_CHOPPER", "CACTUS_KNIFE", "FUNGI_CUTTER"
   );
   private static final Set<String> field8 = Set.of("ANCESTRAL_SPADE", "ARCHAIC_SPADE", "DEIFIC_SPADE");
   private ItemStackBridge field9;
   @NotNull
   private String id = "";
   @NotNull
   private String field10 = "";

   public GuiRewindhandlersHandler29() {
      this.handle(EventClientTick.class, this::method1);
   }

   private void method1(EventClientTick var1) {
      String var2 = this.field10;
      if (!this.method5()) {
         this.field9 = null;
         this.id = "";
         this.field10 = "";
      }

      if (!this.field10.equals(var2)) {
         ClientEventBus.method29().method12(HighlightImpl.class, () -> new HighlightImpl(this.field9, this.id, this.field10));
      }
   }

   private boolean method5() {
      if (!Click3.hasIsland()) {
         return false;
      }

      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 == null) {
         return false;
      }

      ItemStackBridge var2 = var1.bridge$getCurrentEquippedItem();
      if (var2 == null) {
         return false;
      }

      this.field9 = var2;
      this.id = Gui3.method2(var2);
      this.field10 = Gui3.method3(var2);
      return true;
   }

   public boolean method6() {
      for (String var2 : field7) {
         if (this.id.startsWith(var2)) {
            return true;
         }
      }

      return false;
   }

   public boolean method7() {
      for (String var2 : field8) {
         if (this.id.equals(var2)) {
            return true;
         }
      }

      return false;
   }

   @Generated
   public ItemStackBridge method8() {
      return this.field9;
   }

   @NotNull
   @Generated
   public String getId() {
      return this.id;
   }

   @NotNull
   @Generated
   public String method9() {
      return this.field10;
   }
}
