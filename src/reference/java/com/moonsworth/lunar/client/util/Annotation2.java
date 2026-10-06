package com.moonsworth.lunar.client.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.util.io.NotNullSerialized;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Annotation2 {
   class Data implements TypeAdapterFactory {
      public <T> @Nullable TypeAdapter<T> create(Gson var1, TypeToken<T> var2) {
         final Set var3 = ThreadModuleDump31.method6(var2.getRawType(), Annotation2.class)
            .filter(var0 -> !Modifier.isStatic(var0.getModifiers()))
            .flatMap(var1x -> method1(var1, var1x))
            .collect(Collectors.toSet());
         if (var3.isEmpty()) {
            return null;
         }

         try {
            var2.getRawType().getDeclaredConstructor();
         } catch (NoSuchMethodException var6) {
            throw new JsonParseException("@NotNullSerialized requires a no-arg constructor on " + var2 + " to keep field defaults", var6);
         }

         final TypeAdapter var4 = var1.getAdapter(JsonElement.class);
         final TypeAdapter var5 = var1.getDelegateAdapter(this, var2);
         return new TypeAdapter<T>() {
            public void write(JsonWriter var1, T var2x) {
               var5.write(var1, var2x);
            }

            public T read(JsonReader var1) {
               JsonElement var2x = (JsonElement)var4.read(var1);
               if (var2x instanceof JsonObject var3x) {
                  for (String var5x : var3) {
                     if (var3x.get(var5x) instanceof JsonNull) {
                        var3x.remove(var5x);
                     }
                  }
               }

               return (T)var5.fromJsonTree(var2x);
            }
         };
      }

      private static Stream<String> method1(Gson var0, Field var1) {
         SerializedName var2 = var1.getAnnotation(SerializedName.class);
         return var2 == null
            ? Stream.of(var0.fieldNamingStrategy().translateName(var1))
            : Stream.concat(Stream.of(var2.value()), Arrays.stream(var2.alternate()));
      }
   }
}
