package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.debug;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConfiguration;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIFacade;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.animations.IUIAnimationControl;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.IUIRendererArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.construction.UIRendererConstructor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.models.IUIModel;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.IUIView;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.IUIComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.IUIComponentContext;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.IUIComponentManager;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.components.IUIComponentView;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.mvvm.views.events.IUIEvent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.shapes.descriptors.IShapeDescriptor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.shapes.interactions.IShapeDescriptorProvider;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.def.theming.IUITheme;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.UINamespaceUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.animations.controls.UIStandardAnimationControlFactory;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.animations.easings.EnumUICommonAnimationEasing;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.animations.targets.UIAnimationTargetUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.construction.UIImmutableComponentArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.construction.UIImmutableViewArguments;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.viewmodels.UIDefaultViewModel;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.UIDefaultComponentView;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.impl.UIButtonComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.impl.UIDefaultComponentManager;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.mvvm.views.components.impl.UIWindowComponent;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.AbstractParsedSupplier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.ParserUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.parsers.UIDefaultComponentSchemaHolder;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.shapes.descriptors.RectangularShapeDescriptor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.theming.UIEmptyTheme;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.minecraft.impl.mvvm.adapters.AbstractContainerScreenAdapter;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.minecraft.impl.mvvm.viewmodels.extensions.UIAbstractMinecraftTickerExtension;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.ColorUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.functions.impl.OneUseRunnable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.minecraft.client.ui.MinecraftTextComponentUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.def.IIdentifier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.structures.impl.ImmutableIdentifier;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.IBinder;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.IBinding.EnumBindingType;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.IBindingAction;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.fields.IBindingField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.def.methods.IBindingMethodDestination;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.BindingUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.DefaultBinder;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.ImmutableBindingAction;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.fields.ImmutableBindingField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.fields.MemoryObservableField;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.binding.impl.methods.ImmutableBindingMethodDestination;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.extensions.def.IExtension;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.extensions.def.IExtensionContainer;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.io.def.IPointerDevice;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.time.impl.Tickers;
import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NonNls;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ConcurrentModificationException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.SuppressWarningsUtilities.suppressBoxing;
import static io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.SuppressWarningsUtilities.suppressThisEscapedWarning;
import static java.util.Objects.requireNonNull;

public enum UIMinecraftDebug {
	;

	@NonNls
	public static final String PATH = "debug_ui";
	private static final ITextComponent DISPLAY_NAME = MinecraftTextComponentUtilities.getEmpty();

	public static Block getBlockEntry() { return DebugBlock.getInstance(); }

	public static TileEntityType<DebugTileEntity> getTileEntityEntry() { return DebugTileEntity.getTypeInstance(); }

	public static ContainerType<DebugContainer> getContainerEntry() { return DebugContainer.getTypeInstance(); }

	@SuppressWarnings("deprecation")
	@OnlyIn(Dist.CLIENT)
	public static void registerUIFactory() {
		{
			ContainerType<DebugContainer> containerType = DebugContainer.getTypeInstance();
			DebugUI uiFactory = DebugUI.INSTANCE;
			// COMMENT this should mean that classloading is executed in parallel
			DeferredWorkQueue.runLater(() ->
					ScreenManager.registerFactory(containerType, uiFactory));
		}
	}

	public static String getPath() { return PATH; }

	public static ITextComponent getDisplayName() { return DISPLAY_NAME; }

