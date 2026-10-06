package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PacketBuffer.class)
public abstract class PacketBufferMixin extends ByteBuf implements Bridge7_9 {
   public PacketBufferMixin() {
   }

   @Shadow
   public abstract ByteBuf writeInt(int number1);

   @Shadow
   public abstract ByteBuf writeLong(long number1);

   @Shadow
   public abstract long readLong();

   @Shadow
   public abstract boolean readBoolean();

   @Shadow
   public abstract ByteBuf writeShort(int number1);

   @Shadow
   public abstract short readShort();

   @Shadow
   public abstract ByteBuf writeBytes(byte[] items1);

   @Shadow
   public abstract ByteBuf readBytes(int number1);

   @Shadow
   public abstract float readFloat();

   @Shadow
   public abstract ByteBuf writeBoolean(boolean flag1);

   public ByteBuf bridge$writeInt(int number1) {
      return this.writeInt(number1);
   }

   public int bridge$readInt() {
      return this.readInt();
   }

   public void bridge$writeString(String text1) {
      if (Ref.MC_VERSION >= 1) {
         this.writeString(text1);
      } else {
         this.writeStringToBuffer$v1_7(text1);
      }
   }

   public String bridge$readStringFromBuffer(int number1) {
      return Ref.MC_VERSION == 5 ? this.readString$v1_12(number1) : this.readStringFromBuffer(number1);
   }

   public ByteBuf bridge$writeLong(long number1) {
      return this.writeLong(number1);
   }

   public long bridge$readLong() {
      return this.readLong();
   }

   public boolean bridge$readBoolean() {
      return this.readBoolean();
   }

   public void bridge$writeVarIntToBuffer(int number1) {
      if (Ref.MC_VERSION == 5) {
         this.writeVarInt$v1_12(number1);
      } else {
         this.writeVarIntToBuffer(number1);
      }
   }

   public int bridge$readVarIntFromBuffer() {
      return Ref.MC_VERSION == 5 ? this.readVarInt$v1_12() : this.readVarIntFromBuffer();
   }

   public ByteBuf bridge$writeShort(int number1) {
      return this.writeShort(number1);
   }

   public short bridge$readShort() {
      return this.readShort();
   }

   public ByteBuf bridge$writeBytes(byte[] items1) {
      return this.writeBytes(items1);
   }

   public ByteBuf bridge$readBytes(byte[] items1) {
      return this.readBytes(items1);
   }

   public float bridge$readFloat() {
      return this.readFloat();
   }

   public ByteBuf bridge$writeBoolean(boolean flag1) {
      return this.writeBoolean(flag1);
   }

   @Shadow
   public abstract int readableBytes();

   public int bridge$readableBytes() {
      return this.readableBytes();
   }

   @Shadow
   public abstract boolean release();

   @Shadow
   public abstract PacketBuffer writeString(String text1);

   @Shadow
   public abstract void writeStringToBuffer$v1_7(String text1);

   @Shadow
   public abstract String readString$v1_12(int number1);

   @Shadow
   public abstract String readStringFromBuffer(int number1);

   @Shadow
   public abstract PacketBuffer writeVarInt$v1_12(int number1);

   @Shadow
   public abstract void writeVarIntToBuffer(int number1);

   @Shadow
   public abstract int readVarInt$v1_12();

   @Shadow
   public abstract int readVarIntFromBuffer();

   public boolean bridge$release() {
      return this.release();
   }

   public ByteBuf bridge$writeFloat(float value) {
      return this.writeFloat(value);
   }
}
