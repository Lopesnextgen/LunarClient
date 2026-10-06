package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.loader.Ichor4Type;
import com.moonsworth.lunar.loader.mixin.MixinHelper;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import org.cadixdev.lorenz.MappingSet;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class MixinTaskTransform extends com.moonsworth.lunar.ichor.MixinInternalTask implements IchorInjection {
   static final String field6 = "com/moonsworth/";
   private static final String field7 = Type.getInternalName(ThreadModuleDump63.class);
   private static final String field8 = "hasModule";
   private static final String field9 = "MC_VERSION";
   private final Config field10;
   private final String field11;
   private final List<String> field12;

   public MixinTaskTransform(Config var1, @Nullable MappingSet var2, @Nullable MappingSet var3, @Nullable String var4, List<String> list) {
      super(var2, var3, var1.getOrdinal());
      this.field10 = var1;
      this.field11 = Objects.requireNonNullElse(var4, "com/moonsworth/");
      this.field12 = list;
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.OMNIMIXIN_PROCESSING};
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      return this.method1(var1.className());
   }

   @Override
   public boolean method1(String var1) {
      if (var1.startsWith("com/moonsworth/lunar/client")) {
         return false;
      }

      for (String var3 : this.field12) {
         if (var1.startsWith(var3)) {
            return false;
         }
      }

      return this.field11 == null || var1.startsWith(this.field11);
   }

   @Override
   public boolean method2(AnnotationNode var1) {
      return MixinHelper.method1(var1, this.field10);
   }

   @Override
   protected boolean method3(FieldInsnNode var1) {
      return var1.getOpcode() == 178 && var1.owner.equals(field7) && var1.name.equals("MC_VERSION");
   }

   @Override
   protected boolean method4(MethodInsnNode var1) {
      return var1.getOpcode() == 184 && var1.owner.equals(field7) && var1.name.equals("hasModule") && var1.desc.equals("(Ljava/lang/String;)Z");
   }
}
