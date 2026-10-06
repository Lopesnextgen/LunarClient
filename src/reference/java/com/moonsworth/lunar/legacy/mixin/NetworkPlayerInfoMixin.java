package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.NetworkPlayerInfoBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.NetworkPlayerInfoImpl;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@VersionGate(min = 1)
@Mixin(NetworkPlayerInfo.class)
public abstract class NetworkPlayerInfoMixin implements NetworkPlayerInfoBridge, PlayerInfoBridge {
   @Shadow
   public Map<Type, ResourceLocation> playerTextures$v1_12;
   @Shadow
   public ResourceLocation locationCape;
   @Final
   @Shadow
   public GameProfile gameProfile;
   @Shadow
   public IChatComponent displayName;
   @Unique
   private final AtomicReference<Object> lunar$profileTextureUuid = new AtomicReference<>();

   public NetworkPlayerInfoMixin() {
   }

   @Shadow
   public abstract GameProfile getGameProfile();

   @Shadow
   public abstract String getSkinType();

   @Shadow
   public abstract int getResponseTime();

   @Shadow
   public abstract ResourceLocation getLocationSkin();

   @Shadow
   public abstract IChatComponent getDisplayName();

   @Shadow
   public abstract ScorePlayerTeam getPlayerTeam();

   public Component bridge$getDisplayName() {
      return (Component)(this.displayName == null ? Component.empty() : TextBridge.asAdventure((Bridge2_42)this.displayName));
   }

   public void bridge$setCapeLocation(ResourceLocationBridge horsestats141) {
      if (Ref.MC_VERSION >= 5) {
         this.playerTextures$v1_12.put(Type.CAPE, (ResourceLocation)horsestats141);
      } else {
         this.locationCape = (ResourceLocation)horsestats141;
      }
   }

   public ResourceLocationBridge bridge$getCapeLocation() {
      return Ref.MC_VERSION >= 5 ? (ResourceLocationBridge)this.playerTextures$v1_12.get(Type.CAPE) : (ResourceLocationBridge)this.locationCape;
   }

   @ModifyReturnValue(method = "getLocationSkin", at = @At("RETURN"))
   private ResourceLocation lunar$getLocationSkin(ResourceLocation location1) {
      if (Ref.method4() != null
         && Ref.method4().method40().method41() != null
         && !((NetworkPlayerInfo)this instanceof NetworkPlayerInfoImpl)) {
         ResourceLocationBridge horsestats142 = Ref.method4().method40().method41().method16(this.getGameProfile().getId(), this.getSkinType(), (ResourceLocationBridge)location1);
         return (ResourceLocation)horsestats142;
      } else {
         return location1;
      }
   }

   @ModifyReturnValue(method = "getSkinType", at = @At("RETURN"))
   private String lunar$getSkinType(String text1) {
      NickHider nickhider2;
      return Ref.method4() != null
            && !((NetworkPlayerInfo)this instanceof NetworkPlayerInfoImpl)
            && (nickhider2 = Ref.method4().method40().method41()) != null
         ? nickhider2.getPlayerName(this.getGameProfile().getId(), text1)
         : text1;
   }

   public GameProfile bridge$getGameProfile() {
      return this.gameProfile;
   }

   public UUID bridge$getProfileTextureId() {
      return this.getLunar$profileTextureUuid();
   }

   public boolean bridge$hasMismatchedId() {
      return this.gameProfile.getId() == null ? false : !this.gameProfile.getId().equals(this.getLunar$profileTextureUuid());
   }

   public int bridge$getLatency() {
      return this.getResponseTime();
   }

   public ResourceLocationBridge bridge$getLocationSkin() {
      return (ResourceLocationBridge)this.getLocationSkin();
   }

   public UUID bridge$getUniqueId() {
      return this.getGameProfile().getId();
   }

   public Component bridge$formatName() {
      return (Component)(this.getDisplayName() != null
         ? TextBridge.asAdventure((Bridge2_42)this.getDisplayName())
         : TextBridge.asAdventure(ScorePlayerTeam.formatPlayerName(this.getPlayerTeam(), this.getGameProfile().getName())));
   }

   @Generated
   public UUID getLunar$profileTextureUuid() {
      Object obj1 = this.lunar$profileTextureUuid.get();
      if (obj1 == null) {
         synchronized (this.lunar$profileTextureUuid) {
            obj1 = this.lunar$profileTextureUuid.get();
            if (obj1 == null) {
               UUID uuid3 = Client.method109().method53().method26(this.gameProfile);
               obj1 = uuid3 == null ? this.lunar$profileTextureUuid : uuid3;
               this.lunar$profileTextureUuid.set(obj1);
            }
         }
      }

      return (UUID)(obj1 == this.lunar$profileTextureUuid ? null : obj1);
   }
}
