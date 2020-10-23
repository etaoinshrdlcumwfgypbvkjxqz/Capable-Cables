package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.impl;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.ObjectUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.core.INamespacePrefixedString;
import org.jetbrains.annotations.NonNls;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class ImmutableNamespacePrefixedString
		implements INamespacePrefixedString {
	private final String namespace;
	private final String path;

	private ImmutableNamespacePrefixedString(@NonNls CharSequence namespace, @NonNls CharSequence path) {
		this.namespace = namespace.toString();
		this.path = path.toString();
	}

	public static ImmutableNamespacePrefixedString of(@NonNls CharSequence string) {
		String[] decomposed = INamespacePrefixedString.decompose(string);
		return of(decomposed[0], decomposed[1]);
	}

	public static ImmutableNamespacePrefixedString of(@NonNls CharSequence namespace, @NonNls CharSequence path) {
		return new ImmutableNamespacePrefixedString(namespace, path);
	}

	@Override
	public @NonNls String getNamespace() { return namespace; }

	@Override
	public @NonNls String getPath() { return path; }

	@Override
	public int hashCode() { return ObjectUtilities.hashCode(this, null, StaticHolder.getObjectVariables()); }

	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	@Override
	public boolean equals(Object obj) { return ObjectUtilities.equals(this, INamespacePrefixedString.class, obj, true, null, StaticHolder.getObjectVariables()); }

	@Override
	public String toString() { return ObjectUtilities.toString(this, super::toString, StaticHolder.getObjectVariablesMap()); }
}