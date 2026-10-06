package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;

public class FieldSetEmitter implements BytecodeEmitter {
   private final MixinHelper3 field1;
   private final FieldNode field2;
   private final MixinHelper3 field3;
   private final BytecodeEmitter field4;
   private final BytecodeEmitter field5;

   public FieldSetEmitter(MixinHelper3 mixinhelper31, FieldNode field2_, MixinHelper3 mixinhelper33, BytecodeEmitter bridge_154, BytecodeEmitter bridge_155) {
      this.field1 = mixinhelper31;
      this.field2 = field2_;
      this.field3 = mixinhelper33;
      this.field4 = bridge_154;
      this.field5 = bridge_155;
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      boolean flag5 = AsmUtils.isStatic(this.field2.access);
      if (!flag5) {
         bridge_671.method2(this.field4);
         bridge_671.method6().method1((MixinHelper3)stack3.pop(), this.field1, instructions4);
      }

      bridge_671.method2(this.field5);
      bridge_671.method6().method1((MixinHelper3)stack3.pop(), this.field3, instructions4);
      instructions4.add(new FieldInsnNode(flag5 ? 179 : 181, ((ClassTypeSignature)this.field1.method2()).name(), this.field2.name, this.field2.desc));
   }

   public MixinHelper3 method2() {
      return this.field1;
   }

   public FieldNode method3() {
      return this.field2;
   }

   public MixinHelper3 method4() {
      return this.field3;
   }

   public BytecodeEmitter method5() {
      return this.field4;
   }

   public BytecodeEmitter method6() {
      return this.field5;
   }
}