	@OnlyIn(Dist.CLIENT)
	private enum DebugUI
			implements ScreenManager.IScreenFactory<DebugContainer, AbstractContainerScreenAdapter<?, DebugContainer>> {
		INSTANCE,
		;

		public static final @NonNls String COMPONENT_TEST_XML_PATH = "component-UI-test.xml";
		public static final @NonNls String COMPONENT_TEST_THEME_XML_PATH = "components-UI-test-theme.xml";
		private static final Supplier<IUIComponentView<?, ?>> VIEW_SUPPLIER = AbstractParsedSupplier.Functional.of(
				() -> ParserUtilities.parseJAXBResource(UIDefaultComponentSchemaHolder.getContext(),
						DebugUI.class,
						getComponentTestXMLPath(),
						UIConfiguration.getInstance().getThrowableHandler()),
				parsed -> (IUIComponentView<?, ?>) ParserUtilities.transformJAXBResource(UIDefaultComponentSchemaHolder.getAdapterRegistry(), parsed)
		);
		private static final Supplier<IUITheme> THEME_SUPPLIER = AbstractParsedSupplier.Functional.of(
				() -> ParserUtilities.parseJAXBResource(UIDefaultComponentSchemaHolder.getContext(),
						DebugUI.class,
						getComponentTestThemeXMLPath(),
						UIConfiguration.getInstance().getThrowableHandler()),
				parsed -> (IUITheme) ParserUtilities.transformJAXBResource(UIDefaultComponentSchemaHolder.getAdapterRegistry(), parsed)
		);

		static {
			// COMMENT early check
			IUIView.withThemes(getViewSupplier().get(), Iterators.singletonIterator(getThemeSupplier().get()));
		}

		private boolean useCode = false;

		private static String getComponentTestXMLPath() { return COMPONENT_TEST_XML_PATH; }

		private static String getComponentTestThemeXMLPath() { return COMPONENT_TEST_THEME_XML_PATH; }

		private static AbstractContainerScreenAdapter<?, DebugContainer> createUI(DebugContainer container) {
			IBinder binder = new DefaultBinder();
			// COMMENT Color <-> Integer
			binder.addTransformer(EnumBindingType.FIELD, (@Nonnull Color color) -> suppressBoxing(color.getRGB()));
			binder.addTransformer(EnumBindingType.FIELD, ColorUtilities::ofRGBA);

			AbstractContainerScreenAdapter<?, DebugContainer> screen =
					UIFacade.Minecraft.createScreen(
							getDisplayName(),
							UIFacade.Minecraft.createInfrastructure(
									getViewSupplier().get(),
									new ViewModel(),
									binder
							),
							container);
			IUITheme theme = getThemeSupplier().get();

			IUIView<?> view = screen.getInfrastructure().getView();
			IUIView.getThemeStack(view).push(theme);

			return screen;
		}

		private static Supplier<IUIComponentView<?, ?>> getViewSupplier() {
			return VIEW_SUPPLIER;
		}

		@Override
		public @Nonnull AbstractContainerScreenAdapter<?, DebugContainer> create(DebugContainer container, PlayerInventory inv, ITextComponent title) {
			return isUseCode() ? createCodeUI(container) : createUI(container);
		}

		public boolean isUseCode() {
			return useCode;
		}

		@SuppressWarnings("unused")
		public void setUseCode(boolean useCode) {
			this.useCode = useCode;
		}

		private static AbstractContainerScreenAdapter<?, DebugContainer> createCodeUI(DebugContainer container) {
			// COMMENT component
			IUIComponentView<?, IUIComponentManager<Rectangle2D>> view;
			{
				UIDefaultComponentManager<Rectangle2D> componentManager =
						new UIDefaultComponentManager<>(UIImmutableComponentArguments.of(null,
								ImmutableMap.of(),
								new RectangularShapeDescriptor<>(new Rectangle(0, 0, 100, 100)),
								null,
								ImmutableMap.of()));
				{
					UIWindowComponent window =
							new UIWindowComponent(UIImmutableComponentArguments.of(null,
									ImmutableMap.of(),
									new RectangularShapeDescriptor<>(new Rectangle(0, 0, 100, 100)),
									null,
									ImmutableMap.of()));
					IUIComponent.addContentChildren(componentManager, Iterators.singletonIterator(window));
				}
				view = UIDefaultComponentView.of(UIImmutableViewArguments.of(ImmutableMap.of()),
						componentManager);
			}

			// COMMENT theme
			IUIView.getThemeStack(view).push(new UIEmptyTheme());

			return UIFacade.Minecraft.createScreen(
					getDisplayName(),
					UIFacade.Minecraft.createInfrastructure(
							view,
							new ViewModel(),
							new DefaultBinder()
					),
					container);
		}

		private static Supplier<IUITheme> getThemeSupplier() {
			return THEME_SUPPLIER;
		}

		@SuppressWarnings("unused")
		private static final class CustomWindowComponentRenderer<C extends UIWindowComponent>
				extends UIWindowComponent.DefaultRenderer<C>
				implements IShapeDescriptorProvider {
			public static final int CURSOR_SHAPE_RADIUS = 10;

			private Color cursorHighlighterColor = Color.WHITE;
			private final IUIAnimationControl animations =
					UIStandardAnimationControlFactory.createSimple(UIStandardAnimationControlFactory.EnumDirection.ALTERNATE,
							UIAnimationTargetUtilities.compose(ImmutableList.of(
									UIAnimationTargetUtilities.range(rgb -> setCursorHighlighterColor(new Color((int) rgb)), 0L, 0xFFFFFFL, EnumUICommonAnimationEasing.LINEAR),
									UIAnimationTargetUtilities.range(UIAnimationTargetUtilities.ShapeDescriptors.translateY(suppressThisEscapedWarning(() -> this)), 0, 100, EnumUICommonAnimationEasing.IN_OUT_BOUNCE)
							)),
							Tickers.SYSTEM,
							true,
							TimeUnit.SECONDS.toNanos(5L),
							TimeUnit.SECONDS.toNanos(1L),
							TimeUnit.SECONDS.toNanos(2L),
							UIStandardAnimationControlFactory.getInfiniteLoop());

			@UIRendererConstructor
			public CustomWindowComponentRenderer(IUIRendererArguments arguments) {
				super(arguments);
			}

			@Override
			@SuppressWarnings({"rawtypes", "RedundantSuppression"})
			public void onRendererAdded(C container) {
				super.onRendererAdded(container);
				getAnimations().reset();
				getContainer()
						.flatMap(IUIComponent::getManager)
						.flatMap(IUIComponentManager::getView)
						.map(IUIView::getAnimationController)
						.ifPresent(animationController ->
								animationController.add(Iterators.singletonIterator(getAnimations())));
			}

			protected IUIAnimationControl getAnimations() { return animations; }

			@Override
			@SuppressWarnings({"rawtypes", "RedundantSuppression"})
			public void onRendererRemoved() {
				getContainer()
						.flatMap(IUIComponent::getManager)
						.flatMap(IUIComponentManager::getView)
						.map(IUIView::getAnimationController)
						.ifPresent(animationController ->
								animationController.remove(Iterators.singletonIterator(getAnimations())));
				getAnimations().reset();
				super.onRendererRemoved();
			}

			@Override
			public void render(IUIComponentContext context, EnumRenderStage stage) {
				super.render(context, stage);
				if (stage == EnumRenderStage.PRE_CHILDREN) {
					context.getViewContext().getInputDevices().getPointerDevice()
							.map(IPointerDevice::getPositionView)
							.ifPresent(pointerPosition -> {
								Shape relativeShape = new Ellipse2D.Double(
										pointerPosition.getX() - CURSOR_SHAPE_RADIUS, pointerPosition.getY() - CURSOR_SHAPE_RADIUS,
										CURSOR_SHAPE_RADIUS << 1, CURSOR_SHAPE_RADIUS << 1);
								IUIComponentContext.withGraphics(context, graphics -> {
									graphics.setColor(getCursorHighlighterColor());
									graphics.fill(relativeShape);
								});
							});
				}
			}

			protected Color getCursorHighlighterColor() { return cursorHighlighterColor; }

			protected void setCursorHighlighterColor(Color cursorHighlighterColor) { this.cursorHighlighterColor = cursorHighlighterColor; }

			@Override
			public IShapeDescriptor<?> getShapeDescriptor() {
				return getContainer().orElseThrow(AssertionError::new).getShapeDescriptor();
			}

			@Override
			public boolean modifyShape(BooleanSupplier action) throws ConcurrentModificationException {
				return getContainer().orElseThrow(AssertionError::new).modifyShape(action);
			}

			@Override
			public boolean isModifyingShape() {
				return getContainer().orElseThrow(AssertionError::new).isModifyingShape();
			}

			@Override
			public Shape getAbsoluteShape() throws IllegalStateException { return getContainer().orElseThrow(AssertionError::new).getAbsoluteShape(); }
		}

		@OnlyIn(Dist.CLIENT)
		private static final class ViewModel
				extends UIDefaultViewModel<Model> {
			@SuppressWarnings("AutoBoxing")
			private final IBindingField<Integer> anchoredWindowBorderColor = ImmutableBindingField.of(
					ImmutableIdentifier.ofInterning(UINamespaceUtilities.getRendererBindingNamespace(), "anchoredWindowBorderColor"),
					new MemoryObservableField<>(Integer.class, ColorUtilities.getColorless().getRGB()));
			private final IBindingMethodDestination<UIButtonComponent.IUIEventActivate> buttonOnActivate = ImmutableBindingMethodDestination.of(
					UIButtonComponent.IUIEventActivate.class,
					ImmutableIdentifier.ofInterning(UINamespaceUtilities.getViewBindingNamespace(), "buttonOnActivate"),
					UIButtonComponent.UIDefaultEventActivate::handleEventCommonly);
			private final Random random = new Random();
			private boolean anchoredWindowFlickering = false;
			private final IBindingMethodDestination<IUIEvent> buttonOnActivated = ImmutableBindingMethodDestination.of(
					IUIEvent.class,
					ImmutableIdentifier.ofInterning(UINamespaceUtilities.getViewBindingNamespace(), "buttonOnActivated"),
					this::onButtonActivated);

			private final Runnable extensionsInitializer;

			private ViewModel() {
				super(new Model());

				this.extensionsInitializer = new OneUseRunnable(() ->
						IExtensionContainer.addExtensionChecked(this, MinecraftTickerExtension.of()));
			}

			protected boolean isAnchoredWindowFlickering() { return anchoredWindowFlickering; }

			protected void setAnchoredWindowFlickering(boolean anchoredWindowFlickering) { this.anchoredWindowFlickering = anchoredWindowFlickering; }

			protected IBindingField<Integer> getAnchoredWindowBorderColor() { return anchoredWindowBorderColor; }

			protected Random getRandom() { return random; }

			protected void onButtonActivated(IUIEvent e) { setAnchoredWindowFlickering(!isAnchoredWindowFlickering()); }

			@Override
			@OverridingMethodsMustInvokeSuper
			public void initializeBindings(Supplier<@Nonnull ? extends Optional<? extends Consumer<? super IBindingAction>>> bindingActionConsumerSupplier) {
				super.initializeBindings(bindingActionConsumerSupplier);
				BindingUtilities.supplyBindingAction(bindingActionConsumerSupplier,
						() -> ImmutableBindingAction.bind(ImmutableList.of(
								getAnchoredWindowBorderColor(),
								getButtonOnActivate(), getButtonOnActivated()
						))
				);
			}

			@SuppressWarnings("unused")
			protected IBindingMethodDestination<UIButtonComponent.IUIEventActivate> getButtonOnActivate() { return buttonOnActivate; }

			@SuppressWarnings("unused")
			protected IBindingMethodDestination<IUIEvent> getButtonOnActivated() { return buttonOnActivated; }

			@Override
			@OverridingMethodsMustInvokeSuper
			public void cleanupBindings() {
				getBindingActionConsumerSupplierHolder().getValue().ifPresent(bindingActionConsumer ->
						BindingUtilities.supplyBindingAction(bindingActionConsumer,
								() -> ImmutableBindingAction.unbind(ImmutableList.of(
										getAnchoredWindowBorderColor(),
										getButtonOnActivate(), getButtonOnActivated()
								))
						));
				super.cleanupBindings();
			}

			@Override
			protected ConcurrentMap<IIdentifier, IExtension<? extends IIdentifier, ?>> getExtensions() {
				extensionsInitializer.run();
				return super.getExtensions();
			}

			@OnlyIn(Dist.CLIENT)
			private static final class MinecraftTickerExtension
					extends UIAbstractMinecraftTickerExtension<ViewModel> {
				private MinecraftTickerExtension() {
					super(ViewModel.class);
				}

				private static MinecraftTickerExtension of() {
					return new MinecraftTickerExtension();
				}

				@Override
				public void tick() {
					getContainer().ifPresent(container -> {
						if (container.isAnchoredWindowFlickering())
							container.getAnchoredWindowBorderColor().setValue(suppressBoxing(container.getRandom().nextInt()));
					});
				}
			}
		}

		@OnlyIn(Dist.CLIENT)
		private static final class Model
				implements IUIModel {}
	}

