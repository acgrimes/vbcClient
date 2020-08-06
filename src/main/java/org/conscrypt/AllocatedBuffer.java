package org.conscrypt;


import java.nio.ByteBuffer;

/**
 * A buffer that was allocated by a {@link BufferAllocator}.
 */
public abstract class AllocatedBuffer {
    /**
     * Returns the {@link ByteBuffer} that backs this buffer.
     */
    public abstract ByteBuffer nioBuffer();

    /**
     * Returns the current instance for backward compatibility.
     *
     * @deprecated this method is not used
     */
    @Deprecated
    public AllocatedBuffer retain() {
        // Do nothing.
        return this;
    }

    /**
     * Returns the delegate {@link ByteBuffer} for reuse or garbage collection.
     *
     * @return this {@link AllocatedBuffer} instance
     */
    public abstract AllocatedBuffer release();

    /**
     * Creates a new {@link AllocatedBuffer} that is backed by the given {@link ByteBuffer}.
     */
    public static AllocatedBuffer wrap(final ByteBuffer buffer) {

        return new AllocatedBuffer() {

            @Override
            public ByteBuffer nioBuffer() {
                return buffer;
            }

            @Override
            public AllocatedBuffer release() {
                // Do nothing.
                return this;
            }
        };
    }
}