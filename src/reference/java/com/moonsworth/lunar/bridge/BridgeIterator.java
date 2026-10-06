package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MethodSignature;
import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import com.moonsworth.lunar.ichor.mixin.ClassTypeSignature;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class BridgeIterator implements BytecodeEmitter {
   private final MixinHelper3 field1;
   private final MethodNode field2;
   private final MethodSignature field3;
   private final boolean isInterface;
   private final BytecodeEmitter field4;
   private final List<BytecodeEmitter> field5;

   public BridgeIterator(MixinHelper3 mixinhelper31, MethodNode method2_, MethodSignature mixinhelper23, boolean flag, BytecodeEmitter bridge_155, List<BytecodeEmitter> list) {
      this.field1 = mixinhelper31;
      this.field2 = method2_;
      this.field3 = mixinhelper23;
      this.isInterface = flag;
      this.field4 = bridge_155;
      this.field5 = list;
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      if (this.field3.method1().size() != this.field5.size()) {
         throw new IllegalStateException("Found %s param providers but expected %s in %s!".formatted(this.field5.size(), this.field3.method1().size(), this));
      }

      boolean flag5 = AsmUtils.isStatic(this.field2.access);
      if (!flag5) {
         bridge_671.method2(this.field4);
         bridge_671.method6().method1((MixinHelper3)stack3.pop(), this.field1, instructions4);
      }

      int index6 = 0;

      for (BytecodeEmitter bridge_158 : this.field5) {
         bridge_671.method2(bridge_158);
         bridge_671.method6().method1((MixinHelper3)stack3.pop(), (MixinHelper3)this.field3.method1().get(index6), instructions4);
         index6++;
      }

      instructions4.add(
         new MethodInsnNode(
            flag5 ? 184 : (this.isInterface ? 185 : (this.field2.name.equals("<init>") ? 183 : 182)),
            ((ClassTypeSignature)this.field1.method2()).name(),
            this.field2.name,
            this.field2.desc,
            this.isInterface
         )
      );
      if (!this.field3.method2().equals(MixinHelper3.field1)) {
         stack3.push(this.field3.method2());
      }
   }

   public MixinHelper3 method2() {
      return this.field1;
   }

   public MethodNode method3() {
      return this.field2;
   }

   public MethodSignature method4() {
      return this.field3;
   }

   public BytecodeEmitter method5() {
      return this.field4;
   }

   public List<BytecodeEmitter> method6() {
      return this.field5;
   }
}
