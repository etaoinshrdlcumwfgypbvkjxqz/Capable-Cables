package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl;

import com.google.common.collect.ImmutableSet;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.IBinding;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.IBindingAction;

public final class ImmutableBindingAction implements IBindingAction {
	private final EnumActionType type;
	private final Iterable<? extends IBinding<?>> bindings;

	private ImmutableBindingAction(EnumActionType type, Iterable<? extends IBinding<?>> bindings) {
		this.type = type;
		this.bindings = ImmutableSet.copyOf(bindings);
	}

	public static ImmutableBindingAction bind(Iterable<? extends IBinding<?>> bindings) {
		return of(EnumActionType.BIND, bindings);
	}

	private static ImmutableBindingAction of(EnumActionType type, Iterable<? extends IBinding<?>> bindings) {
		return new ImmutableBindingAction(type, bindings);
	}

	public static ImmutableBindingAction unbind(Iterable<? extends IBinding<?>> bindings) {
		return of(EnumActionType.UNBIND, bindings);
	}

	@Override
	public EnumActionType getActionType() { return type; }

	@Override
	public Iterable<? extends IBinding<?>> getBindings() { return bindings; }
}
