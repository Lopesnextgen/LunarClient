package com.moonsworth.lunar.client.util;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.OpenType;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class ThreadModuleDump61 {
   private static final Set<String> field1 = Set.of("http", "https");
   private static final String field2 = "lunarclient";

   public static boolean method1(@Nullable URI var0) {
      if (var0 == null) {
         return false;
      }

      String var1 = var0.getScheme();
      if (var1 == null) {
         return false;
      }

      String var2 = var1.toLowerCase(Locale.ROOT);
      return field1.contains(var2) || var2.equals("lunarclient");
   }

   public static boolean method2(@Nullable String var0) {
      if (var0 == null) {
         return false;
      }

      try {
         return method1(new URI(var0));
      } catch (URISyntaxException var2) {
         return false;
      }
   }

   public static boolean method3(@Nullable String var0) {
      if (var0 == null) {
         return false;
      }

      try {
         String var1 = new URI(var0).getScheme();
         return var1 != null && !field1.contains(var1.toLowerCase(Locale.ROOT));
      } catch (URISyntaxException var2) {
         return false;
      }
   }

   public static URI method4(String var0) {
      URI var1 = new URI(var0);
      String var2 = var1.getScheme();
      if (var2 == null) {
         throw new URISyntaxException(var0, "Missing protocol");
      } else {
         String var3 = var2.toLowerCase(Locale.ROOT);
         if (!field1.contains(var3)) {
            throw new URISyntaxException(var0, "Unsupported protocol: " + var3);
         } else {
            return var1;
         }
      }
   }

   public static boolean method5(URI var0, Initiator var1) {
      return method6(var0, var1, false);
   }

   public static boolean method6(URI var0, Initiator var1, boolean var2) {
      if (method12(var0.toString(), var1, var2)) {
         return true;
      } else if (!method1(var0)) {
         method11(var0.toString());
         return false;
      } else {
         return Bridge.method8().method64(var0);
      }
   }

   public static boolean method7(String var0, Initiator var1) {
      return method8(var0, var1, false);
   }

   public static boolean method8(String var0, Initiator var1, boolean var2) {
      if (method12(var0, var1, var2)) {
         return true;
      } else if (var2) {
         return false;
      } else if (!method2(var0)) {
         method11(var0);
         return false;
      } else {
         return Bridge.method8().method65(var0);
      }
   }

   public static boolean method9(URL var0, Initiator var1) {
      return method12(var0.toString(), var1, false);
   }

   public static boolean method10(File var0) {
      return Bridge.method8().method66(var0);
   }

   private static void method11(String var0) {
      Slayer.method6("Browser", "Refusing to open a URL with an unsupported protocol: " + var0, new Object[0]);
   }

   private static boolean method12(String var0, Initiator var1, boolean var2) {
      try {
         URL var3 = new URL(var0);
         String var4 = var3.getProtocol();
         if (!var4.equals("http") && !var4.equals("https") && !var4.equals("lunarclient")) {
            return false;
         }
      } catch (Exception var5) {
         return false;
      }

      if (!ThreadModuleDump63.method6().isEmpty() && ThreadModuleDump63.method6().get().method19() != EntityRendererType2.READY) {
         ThreadModuleDump63.method6()
            .get()
            .method16()
            .openUrl(
               null,
               OpenUrlRequest.newBuilder()
                  .setUrl(var0)
                  .setInitiator(var1)
                  .setOpenType(var2 ? OpenType.OPEN_TYPE_FORCE_EMBEDDED : OpenType.OPEN_TYPE_UNSPECIFIED)
                  .build(),
               var0x -> {}
            );
         return true;
      } else {
         return false;
      }
   }

   @Generated
   private ThreadModuleDump61() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
