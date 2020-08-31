package $group__.utilities.functions;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Supplier;

public final class ConstantSupplier<T>
		implements Supplier<T> {
	private static final ConstantSupplier<?> EMPTY = new ConstantSupplier<>();
	@Nullable
	protected final T constant;

	protected ConstantSupplier() { this.constant = null; }

	protected ConstantSupplier(T constant) { this.constant = Objects.requireNonNull(constant); }

	public static <T> ConstantSupplier<T> ofNullable(@Nullable T constant) { return constant == null ? empty() : of(constant); }

	@SuppressWarnings("unchecked")
	public static <T> ConstantSupplier<T> empty() {
		return (ConstantSupplier<T>) EMPTY; // COMMENT always safe, returns null
	}

	public static <T> ConstantSupplier<T> of(T constant) { return new ConstantSupplier<>(constant); }

	@Override
	@Nullable
	public T get() { return getConstant(); }

	@Nullable
	protected T getConstant() { return constant; }
}
