package com.moonsworth.lunar.client.cosmetics.molang;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class MolangClassBuilder {
   private final List<Consumer<MethodVisitor>> field1 = new ArrayList<>();
   private final List<Type> field2 = new ArrayList<>();
   private final List<Object> field3 = new ArrayList<>();
   private int field4 = 0;

   public MolangClassBuilder() {
   }

   public String method1(Object object) {
      for (int index2 = 0; index2 < this.field2.size(); index2++) {
         if (this.field3.get(index2) == object) {
            return "_" + index2;
         }
      }

      this.field2.add(Type.getType(object.getClass()));
      this.field3.add(object);
      return "_" + this.field4++;
   }

   public void method2(Consumer<MethodVisitor> consumer1) {
      this.field1.add(consumer1);
   }

   @Generated
   public List<Consumer<MethodVisitor>> method3() {
      return this.field1;
   }

   @Generated
   public List<Type> method4() {
      return this.field2;
   }

   @Generated
   public List<Object> method5() {
      return this.field3;
   }
}
