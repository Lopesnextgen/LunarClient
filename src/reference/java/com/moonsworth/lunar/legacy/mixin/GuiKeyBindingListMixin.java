package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiKeyBindingList.CategoryEntry;
import net.minecraft.client.gui.GuiKeyBindingList.KeyEntry;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GuiKeyBindingList.class)
public class GuiKeyBindingListMixin {
   @Final
   @Shadow
   public IGuiListEntry[] listEntries;
   @Unique
   private List<String> lunar$cachedDisabledMods;
   @Unique
   private IGuiListEntry[] lunar$cachedListEntries;

   public GuiKeyBindingListMixin() {
   }

   @Overwrite
   public IGuiListEntry getListEntry(int index1) {
      return this.lunar$getCachedKeyBindingList()[index1];
   }

   @Overwrite
   public int getSize() {
      return this.lunar$getCachedKeyBindingList().length;
   }

   @Unique
   private IGuiListEntry[] lunar$getCachedKeyBindingList() {
      List list1 = Ref.method4().method40().method10();
      if (!list1.equals(this.lunar$cachedDisabledMods)) {
         this.lunar$cachedDisabledMods = list1;
         IGuiListEntry[] items2 = Arrays.copyOf(this.listEntries, this.listEntries.length);
         Collections.reverse(Arrays.asList(items2));
         MutableBoolean mutableboolean3 = new MutableBoolean(false);
         this.lunar$cachedListEntries = Arrays.stream(items2).filter(arg2x -> {
            if (arg2x == null) {
               return false;
            }

            if (arg2x instanceof KeyEntry keyentry3x) {
               KeyBindingBridge mixinhelper_154 = Ref.MC_VERSION >= 1 ? (KeyBindingBridge)keyentry3x.keybinding : (KeyBindingBridge)keyentry3x.field_148282_b$v1_7;
               SimpleKeybindOption lightingextension491325 = (SimpleKeybindOption)SimpleKeybindOption.keybindRegistry.inverse().get(mixinhelper_154);
               if (lightingextension491325 != null && lightingextension491325.isHidden()) {
                  return false;
               }

               String text6 = mixinhelper_154.bridge$getCategory();
               if (list1.contains(text6)) {
                  return false;
               }

               mutableboolean3.setValue(true);
            } else if (arg2x instanceof CategoryEntry) {
               if (!mutableboolean3.getValue()) {
                  return false;
               }

               mutableboolean3.setValue(false);
            }

            return true;
         }).toArray(IGuiListEntry[]::new);
         Collections.reverse(Arrays.asList(this.lunar$cachedListEntries));
      }

      return this.lunar$cachedListEntries;
   }
}
