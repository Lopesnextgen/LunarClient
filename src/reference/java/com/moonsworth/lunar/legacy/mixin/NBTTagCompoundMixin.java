package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.NBTTagListBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NBTTagCompound.class)
public abstract class NBTTagCompoundMixin implements CompoundTagBridge {
   @Shadow
   public Map<String, NBTBase> tagMap;

   public NBTTagCompoundMixin() {
   }

   @Shadow
   public abstract long getLong(String text1);

   @Shadow
   public abstract byte getByte(String text1);

   @Shadow
   public abstract int getInteger(String text1);

   @Shadow
   public abstract String getString(String text1);

   @Shadow
   public abstract float getFloat(String text1);

   @Shadow
   public abstract double getDouble(String text1);

   @Shadow
   public abstract short getShort(String text1);

   @Shadow
   public abstract boolean getBoolean(String text1);

   @Shadow
   public abstract NBTTagCompound getCompoundTag(String text1);

   @Shadow
   public abstract void setString(String text1, String text2);

   @Shadow
   public abstract boolean hasKey(String text1, int number2);

   @Shadow
   public abstract boolean isEmpty();

   @Shadow
   public abstract NBTTagList getTagList(String text1, int number2);

   @Shadow
   public abstract Set<String> getKeySet();

   @Shadow
   public abstract void setTag(String text1, NBTBase nbt2);

   @Shadow
   public abstract byte[] getByteArray(String text1);

   public long bridge$getLong(String text1) {
      return this.getLong(text1);
   }

   public byte bridge$getByte(String text1) {
      return this.getByte(text1);
   }

   public byte[] bridge$getByteArray(String text1) {
      return this.getByteArray(text1);
   }

   public int bridge$getInteger(String text1) {
      return this.getInteger(text1);
   }

   public String bridge$getString(String text1) {
      return this.getString(text1);
   }

   public float bridge$getFloat(String text1) {
      return this.getFloat(text1);
   }

   public double bridge$getDouble(String text1) {
      return this.getDouble(text1);
   }

   public short bridge$getShort(String text1) {
      return this.getShort(text1);
   }

   public boolean bridge$getBoolean(String text1) {
      return this.getBoolean(text1);
   }

   public NBTTagListBridge bridge$getList(String text1, int number2) {
      return (NBTTagListBridge)this.getTagList(text1, number2);
   }

   public CompoundTagBridge bridge$getCompoundTag(String text1) {
      return (CompoundTagBridge)this.getCompoundTag(text1);
   }

   public void bridge$putString(@NotNull String text1, String text2) {
      this.setString(text1, text2);
   }

   public void bridge$putCompound(String text1, CompoundTagBridge bridge_572) {
      this.setTag(text1, (NBTBase)bridge_572);
   }

   public Set<String> bridge$getAllKeys() {
      return this.getKeySet();
   }

   public boolean bridge$contains(String text1, int number2) {
      return this.hasKey(text1, number2);
   }

   public boolean bridge$isEmpty() {
      return Ref.MC_VERSION == 5 ? this.isEmpty() : this.tagMap.isEmpty();
   }
}
