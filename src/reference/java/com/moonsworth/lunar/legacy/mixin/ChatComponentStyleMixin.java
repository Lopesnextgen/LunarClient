package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.Bridge3_25;
import com.moonsworth.lunar.bridge.Bridge4_20;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.minecraft.util.ChatComponentScore;
import net.minecraft.util.ChatComponentSelector;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.text.TextComponentKeybind;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatComponentStyle.class)
public class ChatComponentStyleMixin implements Bridge3_25 {
   @Unique
   private Component bridge$adventureComponent;

   @Override
   public Component moonBridge$asAdventureComponent() {
      if (this.bridge$adventureComponent == null) {
         ChatComponentStyle var2 = (ChatComponentStyle)this;
         Style var3 = null;
         Object var1;
         if (var2 instanceof ChatComponentText var5) {
            String var9 = ThreadModuleDump63.MC_VERSION >= 5 ? var5.getText$v1_12() : var5.text;
            TextComponent var10 = AdventureTextBridge.asAdventure(var9);
            var3 = var10.style();
            var1 = var10.toBuilder();
         } else if (var2 instanceof ChatComponentTranslation var6) {
            ArrayList var20 = new ArrayList(var6.formatArgs.length);

            for (Object var13 : var6.formatArgs) {
               if ((ThreadModuleDump63.MC_VERSION < 1 || !(var13 instanceof IChatComponent))
                  && (ThreadModuleDump63.MC_VERSION > 0 || !(var13 instanceof IChatComponent))) {
                  var20.add(Component.text(var13.toString()));
               } else {
                  var20.add(AdventureTextBridge.asAdventure((Bridge2_42)var13));
               }
            }

            var1 = Component.translatable().key(var6.getKey()).args(var20.toArray(new Component[0]));
         } else if (ThreadModuleDump63.MC_VERSION == 5 && var2 instanceof TextComponentKeybind var7) {
            var1 = Component.keybind().keybind(var7.keybind);
         } else if (ThreadModuleDump63.MC_VERSION >= 1 && var2 instanceof ChatComponentScore var8) {
            var1 = Component.score().name(var8.getName()).objective(var8.getObjective());
         } else {
            if (ThreadModuleDump63.MC_VERSION < 1 || !(var2 instanceof ChatComponentSelector var4)) {
               throw new UnsupportedOperationException("Don't know how to convert " + this.getClass().getName() + " to adventure");
            }

            var1 = Component.selector().pattern(var4.selector);
         }

         if (ThreadModuleDump63.MC_VERSION >= 1) {
            for (IChatComponent var18 : var2.getSiblings()) {
               var1.append(AdventureTextBridge.asAdventure((Bridge2_42)var18));
            }
         } else {
            for (IChatComponent var19 : var2.getSiblings()) {
               var1.append(AdventureTextBridge.asAdventure((Bridge2_42)var19));
            }
         }

         Style var16 = ((Bridge4_20)(ThreadModuleDump63.MC_VERSION >= 5 ? var2.getStyle$v1_12() : var2.getChatStyle())).moonBridge$asAdventureStyle();
         var1.style(var3 != null ? var16.merge(var3) : var16);
         this.bridge$adventureComponent = var1.build();
      }

      return this.bridge$adventureComponent;
   }
}
