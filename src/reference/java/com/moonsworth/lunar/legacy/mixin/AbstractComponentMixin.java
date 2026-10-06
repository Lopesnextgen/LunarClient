package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge2_13;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.Bridge_36;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import net.kyori.adventure.text.AbstractComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.KeybindComponent;
import net.kyori.adventure.text.ScoreComponent;
import net.kyori.adventure.text.SelectorComponent;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.TranslationArgument;
import net.minecraft.util.ChatComponentScore;
import net.minecraft.util.ChatComponentSelector;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.text.TextComponentKeybind;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractComponent.class)
public class AbstractComponentMixin implements Bridge2_13 {
   @Annotation2(min = 1)
   @Unique
   private IChatComponent bridge$vanillaComponent$v1_8;
   @Annotation2(max = 0)
   @Unique
   private IChatComponent bridge$vanillaComponent$v1_7;

   @Override
   public Bridge2_42 moonBridge$asBridgeComponent() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         return this.moonBridge$asBridgeComponent$v1_12();
      } else {
         return ThreadModuleDump63.MC_VERSION >= 1 ? this.moonBridge$asBridgeComponent$v1_8() : this.moonBridge$asBridgeComponent$v1_7();
      }
   }

   @Unique
   @Annotation2(min = 5)
   private Bridge2_42 moonBridge$asBridgeComponent$v1_12() {
      if (this.bridge$vanillaComponent$v1_8 == null) {
         if (this instanceof TextComponent var1) {
            this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentText(var1.content()));
         } else if (!(this instanceof TranslatableComponent var2)) {
            if (this instanceof KeybindComponent var3) {
               this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new TextComponentKeybind(var3.keybind()));
            } else if (this instanceof ScoreComponent var4) {
               this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentScore(var4.name(), var4.objective()));
            } else if (this instanceof SelectorComponent var5) {
               this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentSelector(var5.pattern()));
            }
         } else {
            ArrayList var14 = new ArrayList(var2.arguments().size());

            for (TranslationArgument var8 : var2.arguments()) {
               var14.add((IChatComponent)((Bridge2_13)var8.asComponent()).moonBridge$asBridgeComponent());
            }

            this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentTranslation(var2.key(), var14.toArray(new Object[0])));
         }

         for (Component var11 : ((Component)this).children()) {
            Bridge2_42 var12 = ((Bridge2_13)var11).moonBridge$asBridgeComponent();
            this.bridge$vanillaComponent$v1_8.appendSibling((IChatComponent)var12);
         }

         ChatStyle var10 = (ChatStyle)((Bridge_36)((Component)this).style()).moonBridge$asBridgeStyle();
         this.bridge$vanillaComponent$v1_8.setStyle$v1_12(var10);
      }

      return (Bridge2_42)this.bridge$vanillaComponent$v1_8.createCopy();
   }

   @Unique
   @Annotation2(min = 1, max = 1)
   private Bridge2_42 moonBridge$asBridgeComponent$v1_8() {
      if (this.bridge$vanillaComponent$v1_8 == null) {
         if (this instanceof TextComponent var1) {
            this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentText(var1.content()));
         } else if (!(this instanceof TranslatableComponent var2)) {
            if (this instanceof ScoreComponent var3) {
               this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentScore(var3.name(), var3.objective()));
            } else if (this instanceof SelectorComponent var4) {
               this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentSelector(var4.pattern()));
            }
         } else {
            ArrayList var13 = new ArrayList(var2.arguments().size());

            for (TranslationArgument var7 : var2.arguments()) {
               var13.add((IChatComponent)((Bridge2_13)var7.asComponent()).moonBridge$asBridgeComponent());
            }

            this.bridge$vanillaComponent$v1_8 = (IChatComponent)(new ChatComponentTranslation(var2.key(), var13.toArray(new Object[0])));
         }

         for (Component var10 : ((Component)this).children()) {
            Bridge2_42 var11 = ((Bridge2_13)var10).moonBridge$asBridgeComponent();
            this.bridge$vanillaComponent$v1_8.appendSibling((IChatComponent)var11);
         }

         ChatStyle var9 = (ChatStyle)((Bridge_36)((Component)this).style()).moonBridge$asBridgeStyle();
         this.bridge$vanillaComponent$v1_8.setChatStyle(var9);
      }

      return (Bridge2_42)this.bridge$vanillaComponent$v1_8.createCopy();
   }

   @Unique
   @Annotation2(max = 0)
   private Bridge2_42 moonBridge$asBridgeComponent$v1_7() {
      if (this.bridge$vanillaComponent$v1_7 == null) {
         if (this instanceof TextComponent var1) {
            this.bridge$vanillaComponent$v1_7 = (IChatComponent)(new ChatComponentText(var1.content()));
         } else if (!(this instanceof TranslatableComponent var2)) {
            if (this instanceof ScoreComponent var3) {
               this.bridge$vanillaComponent$v1_7 = (IChatComponent)(new ChatComponentText(var3.name() + ":" + var3.objective()));
            } else if (this instanceof SelectorComponent var4) {
               this.bridge$vanillaComponent$v1_7 = (IChatComponent)(new ChatComponentText(var4.pattern()));
            }
         } else {
            ArrayList var13 = new ArrayList(var2.arguments().size());

            for (TranslationArgument var7 : var2.arguments()) {
               var13.add((IChatComponent)((Bridge2_13)var7.asComponent()).moonBridge$asBridgeComponent());
            }

            this.bridge$vanillaComponent$v1_7 = (IChatComponent)(new ChatComponentTranslation(var2.key(), var13.toArray(new Object[0])));
         }

         for (Component var10 : ((Component)this).children()) {
            Bridge2_42 var11 = ((Bridge2_13)var10).moonBridge$asBridgeComponent();
            this.bridge$vanillaComponent$v1_7.appendSibling((IChatComponent)var11);
         }

         ChatStyle var9 = (ChatStyle)((Bridge_36)((Component)this).style()).moonBridge$asBridgeStyle();
         this.bridge$vanillaComponent$v1_7.setChatStyle(var9);
      }

      return (Bridge2_42)this.bridge$vanillaComponent$v1_7.createCopy();
   }
}
