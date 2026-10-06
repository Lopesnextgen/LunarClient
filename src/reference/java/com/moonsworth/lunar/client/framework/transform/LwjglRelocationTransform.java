package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.ichor.TransformMethod;
import com.moonsworth.lunar.ichor.TransformClass;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.ClassNodeRemapper;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.loader.PipelineStage;
import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.SimpleRemapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import com.moonsworth.lunar.client.framework.transform.LwjglFunctionProvider;

public class LwjglRelocationTransform extends com.moonsworth.lunar.ichor.util.ClassPrefixFilter {
   private static final String field2 = Type.getInternalName(FunctionProviderFactory.class);
   private final Map<String, String> field3 = new HashMap<>();
   private final Remapper field4;

   public LwjglRelocationTransform() {
      super(new String[]{"org/lwjgl/nanovg/", "org/lwjgl/util/tinyfd/", "org/lwjgl/stb/", "org/lwjgl/system/", "org/lwjgl/util/opus/"});
      this.field3.put("org/lwjgl/BufferUtils", "org/lwjgl/actually3/BufferUtils");
      this.field3.put("org/lwjgl/PointerBuffer", "org/lwjgl/actually3/PointerBuffer");
      this.field3.put("org/lwjgl/CLongBuffer", "org/lwjgl/actually3/CLongBuffer");
      this.field4 = new SimpleRemapper(this.field3);
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.POST_MIXIN};
   }

   @TransformClass
   public void method2(ClassNode node1, URLClassLoader urlclassloader2) {
      if (!this.field3.containsKey(node1.name)) {
         ClassNodeRemapper.method1(urlclassloader2.method1(), node1, this.field4);
      }
   }

   @TransformMethod
   public void method3(ClassNode node1, MethodNode method2_) {
      if (node1.name.equals("org/lwjgl/nanovg/NanoVGGLConfig") && method2_.name.equals("configGL")) {
         InsnList instructions3 = new InsnList();
         instructions3.add(new VarInsnNode(22, 0));
         instructions3.add(new MethodInsnNode(184, field2, "newFunctionProvider", "()Ljava/lang/Object;", false));
         instructions3.add(new TypeInsnNode(192, "org/lwjgl/system/LwjglFunctionProvider"));
         instructions3.add(new MethodInsnNode(184, "org/lwjgl/nanovg/NanoVGGLConfig", "config", "(JLorg/lwjgl/system/LwjglFunctionProvider;)V", false));
         instructions3.add(new InsnNode(177));
         method2_.instructions.clear();
         method2_.instructions.insert(instructions3);
      }
   }
}
