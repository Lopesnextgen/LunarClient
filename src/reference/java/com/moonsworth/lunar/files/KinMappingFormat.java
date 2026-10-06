package com.moonsworth.lunar.files;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import org.cadixdev.lorenz.io.MappingFormat;
import org.cadixdev.lorenz.io.MappingsReader;
import org.cadixdev.lorenz.io.MappingsWriter;

class KinMappingFormat implements MappingFormat {
   KinMappingFormat() {
   }

   public MappingsReader createReader(InputStream stream) {
      return new BinaryMappingsReader(stream);
   }

   public MappingsWriter createWriter(OutputStream stream) {
      return new BinaryMappingsWriter(stream);
   }

   public Optional<String> getStandardFileExtension() {
      return Optional.of("kin");
   }
}