	private static final class DebugContainer extends Container {
		private static final Supplier<@Nonnull ContainerType<DebugContainer>> TYPE_INSTANCE = Suppliers.memoize(() ->
				IForgeContainerType.create((windowID, inv, data) ->
						new DebugContainer(windowID, AssertionUtilities.assertNonnull(inv.player.getEntityWorld().getTileEntity(data.readBlockPos())))));
		private final TileEntity tileEntity;

		private DebugContainer(int id, TileEntity tileEntity) {
			super(getTypeInstance(), id);
			this.tileEntity = tileEntity;
		}

		private static ContainerType<DebugContainer> getTypeInstance() { return AssertionUtilities.assertNonnull(TYPE_INSTANCE.get()); }

		@Override
		public boolean canInteractWith(PlayerEntity playerIn) {
			return isWithinUsableDistance(IWorldPosCallable.of(requireNonNull(tileEntity.getWorld()), tileEntity.getPos()), playerIn, DebugBlock.getInstance());
		}
	}

	private static final class DebugBlock extends Block {
		private static final Supplier<@Nonnull DebugBlock> INSTANCE = Suppliers.memoize(DebugBlock::new);

		private DebugBlock() {
			super(Properties.from(Blocks.STONE));
		}

		private static DebugBlock getInstance() { return INSTANCE.get(); }

