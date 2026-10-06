package com.moonsworth.lunar.legacy.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Objects;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Annotation2(max = 0)
@Mixin(GuiPlayerInfo.class)
public abstract class GuiPlayerInfoMixin implements Bridge2_33 {
   @Final
   @Shadow
   public String name;
   @Shadow
   public int responseTime;
   @Unique
   private UUID lunar$profileTextureUuid = null;
   @Unique
   private GameProfile lunar$gameProfile = null;
   @Unique
   private String lunar$previousName = null;
   @Unique
   private String lunar$previousTeamStrippedName = null;

   @Override
   public Component bridge$getDisplayName() {
      return this.name == null ? Component.empty() : AdventureTextBridge.asAdventure(this.name);
   }

   @Override
   public GameProfile bridge$getGameProfile() {
      ScorePlayerTeam var1 = Minecraft.getMinecraft().theWorld.getScoreboard().getPlayersTeam(this.name);
      boolean var2 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.name) == 0;
      String var3 = ScorePlayerTeam.formatPlayerName(var1, var2 ? "" : this.name);
      String var4 = AdventureChatFormatting.getTextWithoutFormattingCodes(var3);
      if (this.lunar$gameProfile != null && Objects.equals(this.lunar$previousName, this.name) && Objects.equals(this.lunar$previousTeamStrippedName, var4)) {
         return this.lunar$gameProfile;
      }

      if (Minecraft.getMinecraft().theWorld != null && this.name != null) {
         this.lunar$gameProfile = null;
         this.lunar$previousName = this.name;
         this.lunar$previousTeamStrippedName = var4;
         EntityPlayer var5 = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(AdventureChatFormatting.getTextWithoutFormattingCodes(this.name));
         if (var5 == null) {
            var4 = var4.replaceAll("[^a-zA-Z0-9_]", "");
            if (var4.length() > 16) {
               var4 = var4.substring(var4.length() - 16);
            }

            var5 = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(var4);
         }

         if (var5 == null) {
            return null;
         }

         this.lunar$gameProfile = var5.getGameProfile();
         this.lunar$profileTextureUuid = Client.method109().method53().method26(this.lunar$gameProfile);
         if (this.lunar$profileTextureUuid == null) {
            this.lunar$profileTextureUuid = this.lunar$gameProfile.getId();
         }

         return this.lunar$gameProfile;
      } else {
         return null;
      }
   }

   @Override
   public UUID bridge$getProfileTextureId() {
      return this.lunar$profileTextureUuid;
   }

   @Override
   public boolean bridge$hasMismatchedId() {
      return this.lunar$profileTextureUuid != null && this.lunar$gameProfile != null
         ? !this.lunar$profileTextureUuid.equals(this.lunar$gameProfile.getId())
         : false;
   }

   @Override
   public int bridge$getLatency() {
      return this.responseTime;
   }

   @Override
   public Component bridge$formatName() {
      ScorePlayerTeam var1 = Minecraft.getMinecraft().theWorld.getScoreboard().getPlayersTeam(this.name);
      return AdventureTextBridge.asAdventure(ScorePlayerTeam.formatPlayerName(var1, this.name));
   }
}
