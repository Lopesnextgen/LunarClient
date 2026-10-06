package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.stats.StatisticsFileBridge;
import com.moonsworth.lunar.bridge.stats.StatBaseBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatBase;
import net.minecraft.util.TupleIntJsonSerializable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.stats.StatFileWriter.class)
public abstract class StatFileWriterMixin implements StatisticsFileBridge {
   @Final
   @Shadow
   public Map field_150875_a$v1_7;
   @Final
   @Shadow
   public Map<StatBase, TupleIntJsonSerializable> statsData;
   @Unique
   private static final int CONVERSION_CACHE_SIZE = 30;
   @Unique
   private static final Object2ObjectLinkedOpenHashMap<String, String> lunar$quickConversionCache = new Object2ObjectLinkedOpenHashMap<String, String>(
      30, 0.25F
   ) {
      protected void rehash(int number1) {
      }
   };
   @Unique
   private static final List<String> IGNORED_STATS = List.of("stat.playOneMinute", "stat.timeSinceDeath");
   @VersionGate(min = 1)
   @Unique
   private static final Map<String, String> FULL_CONVERSIONS = Map.copyOf(new HashMap<String, String>() {
      {
         this.put("cakeSlicesEaten", "eat_cake_slice");
         this.put("cauldronFilled", "fill_cauldron");
         this.put("cauldronUsed", "use_cauldron");
         this.put("armorCleaned", "clean_armor");
         this.put("bannerCleaned", "clean_banner");
         this.put("brewingstandInteraction", "interact_with_brewingstand");
         this.put("beaconInteraction", "interact_with_beacon");
         this.put("dropperInspected", "inspect_dropper");
         this.put("hopperInspected", "inspect_hopper");
         this.put("dispenserInspected", "inspect_dispenser");
         this.put("noteblockPlayed", "play_noteblock");
         this.put("noteblockTuned", "tune_noteblock");
         this.put("flowerPotted", "pot_flower");
         this.put("trappedChestTriggered", "trigger_trapped_chest");
         this.put("enderchestOpened", "open_enderchest");
         this.put("itemEnchanted", "enchant_item");
         this.put("recordPlayed", "play_record");
         this.put("furnaceInteraction", "interact_with_furnace");
         this.put("craftingTableInteraction", "interact_with_crafting_table");
         this.put("chestOpened", "open_chest");
         if (Ref.MC_VERSION >= 5) {
            this.put("shulkerBoxOpened", "open_shulker_box");
         }
      }
   });
   @Unique
   private static final Map<String, String> CONVERT_TYPES = Map.copyOf(new HashMap<String, String>() {
      {
         this.put("mineBlock", "mined");
         this.put("useItem", "used");
         this.put("killEntity", "killed");
         this.put("craftItem", "crafted");
         this.put("breakItem", "broken");
         this.put("entityKilledBy", "killed_by");
         if (Ref.MC_VERSION >= 5) {
            this.put("drop", "dropped");
            this.put("pickup", "picked_up");
         }
      }
   });

   public StatFileWriterMixin() {
   }

   @Shadow
   public abstract int readStat(StatBase statbase1);

   @Shadow
   public abstract int writeStat$v1_7(StatBase statbase1);

   @Shadow
   public abstract void increaseStat(EntityPlayer player1, StatBase statbase2, int number3);

   @Shadow
   public abstract void func_150871_b$v1_7(EntityPlayer player1, StatBase statbase2, int number3);

   @Inject(method = {"func_150873_a$v1_7", "unlockAchievement$v1_8"}, at = @At("HEAD"))
   private void lunar$getOriginalStatAmt(EntityPlayer player1, StatBase statbase2, int number3, CallbackInfo callback4) {
      if (Ref.MC_VERSION >= 1) {
         if (Ref.method3().bridge$getCurrentServerData() != null) {
            int number5 = this.readStat(statbase2);
            this.bridge$recordStat((StatBaseBridge)statbase2, number3 - number5);
         }
      } else if (Ref.method3().bridge$getCurrentServerData() == null) {
         if (!player1.world.isRemote) {
            int number6 = this.writeStat$v1_7(statbase2);
            this.bridge$recordStat((StatBaseBridge)statbase2, number3 - number6);
         }
      } else if (player1.world.isRemote) {
         int number7 = this.writeStat$v1_7(statbase2);
         this.bridge$recordStat((StatBaseBridge)statbase2, number3 - number7);
      }
   }

