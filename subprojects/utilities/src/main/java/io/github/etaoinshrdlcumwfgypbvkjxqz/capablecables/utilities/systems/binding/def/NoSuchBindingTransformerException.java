package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;

import java.util.Optional;

public class NoSuchBindingTransformerException
		extends IllegalArgumentException {
	private static final long serialVersionUID = 7374170655174983888L;

	public NoSuchBindingTransformerException() {}

	public NoSuchBindingTransformerException(@Nullable CharSequence message) {
		super(Optional.ofNullable(message).map(CharSequence::toString).orElse(null));
	}
}
