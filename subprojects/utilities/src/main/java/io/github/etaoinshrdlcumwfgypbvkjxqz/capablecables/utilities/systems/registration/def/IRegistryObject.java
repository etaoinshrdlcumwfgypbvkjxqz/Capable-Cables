package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.registration.def;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.def.ICompatibilitySupplier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.interfaces.ISealedClassCandidate;

import java.io.Serializable;

@SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
public interface IRegistryObject<V>
		extends ICompatibilitySupplier<V>, Serializable, ISealedClassCandidate {
	long serialVersionUID = 7285998289519574757L;

	@Nonnull
	@Override
	@Deprecated
	default V get() { return getValue(); }

	V getValue();
}
