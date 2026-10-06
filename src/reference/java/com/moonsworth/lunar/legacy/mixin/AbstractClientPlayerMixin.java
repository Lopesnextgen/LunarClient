package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_6;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_30;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.player.PlayerJoinWorldEventLegacy;
import com.moonsworth.lunar.client.event.player.PlayerJoinWorldLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.FovRenderEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.EntityPlayerSPImpl;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends EntityPlayer implements Bridge5_11 {
   @Unique
   private ResourceLocation lunar$skinLocationOverride;
   @Unique
   private boolean lunar$cancelSkinOverride;
   @Unique
   private String lunar$skinTypeOverride;
   @Shadow
   public ResourceLocation locationCape$v1_7;
   @Shadow
   public ResourceLocation locationSkin$v1_7;
   @Unique
   @Annotation2(max = 0)
   private String skinType;

   @Shadow
   public abstract boolean isSpectator();

   @Shadow
   public abstract String getSkinType();

   @Shadow
   public abstract ResourceLocation getLocationSkin();

   @Shadow
   @Nullable
   public abstract NetworkPlayerInfo getPlayerInfo();

   public AbstractClientPlayerMixin(World var1, GameProfile var2) {
      super(var1, var2);
   }

   @Override
   public boolean bridge$isSpectator() {
      return ThreadModuleDump63.MC_VERSION >= 1 && this.isSpectator();
   }

   @Override
   public ResourceLocationBridge bridge$getCapeLocation() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         NetworkPlayerInfo var1 = this.getPlayerInfo();
         return var1 instanceof Bridge_30 ? ((Bridge_30)var1).bridge$getCapeLocation() : null;
      } else {
         return (ResourceLocationBridge)this.locationCape$v1_7;
      }
   }

   @Override
   public void bridge$setCapeLocation(ResourceLocationBridge var1) {
      PlayerJoinWorldLegacyEvent var2 = ClientEventBus.method29().method12(PlayerJoinWorldLegacyEvent.class, () -> new PlayerJoinWorldLegacyEvent(this));
      if (var2 == null || !var2.isCancelled()) {
         this.bridge$setCapeLocationOverride(var1);
      }
   }

   @Override
   public void bridge$setCapeLocationOverride(ResourceLocationBridge var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         try {
            NetworkPlayerInfo var2 = this.getPlayerInfo();
            if (var2 instanceof Bridge_30) {
               ((Bridge_30)var2).bridge$setCapeLocation(var1);
            }
         } catch (NullPointerException var3) {
         }
      } else {
         this.locationCape$v1_7 = (ResourceLocation)var1;
      }
   }

   @Inject(method = "<init>", at = @At("RETURN"))
   private void lunar$createPlayer(World var1, GameProfile var2, CallbackInfo var3) {
      ClientEventBus.method29().method12(PlayerJoinWorldEventLegacy.class, () -> new PlayerJoinWorldEventLegacy(this));
   }

   @Annotation2(max = 0)
   @Inject(method = "hasSkin", at = @At("HEAD"), cancellable = true)
   private void lunar$hasSkin(CallbackInfoReturnable<Boolean> var1) {
      var1.setReturnValue(this.getLocationSkin() != null);
   }

   @Annotation2(max = 0)
   @Inject(method = "getLocationSkin", at = @At("RETURN"), cancellable = true)
   public void lunar$getLocationSkin$v1_7(CallbackInfoReturnable<ResourceLocation> var1) {
      ResourceLocation var2 = (ResourceLocation)var1.getReturnValue();
      if (ThreadModuleDump63.method4() != null
         && ThreadModuleDump63.method4().method40().method41() != null
         && !((AbstractClientPlayer)this instanceof EntityPlayerSPImpl)) {
         ResourceLocationBridge var3 = ThreadModuleDump63.method4().method40().method41().method16(this.getGameProfile().getId(), "default", (ResourceLocationBridge)var2);
         var1.setReturnValue((ResourceLocation)var3);
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "getFovModifier$v1_8", at = @At("TAIL"), cancellable = true)
   private void lunar$getFovModifier(CallbackInfoReturnable<Float> var1) {
      FovRenderEvent var2 = ClientEventBus.method29().method12(FovRenderEvent.class, () -> new FovRenderEvent((Float)var1.getReturnValue()));
      if (var2 != null) {
         if (var2.isCancelled()) {
            var1.setReturnValue(1.0F);
         } else {
            var1.setReturnValue(var2.method1());
         }
      }
   }

   @Override
   public String bridge$getSkinType() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         return this.getSkinType();
      }

      if (!this.lunar$cancelSkinOverride && this.lunar$skinTypeOverride != null) {
         return this.lunar$skinTypeOverride;
      }

      if (this.skinType == null) {
         Map var1 = Minecraft.getMinecraft().sessionService.getTextures(this.gameProfile, false);
         if (this.gameProfile.getProperties().isEmpty()) {
            Minecraft.getMinecraft().getSessionService().fillProfileProperties(this.gameProfile, true);
            var1 = Minecraft.getMinecraft().getSessionService().getTextures(this.gameProfile, false);
         }

         if (var1.containsKey(com.mojang.authlib.minecraft.MinecraftProfileTexture.Type.SKIN)
            && ((MinecraftProfileTexture)var1.get(com.mojang.authlib.minecraft.MinecraftProfileTexture.Type.SKIN)).getMetadata("model") != null) {
            this.skinType = "slim";
         } else {
            this.skinType = "default";
         }
      }

      return this.skinType;
   }

   @Override
   public ResourceLocationBridge bridge$getLocationSkin() {
      return (ResourceLocationBridge)this.getLocationSkin();
   }

   @Override
   public ResourceLocationBridge bridge$getLocationSkinNoOverride() {
      this.lunar$cancelSkinOverride = true;
      ResourceLocationBridge var1 = (ResourceLocationBridge)this.getLocationSkin();
      this.lunar$cancelSkinOverride = false;
      return var1;
   }

   @Override
   public float bridge$getSwingProgress(float var1) {
      return this.getSwingProgress(var1);
   }

   @Override
   public Optional<String> bridge$loadAndGetRealSkinType() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         NetworkPlayerInfo var1 = this.getPlayerInfo();
         if (var1 != null) {
            var1.loadPlayerTextures();
            return Optional.ofNullable(var1.skinType);
         } else {
            return Optional.empty();
         }
      } else {
         return Optional.ofNullable(this.bridge$getSkinType());
      }
   }

   @Override
   public ResourceLocationBridge bridge$getLocationSkinDefault() {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (ResourceLocationBridge)DefaultPlayerSkin.getDefaultSkin(this.getUniqueID())
         : (ResourceLocationBridge)AbstractClientPlayer.locationStevePng$v1_7;
   }

   @Override
   public boolean bridge$isSkinTextureUploaded() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         if (!this.lunar$cancelSkinOverride && this.lunar$skinLocationOverride != null) {
            return true;
         } else {
            NetworkPlayerInfo var1 = ((AbstractClientPlayer)this).getPlayerInfo();
            if (var1 == null) {
               return false;
            } else {
               return ThreadModuleDump63.MC_VERSION >= 5
                  ? var1.playerTextures$v1_12.get(com.mojang.authlib.minecraft.MinecraftProfileTexture.Type.SKIN) != null
                  : var1.locationSkin != null;
            }
         }
      } else {
         return !this.lunar$cancelSkinOverride && this.lunar$skinLocationOverride != null || ((AbstractClientPlayer)this).locationSkin$v1_7 != null;
      }
   }

   @Override
   public boolean bridge$isModelPartShown(Bridge5_6 var1) {
      return ThreadModuleDump63.MC_VERSION >= 1 ? ((AbstractClientPlayer)this).isWearing((EnumPlayerModelParts)var1) : true;
   }

   @Inject(method = "getLocationSkin", at = @At("HEAD"), cancellable = true)
   private void lunar$getLocationSkin(CallbackInfoReturnable<ResourceLocation> var1) {
      if (!this.lunar$cancelSkinOverride && this.lunar$skinLocationOverride != null) {
         var1.setReturnValue(this.lunar$skinLocationOverride);
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "getSkinType$v1_8", at = @At("HEAD"), cancellable = true)
   private void lunar$getSkinType$v1_8(CallbackInfoReturnable<String> var1) {
      if (!this.lunar$cancelSkinOverride && this.lunar$skinTypeOverride != null) {
         var1.setReturnValue(this.lunar$skinTypeOverride);
      }
   }

   @Override
   public void bridge$setSkinLocationOverride(ResourceLocationBridge var1, String var2) {
      this.lunar$skinLocationOverride = (ResourceLocation)var1;
      this.lunar$skinTypeOverride = var2;
   }

   @Override
   public ItemStackRenderStateBridge bridge$getMainHandItemRenderState() {
      return (ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getHeldItem(EnumHand.MAIN_HAND) : this.getHeldItem());
   }

   @Override
   public ItemStackRenderStateBridge bridge$getOffHandItemRenderState() {
      return (ItemStackBridge)(ThreadModuleDump63.MC_VERSION >= 5 ? this.getHeldItem(EnumHand.OFF_HAND) : this.getHeldItem());
   }

   @Override
   public void bridge$setSkinLocation(ResourceLocationBridge var1, String var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         NetworkPlayerInfo var3 = this.getPlayerInfo();
         if (var3 != null) {
            var3.skinType = var2;
            if (ThreadModuleDump63.MC_VERSION == 5) {
               var3.playerTextures$v1_12.put(com.mojang.authlib.minecraft.MinecraftProfileTexture.Type.SKIN, (ResourceLocation)var1);
            } else {
               var3.locationSkin = (ResourceLocation)var1;
            }
         }
      } else {
         this.locationSkin$v1_7 = (ResourceLocation)var1;
      }
   }
}
