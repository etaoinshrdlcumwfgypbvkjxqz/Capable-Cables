package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.extensions.caches;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConfiguration;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.IUIExtensionArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.UIExtensionConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.IUIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.IUIComponentManager;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.extensions.caches.IUICacheExtension;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.extensions.caches.IUICacheType;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.extensions.caches.UICacheRegistry;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.construction.UIImmutableExtensionArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.events.bus.UIEventBusEntryPoint;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.events.bus.UIAbstractComponentHierarchyChangeBusEvent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CapacityUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.CastUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.ConcurrencyUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.collections.CacheLoaderLoadedNullException;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.collections.CacheUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.impl.FunctionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.reactive.FunctionalNextSubscriber;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.reactive.ReactiveUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.references.OptionalWeakReference;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.def.IIdentifier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.events.impl.AutoSubscribingDisposable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.events.impl.EnumHookStage;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.events.impl.EventBusSubscriber;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.events.impl.ImmutableSubscribeEvent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.extensions.def.IExtensionContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.extensions.def.IExtensionType;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.extensions.impl.AbstractContainerAwareExtension;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.registration.def.IRegistryObject;
import net.minecraftforge.eventbus.api.EventPriority;
import sun.misc.Cleaner;

import java.util.Optional;

import static io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.SuppressWarningsUtilities.suppressBoxing;
import static io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.SuppressWarningsUtilities.suppressThisEscapedWarning;

