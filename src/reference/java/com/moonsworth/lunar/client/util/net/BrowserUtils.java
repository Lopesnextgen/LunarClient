package com.moonsworth.lunar.client.util.net;

import com.lunarclient.gameipc.browser.v1.OpenUrlRequest;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.OpenType;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.network.websocket.ConnectionState;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Set;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.Ref;

public final class BrowserUtils {
   private static final Set<String> field1 = Set.of("http", "https");
   private static final String field2 = "lunarclient";

   public static boolean method1(@Nullable URI uri0) {
      if (uri0 == null) {
         return false;
      }

      String text1 = uri0.getScheme();
      if (text1 == null) {
         return false;
      }

      String text2 = text1.toLowerCase(Locale.ROOT);
      return field1.contains(text2) || text2.equals("lunarclient");
   }

   public static boolean method2(@Nullable String text0) {
      if (text0 == null) {
         return false;
      }

      try {
         return method1(new URI(text0));
      } catch (URISyntaxException urisyntaxexception2) {
         return false;
      }
   }

   public static boolean method3(@Nullable String text0) {
      if (text0 == null) {
         return false;
      }

      try {
         String text1 = new URI(text0).getScheme();
         return text1 != null && !field1.contains(text1.toLowerCase(Locale.ROOT));
      } catch (URISyntaxException urisyntaxexception2) {
         return false;
      }
   }

   public static URI method4(String text0) {
      URI uri1 = new URI(text0);
      String text2 = uri1.getScheme();
      if (text2 == null) {
         throw new URISyntaxException(text0, "Missing protocol");
      } else {
         String text3 = text2.toLowerCase(Locale.ROOT);
         if (!field1.contains(text3)) {
            throw new URISyntaxException(text0, "Unsupported protocol: " + text3);
         } else {
            return uri1;
         }
      }
   }

   public static boolean method5(URI uri0, Initiator initiator1) {
      return method6(uri0, initiator1, false);
   }

   public static boolean method6(URI uri0, Initiator initiator1, boolean flag2) {
      if (method12(uri0.toString(), initiator1, flag2)) {
         return true;
      } else if (!method1(uri0)) {
         method11(uri0.toString());
         return false;
      } else {
         return Bridge.method8().method64(uri0);
      }
   }

   public static boolean method7(String text0, Initiator initiator1) {
      return method8(text0, initiator1, false);
   }

   public static boolean method8(String text0, Initiator initiator1, boolean flag2) {
      if (method12(text0, initiator1, flag2)) {
         return true;
      } else if (flag2) {
         return false;
      } else if (!method2(text0)) {
         method11(text0);
         return false;
      } else {
         return Bridge.method8().method65(text0);
      }
   }

   public static boolean method9(URL url0, Initiator initiator1) {
      return method12(url0.toString(), initiator1, false);
   }

   public static boolean method10(File file0) {
      return Bridge.method8().method66(file0);
   }

   private static void method11(String text0) {
      LunarLogger.method6("Browser", "Refusing to open a URL with an unsupported protocol: " + text0, new Object[0]);
   }

   private static boolean method12(String text0, Initiator initiator1, boolean flag2) {
      try {
         URL url3 = new URL(text0);
         String text4 = url3.getProtocol();
         if (!text4.equals("http") && !text4.equals("https") && !text4.equals("lunarclient")) {
            return false;
         }
      } catch (Exception exception5) {
         return false;
      }

      if (!Ref.method6().isEmpty() && Ref.method6().get().method19() != ConnectionState.DISCONNECTED) {
         Ref.method6()
            .get()
            .method16()
            .openUrl(
               null,
               OpenUrlRequest.newBuilder()
                  .setUrl(text0)
                  .setInitiator(initiator1)
                  .setOpenType(flag2 ? OpenType.OPEN_TYPE_FORCE_EMBEDDED : OpenType.OPEN_TYPE_UNSPECIFIED)
                  .build(),
               arg0x -> {}
            );
         return true;
      } else {
         return false;
      }
   }

   @Generated
   private BrowserUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
