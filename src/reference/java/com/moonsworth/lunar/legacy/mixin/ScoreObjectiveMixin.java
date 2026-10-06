package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScoreObjective.class)
public abstract class ScoreObjectiveMixin implements ScoreboardObjectiveBridge {
   public ScoreObjectiveMixin() {
   }

   @Shadow
   public abstract Scoreboard getScoreboard();

   @Shadow
   public abstract String getDisplayName();

   @Shadow
   public abstract void setDisplayName(String text1);

   public ScoreboardBridge bridge$getScoreboard() {
      return (ScoreboardBridge)this.getScoreboard();
   }

   public Bridge2_42 bridge$getDisplayName() {
      return (Bridge2_42)(new ChatComponentText(this.getDisplayName()));
   }

   public void bridge$setDisplayName(String text1) {
      this.setDisplayName(text1);
   }
}
