package com.moonsworth.lunar.client.util.io;

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
import com.moonsworth.lunar.client.framework.listener.ReflectionUtils;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNullSerialized {
   class Data implements TypeAdapterFactory {
      public Data() {
      }

      public <T> @Nullable TypeAdapter<T> create(Gson gson1, TypeToken<T> typetoken2) {
         final Set set3 = ReflectionUtils.method6(typetoken2.getRawType(), NotNullSerialized.class)
            .filter(arg0 -> !Modifier.isStatic(arg0.getModifiers()))
            .flatMap(arg1x -> method1(gson1, arg1x))
            .collect(Collectors.toSet());
         if (set3.isEmpty()) {
            return null;
         }

         try {
            typetoken2.getRawType().getDeclaredConstructor();
         } catch (NoSuchMethodException nosuchmethodexception6) {
            throw new JsonParseException("@NotNullSerialized requires a no-arg constructor on " + typetoken2 + " to keep field defaults", nosuchmethodexception6);
         }

         final TypeAdapter typeadapter4 = gson1.getAdapter(JsonElement.class);
         final TypeAdapter typeadapter5 = gson1.getDelegateAdapter(this, typetoken2);
         return new TypeAdapter<T>() {
            public void write(JsonWriter jsonwriter1, T value2x) {
               typeadapter5.write(jsonwriter1, value2x);
            }

            public T read(JsonReader jsonreader1) {
               JsonElement element2x = (JsonElement)typeadapter4.read(jsonreader1);
               if (element2x instanceof JsonObject json3x) {
                  for (String text5x : set3) {
                     if (json3x.get(text5x) instanceof JsonNull) {
                        json3x.remove(text5x);
                     }
                  }
               }

               return (T)typeadapter5.fromJsonTree(element2x);
            }
         };
      }

      private static Stream<String> method1(Gson gson0, Field field1) {
         SerializedName serializedname2 = field1.getAnnotation(SerializedName.class);
         return serializedname2 == null
            ? Stream.of(gson0.fieldNamingStrategy().translateName(field1))
            : Stream.concat(Stream.of(serializedname2.value()), Arrays.stream(serializedname2.alternate()));
      }
   }
}
