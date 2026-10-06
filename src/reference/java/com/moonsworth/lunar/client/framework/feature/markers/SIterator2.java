package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SIterator2 extends SIterator<SIterator2.Data, BridgeExtension> {
   @Override
   protected void initMappings() {
      this.method4("ThrownEnderpearl", "minecraft:ender_pearl");
      this.method4("LavaSlime", "minecraft:magma_cube");
      this.method4("ThrownPotion", "minecraft:potion");
      this.method4("Item", "minecraft:item");
      this.method4("AreaEffectCloud", "minecraft:area_effect_cloud");
      this.method4("ElderGuardian", "minecraft:elder_guardian");
      this.method4("WitherSkeleton", "minecraft:wither_skeleton");
      this.method4("Stray", "minecraft:stray");
      this.method4("LeashKnot", "minecraft:leash_knot");
      this.method4("Painting", "minecraft:painting");
      this.method4("Arrow", "minecraft:arrow");
      this.method4("Snowball", "minecraft:snowball");
      this.method4("Fireball", "minecraft:fireball");
      this.method4("SmallFireball", "minecraft:small_fireball");
      this.method4("ItemFrame", "minecraft:item_frame");
      this.method4("WitherSkull", "minecraft:wither_skull");
      this.method4("Husk", "minecraft:husk");
      this.method4("SpectralArrow", "minecraft:spectral_arrow");
      this.method4("ShulkerBullet", "minecraft:shulker_bullet");
      this.method4("DragonFireball", "minecraft:dragon_fireball");
      this.method4("ZombieVillager", "minecraft:zombie_villager");
      this.method4("SkeletonHorse", "minecraft:skeleton_horse");
      this.method4("ZombieHorse", "minecraft:zombie_horse");
      this.method4("ArmorStand", "minecraft:armor_stand");
      this.method4("Donkey", "minecraft:donkey");
      this.method4("Mule", "minecraft:mule");
      this.method4("Vex", "minecraft:vex");
      this.method4("Creeper", "minecraft:creeper");
      this.method4("Skeleton", "minecraft:skeleton");
      this.method4("Spider", "minecraft:spider");
      this.method4("Giant", "minecraft:giant");
      this.method4("Zombie", "minecraft:zombie");
      this.method4("Slime", "minecraft:slime");
      this.method4("Ghast", "minecraft:ghast");
      this.method4("Enderman", "minecraft:enderman");
      this.method4("CaveSpider", "minecraft:cave_spider");
      this.method4("Silverfish", "minecraft:silverfish");
      this.method4("Blaze", "minecraft:blaze");
      this.method4("EnderDragon", "minecraft:ender_dragon");
      this.method4("Bat", "minecraft:bat");
      this.method4("Witch", "minecraft:witch");
      this.method4("Endermite", "minecraft:endermite");
      this.method4("Guardian", "minecraft:guardian");
      this.method4("Shulker", "minecraft:shulker");
      this.method4("Pig", "minecraft:pig");
      this.method4("Sheep", "minecraft:sheep");
      this.method4("MushroomCow", "minecraft:mooshroom");
      this.method4("Cow", "minecraft:cow");
      this.method4("Chicken", "minecraft:chicken");
      this.method4("Squid", "minecraft:squid");
      this.method4("Wolf", "minecraft:wolf");
      this.method4("Horse", "minecraft:horse");
      this.method4("Rabbit", "minecraft:rabbit");
      this.method4("PolarBear", "minecraft:polar_bear");
      this.method4("Llama", "minecraft:llama");
      this.method4("LlamaSpit", "minecraft:llama_spit");
      this.method4("Parrot", "minecraft:parrot");
      this.method4("Villager", "minecraft:villager");
      this.method4("PigZombie", "minecraft:zombified_piglin");
      this.method4("MinecartSpawner", "minecraft:spawner_minecart");
      this.method4("FireworksRocketEntity", "minecraft:firework_rocket");
      this.method4("ThrownExpBottle", "minecraft:experience_bottle");
      this.method4("VillagerGolem", "minecraft:iron_golem");
      this.method4("MinecartHopper", "minecraft:hopper_minecart");
      this.method4("XPOrb", "minecraft:experience_orb");
      this.method4("FallingSand", "minecraft:falling_block");
      this.method4("MinecartChest", "minecraft:chest_minecart");
      this.method4("WitherBoss", "minecraft:wither");
      this.method4("SnowMan", "minecraft:snow_golem");
      this.method4("EyeOfEnderSignal", "minecraft:eye_of_ender");
      this.method4("MinecartRideable", "minecraft:minecart");
      this.method4("EnderCrystal", "minecraft:end_crystal");
      this.method4("MinecartFurnace", "minecraft:furnace_minecart");
      this.method4("EvocationFangs", "minecraft:evoker_fangs");
      this.method4("MinecartCommandBlock", "minecraft:command_block_minecart");
      this.method4("PrimedTnt", "minecraft:tnt");
      this.method4("MinecartTNT", "minecraft:tnt_minecart");
      this.method4("ThrownEgg", "minecraft:egg");
      this.method4("Ozelot", "minecraft:ocelot");
      this.method4("EvocationIllager", "minecraft:evoker");
      this.method4("VindicationIllager", "minecraft:vindicator");
      this.method4("IllusionIllager", "minecraft:illusioner");
      this.method2(new SExtension2());
      this.method2(new SExtension());
   }

   public boolean method1(@NotNull BridgeExtension var1) {
      return ThreadModuleDump63.MC_VERSION > 5 ? var1.bridge$getEntityString().startsWith("minecraft:") : true;
   }

   public boolean method2(@NotNull Markers3_2 var1) {
      return var1.value().startsWith("minecraft:");
   }

   public Optional<Markers3_2> method3(@NotNull BridgeExtension var1) {
      String var2 = var1.bridge$getEntityString();
      return var2 != null && !var2.trim().isEmpty() ? Optional.of(new Markers3_2(var2, method5(var1))) : Optional.empty();
   }

   public Optional<SIterator2.Data> method4(@NotNull Markers3_2 var1) {
      return SIterator2.Data.method1(var1);
   }

   public static Markers2_3 method5(@NotNull BridgeExtension var0) {
      Markers2_3 var1 = Markers2_3.method2();
      Component var2 = var0.bridge$getCustomName();
      if (var2 != null) {
         var1 = Markers2_3.method3(LegacyComponentSerializer.legacySection().serialize(var2));
      }

      return var1;
   }

   public class Data {
      private final String field1;
      @Nullable
      private final Component field2;

      public Data(String var1, @Nullable Component var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public static Optional<SIterator2.Data> method1(Markers3_2 var0) {
         String var1 = var0.value();
         TextComponent var2 = null;
         if (!var0.method1().isEmpty()) {
            var2 = LegacyComponentSerializer.legacySection().deserialize(var0.method1().value());
         }

         return Optional.of(new SIterator2.Data(var1, var2));
      }

      public String method2() {
         return this.field1;
      }

      @Nullable
      public Component method3() {
         return this.field2;
      }
   }
}
