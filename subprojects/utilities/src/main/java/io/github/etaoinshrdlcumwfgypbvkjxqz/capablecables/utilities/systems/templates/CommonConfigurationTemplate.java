package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.templates;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nonnull;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.dynamic.StackTraceUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.interfaces.IRecordCandidate;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.internationalization.ChangingLocaleResourceBundle;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.logging.ProperLoggingEventBuilderLogger;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.def.IThrowableHandler;
import org.slf4j.Logger;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public abstract class CommonConfigurationTemplate<D extends CommonConfigurationTemplate.ConfigurationData>
		extends ConfigurationTemplate<D> {
	@Nullable
	private volatile Logger logger;
	@Nullable
	private volatile IThrowableHandler<Throwable> throwableHandler;
	@Nullable
	private volatile Supplier<@Nonnull ? extends Locale> localeSupplier;

	protected CommonConfigurationTemplate() {}

	public static ResourceBundle createBundle(CommonConfigurationTemplate<?> instance) {
		return new ChangingLocaleResourceBundle.Builder()
				.setBaseName(StackTraceUtilities.getCallerClass())
				.setLocaleSupplier(instance.getLocaleSupplier())
				.build();
	}

	public Supplier<@Nonnull ? extends Locale> getLocaleSupplier() { return AssertionUtilities.assertNonnull(localeSupplier); }

	protected void setLocaleSupplier(@Nullable Supplier<@Nonnull ? extends Locale> localeSupplier) { this.localeSupplier = localeSupplier; }

	public Logger getLogger() { return AssertionUtilities.assertNonnull(logger); }

	protected void setLogger(@Nullable Logger logger) { this.logger = logger; }

	public IThrowableHandler<Throwable> getThrowableHandler() { return AssertionUtilities.assertNonnull(throwableHandler); }

	protected void setThrowableHandler(@Nullable IThrowableHandler<Throwable> throwableHandler) { this.throwableHandler = throwableHandler; }

	@Override
	@OverridingMethodsMustInvokeSuper
	protected void configure0(D data) {
		setLogger(new ProperLoggingEventBuilderLogger<>(data.getLogger()));
		setThrowableHandler(data.getThrowableHandler());
		setLocaleSupplier(data.getLocaleSupplier());
	}

	public static abstract class ConfigurationData implements IRecordCandidate {
		private final Logger logger;
		private final IThrowableHandler<Throwable> throwableHandler;
		private final Supplier<@Nonnull ? extends Locale> localeSupplier;

		protected ConfigurationData(Logger logger,
		                            IThrowableHandler<Throwable> throwableHandler,
		                            Supplier<@Nonnull ? extends Locale> localeSupplier) {
			this.logger = logger;
			this.throwableHandler = throwableHandler;
			this.localeSupplier = localeSupplier;
		}

		protected Logger getLogger() { return logger; }

		protected IThrowableHandler<Throwable> getThrowableHandler() { return throwableHandler; }

		protected Supplier<@Nonnull ? extends Locale> getLocaleSupplier() { return localeSupplier; }
	}
}
