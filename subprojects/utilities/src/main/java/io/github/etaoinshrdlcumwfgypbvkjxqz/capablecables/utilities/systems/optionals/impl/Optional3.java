package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.optionals.impl;

import com.google.common.collect.ImmutableList;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.def.*;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.optionals.def.ICompositeOptionalValues;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;

public final class Optional3<V1, V2, V3>
		extends AbstractCompositeOptional<Optional3<V1, V2, V3>, Optional3.Values<V1, V2, V3>> {
	private static final Optional3<?, ?, ?> EMPTY = new Optional3<>();
	private final Values<V1, V2, V3> values;

	private Optional3(V1 value1, V2 value2, V3 value3) {
		this.values = new Values<>(value1, value2, value3);
	}

	private Optional3() {
		this.values = new Values<>(null, null, null);
	}

	public static <V1, V2, V3> Optional3<V1, V2, V3> of(@Nullable V1 value1, @Nullable V2 value2, @Nullable V3 value3) {
		return value1 == null || value2 == null || value3 == null ? getEmpty() : new Optional3<>(value1, value2, value3);
	}

	@SuppressWarnings("unchecked")
	public static <V1, V2, V3> Optional3<V1, V2, V3> getEmpty() { return (Optional3<V1, V2, V3>) EMPTY; }

	public static <V1, V2, V3> Optional3<V1, V2, V3> of(Supplier<@Nullable ? extends V1> value1Supplier,
	                                                    Supplier<@Nullable ? extends V2> value2Supplier,
	                                                    Supplier<@Nullable ? extends V3> value3Supplier) {
		@Nullable V1 value1;
		@Nullable V2 value2;
		@Nullable V3 value3;
		return (value1 = value1Supplier.get()) == null || (value2 = value2Supplier.get()) == null || (value3 = value3Supplier.get()) == null
				? getEmpty()
				: new Optional3<>(value1, value2, value3);
	}

	@Override
	protected Optional3<V1, V2, V3> getThis() { return this; }

	@Override
	protected Optional3<V1, V2, V3> getStaticEmpty() { return getEmpty(); }

	@Override
	protected Values<V1, V2, V3> getValues() { return values; }

	/* SECTION specialized */

	public <EX extends Throwable> void ifPresent(IConsumer3<@Nonnull ? super V1, @Nonnull ? super V2, @Nonnull ? super V3, ? extends EX> consumer)
			throws EX {
		ifPresent(IThrowingConsumer.executeNow(values ->
				consumer.accept(values.getValue1Nonnull(), values.getValue2Nonnull(), values.getValue3Nonnull())
		));
	}

	public <EX extends Throwable> Optional3<V1, V2, V3> filter(IFunction3<@Nonnull ? super V1, @Nonnull ? super V2, @Nonnull ? super V3, ? extends Boolean, ? extends EX> predicate)
			throws EX {
		return filter(IThrowingPredicate.executeNow(values ->
				predicate.apply(values.getValue1Nonnull(), values.getValue2Nonnull(), values.getValue3Nonnull())
		));
	}

	public <R, EX extends Throwable> Optional<R> map(IFunction3<@Nonnull ? super V1, @Nonnull ? super V2, @Nonnull ? super V3, @Nullable ? extends R, ? extends EX> mapper)
			throws EX {
		return map(IThrowingFunction.executeNow(
				values -> mapper.apply(values.getValue1Nonnull(), values.getValue2Nonnull(), values.getValue3Nonnull())
		));
	}

	public <R, EX extends Throwable> Optional<R> flatMap(IFunction3<@Nonnull ? super V1, @Nonnull ? super V2, @Nonnull ? super V3, @Nonnull ? extends Optional<? extends R>, ? extends EX> mapper)
			throws EX {
		return flatMap(IThrowingFunction.executeNow(
				values -> mapper.apply(values.getValue1Nonnull(), values.getValue2Nonnull(), values.getValue3Nonnull())
		));
	}

	/* SECTION END specialized */

	public static final class Values<V1, V2, V3>
			implements ICompositeOptionalValues {
		@Nullable
		private final V1 value1;
		@Nullable
		private final V2 value2;
		@Nullable
		private final V3 value3;

		private Values(@Nullable V1 value1, @Nullable V2 value2, @Nullable V3 value3) {
			this.value1 = value1;
			this.value2 = value2;
			this.value3 = value3;
		}

		public V1 getValue1Nonnull() { return AssertionUtilities.assertNonnull(getValue1()); }

		@Nullable
		protected V1 getValue1() { return value1; }

		public V2 getValue2Nonnull() { return AssertionUtilities.assertNonnull(getValue2()); }

		@Nullable
		protected V2 getValue2() { return value2; }

		public V3 getValue3Nonnull() { return AssertionUtilities.assertNonnull(getValue3()); }

		@Nullable
		protected V3 getValue3() { return value3; }

		@Override
		public Iterator<? extends Supplier<@Nullable ?>> getSuppliers() {
			return ImmutableList.<Supplier<@Nullable ?>>of(this::getValue1, this::getValue2, this::getValue3).iterator();
		}
	}
}
