package com.moonsworth.lunar.client.util.io;

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
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.text.UuidUtils;
import com.moonsworth.lunar.client.util.text.TextUtils;
import com.moonsworth.lunar.client.util.collection.CollectionUtils;
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

public class ExtraCodecs {
   public static final Codec<JsonElement> field1 = method4(JsonOps.INSTANCE);
   public static final Codec<Object> field2 = method4(JavaOps.INSTANCE);
   public static final Codec<Vector3f> field3 = Codec.FLOAT
      .listOf()
      .comapFlatMap(
         arg0 -> CollectionUtils.method8(arg0, 3).map(arg0x -> new Vector3f((Float)arg0x.get(0), (Float)arg0x.get(1), (Float)arg0x.get(2))),
         arg0 -> List.of(arg0.x(), arg0.y(), arg0.z())
      );
   public static final Codec<Vector4f> field4 = Codec.FLOAT
      .listOf()
      .comapFlatMap(
         arg0 -> CollectionUtils.method8(arg0, 4)
            .map(arg0x -> new Vector4f((Float)arg0x.get(0), (Float)arg0x.get(1), (Float)arg0x.get(2), (Float)arg0x.get(3))),
         arg0 -> List.of(arg0.x(), arg0.y(), arg0.z(), arg0.w())
      );
   public static final Codec<Quaternionf> field5 = Codec.FLOAT
      .listOf()
      .comapFlatMap(
         arg0 -> CollectionUtils.method8(arg0, 4)
            .map(arg0x -> new Quaternionf((Float)arg0x.get(0), (Float)arg0x.get(1), (Float)arg0x.get(2), (Float)arg0x.get(3)).normalize()),
         arg0 -> List.of(arg0.x, arg0.y, arg0.z, arg0.w)
      );
   public static final Codec<AxisAngle4f> field6 = RecordCodecBuilder.create(
      arg0 -> arg0.group(
            Codec.FLOAT.fieldOf("angle").forGetter(arg0x -> arg0x.angle), field3.fieldOf("axis").forGetter(arg0x -> new Vector3f(arg0x.x, arg0x.y, arg0x.z))
         )
         .apply(arg0, AxisAngle4f::new)
   );
   public static final Codec<Quaternionf> field7 = Codec.withAlternative(field5, field6.xmap(Quaternionf::new, AxisAngle4f::new));
   public static final Codec<Matrix4f> field8 = Codec.FLOAT.listOf().comapFlatMap(arg0 -> CollectionUtils.method8(arg0, 16).map(arg0x -> {
      Matrix4f matrix4f1 = new Matrix4f();

      for (int index2 = 0; index2 < arg0x.size(); index2++) {
         matrix4f1.setRowColumn(index2 >> 2, index2 & 3, (Float)arg0x.get(index2));
      }

      return matrix4f1.determineProperties();
   }), arg0 -> {
      FloatArrayList floatarraylist1 = new FloatArrayList(16);

      for (int index2 = 0; index2 < 16; index2++) {
         floatarraylist1.add(arg0.getRowColumn(index2 >> 2, index2 & 3));
      }

      return floatarraylist1;
   });
   public static final Codec<Integer> field9 = Codec.withAlternative(
      Codec.INT, field4, arg0 -> ColorUtils.method11(arg0.w(), arg0.x(), arg0.y(), arg0.z())
   );
   public static final Codec<Integer> field10 = Codec.BYTE
      .flatComapMap(
         UnsignedBytes::toInt,
         arg0 -> arg0 > 255 ? DataResult.error(() -> "Unsigned byte was too large: " + arg0 + " > 255") : DataResult.success(arg0.byteValue())
      );
   public static final Codec<Integer> field11 = method12(0, Integer.MAX_VALUE, arg0 -> "Value must be non-negative: " + arg0);
   public static final Codec<Integer> field12 = method12(1, Integer.MAX_VALUE, arg0 -> "Value must be positive: " + arg0);
   public static final Codec<Float> field13 = method13(0.0F, Float.MAX_VALUE, arg0 -> "Value must be positive: " + arg0);
   public static final Codec<Pattern> field14 = Codec.STRING.comapFlatMap(arg0 -> {
      try {
         return DataResult.success(Pattern.compile(arg0));
      } catch (PatternSyntaxException patternsyntaxexception2) {
         return DataResult.error(() -> "Invalid regex pattern '" + arg0 + "': " + patternsyntaxexception2.getMessage());
      }
   }, Pattern::pattern);
   public static final Codec<Instant> field15 = method17(DateTimeFormatter.ISO_INSTANT).xmap(Instant::from, Function.identity());
   public static final Codec<byte[]> field16 = Codec.STRING.comapFlatMap(arg0 -> {
      try {
         return DataResult.success(Base64.getDecoder().decode(arg0));
      } catch (IllegalArgumentException illegalargumentexception2) {
         return DataResult.error(() -> "Malformed base64 string");
      }
   }, arg0 -> Base64.getEncoder().encodeToString(arg0));
   public static final Codec<String> field17 = Codec.STRING
      .comapFlatMap(arg0 -> DataResult.success(StringEscapeUtils.unescapeJava(arg0)), StringEscapeUtils::escapeJava);
   public static final Codec<ExtraCodecs.TagOrElementLocation> field18 = Codec.STRING
      .comapFlatMap(
         arg0 -> arg0.startsWith("#")
            ? method22(arg0.substring(1)).map(arg0x -> new ExtraCodecs.TagOrElementLocation(arg0x, true))
            : method22(arg0).map(arg0x -> new ExtraCodecs.TagOrElementLocation(arg0x, false)),
         ExtraCodecs.TagOrElementLocation::method1
      );
   public static final Codec<BitSet> field19 = Codec.LONG_STREAM.xmap(arg0 -> BitSet.valueOf(arg0.toArray()), arg0 -> Arrays.stream(arg0.toLongArray()));
   public static final Codec<IntSet> field20 = Codec.INT_STREAM.xmap(arg0 -> method27(arg0.toArray()), arg0 -> Arrays.stream(arg0.toIntArray()));
   public static final Codec<LongSet> field21 = Codec.LONG_STREAM.xmap(arg0 -> method28(arg0.toArray()), arg0 -> Arrays.stream(arg0.toLongArray()));
   public static final Codec<Integer> field22 = Codec.STRING
      .comapFlatMap(
         arg0 -> {
            try {
               return arg0.startsWith("#")
                  ? DataResult.success((int)Long.parseLong(arg0.substring(1), 16), Lifecycle.stable())
                  : DataResult.success(Integer.decode(arg0), Lifecycle.stable());
            } catch (IllegalArgumentException illegalargumentexception2) {
               return DataResult.error(() -> "Invalid Integer " + arg0 + ": " + illegalargumentexception2.getMessage());
            }
         },
         arg0 -> arg0 + ""
      );
   public static final Codec<Int2IntMap> field23 = Codec.unboundedMap(field22, Codec.INT).xmap(Int2IntArrayMap::new, Int2IntArrayMap::new);
   public static final Codec<String> field24 = Codec.string(0, 16)
      .validate(
         arg0 -> TextUtils.isValidPlayerName(arg0)
            ? DataResult.success(arg0)
            : DataResult.error(() -> "Player name contained disallowed characters: '" + arg0 + "'")
      );
   public static final Codec<UUID> field25 = Codec.INT_STREAM
      .comapFlatMap(arg0 -> CollectionUtils.method6(arg0, 4).map(UuidUtils::method4), arg0 -> Arrays.stream(UuidUtils.method5(arg0)));
   public static final Codec<Set<UUID>> field26 = Codec.list(field25).xmap(Sets::newHashSet, Lists::newArrayList);
   public static final Codec<Set<UUID>> field27 = Codec.list(field25).xmap(Sets::newLinkedHashSet, Lists::newArrayList);
   public static final Codec<UUID> field28 = Codec.STRING.comapFlatMap(arg0 -> {
      try {
         return DataResult.success(UUID.fromString(arg0), Lifecycle.stable());
      } catch (IllegalArgumentException illegalargumentexception2) {
         return DataResult.error(() -> "Invalid UUID " + arg0 + ": " + illegalargumentexception2.getMessage());
      }
   }, UUID::toString);
   public static final Codec<UUID> field29 = Codec.withAlternative(Codec.STRING.comapFlatMap(arg0 -> {
      try {
         return DataResult.success(UuidUtils.method2(arg0), Lifecycle.stable());
      } catch (IllegalArgumentException illegalargumentexception2) {
         return DataResult.error(() -> "Invalid UUID " + arg0 + ": " + illegalargumentexception2.getMessage());
      }
   }, UuidUtils::method3), field25);
   public static final Codec<UUID> field30 = Codec.withAlternative(field25, field28);
   public static final Codec<String> field31 = Codec.STRING
      .validate(arg0 -> arg0.isEmpty() ? DataResult.error(() -> "Expected non-empty string") : DataResult.success(arg0));
   public static final Codec<Integer> field32 = Codec.STRING.comapFlatMap(arg0 -> {
      int[] items1 = arg0.codePoints().toArray();
      return items1.length != 1 ? DataResult.error(() -> "Expected one codepoint, got: " + arg0) : DataResult.success(items1[0]);
   }, Character::toString);
   public static final Codec<String> field33 = Codec.STRING
      .validate(
         arg0 -> !ResourceLocationBridge.isValidPath(arg0)
            ? DataResult.error(() -> "Invalid string to use as a resource path element: " + arg0)
            : DataResult.success(arg0)
      );
   public static final Codec<Boolean> field34 = Codec.STRING.comapFlatMap(arg0 -> {
      try {
         return DataResult.success(Boolean.parseBoolean(arg0), Lifecycle.stable());
      } catch (IllegalArgumentException illegalargumentexception2) {
         return DataResult.error(() -> "Invalid Boolean " + arg0 + ": " + illegalargumentexception2.getMessage());
      }
   }, arg0 -> arg0 + "");
   public static final Codec<Boolean> field35 = Codec.withAlternative(Codec.BOOL, field34);
   public static final MapCodec<GameProfile> field36 = RecordCodecBuilder.mapCodec(
      arg0 -> arg0.group(field29.fieldOf("id").forGetter(GameProfile::getId), field24.fieldOf("name").forGetter(GameProfile::getName))
         .apply(arg0, GameProfile::new)
   );
   public static final Function<Optional<Long>, OptionalLong> field37 = arg0 -> arg0.map(OptionalLong::of).orElseGet(OptionalLong::empty);
   public static final Function<OptionalLong, Optional<Long>> field38 = arg0 -> arg0.isPresent() ? Optional.of(arg0.getAsLong()) : Optional.empty();

