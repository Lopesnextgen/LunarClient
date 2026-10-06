package com.moonsworth.lunar.files;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import org.cadixdev.lorenz.io.MappingFormat;
import org.cadixdev.lorenz.io.MappingsReader;
import org.cadixdev.lorenz.io.MappingsWriter;

public class Files5_2 {
   public static MappingFormat field1 = new MappingFormat() {
      public MappingsReader createReader(InputStream var1) {
         return new BinaryMappingsReader(var1);
      }

      public MappingsWriter createWriter(OutputStream var1) {
         return new BinaryMappingsWriter(var1);
      }

      public Optional<String> getStandardFileExtension() {
         return Optional.of("kin");
      }
   };
}
