package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.mixin.MixinHelper3;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.VarInsnNode;

public class MethodParameter {
   private final MixinHelper3 field1;
   private final int field2;
   private final Map<String, AnnotationNode> annotations;

   public MethodParameter(MixinHelper3 mixinhelper31, int number2, List<AnnotationNode> list) {
      this(mixinhelper31, number2, new HashMap<>());
      if (list != null) {
         for (AnnotationNode annotation5 : list) {
            this.annotations.put(annotation5.desc, annotation5);
         }
      }
   }

   public MethodParameter(MixinHelper3 mixinhelper31, int number2, Map<String, AnnotationNode> map) {
      this.field1 = mixinhelper31;
      this.field2 = number2;
      this.annotations = map;
   }

   public AbstractInsnNode load() {
      return new VarInsnNode(this.field1.method2().getOpcode(21), this.field2);
   }

   public MixinHelper3 method1() {
      return this.field1;
   }

   public int index() {
      return this.field2;
   }

   public Map<String, AnnotationNode> getAnnotations() {
      return this.annotations;
   }
}
