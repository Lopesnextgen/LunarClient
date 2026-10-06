package com.moonsworth.lunar.client.util.nameplate;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.primitives.UnsignedBytes;
import com.google.gson.JsonElement;
import com.lunarclient.dfu.datafixers.util.Pair;
import com.lunarclient.dfu.serialization.Codec;
import com.lunarclient.dfu.serialization.DataResult;
import com.lunarclient.dfu.serialization.Decoder;
import com.lunarclient.dfu.serialization.Dynamic;
import com.lunarclient.dfu.serialization.DynamicOps;
import com.lunarclient.dfu.serialization.JavaOps;
import com.lunarclient.dfu.serialization.JsonOps;
import com.lunarclient.dfu.serialization.Lifecycle;
import com.lunarclient.dfu.serialization.MapCodec;
import com.lunarclient.dfu.serialization.MapLike;
import com.lunarclient.dfu.serialization.RecordBuilder;
import com.lunarclient.dfu.serialization.Codec.ResultFunction;
import com.lunarclient.dfu.serialization.DataResult.Error;
import com.lunarclient.dfu.serialization.codecs.BaseMapCodec;
import com.lunarclient.dfu.serialization.codecs.RecordCodecBuilder;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump36;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import com.moonsworth.lunar.client.util.ThreadModuleDump65;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Base64;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.mutable.MutableObject;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Nameplate {
   public static final Codec<JsonElement> field1 = method4(JsonOps.INSTANCE);
   public static final Codec<Object> field2 = method4(JavaOps.INSTANCE);
   public static final Codec<Vector3f> field3 = Codec.FLOAT
      .listOf()
      .comapFlatMap(
         var0 -> ThreadModuleDump65.method8(var0, 3).map(var0x -> new Vector3f((Float)var0x.get(0), (Float)var0x.get(1), (Float)var0x.get(2))),
         var0 -> List.of(var0.x(), var0.y(), var0.z())
      );
   public static final Codec<Vector4f> field4 = Codec.FLOAT
      .listOf()
      .comapFlatMap(
         var0 -> ThreadModuleDump65.method8(var0, 4)
            .map(var0x -> new Vector4f((Float)var0x.get(0), (Float)var0x.get(1), (Float)var0x.get(2), (Float)var0x.get(3))),
         var0 -> List.of(var0.x(), var0.y(), var0.z(), var0.w())
      );
   public static final Codec<Quaternionf> field5 = Codec.FLOAT
      .listOf()
      .comapFlatMap(
         var0 -> ThreadModuleDump65.method8(var0, 4)
            .map(var0x -> new Quaternionf((Float)var0x.get(0), (Float)var0x.get(1), (Float)var0x.get(2), (Float)var0x.get(3)).normalize()),
         var0 -> List.of(var0.x, var0.y, var0.z, var0.w)
      );
   public static final Codec<AxisAngle4f> field6 = RecordCodecBuilder.create(
      var0 -> var0.group(
            Codec.FLOAT.fieldOf("angle").forGetter(var0x -> var0x.angle), field3.fieldOf("axis").forGetter(var0x -> new Vector3f(var0x.x, var0x.y, var0x.z))
         )
         .apply(var0, AxisAngle4f::new)
   );
   public static final Codec<Quaternionf> field7 = Codec.withAlternative(field5, field6.xmap(Quaternionf::new, AxisAngle4f::new));
   public static final Codec<Matrix4f> field8 = Codec.FLOAT.listOf().comapFlatMap(var0 -> ThreadModuleDump65.method8(var0, 16).map(var0x -> {
      Matrix4f var1 = new Matrix4f();

      for (int var2 = 0; var2 < var0x.size(); var2++) {
         var1.setRowColumn(var2 >> 2, var2 & 3, (Float)var0x.get(var2));
      }

      return var1.determineProperties();
   }), var0 -> {
      FloatArrayList var1 = new FloatArrayList(16);

      for (int var2 = 0; var2 < 16; var2++) {
         var1.add(var0.getRowColumn(var2 >> 2, var2 & 3));
      }

      return var1;
   });
   public static final Codec<Integer> field9 = Codec.withAlternative(
      Codec.INT, field4, var0 -> ThreadModuleDump23.method11(var0.w(), var0.x(), var0.y(), var0.z())
   );
   public static final Codec<Integer> field10 = Codec.BYTE
      .flatComapMap(
         UnsignedBytes::toInt,
         var0 -> var0 > 255 ? DataResult.error(() -> "Unsigned byte was too large: " + var0 + " > 255") : DataResult.success(var0.byteValue())
      );
   public static final Codec<Integer> field11 = method12(0, Integer.MAX_VALUE, var0 -> "Value must be non-negative: " + var0);
   public static final Codec<Integer> field12 = method12(1, Integer.MAX_VALUE, var0 -> "Value must be positive: " + var0);
   public static final Codec<Float> field13 = method13(0.0F, Float.MAX_VALUE, var0 -> "Value must be positive: " + var0);
   public static final Codec<Pattern> field14 = Codec.STRING.comapFlatMap(var0 -> {
      try {
         return DataResult.success(Pattern.compile(var0));
      } catch (PatternSyntaxException var2) {
         return DataResult.error(() -> "Invalid regex pattern '" + var0 + "': " + var2.getMessage());
      }
   }, Pattern::pattern);
   public static final Codec<Instant> field15 = method17(DateTimeFormatter.ISO_INSTANT).xmap(Instant::from, Function.identity());
   public static final Codec<byte[]> field16 = Codec.STRING.comapFlatMap(var0 -> {
      try {
         return DataResult.success(Base64.getDecoder().decode(var0));
      } catch (IllegalArgumentException var2) {
         return DataResult.error(() -> "Malformed base64 string");
      }
   }, var0 -> Base64.getEncoder().encodeToString(var0));
   public static final Codec<String> field17 = Codec.STRING
      .comapFlatMap(var0 -> DataResult.success(StringEscapeUtils.unescapeJava(var0)), StringEscapeUtils::escapeJava);
   public static final Codec<Nameplate.Data10> field18 = Codec.STRING
      .comapFlatMap(
         var0 -> var0.startsWith("#")
            ? method22(var0.substring(1)).map(var0x -> new Nameplate.Data10(var0x, true))
            : method22(var0).map(var0x -> new Nameplate.Data10(var0x, false)),
         Nameplate.Data10::method1
      );
   public static final Codec<BitSet> field19 = Codec.LONG_STREAM.xmap(var0 -> BitSet.valueOf(var0.toArray()), var0 -> Arrays.stream(var0.toLongArray()));
   public static final Codec<IntSet> field20 = Codec.INT_STREAM.xmap(var0 -> method27(var0.toArray()), var0 -> Arrays.stream(var0.toIntArray()));
   public static final Codec<LongSet> field21 = Codec.LONG_STREAM.xmap(var0 -> method28(var0.toArray()), var0 -> Arrays.stream(var0.toLongArray()));
   public static final Codec<Integer> field22 = Codec.STRING
      .comapFlatMap(
         var0 -> {
            try {
               return var0.startsWith("#")
                  ? DataResult.success((int)Long.parseLong(var0.substring(1), 16), Lifecycle.stable())
                  : DataResult.success(Integer.decode(var0), Lifecycle.stable());
            } catch (IllegalArgumentException var2) {
               return DataResult.error(() -> "Invalid Integer " + var0 + ": " + var2.getMessage());
            }
         },
         var0 -> var0 + ""
      );
   public static final Codec<Int2IntMap> field23 = Codec.unboundedMap(field22, Codec.INT).xmap(Int2IntArrayMap::new, Int2IntArrayMap::new);
   public static final Codec<String> field24 = Codec.string(0, 16)
      .validate(
         var0 -> ThreadModuleDump46.method2(var0)
            ? DataResult.success(var0)
            : DataResult.error(() -> "Player name contained disallowed characters: '" + var0 + "'")
      );
   public static final Codec<UUID> field25 = Codec.INT_STREAM
      .comapFlatMap(var0 -> ThreadModuleDump65.method6(var0, 4).map(ThreadModuleDump36::method4), var0 -> Arrays.stream(ThreadModuleDump36.method5(var0)));
   public static final Codec<Set<UUID>> field26 = Codec.list(field25).xmap(Sets::newHashSet, Lists::newArrayList);
   public static final Codec<Set<UUID>> field27 = Codec.list(field25).xmap(Sets::newLinkedHashSet, Lists::newArrayList);
   public static final Codec<UUID> field28 = Codec.STRING.comapFlatMap(var0 -> {
      try {
         return DataResult.success(UUID.fromString(var0), Lifecycle.stable());
      } catch (IllegalArgumentException var2) {
         return DataResult.error(() -> "Invalid UUID " + var0 + ": " + var2.getMessage());
      }
   }, UUID::toString);
   public static final Codec<UUID> field29 = Codec.withAlternative(Codec.STRING.comapFlatMap(var0 -> {
      try {
         return DataResult.success(ThreadModuleDump36.method2(var0), Lifecycle.stable());
      } catch (IllegalArgumentException var2) {
         return DataResult.error(() -> "Invalid UUID " + var0 + ": " + var2.getMessage());
      }
   }, ThreadModuleDump36::method3), field25);
   public static final Codec<UUID> field30 = Codec.withAlternative(field25, field28);
   public static final Codec<String> field31 = Codec.STRING
      .validate(var0 -> var0.isEmpty() ? DataResult.error(() -> "Expected non-empty string") : DataResult.success(var0));
   public static final Codec<Integer> field32 = Codec.STRING.comapFlatMap(var0 -> {
      int[] var1 = var0.codePoints().toArray();
      return var1.length != 1 ? DataResult.error(() -> "Expected one codepoint, got: " + var0) : DataResult.success(var1[0]);
   }, Character::toString);
   public static final Codec<String> field33 = Codec.STRING
      .validate(
         var0 -> !ResourceLocationBridge.isValidPath(var0)
            ? DataResult.error(() -> "Invalid string to use as a resource path element: " + var0)
            : DataResult.success(var0)
      );
   public static final Codec<Boolean> field34 = Codec.STRING.comapFlatMap(var0 -> {
      try {
         return DataResult.success(Boolean.parseBoolean(var0), Lifecycle.stable());
      } catch (IllegalArgumentException var2) {
         return DataResult.error(() -> "Invalid Boolean " + var0 + ": " + var2.getMessage());
      }
   }, var0 -> var0 + "");
   public static final Codec<Boolean> field35 = Codec.withAlternative(Codec.BOOL, field34);
   public static final MapCodec<GameProfile> field36 = RecordCodecBuilder.mapCodec(
      var0 -> var0.group(field29.fieldOf("id").forGetter(GameProfile::getId), field24.fieldOf("name").forGetter(GameProfile::getName))
         .apply(var0, GameProfile::new)
   );
   public static final Function<Optional<Long>, OptionalLong> field37 = var0 -> var0.map(OptionalLong::of).orElseGet(OptionalLong::empty);
   public static final Function<OptionalLong, Optional<Long>> field38 = var0 -> var0.isPresent() ? Optional.of(var0.getAsLong()) : Optional.empty();

   public static Codec<Byte> method1(byte var0, byte var1) {
      Function var2 = checkRange(var0, var1);
      return Codec.BYTE.flatXmap(var2, var2);
   }

   public static Codec<Long> method2(long var0, long var2) {
      Function var4 = checkRange(var0, var2);
      return Codec.LONG.flatXmap(var4, var4);
   }

   public static Codec<Short> method3(short var0, short var1) {
      Function var2 = checkRange(var0, var1);
      return Codec.SHORT.flatXmap(var2, var2);
   }

   public static <T> Codec<T> method4(DynamicOps<T> var0) {
      return Codec.PASSTHROUGH.xmap(var1 -> var1.convert(var0).getValue(), var1 -> new Dynamic(var0, var1));
   }

   public static <A> ResultFunction<A> method5(final A var0) {
      return new ResultFunction<A>() {
         public <T> DataResult<Pair<A, T>> apply(DynamicOps<T> var1, T var2, DataResult<Pair<A, T>> var3) {
            MutableObject var4 = new MutableObject();
            Optional var5 = var3.resultOrPartial(var4::setValue);
            return var5.isPresent() ? var3 : DataResult.error(() -> "(" + (String)var4.getValue() + " -> using default)", Pair.of(var0, var2));
         }

         public <T> DataResult<T> coApply(DynamicOps<T> var1, A var2, DataResult<T> var3) {
            return var3;
         }

         @Override
         public String toString() {
            return "OrElsePartial[" + var0 + "]";
         }
      };
   }

   public static <E> Codec<E> method6(ToIntFunction<E> var0, IntFunction<E> var1, int var2) {
      return Codec.INT
         .flatXmap(
            var1x -> Optional.ofNullable(var1.apply(var1x))
               .<DataResult>map(DataResult::success)
               .orElseGet(() -> DataResult.error(() -> "Unknown element id: " + var1x)),
            var2x -> {
               int var3 = var0.applyAsInt(var2x);
               return var3 == var2 ? DataResult.error(() -> "Element with unknown id: " + var2x) : DataResult.success(var3);
            }
         );
   }

   public static <E> Codec<E> method7(final Codec<E> var0, final Codec<E> var1) {
      return new Codec<E>() {
         public <T> DataResult<T> encode(E var1x, DynamicOps<T> var2, T var3) {
            return var2.compressMaps() ? var1.encode(var1x, var2, var3) : var0.encode(var1x, var2, var3);
         }

         public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> var1x, T var2) {
            return var1x.compressMaps() ? var1.decode(var1x, var2) : var0.decode(var1x, var2);
         }

         @Override
         public String toString() {
            return var0 + " orCompressed " + var1;
         }
      };
   }

   public static <E> MapCodec<E> method8(final MapCodec<E> var0, final MapCodec<E> var1) {
      return new MapCodec<E>() {
         public <T> RecordBuilder<T> encode(E var1x, DynamicOps<T> var2, RecordBuilder<T> var3) {
            return var2.compressMaps() ? var1.encode(var1x, var2, var3) : var0.encode(var1x, var2, var3);
         }

         public <T> DataResult<E> decode(DynamicOps<T> var1x, MapLike<T> var2) {
            return var1x.compressMaps() ? var1.decode(var1x, var2) : var0.decode(var1x, var2);
         }

         public <T> Stream<T> keys(DynamicOps<T> var1x) {
            return var1.keys(var1x);
         }

         public String toString() {
            return var0 + " orCompressed " + var1;
         }
      };
   }

   public static <E> Codec<E> method9(Codec<E> var0, final Function<E, Lifecycle> var1, final Function<E, Lifecycle> var2) {
      return var0.mapResult(new ResultFunction<E>() {
         public <T> DataResult<Pair<E, T>> apply(DynamicOps<T> var1x, T var2x, DataResult<Pair<E, T>> var3) {
            return var3.result().map(var2xxx -> var3.setLifecycle((Lifecycle)var1.apply(var2xxx.getFirst()))).orElse(var3);
         }

         public <T> DataResult<T> coApply(DynamicOps<T> var1x, E var2x, DataResult<T> var3) {
            return var3.setLifecycle((Lifecycle)var2.apply(var2x));
         }

         @Override
         public String toString() {
            return "WithLifecycle[" + var1 + " " + var2 + "]";
         }
      });
   }

   public static <E> Codec<E> method10(Codec<E> var0, Function<E, Lifecycle> var1) {
      return method9(var0, var1, var1);
   }

   public static <K, V> Nameplate.Data11<K, V> method11(Codec<K> var0, Codec<V> var1) {
      return new Nameplate.Data11<>(var0, var1);
   }

   public static Codec<Integer> method12(int var0, int var1, Function<Integer, String> var2) {
      return Codec.INT
         .validate(var3 -> var3.compareTo(var0) >= 0 && var3.compareTo(var1) <= 0 ? DataResult.success(var3) : DataResult.error(() -> (String)var2.apply(var3)));
   }

   public static Codec<Float> method13(float var0, float var1, Function<Float, String> var2) {
      return Codec.FLOAT
         .validate(var3 -> var3.compareTo(var0) > 0 && var3.compareTo(var1) <= 0 ? DataResult.success(var3) : DataResult.error(() -> (String)var2.apply(var3)));
   }

   public static <T> Codec<List<T>> method14(Codec<List<T>> var0) {
      return var0.validate(var0x -> var0x.isEmpty() ? DataResult.error(() -> "List must have contents") : DataResult.success(var0x));
   }

   public static <E, L extends Collection<E>, T> Function<L, DataResult<L>> method15(Function<E, T> var0) {
      return var1 -> {
         Iterator var2 = var1.iterator();
         if (var2.hasNext()) {
            Object var3 = var0.apply(var2.next());

            while (var2.hasNext()) {
               Object var4 = var2.next();
               Object var5 = var0.apply(var4);
               if (var5 != var3) {
                  return DataResult.error(() -> "Mixed type list: element " + var4 + " had type " + var5 + ", but list is of type " + var3);
               }
            }
         }

         return DataResult.success(var1, Lifecycle.stable());
      };
   }

   public static <A> Codec<A> method16(final Codec<A> var0) {
      return Codec.of(var0, new Decoder<A>() {
         public <T> DataResult<Pair<A, T>> decode(DynamicOps<T> var1, T var2) {
            try {
               return var0.decode(var1, var2);
            } catch (Exception var4) {
               return DataResult.error(() -> "Caught exception decoding " + var2 + ": " + var4.getMessage());
            }
         }
      });
   }

   public static Codec<TemporalAccessor> method17(DateTimeFormatter var0) {
      Function var1 = var1x -> {
         try {
            return DataResult.success(var0.parse(var1x));
         } catch (Exception var3) {
            return DataResult.error(var3::getMessage);
         }
      };
      return Codec.STRING.comapFlatMap(var1, var0::format);
   }

   public static MapCodec<OptionalLong> method18(MapCodec<Optional<Long>> var0) {
      return var0.xmap(field37, field38);
   }

   public static <K, V> Codec<Map<K, V>> method19(Codec<Map<K, V>> var0, int var1) {
      return var0.validate(
         var1x -> var1x.size() > var1
            ? DataResult.error(() -> "Map is too long: " + var1x.size() + ", expected range [0-" + var1 + "]")
            : DataResult.success(var1x)
      );
   }

   public static <T> Codec<Object2BooleanMap<T>> method20(Codec<T> var0) {
      return Codec.unboundedMap(var0, Codec.BOOL).xmap(Object2BooleanOpenHashMap::new, Object2ObjectOpenHashMap::new);
   }

   public static <A> Codec<Optional<A>> method21(final Codec<A> var0) {
      return new Codec<Optional<A>>() {
         public <T> DataResult<Pair<Optional<A>, T>> decode(DynamicOps<T> var1, T var2) {
            return method2(var1, (T)var2)
               ? DataResult.success(Pair.of(Optional.empty(), var2))
               : var0.decode(var1, var2).map(var0xx -> var0xx.mapFirst(Optional::of));
         }

         public <T> DataResult<T> method1(Optional<A> var1, DynamicOps<T> var2, T var3) {
            return var1.isEmpty() ? DataResult.success(var2.emptyMap()) : var0.encode(var1.get(), var2, var3);
         }

         private static <T> boolean method2(DynamicOps<T> var0x, T var1) {
            Optional var2 = var0x.getMap(var1).result();
            return var2.isPresent() && ((MapLike)var2.get()).entries().findAny().isEmpty();
         }
      };
   }

   public static DataResult<ResourceLocationBridge> method22(String var0) {
      try {
         return DataResult.success(ResourceLocationBridge.create(var0));
      } catch (Exception var2) {
         return DataResult.error(() -> "Not a valid resource location: " + var0 + " " + var2.getMessage());
      }
   }

   public static <T> Codec<Set<T>> method23(Codec<T> var0) {
      return Codec.list(var0).xmap(Sets::newHashSet, Lists::newArrayList);
   }

   public static <T> Codec<Set<T>> method24(Codec<T> var0, int var1, int var2) {
      return Codec.list(var0, var1, var2).xmap(Sets::newHashSet, Lists::newArrayList);
   }

   public static <E extends Enum<E>> Codec<E> method25(Supplier<E[]> var0) {
      Enum[] var1 = (Enum[])var0.get();
      if (var1.length == 0) {
         throw new IllegalArgumentException("Empty enum");
      }

      Class var2 = var1[0].getClass();
      return method26((E[])var1, Enum::name, var1x -> Enum.valueOf(var2, var1x), Enum::ordinal);
   }

   public static <E extends Enum<E>> Codec<E> method26(E[] var0, Function<E, String> var1, Function<String, E> var2, ToIntFunction<E> var3) {
      return method7(Codec.stringResolver(var1, var2), method6(var3, var1x -> (E)(var1x >= 0 && var1x < var0.length ? var0[var1x] : null), -1));
   }

   public static <N extends Number & Comparable<N>> Function<N, DataResult<N>> checkRange(N var0, N var1) {
      return var2 -> var2.compareTo(var0) >= 0 && var2.compareTo(var1) <= 0
         ? DataResult.success(var2)
         : DataResult.error(() -> "Value " + var2 + " outside of range [" + var0 + ":" + var1 + "]");
   }

   private static IntSet method27(int... var0) {
      Object var1 = var0.length <= 4 ? new IntArraySet(var0.length) : new IntOpenHashSet(var0.length);

      for (int var5 : var0) {
         if (!var1.add(var5)) {
            throw new IllegalArgumentException("Duplicate element: " + var5);
         }
      }

      return (IntSet)var1;
   }

   private static LongSet method28(long... var0) {
      Object var1 = var0.length <= 4 ? new LongArraySet(var0.length) : new LongOpenHashSet(var0.length);

      for (long var5 : var0) {
         if (!var1.add(var5)) {
            throw new IllegalArgumentException("Duplicate element: " + var5);
         }
      }

      return (LongSet)var1;
   }

   public class Data10 {
      private final ResourceLocationBridge field1;
      private final boolean field2;

      public Data10(ResourceLocationBridge var1, boolean var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @Override
      public String toString() {
         return this.method1();
      }

      public String method1() {
         return this.field2 ? "#" + this.field1 : this.field1.toString();
      }

      public ResourceLocationBridge method2() {
         return this.field1;
      }

      public boolean method3() {
         return this.field2;
      }
   }

   public class Data11<K, V> implements Codec<Map<K, V>>, BaseMapCodec<K, V> {
      private final Codec<K> field1;
      private final Codec<V> field2;

      public Data11(Codec<K> var1, Codec<V> var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public <T> DataResult<Map<K, V>> decode(DynamicOps<T> var1, MapLike<T> var2) {
         Builder var3 = ImmutableMap.builder();

         for (Pair var5 : var2.entries().toList()) {
            DataResult var6 = this.keyCodec().parse(var1, var5.getFirst());
            DataResult var7 = this.elementCodec().parse(var1, var5.getSecond());
            DataResult var8 = var6.apply2stable(Pair::of, var7);
            Optional var9 = var8.error();
            if (var9.isPresent()) {
               String var11 = ((Error)var9.get()).message();
               return DataResult.error(() -> var6.result().isPresent() ? "Map entry '" + var6.result().get() + "' : " + var11 : var11);
            }

            if (var8.result().isEmpty()) {
               return DataResult.error(() -> "Empty or invalid map contents are not allowed");
            }

            Pair var10 = (Pair)var8.result().get();
            var3.put(var10.getFirst(), var10.getSecond());
         }

         return DataResult.success(var3.build());
      }

      public <T> DataResult<Pair<Map<K, V>, T>> decode(DynamicOps<T> var1, T var2) {
         return var1.getMap(var2).setLifecycle(Lifecycle.stable()).flatMap(var2x -> this.decode(var1, var2x)).map(var1x -> Pair.of(var1x, var2));
      }

      public <T> DataResult<T> encode(Map<K, V> var1, DynamicOps<T> var2, T var3) {
         return this.encode(var1, var2, var2.mapBuilder()).build(var3);
      }

      @Override
      public String toString() {
         return "StrictUnboundedMapCodec[" + this.field1 + " -> " + this.field2 + "]";
      }

      public Codec<K> keyCodec() {
         return this.field1;
      }

      public Codec<V> elementCodec() {
         return this.field2;
      }
   }
}
