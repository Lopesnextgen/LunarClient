package com.moonsworth.lunar.client.network.apollo;

import com.lunarclient.apollo.option.Option;
import com.lunarclient.apollo.player.ApolloPlayer;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.options.OptionUpdateEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public class OptionsImpl extends com.lunarclient.apollo.option.OptionsImpl {
   public OptionsImpl(Collection<Option<?, ?, ?>> var1) {
      super(null, var1);
   }

   public Optional<Option<?, ?, ?>> method1(String var1) {
      return Optional.ofNullable((Option<?, ?, ?>)this.registry.get(var1));
   }

   public <T, C extends Option<T, ?, ?>> T get(@NonNull C var1) {
      if (var1 == null) {
         throw new NullPointerException("option is marked non-null but is null");
      }

      var1 = this.registry.getOrDefault(var1.getKey(), var1);
      Object var2 = this.options.get(var1.getKey());
      return (T)(var2 == null ? var1.getDefaultValue() : var2);
   }

   @Nullable
   public <T, C extends Option<T, ?, ?>> T get(@NonNull ApolloPlayer var1, @NonNull C var2) {
      if (var1 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var2 == null) {
         throw new NullPointerException("option is marked non-null but is null");
      }

      var2 = this.registry.getOrDefault(var2.getKey(), var2);
      Object var3 = this.playerOptions.getOrDefault(var1.getUniqueId(), Collections.emptyMap()).get(var2.getKey());
      return (T)(var3 == null ? this.get((C)var2) : var3);
   }

   public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(@NonNull C var1) {
      if (var1 == null) {
         throw new NullPointerException("option is marked non-null but is null");
      }

      var1 = this.registry.getOrDefault(var1.getKey(), var1);
      Object var2 = this.options.get(var1.getKey());
      return var2 == null ? Optional.empty() : Optional.of((T)var2);
   }

   public <T, C extends Option<T, ?, ?>> Optional<T> getDirect(@NonNull ApolloPlayer var1, @NonNull C var2) {
      if (var1 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var2 == null) {
         throw new NullPointerException("option is marked non-null but is null");
      }

      var2 = this.registry.getOrDefault(var2.getKey(), var2);
      Object var3 = this.playerOptions.getOrDefault(var1.getUniqueId(), Collections.emptyMap()).get(var2.getKey());
      return var3 == null ? this.getDirect((C)var2) : Optional.of((T)var3);
   }

   protected boolean postEvent(Option<?, ?, ?> var1, @Nullable ApolloPlayer var2, @Nullable Object var3) {
      OptionUpdateEvent var4 = ClientEventBus.method29().method12(OptionUpdateEvent.class, () -> new OptionUpdateEvent(this, var1, var3));
      return var4 != null && var4.isCancelled();
   }

   protected void postPacket(Option<?, ?, ?> var1, @Nullable ApolloPlayer var2, @Nullable Object var3) {
   }
}