   public ExtraCodecs() {
   }

   public static Codec<Byte> method1(byte number0, byte number1) {
      Function function2 = checkRange(number0, number1);
      return Codec.BYTE.flatXmap(function2, function2);
   }

   public static Codec<Long> method2(long number0, long number2) {
      Function function4 = checkRange(number0, number2);
      return Codec.LONG.flatXmap(function4, function4);
   }

   public static Codec<Short> method3(short number0, short number1) {
      Function function2 = checkRange(number0, number1);
      return Codec.SHORT.flatXmap(function2, function2);
   }

   public static <T> Codec<T> method4(DynamicOps<T> dynamicops0) {
      return Codec.PASSTHROUGH.xmap(arg1 -> arg1.convert(dynamicops0).getValue(), arg1 -> new Dynamic(dynamicops0, arg1));
   }

   public static <A> ResultFunction<A> method5(final A value0) {
      return new ResultFunction<A>() {
         public <T> DataResult<Pair<A, T>> apply(DynamicOps<T> dynamicops1, T value2, DataResult<Pair<A, T>> dataresult3) {
            MutableObject mutableobject4 = new MutableObject();
            Optional optional5 = dataresult3.resultOrPartial(mutableobject4::setValue);
            return optional5.isPresent() ? dataresult3 : DataResult.error(() -> "(" + (String)mutableobject4.getValue() + " -> using default)", Pair.of(value0, value2));
         }

         public <T> DataResult<T> coApply(DynamicOps<T> dynamicops1, A value2, DataResult<T> dataresult3) {
            return dataresult3;
         }

         @Override
         public String toString() {
            return "OrElsePartial[" + value0 + "]";
         }
      };
   }

