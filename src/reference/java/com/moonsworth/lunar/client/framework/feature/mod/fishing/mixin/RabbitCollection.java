package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;

public class RabbitCollection {
   @SerializedName("collection")
   private final HashMap<String, Fishing6> field1 = new HashMap<>();
   private transient boolean dirty;
   private transient List<TextComponent> field2;

   public RabbitCollection() {
   }

   public void method1(String text1, ItemRarity guitype32, boolean flag3) {
      this.field1.put(text1, new Fishing6(guitype32, flag3));
      this.field2 = null;
      this.dirty = true;
   }

   public List<TextComponent> method2(Style style1, Style style2, int number3, int number4) {
      if (this.field2 == null) {
         this.field2 = this.method3(style1, style2);
         if (this.method4(number3, number4)) {
            this.field2.add(Component.text("Please scroll through all pages.", NamedTextColor.RED));
         }
      }

      return this.field2;
   }

   public List<TextComponent> method3(Style style1, Style style2) {
      int number3 = 0;
      int index4 = 0;
      int number5 = 0;
      int index6 = 0;
      int number7 = 0;
      int index8 = 0;
      int number9 = 0;
      int index10 = 0;
      int number11 = 0;
      int index12 = 0;
      int number13 = 0;
      int index14 = 0;
      int number15 = 0;
      int index16 = 0;
      int number17 = 0;
      int index18 = 0;

      for (Fishing6 fishing620 : this.field1.values()) {
         int number21 = fishing620.method2() ? 1 : 0;
         ItemRarity guitype322 = fishing620.method1();
         if (guitype322 != null) {
            switch (guitype322) {
               case COMMON:
                  number5 += number21;
                  index6++;
                  break;
               case UNCOMMON:
                  number7 += number21;
                  index8++;
                  break;
               case RARE:
                  number9 += number21;
                  index10++;
                  break;
               case EPIC:
                  number11 += number21;
                  index12++;
                  break;
               case LEGENDARY:
                  number13 += number21;
                  index14++;
                  break;
               case MYTHIC:
                  number15 += number21;
                  index16++;
                  break;
               case DIVINE:
                  number17 += number21;
                  index18++;
                  break;
               default:
                  continue;
            }

            number3 += number21;
            index4++;
         }
      }

      ArrayList list23 = new ArrayList();
      TextComponent text24 = Component.text("/", NamedTextColor.GRAY);
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.empty().append(Component.text("Rabbit Collection: ", style1)))
                  .append(Component.text(number3, style2)))
               .append(text24))
            .append(Component.text(index4, style2))
      );
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Common Rabbits: ", NamedTextColor.WHITE).append(Component.text(number5, style2)))
               .append(text24))
            .append(Component.text(index6, style2))
      );
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Uncommon Rabbits: ", NamedTextColor.GREEN).append(Component.text(number7, style2)))
               .append(text24))
            .append(Component.text(index8, style2))
      );
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Rare Rabbits: ", NamedTextColor.BLUE).append(Component.text(number9, style2))).append(text24))
            .append(Component.text(index10, style2))
      );
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Epic Rabbits: ", NamedTextColor.DARK_PURPLE).append(Component.text(number11, style2)))
               .append(text24))
            .append(Component.text(index12, style2))
      );
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Legendary Rabbits: ", NamedTextColor.GOLD).append(Component.text(number13, style2)))
               .append(text24))
            .append(Component.text(index14, style2))
      );
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Mythic Rabbits: ", NamedTextColor.LIGHT_PURPLE).append(Component.text(number15, style2)))
               .append(text24))
            .append(Component.text(index16, style2))
      );
      list23.add(
         (TextComponent)((TextComponent)((TextComponent)Component.text("Divine Rabbits: ", NamedTextColor.AQUA).append(Component.text(number17, style2)))
               .append(text24))
            .append(Component.text(index18, style2))
      );
      return list23;
   }

   private boolean method4(int number1, int number2) {
      long number3 = this.field1.values().stream().filter(arg0 -> arg0.method1() != ItemRarity.NONE).count();
      if (number2 > number3) {
         return true;
      }

      int index5 = 0;

      for (Fishing6 fishing67 : this.field1.values()) {
         if (fishing67.method2()) {
            index5++;
         }
      }

      return number1 > index5;
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
   public void setDirty(boolean flag1) {
      this.dirty = flag1;
   }
}
