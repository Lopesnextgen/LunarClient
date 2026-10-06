package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

public class Fishing4 {
   @SerializedName("collection")
   private final HashMap<String, Fishing6> field1 = new HashMap<>();
   private transient boolean dirty;
   private transient List<TextComponent> field2;

   public void method1(String var1, GuiType3 var2, boolean var3) {
      this.field1.put(var1, new Fishing6(var2, var3));
      this.field2 = null;
      this.dirty = true;
   }

   public List<TextComponent> method2(Style var1, Style var2, int var3, int var4) {
      if (this.field2 == null) {
         this.field2 = this.method3(var1, var2);
         if (this.method4(var3, var4)) {
            this.field2.add(Component.text("Please scroll through all pages.", NamedTextColor.RED));
         }
      }

      return this.field2;
   }

   public List<TextComponent> method3(Style var1, Style var2) {
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      int var12 = 0;
      int var13 = 0;
      int var14 = 0;
      int var15 = 0;
      int var16 = 0;
      int var17 = 0;
      int var18 = 0;

      for (Fishing6 var20 : this.field1.values()) {
         int var21 = var20.method2() ? 1 : 0;
         GuiType3 var22 = var20.method1();
         if (var22 != null) {
            switch (var22) {
               case COMMON:
                  var5 += var21;
                  var6++;
                  break;
               case UNCOMMON:
                  var7 += var21;
                  var8++;
                  break;
               case RARE:
                  var9 += var21;
                  var10++;
                  break;
               case EPIC:
                  var11 += var21;
                  var12++;
                  break;
               case LEGENDARY:
                  var13 += var21;
                  var14++;
                  break;
               case MYTHIC:
                  var15 += var21;
                  var16++;
                  break;
               case DIVINE:
                  var17 += var21;
                  var18++;
                  break;
               default:
                  continue;
            }

            var3 += var21;
            var4++;
         }
      }

      ArrayList var23 = new ArrayList();
      TextComponent var24 = Component.text("/", NamedTextColor.GRAY);
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.empty().append(Component.text("Rabbit Collection: ", var1)))
                  .append(Component.text(var3, var2)))
               .append(var24))
            .append(Component.text(var4, var2))
      );
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Common Rabbits: ", NamedTextColor.WHITE).append(Component.text(var5, var2)))
               .append(var24))
            .append(Component.text(var6, var2))
      );
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Uncommon Rabbits: ", NamedTextColor.GREEN).append(Component.text(var7, var2)))
               .append(var24))
            .append(Component.text(var8, var2))
      );
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Rare Rabbits: ", NamedTextColor.BLUE).append(Component.text(var9, var2))).append(var24))
            .append(Component.text(var10, var2))
      );
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Epic Rabbits: ", NamedTextColor.DARK_PURPLE).append(Component.text(var11, var2)))
               .append(var24))
            .append(Component.text(var12, var2))
      );
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Legendary Rabbits: ", NamedTextColor.GOLD).append(Component.text(var13, var2)))
               .append(var24))
            .append(Component.text(var14, var2))
      );
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Mythic Rabbits: ", NamedTextColor.LIGHT_PURPLE).append(Component.text(var15, var2)))
               .append(var24))
            .append(Component.text(var16, var2))
      );
      var23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Divine Rabbits: ", NamedTextColor.AQUA).append(Component.text(var17, var2)))
               .append(var24))
            .append(Component.text(var18, var2))
      );
      return var23;
   }

   private boolean method4(int var1, int var2) {
      long var3 = this.field1.values().stream().filter(var0 -> var0.method1() != GuiType3.NONE).count();
      if (var2 > var3) {
         return true;
      }

      int var5 = 0;

      for (Fishing6 var7 : this.field1.values()) {
         if (var7.method2()) {
            var5++;
         }
      }

      return var1 > var5;
   }

   @Generated
   public HashMap<String, Fishing6> method5() {
      return this.field1;
   }

   @Generated
   public boolean isDirty() {
      return this.dirty;
   }

   @Generated
   public List<TextComponent> method7() {
      return this.field2;
   }

   @Generated
   public void setDirty(boolean var1) {
      this.dirty = var1;
   }
}
