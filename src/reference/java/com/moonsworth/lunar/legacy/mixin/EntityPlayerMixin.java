package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_30;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.Bridge3_32;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_24;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventPlayerDied;
import com.moonsworth.lunar.client.event.mixin.highlight.HologramUpdateEvent;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements BridgeExtension, Bridge6_10 {
   @Final
   @Shadow
   public GameProfile gameProfile;
   @Shadow
   public PlayerCapabilities capabilities;
   @Shadow
   public InventoryPlayer inventory;
   @Shadow
   public int flyToggleTimer;
   @Shadow
   public ItemStack itemInUse;

   @Shadow
   public abstract FoodStats getFoodStats();

   @Shadow
   public abstract float getBedOrientationInDegrees();

   @Shadow
   public abstract boolean canEat(boolean var1);

   @Shadow
   public abstract boolean isSpectator();

   @Shadow
   public abstract ItemStack getHeldItem();

   @Shadow
   public abstract boolean isBlocking();

   @Shadow
   public abstract ItemStack getCurrentEquippedItem();

   @Shadow
   public abstract ItemStack getCurrentArmor(int var1);

   @Shadow
   public abstract int getItemInUseCount();

   @Shadow
   public abstract boolean isUsingItem();

   @Shadow
   public abstract ItemStack getItemInUse();

   @Shadow
   public abstract int getItemInUseDuration();

   @Shadow
   public abstract void addChatComponentMessage(IChatComponent var1);

   @Shadow
   public abstract EnumHandSide getPrimaryHand$v1_12();

   public EntityPlayerMixin(World var1) {
      super(var1);
   }

   @Override
   public GameProfile bridge$getGameProfile() {
      return this.gameProfile;
   }

   @Override
   public Bridge3_32 bridge$getPlayerCapabilities() {
      return (Bridge3_32)this.capabilities;
   }

   @Override
   public boolean bridge$isSpectator() {
      return ThreadModuleDump63.MC_VERSION >= 1 && this.isSpectator();
   }

   @Override
   public void bridge$addChatMessage(Bridge2_42 var1) {
      Chat var2 = ThreadModuleDump63.method4().method40().method47();
      if (!var2.isEnabled() || !var2.method39().get()) {
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            this.sendMessage$v1_12((IChatComponent)var1);
         } else if (ThreadModuleDump63.MC_VERSION >= 1) {
            this.addChatMessage((IChatComponent)var1);
         } else {
            this.addChatComponentMessage((IChatComponent)var1);
         }
      }
   }

   @Override
   public Bridge_24 bridge$getInventory() {
      return (Bridge_24)this.inventory;
   }

   @Override
   public void bridge$openInventory() {
      Minecraft.getMinecraft().displayGuiScreen(new GuiInventory((EntityPlayer)this));
   }

   @Override
   public ItemStackBridge bridge$getCurrentEquippedItem() {
      return (ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getHeldItem(EnumHand.MAIN_HAND) : this.getCurrentEquippedItem());
   }

   @Override
   public int bridge$getCurrentEquippedItemIndex() {
      return this.inventory.currentItem;
   }

   @Override
   public void bridge$setCurrentEquippedItemIndex(int var1) {
      this.inventory.currentItem = var1;
   }

   @Override
   public boolean bridge$isSprinting() {
      return this.isSprinting();
   }

   @Override
   public String bridge$getName() {
      return this.gameProfile.getName();
   }

   @Override
   public Bridge2_30 bridge$getFoodStats() {
      return (Bridge2_30)this.getFoodStats();
   }

   @Override
   public boolean bridge$isBlocking() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.isActiveItemStackBlocking$v1_12() : this.isBlocking();
   }

   public ItemStackBridge bridge$getMainHandItemRenderState() {
      return (ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getHeldItem(EnumHand.MAIN_HAND) : this.getHeldItem());
   }

   @Override
   public ItemStackRenderStateBridge bridge$getOffHandItemRenderState() {
      return (ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getHeldItem(EnumHand.OFF_HAND) : this.getHeldItem());
   }

   @Override
   public void bridge$preparePlayerToSpawn() {
      super.preparePlayerToSpawn();
   }

   @Override
   public ItemStackRenderStateBridge bridge$getArmor(EquipmentSlotBridge var1) {
      byte var2 = switch (var1) {
         case MAINHAND, OFFHAND -> throw new UnsupportedOperationException("Mainhand/Offhand is invalid slot for method getArmor!");
         case FEET -> 0;
         case LEGS -> 1;
         case CHEST -> 2;
         case HEAD -> 3;
      };
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         ItemStack var3 = this.inventory.armorItemInSlot(var2);
         return !var3.equals(ItemStack.EMPTY$v1_12) && var3.getItem() != Items.AIR$v1_12 ? (ItemStackBridge)var3 : null;
      } else {
         return (ItemStackBridge)this.getCurrentArmor(var2);
      }
   }

   @Override
   public float bridge$getItemProgress() {
      ItemStack var1 = ThreadModuleDump63.MC_VERSION >= 5 ? this.getActiveItemStack$v1_12() : this.itemInUse;
      if (var1 != null && (ThreadModuleDump63.MC_VERSION < 5 || !var1.isEmpty())) {
         float var2 = var1.item == Items.bow ? 20.0F : var1.getMaxItemUseDuration();
         return var2 == 0.0F ? 0.0F : Math.min(1.0F, this.bridge$getItemInUseDuration() / var2);
      } else {
         return 0.0F;
      }
   }

   @Override
   public int bridge$getItemInUseCount() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.getItemInUseCount() : this.getItemInUseCount();
   }

   @Override
   public int bridge$getTicksUsingItem() {
      return this.bridge$getItemInUseCount();
   }

   @Override
   public float bridge$getBedOrientationInDegrees() {
      return this.getBedOrientationInDegrees();
   }

   @Inject(method = "onUpdate", at = @At("TAIL"))
   private void lunar$cosmeticPostUpdate(CallbackInfo var1) {
      if (this.world.isRemote) {
         ClientEventBus.method29().method12(HologramUpdateEvent.class, () -> new HologramUpdateEvent(this));
      }
   }

   @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
   private void lunar$livingEntityDeathEvent(DamageSource var1, CallbackInfo var2) {
      if (this.world.isRemote) {
         EventPlayerDied var3 = ClientEventBus.method29().method12(EventPlayerDied.class, () -> new EventPlayerDied(this));
         if (var3 != null && var3.isCancelled()) {
            var2.cancel();
         }
      }
   }

   @Override
   public boolean bridge$isUsingItem() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.isHandActive$v1_12() : this.isUsingItem();
   }

   @Override
   public void bridge$setFlyToggleTimer(int var1) {
      this.flyToggleTimer = var1;
   }

   @Override
   public double bridge$getMovementSpeedAttribute() {
      return this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
   }

   @Override
   public double bridge$getAttackDamageAttribute() {
      return this.getEntityAttribute(SharedMonsterAttributes.damageVsEntity).getAttributeValue();
   }

   @Override
   public Optional<ItemStackBridge> bridge$getItemInUse() {
      return Optional.ofNullable((ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getActiveItemStack$v1_12() : this.getItemInUse()));
   }

   @Override
   public int bridge$getItemInUseDuration() {
      return ThreadModuleDump63.MC_VERSION >= 5 ? this.getItemInUseMaxCount$v1_12() : this.getItemInUseDuration();
   }

   @Override
   public boolean bridge$canEat(boolean var1) {
      return this.canEat(var1);
   }

   @Override
   public boolean bridge$isFlying() {
      return this.capabilities.isFlying;
   }

   @Override
   public boolean bridge$isJumping() {
      return this.isJumping;
   }

   @Override
   public ItemStackBridge bridge$getPickResult() {
      return null;
   }

   @Nullable
   @Override
   public ResourceLocationBridge bridge$getServerSkinTexture() {
      Minecraft var1 = Minecraft.getMinecraft();
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         AbstractClientPlayer var5 = (AbstractClientPlayer)var1.theWorld.getPlayerEntityByUUID(this.getUniqueID());
         return var5 == null ? null : (ResourceLocationBridge)var5.getLocationSkin();
      }

      NetHandlerPlayClient var2 = ThreadModuleDump63.MC_VERSION == 5 ? var1.getConnection$v1_12() : var1.getNetHandler();
      if (var2 == null) {
         return null;
      }

      Map var3 = var2.playerInfoMap;
      if (var3 == null) {
         return null;
      }

      NetworkPlayerInfo var4 = (NetworkPlayerInfo)var3.get(this.getUniqueID());
      return var4 == null ? null : (ResourceLocationBridge)var4.getLocationSkin();
   }

   @Override
   public double bridge$blockInteractionRange() {
      PlayerControllerMP var1 = ((Minecraft)ThreadModuleDump63.method3()).playerController;
      return var1.extendedReach() ? 6.0 : var1.getBlockReachDistance();
   }

   @Override
   public double bridge$entityInteractionRange() {
      PlayerControllerMP var1 = ((Minecraft)ThreadModuleDump63.method3()).playerController;
      return var1.extendedReach() ? 6.0 : 3.0;
   }

   @Override
   public boolean bridge$isEmoting() {
      return ThreadModuleDump63.method4().method45().method9(this);
   }

   @Nullable
   @Override
   public <C> C bridge$getEmoteController() {
      return (C)EmoteController.get(this);
   }

   @Override
   public <C> List<C> bridge$getWornCosmetics() {
      return (List<C>)ThreadModuleDump63.method4().method53().method19(this.bridge$getUniqueID());
   }

   @Override
   public boolean bridge$isDummySelf() {
      return this instanceof ThreadModuleDump54 var1 && var1.getDummyPlayerType() == ThreadModuleDump54.Type.SELF;
   }

   @Override
   public boolean bridge$isDummyMannequin() {
      return this instanceof ThreadModuleDump54 var1 && var1.getDummyPlayerType() == ThreadModuleDump54.Type.MANNEQUIN;
   }

   @Override
   public boolean bridge$isSelf() {
      return this == ThreadModuleDump63.method3().bridge$getPlayer();
   }

   @Override
   public boolean bridge$isSkinTextureUploaded() {
      return false;
   }

   @Override
   public void bridge$setUseItem(ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         this.activeItemStack$v1_12 = (ItemStack)var1;
      }
   }

   @Override
   public void bridge$setUseItemRemaining(int var1) {
      if (ThreadModuleDump63.MC_VERSION == 5) {
         this.activeItemStackUseCount$v1_12 = var1;
      }
   }

   @Annotation2(min = 5)
   @Override
   public boolean bridge$isMainHandSwapped() {
      return this.getPrimaryHand$v1_12() == EnumHandSide.LEFT;
   }
}
