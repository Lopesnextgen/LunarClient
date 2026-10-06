package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.VanillaStyleBridge;
import com.moonsworth.lunar.bridge.StyleBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.legacy.wrapper.AdventureStyleConverter;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.util.ChatStyle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(targets = "net.kyori.adventure.text.format.StyleImpl")
public abstract class StyleImplMixin implements StyleBridge {
   @Unique
   private ChatStyle bridge$vanilla;

   public StyleImplMixin() {
   }

   public VanillaStyleBridge moonBridge$asBridgeStyle() {
      if (this == Style.empty()) {
         return (VanillaStyleBridge)(new ChatStyle());
      }

      if (this.bridge$vanilla == null) {
         Style style1 = (Style)this;
         this.bridge$vanilla = new ChatStyle()
            .setBold(TextBridge.asBoolean(style1.decoration(TextDecoration.BOLD)))
            .setItalic(TextBridge.asBoolean(style1.decoration(TextDecoration.ITALIC)))
            .setUnderlined(TextBridge.asBoolean(style1.decoration(TextDecoration.UNDERLINED)))
            .setStrikethrough(TextBridge.asBoolean(style1.decoration(TextDecoration.STRIKETHROUGH)))
            .setObfuscated(TextBridge.asBoolean(style1.decoration(TextDecoration.OBFUSCATED)))
            .setColor(AdventureStyleConverter.method1(style1.color()));
         if (Ref.MC_VERSION >= 5) {
            this.bridge$vanilla.setClickEvent$v1_12(AdventureStyleConverter.method4(style1.clickEvent())).setHoverEvent$v1_12(AdventureStyleConverter.method9(style1.hoverEvent()));
         } else {
            this.bridge$vanilla.setChatClickEvent(AdventureStyleConverter.method4(style1.clickEvent())).setChatHoverEvent(AdventureStyleConverter.method9(style1.hoverEvent()));
         }

         if (Ref.MC_VERSION >= 1) {
            this.bridge$vanilla.setInsertion(style1.insertion());
         }
      }

      return (VanillaStyleBridge)this.bridge$vanilla;
   }
}
