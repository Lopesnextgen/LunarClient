package com.moonsworth.lunar.ichor;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public final class MixinLegacy2Iterator implements MixinLegacy2 {
   private final Map<String, MixinLegacy> field1;
   private final URLClassLoader field2;
   private final IchorTransformer field3;

   public MixinLegacy2Iterator(IchorTransformer var1, URLClassLoader var2) {
      this.field2 = var2;
      this.field3 = var1;
      this.field1 = new ConcurrentHashMap<>();
   }

   @Override
   public MixinLegacy provide(String var1) {
      if (var1 == null) {
         return null;
      }

      if (var1.equals("java/lang/Object")) {
         return MixinLegacy.field3;
      }

      MixinLegacy var2 = this.field1.get(var1);
      if (var2 != null) {
         return var2;
      }

      if (var1.startsWith("java.") || var1.startsWith("java/")) {
         try {
            Class var26 = Class.forName(var1.replace("/", "."), false, this.getClass().getClassLoader());
            MixinLegacy var28 = var26.getSuperclass() == null ? null : this.provide(var26.getSuperclass().getName().replace(".", "/"));
            List var29 = Arrays.stream(var26.getInterfaces()).map(var1x -> this.provide(var1x.getName().replace(".", "/"))).toList();
            var2 = new MixinLegacy(var1.replace(".", "/"), var28, var29);
            this.field1.put(var1.replace(".", "/"), var2);
            return var2;
         } catch (ClassNotFoundException var23) {
            var23.printStackTrace();
         }
      }

      Ichor4 var3 = this.field2.method18();
      String var4 = var1.replace('.', '/') + ".class";
      Set var5 = this.field3.method13(var3, var1);
      String var6 = this.field3.method15(var3, var1);

      try {
         for (String var8 : var5) {
            var4 = var8.replace('.', '/') + ".class";
            InputStream var9 = this.field2.getResourceAsStream(var4);
            if (var9 != null) {
               try {
                  ClassReader var10 = new ClassReader(var9);
                  ClassNode var11 = new ClassNode();
                  var10.accept(var11, 7);
                  var9.close();
                  String var12 = var11.superName;
                  String var13 = null;
                  if (var12 != null) {
                     var13 = this.field3.method15(var3, var12);
                  }

                  MixinLegacy var14 = this.provide(var13);
                  List var15 = var11.interfaces.stream().map(var2x -> this.field3.method15(var3, var2x)).map(this::provide).filter(Objects::nonNull).toList();
                  var2 = new MixinLegacy(var6, var14, var15);
                  this.field1.put(var6, var2);
                  return var2;
               } finally {
                  var9.close();
               }
            }
         }
      } catch (Exception var22) {
      }

      return null;
   }
}
