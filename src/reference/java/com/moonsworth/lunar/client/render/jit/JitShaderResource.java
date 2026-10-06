package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump94;
import com.moonsworth.lunar.client.util.alert.Alert6;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JitShaderResource extends JitResourceBase<String> {
   private final JitShaderResource.Type field7;
   @NotNull
   private final Alert6 field8;

   public JitShaderResource(JitAssetKey var1, JitShaderResource.Type var2, Alert6 var3) {
      super(var1, JitResource.Data6.method1());
      this.field7 = var2;
      this.field8 = var3;
   }

   protected String method7() {
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      ResourceLocationBridge var2 = this.field3.method3();
      IResourceBridge var3 = var1.bridge$getResource(var2);
      if (var3 == null) {
         Slayer.method5("Couldn't find: " + var2, new Object[0]);
         throw new RuntimeException(new FileNotFoundException(var2.toString()));
      }

      String var4;
      try (InputStream var5 = var3.bridge$getInputStream()) {
         var4 = IOUtils.toString(var5, StandardCharsets.UTF_8);
      } catch (Exception var10) {
         Slayer.warn("Couldn't load: " + var2, var10);
         throw new RuntimeException("Failed to load " + var2.toString(), var10);
      }

      return ThreadModuleDump94.method4(var4, this.field8, this.field7);
   }

   public enum Type {
      FRAGMENT,
      VERTEX;

      @Nullable
      public static JitShaderResource.Type fromString(String var0) {
         return switch (var0.toLowerCase()) {
            case "vsh", "vertex" -> VERTEX;
            case "fsh", "fragment" -> FRAGMENT;
            default -> null;
         };
      }
   }
}
