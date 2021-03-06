package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.dynamic;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Immutable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CapacityUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CastUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.impl.ThrowableUtilities;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public enum ClassUtilities {
	;

	private static final MethodHandle DEFINE_CLASS_METHOD_HANDLE;

	static {
		try {
			DEFINE_CLASS_METHOD_HANDLE = InvokeUtilities.getImplLookup().findVirtual(ClassLoader.class, "defineClass",
					MethodType.methodType(Class.class, String.class, byte[].class, int.class, int.class));
		} catch (NoSuchMethodException | IllegalAccessException e) {
			throw ThrowableUtilities.propagate(e);
		}
	}

	public static <U> List<Class<? extends U>> getThisAndSuperclasses(Class<? extends U> clazz) { return getLowerAndIntermediateSuperclasses(clazz, null); }

	public static <U> List<Class<? extends U>> getLowerAndIntermediateSuperclasses(@Nullable Class<? extends U> lower, @Nullable Class<U> upper) {
		ImmutableList.Builder<Class<? extends U>> r = ImmutableList.builder();
		for (@Nullable Class<?> i = lower;
		     !Objects.equals(i, upper) && i != null;
		     i = i.getSuperclass())
			r.add(CastUtilities.<Class<? extends U>>castUnchecked(i));
		return r.build();
	}

	public static Spliterator<Field> getAllFields(Class<?> clazz) {
		return getThisAndSuperclassesAndInterfaces(clazz).stream()
				.flatMap(Collection::stream)
				.map(Class::getDeclaredFields)
				.flatMap(Arrays::stream)
				.spliterator();
	}

	public static List<Set<Class<?>>> getThisAndSuperclassesAndInterfaces(Class<?> type) {
		return ImmutableList.<Set<Class<?>>>builder().add(ImmutableSet.of(type)).addAll(getSuperclassesAndInterfaces(type)).build();
	}

	@SuppressWarnings({"ObjectAllocationInLoop", "UnstableApiUsage"})
	public static List<Set<Class<?>>> getSuperclassesAndInterfaces(Class<?> clazz) {
		List<Set<Class<?>>> ret = new ArrayList<>(CapacityUtilities.getInitialCapacitySmall());

		@Immutable List<Class<?>> superclasses = getSuperclasses(clazz);
		ret.add(ImmutableSet.copyOf(superclasses));
		AtomicReference<Set<Class<?>>> currentLayerInterfaces =
				new AtomicReference<>(ImmutableSet.copyOf(clazz.getInterfaces()));
		superclasses.stream()
				.map(Class::getInterfaces)
				.map(ImmutableList::copyOf)
				.forEachOrdered(superclassInterfaces ->
						ret.add(ImmutableSet.copyOf(AssertionUtilities.assertNonnull(
								currentLayerInterfaces.getAndUpdate(currentLayerInterfaces2 -> {
									ImmutableSet.Builder<Class<?>> nextLayerInterfaces = ImmutableSet.builder();
									nextLayerInterfaces.addAll(superclassInterfaces);
									currentLayerInterfaces2.stream()
											.map(Class::getInterfaces)
											.flatMap(Arrays::stream)
											.forEachOrdered(nextLayerInterfaces::add);
									return nextLayerInterfaces.build();
								})))));
		while (!AssertionUtilities.assertNonnull(currentLayerInterfaces.get())
				.isEmpty()) {
			ret.add(ImmutableSet.copyOf(AssertionUtilities.assertNonnull(
					currentLayerInterfaces.getAndUpdate(currentLayerInterfaces2 ->
							currentLayerInterfaces2.stream()
									.map(Class::getInterfaces)
									.flatMap(Arrays::stream)
									.collect(ImmutableSet.toImmutableSet())))));
		}

		ret.removeIf(Collection::isEmpty);
		return ImmutableList.copyOf(ret);
	}

	public static <U> List<Class<? extends U>> getSuperclasses(@Nullable Class<? extends U> clazz) { return getIntermediateSuperclasses(clazz, null); }

	public static <U> List<Class<? extends U>> getIntermediateSuperclasses(@Nullable Class<? extends U> lower,
	                                                                       @Nullable Class<U> upper) {
		return getLowerAndIntermediateSuperclasses(
				CastUtilities.castUncheckedNullable(
						Optional.ofNullable(lower)
								.map(Class::getSuperclass)
								.orElse(null)), upper);
	}

	public static Optional<Field> getAnyField(Class<?> clazz, CharSequence name) {
		String name2 = name.toString();
		return ClassUtilities.getThisAndSuperclassesAndInterfaces(clazz).stream()
				.flatMap(Collection::stream)
				.map(clazz1 -> {
					try {
						return clazz1.getDeclaredField(name2);
					} catch (NoSuchFieldException e) {
						return null;
					}
				})
				.filter(Objects::nonNull)
				.findFirst();
	}

	public static Optional<Method> getAnyMethod(Class<?> clazz, CharSequence name, Class<?>... parameterTypes) {
		String name2 = name.toString();
		return ClassUtilities.getThisAndSuperclassesAndInterfaces(clazz).stream()
				.flatMap(Collection::stream)
				.map(clazz1 -> {
					try {
						return clazz1.getDeclaredMethod(name2, parameterTypes);
					} catch (NoSuchMethodException e) {
						return null;
					}
				})
				.filter(Objects::nonNull)
				.findFirst();
	}

	public static Spliterator<Method> getAllMethods(Class<?> clazz) {
		return getThisAndSuperclassesAndInterfaces(clazz).stream()
				.flatMap(Collection::stream)
				.map(Class::getDeclaredMethods)
				.flatMap(Arrays::stream)
				.spliterator();
	}

	@SuppressWarnings("cast")
	public static Class<?> defineClass(ClassLoader classLoader, CharSequence name, byte[] data) {
		// TODO Java 9 - use Lookup.defineClass
		try {
			return (Class<?>) getDefineClassMethodHandle().invokeExact((ClassLoader) classLoader, (String) name.toString(), (byte[]) data, (int) 0, (int) data.length);
		} catch (Throwable throwable) {
			throw ThrowableUtilities.propagate(throwable);
		}
	}

	private static MethodHandle getDefineClassMethodHandle() {
		return DEFINE_CLASS_METHOD_HANDLE;
	}

	public static String getPackage(Class<?> clazz) {
		String r = clazz.getName().replace(clazz.getSimpleName(), "");
		if (r.endsWith(".")) r = r.substring(0, r.length() - 1);
		return r;
	}

	public static boolean isClassAbstract(Class<?> clazz) { return clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()); }
}
