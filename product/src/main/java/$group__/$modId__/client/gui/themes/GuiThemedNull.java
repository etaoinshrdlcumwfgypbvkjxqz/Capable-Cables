package $group__.$modId__.client.gui.themes;

import $group__.$modId__.annotations.OverridingStatus;
import $group__.$modId__.utilities.extensions.IStructure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import static $group__.$modId__.utilities.Constants.PACKAGE;
import static $group__.$modId__.utilities.extensions.IStrictEquals.areEqual;
import static $group__.$modId__.utilities.extensions.IStrictHashCode.getHashCode;
import static $group__.$modId__.utilities.extensions.IStrictToString.getToStringString;
import static $group__.$modId__.utilities.helpers.Casts.castUncheckedUnboxedNonnull;
import static $group__.$modId__.utilities.helpers.PreconditionsExtension.requireRunOnceOnly;

@Immutable
public final class GuiThemedNull<T extends ITheme<T>> implements IStructure<GuiThemedNull<T>, GuiThemedNull<T>>,
		IThemed<T> {
	/* SECTION static variables */

	public static final GuiThemedNull<?> INSTANCE = new GuiThemedNull<>();

	private static final Logger LOGGER = LogManager.getLogger(GuiThemedNull.class);


	/* SECTION constructors */

	protected GuiThemedNull() { requireRunOnceOnly(LOGGER); }

	@SuppressWarnings("unused")
	protected GuiThemedNull(GuiThemedNull<T> copy) { this(); }


	/* SECTION static getters & setters */

	public static <T extends ITheme<T>> GuiThemedNull<T> getInstance() { return castUncheckedUnboxedNonnull(INSTANCE); }


	/* SECTION methods */

	@Nullable
	@Override
	public T getTheme() { return null; }

	@Override
	public boolean trySetTheme(@Nullable T theme) { return false; }


	@Override
	@OverridingStatus(group = PACKAGE)
	@Deprecated
	public final GuiThemedNull<T> toImmutable() { return this; }

	@Override
	@OverridingStatus(group = PACKAGE)
	public final boolean isImmutable() { return true; }


	@Override
	public final String toString() { return getToStringString(this, super.toString()); }

	@Override
	public final int hashCode() { return getHashCode(this, super::hashCode); }

	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	@Override
	public final boolean equals(Object obj) { return areEqual(this, obj, super::equals); }
}