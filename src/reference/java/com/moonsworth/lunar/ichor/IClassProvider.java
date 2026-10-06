package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.api.IchorClassLoader;
import java.net.URL;
import org.spongepowered.asm.launch.MixinBootstrap;

public class IClassProvider implements org.spongepowered.asm.service.IClassProvider {
   public IClassProvider() {
   }

   @Deprecated
   public URL[] getClassPath() {
      return new URL[0];
   }

   public Class<?> findClass(String text1) {
      return this.findClass(text1, true);
   }

   public Class<?> findClass(String text1, boolean flag2) {
      ClassLoader classloader3 = MixinBootstrap.class.getClassLoader();
      if (classloader3 instanceof IchorClassLoader) {
         return Class.forName(text1, flag2, classloader3);
      } else {
         throw new IllegalStateException("Loaded Mixin from " + classloader3.getName());
      }
   }

   public Class<?> findAgentClass(String text1, boolean flag2) {
      throw new ClassNotFoundException("Ichor doesn't support Mixin Agent Classes. Tried to find " + text1);
   }
}
