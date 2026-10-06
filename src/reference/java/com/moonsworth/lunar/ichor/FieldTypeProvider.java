package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.config.Config;
import java.util.Objects;
import java.util.Optional;
import java.util.jar.JarFile;
import lombok.Generated;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.bombe.provider.JarFileClassProvider;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class FieldTypeProvider implements org.cadixdev.lorenz.model.jar.FieldTypeProvider {
   private final ClassProvider field1;
   private final NestClassNameMapper field2;

   public Optional<FieldType> provide(FieldMapping fieldmapping1) {
      String text2 = ((ClassMapping)fieldmapping1.getParent()).getFullObfuscatedName();
      ClassNode node3 = this.field1.getAsNode(this.field2.unmap(text2));
      if (node3 == null) {
         return Optional.empty();
      } else {
         Optional optional4 = node3.fields.stream().filter(arg1x -> Objects.equals(arg1x.name, fieldmapping1.getObfuscatedName())).findAny();
         if (optional4.isPresent()) {
            FieldType fieldtype5 = this.field2.method2(FieldType.of(((FieldNode)optional4.get()).desc));
            return Optional.of(fieldtype5);
         } else {
            return Optional.empty();
         }
      }
   }

   public static FieldTypeProvider method1(Config config0, NestClassNameMapper fieldtypeprovider21) {
      try {
         JarFile jarfile2 = new JarFile(config0.method28().toFile());
         return new FieldTypeProvider(new JarFileClassProvider(jarfile2), fieldtypeprovider21);
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   @Generated
   public FieldTypeProvider(ClassProvider classprovider1, NestClassNameMapper fieldtypeprovider22) {
      this.field1 = classprovider1;
      this.field2 = fieldtypeprovider22;
   }
}
