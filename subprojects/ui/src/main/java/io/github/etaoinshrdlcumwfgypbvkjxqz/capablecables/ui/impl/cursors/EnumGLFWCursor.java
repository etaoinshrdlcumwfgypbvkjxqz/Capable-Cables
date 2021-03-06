package io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.impl.cursors;

import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.annotations.Nullable;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.ui.UIConfiguration;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.AssertionUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.graphics.impl.UIObjectUtilities;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.io.def.ICursor;
import io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.systems.throwable.impl.ThrowableUtilities;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.jetbrains.annotations.NonNls;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import sun.misc.Cleaner;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.function.LongSupplier;

import static io.github.etaoinshrdlcumwfgypbvkjxqz.capablecables.utilities.SuppressWarningsUtilities.suppressThisEscapedWarning;

public enum EnumGLFWCursor
		implements ICursor {
	DEFAULT_CURSOR(() -> MemoryUtil.NULL),
	STANDARD_ARROW_CURSOR(() -> GLFW.glfwCreateStandardCursor(GLFW.GLFW_ARROW_CURSOR)),
	STANDARD_I_BEAM_CURSOR(() -> GLFW.glfwCreateStandardCursor(GLFW.GLFW_IBEAM_CURSOR)),
	STANDARD_CROSS_HAIR_CURSOR(() -> GLFW.glfwCreateStandardCursor(GLFW.GLFW_CROSSHAIR_CURSOR)),
	STANDARD_HAND_CURSOR(() -> GLFW.glfwCreateStandardCursor(GLFW.GLFW_HAND_CURSOR)),
	STANDARD_RESIZE_HORIZONTAL_CURSOR(() -> GLFW.glfwCreateStandardCursor(GLFW.GLFW_HRESIZE_CURSOR)),
	STANDARD_RESIZE_VERTICAL_CURSOR(() -> GLFW.glfwCreateStandardCursor(GLFW.GLFW_VRESIZE_CURSOR)),
	EXTENSION_RESIZE_NW_SE_CURSOR(() -> {
		InputStream input = AssertionUtilities.assertNonnull(EnumGLFWCursor.class.getResourceAsStream(StaticHolder.getExtensionResizeNwSeCursorPath()));
		try {
			try {
				return createCursor(input, new Point2D.Double(8, 8));
			} catch (IOException e) {
				throw ThrowableUtilities.propagate(e);
			}
		} finally {
			ThrowableUtilities.runQuietly(input::close, IOException.class, UIConfiguration.getInstance().getThrowableHandler());
		}
	}),
	EXTENSION_RESIZE_NE_SW_CURSOR(() -> {
		InputStream input = AssertionUtilities.assertNonnull(EnumGLFWCursor.class.getResourceAsStream(StaticHolder.getExtensionResizeNeSwCursorPath()));
		try {
			try {
				return createCursor(input, new Point2D.Double(8, 8));
			} catch (IOException e) {
				throw ThrowableUtilities.propagate(e);
			}
		} finally {
			ThrowableUtilities.runQuietly(input::close, IOException.class, UIConfiguration.getInstance().getThrowableHandler());
		}
	}),
	;

	private final ICursor delegate;

	EnumGLFWCursor(LongSupplier handle) {
		ICursor delegate = ImmutableGLFWCursor.of(handle.getAsLong());
		this.delegate = delegate;
		Cleaner.create(suppressThisEscapedWarning(() -> this),
				delegate::close);
	}

	@Override
	public String toString() {
		return getDelegate().toString();
	}

	protected ICursor getDelegate() {
		return delegate;
	}

	@SuppressWarnings("EmptyMethod")
	public static void initializeClass() {}

	public static long createCursor(InputStream inputStream, Point2D hotspot)
			throws IOException {
		Point2D hotspotF = UIObjectUtilities.floorPoint(hotspot, new Point2D.Double());
		@Nullable ByteBuffer buffer = null;
		try {
			buffer = TextureUtil.readToBuffer(inputStream);
			buffer.rewind();
			try (MemoryStack stack = MemoryStack.stackPush()) {
				IntBuffer width = stack.mallocInt(1), height = stack.mallocInt(1), channels = stack.mallocInt(1);
				@Nullable ByteBuffer pixels = STBImage.stbi_load_from_memory(buffer, width, height, channels, 4);
				if (pixels == null)
					throw new IOException(STBImage.stbi_failure_reason());
				GLFWImage image = GLFWImage.mallocStack(stack)
						.width(width.get())
						.height(height.get())
						.pixels(pixels);
				return GLFW.glfwCreateCursor(image, (int) hotspotF.getX(), (int) hotspotF.getY());
			}
		} finally {
			MemoryUtil.memFree(buffer);
		}
	}

	public enum StaticHolder {
		;

		@SuppressWarnings("HardcodedFileSeparator")
		@NonNls
		public static final String EXTENSION_RESIZE_NW_SE_CURSOR_PATH = "aero_nwse/32x32.png";
		@SuppressWarnings("HardcodedFileSeparator")
		@NonNls
		public static final String EXTENSION_RESIZE_NE_SW_CURSOR_PATH = "aero_nesw/32x32.png";

		public static String getExtensionResizeNwSeCursorPath() { return EXTENSION_RESIZE_NW_SE_CURSOR_PATH; }

		public static String getExtensionResizeNeSwCursorPath() { return EXTENSION_RESIZE_NE_SW_CURSOR_PATH; }
	}

	@Override
	public long getHandle() {
		return getDelegate().getHandle();
	}

	@Override
	public void close() {
		getDelegate().close();
	}
}
