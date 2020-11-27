package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.core;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.impl.ThrowableUtilities;

@FunctionalInterface
public interface IThrowingRunnable<TH extends Throwable> {
	@SuppressWarnings("RedundantThrows")
	static <TH extends Throwable> Runnable executeNow(IThrowingRunnable<TH> lambda)
			throws TH {
		return () -> {
			try {
				lambda.run();
			} catch (Throwable th) {
				throw ThrowableUtilities.propagateUnverified(th);
			}
		};
	}

	void run() throws TH;
}