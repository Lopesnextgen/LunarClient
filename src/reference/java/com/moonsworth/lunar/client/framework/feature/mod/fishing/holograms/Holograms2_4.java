package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class Holograms2_4 {
   private final List<Holograms4Updater> field1;
   private final Holograms4Iterator field2;

   public TextComponent method1(Holograms4Updater var1) {
      String var2 = this.field2.method11(false);
      NamedTextColor var3 = this.field2.method30().method6().color();
      String var4 = var2 + " (" + this.field2.method30().method6() + ")";
      if (!this.method2()) {
         StringBuilder var5 = new StringBuilder(" Stacked with ");
         boolean var6 = true;

         for (Holograms4Updater var8 : this.field1) {
            if (var8 != var1) {
               if (!var6) {
                  var5.append(", ");
               }

               var5.append(var8.method20(true));
               var6 = false;
            }
         }

         var4 = var4 + var5.toString();
      }

      return Component.text(var4, var3);
   }

   public boolean method2() {
      return this.field1.size() == 1;
   }

   @Generated
   public Holograms2_4(List<Holograms4Updater> var1, Holograms4Iterator var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
