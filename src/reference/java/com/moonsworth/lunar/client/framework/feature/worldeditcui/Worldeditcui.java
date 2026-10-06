package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventWorldEditSelection;
import java.util.Locale;

public final class Worldeditcui {
   private WorldeditcuiType field1 = WorldeditcuiType.CUBOID;
   private WorldeditSelection field2 = new CuboidSelection();

   public Worldeditcui() {
   }

   public WorldeditcuiType method1() {
      return this.field1;
   }

   public WorldeditSelection method2() {
      return this.field2;
   }

   public void method3(String text) {
      String[] items2 = text.split("\\|");
      if (items2[0].equalsIgnoreCase("s")) {
         this.field2 = (this.field1 = WorldeditcuiType.valueOf(items2[1].toUpperCase(Locale.ROOT))).create();
         LunarEventBus.method29().method12(EventWorldEditSelection.class, () -> new EventWorldEditSelection(this.field2));
      } else if (items2[0].equalsIgnoreCase("p")) {
         this.field2.method3(Integer.parseInt(items2[1]), Double.parseDouble(items2[2]), Double.parseDouble(items2[3]), Double.parseDouble(items2[4]));
         if (this.field2 instanceof VersionedSelection) {
            ((VersionedSelection)this.field2).method2(Long.parseLong(items2[5]));
         }

         LunarEventBus.method29().method12(EventWorldEditSelection.class, () -> new EventWorldEditSelection(this.field2));
      }
   }
}
