package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge2_26;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge4_5;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6Extension3;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.BridgeExtension$Type;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.bridge.BridgeType3_2;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.bridge.Bridge_9;
import com.moonsworth.lunar.bridge.ItemDataComponentTypes;
import com.moonsworth.lunar.bridge.ArmorColorState;
import com.moonsworth.lunar.bridge.ContainerItemsComponent;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.client.framework.feature.markers.Markers7;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.MixinHelper_2;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import net.kyori.adventure.text.Component;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemLingeringPotion;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTippedArrow;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackBridge {
   @Unique
   private static final ItemStack lunar$EMPTY = ThreadModuleDump63.MC_VERSION >= 5 ? ItemStack.EMPTY$v1_12 : new ItemStack(Blocks.air);
   @Final
   @Shadow
   public Item item;
   @Shadow
   public int stackSize;
   @Shadow
   public NBTTagCompound stackTagCompound;
   @Unique
   private BridgeType2_4 lunar$itemType = BridgeType2_4.UNKNOWN;
   @Unique
   private BridgeType3_2 lunar$itemMaterial;
   @Unique
   private Item lunar$lastItemTypeCheck;
   @Unique
   private Boolean lunar$isCubeBlock = null;

   @Shadow
   public abstract NBTTagCompound getTagCompound();

   @Shadow
   public abstract String getDisplayName();

   @Shadow
   public abstract Item getItem();

   @Shadow
   public abstract boolean isItemDamaged();

   @Shadow
   public abstract int getMaxStackSize();

   @Shadow
   public abstract int getRepairCost();

   @Shadow
   public abstract boolean hasDisplayName();

   @Shadow
   public abstract boolean isItemStackDamageable();

   @Shadow
   public abstract ItemStack setStackDisplayName(String var1);

   @Shadow
   public abstract ItemStack copy();

   @Shadow
   public abstract void setRepairCost(int var1);

   @Shadow
   public abstract boolean isItemEqual(ItemStack var1);

   @Shadow
   public abstract EnumAction getItemUseAction();

   @Shadow
   public abstract int getMaxItemUseDuration();

   @Shadow
   public abstract int getMaxDamage();

   @Shadow
   public abstract int getMaxDurability$v1_7();

   @Shadow
   public abstract int getItemDamage();

   @Shadow
   public abstract int getCurrentDurability$v1_7();

   @Shadow
   public abstract void clearCustomName();

   @Shadow
   public abstract void setItemDamage(int var1);

   @Shadow
   public abstract void setMetadata$v1_7(int var1);

   @Shadow
   public abstract List<String> getTooltip(EntityPlayer var1, ITooltipFlag var2);

   @Shadow
   public abstract List<String> getTooltip(EntityPlayer var1, boolean var2);

   @Shadow
   public abstract String getTranslationKey$v1_12();

   @Shadow
   public abstract String getUnlocalizedName();

   @Shadow
   public abstract boolean isItemEnchanted();

   @Shadow
   public abstract List<String> getTooltip(EntityPlayer var1, boolean var2);

   @Shadow
   public abstract boolean isEmpty();

   @Shadow
   public abstract NBTTagCompound getSubCompound(String var1, boolean var2);

   @Shadow
   public abstract NBTTagCompound getSubCompound(String var1);

   @Shadow
   public abstract void setTagInfo(String var1, NBTBase var2);

   @Shadow
   public abstract void setTagCompound(@Nullable NBTTagCompound var1);

   @Shadow
   public abstract NBTTagCompound writeToNBT(NBTTagCompound var1);

   @Shadow
   public abstract NBTTagCompound getOrCreateSubCompound$v1_12(String var1);

   @Shadow
   public abstract int getMetadata();

   @Override
   public String bridge$getDisplayName() {
      return this.getDisplayName();
   }

   @Override
   public String bridge$getRawDisplayName() {
      return this.getDisplayName().replaceAll("(?i)§[0-9A-FK-ORa-fk-or]", "");
   }

   @Override
   public boolean bridge$hasCustomLore() {
      if (this.stackTagCompound == null) {
         return false;
      }

      if (this.stackTagCompound.hasKey("display", 10)) {
         NBTTagCompound var1 = this.stackTagCompound.getCompoundTag("display");
         if (var1.getTagId("Lore") == 9) {
            NBTTagList var2 = var1.getTagList("Lore", 8);
            return var2.tagCount() > 0;
         }
      }

      return false;
   }

   @Override
   public Bridge6_4 bridge$getItem() {
      return (Bridge6_4)this.getItem();
   }

   @Override
   public int bridge$getStackSize() {
      return this.stackSize;
   }

   @Override
   public int bridge$getMaxDamage() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.getMaxDamage() : this.getMaxDurability$v1_7();
   }

   @Override
   public boolean bridge$isItemDamaged() {
      return this.isItemDamaged();
   }

   @Override
   public int bridge$getItemDamage() {
      return ThreadModuleDump63.MC_VERSION >= 1 ? this.getItemDamage() : this.getCurrentDurability$v1_7();
   }

   @Override
   public void bridge$setCustomModelData(List<Float> var1, List<Boolean> var2, List<String> var3, List<Integer> var4) {
      this.bridge$setItemDamage((Integer)var4.get(0));
   }

   @Override
   public int bridge$getCustomModelData() {
      return this.bridge$getItemDamage();
   }

   @Override
   public int bridge$getMaxStackSize() {
      return this.getMaxStackSize();
   }

   @Override
   public boolean bridge$isItemStackDamageableNoUnbr() {
      return this.item != null && (ThreadModuleDump63.MC_VERSION >= 1 ? this.item.getMaxDamage() : this.item.getMaxDurability$v1_7()) > 0;
   }

   @Override
   public int bridge$getRepairCost() {
      return this.getRepairCost();
   }

   @Override
   public boolean bridge$hasDisplayName() {
      return this.hasDisplayName();
   }

   @Override
   public boolean bridge$isItemStackDamageable() {
      return this.isItemStackDamageable();
   }

   @Override
   public void bridge$clearCustomName() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.clearCustomName();
      }
   }

   @Override
   public void bridge$setItemDamage(int var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.setItemDamage(var1);
      } else {
         this.setMetadata$v1_7(var1);
      }
   }

   @Override
   public void bridge$setStackDisplayName(Component var1) {
      this.setStackDisplayName(AdventureTextBridge.asLegacyString(var1));
   }

   @Annotation2(min = 5)
   @Override
   public void bridge$setLore(List<Component> var1) {
      NBTTagList var2 = new NBTTagList();

      for (Component var4 : var1) {
         var2.appendTag(new NBTTagString(AdventureTextBridge.asLegacyString(var4)));
      }

      this.getOrCreateSubCompound$v1_12("display").setTag("Lore", var2);
   }

   @Override
   public ItemStackBridge bridge$copy() {
      return (ItemStackBridge)this.copy();
   }

   @Override
   public void bridge$setRepairCost(int var1) {
      this.setRepairCost(var1);
   }

   @Override
   public Map<Bridge2_26, Integer> bridge$getEnchantments() {
      Map var1;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var1 = EnchantmentHelper.getEnchantments((ItemStack)this);
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         var1 = EnchantmentHelper.getEnchantments((ItemStack)this);
      } else {
         var1 = EnchantmentHelper.getEnchantments((ItemStack)this);
      }

      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var1.remove(null);
         return var1;
      }

      HashMap var2 = new HashMap();

      for (Entry var4 : var1.entrySet()) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            var2.put((Bridge2_26)Enchantment.getEnchantmentById((Integer)var4.getKey()), (Integer)var4.getValue());
         } else {
            var2.put((Bridge2_26)Enchantment.enchantmentsList[var4.getKey()], (Integer)var4.getValue());
         }
      }

      var2.remove(null);
      return var2;
   }

   @Override
   public void bridge$setEnchantments(Map<Bridge2_26, Integer> var1) {
      HashMap var2 = new HashMap();
      var1.forEach((var1x, var2x) -> {
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            var2.put(var1x, var2x);
         } else {
            int var3 = ((Enchantment)var1x).effectId;
            var2.put(var3, var2x);
         }
      });
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         EnchantmentHelper.setEnchantments(var2, (ItemStack)this);
      } else if (ThreadModuleDump63.MC_VERSION >= 1) {
         EnchantmentHelper.setEnchantments(var2, (ItemStack)this);
      } else {
         EnchantmentHelper.setEnchantments(var2, (ItemStack)this);
      }
   }

   @Override
   public void bridge$setPotionEffects(List<Fog> var1) {
      throw new AbstractMethodErrorImpl();
   }

   @Override
   public void bridge$setProfile(@Nullable UUID var1, String var2, String var3) {
      NBTTagCompound var4 = this.getTagCompound();
      if (var4 == null) {
         var4 = new NBTTagCompound();
      }

      NBTTagCompound var5 = new NBTTagCompound();
      UUID var6 = var1 != null ? var1 : UUID.nameUUIDFromBytes(var2.getBytes(StandardCharsets.UTF_8));
      var5.setString("Id", var6.toString());
      NBTTagCompound var7 = new NBTTagCompound();
      NBTTagList var8 = new NBTTagList();
      NBTTagCompound var9 = new NBTTagCompound();
      var9.setString("Value", var2);
      var9.setString("Signature", var3);
      var8.appendTag(var9);
      var7.setTag("textures", var8);
      var5.setTag("Properties", var7);
      var4.setTag("SkullOwner", var5);
      this.setTagCompound(var4);
   }

   @Annotation2(min = 5)
   @Override
   public void bridge$setArmorColor(int var1) {
      this.getOrCreateSubCompound$v1_12("display").setInteger("color", var1);
   }

   @Override
   public boolean bridge$isEmpty() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.isEmpty() : this.isItemEqual(lunar$EMPTY);
   }

   @Override
   public BridgeExtension$Type bridge$getItemUseAction() {
      return BridgeExtension$Type.values()[this.getItemUseAction().ordinal()];
   }

   @Override
   public int bridge$getMaxItemUseDuration() {
      return this.getMaxItemUseDuration();
   }

   @Override
   public boolean bridge$areItemsEqual(ItemStackBridge var1) {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? ItemStack.areItemsEqual((ItemStack)this, (ItemStack)var1)
         : ItemStack.areItemStacksEqual((ItemStack)this, (ItemStack)var1);
   }

   @Override
   public boolean bridge$areItemsEqual(ItemStackBridge var1, BiPredicate<ItemStackBridge, ItemStackBridge> var2) {
      return var2.test(this, var1);
   }

   @Override
   public List<String> bridge$getTooltip(Bridge5Extension_5 var1, boolean var2) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.getTooltip((EntityPlayer)var1, var2 ? TooltipFlags.ADVANCED : TooltipFlags.NORMAL);
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? this.getTooltip((EntityPlayer)var1, var2) : this.getTooltip((EntityPlayer)var1, var2);
      }
   }

   @Override
   public List<Component> bridge$getTooltipComponents(Bridge5Extension_5 var1, boolean var2) {
      return this.bridge$getTooltip(var1, var2).stream().map(var0 -> AdventureTextBridge.asAdventure(var0)).toList();
   }

   @Override
   public void bridge$setStackSize(int var1) {
      this.stackSize = var1;
   }

   @Override
   public String bridge$getUnlocalizedName() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.getTranslationKey$v1_12() : this.getUnlocalizedName();
   }

   @Override
   public void bridge$setTagCompound(Bridge_57 var1) {
      this.stackTagCompound = (NBTTagCompound)var1;
   }

   @Override
   public Bridge_57 bridge$getTagCompound() {
      return (Bridge_57)this.getTagCompound();
   }

   @Override
   public Optional<Bridge_57> bridge$saveTagCompound() {
      try {
         return Optional.ofNullable((Bridge_57)this.writeToNBT(new NBTTagCompound()));
      } catch (Exception var2) {
         return Optional.empty();
      }
   }

   @Override
   public boolean bridge$isItemEnchanted() {
      return this.isItemEnchanted();
   }

   @Override
   public boolean bridge$hasFoil() {
      return this.isItemEnchanted();
   }

   @Override
   public Optional<Bridge_9> bridge$getFood() {
      return this.item instanceof Bridge_9 var1 ? Optional.of(var1) : Optional.empty();
   }

   @Override
   public Optional<String> bridge$getBannerColor() {
      if (!(ThreadModuleDump63.MC_VERSION != 0 && this.item instanceof ItemBanner var1)) {
         return Optional.empty();
      } else {
         return ThreadModuleDump63.MC_VERSION == 1
            ? Optional.of(var1.getBaseColor((ItemStack)this).name)
            : Optional.of(ItemBanner.getBaseColor((ItemStack)this).name);
      }
   }

   @Override
   public void bridge$setBannerColor(String var1) {
      if (ThreadModuleDump63.MC_VERSION != 0 && this.item instanceof ItemBanner) {
         Integer var2 = (Integer)Markers7.field1.get(var1);
         if (var2 != null) {
            NBTTagCompound var3 = ThreadModuleDump63.MC_VERSION == 5 ? this.getSubCompound("BlockEntityTag") : this.getSubCompound("BlockEntityTag", true);
            if (var3 == null) {
               var3 = new NBTTagCompound();
               this.setTagInfo("BlockEntityTag", var3);
            }

            var3.setInteger("Base", var2);
            this.bridge$setItemDamage(var2);
         }
      }
   }

   @Override
   public boolean bridge$isItemEqual(ItemStackBridge var1) {
      return this.isItemEqual((ItemStack)var1);
   }

   @Override
   public void bridge$sbHideTooltipComponents() {
      throw new AbstractMethodErrorImpl();
   }

   @Nullable
   @Override
   public List<ItemStackBridge> bridge$getContainerItems() {
      ContainerItemsComponent var1 = this.bridge$getDataComponent(ItemDataComponentTypes.field53);
      return var1 == null ? null : var1.bridge$items();
   }

   @Override
   public boolean bridge$hasCustomModel() {
      return false;
   }

   @Override
   public boolean bridge$isItemCubeBlock() {
      if (this.lunar$isCubeBlock == null) {
         this.lunar$isCubeBlock = this.bridge$getItem().bridge$getBlockFromItem().map(Bridge3_23::bridge$isCubeBlock).orElse(false);
      }

      return this.lunar$isCubeBlock;
   }

   @Override
   public boolean bridge$isCrossbowFullyCharged() {
      return false;
   }

   @Override
   public boolean bridge$isItemSplashPotion() {
      return ThreadModuleDump63.MC_VERSION == 5 ? this.getItem() instanceof ItemSplashPotion : ItemPotion.isSplash(this.getMetadata());
   }

   @Override
   public void bridge$setDyedColor(int var1) {
      NBTTagCompound var2 = this.getTagCompound();
      if (var2 == null) {
         var2 = new NBTTagCompound();
      }

      NBTTagCompound var3 = var2.getCompoundTag("display");
      var3.setInteger("color", var1);
      var2.setTag("display", var3);
      this.setTagCompound(var2);
   }

   @Override
   public boolean bridge$canElytraFly() {
      return this.bridge$getItem().bridge$isItemElytra();
   }

   @Override
   public BridgeType2_4 bridge$getLunarItemType() {
      this.lunar$checkForItemUpdates();
      return this.lunar$itemType;
   }

   @Override
   public BridgeType3_2 bridge$getLunarItemMaterial() {
      this.lunar$checkForItemUpdates();
      return this.lunar$itemMaterial;
   }

   @Unique
   private void lunar$checkForItemUpdates() {
      Item var1 = this.getItem();
      if (!Objects.equals(var1, this.lunar$lastItemTypeCheck)) {
         this.lunar$lastItemTypeCheck = var1;
         this.lunar$setItemType();
         this.lunar$setItemMaterial();
      }
   }

   @Override
   public String bridge$getItemRegistryName() {
      return this.bridge$getItem().bridge$getRegistryName();
   }

   @Nullable
   @Override
   public ArmorColorState bridge$getArmorState() {
      Bridge6_4 var1 = (Bridge6_4)this.getItem();
      if (var1.bridge$isArmor()) {
         Bridge6Extension3 var2 = (Bridge6Extension3)var1;
         boolean var3 = var2.bridge$hasColor(this);
         return new ArmorColorState(var3, var3 ? var2.bridge$getColor(this) : 0, var2.bridge$getResourcePath());
      } else {
         return null;
      }
   }

   @Unique
   private void lunar$setItemType() {
      Bridge6_4 var1 = (Bridge6_4)this.item;
      if (var1 != null && (ThreadModuleDump63.MC_VERSION < 5 || this.item != ItemStack.EMPTY$v1_12.item && this.item != Items.AIR$v1_12)) {
         if (var1 instanceof ItemSword) {
            this.lunar$itemType = BridgeType2_4.SWORD;
         } else if (var1 instanceof ItemPickaxe) {
            this.lunar$itemType = BridgeType2_4.PICKAXE;
         } else if (var1 instanceof ItemAxe) {
            this.lunar$itemType = BridgeType2_4.AXE;
         } else if (var1 instanceof ItemSpade) {
            this.lunar$itemType = BridgeType2_4.SHOVEL;
         } else if (var1 instanceof ItemHoe) {
            this.lunar$itemType = BridgeType2_4.HOE;
         } else if (var1.bridge$isItemSkull()) {
            this.lunar$itemType = BridgeType2_4.SKULL;
         } else if (var1.bridge$getRegistryName().contains("elytra")) {
            this.lunar$itemType = BridgeType2_4.ELYTRA;
         } else if (ThreadModuleDump63.MC_VERSION >= 5 && var1 instanceof ItemShield) {
            this.lunar$itemType = BridgeType2_4.SHIELD;
         } else if (var1 instanceof ItemBlock) {
            this.lunar$itemType = BridgeType2_4.BLOCK;
         } else if (var1 instanceof ItemArmor) {
            this.lunar$itemType = BridgeType2_4.ARMOR;
         } else if (var1 instanceof ItemPotion) {
            if (ThreadModuleDump63.MC_VERSION >= 5 && var1 instanceof ItemSplashPotion) {
               this.lunar$itemType = BridgeType2_4.SPLASH_POTION;
            } else if (ThreadModuleDump63.MC_VERSION >= 5 && var1 instanceof ItemLingeringPotion) {
               this.lunar$itemType = BridgeType2_4.LINGERING_POTION;
            } else {
               this.lunar$itemType = BridgeType2_4.POTION;
            }
         } else if (ThreadModuleDump63.MC_VERSION >= 5 && var1 instanceof ItemTippedArrow) {
            this.lunar$itemType = BridgeType2_4.TIPPED_ARROW;
         } else if (var1 instanceof ItemMonsterPlacer) {
            this.lunar$itemType = BridgeType2_4.SPAWN_EGG;
         } else if (var1 instanceof ItemFireworkCharge) {
            this.lunar$itemType = BridgeType2_4.FIREWORK_STAR;
         } else {
            this.lunar$itemType = BridgeType2_4.UNKNOWN;
         }
      } else {
         this.lunar$itemType = BridgeType2_4.EMPTY;
      }
   }

   @Unique
   private void lunar$setItemMaterial() {
      if (this.item instanceof ItemSword var1) {
         this.lunar$itemMaterial = ThreadModuleDump63.MC_VERSION >= 1
            ? BridgeType3_2.fromMaterial(var1.blockMaterial.name())
            : BridgeType3_2.fromMaterial(var1.repairMaterial$v1_7.name());
      } else if (this.item instanceof ItemPickaxe var2) {
         this.lunar$itemMaterial = BridgeType3_2.fromMaterial(var2.toolMaterial.name());
      } else if (this.item instanceof ItemAxe var3) {
         this.lunar$itemMaterial = BridgeType3_2.fromMaterial(var3.toolMaterial.name());
      } else if (this.item instanceof ItemSpade var4) {
         this.lunar$itemMaterial = BridgeType3_2.fromMaterial(var4.toolMaterial.name());
      } else if (this.item instanceof ItemHoe var5) {
         this.lunar$itemMaterial = ThreadModuleDump63.MC_VERSION == 5
            ? BridgeType3_2.fromMaterial(var5.toolMaterial$v1_12.name())
            : BridgeType3_2.fromMaterial(var5.theToolMaterial.name());
      } else {
         this.lunar$itemMaterial = BridgeType3_2.UNKNOWN;
      }
   }

   @Override
   public Optional<Bridge4_5> bridge$getMaterial() {
      return this.getItem() instanceof MixinHelper_2 var1 ? Optional.of(var1.lunar$getMaterial()) : Optional.empty();
   }
}
