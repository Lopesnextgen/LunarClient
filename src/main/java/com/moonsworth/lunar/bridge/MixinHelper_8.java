package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

public class MixinHelper_8 implements Consumer<String> {
   private final MixinHelper2_11 field1;
   private boolean field2 = false;
   private Style style = Style.empty();
   private int position = 0;

   public void accept(String var1) {
      var1.codePoints().forEach(this::accept);
   }

   public void accept(int var1) {
      if (this.field2) {
         this.field2 = false;
         if (Character.isBmpCodePoint(var1)) {
            AdventureChatFormatting var2 = AdventureChatFormatting.getByCode((char)var1);
            if (var2 != null) {
               this.style = switch (var2) {
                  case OBFUSCATED -> this.style.decorate(TextDecoration.OBFUSCATED);
                  case BOLD -> this.style.decorate(TextDecoration.BOLD);
                  case STRIKETHROUGH -> this.style.decorate(TextDecoration.STRIKETHROUGH);
                  case UNDERLINE -> this.style.decorate(TextDecoration.UNDERLINED);
                  case ITALIC -> this.style.decorate(TextDecoration.ITALIC);
                  case RESET -> Style.empty();
                  default -> Style.empty().color(var2.getAdventureColor());
               };
            }
         }
      } else if (var1 == 167) {
         this.field2 = true;
      } else {
         this.field1.method1(this.position, this.style, var1);
         this.position++;
      }
   }

   @Generated
   public MixinHelper_8(MixinHelper2_11 var1) {
      this.field1 = var1;
   }
}
