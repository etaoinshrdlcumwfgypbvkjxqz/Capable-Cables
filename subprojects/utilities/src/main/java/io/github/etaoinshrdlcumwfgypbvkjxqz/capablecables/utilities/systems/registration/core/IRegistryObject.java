package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.registration.core;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.ICompatibilitySupplier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.interfaces.ISealedClassCandidate;

import javax.annotation.Nonnull;
import java.io.Serializable;

@SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
public interface IRegistryObject<V>
		extends ICompatibilitySupplier<V>, Serializable, ISealedClassCandidate {
	@Nonnull
	@Override
	default V get() { return getValue(); }

	V getValue();
}
