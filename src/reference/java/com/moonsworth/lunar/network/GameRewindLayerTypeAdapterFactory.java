package com.moonsworth.lunar.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
public class GameRewindLayerTypeAdapterFactory implements TypeAdapterFactory {
   public GameRewindLayerTypeAdapterFactory() {
   }

   public <T> TypeAdapter<T> create(Gson gson1, TypeToken<T> typetoken2) {
      if (!GameRewindLayer.class.isAssignableFrom(typetoken2.getRawType())) {
         return null;
      }

      TypeAdapter typeadapter3 = gson1.getAdapter(JsonElement.class);
      TypeAdapter typeadapter4 = gson1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerAudio.class));
      TypeAdapter typeadapter5 = gson1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerGameplay.class));
      TypeAdapter typeadapter6 = gson1.getDelegateAdapter(this, TypeToken.get(GameRewindLayerEffect.class));
      return new Data$1(this, typeadapter3, typeadapter4, typeadapter5, typeadapter6).nullSafe();
   }
}
