package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.events.impl;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.reactive.ReifiedSubscriber;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.events.def.ISubscribeEventProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.reactivestreams.Subscriber;

import java.util.Optional;

public abstract class EventBusSubscriber<T>
		extends ReifiedSubscriber<T>
		implements ISubscribeEventProvider {
	private final SubscribeEvent subscribeEvent;

	protected EventBusSubscriber(@Nullable SubscribeEvent subscribeEvent, Subscriber<? super T> delegate) {
		super(delegate);
		this.subscribeEvent = subscribeEvent;
	}

	@SuppressWarnings("unused")
	protected EventBusSubscriber(Subscriber<? super T> delegate) {
		this(null, delegate);
	}

	@Override
	public Optional<? extends SubscribeEvent> getSubscribeEvent() {
		return Optional.ofNullable(subscribeEvent);
	}
}
