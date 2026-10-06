package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump88;
import com.moonsworth.lunar.ichor.Annotation2;
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
public class ScoreboardMixin2 {
   @Shadow
   public Map<String, ScorePlayerTeam> teams;
   @Shadow
   public Map teams$v1_7;

   @Inject(method = "createTeam", at = @At("HEAD"), cancellable = true)
   private void lunar$cancelDuplicateTeamCreation(String var1, CallbackInfoReturnable<ScorePlayerTeam> var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         if (this.teams.containsKey(var1)) {
            var2.setReturnValue(this.teams.get(var1));
         }
      } else if (this.teams$v1_7.containsKey(var1)) {
         var2.setReturnValue((ScorePlayerTeam)this.teams$v1_7.get(var1));
      }
   }

   @Annotation2(0)
   @Inject(method = "func_151392_a$v1_7", at = @At("HEAD"))
   private void lunar$addPlayerToTeam$v1_7(String var1, String var2, CallbackInfoReturnable<Boolean> var3) {
      this.lunar$invalidateNameTagsByName(var1);
   }

   @Annotation2(min = 1)
   @Inject(method = "addPlayerToTeam$v1_8", at = @At("HEAD"))
   private void lunar$addPlayerToTeam$v1_8(String var1, String var2, CallbackInfoReturnable<Boolean> var3) {
      this.lunar$invalidateNameTagsByName(var1);
   }

   @Inject(method = "removeTeam", at = @At("HEAD"))
   private void lunar$removeTeam(ScorePlayerTeam var1, CallbackInfo var2) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         for (String var4 : var1.membershipSet) {
            this.lunar$invalidateNameTagsByName(var4);
         }
      } else {
         for (Object var7 : var1.membershipSet$v1_7) {
            if (var7 instanceof String var5) {
               this.lunar$invalidateNameTagsByName(var5);
            }
         }
      }
   }

   @Unique
   private void lunar$invalidateNameTagsByName(String var1) {
      WorldClient var2 = Minecraft.getMinecraft().theWorld;
      if (var2 != null) {
         for (EntityPlayer var5 : ThreadModuleDump63.MC_VERSION >= 1 ? var2.playerEntities : var2.playerEntities$v1_7) {
            if (var1.equals(var5.getGameProfile().getName()) && var5 instanceof ThreadModuleDump88 var6) {
               var6.lunar$onNameTagUpdate();
            }
         }
      }
   }
}
