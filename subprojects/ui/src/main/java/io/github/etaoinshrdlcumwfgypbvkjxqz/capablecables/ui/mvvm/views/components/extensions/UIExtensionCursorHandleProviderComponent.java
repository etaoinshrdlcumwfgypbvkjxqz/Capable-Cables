package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.mvvm.views.components.extensions;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.extensions.cursors.IUIExtensionCursorHandleProvider;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.IUIView;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.IUIViewContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIViewComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.modifiers.IUIComponentCursorHandleProviderModifier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.parsers.components.UIExtensionConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.structures.IUIComponentContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.mvvm.views.components.modifiers.NullUIComponentCursorHandleProviderModifier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CastUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.extensions.AbstractContainerAwareExtension;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.extensions.core.IExtensionType;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.inputs.IInputPointerDevice;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.INamespacePrefixedString;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.optionals.Optional2;

import java.util.Optional;

public class UIExtensionCursorHandleProviderComponent<E extends IUIViewComponent<?, ?>>
		extends AbstractContainerAwareExtension<INamespacePrefixedString, IUIView<?>, E>
		implements IUIExtensionCursorHandleProvider {
	@UIExtensionConstructor(type = UIExtensionConstructor.EnumConstructorType.CONTAINER_CLASS)
	public UIExtensionCursorHandleProviderComponent(Class<E> containerClass) {
		super(CastUtilities.castUnchecked(IUIView.class), // COMMENT generics should not matter in this case
				containerClass);
	}

	@Override
	public Optional<Long> getCursorHandle(IUIViewContext context) {
		return Optional2.of(getContainer().orElse(null), context.getInputDevices().getPointerDevice().orElse(null))
				.flatMap(values -> {
					E view = values.getValue1Nonnull();
					IInputPointerDevice pointerDevice = values.getValue2Nonnull();
					return IUIViewComponent.StaticHolder.createComponentContextWithManager(view, context)
							.flatMap(componentContext -> {
								try (IUIComponentContext cCtx = componentContext) {
									Optional<Long> ret = Optional.empty();
									view.getPathResolver().resolvePath(cCtx, pointerDevice.getPositionView());
									while (!cCtx.getPath().isEmpty()) {
										IUIComponent component = cCtx.getPath().getPathEnd().orElseThrow(AssertionError::new);
										IUIComponentCursorHandleProviderModifier componentAsModifier =
												CastUtilities.castChecked(IUIComponentCursorHandleProviderModifier.class, component)
														.orElseGet(NullUIComponentCursorHandleProviderModifier::getInstance);
										ret = IUIComponentCursorHandleProviderModifier.StaticHolder
												.handleComponentModifiers(componentAsModifier,
														component.getModifiersView(),
														cCtx);
										if (ret.isPresent())
											break;
										cCtx.getMutator().pop(cCtx);
									}
									return ret;
								}
							});
				});
	}

	@Override
	public IExtensionType<INamespacePrefixedString, ?, IUIView<?>> getType() { return TYPE.getValue(); }
}
