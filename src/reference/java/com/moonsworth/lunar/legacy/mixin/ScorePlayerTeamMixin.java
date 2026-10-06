package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.bridge.scoreboard.ScorePlayerTeamBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScorePlayerTeam.class)
public abstract class ScorePlayerTeamMixin implements ScorePlayerTeamBridge {
   @Shadow
   public String prefix;
   @Shadow
   public String suffix;
   @Shadow
   public EnumChatFormatting color$v1_8;

   public ScorePlayerTeamMixin() {
   }

   @Shadow
   public abstract String formatString(String text1);

   public ChatFormatting bridge$getChatFormat() {
      return Ref.MC_VERSION >= 1 ? ChatFormatting.valueOf(this.color$v1_8.name()) : ChatFormatting.WHITE;
   }

   public Component bridge$formatString(Component component1) {
      return Component.text(this.formatString(TextBridge.asLegacyString(component1)));
   }

   public Bridge2_42 bridge$formatString(Bridge2_42 bridge2_421) {
      return bridge2_421 instanceof ChatComponentText text2
         ? (Bridge2_42)(new ChatComponentText(this.formatString(text2.text)))
         : (Bridge2_42)(new ChatComponentText(this.formatString(TextBridge.asLegacyString(TextBridge.asAdventure(bridge2_421)))));
   }

   public Component bridge$getPrefixAndSuffix() {
      return TextBridge.asAdventure(this.prefix + this.suffix);
   }
}
