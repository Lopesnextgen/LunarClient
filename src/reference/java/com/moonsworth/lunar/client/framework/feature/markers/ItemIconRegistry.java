package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class ItemIconRegistry extends IconRegistry<ItemStackBridge, ItemStackBridge> {
   public ItemIconRegistry() {
   }

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
      this.method2(new ScuteIconMapping());
      this.method2(new GrassIconMapping());
      this.method2(new ItemBoatIconMapping());
      this.method2(new LeavesIconMapping());
      this.method2(new LogIconMapping());
      this.method2(new RedstoneComponentIconMapping());
      this.method2(new NetherBrickIconMapping());
      this.method2(new BannerIconMapping());
      this.method2(new SignIconMapping());
      this.method2(new TrapdoorIconMapping());
      this.method2(new TerracottaIconMapping());
      this.method2(new SkullIconMapping());
      this.method2(new StoneBrickIconMapping());
      this.method2(new SlabIconMapping());
      this.method2(new SpawnEggIconMapping());
      this.method2(new WoodTypeIconMapping("planks"));
      this.method2(new WoodTypeIconMapping("sapling"));
      this.method2(new FenceIconMapping("fence"));
      this.method2(new FenceIconMapping("fence_gate"));
      this.method2(new WoodenBlockIconMapping("door"));
      this.method2(new WoodenBlockIconMapping("button"));
      this.method2(new WoodenBlockIconMapping("pressure_plate"));
      this.method2(new DyeColorIconMapping("dye", false));
      this.method2(new DyeColorIconMapping("carpet"));
      this.method2(new DyeColorIconMapping("bed"));
      this.method2(new DyeColorIconMapping("wool"));
      this.method2(new DyeColorIconMapping("stained_glass"));
      this.method2(new DyeColorIconMapping("stained_glass_pane"));
      this.method2(new DyeColorIconMapping("concrete"));
      this.method2(new DyeColorIconMapping("concrete_powder"));
      this.method2(new LegacyItemAliasMapping("minecraft:daylight_detector_inverted", "minecraft:daylight_detector"));
      this.method2(new LegacyItemAliasMapping("minecraft:unlit_redstone_torch", "minecraft:redstone_torch"));
      this.method2(new LegacyItemAliasMapping("minecraft:piston_extension", "minecraft:piston"));
      this.method2(new LegacyItemAliasMapping("minecraft:lit_furnace", "minecraft:furnace"));
      this.method2(new LegacyItemAliasMapping("minecraft:lit_redstone_ore", "minecraft:redstone_ore"));
      this.method2(new LegacyItemAliasMapping("minecraft:lit_redstone_lamp", "minecraft:redstone_lamp"));
      this.method2(new BlockVariantIconMapping("stone", "granite", "polished_granite", "diorite", "polished_diorite", "andesite", "polished_andesite"));
      this.method2(new BlockVariantIconMapping("quartz_block", "chiseled_quartz_block", "quartz_pillar", "quartz_pillar", "quartz_pillar"));
      this.method2(new BlockVariantIconMapping("sandstone", "chiseled_sandstone", "smooth_sandstone"));
      this.method2(new BlockVariantIconMapping("red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone"));
   }

   public boolean method1(@NotNull ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$getItemRegistryName().startsWith("minecraft:");
   }

   public boolean method2(@NotNull Markers3_2 markers3_21) {
      return markers3_21.value().startsWith("minecraft:");
   }

   public Optional<Markers3_2> method3(@NotNull ItemStackBridge bridgeextension_41) {
      return method6(bridgeextension_41) ? Optional.of(new Markers3_2(bridgeextension_41.bridge$getItemRegistryName(), Markers2_3.method2())) : Optional.empty();
   }

   public Optional<ItemStackBridge> method4(@NotNull Markers3_2 markers3_21) {
      return method8(markers3_21.value());
   }

   public static Optional<String> method5(ItemStackBridge bridgeextension_40) {
      String text1 = bridgeextension_40.bridge$getItemRegistryName();
      return !text1.contains(":") ? Optional.empty() : Optional.of(text1.split(":")[1]);
   }

   public static boolean method6(ItemStackBridge bridgeextension_40) {
      return bridgeextension_40 != null && bridgeextension_40.bridge$getItem() != null && !bridgeextension_40.bridge$isEmpty();
   }

   public static Optional<ItemStackBridge> method7(ItemStackBridge bridgeextension_40) {
      return method6(bridgeextension_40) ? Optional.of(bridgeextension_40) : Optional.empty();
   }

   public static Optional<ItemStackBridge> method8(String text0) {
      return method7(method9(text0));
   }

   public static ItemStackBridge method9(String text0) {
      return Bridge.method8().method38(Bridge.method28().method22(text0));
   }
}
