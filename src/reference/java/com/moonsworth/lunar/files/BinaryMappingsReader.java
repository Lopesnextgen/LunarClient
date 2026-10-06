package com.moonsworth.lunar.files;

import java.io.InputStream;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;

public class BinaryMappingsReader extends org.cadixdev.lorenz.io.BinaryMappingsReader {
   public BinaryMappingsReader(InputStream input1) {
      super(input1);
   }

   public MappingSet read(MappingSet mappingset1) {
      int number2 = this.stream.readInt();
      if (number2 != 99151942) {
         throw new IllegalStateException("Invalid magic marker! '" + BinaryMappingsFormat.toHexString(number2) + "'");
      }

      byte number3 = this.stream.readByte();
      if (number3 != 1) {
         throw new IllegalStateException("Invalid kin version! '" + number3 + "'");
      }

      int number4 = this.stream.readInt();

      for (int index5 = 0; index5 < number4; index5++) {
         String text6 = this.stream.readUTF();
         String text7 = this.stream.readUTF();
      }

      int number8 = this.stream.readInt();

      for (int index9 = 0; index9 < number8; index9++) {
         this.readClass(mappingset1.getOrCreateTopLevelClassMapping(this.stream.readUTF()));
      }

      return mappingset1;
   }

   private void readClass(ClassMapping classmapping1) {
      classmapping1.setDeobfuscatedName(this.stream.readUTF());
      int number2 = this.stream.readInt();

      for (int index3 = 0; index3 < number2; index3++) {
         this.readClass(classmapping1.getOrCreateInnerClassMapping(this.stream.readUTF()));
      }

      int number7 = this.stream.readInt();

      for (int index4 = 0; index4 < number7; index4++) {
         String text5 = this.stream.readUTF();
         FieldMapping fieldmapping6 = classmapping1.getOrCreateFieldMapping(text5, this.stream.readUTF());
         fieldmapping6.setDeobfuscatedName(this.stream.readUTF());
      }

      int number8 = this.stream.readInt();

      for (int index9 = 0; index9 < number8; index9++) {
         classmapping1.getOrCreateMethodMapping(this.stream.readUTF(), this.stream.readUTF()).setDeobfuscatedName(this.stream.readUTF());
      }
   }
}
