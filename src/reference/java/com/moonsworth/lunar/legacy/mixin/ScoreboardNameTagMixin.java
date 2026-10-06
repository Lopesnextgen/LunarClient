package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.mixin.NameTagUpdateListener;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Scoreboard.class)
public class ScoreboardNameTagMixin {
   @Shadow
   public Map<String, ScorePlayerTeam> teams;
   @Shadow
   public Map teams$v1_7;

   public ScoreboardNameTagMixin() {
   }

   @Inject(method = "createTeam", at = @At("HEAD"), cancellable = true)
   private void lunar$cancelDuplicateTeamCreation(String text1, CallbackInfoReturnable<ScorePlayerTeam> callbackinforeturnable2) {
      if (Ref.MC_VERSION >= 1) {
         if (this.teams.containsKey(text1)) {
            callbackinforeturnable2.setReturnValue(this.teams.get(text1));
         }
      } else if (this.teams$v1_7.containsKey(text1)) {
         callbackinforeturnable2.setReturnValue((ScorePlayerTeam)this.teams$v1_7.get(text1));
      }
   }

   @VersionGate(0)
   @Inject(method = "func_151392_a$v1_7", at = @At("HEAD"))
   private void lunar$addPlayerToTeam$v1_7(String text1, String text2, CallbackInfoReturnable<Boolean> callbackinforeturnable3) {
      this.lunar$invalidateNameTagsByName(text1);
   }

   @VersionGate(min = 1)
   @Inject(method = "addPlayerToTeam$v1_8", at = @At("HEAD"))
   private void lunar$addPlayerToTeam$v1_8(String text1, String text2, CallbackInfoReturnable<Boolean> callbackinforeturnable3) {
      this.lunar$invalidateNameTagsByName(text1);
   }

   @Inject(method = "removeTeam", at = @At("HEAD"))
   private void lunar$removeTeam(ScorePlayerTeam scoreplayerteam1, CallbackInfo callback2) {
      if (Ref.MC_VERSION >= 1) {
         for (String text4 : scoreplayerteam1.membershipSet) {
            this.lunar$invalidateNameTagsByName(text4);
         }
      } else {
         for (Object obj7 : scoreplayerteam1.membershipSet$v1_7) {
            if (obj7 instanceof String text5) {
               this.lunar$invalidateNameTagsByName(text5);
            }
         }
      }
   }

   @Unique
   private void lunar$invalidateNameTagsByName(String text1) {
      WorldClient world2 = Minecraft.getMinecraft().theWorld;
      if (world2 != null) {
         for (EntityPlayer player5 : Ref.MC_VERSION >= 1 ? world2.playerEntities : world2.playerEntities$v1_7) {
            if (text1.equals(player5.getGameProfile().getName()) && player5 instanceof NameTagUpdateListener threadmoduledump886) {
               threadmoduledump886.lunar$onNameTagUpdate();
            }
         }
      }
   }
}
