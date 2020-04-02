package $group__.$modId__.utilities.helpers;

import javax.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Function;

import static $group__.$modId__.utilities.helpers.Assertions.assertNonnull;
import static $group__.$modId__.utilities.helpers.Capacities.INITIAL_CAPACITY_3;
import static $group__.$modId__.utilities.helpers.specific.Optionals.unboxOptional;

public enum Recursions {
	/* MARK empty */;


	/* SECTION static methods */

	public static <T, R> Optional<R> recurseAsDepthFirstLoop(T obj, Function<? super T, ? extends R> function,
	                                                         Function<? super T, ? extends Iterable<? extends T>> splitter, @Nullable Function<? super Deque<R>, ? extends R> combiner) {
		ArrayDeque<T> stackT = new ArrayDeque<>(INITIAL_CAPACITY_3);
		@Nullable ArrayDeque<R> listR = combiner == null ? null : new ArrayDeque<>(INITIAL_CAPACITY_3);
		stackT.push(obj);

		HashSet<T> discovered = new HashSet<>(INITIAL_CAPACITY_3);
		while (!stackT.isEmpty()) {
			T t = stackT.pop();
			if (discovered.add(t)) {
				R r = function.apply(t);
				if (listR != null) listR.add(r);
				splitter.apply(t).forEach(stackT::push);
			}
		}

		return listR == null ? Optional.empty() : Optional.ofNullable(combiner.apply(listR));
	}

	@Nullable
	public static <T, R> R recurseAsDepthFirstLoopUnboxed(T obj, Function<? super T, ? extends R> function, Function<?
			super T, ? extends Iterable<? extends T>> splitter, @Nullable Function<? super Deque<R>, ? extends R> combiner) { return unboxOptional(recurseAsDepthFirstLoop(obj, function, splitter, combiner)); }

	public static <T, R> R recurseAsDepthFirstLoopUnboxedNonnull(T obj, Function<? super T, ? extends R> function,
	                                                             Function<? super T, ? extends Iterable<? extends T>> splitter, @Nullable Function<? super Deque<R>, ? extends R> combiner) { return assertNonnull(recurseAsDepthFirstLoopUnboxed(obj, function, splitter, combiner)); }


	public static <T, R> Optional<R> recurseAsBreadthFirstLoop(T obj, Function<? super T, ? extends R> function,
	                                                           Function<? super T, ? extends Iterable<? extends T>> splitter, @Nullable Function<? super Deque<R>, ? extends R> combiner) {
		ArrayDeque<T> stackT = new ArrayDeque<>(INITIAL_CAPACITY_3);
		@Nullable ArrayDeque<R> listR = combiner == null ? null : new ArrayDeque<>(INITIAL_CAPACITY_3);
		stackT.add(obj);

		HashSet<T> discovered = new HashSet<>(INITIAL_CAPACITY_3);
		while (!stackT.isEmpty()) {
			T t = stackT.poll();
			if (discovered.add(t)) {
				R r = function.apply(t);
				if (listR != null) listR.add(r);
				splitter.apply(t).forEach(stackT::addLast);
			}
		}

		return listR == null ? Optional.empty() : Optional.ofNullable(combiner.apply(listR));
	}

	@Nullable
	public static <T, R> R recurseAsBreadthFirstLoopUnboxed(T obj, Function<? super T, ? extends R> function,
	                                                        Function<? super T, ? extends Iterable<? extends T>> splitter, @Nullable Function<? super Deque<R>, ? extends R> combiner) { return unboxOptional(recurseAsBreadthFirstLoop(obj, function, splitter, combiner)); }

	public static <T, R> R recurseAsBreadthFirstLoopUnboxedNonnull(T obj, Function<? super T, ? extends R> function,
	                                                               Function<? super T,
			                                                               ? extends Iterable<? extends T>> splitter,
	                                                               @Nullable Function<? super Deque<R>, ? extends R> combiner) { return assertNonnull(recurseAsBreadthFirstLoopUnboxed(obj, function, splitter, combiner)); }
}
