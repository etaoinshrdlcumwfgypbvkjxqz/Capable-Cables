package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Immutable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConfiguration;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.binding.IUIPropertyMappingValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.binding.UIProperty;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.IUIComponentArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.IUIRendererArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.UIComponentConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.construction.UIRendererConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.IUIComponentContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.components.embed.IUIComponentEmbed;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.events.IUIEvent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.rendering.IUIComponentRenderer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.core.mvvm.views.rendering.IUIRendererContainerContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.UINamespaceUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.binding.UIImmutablePropertyMappingValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.embed.UIChildlessComponentEmbed;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.embed.UIComponentEmbedUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.rendering.UIDefaultRendererContainerContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.shapes.descriptors.SupplierShapeDescriptor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.utilities.EnumUIAxis;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.utilities.EnumUISide;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CastUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.EnumTimeUnit;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.collections.MapBuilderUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.reactive.LoggingDisposableObserver;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.references.OptionalWeakReference;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.core.INamespacePrefixedString;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.core.IValueHolder;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.impl.ConstantValue;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.impl.DefaultValueHolder;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.impl.ImmutableNamespacePrefixedString;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.core.IBinderAction;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.core.fields.IBindingField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.core.fields.IField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.core.traits.IHasBindingKey;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.BindingUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.ImmutableBinderAction;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.fields.RangedBindingField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.methods.ImmutableBindingMethodDestination;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.inputs.core.IInputPointerDevice;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.optionals.impl.OptionalUtilities;
import io.reactivex.rxjava3.observers.DisposableObserver;
import org.jetbrains.annotations.NonNls;
import org.slf4j.Logger;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.function.Supplier;

import static io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.SuppressWarningsUtilities.*;

