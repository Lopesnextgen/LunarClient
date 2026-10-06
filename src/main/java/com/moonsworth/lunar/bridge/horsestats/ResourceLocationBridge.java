package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/ResourceLocation")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/resources/ResourceLocation")),
         @BridgeVersionMapping(version = 35, targets = @BridgeTargetMapping("net/minecraft/resources/Identifier"))
   }
)
public interface ResourceLocationBridge {
   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("resourceDomain")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("namespace"))
      }
   )
   String bridge$getDomain();

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("resourcePath")),
            @BridgeVersionMapping(version = 5, targets = @BridgeTargetMapping("path"))
      }
   )
   String bridge$getPath();

   @Annotation("<init>(Ljava/lang/String;Ljava/lang/String;)V")
   @Contract("_, _ -> new")
   static ResourceLocationBridge create(String var0, String var1) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("<init>(Ljava/lang/String;)V")),
            @BridgeVersionMapping(version = 24, targets = {})
      }
   )
   @Contract("_ -> new")
   static ResourceLocationBridge create(String var0) {
      String[] var1 = new String[]{"minecraft", var0};
      int var2 = var0.indexOf(58);
      if (var2 >= 0) {
         var1[1] = var0.substring(var2 + 1);
         if (var2 >= 1) {
            var1[0] = var0.substring(0, var2);
         }
      }

      return create(var1[0], var1[1]);
   }

   @Contract("_, _ -> new")
   static ResourceLocationBridge method1(String var0, String var1) {
      return var0.contains(":") ? create(var0) : create(var1, var0);
   }

   static boolean method2(char var0) {
      return var0 >= '0' && var0 <= '9' || var0 >= 'a' && var0 <= 'z' || var0 == '_' || var0 == ':' || var0 == '/' || var0 == '.' || var0 == '-';
   }

   static boolean isValidPath(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (!validPathChar(var0.charAt(var1))) {
            return false;
         }
      }

      return true;
   }

   static boolean method3(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (!validNamespaceChar(var0.charAt(var1))) {
            return false;
         }
      }

      return true;
   }

   static boolean validPathChar(char var0) {
      return var0 == '_' || var0 == '-' || var0 >= 'a' && var0 <= 'z' || var0 >= '0' && var0 <= '9' || var0 == '/' || var0 == '.';
   }

   static boolean validNamespaceChar(char var0) {
      return var0 == '_' || var0 == '-' || var0 >= 'a' && var0 <= 'z' || var0 >= '0' && var0 <= '9' || var0 == '.';
   }

   static boolean method4(String var0) {
      String[] var1 = method5(var0, ':');
      return method3(StringUtils.isEmpty(var1[0]) ? "minecraft" : var1[0]) && isValidPath(var1[1]);
   }

   private static String[] method5(String var0, char var1) {
      String[] var2 = new String[]{"minecraft", var0};
      int var3 = var0.indexOf(var1);
      if (var3 >= 0) {
         var2[1] = var0.substring(var3 + 1);
         if (var3 >= 1) {
            var2[0] = var0.substring(0, var3);
         }
      }

      return var2;
   }
}
