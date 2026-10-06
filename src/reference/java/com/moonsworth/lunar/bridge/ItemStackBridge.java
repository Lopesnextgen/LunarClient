package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface ItemStackBridge extends ItemStackRenderStateBridge {
   String bridge$getRawDisplayName();

   boolean bridge$hasCustomLore();

   Bridge6_4 bridge$getItem();

   void bridge$setStackSize(int var1);

   int bridge$getMaxDamage();

   boolean bridge$isItemDamaged();

   int bridge$getItemDamage();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   double bridge$getAttackDamage();

   void bridge$setCustomModelData(List<Float> var1, List<Boolean> var2, List<String> var3, List<Integer> var4);

   int bridge$getCustomModelData();

   int bridge$getStackSize();

   int bridge$getMaxStackSize();

   boolean bridge$isItemStackDamageableNoUnbr();

   int bridge$getRepairCost();

   boolean bridge$hasDisplayName();

   boolean bridge$isItemStackDamageable();

   void bridge$clearCustomName();

   void bridge$setItemDamage(int var1);

   void bridge$setStackDisplayName(Component var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   void bridge$setLore(List<Component> var1);

   ItemStackBridge bridge$copy();

   void bridge$setRepairCost(int var1);

   Map<Bridge2_26, Integer> bridge$getEnchantments();

   void bridge$setEnchantments(Map<Bridge2_26, Integer> var1);

   default @Range(from = 0L, to = 255L) int method1(Bridge2_26 var1) {
      for (Entry var3 : this.bridge$getEnchantments().entrySet()) {
         if (((Bridge2_26)var3.getKey()).bridge$isEnchantment(var1)) {
            return (Integer)var3.getValue();
         }
      }

      return 0;
   }

   void bridge$setProfile(@Nullable UUID var1, String var2, String var3);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   void bridge$setPotionEffects(List<Fog> var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 26)
   void bridge$setItemModel(ResourceLocationBridge var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   void bridge$setArmorColor(int var1);

   BridgeExtension$Type bridge$getItemUseAction();

   int bridge$getMaxItemUseDuration();

   boolean bridge$areItemsEqual(ItemStackBridge var1);

   boolean bridge$areItemsEqual(ItemStackBridge var1, BiPredicate<ItemStackBridge, ItemStackBridge> var2);

   String bridge$getUnlocalizedName();

   List<String> bridge$getTooltip(Bridge5Extension_5 var1, boolean var2);

   List<Component> bridge$getTooltipComponents(Bridge5Extension_5 var1, boolean var2);

   void bridge$setTagCompound(Bridge_57 var1);

   Bridge_57 bridge$getTagCompound();

   Optional<Bridge_57> bridge$saveTagCompound();

   @Nullable
   default <T> T bridge$getDataComponent(MixinHelper_9<T> var1) {
      return (T)var1.bridge$get(this);
   }

   boolean bridge$isItemEnchanted();

   boolean bridge$hasFoil();

   @com.moonsworth.lunar.ichor.Annotation2(min = 22)
   void bridge$setFoil(boolean var1);

   Optional<Bridge_9> bridge$getFood();

   @com.moonsworth.lunar.ichor.Annotation2(max = 25)
   Optional<Bridge4_5> bridge$getMaterial();

   Optional<String> bridge$getBannerColor();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1, max = 5)
   void bridge$setBannerColor(String var1);

   boolean bridge$isItemEqual(ItemStackBridge var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 33)
   void bridge$setSkyBlockExtraAttributes(Bridge_57 var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 33)
   void bridge$sbHideTooltipComponents();

   @Nullable
   List<ItemStackBridge> bridge$getContainerItems();

   boolean bridge$isItemCubeBlock();

   boolean bridge$isCrossbowFullyCharged();

   boolean bridge$isItemSplashPotion();

   void bridge$setDyedColor(int var1);

   boolean bridge$canElytraFly();

   @Nullable
   default KineticWeaponTiming bridge$getKineticWeaponPhases() {
      return null;
   }
}
