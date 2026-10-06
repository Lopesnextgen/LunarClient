package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NBTTagList.class)
public abstract class NBTTagListMixin implements NBTTagListBridge {
   public NBTTagListMixin() {
   }

   @Shadow
   public abstract NBTTagCompound getCompoundTagAt(int number1);

   @Shadow
   public abstract String getStringTagAt(int number1);

   @Shadow
   public abstract int tagCount();

   public CompoundTagBridge bridge$getCompoundAt(int number1) {
      return (CompoundTagBridge)this.getCompoundTagAt(number1);
   }

   public int bridge$size() {
      return this.tagCount();
   }

   public String bridge$getString(int number1) {
      return this.getStringTagAt(number1);
   }
}
