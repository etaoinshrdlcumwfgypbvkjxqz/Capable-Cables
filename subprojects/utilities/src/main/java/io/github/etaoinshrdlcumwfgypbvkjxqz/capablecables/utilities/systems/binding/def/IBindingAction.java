package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def;

import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IBindingAction
		extends Consumer<IBinder> {
	EnumActionType getActionType();

	Iterable<? extends IBinding<?>> getBindings();

	@Override
	default void accept(IBinder binder) {
		getActionType().accept(binder, getBindings().iterator());
	}

	enum EnumActionType
			implements BiConsumer<IBinder, Iterator<? extends IBinding<?>>> {
		BIND {
			@Override
			public void accept(IBinder binder, Iterator<? extends IBinding<?>> bindings) {
				binder.bind(bindings);
			}
		},
		UNBIND {
			@Override
			public void accept(IBinder binder, Iterator<? extends IBinding<?>> bindings) {
				binder.unbind(bindings);
			}
		},
	}
}
