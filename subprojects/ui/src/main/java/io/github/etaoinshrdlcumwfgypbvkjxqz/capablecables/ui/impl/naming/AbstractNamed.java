package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.naming;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.naming.INamed;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.interfaces.IRecordCandidate;
import org.jetbrains.annotations.NonNls;

import java.util.Optional;

public abstract class AbstractNamed
		implements INamed, IRecordCandidate {
	@Nullable
	private final String name;

	public AbstractNamed(@NonNls @Nullable CharSequence name) {
		this.name = Optional.ofNullable(name).map(CharSequence::toString).orElse(null);
	}

	@Override
	public Optional<? extends String> getName() { return Optional.ofNullable(name); }
}
