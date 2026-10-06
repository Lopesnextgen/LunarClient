package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.forge.lib.mixin.Bootstrap2;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.Adler32;

public class MixinMisc2 {
   public String sourceClassName;
   public String field1;
   public boolean field2;
   public int field3;
   public byte[] field4;

   public MixinMisc2 method1(InputStream var1) {
      try {
         DataInputStream var2 = new DataInputStream(var1);
         var2.readUTF();
         this.sourceClassName = var2.readUTF().replace('.', '/');
         this.field1 = var2.readUTF().replace('.', '/');
         this.field2 = var2.readBoolean();
         this.field3 = this.field2 ? var2.readInt() : 1;
         int var3 = var2.readInt();
         this.field4 = new byte[var3];
         var2.readFully(this.field4);
         return this;
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public byte[] method2(byte[] var1) {
      try {
         if (Ichor6Impl.field4) {
            Adler32 var2 = new Adler32();
            var2.update(var1);
            int var3 = (int)var2.getValue();
            if (var3 != this.field3) {
               System.err.println("WRONG CHECKSUM: " + this);
               System.err.println("  Expected checksum " + this.field3 + " but got " + var3);
            } else {
               System.err.println("CORRECT CHECKSUM: " + this);
            }
         }

         return new Bootstrap2().patch(var1, this.field4);
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Override
   public String toString() {
      return "Binpatch " + this.sourceClassName + " -> " + this.field1 + " (" + this.field4.length + " bytes)";
   }
}
