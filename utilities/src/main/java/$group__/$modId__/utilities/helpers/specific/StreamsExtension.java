package $group__.$modId__.utilities.helpers.specific;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum StreamsExtension {
	/* MARK empty */;


	/* SECTION static variables */

	// COMMENT from http://gee.cs.oswego.edu/dl/html/StreamParallelGuidance.html
	public static final int THRESHOLD_PARALLEL = 10000;


	/* SECTION static methods */

	// COMMENT from http://gee.cs.oswego.edu/dl/html/StreamParallelGuidance.html
	public static <T extends Stream<?>> T streamSmart(int size, int cost, Supplier<? extends T> sequential, Supplier<?
			extends T> parallel) { return size * cost >= THRESHOLD_PARALLEL ? parallel.get() : sequential.get(); }

	public static <T> Stream<T> streamSmart(Collection<T> collection, int cost) { return streamSmart(collection.size()
			, cost, collection::stream, collection::parallelStream); }
}