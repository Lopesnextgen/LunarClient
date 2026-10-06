package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.forge.lib.mixin.Bootstrap2;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.Adler32;

public class ClassPatch {
   public String sourceClassName;
   public String field1;
   public boolean field2;
   public int field3;
   public byte[] field4;

   public ClassPatch() {
   }

   public ClassPatch method1(InputStream input1) {
      try {
         DataInputStream input2 = new DataInputStream(input1);
         input2.readUTF();
         this.sourceClassName = input2.readUTF().replace('.', '/');
         this.field1 = input2.readUTF().replace('.', '/');
         this.field2 = input2.readBoolean();
         this.field3 = this.field2 ? input2.readInt() : 1;
         int index3 = input2.readInt();
         this.field4 = new byte[index3];
         input2.readFully(this.field4);
         return this;
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   public byte[] method2(byte[] items1) {
      try {
         if (Ichor6Impl.field4) {
            Adler32 adler322 = new Adler32();
            adler322.update(items1);
            int number3 = (int)adler322.getValue();
            if (number3 != this.field3) {
               System.err.println("WRONG CHECKSUM: " + this);
               System.err.println("  Expected checksum " + this.field3 + " but got " + number3);
            } else {
               System.err.println("CORRECT CHECKSUM: " + this);
            }
         }

         return new Bootstrap2().patch(items1, this.field4);
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   @Override
   public String toString() {
      return "Binpatch " + this.sourceClassName + " -> " + this.field1 + " (" + this.field4.length + " bytes)";
   }
}
