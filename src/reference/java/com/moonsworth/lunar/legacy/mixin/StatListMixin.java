package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;
import net.minecraft.util.IChatComponent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@VersionGate(max = 0)
@Mixin(StatList.class)
public class StatListMixin {
   public StatListMixin() {
   }

   @Redirect(method = "func_151181_c$v1_7", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getEnableStats()Z"))
   private static boolean lunar$useItemName$mineBlock(Block block0, @Share("mineId") LocalRef<String> localref1) {
      if (!block0.getEnableStats()) {
         return false;
      }

      Item item2 = Item.getItemFromBlock(block0);
      String text3 = lunar$getFormattedItemName(item2);
      if (text3 == null) {
         return false;
      }

      localref1.set("stat.mineBlock." + text3);
      return true;
   }

   @Redirect(method = "func_151181_c$v1_7", at = @At(value = "NEW", target = "net/minecraft/stats/StatCrafting"))
   private static StatCrafting lunar$modifyStatId$mineBlock(String text0, IChatComponent text1, Item item2, @Share("mineId") LocalRef<String> localref3) {
      return new StatCrafting((String)localref3.get(), text1, item2);
   }

   @Redirect(method = "initCraftableStats", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;", ordinal = 2))
   private static Object lunar$useItemName$craftItem(Iterator<Item> iterator0, @Share("craftId") LocalRef<String> localref1) {
      Item item2 = (Item)iterator0.next();
      String text3 = lunar$getFormattedItemName(item2);
      if (text3 == null) {
         return null;
      }

      localref1.set("stat.craftItem." + text3);
      return item2;
   }

   @Redirect(method = "initCraftableStats", at = @At(value = "NEW", target = "net/minecraft/stats/StatCrafting"))
   private static StatCrafting lunar$modifyStatId$craftItem$v1_7(String text0, IChatComponent text1, Item item2, @Share("craftId") LocalRef<String> localref3) {
      return new StatCrafting((String)localref3.get(), text1, item2);
   }

   @Redirect(method = "initStats", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;", ordinal = 0))
   private static Object lunar$useItemName$useItem(Iterator<Item> iterator0, @Share("useId") LocalRef<String> localref1) {
      Item item2 = (Item)iterator0.next();
      String text3 = lunar$getFormattedItemName(item2);
      if (text3 == null) {
         return null;
      }

      localref1.set("stat.useItem." + text3);
      return item2;
   }

   @Redirect(method = "initStats", at = @At(value = "NEW", target = "net/minecraft/stats/StatCrafting"))
   private static StatCrafting lunar$modifyStatId$useItem$v1_7(String text0, IChatComponent text1, Item item2, @Share("useId") LocalRef<String> localref3) {
      return new StatCrafting((String)localref3.get(), text1, item2);
   }

   @Redirect(method = "func_151179_e$v1_7", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;", ordinal = 0))
   private static Object lunar$useItemName$breakItem(Iterator<Item> iterator0, @Share("breakId") LocalRef<String> localref1) {
      Item item2 = (Item)iterator0.next();
      String text3 = lunar$getFormattedItemName(item2);
      if (text3 == null) {
         return null;
      }

      localref1.set("stat.breakItem." + text3);
      return item2;
   }

   @Redirect(method = "func_151179_e$v1_7", at = @At(value = "NEW", target = "net/minecraft/stats/StatCrafting"))
   private static StatCrafting lunar$modifyStatId$breakItem(String text0, IChatComponent text1, Item item2, @Share("breakId") LocalRef<String> localref3) {
      return new StatCrafting((String)localref3.get(), text1, item2);
   }

   @Unique
   @Nullable
   private static String lunar$getFormattedItemName(Item item0) {
      if (item0 == null) {
         return null;
      }

      String text1 = Item.itemRegistry$v1_7.getNameForObject(item0);
      if (text1 == null) {
         return null;
      }

      int index2 = text1.indexOf(":");
      if (index2 != -1) {
         text1 = text1.substring(index2 + 1);
      }

      return text1;
   }
}