// COMMENT note: while initially writing this, the author's mind had gone insane and started doing copy-and-paste programming
public class UIScrollbarComponent
		extends UIShapeComponent {
	public static final @NonNls String PROPERTY_SCROLL_DIRECTION = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.scrollbar.direction";
	public static final @NonNls String PROPERTY_SCROLL_RELATIVE_PROGRESS = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.scrollbar.relative_progress";
	public static final @NonNls String PROPERTY_THUMB_RELATIVE_SIZE = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.scrollbar.thumb.relative_size";
	public static final @NonNls String PROPERTY_THUMB_MOVEMENT_RELATIVE_SPEED = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.scrollbar.thumb.movement.relative_speed";
	public static final @NonNls String PROPERTY_BUTTON_SIZE = IHasBindingKey.StaticHolder.DEFAULT_PREFIX + "property.scrollbar.buttons.size";
	public static final @NonNls String EMBED_THUMB_NAME = "thumb";
	public static final @NonNls String EMBED_BUTTON_1_NAME = "button1";
	public static final @NonNls String EMBED_BUTTON_2_NAME = "button2";
	public static final @NonNls String INTERNAL_BINDING_BUTTON_ACTIVATE_PREFIX = "scrollbar.button.activate";
	public static final @NonNls String INTERNAL_BINDING_BUTTON_ACTIVATED_PREFIX = "scrollbar.button.activated";
	public static final @NonNls String INTERNAL_BINDING_BUTTON_CANCELED_PREFIX = "scrollbar.button.canceled";
	private static final INamespacePrefixedString PROPERTY_SCROLL_DIRECTION_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyScrollDirection());
	private static final INamespacePrefixedString PROPERTY_SCROLL_RELATIVE_PROGRESS_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyScrollRelativeProgress());
	private static final INamespacePrefixedString PROPERTY_THUMB_RELATIVE_SIZE_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyThumbRelativeSize());
	private static final INamespacePrefixedString PROPERTY_THUMB_MOVEMENT_RELATIVE_SPEED_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyThumbMovementRelativeSpeed());
	private static final INamespacePrefixedString PROPERTY_BUTTON_SIZE_LOCATION = ImmutableNamespacePrefixedString.of(getPropertyButtonSize());
	@SuppressWarnings("UnstableApiUsage")
	private static final @Immutable Map<EnumUIAxis, EnumUISide> AXIS_TO_CONVENTIONAL_DIRECTION_MAP = Maps.immutableEnumMap(ImmutableMap.of(
			EnumUIAxis.X, EnumUISide.RIGHT,
			EnumUIAxis.Y, EnumUISide.DOWN
	));
	@UIProperty(PROPERTY_SCROLL_DIRECTION)
	private final IBindingField<EnumUISide> scrollDirection;
	@UIProperty(PROPERTY_SCROLL_RELATIVE_PROGRESS)
	private final IBindingField<Double> scrollRelativeProgress; // COMMENT relative to the whole bar
	@UIProperty(PROPERTY_THUMB_RELATIVE_SIZE)
	private final IBindingField<Double> thumbRelativeSize;
	@UIProperty(PROPERTY_THUMB_MOVEMENT_RELATIVE_SPEED)
	private final IBindingField<Double> thumbMovementRelativeSpeed; // COMMENT per second, relative to the thumb relative size
	@UIProperty(PROPERTY_BUTTON_SIZE)
	private final IBindingField<Double> buttonSize;
	private final IUIComponentEmbed<UIButtonComponent> thumbEmbed;
	private final IUIComponentEmbed<UIButtonComponent> button1Embed;
	private final IUIComponentEmbed<UIButtonComponent> button2Embed;
	private final IUIRendererContainerContainer<IUIComponentRenderer<?>> rendererContainerContainer;
	private final Set<EnumScrollbarState> scrollbarStates = Collections.newSetFromMap(
			MapBuilderUtilities.newMapMakerSingleThreaded().initialCapacity(EnumScrollbarState.values().length).makeMap()
	);
	private @Nullable Double thumbPullingProgressStart;
	private @Nullable Double thumbPullingStartingPoint;

	@UIComponentConstructor
	public UIScrollbarComponent(IUIComponentArguments arguments) {
		super(arguments);

		this.rendererContainerContainer =
				UIDefaultRendererContainerContainer.ofDefault(arguments.getRendererName().orElse(null), suppressThisEscapedWarning(() -> this),
						CastUtilities.castUnchecked(DefaultRenderer.class));

		Map<INamespacePrefixedString, ? extends IUIPropertyMappingValue> mappings = arguments.getMappingsView();
		OptionalWeakReference<UIScrollbarComponent> thisReference = OptionalWeakReference.of(suppressThisEscapedWarning(() -> this));

		this.scrollDirection = IUIPropertyMappingValue.createBindingField(EnumUISide.class, ConstantValue.of(EnumUISide.DOWN),
				mappings.get(getPropertyScrollDirectionLocation()));
		this.scrollRelativeProgress = RangedBindingField.of(
				IUIPropertyMappingValue.createBindingField(Double.class, ConstantValue.of(suppressBoxing(0D)),
						mappings.get(getPropertyScrollRelativeProgressLocation())),
				ConstantValue.of(suppressBoxing(0D)),
				() -> {
					// COMMENT see 'PropertyThumbRelativeSizeObserver'
					return thisReference.getOptional()
							.map(UIScrollbarComponent::getThumbRelativeSize)
							.map(IBindingField::getValue)
							.map(thumbRelativeSizeValue -> suppressBoxing(
									1D - suppressUnboxing(thumbRelativeSizeValue)
							))
							.orElse(null);
				}
		);
		this.thumbRelativeSize = IUIPropertyMappingValue.createBindingField(Double.class, ConstantValue.of(suppressBoxing(1D)),
				mappings.get(getPropertyThumbRelativeSizeLocation()));
		this.thumbMovementRelativeSpeed = IUIPropertyMappingValue.createBindingField(Double.class, ConstantValue.of(suppressBoxing(1D)),
				mappings.get(getPropertyThumbMovementRelativeSpeedLocation()));
		this.buttonSize = IUIPropertyMappingValue.createBindingField(Double.class, ConstantValue.of(suppressBoxing(10D)),
				mappings.get(getPropertyButtonSizeLocation()));

		this.thumbRelativeSize.getField().getNotifier()
				.subscribe(new PropertyThumbRelativeSizeObserver(suppressThisEscapedWarning(() -> this), UIConfiguration.getInstance().getLogger()));

		/* TODO slay the dragons
		====================================================================================================
		In front of you is a dragon, sleeping quietly.  It is huge, like a mountain.
		The scales of the dragon is as black as the cold universe,
		yet it shines like a star under the light of the warm star.
		You could not see its head or tail.[1]
		It evokes fear deep within your heart.

		It has not discovered you.

		You decide that fighting the dragon head on is not a great idea, so you start to think of solutions.
		Dragons, like all earth-like animals, have a heart.  Therefore, you decide to target the heart.

		However, such a large animal has a really large volume, so it would be difficult to find the heart.

		You find it after following some strong 'code smell', seemingly coming from the heart.
		With that, you take your trusty 'refactor' sword and stab the skin as hard as you can.

		...
		   ...
		......
		      ...
		...   ...
		   ......
		.........

		The dragon falls out of the world.
		====================================================================================================
		[1] Definitely not because we were too lazy.
		 */
		this.thumbEmbed = new UIChildlessComponentEmbed<>(UIButtonComponent.class, suppressThisEscapedWarning(() -> this),
				arguments.computeEmbedArgument(getEmbedThumbName(),
						arguments1 -> new UIButtonComponent(
								thisReference.getOptional()
										.<IUIComponentArguments>map(this1 -> {
											String keyPrefix = UINamespaceUtilities.getUniqueInternalBindingNamespace(this1);

											INamespacePrefixedString onActivateKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonActivatePrefix() + ".thumb");
											INamespacePrefixedString onActivatedKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonActivatedPrefix() + ".thumb");
											INamespacePrefixedString onCanceledKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonCanceledPrefix() + ".thumb");

											IValueHolder<IUIComponentArguments> pointerArguments = DefaultValueHolder.of(arguments1);

											if (UIComponentEmbedUtilities.withMappingsIfUndefined(pointerArguments,
													ImmutableMap.of(
															UIButtonComponent.getMethodOnActivateLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onActivateKey),
															UIButtonComponent.getMethodOnActivatedLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onActivatedKey),
															UIButtonComponent.getMethodOnCanceledLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onCanceledKey)
													))) {
												this1.getEmbedBindings()
														.addAll(ImmutableList.of(
																ImmutableBindingMethodDestination.of(UIButtonComponent.IUIEventActivate.class,
																		onActivateKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonActivate(EnumScrollbarState.PULLING, event))),
																ImmutableBindingMethodDestination.of(IUIEvent.class,
																		onActivatedKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonFinish(EnumScrollbarState.PULLING, event))),
																ImmutableBindingMethodDestination.of(IUIEvent.class,
																		onCanceledKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonFinish(EnumScrollbarState.PULLING, event)))
														));
											}

											return pointerArguments.getValue()
													.orElseThrow(AssertionError::new);
										}).orElse(arguments1)
						),
						new SupplierShapeDescriptor<>(() ->
								thisReference.getOptional()
										.map(this1 -> {
											Shape this1Shape = IUIComponent.getShape(this1);
											EnumUISide endingScrollDirection = this1.getScrollDirection().getValue();
											EnumUISide startingScrollDirection = endingScrollDirection.getOpposite().orElseThrow(IllegalStateException::new);
											@SuppressWarnings("AutoUnboxing") double buttonSize = this1.getButtonSize().getValue();
											@SuppressWarnings("AutoUnboxing") double thumbRelativeSize = this1.getThumbRelativeSize().getValue();
											@SuppressWarnings("AutoUnboxing") double scrollRelativeProgress = this1.getScrollRelativeProgress().getValue();

											Rectangle2D this1ShapeBounds = this1Shape.getBounds2D();

											Rectangle2D result = new Rectangle2D.Double(0D, 0D,
													this1ShapeBounds.getWidth(), this1ShapeBounds.getHeight());
											double thumbMovementSize = getThumbMovementSize(this1);

											// COMMENT the width
											double thumbThicknessStart = startingScrollDirection.getValue(result)
													+ startingScrollDirection.inwardsBy(buttonSize + thumbMovementSize * scrollRelativeProgress)
													.orElseThrow(IllegalStateException::new);
											startingScrollDirection.setValue(result, thumbThicknessStart);
											endingScrollDirection.setValue(result, thumbThicknessStart
													+ startingScrollDirection.inwardsBy(thumbMovementSize * thumbRelativeSize)
													.orElseThrow(IllegalStateException::new));

											return result;
										})
										.orElseGet(Rectangle2D.Double::new)
						)));
		// COMMENT button 1, initial position of the thumb
		this.button1Embed = new UIChildlessComponentEmbed<>(UIButtonComponent.class, suppressThisEscapedWarning(() -> this),
				arguments.computeEmbedArgument(getEmbedButton1Name(),
						arguments1 -> new UIButtonComponent(
								thisReference.getOptional()
										.<IUIComponentArguments>map(this1 -> {
											String keyPrefix = UINamespaceUtilities.getUniqueInternalBindingNamespace(this1);

											INamespacePrefixedString onActivateKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonActivatePrefix() + ".1");
											INamespacePrefixedString onActivatedKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonActivatedPrefix() + ".1");
											INamespacePrefixedString onCanceledKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonCanceledPrefix() + ".1");

											IValueHolder<IUIComponentArguments> pointerArguments = DefaultValueHolder.of(arguments1);

											if (UIComponentEmbedUtilities.withMappingsIfUndefined(pointerArguments,
													ImmutableMap.of(
															UIButtonComponent.getMethodOnActivateLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onActivateKey),
															UIButtonComponent.getMethodOnActivatedLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onActivatedKey),
															UIButtonComponent.getMethodOnCanceledLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onCanceledKey)
													))) {
												this1.getEmbedBindings()
														.addAll(ImmutableList.of(
																ImmutableBindingMethodDestination.of(UIButtonComponent.IUIEventActivate.class,
																		onActivateKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonActivate(EnumScrollbarState.SCROLLING_BACKWARD, event))),
																ImmutableBindingMethodDestination.of(IUIEvent.class,
																		onActivatedKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonFinish(EnumScrollbarState.SCROLLING_BACKWARD, event))),
																ImmutableBindingMethodDestination.of(IUIEvent.class,
																		onCanceledKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonFinish(EnumScrollbarState.SCROLLING_BACKWARD, event)))
														));
											}

											return pointerArguments.getValue()
													.orElseThrow(AssertionError::new);
										}).orElse(arguments1)
						),
						new SupplierShapeDescriptor<>(() ->
								thisReference.getOptional()
										.map(this1 -> {
											Shape this1Shape = IUIComponent.getShape(this1);
											EnumUISide endingScrollDirection = this1.getScrollDirection().getValue();
											EnumUISide startingScrollDirection = endingScrollDirection.getOpposite().orElseThrow(IllegalStateException::new);
											@SuppressWarnings("AutoUnboxing") double buttonSize = this1.getButtonSize().getValue();

											Rectangle2D this1ShapeBounds = this1Shape.getBounds2D();

											Rectangle2D result = new Rectangle2D.Double(0D, 0D,
													this1ShapeBounds.getWidth(), this1ShapeBounds.getHeight());
											endingScrollDirection.setValue(result,
													startingScrollDirection.getValue(result)
															+ startingScrollDirection.inwardsBy(buttonSize)
															.orElseThrow(IllegalStateException::new));

											return result;
										})
										.orElseGet(Rectangle2D.Double::new)
						)));
		// COMMENT button 2, position limit of the thumb
		this.button2Embed = new UIChildlessComponentEmbed<>(UIButtonComponent.class, suppressThisEscapedWarning(() -> this),
				arguments.computeEmbedArgument(getEmbedButton2Name(),
						arguments1 -> new UIButtonComponent(
								thisReference.getOptional()
										.<IUIComponentArguments>map(this1 -> {
											String keyPrefix = UINamespaceUtilities.getUniqueInternalBindingNamespace(this1);

											INamespacePrefixedString onActivateKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonActivatePrefix() + ".2");
											INamespacePrefixedString onActivatedKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonActivatedPrefix() + ".2");
											INamespacePrefixedString onCanceledKey =
													ImmutableNamespacePrefixedString.of(keyPrefix,
															getInternalBindingButtonCanceledPrefix() + ".2");

											IValueHolder<IUIComponentArguments> pointerArguments = DefaultValueHolder.of(arguments1);

											if (UIComponentEmbedUtilities.withMappingsIfUndefined(pointerArguments,
													ImmutableMap.of(
															UIButtonComponent.getMethodOnActivateLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onActivateKey),
															UIButtonComponent.getMethodOnActivatedLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onActivatedKey),
															UIButtonComponent.getMethodOnCanceledLocation(),
															() -> UIImmutablePropertyMappingValue.of(null, onCanceledKey)
													))) {
												this1.getEmbedBindings()
														.addAll(ImmutableList.of(
																ImmutableBindingMethodDestination.of(UIButtonComponent.IUIEventActivate.class,
																		onActivateKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonActivate(EnumScrollbarState.SCROLLING_FORWARD, event))),
																ImmutableBindingMethodDestination.of(IUIEvent.class,
																		onActivatedKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonFinish(EnumScrollbarState.SCROLLING_FORWARD, event))),
																ImmutableBindingMethodDestination.of(IUIEvent.class,
																		onCanceledKey,
																		event -> thisReference.getOptional()
																				.ifPresent(this2 -> this2.onButtonFinish(EnumScrollbarState.SCROLLING_FORWARD, event)))
														));
											}

											return pointerArguments.getValue()
													.orElseThrow(AssertionError::new);
										}).orElse(arguments1)
						),
						new SupplierShapeDescriptor<>(() ->
								thisReference.getOptional()
										.map(this1 -> {
											Shape this1Shape = IUIComponent.getShape(this1);
											EnumUISide endingScrollDirection = this1.getScrollDirection().getValue();
											EnumUISide startingScrollDirection = endingScrollDirection.getOpposite().orElseThrow(IllegalStateException::new);
											@SuppressWarnings("AutoUnboxing") double buttonSize = this1.getButtonSize().getValue();

											Rectangle2D this1ShapeBounds = this1Shape.getBounds2D();

											Rectangle2D result = new Rectangle2D.Double(0D, 0D,
													this1ShapeBounds.getWidth(), this1ShapeBounds.getHeight());
											startingScrollDirection.setValue(result,
													endingScrollDirection.getValue(result)
															+ endingScrollDirection.inwardsBy(buttonSize)
															.orElseThrow(IllegalStateException::new));

											return result;
										})
										.orElseGet(Rectangle2D.Double::new)
						)));
	}

	public static INamespacePrefixedString getPropertyScrollDirectionLocation() {
		return PROPERTY_SCROLL_DIRECTION_LOCATION;
	}

	public static INamespacePrefixedString getPropertyScrollRelativeProgressLocation() {
		return PROPERTY_SCROLL_RELATIVE_PROGRESS_LOCATION;
	}

	protected IBindingField<Double> getThumbRelativeSize() {
		return thumbRelativeSize;
	}

	public static INamespacePrefixedString getPropertyThumbRelativeSizeLocation() {
		return PROPERTY_THUMB_RELATIVE_SIZE_LOCATION;
	}

	public static INamespacePrefixedString getPropertyThumbMovementRelativeSpeedLocation() {
		return PROPERTY_THUMB_MOVEMENT_RELATIVE_SPEED_LOCATION;
	}

	public static INamespacePrefixedString getPropertyButtonSizeLocation() {
		return PROPERTY_BUTTON_SIZE_LOCATION;
	}

	public static @NonNls String getEmbedThumbName() {
		return EMBED_THUMB_NAME;
	}

	public static @NonNls String getInternalBindingButtonActivatePrefix() {
		return INTERNAL_BINDING_BUTTON_ACTIVATE_PREFIX;
	}

	public static @NonNls String getInternalBindingButtonActivatedPrefix() {
		return INTERNAL_BINDING_BUTTON_ACTIVATED_PREFIX;
	}

	public static @NonNls String getInternalBindingButtonCanceledPrefix() {
		return INTERNAL_BINDING_BUTTON_CANCELED_PREFIX;
	}

	protected void onButtonActivate(EnumScrollbarState state, UIButtonComponent.IUIEventActivate event) {
		UIButtonComponent.UIDefaultEventActivate.handleMouseEventCommonly(event); // COMMENT only accept mouse
		if (event.isDefaultPrevented())
			getScrollbarStates().add(state);
	}

	protected void onButtonFinish(EnumScrollbarState state, IUIEvent event) {
		getScrollbarStates().remove(state);
		setThumbPullingProgressStart(null);
		setThumbPullingStartingPoint(null);
		event.preventDefault();
	}

	protected IBindingField<EnumUISide> getScrollDirection() {
		return scrollDirection;
	}

	protected IBindingField<Double> getButtonSize() {
		return buttonSize;
	}

	protected IBindingField<Double> getScrollRelativeProgress() {
		return scrollRelativeProgress;
	}

	@SuppressWarnings("MagicNumber")
	protected static double getThumbMovementSize(UIScrollbarComponent instance) {
		Shape shape = IUIComponent.getShape(instance);
		EnumUISide endingScrollDirection = instance.getScrollDirection().getValue();
		@SuppressWarnings("AutoUnboxing") double buttonSize = instance.getButtonSize().getValue();

		return Math.max(endingScrollDirection.getAxis().getSize(shape.getBounds2D()) - buttonSize * 2D, 0D);
	}

	public static @NonNls String getEmbedButton1Name() {
		return EMBED_BUTTON_1_NAME;
	}

	public static @NonNls String getEmbedButton2Name() {
		return EMBED_BUTTON_2_NAME;
	}

	@SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
	protected Set<EnumScrollbarState> getScrollbarStates() {
		return scrollbarStates;
	}

	public static @NonNls String getPropertyScrollDirection() {
		return PROPERTY_SCROLL_DIRECTION;
	}

	public static @NonNls String getPropertyScrollRelativeProgress() {
		return PROPERTY_SCROLL_RELATIVE_PROGRESS;
	}

	public static @NonNls String getPropertyThumbRelativeSize() {
		return PROPERTY_THUMB_RELATIVE_SIZE;
	}

	public static @NonNls String getPropertyButtonSize() {
		return PROPERTY_BUTTON_SIZE;
	}

	public static @NonNls String getPropertyThumbMovementRelativeSpeed() {
		return PROPERTY_THUMB_MOVEMENT_RELATIVE_SPEED;
	}

	public static @Immutable Map<EnumUIAxis, EnumUISide> getAxisToConventionalDirectionMap() {
		return AXIS_TO_CONVENTIONAL_DIRECTION_MAP;
	}

	@Override
	protected Iterable<? extends IUIComponentEmbed<?>> getComponentEmbeds() {
		return Iterables.concat(super.getComponentEmbeds(),
				ImmutableList.of(
						getThumbEmbed(),
						getButton1Embed(), getButton2Embed()
				));
	}

	@Override
	public void initializeBindings(Supplier<@Nonnull ? extends Optional<? extends DisposableObserver<IBinderAction>>> binderObserverSupplier) {
		super.initializeBindings(binderObserverSupplier);
		BindingUtilities.actOnBinderObserverSupplier(binderObserverSupplier, () ->
				ImmutableBinderAction.bind(
						getScrollDirection(), getScrollRelativeProgress(),
						getThumbRelativeSize(), getThumbMovementRelativeSpeed(),
						getButtonSize()
				));
	}

	@Override
	public void cleanupBindings() {
		getBinderObserverSupplierHolder().getValue().ifPresent(binderObserverSupplier ->
				BindingUtilities.actOnBinderObserverSupplier(binderObserverSupplier, () ->
						ImmutableBinderAction.unbind(
								getScrollDirection(), getScrollRelativeProgress(),
								getThumbRelativeSize(), getThumbMovementRelativeSpeed(),
								getButtonSize()
						)));
		super.cleanupBindings();
	}

	@Override
	@OverridingMethodsMustInvokeSuper
	public void update0(IUIComponentContext context) {
		super.update0(context);
		Set<EnumScrollbarState> scrollbarStates = getScrollbarStates();
		if (scrollbarStates.contains(EnumScrollbarState.PULLING)) {
			if (!getThumbPullingProgressStart().isPresent())
				setThumbPullingProgressStart(getScrollRelativeProgress().getValue());
			context.getViewContext().getInputDevices().getPointerDevice()
					.map(IInputPointerDevice::getPositionView)
					.ifPresent(pointerPosition -> {
						EnumUISide scrollDirection = getScrollDirection().getValue();
						double thumbPullingCoordinate = scrollDirection.getAxis().getCoordinate(pointerPosition);

						if (!getThumbPullingStartingPoint().isPresent())
							setThumbPullingStartingPoint(suppressBoxing(thumbPullingCoordinate));

						double lastThumbPullingCoordinate = getThumbPullingStartingPoint()
								.orElse(thumbPullingCoordinate); // COMMENT no starting point - no delta
						double thumbPullingDiff = thumbPullingCoordinate - lastThumbPullingCoordinate;
						double thumbRelativeDiff = thumbPullingDiff / getThumbMovementSize(this);
						double directionalThumbRelativeDiff = scrollDirection.outwardsBy(thumbRelativeDiff)
								.orElseThrow(IllegalStateException::new);
						assert getThumbPullingProgressStart().isPresent();
						double relativeProgress = getThumbPullingProgressStart().getAsDouble() + directionalThumbRelativeDiff;

						getScrollRelativeProgress().setValue(suppressBoxing(relativeProgress));
					});
		} else if (!scrollbarStates.isEmpty()) {
			// COMMENT we are not pulling the thumb
			double nominalThumbDiff = suppressUnboxing(getThumbMovementRelativeSpeed().getValue())
					* suppressUnboxing(getThumbRelativeSize().getValue())
					* getUpdateTimeDelta().orElse(0L)
					* EnumTimeUnit.getScale(EnumTimeUnit.SECOND, EnumTimeUnit.NANOSECOND);
			double thumbDiff = scrollbarStates.stream().unordered()
					.mapToDouble(scrollbarState -> scrollbarState.getDiff(nominalThumbDiff))
					.sum();
			getScrollRelativeProgress().setValue(suppressBoxing(
					suppressUnboxing(getScrollRelativeProgress().getValue())
							+ thumbDiff
			));
		}
	}

	protected OptionalDouble getThumbPullingProgressStart() {
		return OptionalUtilities.ofDouble(thumbPullingProgressStart);
	}

	protected OptionalDouble getThumbPullingStartingPoint() {
		return OptionalUtilities.ofDouble(thumbPullingStartingPoint);
	}

	protected IBindingField<Double> getThumbMovementRelativeSpeed() {
		return thumbMovementRelativeSpeed;
	}

	protected void setThumbPullingStartingPoint(@Nullable Double thumbPullingStartingPoint) {
		this.thumbPullingStartingPoint = thumbPullingStartingPoint;
	}

	protected void setThumbPullingProgressStart(@Nullable Double thumbPullingProgressStart) {
		this.thumbPullingProgressStart = thumbPullingProgressStart;
	}

	protected IUIComponentEmbed<? extends UIButtonComponent> getThumbEmbed() {
		return thumbEmbed;
	}

	protected IUIComponentEmbed<? extends UIButtonComponent> getButton1Embed() {
		return button1Embed;
	}

	protected IUIComponentEmbed<? extends UIButtonComponent> getButton2Embed() {
		return button2Embed;
	}

	@Override
	protected IUIRendererContainerContainer<IUIComponentRenderer<?>> getRendererContainerContainer() {
		return rendererContainerContainer;
	}

	public enum EnumScrollbarState {
		PULLING {
			@Override
			public double getDiff(double diff) {
				return 0D;
			}
		},
		SCROLLING_FORWARD {
			@Override
			public double getDiff(double diff) {
				return diff;
			}
		},
		SCROLLING_BACKWARD {
			@Override
			public double getDiff(double diff) {
				return -diff;
			}
		},
		;

		public abstract double getDiff(double diff);
	}

	public static class DefaultRenderer<C extends UIScrollbarComponent>
			extends UIShapeComponent.DefaultRenderer<C> {
		@UIRendererConstructor
		public DefaultRenderer(IUIRendererArguments arguments) {
			super(arguments);
		}
	}

	public static class PropertyThumbRelativeSizeObserver
			extends LoggingDisposableObserver<Double> {
		private final OptionalWeakReference<UIScrollbarComponent> owner;

		public PropertyThumbRelativeSizeObserver(UIScrollbarComponent owner, Logger logger) {
			super(logger);
			this.owner = OptionalWeakReference.of(owner);
		}

		@Override
		public void onNext(@Nonnull Double value) {
			super.onNext(value);
			getOwner()
					.map(UIScrollbarComponent::getScrollRelativeProgress)
					.ifPresent(IField::updateValue);
		}

		protected Optional<? extends UIScrollbarComponent> getOwner() {
			return owner.getOptional();
		}
	}
}
