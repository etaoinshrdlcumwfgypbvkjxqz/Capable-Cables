package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.binding.fields;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.binding.core.fields.IBindingField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.binding.core.fields.IObservableField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.core.INamespacePrefixedString;

import javax.annotation.Nullable;
import java.util.Optional;

public final class ImmutableBindingField<T>
		implements IBindingField<T> {
	@Nullable
	private final INamespacePrefixedString bindingKey;
	private final IObservableField<T> field;

	public ImmutableBindingField(@Nullable INamespacePrefixedString bindingKey, IObservableField<T> field) {
		this.bindingKey = bindingKey;
		this.field = field;
	}

	@Override
	public IObservableField<T> getField() { return field; }

	@Override
	public Optional<? extends INamespacePrefixedString> getBindingKey() { return Optional.ofNullable(bindingKey); }
}