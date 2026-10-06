package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.bridge.fog.Fog3;
import com.moonsworth.lunar.client.util.ThreadModuleDump33;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.jetbrains.annotations.Nullable;

public class Fog3Handler implements Fog3 {
   @Override
   public Fog2 method1() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.REGISTRY$v1_12.getObjectById(2) : (Fog2)Potion.moveSlowdown;
   }

   @Override
   public Fog2 method2() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.REGISTRY$v1_12.getObjectById(1) : (Fog2)Potion.moveSpeed;
   }

   @Override
   public Fog2 method8(int var1) {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.getPotionById$v1_12(var1) : (Fog2)Potion.potionTypes[var1];
   }

   @Override
   public Fog2 method9(String var1) {
      throw new AbstractMethodErrorImpl();
   }

   @Override
   public Fog method10(int var1, String var2, int var3, int var4) {
      return ThreadModuleDump63.MC_VERSION == 5
         ? (Fog)(new PotionEffect(Potion.getPotionById$v1_12(var1), var3, var4))
         : (Fog)(new PotionEffect(var1, var3, var4));
   }

   @Override
   public String method11(String var1) {
      return StatCollector.translateToFallback(var1);
   }

   @Override
   public Component method12(Fog var1) {
      return AdventureTextBridge.asAdventure(
         ThreadModuleDump63.MC_VERSION == 5 ? Potion.getPotionDurationString$v1_12((PotionEffect)var1, 1.0F) : Potion.getDurationString((PotionEffect)var1)
      );
   }

   @Override
   public String method13(Fog var1) {
      return ThreadModuleDump63.MC_VERSION == 5 ? Potion.getPotionDurationString$v1_12((PotionEffect)var1, 1.0F) : Potion.getDurationString((PotionEffect)var1);
   }

   @Override
   public Fog2 method3() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.REGISTRY$v1_12.getObjectById(6) : (Fog2)Potion.heal;
   }

   @Override
   public Fog2 method4() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.REGISTRY$v1_12.getObjectById(15) : (Fog2)Potion.blindness;
   }

   @Override
   public Fog2 method5() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.REGISTRY$v1_12.getObjectById(3) : (Fog2)Potion.digSpeed;
   }

   @Override
   public Fog2 method6() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.REGISTRY$v1_12.getObjectById(18) : (Fog2)Potion.weakness;
   }

   @Override
   public Fog2 method7() {
      return ThreadModuleDump63.MC_VERSION == 5 ? (Fog2)Potion.REGISTRY$v1_12.getObjectById(5) : (Fog2)Potion.damageBoost;
   }

   @Override
   public boolean method14(ItemStackBridge var1) {
      return ThreadModuleDump63.MC_VERSION == 5 ? var1.bridge$getItem() instanceof ItemSplashPotion : ItemPotion.isSplash(var1.bridge$getItemDamage());
   }

   @Override
   public List<String> method15() {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         return PotionType.REGISTRY.getKeys().stream().<String>map(ResourceLocation::toString).toList();
      } else {
         return ThreadModuleDump63.MC_VERSION == 1
            ? Potion.field_180150_I.keySet().stream().<String>map(ResourceLocation::toString).toList()
            : Arrays.stream(Potion.potionTypes).map(var0 -> var0.name).toList();
      }
   }

   @Nullable
   @Override
   public String method16(ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         ItemStack var2 = (ItemStack)var1;
         if (!var2.hasTagCompound()) {
            return null;
         }

         NBTTagCompound var3 = var2.getTagCompound();
         return var3 != null && var3.hasKey("Potion") ? var3.getString("Potion") : null;
      } else {
         return ThreadModuleDump33.method1(var1);
      }
   }

   @Override
   public void method18(ItemStackBridge var1, String var2) {
      Bridge_57 var3 = Bridge.method8().method68();
      var3.bridge$putString("Potion", var2);
      var1.bridge$setTagCompound(var3);
   }
}
