package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ChatStyleBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.legacy.wrapper.AdventureStyleConverter;
import net.kyori.adventure.text.event.ClickEvent.Action;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.Style.Builder;
import net.kyori.adventure.text.format.TextDecoration.State;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatStyle.class)
public abstract class ChatStyleMixin implements ChatStyleBridge {
   @Unique
   private Style bridge$adventure;

   public ChatStyleMixin() {
   }

   public Style moonBridge$asAdventureStyle() {
      if (this.bridge$adventure == null) {
         Builder builder1 = Style.style();
         ChatStyle style2 = (ChatStyle)this;
         NamedTextColor namedtextcolor3 = AdventureStyleConverter.method3(style2.getColor());
         builder1.color(namedtextcolor3);
         builder1.decoration(TextDecoration.OBFUSCATED, State.byBoolean(style2.obfuscated));
         builder1.decoration(TextDecoration.BOLD, State.byBoolean(style2.bold));
         builder1.decoration(TextDecoration.STRIKETHROUGH, State.byBoolean(style2.strikethrough));
         builder1.decoration(TextDecoration.UNDERLINED, State.byBoolean(style2.underlined));
         builder1.decoration(TextDecoration.ITALIC, State.byBoolean(style2.italic));
         HoverEvent hoverevent4 = Ref.MC_VERSION >= 5 ? style2.getHoverEvent() : style2.getChatHoverEvent();
         if (hoverevent4 != null) {
            builder1.hoverEvent(AdventureStyleConverter.method7(hoverevent4));
         }

         ClickEvent clickevent5 = Ref.MC_VERSION >= 5 ? style2.getClickEvent$v1_12() : style2.getChatClickEvent();
         if (clickevent5 != null) {
            Action action6 = AdventureStyleConverter.method6(clickevent5.getAction());
            if (action6 != null) {
               builder1.clickEvent(net.kyori.adventure.text.event.ClickEvent.clickEvent(action6, clickevent5.getValue()));
            }
         }

         if (Ref.MC_VERSION >= 1) {
            builder1.insertion(style2.getInsertion());
         }

         this.bridge$adventure = builder1.build();
      }

      return this.bridge$adventure;
   }
}
