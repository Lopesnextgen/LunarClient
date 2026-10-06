package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class SIterator_2 extends SIterator<ItemStackBridge, ItemStackBridge> {
   @Override
   protected void initMappings() {
      this.method4("minecraft:end_bricks", "minecraft:end_stone_bricks");
      this.method4("minecraft:golden_rail", "minecraft:powered_rail");
      this.method4("minecraft:grass", "minecraft:grass_block");
      this.method4("minecraft:magma", "minecraft:magma_block");
      this.method4("minecraft:brick_block", "minecraft:bricks");
      this.method4("minecraft:quartz_ore", "minecraft:nether_quartz_ore");
      this.method4("minecraft:melon_block", "minecraft:melon");
      this.method4("minecraft:lit_pumpkin", "minecraft:carved_pumpkin");
      this.method4("minecraft:mob_spawner", "minecraft:spawner");
      this.method4("minecraft:slime", "minecraft:slime_block");
      this.method4("minecraft:grass_path", "minecraft:dirt_path");
      this.method4("minecraft:snow_layer", "minecraft:snow");
      this.method4("minecraft:yellow_flower", "minecraft:dandelion");
      this.method4("minecraft:web", "minecraft:cobweb");
      this.method4("minecraft:reeds", "minecraft:sugar_cane");
      this.method4("minecraft:waterlily", "minecraft:lily_pad");
      this.method4("minecraft:noteblock", "minecraft:note_block");
      this.method4("minecraft:deadbush", "minecraft:dead_bush");
      this.method4("minecraft:portal", "minecraft:nether_portal");
      this.method4("minecraft:silver_shulker_box", "minecraft:shulker_box");
      this.method4("minecraft:silver_glazed_terracotta", "minecraft:terracotta");
      this.method4("minecraft:record_13", "minecraft:music_disc_13");
      this.method4("minecraft:record_cat", "minecraft:music_disc_cat");
      this.method4("minecraft:record_blocks", "minecraft:music_disc_blocks");
      this.method4("minecraft:record_chirp", "minecraft:music_disc_chirp");
      this.method4("minecraft:record_far", "minecraft:music_disc_far");
      this.method4("minecraft:record_mall", "minecraft:music_disc_mall");
      this.method4("minecraft:record_mellohi", "minecraft:music_disc_mellohi");
      this.method4("minecraft:record_stal", "minecraft:music_disc_stal");
      this.method4("minecraft:record_strad", "minecraft:music_disc_strad");
      this.method4("minecraft:record_ward", "minecraft:music_disc_ward");
      this.method4("minecraft:record_11", "minecraft:music_disc_11");
      this.method4("minecraft:record_wait", "minecraft:music_disc_wait");
      this.method4("minecraft:speckled_melon", "minecraft:glistering_melon_slice");
      this.method4("minecraft:fish", "minecraft:cod");
      this.method4("minecraft:cooked_fish", "minecraft:cooked_cod");
      this.method4("minecraft:chorus_fruit_popped", "minecraft:chorus_fruit");
      this.method4("minecraft:fireworks", "minecraft:firework_rocket");
      this.method4("minecraft:firework_charge", "minecraft:firework_star");
      this.method4("minecraft:tallgrass", "minecraft:tall_grass");
      this.method4("minecraft:hardened_clay", "minecraft:terracota");
      this.method2(new Markers12Impl2());
      this.method2(new Markers12Impl3());
      this.method2(new Markers3_3());
      this.method2(new Markers12Base2());
      this.method2(new Markers12Base3());
      this.method2(new Markers_2());
      this.method2(new Markers8());
      this.method2(new Markers6());
      this.method2(new Markers12Impl4());
      this.method2(new Markers12Impl6());
      this.method2(new Markers12Impl());
      this.method2(new Markers5());
      this.method2(new Markers9Impl());
      this.method2(new Markers12Impl5());
      this.method2(new Markers11());
      this.method2(new Markers2_2("planks"));
      this.method2(new Markers2_2("sapling"));
      this.method2(new Markers10("fence"));
      this.method2(new Markers10("fence_gate"));
      this.method2(new Markers4("door"));
      this.method2(new Markers4("button"));
      this.method2(new Markers4("pressure_plate"));
      this.method2(new Markers7("dye", false));
      this.method2(new Markers7("carpet"));
      this.method2(new Markers7("bed"));
      this.method2(new Markers7("wool"));
      this.method2(new Markers7("stained_glass"));
      this.method2(new Markers7("stained_glass_pane"));
      this.method2(new Markers7("concrete"));
      this.method2(new Markers7("concrete_powder"));
      this.method2(new Markers12("minecraft:daylight_detector_inverted", "minecraft:daylight_detector"));
      this.method2(new Markers12("minecraft:unlit_redstone_torch", "minecraft:redstone_torch"));
      this.method2(new Markers12("minecraft:piston_extension", "minecraft:piston"));
      this.method2(new Markers12("minecraft:lit_furnace", "minecraft:furnace"));
      this.method2(new Markers12("minecraft:lit_redstone_ore", "minecraft:redstone_ore"));
      this.method2(new Markers12("minecraft:lit_redstone_lamp", "minecraft:redstone_lamp"));
      this.method2(new Markers9("stone", "granite", "polished_granite", "diorite", "polished_diorite", "andesite", "polished_andesite"));
      this.method2(new Markers9("quartz_block", "chiseled_quartz_block", "quartz_pillar", "quartz_pillar", "quartz_pillar"));
      this.method2(new Markers9("sandstone", "chiseled_sandstone", "smooth_sandstone"));
      this.method2(new Markers9("red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone"));
   }

   public boolean method1(@NotNull ItemStackBridge var1) {
      return var1.bridge$getItemRegistryName().startsWith("minecraft:");
   }

   public boolean method2(@NotNull Markers3_2 var1) {
      return var1.value().startsWith("minecraft:");
   }

   public Optional<Markers3_2> method3(@NotNull ItemStackBridge var1) {
      return method6(var1) ? Optional.of(new Markers3_2(var1.bridge$getItemRegistryName(), Markers2_3.method2())) : Optional.empty();
   }

   public Optional<ItemStackBridge> method4(@NotNull Markers3_2 var1) {
      return method8(var1.value());
   }

   public static Optional<String> method5(ItemStackBridge var0) {
      String var1 = var0.bridge$getItemRegistryName();
      return !var1.contains(":") ? Optional.empty() : Optional.of(var1.split(":")[1]);
   }

   public static boolean method6(ItemStackBridge var0) {
      return var0 != null && var0.bridge$getItem() != null && !var0.bridge$isEmpty();
   }

   public static Optional<ItemStackBridge> method7(ItemStackBridge var0) {
      return method6(var0) ? Optional.of(var0) : Optional.empty();
   }

   public static Optional<ItemStackBridge> method8(String var0) {
      return method7(method9(var0));
   }

   public static ItemStackBridge method9(String var0) {
      return Bridge.method8().method38(Bridge.method28().method22(var0));
   }
}