		@Override
		public boolean hasTileEntity(BlockState state) { return true; }

		@Override
		public TileEntity createTileEntity(BlockState state, IBlockReader world) { return new DebugTileEntity(); }

		@SuppressWarnings("deprecation")
		@Override
		@Deprecated
		public @Nonnull ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
			if (!worldIn.isRemote()) {
				TileEntity tileEntity = AssertionUtilities.assertNonnull(worldIn.getTileEntity(pos));
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
			}
			return ActionResultType.SUCCESS;
		}
	}

	private static final class DebugTileEntity extends TileEntity implements INamedContainerProvider {
		private static final Supplier<@Nonnull TileEntityType<DebugTileEntity>> TYPE_INSTANCE = Suppliers.memoize(() ->
				TileEntityType.Builder.create(DebugTileEntity::new, DebugBlock.getInstance()).build(null));

		private DebugTileEntity() {
			super(getTypeInstance());
		}

		private static TileEntityType<DebugTileEntity> getTypeInstance() {
			return AssertionUtilities.assertNonnull(TYPE_INSTANCE.get());
		}

		@Override
		public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
			return DebugContainer.getTypeInstance().create(id, playerInventory, new PacketBuffer(Unpooled.buffer(Long.BYTES)).writeBlockPos(getPos()));
		}

		@Override
		public @Nonnull ITextComponent getDisplayName() { return UIMinecraftDebug.getDisplayName(); }
	}
}
