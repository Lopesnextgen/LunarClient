package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import java.util.Queue;
import java.util.Stack;
import org.objectweb.asm.tree.InsnList;

public interface BytecodeEmitter {
   void method1(BridgeMethodGenerator bridge_671, Queue<MethodParameter> list2, Stack<MixinHelper3> stack3, InsnList instructions4);
}
