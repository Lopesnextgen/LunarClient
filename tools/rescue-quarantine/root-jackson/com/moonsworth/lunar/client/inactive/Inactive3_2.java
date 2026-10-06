package com.moonsworth.lunar.client.inactive;

import com.eliotlash.molang.ast.Evaluatable;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.IOException22;
import com.moonsworth.lunar.MixinHelper27;
import com.moonsworth.lunar.MixinHelper27_3;
import com.moonsworth.lunar.MixinHelper28;
import com.moonsworth.lunar.MixinHelper312;
import com.moonsworth.lunar.MixinHelper3122;
import com.moonsworth.lunar.MixinHelper42_3;
import com.moonsworth.lunar.MixinHelper43;
import com.moonsworth.lunar.MixinHelper44;
import com.moonsworth.lunar.MixinHelper53;
import com.moonsworth.lunar.client.inactive.gui.Gui;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.inactive.mixin.fishing.Fishing;
import com.moonsworth.lunar.client.inactive.mixin.gui.Gui2;
import io.sentry.Attachment;
import io.sentry.Sentry;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.builder.AnimationBuilder;

public class Inactive3_2 {
   private static final DateTimeFormatter field1 = new DateTimeFormatterBuilder()
      .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
      .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      .appendOptional(DateTimeFormatter.ISO_INSTANT)
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
      .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      .toFormatter()
      .withZone(ZoneOffset.UTC);
   private static final AtomicReference<Object> field2 = new AtomicReference<>();

   public static Gui2Handler method1(String var0) {
      try {
         return (Gui2Handler)method4().method98(var0);
      } catch (Exception var4) {
         byte[] var2 = var0.getBytes(StandardCharsets.UTF_8);
         Attachment var3 = new Attachment(var2, "geckolib-cosmetic-definition.json");
         Sentry.configureScope(var1 -> var1.addAttachment(var3));
         throw var4;
      }
   }

   @NotNull
   private static MixinHelper28 method2() {
      com.moonsworth.lunar.MixinHelper27.Data3 var0 = (com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)MixinHelper27_3.method8()
                     .method5(new MixinHelper3122()))
                  .method5(MixinHelper44.WRITE_DATES_AS_TIMESTAMPS, false))
               .method5(MixinHelper42_3.FAIL_ON_UNKNOWN_PROPERTIES, false))
            .method5(MixinHelper43.ACCEPT_CASE_INSENSITIVE_ENUMS, true))
         .ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new MixinHelper43[]{
               MixinHelper43.AUTO_DETECT_CREATORS, MixinHelper43.AUTO_DETECT_FIELDS, MixinHelper43.AUTO_DETECT_GETTERS, MixinHelper43.AUTO_DETECT_IS_GETTERS
            }
         );
      MixinHelper27 var1 = var0.method1();
      MixinHelper312 var2 = new MixinHelper312();
      var2.method13(Gui2Handler.class, new Inactive3$Data3(var0, var2));
      var2.method13(Inactive4.class, new Inactive4.Data());
      var2.method13(OffsetDateTime.class, new Inactive3$Data5());
      var2.method13(Gui2.class, new Inactive3$Data4(var0, var2));
      var2.method13(AnimationBuilder.class, new Inactive3$Data2());
      var2.method13(Gui.class, new Inactive3$Data(var0, var2));
      var2.method13(Evaluatable.class, new MixinHelper1022());
      var2.method13(Fishing.class, new Inactive3$Data6(var0, var2));
      var1.method10(var2);
      return var1.method223(Gui2Handler.class);
   }

   private static <T> T method3(MixinHelper53 var0, T var1) {
      for (Class var2 = var1.getClass(); var2 != null && var2 != Object.class; var2 = var2.getSuperclass()) {
         for (Field var6 : var2.getDeclaredFields()) {
            Annotation27 var7 = var6.getAnnotation(Annotation27.class);
            if (var7 != null && var7.required()) {
               var6.setAccessible(true);

               try {
                  if (var6.get(var1) == null) {
                     throw new IOException22(var0, "Missing field in JSON: " + var7.value());
                  }
               } catch (IllegalAccessException var10) {
                  var10.printStackTrace();
               }
            }
         }
      }

      for (Method var14 : var1.getClass().getMethods()) {
         Annotation27 var15 = var14.getAnnotation(Annotation27.class);
         if (var15 != null && var15.required()) {
            var14.setAccessible(true);
            if (var14.getParameterCount() == 0) {
               try {
                  if (var14.invoke(var1) == null) {
                     throw new IOException22(var0, "Missing field in JSON: " + var15.value());
                  }
               } catch (IllegalAccessException | InvocationTargetException var9) {
                  var9.printStackTrace();
               }
            }
         }
      }

      return (T)var1;
   }

   @Generated
   public static MixinHelper28 method4() {
      Object var0 = field2.get();
      if (var0 == null) {
         synchronized (field2) {
            var0 = field2.get();
            if (var0 == null) {
               MixinHelper28 var2 = method2();
               var0 = var2 == null ? field2 : var2;
               field2.set(var0);
            }
         }
      }

      return (MixinHelper28)(var0 == field2 ? null : var0);
   }
}
