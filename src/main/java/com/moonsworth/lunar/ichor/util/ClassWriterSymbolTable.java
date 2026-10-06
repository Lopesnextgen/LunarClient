package com.moonsworth.lunar.ichor.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class ClassWriterSymbolTable {
   private final List<ClassWriterSymbolTable.Data> field1;

   public ClassWriterSymbolTable(org.objectweb.asm.ClassWriter classwriter1) {
      try {
         Field field2_ = org.objectweb.asm.ClassWriter.class.getDeclaredField("symbolTable");
         field2_.setAccessible(true);
         Object obj3 = field2_.get(classwriter1);
         Field field4 = obj3.getClass().getDeclaredField("entries");
         field4.setAccessible(true);
         Object[] items5 = (Object[])field4.get(obj3);
         this.field1 = new ArrayList<>(items5.length);

         for (Object obj9 : items5) {
            if (obj9 != null) {
               this.field1.add(new ClassWriterSymbolTable.Data(obj9));
            }
         }
      } catch (ReflectiveOperationException reflectiveoperationexception10) {
         throw new RuntimeException(reflectiveoperationexception10);
      }
   }

   public List<ClassWriterSymbolTable.Data> method1() {
      return this.field1;
   }

   public static final class Data {
      private final Object field1;
      private int index;
      private int tag;
      private String owner;
      private String name;
      private String value;
      private long field2;

      public Data(Object object) {
         this.field1 = object;

         try {
            Class clazz2 = object.getClass().getSuperclass();

            for (Field field6 : ClassWriterSymbolTable.Data.class.getDeclaredFields()) {
               if (!field6.getName().equals("asmEntry")) {
                  Field field7 = clazz2.getDeclaredField(field6.getName());
                  field7.setAccessible(true);
                  field6.setAccessible(true);
                  field6.set(this, field7.get(object));
               }
            }
         } catch (ReflectiveOperationException reflectiveoperationexception8) {
            throw new RuntimeException(reflectiveoperationexception8);
         }
      }

      public int getIndex() {
         return this.index;
      }

      public int getTag() {
         return this.tag;
      }

      public String getOwner() {
         return this.owner;
      }

      public String getName() {
         return this.name;
      }

      public String getValue() {
         return this.value;
      }

      public long method1() {
         return this.field2;
      }

      @Override
      public String toString() {
         return "EntryWrapper{index="
            + this.index
            + ", tag="
            + this.tag
            + ", owner='"
            + this.owner
            + "', name='"
            + this.name
            + "', value='"
            + this.value
            + "', data="
            + this.field2
            + "}";
      }
   }
}
