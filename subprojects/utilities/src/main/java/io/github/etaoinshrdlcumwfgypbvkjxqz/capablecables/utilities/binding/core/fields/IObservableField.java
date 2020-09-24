package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.binding.core.fields;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.interfaces.IHasGenericClass;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.interfaces.IValue;
import io.reactivex.rxjava3.core.ObservableSource;

public interface IObservableField<T> extends IField<T>, IHasGenericClass<T> {
	ObservableSource<? extends IValue<T>> getNotifier();
}