public class UIDefaultCacheExtension
		extends AbstractContainerAwareExtension<IIdentifier, IExtensionContainer<IIdentifier>, IExtensionContainer<IIdentifier>>
		implements IUICacheExtension {
	private static final IUIExtensionArguments DEFAULT_ARGUMENTS = UIImmutableExtensionArguments.of(ImmutableMap.of(), IExtensionContainer.class, null);
	private final Cache<IIdentifier, Object> cache =
			CacheBuilder.newBuilder()
					.initialCapacity(CapacityUtilities.getInitialCapacitySmall())
					.expireAfterAccess(CacheUtilities.getCacheExpirationAccessDuration(), CacheUtilities.getCacheExpirationAccessTimeUnit())
					.concurrencyLevel(ConcurrencyUtilities.getSingleThreadThreadCount()).build();

	public UIDefaultCacheExtension() { this(getDefaultArguments()); }

	@UIExtensionConstructor
	public UIDefaultCacheExtension(@SuppressWarnings("unused") IUIExtensionArguments arguments) {
		super(CastUtilities.castUnchecked(IExtensionContainer.class)); // COMMENT should not matter in this case
	}

	protected static IUIExtensionArguments getDefaultArguments() { return DEFAULT_ARGUMENTS; }

	@Override
	public void onExtensionRemoved() {
		getDelegate().invalidateAll();
		super.onExtensionRemoved();
	}

	@Override
	public Cache<IIdentifier, Object> getDelegate() { return cache; }

	@Override
	public IExtensionType<IIdentifier, ?, IExtensionContainer<IIdentifier>> getType() { return StaticHolder.getType().getValue(); }

	public enum ComponentCache {
		;

		@SuppressWarnings({"AnonymousInnerClass", "AnonymousInnerClassMayBeStatic"})
		private static final IRegistryObject<IUICacheType<IUIComponentManager<?>, IUIComponent>> MANAGER =
				AssertionUtilities.assertNonnull(FunctionUtilities.apply(IUICacheType.generateKey("manager"),
						key -> UICacheRegistry.getInstance().register(key,
								new UIAbstractCacheType<OptionalWeakReference<? extends IUIComponentManager<?>>, IUIComponentManager<?>, IUIComponent>(key) {
									{
										OptionalWeakReference<? extends IUICacheType<?, IUIComponent>> thisRef =
												OptionalWeakReference.of(suppressThisEscapedWarning(() -> this));
										Cleaner.create(suppressThisEscapedWarning(() -> this),
												AutoSubscribingDisposable.of(UIEventBusEntryPoint.getBusPublisher(),
														ImmutableList.of(
																new EventBusSubscriber<UIAbstractComponentHierarchyChangeBusEvent.Parent>(
																		ImmutableSubscribeEvent.of(EventPriority.LOWEST, true),
																		ReactiveUtilities.decorateAsListener(
																				delegate -> FunctionalNextSubscriber.of(delegate,
																						event -> {
																							if (event.getStage() == EnumHookStage.POST)
																								thisRef.getOptional().ifPresent(t ->
																										t.invalidate(event.getComponent()));
																						}),
																				UIConfiguration.getInstance().getLogger()
																		)
																) {
																	@Override
																	public void onNext(UIAbstractComponentHierarchyChangeBusEvent.Parent event) {
																		onNextImpl(event);
																	}
																}
														)
												)::dispose);
									}

									@Override
									protected OptionalWeakReference<? extends IUIComponentManager<?>> load(IUIComponent container)
											throws CacheLoaderLoadedNullException {
										return IUIComponent.getYoungestParentInstanceOf(container, IUIComponentManager.class)
												.<OptionalWeakReference<IUIComponentManager<?>>>map(OptionalWeakReference::of)
												.orElseThrow(CacheLoaderLoadedNullException::new);
									}

									@Override
									protected Optional<? extends IUIComponentManager<?>> transform(IUIComponent container,
									                                                               @Nullable OptionalWeakReference<? extends IUIComponentManager<?>> value) throws ReloadException {
										if (value == null)
											return Optional.empty(); // COMMENT no result
										return Optional.of(value.getOptional()
												.orElseThrow(ReloadException::new)); // COMMENT stale or expired result, reload
									}
								})));
		@SuppressWarnings({"AnonymousInnerClass", "AnonymousInnerClassMayBeStatic"})
		private static final IRegistryObject<IUICacheType<Integer, IUIComponent>> Z =
				AssertionUtilities.assertNonnull(FunctionUtilities.apply(IUICacheType.generateKey("z"),
						key -> UICacheRegistry.getInstance().register(key,
								new UIAbstractCacheType.Identity<Integer, IUIComponent>(key) {
									{
										OptionalWeakReference<? extends IUICacheType<?, IUIComponent>> thisRef =
												OptionalWeakReference.of(suppressThisEscapedWarning(() -> this));
										Cleaner.create(suppressThisEscapedWarning(() -> this),
												AutoSubscribingDisposable.of(UIEventBusEntryPoint.getBusPublisher(),
														ImmutableList.of(
																new EventBusSubscriber<UIAbstractComponentHierarchyChangeBusEvent.Parent>(
																		ImmutableSubscribeEvent.of(EventPriority.LOWEST, true),
																		ReactiveUtilities.decorateAsListener(
																				delegate -> FunctionalNextSubscriber.of(delegate,
																						event -> {
																							if (event.getStage() == EnumHookStage.POST)
																								thisRef.getOptional().ifPresent(t ->
																										t.invalidate(event.getComponent()));
																						}),
																				UIConfiguration.getInstance().getLogger()
																		)
																) {
																	@Override
																	public void onNext(UIAbstractComponentHierarchyChangeBusEvent.Parent event) {
																		onNextImpl(event);
																	}
																}
														)
												)::dispose);
									}

									@Override
									public void invalidate(IUIComponent container) {
										super.invalidate(container);
										IUICacheType.invalidateChildrenImpl(container, this);
									}

									@Override
									protected Integer load(IUIComponent container) {
										// COMMENT 0 counts container already
										return suppressBoxing(Iterators.size(new IUIComponent.ParentIterator(container.getParent().orElse(null))));
									}
								})));

		public static IRegistryObject<IUICacheType<IUIComponentManager<?>, IUIComponent>> getManager() {
			return MANAGER;
		}

		public static IRegistryObject<IUICacheType<Integer, IUIComponent>> getZ() {
			return Z;
		}
	}
}
