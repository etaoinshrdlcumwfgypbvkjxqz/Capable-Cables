package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.shapes.descriptors.IShapeDescriptor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.impl.FunctionUtilities;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.function.Predicate;

@SuppressWarnings("InterfaceMayBeAnnotatedFunctional")
public interface IUIReshapeExplicitly<S extends Shape> {
	@SuppressWarnings("UnusedReturnValue")
	static boolean refresh(IUIReshapeExplicitly<?> trait) { return trait.reshape(FunctionUtilities.getAlwaysTruePredicate()); }

	boolean reshape(Predicate<@Nonnull ? super IShapeDescriptor<? super S>> action)
			throws ConcurrentModificationException;
}
