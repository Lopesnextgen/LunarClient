package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers11 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   private static final BiMap<Integer, String> field1 = new Builder()
      .put(1, "item")
      .put(2, "experience_orb")
      .put(3, "area_effect_cloud")
      .put(4, "elder_guardian")
      .put(5, "wither_skeleton")
      .put(6, "stray")
      .put(7, "egg")
      .put(8, "leash_knot")
      .put(9, "painting")
      .put(10, "arrow")
      .put(11, "snowball")
      .put(12, "fireball")
      .put(13, "small_fireball")
      .put(14, "ender_pearl")
      .put(15, "eye_of_ender")
      .put(16, "potion")
      .put(17, "experience_bottle")
      .put(18, "item_frame")
      .put(19, "wither_skull")
      .put(20, "tnt")
      .put(21, "falling_block")
      .put(22, "firework_rocket")
      .put(23, "husk")
      .put(24, "spectral_arrow")
      .put(25, "shulker_bullet")
      .put(26, "dragon_fireball")
      .put(27, "zombie_villager")
      .put(28, "skeleton_horse")
      .put(29, "zombie_horse")
      .put(30, "armor_stand")
      .put(31, "donkey")
      .put(32, "mule")
      .put(33, "evoker_fangs")
      .put(34, "evoker")
      .put(35, "vex")
      .put(36, "vindicator")
      .put(37, "illusioner")
      .put(40, "command_block_minecart")
      .put(41, "null")
      .put(42, "minecart")
      .put(43, "chest_minecart")
      .put(44, "furnace_minecart")
      .put(45, "tnt_minecart")
      .put(46, "hopper_minecart")
      .put(47, "spawner_minecart")
      .put(50, "creeper")
      .put(51, "skeleton")
      .put(52, "spider")
      .put(53, "giant")
      .put(54, "zombie")
      .put(55, "slime")
      .put(56, "ghast")
      .put(57, "zombified_piglin")
      .put(58, "enderman")
      .put(59, "cave_spider")
      .put(60, "silverfish")
      .put(61, "blaze")
      .put(62, "magma_cube")
      .put(63, "ender_dragon")
      .put(64, "wither")
      .put(65, "bat")
      .put(66, "witch")
      .put(67, "endermite")
      .put(68, "guardian")
      .put(69, "shulker")
      .put(90, "pig")
      .put(91, "sheep")
      .put(92, "cow")
      .put(93, "chicken")
      .put(94, "squid")
      .put(95, "wolf")
      .put(96, "mooshroom")
      .put(97, "snow_golem")
      .put(98, "ocelot")
      .put(99, "iron_golem")
      .put(100, "horse")
      .put(101, "rabbit")
      .put(102, "polar_bear")
      .put(103, "llama")
      .put(104, "llama_spit")
      .put(105, "parrot")
      .put(120, "villager")
      .put(200, "end_crystal")
      .build();

   @NotNull
   @Override
   public String method4() {
      return "spawn_eggs";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return "minecraft:spawn_egg".equals(var2) || "minecraft:monster_egg".equals(var2) || var2.endsWith("_spawn_egg");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         return SIterator_2.method5(var1).map(var0 -> Markers2_3.method3(var0.replace("_spawn_egg", ""))).orElse(Markers2_3.method2());
      }

      if (ThreadModuleDump63.MC_VERSION == 5) {
         Bridge_57 var5 = var1.bridge$getTagCompound();
         if (var5 != null && var5.bridge$contains("EntityTag", 10)) {
            var5 = var5.bridge$getCompoundTag("EntityTag");
            String var3 = var5.bridge$getString("id");
            if (var3 != null) {
               int var4 = Bridge.method61().bridge$getEntityId(var3);
               return Markers2_3.method3((String)field1.get(var4));
            }
         }

         return Markers2_3.method2();
      } else {
         int var2 = var1.bridge$getItemDamage();
         return Markers2_3.method3((String)field1.get(var2));
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (var1.isEmpty()) {
         return SIterator_2.method8("minecraft:spawn_egg");
      }

      if (ThreadModuleDump63.MC_VERSION > 5) {
         return SIterator_2.method8("minecraft:" + var1.value() + "_spawn_egg");
      }

      ItemStackBridge var2 = SIterator_2.method9("minecraft:spawn_egg");
      if (ThreadModuleDump63.MC_VERSION == 5) {
         Bridge_57 var3 = Bridge.method8().method68();
         Bridge_57 var4 = Bridge.method8().method68();
         var4.bridge$putString("id", "minecraft:" + var1.value());
         var3.bridge$putCompound("EntityTag", var4);
         var2.bridge$setTagCompound(var3);
      } else {
         Integer var5 = (Integer)field1.inverse().get(var1.value());
         if (var5 != null) {
            var2.bridge$setItemDamage(var5);
         }
      }

      return SIterator_2.method7(var2);
   }
}
