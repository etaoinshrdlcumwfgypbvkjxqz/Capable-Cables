package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.minecraft.mvvm.views.components.impl;

import com.google.common.collect.ImmutableList;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.binding.IUIPropertyMappingValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.binding.UIProperty;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.IUIComponentArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.IUIRendererArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.UIComponentConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.UIRendererConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIComponentContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.rendering.IUIRendererContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.rendering.IUIRendererContainerContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.graphics.AutoCloseableGraphics2D;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.minecraft.core.mvvm.views.components.IUIComponentMinecraft;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.minecraft.core.mvvm.views.rendering.IUIMinecraftComponentRenderer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.minecraft.mvvm.views.components.rendering.UIDefaultMinecraftComponentRenderer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.impl.UIButtonComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.rendering.UIDefaultRendererContainerContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.core.INamespacePrefixedString;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.impl.ImmutableNamespacePrefixedString;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.core.IBinderAction;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.core.fields.IBindingField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.core.traits.IHasBindingKey;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.BindingUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.ImmutableBinderAction;
import io.reactivex.rxjava3.observers.DisposableObserver;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NonNls;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class UIMinecraftButtonComponent
		extends UIButtonComponent
		implements IUIComponentMinecraft {
	@SuppressWarnings({"ThisEscapedInObjectConstruction", "unchecked", "rawtypes"})
	private final IUIRendererContainerContainer<IUIMinecraftComponentRenderer<?>> rendererContainerContainer =
			new UIDefaultRendererContainerContainer<>(this,
					UIDefaultRendererContainerContainer.createRendererContainerInitializer(DefaultRenderer.class));

	@UIComponentConstructor
	public UIMinecraftButtonComponent(IUIComponentArguments arguments) { super(arguments); }

	@Override
	public IUIRendererContainer<? extends IUIMinecraftComponentRenderer<?>> getRendererContainer()
			throws IllegalStateException {
		return getRendererContainerContainer().getRendererContainer();
	}

	protected IUIRendererContainerContainer<IUIMinecraftComponentRenderer<?>> getRendererContainerContainer() {
		return rendererContainerContainer;
	}

	@Override
	public void initializeRendererContainer(@NonNls CharSequence name)
			throws IllegalStateException {
		getRendererContainerContainer().initializeRendererContainer(name);
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void initializeBindings(Supplier<@Nonnull ? extends Optional<? extends DisposableObserver<IBinderAction>>> binderObserverSupplier) {
		super.initializeBindings(binderObserverSupplier);
		BindingUtilities.initializeBindings(
				ImmutableList.of(getRendererContainerContainer()),
				binderObserverSupplier
		);
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void cleanupBindings(Supplier<@Nonnull ? extends Optional<? extends DisposableObserver<IBinderAction>>> binderObserverSupplier) {
		super.cleanupBindings(binderObserverSupplier);
		BindingUtilities.cleanupBindings(
				ImmutableList.of(getRendererContainerContainer()),
				binderObserverSupplier
		);
	}

	@Override
	public void tick(IUIComponentContext context) {}

	@OnlyIn(Dist.CLIENT)
	public static class DefaultRenderer<C extends UIMinecraftButtonComponent>
			extends UIDefaultMinecraftComponentRenderer<C> {
		@NonNls
		public static final String PROPERTY_BASE_COLOR = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.button.base.color";
		@NonNls
		public static final String PROPERTY_BASE_BORDER_COLOR = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.button.base.border.color";
		@NonNls
		public static final String PROPERTY_HOVERING_COLOR = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.button.hovering.color";
		@NonNls
		public static final String PROPERTY_HOVERING_BORDER_COLOR = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.button.hovering.border.color";
		@NonNls
		public static final String PROPERTY_PRESSED_COLOR = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.button.pressed.color";
		@NonNls
		public static final String PROPERTY_PRESSED_BORDER_COLOR = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.button.pressed.border.color";

		private static final INamespacePrefixedString PROPERTY_BASE_COLOR_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyBaseColor());
		private static final INamespacePrefixedString PROPERTY_BASE_BORDER_COLOR_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyBaseBorderColor());
		private static final INamespacePrefixedString PROPERTY_HOVERING_COLOR_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyHoveringColor());
		private static final INamespacePrefixedString PROPERTY_HOVERING_BORDER_COLOR_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyHoveringBorderColor());
		private static final INamespacePrefixedString PROPERTY_PRESSED_COLOR_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyPressedColor());
		private static final INamespacePrefixedString PROPERTY_PRESSED_BORDER_COLOR_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyPressedBorderColor());

		@UIProperty(PROPERTY_BASE_COLOR)
		private final IBindingField<Color> baseColor;
		@UIProperty(PROPERTY_BASE_BORDER_COLOR)
		private final IBindingField<Color> baseBorderColor;
		@UIProperty(PROPERTY_HOVERING_COLOR)
		private final IBindingField<Color> hoveringColor;
		@UIProperty(PROPERTY_HOVERING_BORDER_COLOR)
		private final IBindingField<Color> hoveringBorderColor;
		@UIProperty(PROPERTY_PRESSED_COLOR)
		private final IBindingField<Color> pressedColor;
		@UIProperty(PROPERTY_PRESSED_BORDER_COLOR)
		private final IBindingField<Color> pressedBorderColor;

		@UIRendererConstructor
		public DefaultRenderer(IUIRendererArguments arguments) {
			super(arguments);

			Map<INamespacePrefixedString, IUIPropertyMappingValue> mappings = arguments.getMappingsView();
			this.baseColor = IUIPropertyMappingValue.createBindingField(Color.class, Color.DARK_GRAY,
					mappings.get(getPropertyBaseColorLocation()));
			this.baseBorderColor = IUIPropertyMappingValue.createBindingField(Color.class, Color.DARK_GRAY,
					mappings.get(getPropertyBaseBorderColorLocation()));
			this.hoveringColor = IUIPropertyMappingValue.createBindingField(Color.class, Color.GRAY,
					mappings.get(getPropertyHoveringColorLocation()));
			this.hoveringBorderColor = IUIPropertyMappingValue.createBindingField(Color.class, Color.GRAY,
					mappings.get(getPropertyHoveringBorderColorLocation()));
			this.pressedColor = IUIPropertyMappingValue.createBindingField(Color.class, Color.LIGHT_GRAY,
					mappings.get(getPropertyPressedColorLocation()));
			this.pressedBorderColor = IUIPropertyMappingValue.createBindingField(Color.class, Color.LIGHT_GRAY,
					mappings.get(getPropertyPressedBorderColorLocation()));
		}

		public static INamespacePrefixedString getPropertyBaseColorLocation() {
			return PROPERTY_BASE_COLOR_LOCATION;
		}

		public static INamespacePrefixedString getPropertyBaseBorderColorLocation() {
			return PROPERTY_BASE_BORDER_COLOR_LOCATION;
		}

		public static INamespacePrefixedString getPropertyHoveringColorLocation() {
			return PROPERTY_HOVERING_COLOR_LOCATION;
		}

		public static INamespacePrefixedString getPropertyHoveringBorderColorLocation() {
			return PROPERTY_HOVERING_BORDER_COLOR_LOCATION;
		}

		public static INamespacePrefixedString getPropertyPressedColorLocation() {
			return PROPERTY_PRESSED_COLOR_LOCATION;
		}

		public static INamespacePrefixedString getPropertyPressedBorderColorLocation() {
			return PROPERTY_PRESSED_BORDER_COLOR_LOCATION;
		}

		public static String getPropertyBaseColor() {
			return PROPERTY_BASE_COLOR;
		}

		public static String getPropertyBaseBorderColor() {
			return PROPERTY_BASE_BORDER_COLOR;
		}

		public static String getPropertyHoveringColor() {
			return PROPERTY_HOVERING_COLOR;
		}

		public static String getPropertyHoveringBorderColor() {
			return PROPERTY_HOVERING_BORDER_COLOR;
		}

		public static String getPropertyPressedColor() {
			return PROPERTY_PRESSED_COLOR;
		}

		public static String getPropertyPressedBorderColor() {
			return PROPERTY_PRESSED_BORDER_COLOR;
		}

		@Override
		@OverridingMethodsMustInvokeSuper
		public void initializeBindings(Supplier<@Nonnull ? extends Optional<? extends DisposableObserver<IBinderAction>>> binderObserverSupplier) {
			super.initializeBindings(binderObserverSupplier);
			BindingUtilities.actOnBinderObserverSupplier(binderObserverSupplier,
					() -> ImmutableBinderAction.bind(
							getBaseColor(), getBaseBorderColor(),
							getHoveringColor(), getHoveringBorderColor(),
							getPressedColor(), getPressedBorderColor()
					));
		}

		@Override
		@OverridingMethodsMustInvokeSuper
		public void cleanupBindings(Supplier<@Nonnull ? extends Optional<? extends DisposableObserver<IBinderAction>>> binderObserverSupplier) {
			super.cleanupBindings(binderObserverSupplier);
			BindingUtilities.actOnBinderObserverSupplier(binderObserverSupplier,
					() -> ImmutableBinderAction.unbind(
							getBaseColor(), getBaseBorderColor(),
							getHoveringColor(), getHoveringBorderColor(),
							getPressedColor(), getPressedBorderColor()
					));
		}

		protected IBindingField<Color> getBaseColor() {
			return baseColor;
		}

		protected IBindingField<Color> getBaseBorderColor() {
			return baseBorderColor;
		}

		protected IBindingField<Color> getHoveringColor() {
			return hoveringColor;
		}

		protected IBindingField<Color> getHoveringBorderColor() {
			return hoveringBorderColor;
		}

		protected IBindingField<Color> getPressedColor() { return pressedColor; }

		protected IBindingField<Color> getPressedBorderColor() {
			return pressedBorderColor;
		}

		@Override
		public void render(IUIComponentContext context, EnumRenderStage stage, C component, double partialTicks) {
			if (stage.isPreChildren()) {
				Shape relativeShape = IUIComponent.getShape(component);
				Color filled, border;
				if (component.getButtonStates().contains(IButtonState.PRESSING)) {
					filled = getPressedColor().getValue();
					border = getPressedBorderColor().getValue();
				} else if (component.getButtonStates().contains(IButtonState.HOVERING)) {
					filled = getHoveringColor().getValue();
					border = getHoveringBorderColor().getValue();
				} else {
					filled = getBaseColor().getValue();
					border = getBaseBorderColor().getValue();
				}
				try (AutoCloseableGraphics2D graphics = AutoCloseableGraphics2D.of(context.createGraphics())) {
					graphics.setColor(filled);
					graphics.fill(relativeShape);

					graphics.setColor(border);
					graphics.draw(relativeShape);
				}
			}
		}
	}
}
