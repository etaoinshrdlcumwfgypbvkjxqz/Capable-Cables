package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions;

@FunctionalInterface
public interface IConsumer4<T1, T2, T3, T4, T extends Throwable> {
	default IConsumer4<T1, T2, T3, T4, T> andThen(IConsumer4<? super T1, ? super T2, ? super T3, ? super T4, ? extends T> after) {
		return (t1, t2, t3, t4) -> {
			accept(t1, t2, t3, t4);
			after.accept(t1, t2, t3, t4);
		};
	}

	void accept(T1 t1, T2 t2, T3 t3, T4 t4)
			throws T;
}