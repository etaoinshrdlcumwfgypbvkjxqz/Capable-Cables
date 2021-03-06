package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.registration.def;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.def.tuples.ITuple2;

import java.util.Optional;

public interface ITuple2Registry<KL, KR, V>
		extends IRegistry<ITuple2<? extends KL, ? extends KR>, V> {
	Optional<? extends IRegistryObject<? extends V>> getWithLeft(KL key);

	Optional<? extends IRegistryObject<? extends V>> getWithRight(KR key);
}
