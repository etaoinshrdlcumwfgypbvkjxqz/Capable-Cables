package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.client;

import com.google.common.base.Suppliers;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ModMarkers;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Immutable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.common.ModCommonProxy;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.proxies.impl.AbstractClientProxy;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConfiguration;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConstants;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.debug.UIMinecraftDebug;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.compile.EnumBuildType;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.minecraft.internationalization.MinecraftLocaleUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.templates.ConfigurationTemplate;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.impl.LoggingThrowableHandler;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.impl.ThreadLocalThrowableHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.*;
import org.slf4j.Marker;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Immutable
public final class ModClientProxy
		extends AbstractClientProxy {
	private static final Supplier<@Nonnull ModClientProxy> INSTANCE = Suppliers.memoize(ModClientProxy::new);

	private ModClientProxy() {
		super(ModCommonProxy.getInstance());
	}

	public static ModClientProxy getInstance() { return INSTANCE.get(); }

	@Override
	protected Marker getMarker() {
		return ModMarkers.getInstance().getMarkerLifecycleClient();
	}

	@Override
	protected void onConstruction() {
		UIConfiguration.MinecraftSpecific.onConstruction();
	}

	@Override
	protected void onFingerprintViolation(FMLFingerprintViolationEvent event) {}

	@Override
	protected void onSetup(FMLCommonSetupEvent event) {
		/* COMMENT
		 * Sneak the configuring in effectively-client common setup.
		 * There may be a better solution though...  suggestions welcome.
		 */
		ConfigurationTemplate.configureSafe(UIConfiguration.getInstance(),
				() -> new UIConfiguration.ConfigurationData(UIConfiguration.getBootstrapLogger(),
						new LoggingThrowableHandler<>(new ThreadLocalThrowableHandler<>(), UIConfiguration.getBootstrapLogger()),
						MinecraftLocaleUtilities::getCurrentLocale));
	}

	@Override
	protected void onInterModEnqueue(InterModEnqueueEvent event) {

	}

	@Override
	protected void onInterModProcess(InterModProcessEvent event) {

	}

	@Override
	protected void onLoadComplete(FMLLoadCompleteEvent event) {
		UIConfiguration.MinecraftSpecific.onLoadComplete();
	}

	@Override
	protected void onModIDMapping(FMLModIdMappingEvent event) {

	}

	@Override
	protected void onGatherData(GatherDataEvent event) {

	}

	@Override
	protected void onSetupSided(FMLClientSetupEvent event) {
		if (UIConstants.getBuildType() == EnumBuildType.DEBUG)
			UIMinecraftDebug.registerUIFactory();
	}
}
