package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardObjectiveBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreBridge;
import net.kyori.adventure.text.Component;
import net.minecraft.scoreboard.Score;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Score.class)
public abstract class ScoreMixin implements ScoreBridge {
   public ScoreMixin() {
   }

   @Shadow
   public abstract String getPlayerName();

   @Shadow
   public abstract int getScorePoints();

   @Shadow
   public abstract void setScorePoints(int number1);

   public String bridge$getPlayerName() {
      return this.getPlayerName();
   }

   public Bridge2_42 bridge$getPlayerComponentBridge() {
      return (Bridge2_42)(new ChatComponentText(this.getPlayerName()));
   }

   public Component bridge$getPlayerComponent() {
      return Component.text(this.getPlayerName());
   }

   public Bridge2_42 bridge$getScoreComponentBridge(ScoreboardObjectiveBridge lighting1) {
      ChatComponentText text2 = new ChatComponentText(String.valueOf(this.bridge$getScorePoints()));
      text2.style = new ChatStyle();
      text2.style.color = EnumChatFormatting.redColor;
      return (Bridge2_42)text2;
   }

   public int bridge$getScorePoints() {
      return this.getScorePoints();
   }

   public void bridge$setScorePoints(int number1) {
      this.setScorePoints(number1);
   }
}
