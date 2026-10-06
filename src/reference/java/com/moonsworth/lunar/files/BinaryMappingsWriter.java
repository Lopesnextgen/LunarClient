package com.moonsworth.lunar.files;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.InnerClassMapping;
import org.cadixdev.lorenz.model.Mapping;
import org.cadixdev.lorenz.model.MethodMapping;
import org.cadixdev.lorenz.model.TopLevelClassMapping;

public class BinaryMappingsWriter extends org.cadixdev.lorenz.io.BinaryMappingsWriter {
   public BinaryMappingsWriter(OutputStream output1) {
      super(output1);
   }

   public void write(MappingSet mappingset1) {
      this.stream.writeInt(99151942);
      this.stream.writeByte(1);
      this.stream.writeInt(0);
      List list2 = getSortedAndFilteredList(mappingset1.getTopLevelClassMappings(), this.getConfig().getClassMappingComparator(), ClassMapping::hasMappings);
      this.stream.writeInt(list2.size());

      for (TopLevelClassMapping toplevelclassmapping4 : list2) {
         this.writeClass(toplevelclassmapping4);
      }

      this.stream.flush();
   }

   private void writeClass(ClassMapping<?, ?> classmapping1) {
      this.stream.writeUTF(classmapping1.getObfuscatedName());
      this.stream.writeUTF(classmapping1.getDeobfuscatedName());
      List list2 = getSortedAndFilteredList(classmapping1.getInnerClassMappings(), this.getConfig().getClassMappingComparator(), ClassMapping::hasMappings);
      this.stream.writeInt(list2.size());

      for (InnerClassMapping innerclassmapping4 : list2) {
         this.writeClass(innerclassmapping4);
      }

      List list7 = getSortedAndFilteredList(
         classmapping1.getFieldMappings(), this.getConfig().getFieldMappingComparator(), arg0 -> arg0.hasDeobfuscatedName() && arg0.getType().isPresent()
      );
      this.stream.writeInt(list7.size());

      for (FieldMapping fieldmapping5 : list7) {
         FieldType fieldtype6 = (FieldType)fieldmapping5.getType().orElseThrow();
         this.stream.writeUTF(fieldmapping5.getObfuscatedName());
         this.stream.writeUTF(fieldtype6.toString());
         this.stream.writeUTF(fieldmapping5.getDeobfuscatedName());
      }

      List list9 = getSortedAndFilteredList(classmapping1.getMethodMappings(), this.getConfig().getMethodMappingComparator(), Mapping::hasDeobfuscatedName);
      this.stream.writeInt(list9.size());

      for (MethodMapping methodmapping11 : list9) {
         this.stream.writeUTF(methodmapping11.getObfuscatedName());
         this.stream.writeUTF(methodmapping11.getObfuscatedDescriptor());
         this.stream.writeUTF(methodmapping11.getDeobfuscatedName());
      }
   }

   private static <T> List<T> getSortedAndFilteredList(Collection<? extends T> list0, Comparator<? super T> comparator1, Predicate<? super T> predicate2) {
      ArrayList list3 = new ArrayList(list0.size());

      for (Object obj5 : list0) {
         if (predicate2.test(obj5)) {
            list3.add(obj5);
         }
      }

      list3.sort(comparator1);
      return list3;
   }
}