   public void bridge$increment(Bridge6_10 bridge6_101, StatBaseBridge hitbox22, int number3) {
      if (Ref.MC_VERSION >= 1) {
         this.increaseStat((EntityPlayer)bridge6_101, (StatBase)hitbox22, number3);
      } else {
         this.func_150871_b$v1_7((EntityPlayer)bridge6_101, (StatBase)hitbox22, number3);
      }
   }

   public void bridge$setValueFromPacket(Bridge6_10 bridge6_101, StatBaseBridge hitbox22, int number3) {
      TupleIntJsonSerializable tupleintjsonserializable4;
      if (Ref.MC_VERSION >= 1) {
         tupleintjsonserializable4 = this.statsData.computeIfAbsent((StatBase)hitbox22, arg0 -> new TupleIntJsonSerializable());
      } else {
         tupleintjsonserializable4 = this.field_150875_a$v1_7.computeIfAbsent((StatBase)hitbox22, arg0 -> new TupleIntJsonSerializable());
      }

      tupleintjsonserializable4.setIntegerValue(number3);
   }

   public void bridge$recordStat(StatBaseBridge hitbox21, int number2) {
      StatBase statbase3 = (StatBase)hitbox21;
      String text4 = statbase3.statId;
      if (!IGNORED_STATS.contains(text4)) {
         if (text4.contains("achievement")) {
            return;
         }

         String text5 = (String)lunar$quickConversionCache.getAndMoveToFirst(text4);
         if (text5 == null) {
            int index6 = text4.lastIndexOf(".");
            String text7 = text4.substring(index6 + 1);
            if (Ref.MC_VERSION >= 1 && FULL_CONVERSIONS.containsKey(text7)) {
               text5 = "minecraft.custom:minecraft." + FULL_CONVERSIONS.get(text4);
            } else {
               String text8 = text4.substring(0, index6);
               int index9 = text8.lastIndexOf(".");
               if (index9 != -1) {
                  String text10 = text8.substring(index9 + 1);
                  if (text10.equals("minecraft")) {
                     String text11 = text8.substring(0, index9);
                     int index12 = text11.indexOf(".");
                     text10 = text11.substring(index12 + 1);
                  }

                  text5 = this.lunar$toModernNaming(CONVERT_TYPES.getOrDefault(text10, null), text7);
               } else {
                  text5 = this.lunar$toModernNaming(null, text7);
               }
            }

            lunar$quickConversionCache.putAndMoveToFirst(text4, text5);
            if (lunar$quickConversionCache.size() == 30) {
               lunar$quickConversionCache.removeLast();
            }
         }

         Ref.method4().method86().method2("stat:" + text5, number2);
      }
   }

   @Unique
   private String lunar$toModernNaming(@Nullable String text1, String text2) {
      return "minecraft." + (text1 == null ? "custom" : text1) + ":minecraft." + this.lunar$convertToLowerSnakeCase(text2);
   }

   @Unique
   private String lunar$convertToLowerSnakeCase(String text1) {
      text1 = text1.replace("-", "_");
      StringBuilder builder2 = new StringBuilder(String.valueOf(Character.toLowerCase(text1.charAt(0))));
      boolean flag3 = false;

      for (int index4 = 1; index4 < text1.length(); index4++) {
         char character5 = text1.charAt(index4);
         if (character5 == '_') {
            flag3 = true;
            builder2.append(character5);
         } else if (Character.isLowerCase(character5)) {
            flag3 = false;
            builder2.append(character5);
         } else {
            if (!flag3) {
               builder2.append("_");
            }

            flag3 = true;
            builder2.append(Character.toLowerCase(character5));
         }
      }

      return builder2.toString();
   }
}
