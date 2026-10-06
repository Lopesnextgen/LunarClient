package com.moonsworth.lunar.client.highlight;

/**
 * Compatibility base for the stale-jar event dispatch API.
 *
 * <p>The jar's {@code Highlight3Iterator_3.handle(Class&lt;T extends Highlight&gt;, Consumer&lt;T&gt;)}
 * is bound to this FQN, so every Lunar event class must be a subtype of it.
 * The source-side event base is {@link com.moonsworth.lunar.client.event.LunarEvent},
 * which extends this class. Drop this shim once the event API is rebuilt from
 * source (restructure/rescue phase).</p>
 */
public class Highlight {
   public Highlight() {
   }
}
