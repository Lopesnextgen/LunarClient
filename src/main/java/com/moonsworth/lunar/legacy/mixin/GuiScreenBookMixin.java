package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiScreenBookBridge;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiScreenBook.class)
public abstract class GuiScreenBookMixin implements GuiScreenBookBridge {
   @Shadow
   public int currPage;
   @Shadow
   public int bookTotalPages;
   @Shadow
   public NBTTagList bookPages;

   public GuiScreenBookMixin() {
   }

   public String bridge$getPageContents(int value) {
      return this.bookPages == null ? "" : this.bookPages.getStringTagAt(value);
   }

   public int bridge$getCurrentPage() {
      return this.currPage;
   }

   public int bridge$getPageCount() {
      return this.bookTotalPages;
   }
}
