package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;

public class FieldWriteEmitter implements BytecodeEmitter {
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper3 field1;
   private final FieldNode field2;
   private final com.moonsworth.lunar.ichor.mixin.MixinHelper3 field3;
   private final BytecodeEmitter field4;
   private final BytecodeEmitter field5;

   public FieldWriteEmitter(
      com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1, FieldNode var2, com.moonsworth.lunar.ichor.mixin.MixinHelper3 var3, BytecodeEmitter var4, BytecodeEmitter var5
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
   }

   @Override
   public void method1(BridgeMethodGenerator var1, Queue<MethodParameter> var2, Stack<com.moonsworth.lunar.ichor.mixin.MixinHelper3> var3, InsnList var4) {
      boolean var5 = FatalIchorError6.isStatic(this.field2.access);
      if (!var5) {
         var1.method2(this.field4);
         var1.method6().method1((com.moonsworth.lunar.ichor.mixin.MixinHelper3)var3.pop(), this.field1, var4);
      }

      var1.method2(this.field5);
      var1.method6().method1((com.moonsworth.lunar.ichor.mixin.MixinHelper3)var3.pop(), this.field3, var4);
      var4.add(
         new FieldInsnNode(var5 ? 179 : 181, ((com.moonsworth.lunar.ichor.mixin.MixinHelper7)this.field1.method2()).name(), this.field2.name, this.field2.desc)
      );
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method2() {
      return this.field1;
   }

   public FieldNode method3() {
      return this.field2;
   }

   public com.moonsworth.lunar.ichor.mixin.MixinHelper3 method4() {
      return this.field3;
   }

   public BytecodeEmitter method5() {
      return this.field4;
   }

   public BytecodeEmitter method6() {
      return this.field5;
   }
}
