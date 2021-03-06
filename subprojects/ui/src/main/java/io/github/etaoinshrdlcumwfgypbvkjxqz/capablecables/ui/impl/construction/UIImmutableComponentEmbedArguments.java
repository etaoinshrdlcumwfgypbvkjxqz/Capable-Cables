package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.construction;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Immutable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.binding.IUIPropertyMappingValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.IUIComponentArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.IUIComponentEmbedArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.IUIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.shapes.descriptors.IShapeDescriptor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AbstractDelegatingObject;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.def.IIdentifier;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public final class UIImmutableComponentEmbedArguments
		extends AbstractDelegatingObject<IUIComponentArguments>
		implements IUIComponentEmbedArguments {
	private final Function<@Nonnull ? super IUIComponentArguments, @Nonnull ? extends IUIComponent> constructor;

	private UIImmutableComponentEmbedArguments(Function<@Nonnull ? super IUIComponentArguments, @Nonnull ? extends IUIComponent> constructor,
	                                           IUIComponentArguments delegate) {
		super(delegate);
		this.constructor = constructor;
	}

	public static UIImmutableComponentEmbedArguments of(Function<@Nonnull ? super IUIComponentArguments, @Nonnull ? extends IUIComponent> constructor,
	                                                    IUIComponentArguments delegate) {
		return new UIImmutableComponentEmbedArguments(constructor, delegate);
	}

	@Override
	public Function<@Nonnull ? super IUIComponentArguments, @Nonnull ? extends IUIComponent> getConstructor() {
		return constructor;
	}

	@Override
	public IShapeDescriptor<?> getShapeDescriptor() {
		return getDelegate().getShapeDescriptor();
	}

	@Override
	public Optional<? extends String> getRendererName() {
		return getDelegate().getRendererName();
	}

	@Override
	public @Immutable Map<IIdentifier, ? extends IUIPropertyMappingValue> getMappingsView() {
		return getDelegate().getMappingsView();
	}

	@Override
	public @Immutable Map<String, ? extends IEmbedPrototype> getEmbedArgumentsView() {
		return getDelegate().getEmbedArgumentsView();
	}

	@Override
	public IUIComponentArguments withMappings(Map<? extends IIdentifier, ? extends IUIPropertyMappingValue> mappings) {
		return of(getConstructor(),
				getDelegate().withMappings(mappings));
	}

	@Override
	public Optional<IUIComponentEmbedArguments> tryComputeEmbedArgument(CharSequence key, Function<@Nonnull ? super IUIComponentArguments, @Nonnull ? extends IUIComponent> constructor, IShapeDescriptor<?> shapeDescriptor) {
		return getDelegate().tryComputeEmbedArgument(key, constructor, shapeDescriptor);
	}

	@Override
	public IUIComponentEmbedArguments computeEmbedArgument(CharSequence key, Function<@Nonnull ? super IUIComponentArguments, @Nonnull ? extends IUIComponent> constructor, IShapeDescriptor<?> shapeDescriptor) {
		return getDelegate().computeEmbedArgument(key, constructor, shapeDescriptor);
	}

	@Override
	public Optional<? extends String> getName() {
		return getDelegate().getName();
	}
}
