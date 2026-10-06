package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.lighting.Lighting;
import com.moonsworth.lunar.bridge.lighting.Lighting2;
import com.moonsworth.lunar.bridge.lighting.Lighting3;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump88;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Scoreboard.class)
public abstract class ScoreboardMixin implements Lighting4 {
   @Shadow
   public Map<String, ScorePlayerTeam> teamMemberships;
   @Shadow
   public Map teamMemberships$v1_7;

   @Shadow
   public abstract ScorePlayerTeam getPlayersTeam(String var1);

   @Shadow
   public abstract ScoreObjective getObjectiveInDisplaySlot(int var1);

   @Shadow
   public abstract Score getOrCreateScore$v1_12(String var1, ScoreObjective var2);

   @Shadow
   public abstract Score getValueFromObjective(String var1, ScoreObjective var2);

   @Shadow
   public abstract Collection<Score> getSortedScores(ScoreObjective var1);

   @Shadow
   public abstract Collection getSortedScores(ScoreObjective var1);

   @Override
   public Lighting3 bridge$getPlayersTeam(String var1) {
      return (Lighting3)this.getPlayersTeam(var1);
   }

   @Override
   public Lighting bridge$getObjectiveInDisplaySlot(int var1) {
      return (Lighting)this.getObjectiveInDisplaySlot(var1);
   }

   @Override
   public Lighting2 bridge$getValueFromObjective(String var1, Lighting var2) {
      return ThreadModuleDump63.MC_VERSION == 5
         ? (Lighting2)this.getOrCreateScore$v1_12(var1, (ScoreObjective)var2)
         : (Lighting2)this.getValueFromObjective(var1, (ScoreObjective)var2);
   }

   @Override
   public Collection<Lighting2> bridge$getSortedScores(Lighting var1) {
      Collection var2 = ThreadModuleDump63.MC_VERSION >= 1 ? this.getSortedScores((ScoreObjective)var1) : this.getSortedScores((ScoreObjective)var1);
      ArrayList var3 = new ArrayList(var2.size());

      for (Score var5 : var2) {
         if (var5 instanceof Lighting2 var6) {
            var3.add(var6);
         }
      }

      return var3;
   }

   @Inject(method = "removeTeam", at = @At("HEAD"), cancellable = true)
   public void impl$removeTeam(ScorePlayerTeam var1, CallbackInfo var2) {
      if (var1 == null) {
         var2.cancel();
      }
   }

   @Annotation2(min = 1)
   @Inject(method = "removeObjective$v1_8", at = @At("HEAD"), cancellable = true)
   public void impl$removeObjective$v1_8(ScoreObjective var1, CallbackInfo var2) {
      if (var1 == null) {
         var2.cancel();
      }
   }

   @Annotation2(max = 0)
   @Inject(method = "func_96519_k$v1_7", at = @At("HEAD"), cancellable = true)
   public void impl$removeObjective$v1_7(ScoreObjective var1, CallbackInfo var2) {
      if (var1 == null) {
         var2.cancel();
      }
   }

   @Overwrite
   public void removePlayerFromTeam(String var1, ScorePlayerTeam var2) {
      if (this.getPlayersTeam(var1) == var2) {
         if (ThreadModuleDump63.MC_VERSION >= 1) {
            this.teamMemberships.remove(var1);
            var2.getMembershipCollection().remove(var1);
         } else {
            this.teamMemberships$v1_7.remove(var1);
            var2.getMembershipCollection().remove(var1);
         }

         WorldClient var3 = Minecraft.getMinecraft().theWorld;
         if (var3 != null) {
            for (EntityPlayer var6 : ThreadModuleDump63.MC_VERSION >= 1 ? var3.playerEntities : var3.playerEntities$v1_7) {
               if (var1.equals(var6.getGameProfile().getName()) && var6 instanceof ThreadModuleDump88 var7) {
                  var7.lunar$onNameTagUpdate();
               }
            }
         }
      }
   }
}
