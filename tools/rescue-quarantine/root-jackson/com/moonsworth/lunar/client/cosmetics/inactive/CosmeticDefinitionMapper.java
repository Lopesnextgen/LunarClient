package com.moonsworth.lunar.client.cosmetics.inactive;

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
import com.moonsworth.lunar.client.cosmetics.inactive.gui.AnimationSelector;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing.InactiveTask;
import com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui.AnimationControllerDefinition;
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

public class CosmeticDefinitionMapper {
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

   public CosmeticDefinitionMapper() {
   }

   public static Gui2Handler method1(String text0) {
      try {
         return (Gui2Handler)method4().method98(text0);
      } catch (Exception exception4) {
         byte[] items2 = text0.getBytes(StandardCharsets.UTF_8);
         Attachment attachment3 = new Attachment(items2, "geckolib-cosmetic-definition.json");
         Sentry.configureScope(arg1 -> arg1.addAttachment(attachment3));
         throw exception4;
      }
   }

   @NotNull
   private static MixinHelper28 method2() {
      com.moonsworth.lunar.MixinHelper27.Data3 data30 = (com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)((com.moonsworth.lunar.MixinHelper27.Data3)MixinHelper27_3.method8()
                     .method5(new MixinHelper3122()))
                  .method5(MixinHelper44.WRITE_DATES_AS_TIMESTAMPS, false))
               .method5(MixinHelper42_3.FAIL_ON_UNKNOWN_PROPERTIES, false))
            .method5(MixinHelper43.ACCEPT_CASE_INSENSITIVE_ENUMS, true))
         .ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new MixinHelper43[]{
               MixinHelper43.AUTO_DETECT_CREATORS, MixinHelper43.AUTO_DETECT_FIELDS, MixinHelper43.AUTO_DETECT_GETTERS, MixinHelper43.AUTO_DETECT_IS_GETTERS
            }
         );
      MixinHelper27 mixinhelper271 = data30.IORCHHRIOCOIHRCRCHHRRHHCRIIIHH();
      MixinHelper312 mixinhelper3122 = new MixinHelper312();
      mixinhelper3122.method13(Gui2Handler.class, new Inactive3$Data3(data30, mixinhelper3122));
      mixinhelper3122.method13(MolangResourceProvider.class, new MolangResourceProvider.MolangResourceLoader());
      mixinhelper3122.method13(OffsetDateTime.class, new OffsetDateTimeDeserializer());
      mixinhelper3122.method13(AnimationControllerDefinition.class, new Inactive3$Data4(data30, mixinhelper3122));
      mixinhelper3122.method13(AnimationBuilder.class, new AnimationBuilderDeserializer());
      mixinhelper3122.method13(AnimationSelector.class, new Inactive3$Data(data30, mixinhelper3122));
      mixinhelper3122.method13(Evaluatable.class, new MolangDeserializer());
      mixinhelper3122.method13(InactiveTask.class, new Inactive3$Data6(data30, mixinhelper3122));
      mixinhelper271.method10(mixinhelper3122);
      return mixinhelper271.method223(Gui2Handler.class);
   }

   private static <T> T method3(MixinHelper53 mixinhelper530, T value1) {
      for (Class clazz2 = value1.getClass(); clazz2 != null && clazz2 != Object.class; clazz2 = clazz2.getSuperclass()) {
         for (Field field6 : clazz2.getDeclaredFields()) {
            Annotation27 annotation277 = field6.getAnnotation(Annotation27.class);
            if (annotation277 != null && annotation277.required()) {
               field6.setAccessible(true);

               try {
                  if (field6.get(value1) == null) {
                     throw new IOException22(mixinhelper530, "Missing field in JSON: " + annotation277.value());
                  }
               } catch (IllegalAccessException illegalaccessexception10) {
                  illegalaccessexception10.printStackTrace();
               }
            }
         }
      }

      for (Method method14 : value1.getClass().getMethods()) {
         Annotation27 annotation2715 = method14.getAnnotation(Annotation27.class);
         if (annotation2715 != null && annotation2715.required()) {
            method14.setAccessible(true);
            if (method14.getParameterCount() == 0) {
               try {
                  if (method14.invoke(value1) == null) {
                     throw new IOException22(mixinhelper530, "Missing field in JSON: " + annotation2715.value());
                  }
               } catch (IllegalAccessException | InvocationTargetException illegalaccessexception9) {
                  illegalaccessexception9.printStackTrace();
               }
            }
         }
      }

      return (T)value1;
   }

   @Generated
   public static MixinHelper28 method4() {
      Object obj0 = field2.get();
      if (obj0 == null) {
         synchronized (field2) {
            obj0 = field2.get();
            if (obj0 == null) {
               MixinHelper28 mixinhelper282 = method2();
               obj0 = mixinhelper282 == null ? field2 : mixinhelper282;
               field2.set(obj0);
            }
         }
      }

      return (MixinHelper28)(obj0 == field2 ? null : obj0);
   }
}