   public static <E> Codec<E> method6(ToIntFunction<E> tointfunction0, IntFunction<E> intfunction1, int number2) {
      return Codec.INT
         .flatXmap(
            arg1x -> Optional.ofNullable(intfunction1.apply(arg1x))
               .<DataResult>map(DataResult::success)
               .orElseGet(() -> DataResult.error(() -> "Unknown element id: " + arg1x)),
            arg2x -> {
               int number3 = tointfunction0.applyAsInt(arg2x);
               return number3 == number2 ? DataResult.error(() -> "Element with unknown id: " + arg2x) : DataResult.success(number3);
            }
         );
   }

   public static <E> Codec<E> method7(final Codec<E> codec0, final Codec<E> codec1) {
      return new Codec<E>() {
         public <T> DataResult<T> encode(E value1x, DynamicOps<T> dynamicops2, T value3) {
            return dynamicops2.compressMaps() ? codec1.encode(value1x, dynamicops2, value3) : codec0.encode(value1x, dynamicops2, value3);
         }

         public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> dynamicops1x, T value2) {
            return dynamicops1x.compressMaps() ? codec1.decode(dynamicops1x, value2) : codec0.decode(dynamicops1x, value2);
         }

         @Override
         public String toString() {
            return codec0 + " orCompressed " + codec1;
         }
      };
   }

   public static <E> MapCodec<E> method8(final MapCodec<E> mapcodec0, final MapCodec<E> mapcodec1) {
      return new MapCodec<E>() {
         public <T> RecordBuilder<T> encode(E value1x, DynamicOps<T> dynamicops2, RecordBuilder<T> recordbuilder3) {
            return dynamicops2.compressMaps() ? mapcodec1.encode(value1x, dynamicops2, recordbuilder3) : mapcodec0.encode(value1x, dynamicops2, recordbuilder3);
         }

         public <T> DataResult<E> decode(DynamicOps<T> dynamicops1x, MapLike<T> maplike2) {
            return dynamicops1x.compressMaps() ? mapcodec1.decode(dynamicops1x, maplike2) : mapcodec0.decode(dynamicops1x, maplike2);
         }

         public <T> Stream<T> keys(DynamicOps<T> dynamicops1x) {
            return mapcodec1.keys(dynamicops1x);
         }

         public String toString() {
            return mapcodec0 + " orCompressed " + mapcodec1;
         }
      };
   }

   public static <E> Codec<E> method9(Codec<E> codec0, final Function<E, Lifecycle> function1, final Function<E, Lifecycle> function2) {
      return codec0.mapResult(new ResultFunction<E>() {
         public <T> DataResult<Pair<E, T>> apply(DynamicOps<T> dynamicops1x, T value2x, DataResult<Pair<E, T>> dataresult3) {
            return dataresult3.result().map(arg2xxx -> dataresult3.setLifecycle((Lifecycle)function1.apply(arg2xxx.getFirst()))).orElse(dataresult3);
         }

         public <T> DataResult<T> coApply(DynamicOps<T> dynamicops1x, E value2x, DataResult<T> dataresult3) {
            return dataresult3.setLifecycle((Lifecycle)function2.apply(value2x));
         }

         @Override
         public String toString() {
            return "WithLifecycle[" + function1 + " " + function2 + "]";
         }
      });
   }

   public static <E> Codec<E> method10(Codec<E> codec0, Function<E, Lifecycle> function1) {
      return method9(codec0, function1, function1);
   }

   public static <K, V> ExtraCodecs.StrictUnboundedMapCodec<K, V> method11(Codec<K> codec0, Codec<V> codec1) {
      return new ExtraCodecs.StrictUnboundedMapCodec<>(codec0, codec1);
   }

   public static Codec<Integer> method12(int number0, int number1, Function<Integer, String> function2) {
      return Codec.INT
         .validate(arg3 -> arg3.compareTo(number0) >= 0 && arg3.compareTo(number1) <= 0 ? DataResult.success(arg3) : DataResult.error(() -> (String)function2.apply(arg3)));
   }

   public static Codec<Float> method13(float value0, float value1, Function<Float, String> function2) {
      return Codec.FLOAT
         .validate(arg3 -> arg3.compareTo(value0) > 0 && arg3.compareTo(value1) <= 0 ? DataResult.success(arg3) : DataResult.error(() -> (String)function2.apply(arg3)));
   }

   public static <T> Codec<List<T>> method14(Codec<List<T>> codec0) {
      return codec0.validate(arg0x -> arg0x.isEmpty() ? DataResult.error(() -> "List must have contents") : DataResult.success(arg0x));
   }

   public static <E, L extends Collection<E>, T> Function<L, DataResult<L>> method15(Function<E, T> function0) {
      return arg1 -> {
         Iterator iterator2 = arg1.iterator();
         if (iterator2.hasNext()) {
            Object obj3 = function0.apply(iterator2.next());

            while (iterator2.hasNext()) {
               Object obj4 = iterator2.next();
               Object obj5 = function0.apply(obj4);
               if (obj5 != obj3) {
                  return DataResult.error(() -> "Mixed type list: element " + obj4 + " had type " + obj5 + ", but list is of type " + obj3);
               }
            }
         }

         return DataResult.success(arg1, Lifecycle.stable());
      };
   }

   public static <A> Codec<A> method16(final Codec<A> codec0) {
      return Codec.of(codec0, new Decoder<A>() {
         public <T> DataResult<Pair<A, T>> decode(DynamicOps<T> dynamicops1, T value2) {
            try {
               return codec0.decode(dynamicops1, value2);
            } catch (Exception exception4) {
               return DataResult.error(() -> "Caught exception decoding " + value2 + ": " + exception4.getMessage());
            }
         }
      });
   }

   public static Codec<TemporalAccessor> method17(DateTimeFormatter datetimeformatter0) {
      Function function1 = arg1x -> {
         try {
            return DataResult.success(datetimeformatter0.parse(arg1x));
         } catch (Exception exception3) {
            return DataResult.error(exception3::getMessage);
         }
      };
      return Codec.STRING.comapFlatMap(function1, datetimeformatter0::format);
   }

   public static MapCodec<OptionalLong> method18(MapCodec<Optional<Long>> mapcodec0) {
      return mapcodec0.xmap(field37, field38);
   }

   public static <K, V> Codec<Map<K, V>> method19(Codec<Map<K, V>> codec0, int number1) {
      return codec0.validate(
         arg1x -> arg1x.size() > number1
            ? DataResult.error(() -> "Map is too long: " + arg1x.size() + ", expected range [0-" + number1 + "]")
            : DataResult.success(arg1x)
      );
   }

   public static <T> Codec<Object2BooleanMap<T>> method20(Codec<T> codec0) {
      return Codec.unboundedMap(codec0, Codec.BOOL).xmap(Object2BooleanOpenHashMap::new, Object2ObjectOpenHashMap::new);
   }

   public static <A> Codec<Optional<A>> method21(final Codec<A> codec0) {
      return new Codec<Optional<A>>() {
         public <T> DataResult<Pair<Optional<A>, T>> decode(DynamicOps<T> dynamicops1, T value2) {
            return method2(dynamicops1, (T)value2)
               ? DataResult.success(Pair.of(Optional.empty(), value2))
               : codec0.decode(dynamicops1, value2).map(arg0xx -> arg0xx.mapFirst(Optional::of));
         }

         public <T> DataResult<T> method1(Optional<A> optional1, DynamicOps<T> dynamicops2, T value3) {
            return optional1.isEmpty() ? DataResult.success(dynamicops2.emptyMap()) : codec0.encode(optional1.get(), dynamicops2, value3);
         }

         private static <T> boolean method2(DynamicOps<T> dynamicops0x, T value1) {
            Optional optional2 = dynamicops0x.getMap(value1).result();
            return optional2.isPresent() && ((MapLike)optional2.get()).entries().findAny().isEmpty();
         }
      };
   }

   public static DataResult<ResourceLocationBridge> method22(String text0) {
      try {
         return DataResult.success(ResourceLocationBridge.create(text0));
      } catch (Exception exception2) {
         return DataResult.error(() -> "Not a valid resource location: " + text0 + " " + exception2.getMessage());
      }
   }

   public static <T> Codec<Set<T>> method23(Codec<T> codec0) {
      return Codec.list(codec0).xmap(Sets::newHashSet, Lists::newArrayList);
   }

   public static <T> Codec<Set<T>> method24(Codec<T> codec0, int number1, int number2) {
      return Codec.list(codec0, number1, number2).xmap(Sets::newHashSet, Lists::newArrayList);
   }

   public static <E extends Enum<E>> Codec<E> method25(Supplier<E[]> supplier0) {
      Enum[] items1 = (Enum[])supplier0.get();
      if (items1.length == 0) {
         throw new IllegalArgumentException("Empty enum");
      }

      Class clazz2 = items1[0].getClass();
      return method26((E[])items1, Enum::name, arg1x -> Enum.valueOf(clazz2, arg1x), Enum::ordinal);
   }

   public static <E extends Enum<E>> Codec<E> method26(E[] items0, Function<E, String> function1, Function<String, E> function2, ToIntFunction<E> tointfunction3) {
      return method7(Codec.stringResolver(function1, function2), method6(tointfunction3, arg1x -> (E)(arg1x >= 0 && arg1x < items0.length ? items0[arg1x] : null), -1));
   }

   public static <N extends Number & Comparable<N>> Function<N, DataResult<N>> checkRange(N value0, N value1) {
      return arg2 -> arg2.compareTo(value0) >= 0 && arg2.compareTo(value1) <= 0
         ? DataResult.success(arg2)
         : DataResult.error(() -> "Value " + arg2 + " outside of range [" + value0 + ":" + value1 + "]");
   }

   private static IntSet method27(int... items0) {
      Object obj1 = items0.length <= 4 ? new IntArraySet(items0.length) : new IntOpenHashSet(items0.length);

      for (int index5 : items0) {
         if (!obj1.add(index5)) {
            throw new IllegalArgumentException("Duplicate element: " + index5);
         }
      }

      return (IntSet)obj1;
   }

   private static LongSet method28(long... items0) {
      Object obj1 = items0.length <= 4 ? new LongArraySet(items0.length) : new LongOpenHashSet(items0.length);

      for (long index5 : items0) {
         if (!obj1.add(index5)) {
            throw new IllegalArgumentException("Duplicate element: " + index5);
         }
      }

      return (LongSet)obj1;
   }

   public class TagOrElementLocation {
      private final ResourceLocationBridge field1;
      private final boolean field2;

      public TagOrElementLocation(ResourceLocationBridge horsestats141, boolean flag2) {
         this.field1 = horsestats141;
         this.field2 = flag2;
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

   public class StrictUnboundedMapCodec<K, V> implements Codec<Map<K, V>>, BaseMapCodec<K, V> {
      private final Codec<K> field1;
      private final Codec<V> field2;

      public StrictUnboundedMapCodec(Codec<K> codec1, Codec<V> codec2) {
         this.field1 = codec1;
         this.field2 = codec2;
      }

      public <T> DataResult<Map<K, V>> decode(DynamicOps<T> dynamicops1, MapLike<T> maplike2) {
         Builder builder3 = ImmutableMap.builder();

         for (Pair pair5 : maplike2.entries().toList()) {
            DataResult dataresult6 = this.keyCodec().parse(dynamicops1, pair5.getFirst());
            DataResult dataresult7 = this.elementCodec().parse(dynamicops1, pair5.getSecond());
            DataResult dataresult8 = dataresult6.apply2stable(Pair::of, dataresult7);
            Optional optional9 = dataresult8.error();
            if (optional9.isPresent()) {
               String text11 = ((Error)optional9.get()).message();
               return DataResult.error(() -> dataresult6.result().isPresent() ? "Map entry '" + dataresult6.result().get() + "' : " + text11 : text11);
            }

            if (dataresult8.result().isEmpty()) {
               return DataResult.error(() -> "Empty or invalid map contents are not allowed");
            }

            Pair pair10 = (Pair)dataresult8.result().get();
            builder3.put(pair10.getFirst(), pair10.getSecond());
         }

         return DataResult.success(builder3.build());
      }

      public <T> DataResult<Pair<Map<K, V>, T>> decode(DynamicOps<T> dynamicops1, T value2) {
         return dynamicops1.getMap(value2).setLifecycle(Lifecycle.stable()).flatMap(arg2x -> this.decode(dynamicops1, arg2x)).map(arg1x -> Pair.of(arg1x, value2));
      }

      public <T> DataResult<T> encode(Map<K, V> map1, DynamicOps<T> dynamicops2, T value3) {
         return this.encode(map1, dynamicops2, dynamicops2.mapBuilder()).build(value3);
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
