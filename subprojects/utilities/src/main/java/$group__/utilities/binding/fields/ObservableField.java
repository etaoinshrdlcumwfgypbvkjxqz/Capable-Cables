package $group__.utilities.binding.fields;

import $group__.utilities.binding.core.fields.IObservableField;
import $group__.utilities.interfaces.IHasGenericClass;
import $group__.utilities.interfaces.IValue;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class ObservableField<T>
		extends IHasGenericClass.Impl<T>
		implements IObservableField<T> {
	protected final Subject<IValue<T>> notifierSubject = PublishSubject.create();
	@Nullable
	protected T value;

	public ObservableField(Class<T> genericClass, @Nullable T value) {
		super(genericClass);
		this.value = value;
	}

	@Override
	public ObservableSource<? extends IValue<T>> getNotifier() { return getNotifierSubject(); }

	protected Subject<IValue<T>> getNotifierSubject() { return notifierSubject; }

	@Override
	public void setValue(@Nullable T value) {
		if (!Objects.equals(getValue().orElse(null), value)) {
			this.value = value;
			getNotifierSubject().onNext(IValue.of(value));
		}
	}

	@Override
	public Optional<? extends T> getValue() { return Optional.ofNullable(value); }
}