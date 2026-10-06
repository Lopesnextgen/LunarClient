package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;

public class PathEmitter implements BytecodeEmitter {
   private final List<MixinTargetMember> path;
   private final boolean field1;

   public PathEmitter(List<MixinTargetMember> list, boolean flag) {
      this.path = list;
      this.field1 = flag;
   }

   public void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> queue, Stack<MixinHelper3> stack3, InsnList instructions4) {
      MixinTargetMember bridge_135 = this.path.get(this.path.size() - 1);
      Object obj6 = new ThisEmitter();

      for (MixinTargetMember bridge_138 : this.path) {
         if (bridge_138.method5() != null) {
            ArrayList list9 = new ArrayList();

            for (MixinHelper3 mixinhelper311 : bridge_138.method6().method1()) {
               list9.add(new ParameterConversionEmitter());
            }

            obj6 = new BridgeIterator(
               bridge_138.method3(),
               bridge_138.method5(),
               bridge_138.method6(),
               bridge_138.isInterface(),
               (BytecodeEmitter)(bridge_138.method5().name.equals("<init>") ? new NewInstanceEmitter(bridge_138.method3()) : obj6),
               list9
            );
         } else {
            if (bridge_138.method4() == null) {
               throw new UnsupportedOperationException("Invalid target node " + bridge_138);
            }

            if (bridge_138 == bridge_135 && this.field1) {
               obj6 = new FieldSetEmitter(bridge_138.method3(), bridge_138.method4(), bridge_138.method1(), (BytecodeEmitter)obj6, new ParameterConversionEmitter());
            } else {
               obj6 = new FieldGetEmitter(bridge_138.method3(), bridge_138.method4(), bridge_138.method1(), (BytecodeEmitter)obj6);
            }
         }
      }

      bridge_671.method2((BytecodeEmitter)obj6);
   }

   public List<MixinTargetMember> getPath() {
      return this.path;
   }

   public boolean method3() {
      return this.field1;
   }
}
