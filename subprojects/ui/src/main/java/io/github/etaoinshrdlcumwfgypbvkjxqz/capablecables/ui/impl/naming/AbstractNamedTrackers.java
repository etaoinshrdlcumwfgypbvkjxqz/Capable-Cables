package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.naming;

import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Immutable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.naming.DuplicateNameException;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.naming.INamed;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.naming.INamedTracker;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.naming.INamedTrackers;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
public abstract class AbstractNamedTrackers
		implements INamedTrackers {
	@Override
	public <E extends INamed> boolean add(Class<E> clazz, Iterator<? extends E> elements) throws DuplicateNameException {
		return getTracker(clazz).add(elements);
	}

	@Override
	public <E extends INamed> boolean remove(Class<E> clazz, Iterator<? extends E> elements) {
		boolean result = getTracker(clazz).remove(elements);
		getData().cleanUp();
		return result;
	}

	@Override
	public <E extends INamed> Optional<? extends E> get(Class<E> clazz, CharSequence element) {
		return getTracker(clazz).get(element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E extends INamed> INamedTracker<E> getTracker(Class<E> clazz) { return (INamedTracker<E>) getData().getUnchecked(clazz); }

	@Override
	public long size() { return getData().size(); }

	@Override
	public boolean isEmpty() { return size() == 0; }

	@Override
	public @Immutable Map<Class<? extends INamed>, INamedTracker<?>> asMapView() { return ImmutableMap.copyOf(getData().asMap()); }

	protected abstract LoadingCache<Class<? extends INamed>, INamedTracker<?>> getData();
}
